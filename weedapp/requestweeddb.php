<?php

	require_once('db.inc.php');

	$query="SELECT * FROM weeds";

	$result=mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
		$weeds = array();
		while ($db_locations = mysql_fetch_assoc($result))
		{
			$weedid=mysql_result($result,$i,"weed_id");
			$commonname=mysql_result($result,$i,"common_name");
			$latinname=mysql_result($result,$i,"latin_name");
			$weedtype=mysql_result($result,$i,"weed_type");
			$lifecycle=mysql_result($result,$i,"life_cycle");
			$season=mysql_result($result,$i,"season");
			$site=mysql_result($result,$i,"site");
			$profcontrol=mysql_result($result,$i,"prof_control");
			$homecontrol=mysql_result($result,$i,"home_control");
			$image_url="http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/weedimages/" . mysql_result($result, $i, "weedphotos_name");

			$weed = array('weed_id' => $weedid, 'common_name' => $commonname, 'latin_name' => $latinname, 'weed_type' => $weedtype, 'life_cycle' => $lifecycle, 'season' => $season, 'site' => $site, 'prof_control' => $profcontrol, 'home_control' => $homecontrol, 'image_url' => $image_url);
			array_push($weeds, $weed);
			$i++;
		}

		$json = json_encode(array('error' => '', 'weeds' => $weeds));
		echo str_replace('\\/', '/', $json);
	}
	else
	{
		echo json_encode(array('error' => "no weeds available", 'weeds' => ''));
	}

	mysql_close();
?>
