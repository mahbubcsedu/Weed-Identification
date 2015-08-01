<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

		//$userid = $_REQUEST["userid"];
		$image =/*"http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Image_Capture/1.jpg";//*/ $_REQUEST["image"];

               $image=str_replace("http://mpss.csce.uark.edu","/var/www",$image);        
               
//if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
//{
        require_once('db.inc.php');
		
		$selectquery= "SELECT * FROM weeds ORDER BY weed_id DESC LIMIT 1";
        $result1=mysql_query($selectquery);
        $audio_id=NULL;
        if($result1 && mysql_numrows($result1)>0)
        {
                $unqidstr = mysql_result($result1,$i,"weed_id");
                $unqid = (int)$unqidstr;
                $unqid = $unqid + 1;
	    }
	    else
		{
			$unqid=1;
		}
		
		$weedphotos_name=$unqid.".jpg";
		$imageurl="/var/www/smsdb/weed_app/weedapp/weedimages/".$weedphotos_name;
                //$imageurl="\"".$imageurl."\"";
                //$image="\"".$image."\"";
        $content = file_get_contents($image);
//Store in the filesystem.
        $fp = fopen($imageurl, "w");
        fwrite($fp, $content);
        fclose($fp);
        echo $image;
        echo $imageurl;
		
		$insertquery="INSERT INTO weeds (weedphotos_name) values ('$weedphotos_name')";
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
 //this is stopped for temporary basis and when we are ready we will start this to create hit in amazon mechanical turk
 //exec ("/usr/bin/php hitcreate.php $imageurl $identification_id >/dev/null &");
?>
