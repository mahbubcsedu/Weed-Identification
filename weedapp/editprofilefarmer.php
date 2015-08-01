<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$username = $_REQUEST["username"];
$firstname = $_REQUEST["firstname"];
$lastname = $_REQUEST["lastname"];
$county = $_REQUEST["county"];

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	$query="UPDATE farmer_profile SET 'first_name' = $firstname, 'last_name' = $lastname, 'county' = $county where 'user_name' = $username"; 
	$result=mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		echo json_encode(array('success' => 'yes'));
	}
	else
	{
		echo json_encode(array('success' => 'no'));
	}

	mysql_close();
}
?>
