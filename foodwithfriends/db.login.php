<?php
$username = $_REQUEST["username"];
$pass = $_REQUEST["pass"];

require_once('db.connect.php');

$searchquery="select * from  mweathers_login where username = '$username'";

$result = mysql_query($searchquery);
if (!$result) {
    die('Could not query:' . mysql_error());
}
$num = mysql_numrows($result);
if($num == 0) {
    die('No such user found.');
}

$searchquery="select * from mweathers_login where username = '$username' and pass = '$pass'";

$result = mysql_query($searchquery);
$num1 = mysql_numrows($result);
if($num1 == 0) {
    die('Incorrect Password.');
}
while($row = mysql_fetch_array($result, MYSQL_NUM)) {
   echo "success " . $row[0] . " " . $row[1] . " ";
}

mysql_close();
?>
