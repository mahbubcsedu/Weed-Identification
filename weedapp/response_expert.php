<?php


    $profileid = $_REQUEST["profile_id"];
	require_once('db.inc.php');

	// $query="select identification.user_id,identification.identification_id, expert_response.expert_id,
	// expert_response.expert_response_id, rank.rank, rank.weed_id from expert_response,identification,rank 
	// where identification.identification_id=expert_response.identification_id 
	// and rank.expert_response_id=expert_response.expert_response_id 
	// and identification.user_id=$profileid 
	// order by  identification.identification_id,expert_response.expert_response_id ASC;";
    $query="select *from identification where user_id=$profileid";
	$result=mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
		$requests = array();
		
		while ($db_locations = mysql_fetch_assoc($result))
		{
			
			$identificationid=mysql_result($result,$i,"identification_id");			
			//$userid=mysql_result($result,$i,"user_id");
			//$expertid=mysql_result($result,$i,"expert_id");
			//$expertresponseid=mysql_result($result,$i,"expert_response_id");
			//$rank=mysql_result($result,$i,"rank");
			//$weedid=mysql_result($result,$i,"weed_id");
			

			//$request = array('identification_id' => $identificationid, 'user_id' => $userid, 'expert_id' => $expertid, 'expert_response_id' => $expertresponseid, 'rank' => $rank, 'weed_id' => $weedid);
			$request = array('identification_id' => $identificationid);
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
