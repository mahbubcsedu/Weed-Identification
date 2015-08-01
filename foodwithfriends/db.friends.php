<?php
$fbid = $_REQUEST["id"];

require_once('db.connect.php');

$searchquery="select rest_id, rating from mweathers_ratings where fb_id = '$fbid'";

$result = mysql_query($searchquery);
if (!$result) {
    die('Could not query:' . mysql_error());
}

while($row = mysql_fetch_array($result, MYSQL_NUM)) {
   echo $row[0] . "|" . $row[1] . "|";
}

mysql_close();
?>

