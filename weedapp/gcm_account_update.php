
<?php 
        foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

		$emailid = $_REQUEST["email"];
		$regid = $_REQUEST["regid"];
		$device_type = $_REQUEST["device_type"];
		
		
		require_once('db.inc.php');
		
		
		if($device_type=="android"){
		$update_query= "update gcm_users set gcm_regid='".$regid."', device_type='".$device_type."' where email='".$emailid."'";
        $result1=mysql_query($update_query);
        if($result1)
        {
          echo json_encode(array('success' => 'Successfully updated'));      
	    }
	    else
		{
			echo json_encode(array('error' => 'failed'));   
		}
		}
		mysql_close();
		
?>