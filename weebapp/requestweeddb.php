<?php

	require_once('db.inc.php');

	$query="SELECT * FROM weeds";

	$result=mysql_query($query);

	echo "SERVER_";

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
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

			echo $weedid."_COL_".$commonname."_COL_".$latinname."_COL_".$weedtype."_COL_".$lifecycle."_COL_".$season."_COL_".$site."_COL_".$profcontrol."_COL_".$homecontrol."_COL_".$i;
			if (mysql_numrows($result)>$i)
				echo "_ROW_";

			$i++;
		}
	}
	else
	{
		echo "The database contains no data";
	}

	mysql_close();
?>