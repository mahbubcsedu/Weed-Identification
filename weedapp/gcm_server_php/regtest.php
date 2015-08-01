<?php

// response json
$json = array();

/**
 * Registering a user device
 * Store reg id in users table
 */
//if (isset($_POST["name"]) && isset($_POST["email"]) && isset($_POST["regId"])) {
    //$name =$argv[1]; //
    $name="mahbubur rahman";//$_POST["name"];
    //$email =$argv[2]; //
    $email="mahbub2001@gmail.com";//$_POST["email"];
    //$gcm_regid =$argv[3]; //
     $gcm_regid="APA91bGgIcMDqmHBfu9F409DN0S9U_aCbBXmlvZwsdbKCLptdjZEdWCP-23_6gciKG4EeIgnFEjInDlq2nSFlMMJWKQZPlAQFRE0--pQLdxDGYBqWPurs1d_PN9aqEGOHKCpMaGP3E68Wi4En-huMlSFqRSspb3YPQ";//$_POST["regId"]; // GCM Registration ID
//    echo $name $email $regid;
    // Store user details in db
    include_once './db_functions.php';
    include_once './GCM.php';

    $db = new DB_Functions();
    $gcm = new GCM();

    $res = $db->storeUser($name, $email, $gcm_regid);

    $registatoin_ids = array($gcm_regid);
    echo $registatoin_ids."result:";
    $message = array("product" => "shirt");

   $result = $gcm->send_notification($registatoin_ids, $message);

    echo $result;
//    } else {
 //   echo "something is not set";
//}
?>
