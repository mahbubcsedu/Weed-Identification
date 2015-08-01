<?php

	require_once('db.inc.php');

	$query="SELECT * FROM weed_photos";

	$result=mysql_query($query);

	echo "SERVER_";

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
		while ($db_locations = mysql_fetch_assoc($result))
		{
			$weedphotoid=mysql_result($result,$i,"weedphoto_id");
			$weedphototype=mysql_result($result,$i,"weedphoto_type");
			$weedphotosize=mysql_result($result,$i,"weedphoto_size");
			$weedphotoname=mysql_result($result,$i,"weedphoto_name");
			$notes=mysql_result($result,$i,"notes");
			$latitude=mysql_result($result,$i,"latitude");
			$longitude=mysql_result($result,$i,"longitude");

			echo $weedphotoid."_COL_".$weedphototype."_COL_".$weedphotosize."_COL_".$weedphotoname."_COL_".$notes."_COL_".$latitude."_COL_".$longitude."_COL_".$i;
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