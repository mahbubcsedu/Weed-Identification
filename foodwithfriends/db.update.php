<?php
$id = $_REQUEST["id"];
$latitude = $_REQUEST["lat"];
$longitude = $_REQUEST["long"];

require_once('db.connect.php');

$insertquery="UPDATE mweathers_location set latitude = $latitude, longitude = $longitude where id = $id";

$result = mysql_query($insertquery);
if (!$result) {
    die('Could not query:' . mysql_error());
} else {
    echo 'success';
}

mysql_close();
?>
