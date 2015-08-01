<?php

	require_once('db.inc.php');

	$query="SELECT * FROM identification";

	$result=mysql_query($query);

	echo "SERVER_";

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
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
			$newreq=mysql_result($result,$i,"newreq");
			$newid=mysql_result($result,$i,"newid");

			echo $identificationid."_COL_".$weedphotoid."_COL_".$userid."_COL_".$weedid."_COL_".$expertid."_COL_".$idranking."_COL_".$requestsentdate."_COL_".$idsentdate."_COL_".$newreq."_COL_".$newid."_COL_".$i;
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
