
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
</head>
<body>
<form name="input" action="hitstatus.php" method="post">
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
		<td>


</td></tr>



	<tr>
		<td>
<label> HIT ID </label>



    
    
    <input type="submit" name="hit_status" id="hit_status" value="Status"  />   
    <?php 
    if(isset($_POST['hit_status']))
     {
    	FindStatus();
    	//echo $_POST['hit'];
    	//echo $_POST['gallery_id'];
    	}
    	
    	?> 
    </td>    
	</tr>
	</table>
    

<?php  
function FindStatus(){
$cmd="/home/mahbabur/hitstatus.sh"; 
$files=array();
exec($cmd,$files);
//echo $cmd;	
echo '<pre>';
print_r($files);
echo '</pre>';

echo "<table class='altrowstable' id='alternatecolor'>\n\n";
$f = fopen("/var/www/smsdb/weed_app/executable/hitstatus.csv", "r");
while (($line = fgetcsv($f)) !== false) {
	echo "<tr>";
	foreach ($line as $cell) {
		echo "<td>" . htmlspecialchars($cell) . "</td>";
	}
	echo "<tr>\n";
}
fclose($f);
echo "\n</table>";

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
