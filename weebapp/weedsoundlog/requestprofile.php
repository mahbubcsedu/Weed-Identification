<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$username = $_REQUEST["username"];

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	$query="SELECT * FROM farmer_profile";

	$result=mysql_query($query);

	echo "SERVER_";

	if($result && mysql_numrows($result)>0)
	{
		$i = 0;
		while ($db_locations = mysql_fetch_assoc($result))
		{
			$farmerid=mysql_result($result,$i,"farmer_id");
			$firstname=mysql_result($result,$i,"first_name");
			$lastname=mysql_result($result,$i,"last_name");
			$username=mysql_result($result,$i,"user_name");
			$fpassword=mysql_result($result,$i,"fpassword");
			$location=mysql_result($result,$i,"location");
			$membersince=mysql_result($result,$i,"member_since");
			$lastactivity=mysql_result($result,$i,"last_activity");

			echo $farmerid."_COL_".$firstname."_COL_".$lastname."_COL_".$username."_COL_".$fpassword."_COL_".$location."_COL_".$membersince."_COL_".$lastactivity."_COL_".$i;
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