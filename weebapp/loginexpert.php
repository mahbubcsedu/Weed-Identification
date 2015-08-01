<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$username = $_REQUEST["username"];
$fpassword = $_REQUEST["fpassword"];

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	$query="SELECT * from expert_profile where user_name='$username' AND fpassword='$fpassword'";
	$result = mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		echo "y";
	}
	else
	{
		echo "n";
	}

	mysql_close();
}

?>