<?php
$cmd ='/home/mahbabur/reviewResult.sh 2NKNJQ2172Z9ST88HYCCYEBU7JCQY2';
 $files = array(); 
// init the output array
                      exec($cmd, $files); // execute the linux command and put the output in output array
                      echo '<pre>';
                      print_r($files);
                      echo '</pre>';

