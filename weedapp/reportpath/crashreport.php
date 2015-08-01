<?php
/* PUT data comes in on the stdin stream */




$putdata = fopen("php://input", "r");

$time = time();
$filename_prefix = 'crashreport';
$filename_extn   = '.txt';

$filename = $filename_prefix.'_'.$time.$filename_extn;
/* Open a file for writing */
$fp = fopen("./$filename", "a");

/* Read the data 1 KB at a time
   and write to the file */
while ($data = fread($putdata, 1024))
  fwrite($fp, $data);

/* Close the streams */
fclose($fp);
fclose($putdata);
?>