<?php
$fbid = $_REQUEST["fbid"];

require_once('db.connect.php');

$searchquery="select app_id from  mweathers_users where fb_id = '$fbid'";

$result = mysql_query($searchquery);
if (!$result) {
    die('Could not query:' . mysql_error());
}
$num = mysql_numrows($result);
if($num != 1) {
    die('null');
}

while($row = mysql_fetch_array($result, MYSQL_NUM)) {
   echo $row[0];
}

mysql_close();
?>

