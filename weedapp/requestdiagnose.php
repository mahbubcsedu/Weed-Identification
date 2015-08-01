<?php

	require_once('db.inc.php');

	$query="SELECT * FROM identification";

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
			$weedid=mysql_result($result,$i,"weed_id");
			$expertid=mysql_result($result,$i,"expert_id");
			$idranking=mysql_result($result,$i,"id_ranking");
			$requestsentdate=mysql_result($result,$i,"request_sent_date");
			$idsentdate=mysql_result($result,$i,"id_sent_date");
			$farmer_comments=mysql_result($result,$i,"farmer_comments");
			$newreq=mysql_result($result,$i,"newreq");
			$newid=mysql_result($result,$i,"newid");
			$voice_url="http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Audio_Capture/" .mysql_result($result,$i,"voice_comments");

			$request = array('identification_id' => $identificationid, 'weed_photo_id' => $weedphotoid, 'user_id' => $userid, 'weed_id' => $weedid, 'expert_id' => $expertid, 'id_ranking' => $idranking, 'request_sent_date' => $requestsentdate, 'id_sent_date' => $idsentdate, 'new_req' => $newreq, 'new_id' => $newid,'voice_url'=>$voice_url,'farmer_comments'=>$farmer_comments);
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
