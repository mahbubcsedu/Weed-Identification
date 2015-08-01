<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

		$userid = $_REQUEST["userid"];
		$image = $_REQUEST["image"];
		$audio = $_REQUEST["audio"];
		$notes = $_REQUEST["comments"];
		$imageheight = $_REQUEST["imageheight"];
		$imagewidth = $_REQUEST["imagewidth"];
		$longitude = $_REQUEST["longitude"];
		$latitude = $_REQUEST["latitude"];
		$sensordata1=$_REQUEST["sensordata1"];
		$sensordata2=$_REQUEST["sensordata2"];
		$sensordata3=$_REQUEST["sensordata3"];

//if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
//{
        require_once('db.inc.php');
        $selectquery= "SELECT * FROM weed_photos ORDER BY weedphoto_id DESC LIMIT 1";
        $result1=mysql_query($selectquery);
        $audio_id=NULL;
        if($result1 && mysql_numrows($result1)>0)
        {
                $unqidstr = mysql_result($result1,$i,"weedphoto_id");
                $unqid = (int)$unqidstr;
                $unqid = $unqid + 1;
	    }
	    else
		{
			$unqid=1;
		}
	
	
	function findexts ($filename) 
	 { 
	 $filename = strtolower($filename) ; 
	 $exts = split("[/\\.]", $filename) ; 
	 $n = count($exts)-1; 
	 $exts = $exts[$n]; 
	 return $exts; 
	 } 
 
 //Audio handling
		 if(empty($_FILES['audio']['name']))
		 {
			$audio_id=NULL;
			echo "no audio";
		 }
		 
		 else{					  
		    $ext = findexts ($_FILES['audio']['name']) ;
            $audio_id="request".$unqid.".".$ext; 			
		    $target_path_audio="/var/www/smsdb/weed_app/weedapp/Audio_Capture/";
		    $target_path_audio = $target_path_audio."request".$unqid.".".$ext;
			
		    if(move_uploaded_file($_FILES['audio']['tmp_name'], $target_path_audio)) 
			     {

			        echo "The file ".  basename( $_FILES['audio']['name']).
			           " has been uploaded";
			     } else{

				        echo "There was an error uploading the file, please try again!";
				        echo "filename: " .  basename( $_FILES['audio']['name']);
				        echo "target_path: " .$target_path_audio;
			          }	
            }
        
		echo "$unqid";
        $datetime = date("Y-m-d H:i:s");
        $insertquery="INSERT INTO identification (weedphoto_id, user_id, weed_id, request_sent_date, newreq, newid,voice_comments,farmer_comments) values ('$unqid', '$userid', 'none', '$datetime', '1', '0','$audio_id','$notes')";
        $result = mysql_query($insertquery);
        $weedphotoname="photo".$unqid.".jpg";
        if($result)
        {
                echo "success";
        }
        else
        {
                echo "error";
        }
		
        $insertquery="INSERT INTO weed_photos (weedphoto_type, weedphoto_size, weedphoto_name,latitude,longitude) values ('image/jpeg', 'width=\"$imagewidth\" height=\"$imageheight\"','$weedphotoname', '$latitude', '$longitude')";
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
//}

    $base=$_REQUEST['image'];
    $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');
	$imagename="Image_Capture/".$unqid.".jpg";
    $file = fopen($imagename, 'wb');
    fwrite($file, $binary);
    fclose($file);
    echo 'Image upload complete!!, $imagename :Please check your php file directory……'; 
    $comments=$_REQUEST['comments'];
 echo $comments;
 $cmd = ' /home/mahbabur/creatingHIT.sh  "/var/www/smsdb/weed_app/weedapp/".$imagename 9 5 "./best_image.xml" '; // the linux command
  $files = array(); // init the output array
  exec($cmd, $files); // execute the linux command and put the output in output array
// dump the output
   echo '<pre>';
   print_r($files);
   echo '</pre>';
?>
