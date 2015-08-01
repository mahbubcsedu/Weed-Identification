<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$userid = $_REQUEST["userid"];
$datenow = $_REQUEST["datenow"];
$notes = $_REQUEST["comment"];
$lat = $_REQUEST["latitude"];
$lon = $_REQUEST["longitude"];


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
		$filename = "photo".$unqid.".jpg";
		$weedphotoid = $unqid;
	}
	else
	{
		$unqidstr = "1";
		$filename = "photo".$unqidstr.".jpg";
		$weedphotoid = $unqidstr;
	}

	$imageinfo = getimagesize("./weedphotolog/$filename");
	$types = $imageinfo ['mime'];
	$sizes = $imageinfo ['3'];

	$notes = $comment;

	$insertquery="INSERT INTO weed_photos (weedphoto_type, weedphoto_size, weedphoto_name, notes, latitude, longitude) VALUES ('$types', '$sizes', '$filename', '$notes', '$lat', '$lon')";
	$result2 = mysql_query($insertquery);


	$insertidquery="INSERT INTO identification (weedphoto_id, user_id, weed_id, expert_id, id_ranking, request_sent_date, id_sent_date, newid, newreq) VALUES ('$weedphotoid', '$userid', 'none', null, null, '$datenow', '$datenow', '0', '1')";
	$result3 = mysql_query($insertidquery);


	mysql_close();
}

?>
