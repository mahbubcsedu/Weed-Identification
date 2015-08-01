<?php
require_once('db.inc.php');

$r = mysql_query("SHOW TABLE STATUS LIKE 'weed_photos' ");
$row = mysql_fetch_array($r);
$Auto_increment = $row['Auto_increment'];
mysql_free_result($r);
echo $Auto_increment;
 mysql_close();
?>