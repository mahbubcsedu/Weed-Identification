<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$append = $_REQUEST["append"];

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	$selectquery= "SELECT * FROM weed_photos ORDER BY weedphoto_id DESC LIMIT 1";
	$result1=mysql_query($selectquery);

	if($result1 && mysql_numrows($result1)>0)
	{
		$unqidstr = mysql_result($result1,$i,"weedphoto_id");
		$unqid = (int)$unqidstr;
		$unqid = $unqid + 1;
		$filename = "sound".$unqid.".wav";
	}
	else
	{
		$unqidstr = "1";
		$filename = "sound".$unqidstr.".wav";
	}

	try
	{
		if(!$append)
		{
			$file = fopen("./weedsoundlog/$filename","w");
		}
		else
		{
			$file = fopen("./weedsoundlog/$filename","a");
		}

		$input = file_get_contents ("php://input");
		fwrite($file,$input);
		fclose($file);
		echo "OK";
	}
	catch (Exception $e)
	{
		echo 'Caught exception: ',  $e->getMessage(), "\n";
	}
	mysql_close();
}

?>
