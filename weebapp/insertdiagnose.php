<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

echo "test1";

$identificationid = $_REQUEST["identificationid"];
$diagnosis = $_REQUEST["diagnosis"];
$idanking = $_REQUEST["idanking"];
$idsent = $_REQUEST["idsent"];
$expertid = $_REQUEST["expertid"];
$newid = 1;


echo "test2";

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	$insertquery="UPDATE identification SET weed_id='$diagnosis', id_ranking='$idanking', expert_id='$expertid', newid='1' WHERE identification_id='$identificationid'";

	echo $insertquery;

	$result = mysql_query($insertquery);

	mysql_close();
}

?>

