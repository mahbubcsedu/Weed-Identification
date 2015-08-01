<?php
$toId = $_REQUEST["toid"];
$fromId = $_REQUEST["fromid"];
$message = $_REQUEST["message"];

require_once('db.connect.php');

$insertquery="INSERT into mweathers_messages (fromId, toId, message) VALUES ('$toId', '$fromId', '$message')";

$result = mysql_query($insertquery);
if (!$result) {
    die('Could not query:' . mysql_error());
} else {
    echo 'success';
}

mysql_close();
?>

