
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
<form name="input" action="hitproperties-setup.php" method="post">
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

//$webpath="http://mpss.csce.uark.edu/".substr($folder,9,strlen($folder));


   ?>
   <table border="1" bordercolor="#FFCC00" style="background-color:#FFFFCC" width="100%" cellpadding="3" cellspacing="3">

   <tr>	

<tr>
		<td>
          <label>HIT Title</label>
          </td>
          <td>
          <input type="text" style="width: 100px; padding: 2px" value="Select the best match images from the checkbox list." name="_hittitle" ></input>
                   
        </td>
</tr>
<tr>
		<td>
          <label>HIT Description</label>
          </td>
          <td>
          <input type="text" value="The task is to select one image out of three that best represents the provided topic given a set of evaluation criteria." name="_hitdescription" ></input>
                   
        </td>
</tr>
<tr>
		<td>
          <label>Keywords for HIT(separated by comma)</label>
          </td>
          <td>
          <input type="text" value="best image, similar image, image search,image tag" name="_hitkeyword" ></input>
                   
        </td>
</tr>
<tr>
		<td>
          <label>Assignment Duration (in seconds)</label>
          </td>
          <td>
          <input type="text" value="3600" name="_hitassDuration" ></input>
                   
        </td>
</tr>
<tr>
		<td>
          <label>Num of Assingment per HIT</label>
          </td>
          <td>
          <input type="text" value="1" name="_hitNumOfAssign" ></input>
                   
        </td>
</tr>
<tr>
		<td>
          <label>Rewards per assignment(in dollar)</label>
          </td>
          <td>
          <input type="text" value="0.01" name="_hitrewards" ></input>
                   
        </td>
</tr>

	<tr>
		<td>
<label> HIT Properties Setup </label>
  </td>
          <td>
    <input type="submit" name="hitproperties" id="hitproperties" value="Update"  />   
    <?php 
    if(isset($_POST['hitproperties']))
     {
    	HITPropertiesSetup();
    	//echo $_POST['hit'];
    	//echo $_POST['gallery_id'];
    	}
    	
    	?> 
    </td>    
	</tr>
	</table>
    
 <?php


function HITPropertiesSetup(){

/*
 This class takes the command line input as

1. Title of the question
2. Description of the question
3. Keyword of the question
4. Reword for each assignment
5. Maximum assingment for a hit or to extend
6. Duration of the assignment
 
*/
//$cmd="/home/mahbabur/propertiesofHIT.sh" ." \"".$_POST['_hittitle']."\"  "."       ". "    \" ".$_POST['_hitdescription']."\"      "."  \"" .$_POST['_hitkeyword']."\"  "."  " .$_POST['_hitrewards']."    " .$_POST['_hitNumOfAssign'] ."    " .$_POST['_hitassDuration']; 
$cmd="/home/mahbabur/propertiesofHIT.sh" ." \"".$_POST['_hittitle']."\"  "." ". " \" ".$_POST['_hitdescription']."\" "."  \"" .$_POST['_hitkeyword']."\" "." " .$_POST['_hitrewards']." " .$_POST['_hitNumOfAssign'] ." " .$_POST['_hitassDuration'];
$files=array();
exec($cmd,$files);
echo $cmd;	
echo '<pre>';
print_r($files);
echo '</pre>';

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
