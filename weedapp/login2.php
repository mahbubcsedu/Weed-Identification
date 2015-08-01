<?php

//foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$username = $_REQUEST["username"];
$fpassword = $_REQUEST["fpassword"];
$location = $_REQUEST["location"];
$date = $_REQUEST["date"];

//if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
//{
	require_once('db.inc.php');

	$query="SELECT * from profile where user_name='$username'";
	$result = mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		$query="SELECT * from profile where user_name='$username' AND fpassword='$fpassword'";
		$result = mysql_query($query);

		if($result && mysql_numrows($result)>0)
		{
			$row = mysql_fetch_array( $result );
			$arr = array('profile_id' => $row['profile_id'],'profile_type' => $row['profile_type'], 'first_name' => $row['first_name'], 'last_name' => $row['last_name'], 'error' => '');
			echo json_encode($arr);

			$query="UPDATE profile SET location='$location', last_activity='$date' WHERE user_name='$username' AND fpassword='$fpassword'";

			mysql_query($query);
		}
		else
		{
			echo json_encode(array('error' => 'Incorrect password'));
		}
	}
	else
	{
		echo json_encode(array('error' => 'No such username found'));
	}

	mysql_close();
//}

?>
