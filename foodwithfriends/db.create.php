<?php
$username = $_REQUEST["username"];
$pass = $_REQUEST["pass"];

require_once('db.connect.php');

$insertquery="INSERT INTO mweathers_login (username, pass) VALUES ('$username', '$pass')";

$result = mysql_query($insertquery);
if (!$result) {
    die('Could not query:' . mysql_error());
} else {
    $locationquery="insert into mweathers_location values ((select id from mweathers_login where username = '$username'), 0.0, 0.0)";
    $result = mysql_query($locationquery);
    if(!result) {
       die('Could not query:' . mysql_error());
    }
    echo 'success';
}

mysql_close();
?>
