<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

    $farmerID = $_REQUEST["userID"];
	require_once('db.inc.php');

	$query="select identification.identification_id as identification_id,identification.weedphoto_id as weedphoto_id,";
	$query .="identification.user_id as user_id,identification.request_sent_date as request_sent_date,identification.farmer_comments as farmer_comments,";
	$query .="identification.newid as newid,identification.newreq as newreq,identification.voice_comments as voice_comments,weed_photos.longitude as longitude,weed_photos.latitude as latitude,weed_photos.weedphoto_name as weedphoto_name";
	$query .=" from identification,weed_photos where identification.weedphoto_id=weed_photos.weedphoto_id and  user_id=".$farmerID.";";
    //echo $query;
	
	$result=mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
		$requests = array();
		while ($db_locations = mysql_fetch_assoc($result))
		{
			$identificationid=mysql_result($result,$i,"identification_id");
			$weedphotoid=mysql_result($result,$i,"weedphoto_id");
			$userid=mysql_result($result,$i,"user_id");
			$longitude=mysql_result($result,$i,"longitude");
			$latitude=mysql_result($result,$i,"latitude");
			//$idranking=mysql_result($result,$i,"id_ranking");
			$requestsentdate=mysql_result($result,$i,"request_sent_date");
			//$idsentdate=mysql_result($result,$i,"id_sent_date");
			$farmer_comments=mysql_result($result,$i,"farmer_comments");
			$newreq=mysql_result($result,$i,"newreq");
			$newid=mysql_result($result,$i,"newid");
			$voice_url="http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Audio_Capture/" .mysql_result($result,$i,"voice_comments");
            $image_url="http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Image_Capture/" .mysql_result($result,$i,"weedphoto_id").".jpg";
			$request = array('identification_id' => $identificationid, 'weedphoto_id' => $weedphotoid, 'user_id' => $userid,  'request_sent_date' => $requestsentdate, 'image_url' => $image_url, 'new_req' => $newreq, 'new_id' => $newid,'voice_url'=>$voice_url,'farmer_comments'=>$farmer_comments,'longitude'=>$longitude,'latitude'=>$latitude);
			array_push($requests, $request);

			$i++;
			
		}

		echo  json_encode(array('error' => '', 'requests' => $requests));
	}
	else
	{
		echo json_encode(array('error' => 'The database contains no data.', 'requests' => ''));
	}

	mysql_close();
?>
