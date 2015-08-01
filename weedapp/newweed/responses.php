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
	function updateNewWeed()
	{
	echo "Hello";
	
	}
    </script>
  <style type="text/css">
<!--
.style1 {color: #8080FF}
-->
  </style>
</head>
<body>
<form name="input" action="<?php echo "responses.php?ms_weed_id=".$_GET["ms_weed_id"]?>" method="post">
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
  
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>Available New Weeds </p>
<?php
	echo 'Hello ' .$_GET["ms_weed_id"] . '!'; 
	  
    if(isset($_GET['ms_weed_id']))
     {
		$com_name=$latin_name=$weed_type=$life_cycle=$season=$site=$prof_control=$home_control=0;
    	$id=$_GET['ms_weed_id'];
		$firsttime=0;
		echo "<p>&nbsp;</p>";
		$result=$fgmembersite->ShowAllResponses($id);
		$numberofresponse=0; 
	      while($row = mysql_fetch_assoc($result)){ 
		$numberofresponse=$numberofresponse+1;
		if($firsttime<1)
		{
		echo "<img height=\"320\" width=\"240\" title=\"New weed Image\" src='".$fgmembersite->getCapturedPath().$row['weedphotos_name']."'/>";
		}
		echo "<h2>Expert Response#".$numberofresponse."</h2>";
        echo "<table class=\"altrowstable\" id=\"requestlist\"><tr><td>New weed Info.</td><td>New weed value.</td></tr><td><p class=\"body\">";        echo " Request No </td><td>".$row['identification_id']."</td></tr>"; 
        echo "<tr><td> Weed Photo Name:</td><td>".$row['weedphotos_name']."</td></tr>";
        echo  "<tr><td> Common Name:</td><td>".$row['common_name']."</td></tr>"; 
		echo  "<tr><td> Latin Name:</td><td>".$row['latin_name']."</td></tr>";
		echo  "<tr><td> Weed Type:</td><td>".$row['weed_type']."</td></tr>";  
		echo  "<tr><td> Life Cycle:</td><td>".$row['life_cycle']."</td></tr>";
		echo  "<tr><td> Season:</td><td>".$row['season']."</td></tr>";
		echo  "<tr><td> Site:</td><td>".$row['site']."</td></tr>";   
		echo  "<tr><td> Professional Control:</td><td>".$row['prof_control']."</td></tr>"; 
		echo  "<tr><td> Home Control:</td><td>".$row['home_control']."</td></tr>"; 
		echo "</table>";  
		$com_name=$row['common_name'];
		//echo $com_name;
		$latin_name=$row['latin_name'];
		//echo $latin_name;
		 $weed_type=$row['weed_type'];
		 $life_cycle=$row['life_cycle'];
		 $season=$row['season'];
		 $site=$row['site'];
		 $prof_control=$row['prof_control'];
		 $home_control=$row['home_control'];
		 $firsttime=$firsttime+1;
        } 
       echo "<h2>Actual Information:</h2>";
       echo "<table border=\"1\" bordercolor=\"#FFCC00\" style=\"background-color:#FFFFCC\" width=\"100%\" cellpadding=\"3\" cellspacing=\"3\">";
	   echo "<tr><td><label>Common Name:</label></td><td ><input type=\"text\" align=\"right\" value=\"".$com_name."\" name=\"common_name\" ></input></td></tr>";
	   echo "<tr><td><label> Latin Name:</label></td><td ><input type=\"text\"  align=\"right\" value=\"".$latin_name."\" name=\"latin_name\" ></input></td></tr>";
	   echo "<tr><td><label>Weed Type:</label></td><td ><input type=\"text\" align=\"right\" value=\"".$weed_type."\" name=\"weed_type\" ></input></td></tr>";
	   echo "<tr><td><label>Life Cycle:</label></td><td ><input type=\"text\" align=\"right\" value=\"".$life_cycle."\" name=\"life_cycle\" ></input></td></tr>";
	   echo "<tr><td><label>Season:</label></td><td ><input type=\"text\" align=\"right\" value=\"".$season."\" name=\"season\" ></input></td></tr>";
	   echo "<tr><td><label>Site:</label></td><td ><input type=\"text\" align=\"right\" value=\"".$site."\" name=\"site\" ></input></td></tr>";
	   echo "<tr><td><label>Professional Control:</label></td><td ><input type=\"text\" align=\"right\" value=\"".$prof_control."\" name=\"prof_control\" ></input></td></tr>";
	   echo "<tr><td ><label>Home Control:</label></td><td ><input type=\"text\"  value=\"".$home_control."\" name=\"home_control\" ></input></td></tr>";
	   
       echo "</table>";
    	} 
		
		 	
    ?>	 
   

<div>
  <input name="Submit" type="submit"  value="submit" />
  <?php 
  if(isset($_POST["Submit"]))
  {
  echo "trying";
   $response=$fgmembersite->updateNewWeedInfo($_GET['ms_weed_id'],$_POST['common_name'],$_POST['latin_name'],$_POST['weed_type'],$_POST['life_cycle'],    $_POST['season'],$_POST['site'],$_POST['prof_control'],$_POST['home_control']);
   echo $response;
}
  
  ?>
</div>
<p><a href='logout.php'>Logout</a></p>
</div></p>
</div>
</div>
</div>
<div class="footer">
<p>Copyright 2010. Designed by <a class="footer-link" target="_blank"
 href="http://www.mpss.csce.uark.edu/">free html templates</a></p>
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

