<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$base = $_REQUEST['image'];

require_once('db.inc.php');

$selectquery= "SELECT * FROM weed_photos ORDER BY weedphoto_id DESC LIMIT 1";
$result1=mysql_query($selectquery);

if($result1 && mysql_numrows($result1)>0)
{
	$unqidstr = mysql_result($result1,$i,"weedphoto_id");
	$unqid = (int)$unqidstr;
	$unqid = $unqid + 1;
	$filename = "photo".$unqid.".jpg";
}
else
{
	$unqidstr = "1";
	$filename = "photo".$unqidstr.".jpg";
}

try
{

	$binary = base64_decode($base);
	$file = fopen("./weedphotolog/$filename","wb");
	fwrite($file,$binary);
	fclose($file);
	echo "OK";
}
catch (Exception $e)
{
	echo 'Caught exception: ',  $e->getMessage(), "\n";
}
mysql_close();

?>
