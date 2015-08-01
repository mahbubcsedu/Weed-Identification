<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$userid = $_REQUEST["userid"];
$image = $_REQUEST["image"];
$audio = $_REQUEST["audio"];
$notes = $_REQUEST["notes"];

if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
{
        require_once('db.inc.php');
        $selectquery= "SELECT * FROM weed_photos ORDER BY weedphoto_id DESC LIMIT 1";
        $result1=mysql_query($selectquery);

        if($result1 && mysql_numrows($result1)>0)
        {
                $unqidstr = mysql_result($result1,$i,"weedphoto_id");
                $unqid = (int)$unqidstr;
                $unqid = $unqid + 1;
	}

echo "$unqid";
        $datetime = date("Y-m-d H:i:s");
        $insertquery="INSERT INTO identification (weedphoto_id, user_id, weed_id, request_sent_date, newreq, newid) values ('$unqid', '$userid', 'none', '$datetime', '1', '0')";
        $result = mysql_query($insertquery);

        if($result)
        {
                echo "success";
        }
        else
        {
                echo "error";
        }


        mysql_close();
}

?>
