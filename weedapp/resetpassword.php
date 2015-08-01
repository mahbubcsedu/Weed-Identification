<?php


    $username = $_REQUEST["user_name"];
	//$password = $_REQUEST["password"];
	require_once('db.inc.php');

	$query="SELECT * from profile where user_name='$username'";
	$result = mysql_query($query);

	if($result && mysql_numrows($result)>0)
	{
		//$query="SELECT * from profile where user_name='$username' AND fpassword='$fpassword'";
		//$result = mysql_query($query);

		//if($result && mysql_numrows($result)>0)
		//{
			$row = mysql_fetch_array( $result );
			$email=$row['profile_id'];
			$password=$row['fpassword'];
			$arr = array('profile_id' => $row['profile_id'],'profile_type' => $row['profile_type'], 'first_name' => $row['first_name'], 'last_name' => $row['last_name'],'email' => $row['email'], 'error' => '');
			echo json_encode($arr);

			//$query="UPDATE profile SET location='$location', last_activity='$date' WHERE user_name='$username' AND fpassword='$fpassword'";

			//mysql_query($query);
			
		//}
		//else$
		//{
		//	echo json_encode(array('error' => 'Incorrect password'));
		//}
		 $to = $email;
         $subject = "Password Reset";
         $body = "Your Username=$username and password=$password";
         if (mail($to, $subject, $body)) {
              echo "Message successfully sent!";
            } else {
                 echo "Message delivery failed...";
                 }
		
	}
	else
	{
		echo json_encode(array('error' => 'No such username found'));
	}

	mysql_close();
?>
