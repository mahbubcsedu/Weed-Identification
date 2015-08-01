<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$username = $_REQUEST["username"];

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	$query="SELECT * FROM expert_profile where user_name='$username'";

	$result=mysql_query($query);

	echo "SERVER_";

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
		while ($db_locations = mysql_fetch_assoc($result))
		{
			$expertid=mysql_result($result,$i,"expert_id");
			$firstname=mysql_result($result,$i,"first_name");
			$lastname=mysql_result($result,$i,"last_name");
			$username=mysql_result($result,$i,"user_name");
			$fpassword=mysql_result($result,$i,"fpassword");
			$location=mysql_result($result,$i,"location");
			$ranking=mysql_result($result,$i,"ranking");
			$membersince=mysql_result($result,$i,"member_since");
			$lastactivity=mysql_result($result,$i,"last_activity");

			echo $expertid."_COL_".$firstname."_COL_".$lastname."_COL_".$username."_COL_".$fpassword."_COL_".$location."_COL_".$ranking."_COL_".$membersince."_COL_".$lastactivity."_COL_".$i;
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
}
?>