<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    
    include_once './GCM.php';
    $regId="APA91bH6k95hfp1CpPsXtDbIQzOA91Oc0bPHW6_jMmQqiaE4eYd8pXlPln1MU54LV6NI745nj2x_fsr_Wn_bAwSzig_5ZAUC9duzrUIQNuq96E84K9mpkTvaRec7lhqw3sUnC-M_ZBA8, APA91bGGGiJwWk5j01c8A5j1nvTVQEcyW29op-nnI-kuc698nYby2qy6cim6Jjqo_1wft5W4T3VUl-8zTefTRwaDSrJPteDCMJWebJWETfwipNi4r4RocksNwh5_zdL_WrbWta1FXF50";
	$message="you have 5 requests";
    $gcm = new GCM();

    $registatoin_ids = array("APA91bH6k95hfp1CpPsXtDbIQzOA91Oc0bPHW6_jMmQqiaE4eYd8pXlPln1MU54LV6NI745nj2x_fsr_Wn_bAwSzig_5ZAUC9duzrUIQNuq96E84K9mpkTvaRec7lhqw3sUnC-M_ZBA8","APA91bGGGiJwWk5j01c8A5j1nvTVQEcyW29op-nnI-kuc698nYby2qy6cim6Jjqo_1wft5W4T3VUl-8zTefTRwaDSrJPteDCMJWebJWETfwipNi4r4RocksNwh5_zdL_WrbWta1FXF50");
    $message = array("price" => $message);

    $result = $gcm->send_notification($registatoin_ids, $message);

    echo $result;

?>