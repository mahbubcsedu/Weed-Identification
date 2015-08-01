
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Welcome to our site</title>
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta http-equiv="Content-Type"
 content="text/html; charset=iso-8859-1">
  <link href="style/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<form name="input" action="hitcreate.php" method="post">
<div class="main">
<div class="page">
<div class="header">
<div class="header-img"><img src="images/header.jpg" alt="" height="170"
 width="800"></div>
<div class="topmenu">
<ul>
  <li><a href="login-home.php">Home</a></li>
  <li><a href="#">Resources</a></li>
  <li><a href="#">Services</a></li>
  <li><a href="#">About Us</a></li>
  <li><a href="#">Email Us</a></li>
</ul>
</div>
</div>
<div class="content">
<div class="leftpanel">
<h2>Main Menu</h2>
<ul>
   <li><a href="hitcreate.php">Create HIT</a></li>
  <li><a href="hitstatus.php">HIT Status</a></li>
  <li><a href="extending-assign.php">Extend HIT</a></li>
</ul>
</div>
<div class="rightpanel">
<div class="rightbody">
<h1 class="title">Welcome to our website</h1>
<p><?php
//$folder = './questionimages/'; 
require_once("./include/membersite_config.php");

if(!$fgmembersite->CheckLogin())
{
	$fgmembersite->RedirectToURL("login.php");
	exit;
}
//$fgmembersite->ImageUpdate("sample.jpg");
$folder = '/var/www/smsdb/weed_app/weedapp/Image_Capture/';
$webpath="http://mpss.csce.uark.edu/".substr($folder,9,strlen($folder));

if(isset($_GET["imageid"]))
{
	 $item = $_GET["imageid"];
	 if ($item == "")
	 {
	 	$item = 1;
	 }
}    
   
   ?>
   <table border="1" bordercolor="#FFCC00" style="background-color:#FFFFCC" width="100%" cellpadding="3" cellspacing="3">

   <tr>
		<td>


</td></tr>

<tr>
		<td>
          <label>Num of Images</label>
          <input type="text" value="5" name="NumOfImages" ></input>
                   
        </td>
</tr>


	<tr>
		<td>


<label> Image ID </label>

    <select id='gallery_id' name='gallery_id'  title="Creating HIT" onChange="window.location='hitcreate.php?imageid='+this.value" style='width:200px;' >
    <?php
    echo  "folder ".$folder;
    echo '<option name="image" >'."\n". 
     dropdown(image_filenames($folder), $selected=@$_POST['imageid']). 
     '</option>'
     
  ?>
    </select>
    
    <input type="submit" name="hitcreate" id="hitcreate" value="Create HIT"  />   
    <?php 
    if(isset($_POST['hitcreate']))
     {
	    //require_once("./include/membersite_config.php");
    	
		createhitfromhere();
		$fgmembersite->ImageUpdate($_POST['gallery_id']);
		//$fgmembersite->ImageUpdate($imageID);
		
    	//echo $_POST['NumOfImages'];
    	//echo $_POST['gallery_id'];
    	}
    	
    	?> 
    </td>    
	</tr>
	<tr>
	

    <td colspan='2' rowspan='2'>
   
    
    <img height="320" width="240" title='.$item' src=' <?=($item!=null)?$webpath.$item:$webpath.$_POST['gallery_id']?> '/>
    <script type="text/javascript">
     document.getElementById('gallery_id').value = "<?php echo $_GET['imageid'];?>";
     //document.getElementById('selectedvalue').value = "<?php echo $_GET['imageid'];?>";
      </script>
     
    </td></tr></table>
    
 <?php

 //
 function image_filenames($dir) 
{ 
    $handle = @opendir($dir) 
        or die("I cannot open the directory '<b>$dir</b>' for reading."); 
    $images = array(); 
    while (false !== ($file = readdir($handle))) 
    { 
        if (eregi('\.(jpg|gif|png)$', $file)) 
        { 
            $images[] = $file; 
        } 
    } 
    closedir($handle); 
    return $images; 
} 

function dropdown($options_array, $selected) 
{ 
    $return = null; 
    foreach($options_array as $option) 
    { 
        $return .= '<option value="'.$option.'"'. 
                   (($option == $selected) ? ' selected="selected"' : null ). 
                   '>'.$option.'</option>'."\n"; 
    } 
    return $return; 
} 
 
 
function numofimage($options_num, $selected) 
{ 
    $return = null; 
    foreach($options_num as $option) 
    { 
        $return .= '<option value="'.$option.'"'. 
                   (($option == $selected) ? ' selected="selected"' : null ). 
                   '>'.$option.'</option>'."\n"; 
    } 
    return $return; 
} 
 
function imagefieldUpdateinDatabase($imageID)
{
echo "can call from the calling function";
  echo $imageID;
  
  $fgmembersite->RedirectToURL("login.php");
  //$fgmembersite->ImageUpdate($imageID);
  echo "return value";
}
function createhitfromhere($item_image){
$cmd="php /var/www/smsdb/weed_app/weedapp/hitcreate.php /var/www/smsdb/weed_app/weedapp/Image_Capture/".$_POST['gallery_id']." 101 "." ".$_POST['NumOfImages'];
$files=array();

exec($cmd,$files);
//echo $cmd;	
echo '<pre>';
print_r($files);
echo '</pre>';
//echo $_POST['gallery_id']."lksjflasfj";
//imagefieldUpdateinDatabase($_POST['gallery_id']);
//echo "executed";
}
 ?></p>
</div>
</div>
</div>
<div class="footer">
<p>Copyright 2010. Designed by <a class="footer-link" target="_blank"
 href="http://www.mpss.csce.uark.edu/">free html templates</a></p>
</div>
</div>
</div>
</form>
</body>
</html>
