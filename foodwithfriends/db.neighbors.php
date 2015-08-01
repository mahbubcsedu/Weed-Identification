<?php
$id = $_REQUEST["id"];
require_once('db.connect.php');

$searchquery="select mweathers_location.id, username, latitude, longitude from  mweathers_location, mweathers_login where mweathers_location.id = mweathers_login.id and mweathers_location.id <> $id";

$result = mysql_query($searchquery);
while($row = mysql_fetch_array($result, MYSQL_NUM)) {
   echo $row[0] . " " . $row[1] . " " . $row[2] . " " . $row[3] . " ";
}

mysql_close();
?>
