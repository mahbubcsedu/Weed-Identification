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
</head>
<body>
<form name="input" action="temp1.php" method="post">
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
  
  <li><a href="newweeds.php">Available New weed</a></li>  
</ul>
</div>
<div class="rightpanel">
<div class="rightbody">
<h1 class="title">Welcome to Weed identification</h1>
<p>

<div id='fg_membersite_content'>
<h2>Home Page</h2>
Welcome back <?= $fgmembersite->UserFullName(); ?>!




<table class="altrowstable" id="alternatecolor">

<tr>
	<td><h3 align="left">Available New Weeds</h3>
    <p class="body">There may have some new weeds available for review. After reviewing the the responses of the experts the admin can select the proper information and then submit to make this available for permanent use in weed database. </td>>
</tr>

</table>
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
</form>
</body>
</html>
