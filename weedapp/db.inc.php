<?
$user="xxxx";
$password="xxxx";
$database="xxxx";
$host="localhost";
mysql_connect($host,$user,$password);
@mysql_select_db($database) or die( "Unable to select database");
?>
