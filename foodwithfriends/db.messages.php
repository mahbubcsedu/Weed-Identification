<?php
$fromId = $_REQUEST["fromid"];
$toId = $_REQUEST["toid"];

require_once('db.connect.php');

$searchquery="select b.id, username, message from mweathers_login a, mweathers_messages b where fromId = $fromId and toId = $toId and a.id = fromId";

$result = mysql_query($searchquery);
while($row = mysql_fetch_array($result, MYSQL_NUM)) {
   echo $row[0] . "|" . $row[1] . "|" . $row[2] . "|";
}

mysql_close();
?>
