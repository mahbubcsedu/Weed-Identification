<?php
$str = $_REQUEST["fb_ids"];
$r = $_REQUEST["rest_ids"];

$ids = explode(",", $str);
$rids = explode(",", $r);

require_once('db.connect.php');

$rest;
for($i = 0; $i < count($rids) - 1; $i++) {
	$rest = $rest . "'" . $rids[$i] . "', ";
}
$rest = $rest . "'" . $rids[count($rids) - 1] . "'";

$fb;
for($i = 0; $i < count($rids) - 1; $i++) {
	$fb = $fb . "'" . $ids[$i] . "', ";
}
$fb = $fb . "'" . $ids[count($ids) - 1] . "'";

$query = "select rest_id, avg(rating) from mweathers_ratings where rest_id in (" . $rest . ") and fb_id in (" . $fb . ") group by rest_id";

$result = mysql_query($query);
if (!$result) {
    die();
} else {
    while($row = mysql_fetch_array($result, MYSQL_NUM)) {
        echo $row[0] . "|" . $row[1] . "|";
    }
}

mysql_close();
?>
