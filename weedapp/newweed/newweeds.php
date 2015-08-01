<?PHP
require_once("./include/membersite_config.php");

if(!$fgmembersite->CheckLogin())
{
    $fgmembersite->RedirectToURL("login.php");
    exit;
}
 
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Welcome to our site</title>
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta http-equiv="Content-Type"
 content="text/html; charset=iso-8859-1">
  <link href="style/style.css" rel="stylesheet" type="text/css">
  <link href="style/tbstyle.css" rel="stylesheet" type="text/css">
  <script type="text/javascript">
    function myFun(e){
    if(!e.target)
        alert(e.srcElement.innerHTML);
    else
        alert(e.target.innerHTML);
    }
    </script>
</head>
<body>
<form name="input" action="<?php echo "responses.php?ms_weed_id=".$_REQUEST["ms_weed_id"]?>" method="post">
<div class="main">
<div class="page">
<div class="header">
<div class="header-img"><img src="images/header.jpg" alt="" height="170"
 width="800"></div>

</div>
<div class="content">
<div class="leftpanel">
<h2>Main Menu</h2>
<ul>
  
  <li><a href="newweed.php">Available New weed</a></li>  
</ul>
</div>
<div class="rightpanel">
<div class="rightbody">
<h1 class="title">Welcome to Weed identification</h1>
<p>

<div id='fg_membersite_content'>
<h2>Home Page</h2>
<p>Welcome back 
  <?= $fgmembersite->UserFullName(); ?>
  !  </p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>Available New Weeds </p>
<table class="altrowstable" id="requestlist"   onclick="myFun(event)">

<tr>
	
    <tr>
        <td>Request No.</td>
        <td>Image</td>
    </tr>
    
    <p class="body"><?php 
     $result=$fgmembersite->AvailableNewWeeds(); 
	      while($row = mysql_fetch_assoc($result)): ?>
    <tr>
        <td><?php echo $row['identification_id']; ?></td>
        <td><?php echo $row['weedphotos_name']; ?></td>
        <td style="visibility:hidden;"><?php echo $row['ms_weed_id']; ?></td>
        
    </tr>
    <?php endwhile; ?></td>>
</tr>

</table>
<label> Image ID </label>

   <!-- <select id='request_id' name='request_id'  title="Show response" onChange="window.location='responses.php?ms_weed_id='+this.value" style='width:200px;' >
    <?php
   // echo  "folder ".$folder;
    echo '<option name="image" >'."\n".
	$results=$fgmembersite->AvailableNewWeeds();
     dropdown($results, $selected=@$_POST['ms_weed_id']). 
     '</option>'     
  ?>
    </select> -->
    <select name="requests" onChange="window.location='newweeds.php?ms_weed_id='+this.value">
     <?php 
	 echo '<option value="select" >Select One</option>';
	 $results=$fgmembersite->AvailableNewWeeds(); 
     while ($row = mysql_fetch_array($results)) { 
    if($_REQUEST['ms_weed_id']==$row['ms_weed_id']){
        $selectCurrent=' selected';
    }else{
        $selectCurrent='';
    }
    echo '<option value="'.$row['ms_weed_id'].'" '.$selectCurrent.'>Request#'.$row['identification_id'].'</option>';
} 
echo '</select>'; 
?>
    <input type="submit" name="response" id="response" value="Show response"  onclick="window.location='responses.php?ms_weed_id='+this.value"/>     
    <!--<?php 
    if(isset($_GET['ms_weed_id']))
     {
    	$id=$_REQUEST['ms_weed_id'];
		echo "which id".$id."test";
		$result=$fgmembersite->ShowAllResponses($id); 
	      while($row = mysql_fetch_assoc($result)){ 
   
        echo $row['identification_id']; 
        echo $row['weedphotos_name'];
        echo $row['ms_weed_id'];   
        } 

    	}
    	
    	?>  -->
    
<p><a href='logout.php'>Logout</a></p>
</div></p>
</div>
</div>
</div>
<div class="footer">
<p>Copyright 2010. Designed by <a class="footer-link" target="_blank"
 href="http://www.mpss.csce.uark.edu/">MPSS lab</a></p>
</div>
</div>
</div>
<div><?php
  function dropdown($options_table, $selected) 
{ 
    $return = null; 
     while($row = mysql_fetch_assoc($options_table)) 
{  
        $return .= '<option value="'.$row['ms_weed_id'].'"'. 
                   (($row["ms_weed_id"] == $selected) ? ' selected="selected"' : null ). 
                   '>'.$row["identification_id"].'</option>'."\n"; 
    } 
    echo $return;
	return $return; 
	
} 


 ?></div>
</form>
</body>
</html>
