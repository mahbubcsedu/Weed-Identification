<?php
    require_once('db.inc.php');
    
    foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}
    
    $notes = $_REQUEST['notes'];
    
    $selectquery = "SELECT * FROM weed_notes ORDER BY weednote_id DESC LIMIT 1";
    $result1 = mysql_query($selectquery);
    
    if ($result1 && mysql_numrows($result1) > 0)
    {
        $unqidstr = mysql_reult($reult1, $i, "weednote_id");
        $unqid = (int)$unqidstr;
        $unqid = $unqid + 1;
        $filename = "note".$unqid.".txt";
    }
    else
    {
        $unqidstr = "1";
        $filename = "note1.txt";
    }
    
    try
    {
        $file = fopen("./weednotelog/$filename", "w");
        fwrite($file, $notes);
        fclose($file);
        echo "OK";
    }
    catch (Exception $e)
    {
        echo "Cought exception: ", $e->getMessage(), "\n";
    }
    
    mysql_close();
?>