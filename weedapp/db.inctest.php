<?
$user="crowdsource";
$password="crowdsource123~";
$database="weedidapp";
$host="mpss.csce.uark.edu";
mysql_connect($host,$user,$password);
@mysql_select_db($database) or die( "Unable to select database");
?>
