<?php

$mtime = microtime(); 
   $mtime = explode(" ",$mtime); 
   $mtime = $mtime[1] + $mtime[0]; 
   $starttime = $mtime;

// check for all required arguments
// first argument is always name of script!
if ($argc != 3) {
    die("Usage: hitcreate.php with argument image url and identificationid\n".$argv[0].$argv[1].$argv[2]);
}

// remove first argument
array_shift($argv);

// get and use remaining arguments
$imageURL = $argv[0];
$identification_id = $argv[1];
//$numofImage=$argv[2];
//echo "create hit with $imageURL and store setting id $identification_id plus $numofImage \n";
 $File = "/var/www/smsdb/weed_app/executable/loghitcreate.txt";
 $Handle = fopen($File, 'a');
 $Data =  "create hit with $imageURL and store setting id $identification_id\n";
 fwrite($Handle, $Data);
// $Data = "Bilbo Jones\n"; 
 //fwrite($Handle, $Data); 

$cmd = "/var/www/smsdb/weed_app/executable/create_request.sh 1 ".$imageURL." ".$identification_id; // the linux command//later should be changed
  $files = array(); // init the output array
  exec($cmd, $files); // execute the linux command and put the output in output array
// dump the output
echo $cmd;


fwrite($Handle, $cmd);
//fwrite($Handle, $files);

 print "Data Written";
 fclose($Handle); 
   echo '<pre>';
   print_r($files);
   echo '</pre>';
require_once('db.inctest.php');
$query="update hitcreationinfo set imageid='".$imageURL  ."'where identification_id=".$identification_id;
echo $query;
	$result=mysql_query($query);

	if($result){

                echo "success";
                    }
         else 
               {
                 echo "failed to insert";
                }
$mtime = microtime(); 
   $mtime = explode(" ",$mtime); 
   $mtime = $mtime[1] + $mtime[0]; 
   $endtime = $mtime; 
   $totaltime = ($endtime - $starttime); 
   echo "This page was created in ".$totaltime." seconds";


$File = "/var/www/smsdb/weed_app/executable/logtimeHIT.txt";
 $Handle = fopen($File, 'a');
 $Data =  "Image Url:".$imageURL."  time to run:".$totaltime." seconds\n";
 fwrite($Handle, $Data);
 fclose($Handle);
  

 ?>
