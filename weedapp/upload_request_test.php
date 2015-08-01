<?php

//foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}
   if (count($argv) == 0) exit;
   
		$userid = $argv[1];//$_REQUEST["userid"];
		$image =$argv[2];// $_REQUEST["image"];
		$audio = $argv[3];//$_REQUEST["audio"];
		$notes = $argv[4];//$_REQUEST["comments"];
		$imageheight = $argv[5];//$_REQUEST["imageheight"];
		$imagewidth = $argv[6];//$_REQUEST["imagewidth"];
		$longitude =$argv[7];// $_REQUEST["longitude"];
		$latitude = $argv[8];//$_REQUEST["latitude"];
		//$sensordata1=$_REQUEST["sensordata1"];
		//$sensordata2=$_REQUEST["sensordata2"];
		//$sensordata3=$_REQUEST["sensordata3"];
        $image_id=substr($image,0,-4);
//if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
//{
        require_once('db.inc.php');
      
        $datetime = date("Y-m-d H:i:s");
        $insertquery="INSERT INTO identification (weedphoto_id, user_id, weed_id, request_sent_date, newreq, newid,voice_comments,farmer_comments) values ('$image_id', '$userid', 'none', '$datetime', '1', '0','$audio','$notes')";
        $result = mysql_query($insertquery);
        $weedphotoname="photo".$image;
        if($result)
        {
                echo "success";
        }
        else
        {
                echo "error";
        }
		
        $insertquery="INSERT INTO weed_photos (weedphoto_id,weedphoto_type, weedphoto_size, weedphoto_name,latitude,longitude) values ('$image_id','image/jpeg', 'width=\"$imagewidth\" height=\"$imageheight\"','$weedphotoname', '$latitude', '$longitude')";
        $result = mysql_query($insertquery);

        if($result)
        {
                echo "success";
        }
        else
        {
                echo "error";
        }


        $selectquery= "SELECT * FROM identification  ORDER BY identification_id  DESC LIMIT 1";
        $result=mysql_query($selectquery);
        //$audio_id=NULL;
        if($result && mysql_numrows($result)>0)
        {
                $identification = mysql_result($result,$i,"identification_id");
                $identification_id = (int)$identification;
                //$identification_id = $identification_id + 1;
            }
            else
                {
                        $identification_id=1;
                }
		  
          $sql="update hitcreationinfo set identification_id=".$identification_id ." where imageid='".$image."'";
		  $result=mysql_query($sql);
		  if($result)
		  {
		  echo "success";
		  }
		  else
		  {
		  echo "false";
		  }

        mysql_close();
//}

    /*$base=$_REQUEST['image'];
    $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');
	$imagename="Image_Capture/".$unqid.".jpg";
    $file = fopen($imagename, 'wb');
    fwrite($file, $binary);
    fclose($file);
    echo 'Image upload complete!!, $imagename :Please check your php file directory……'; 
    $comments=$_REQUEST['comments'];
 echo $comments;
 $imageurl="/var/www/smsdb/weed_app/weedapp/".$imagename;
 

 $File = "/var/www/smsdb/weed_app/executable/loghitcreate.txt"; 
 $Handle = fopen($File, 'w');
 $Data = "Jane Doe\n"; 
 fwrite($Handle, $imageurl); 
// $Data = "Bilbo Jones\n"; 
 //fwrite($Handle, $Data); 
 print "Data Written"; 
 fclose($Handle); 
 
 exec ("/usr/bin/php hitcreate.php $imageurl $identification_id >/dev/null &");
 */
?>
