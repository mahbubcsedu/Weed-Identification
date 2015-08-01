<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


class GCM {

    //put your code here
    // constructor
    function __construct() {
        
    }

    /**
     * Sending Push Notification
     */
    public function send_notification($registatoin_ids, $message) {
        // include config
        include_once './gcm_server_php/config.php';

        // Set POST variables
        $url = 'https://android.googleapis.com/gcm/send';

       $File="gcm_check.txt";
       $Handle=fopen($File,'w');
       fwrite($Handle,$url);
        $fields = array(
            'registration_ids' => $registatoin_ids,
            'data' => $message,
        );
        //$Data=$fields;
       fwrite($Handle,print_r($fields,true));

        $headers = array(
            'Authorization: key=' . GOOGLE_API_KEY,
            'Content-Type: application/json'
        );
       fwrite($Handle,print_r($headers,true));
       //$Data=$headers;
       //fwrite($Handle,$Data);
       //fclose($Handle);
        // Open connection
        $ch = curl_init();

        // Set the url, number of POST vars, POST data
        curl_setopt($ch, CURLOPT_URL, $url);

        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

        // Disabling SSL Certificate support temporarly
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);

        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));

        // Execute post
        fwrite($Handle,"start executing");
        $result = curl_exec($ch);
        if ($result === FALSE) {
            die('Curl failed: ' . curl_error($ch));
            $Data='curl error'. curl_error($ch);
        }
        fwrite($Handle,"printing after curl");
        fwrite($Handle,print_r(curl_getinfo($ch),true));
        //print "'$response'\n";
        //print_r(curl_getinfo($ch));
        // Close connection
        curl_close($ch);
        echo $result;
        fwrite($Handle,$result);
        fwrite($Handle,$result);
        fclose($Handle);
    }

}

?>
