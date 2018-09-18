<?
$user="username";
$password="";
$database="mobsys";
$host="hostname";
mysql_connect($host, $user, $password);
mysql_select_db($database) or die( "Unable to select database");
?>
