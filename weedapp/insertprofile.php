<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$firstname = $_REQUEST["firstname"];
$lastname = $_REQUEST["lastname"];
$username = $_REQUEST["username"];
$fpassword = $_REQUEST["fpassword"];
$location = $_REQUEST["location"];
$usertype = $_REQUEST["user_type"];
$county = $_REQUEST["county"];
$email = $_REQUEST["email"];

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
	require_once('db.inc.php');

	$date = date("Y-m-d");
	$datetime = date("Y-m-d H:i:s");
	$insertquery="INSERT INTO profile (first_name, last_name, user_name, fpassword, email,location, member_since, last_activity, county,profile_type) VALUES ('$firstname', '$lastname', '$username', '$fpassword','$email', '$location', '$date', '$datetime', '$county','$usertype')";
	$result = mysql_query($insertquery);

        if($result)
        {
	        $query="SELECT * from profile where user_name='$username' AND fpassword='$fpassword'";
	        $result = mysql_query($query);

                $row = mysql_fetch_array( $result );
                $arr = array('profile_id' => $row['profile_id'], 'first_name' => $row['first_name'],'profile_type' => $row['profile_type'], 'last_name' => $row['last_name'], 'error' => '');
                echo json_encode($arr);
        }
        else
        {
                echo json_encode(array('error' => 'Sorry, somebody already has that username. Please choose another'));
        }

	mysql_close();
}

?>
