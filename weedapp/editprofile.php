<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$profile_id = $_REQUEST["profile_id"];
$update_password = $_REQUEST["fpassword"];


if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	//$date = date("Y-m-d");
	//$datetime = date("Y-m-d H:i:s");
	$updatequery="update profile set fpassword='$update_password' where profile_id='$profile_id'";
	$result = mysql_query($updatequery);

        if($result)
        {
	        $arr = array('profile_id' => $profile_id, 'error' => '');
			echo json_encode($arr);
			
        }
        else
        {
                echo json_encode(array('error' => 'Incorrect password'));
        }

	mysql_close();
}

?>
