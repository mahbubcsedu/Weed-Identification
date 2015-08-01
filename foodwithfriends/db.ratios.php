<?php
$myid = $_REQUEST["myid"];
$str = $_REQUEST["ids"];
$ids = explode(",", $str);

require_once('db.connect.php');

$query = "select person2, ratio from mweathers_ratios where person1 = $myid and person2 in (";
$person2;
for($i = 0; $i < count($ids) - 1; $i++) {
	$person2 = $person2 . "'" . $ids[$i] . "', ";
}
$person2 = $person2 . "'" . $ids[count($ids) - 1] . "'";
$query = $query . $person2 . ")";

$result = mysql_query($query);
if (!$result) {
    die('Could not query:' . mysql_error());
} else {
    while($row = mysql_fetch_array($result, MYSQL_NUM)) {
        echo $row[0] . "|" . $row[1] . "|";
    }
}

mysql_close();
?>
