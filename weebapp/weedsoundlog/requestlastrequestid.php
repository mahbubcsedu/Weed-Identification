<?php

	require_once('db.inc.php');

	$query="SELECT MAX(identification_id) AS id FROM identification";

	$result=mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		$identificationid=mysql_result($result,0,"id");
		$identificationid = $identificationid + 1;

		echo $identificationid;
	}
	else
	{
		echo "The database contains no data";
	}

	mysql_close();
?>