<?php

// check for all required arguments
// first argument is always name of script!
if ($argc != 3) {
    die("Usage: creatinghit.php with argument image location and append or not argc:$argc \n");
}

// remove first argument
array_shift($argv);

// get and use remaining arguments
$imageindex = $argv[0];
$appendornot= $argv[1];
$cmd = ' /home/mahbabur/LireIndexing.sh '.$imageindex .$appendornot; // the linux command
echo $cmd;
  $files = array(); // init the output array
  exec($cmd, $files); // execute the linux command and put the output in output array
?>