<?php

//take input from command line or from other scripts
$deviceToken=$argv[1];
$message=$argv[2];

// Put your device token here (without spaces):
if($deviceToken==null)
{
$deviceToken = '0f744707bebcf74f9b7c25d48e3358945f6aa01da5ddb387462c7eaf61bbad78';
}
// Put your private key's passphrase here:
$passphrase = 'mpss!WeedID1';

if($message==null)
{
// Put your alert message here:
$message = 'There is a new request to be answered!';
}

echo $deviceToken;
echo $message;
////////////////////////////////////////////////////////////////////////////////
/*
$ctx = stream_context_create();
stream_context_set_option($ctx, 'ssl', 'local_cert', 'weedidc.pem');
stream_context_set_option($ctx, 'ssl', 'passphrase', $passphrase);

// Open a connection to the APNS server
$fp = stream_socket_client(
	'ssl://gateway.sandbox.push.apple.com:2195', $err,
	$errstr, 60, STREAM_CLIENT_CONNECT|STREAM_CLIENT_PERSISTENT, $ctx);

if (!$fp)
	exit("Failed to connect: $err $errstr" . PHP_EOL);

echo 'Connected to APNS' . PHP_EOL;

// Create the payload body
$body['aps'] = array(
	'alert' => $message,
	'sound' => 'default'
	);

// Encode the payload as JSON
$payload = json_encode($body);

// Build the binary notification
$msg = chr(0) . pack('n', 32) . pack('H*', $deviceToken) . pack('n', strlen($payload)) . $payload;

// Send it to the server
$result = fwrite($fp, $msg, strlen($msg));

if (!$result)
	echo 'Message not delivered' . PHP_EOL;
else
	echo 'Message successfully delivered' . PHP_EOL;

// Close the connection to the server
fclose($fp);
*/