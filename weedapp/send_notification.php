<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


    include_once './gcm_server_php/GCM.php';
	require_once('db.inc.php');
    // $regId="APA91bH6k95hfp1CpPsXtDbIQzOA91Oc0bPHW6_jMmQqiaE4eYd8pXlPln1MU54LV6NI745nj2x_fsr_Wn_bAwSzig_5ZAUC9duzrUIQNuq96E84K9mpkTvaRec7lhqw3sUnC-M_ZBA8, APA91bGGGiJwWk5j01c8A5j1nvTVQEcyW29op-nnI-kuc698nYby2qy6cim6Jjqo_1wft5W4T3VUl-8zTefTRwaDSrJPteDCMJWebJWETfwipNi4r4RocksNwh5_zdL_WrbWta1FXF50";
	// echo $argv[2] ;
	// echo $argv[1] ;
	//require_once('db.inc.php');
	
	$identification=$argv[2];
	$notification_from=$argv[1];
	$message="No message";
	$response_from=0;
	
    $select_query;

	if($notification_from=="expert")
	{
		
		$select_query="select DISTINCT(gcm_regid) from profile,gcm_users,identification where profile.email=gcm_users.email";
        $select_query=$select_query." and profile.profile_id=identification.user_id and profile.profile_id=identification.user_id";
        $select_query=$select_query." and identification.identification_id=".$identification." and profile_type=2 and gcm_users.device_type='android'";	
		
		$message="You have one new response for the request #".$identification;
		$response_from=2;
		echo $message;
	}
	else if($notification_from=="farmer")
	{
		$response_from=1;
		$select_query="select DISTINCT(gcm_regid) from profile,gcm_users where profile.email=gcm_users.email and gcm_users.device_type='android' and profile_type=".$response_from;	
		$message="You have one new request";
		
		echo $message;
	}
	echo $select_query;
	//$message=$message."notification from-".$notification_from."identity-".$identification;
	$message = array("price" => $message);
    $gcm = new GCM();
    //$registatoin_ids = array($regID);
	
	$result=mysql_query($select_query);
	$regids=array();
   
     
	 if($result === FALSE) 
	 {
        die(mysql_error()); // TODO: better error handling
     }
    $id_num=0;
    while($row = mysql_fetch_array($result))
    {
    $regids[$id_num]=$row['gcm_regid'];
	$id_num++;
    }
//foreach($regids as $value) {
 // print $value;
//}
    // $registatoin_ids = array("APA91bH6k95hfp1CpPsXtDbIQzOA91Oc0bPHW6_jMmQqiaE4eYd8pXlPln1MU54LV6NI745nj2x_fsr_Wn_bAwSzig_5ZAUC9duzrUIQNuq96E84K9mpkTvaRec7lhqw3sUnC-M_ZBA8","APA91bGGGiJwWk5j01c8A5j1nvTVQEcyW29op-nnI-kuc698nYby2qy6cim6Jjqo_1wft5W4T3VUl-8zTefTRwaDSrJPteDCMJWebJWETfwipNi4r4RocksNwh5_zdL_WrbWta1FXF50");
    // $message = array("price" => $message);

   $result = $gcm->send_notification($regids, $message);

    echo $result;

?>