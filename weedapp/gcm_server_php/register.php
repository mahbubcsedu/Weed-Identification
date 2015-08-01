<?php

// response json
$json = array();

/**
 * Registering a user device
 * Store reg id in users table
 */

 $File = "registrtionerror.txt"; 
 $Handle = fopen($File, 'w');
 //$Data = "Jane Doe\n"; 
 //fwrite($Handle, $Data); 
 //$Data = "Bilbo Jones\n"; 
 //fwrite($Handle, $Data); 
 //print "Data Written"; 
 //fclose($Handle);


if (isset($_POST["name"]) && isset($_POST["email"]) && isset($_POST["regId"]) && isset($_POST["device_type"])) {
    //$name =$argv[1]; //
    $name=$_POST["name"];
    echo "name".$name;
    //$email =$argv[2]; //
    $email=$_POST["email"];
    echo "email".$email;
    //$gcm_regid =$argv[3]; //
     $gcm_regid=$_POST["regId"]; // GCM Registration ID
    echo "gcm_regid".$gcm_regid;
//    echo $name $email $regid;
    // Store user details in db
    $Data=$email.$name.$gcm_regid;
   $device=$_POST["device_type"];
    // fwrite($Handle,$Data);
    
    include_once './db_functions.php';
    include_once './GCM.php';

    $db = new DB_Functions();
    
    //$Data=$Data.$db;
  
    $gcm = new GCM();
  
   // $Data=$Data."gcm".$gcm;
 
    $res = $db->storeUser($name, $email, $gcm_regid,$device);
  
    

    $registatoin_ids = array($gcm_regid);
  
    $Data=$Data.$registatoin_ids.$gcm_regid;

    fwrite($Handle,$Data);
   
    $message = array("product" => "shirt");

    $result = $gcm->send_notification($registatoin_ids, $message);
   
    fwrite($Handle,$result);
  
    fclose($Handle);

    echo $result;
    } else {
    echo "something is not set";
}
?>
