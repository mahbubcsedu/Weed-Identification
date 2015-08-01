<?php
$fbid = $_REQUEST["fbid"];
$restid = $_REQUEST["restid"];
$rating = $_REQUEST["rating"];

require_once('db.connect.php');
$existquery="select * from mweathers_ratings where fb_id = '$fbid' and rest_id = '$restid'";
$existresult = mysql_query($existquery);

if (!$existresult) {
    die('Could not query:' . mysql_error());
}
$num = mysql_numrows($existresult);
if($num == 0) {
    $insertquery="INSERT into mweathers_ratings VALUES ('$fbid', '$restid', '$rating')";

    $result = mysql_query($insertquery);
    if (!$result) {
        die('Could not query:' . mysql_error());
    } else {
        echo 'success';
    }
} else {
    $updatequery="update mweathers_ratings set rating = '$rating' where fb_id = '$fbid' and rest_id = '$restid'";

    $result = mysql_query($updatequery);
    if (!$result) {
        die('Could not query:' . mysql_error());
    } else {
        echo 'success';
    }
}

mysql_close();
?>

