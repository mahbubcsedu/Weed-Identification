<?php

//foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}


        // $expert_id = 7;
		// $identification_id = 121;
		// $common_name = "common name";
		// $latin_name = "latin_name";
		// $weed_type = "grass";
		// $life_cycle = "annual";
		// $season = "winter";
		// $site = "lawn";
		// $prof_control = "professional control";
		// $home_control = "home control";
		// $image="232.jpg";//name of the image only like...2.jpg, 3.jpg
		
		$expert_id = $_REQUEST["expert_id"];
		$identification_id = $_REQUEST["identification_id"];
		$common_name = $_REQUEST["common_name"];
		$latin_name = $_REQUEST["latin_name"];
		$weed_type = $_REQUEST["weed_type"];
		$life_cycle = $_REQUEST["life_cycle"];
		$season = $_REQUEST["season"];
		$site = $_REQUEST["site"];
		$prof_control = $_REQUEST["prof_control"];
		$home_control = $_REQUEST["home_control"];
		$image=$_REQUEST["image_name"];//name of the image only like...2.jpg, 3.jpg
		
		
		
		$_cur_src_dir = getcwd()."/Image_Capture/";
		$_cur_des_dir = getcwd()."/newweedimages/";
		makeDir($_cur_src_dir);
		makeDir($_cur_des_dir);
		
		//$image =/*"http://mpss.csce.uark.edu/smsdb/weed_app/weedapp/Image_Capture/1.jpg";//*/ $_REQUEST["image"];

		$image_src_dir=$_cur_src_dir.$image;
               //$image=str_replace("http://mpss.csce.uark.edu","/var/www",$image);        
             
//if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
//{
        require_once('db.inc.php');
		//see which requested image is new one
		$selectquery= "SELECT * FROM ms_new_weeds where identification_id=".$identification_id." ORDER BY identification_id DESC LIMIT 1";
		$result=mysql_query($selectquery);
        $first_time=true;
		//echo $selectquery;
        if($result) 
        {
			if(mysql_numrows($result)>0)
			{
			// echo "repeated request";
             $first_time=false;			 
			}
			else
			{
			$first_time=true;
			$insertquery="INSERT INTO ms_new_weeds (expert_id,identification_id) VALUES (".$expert_id.",".$identification_id.")";
		    $result1=mysql_query($insertquery);
            // echo " first time";			
			}   
	    }
	    //echo $first_time ;
		
		
		$selectquery= "SELECT * FROM ms_new_weeds ORDER BY ms_weed_id DESC LIMIT 1";
        $result1=mysql_query($selectquery);
        $i=0;
		$weedphotos_name;
		$uniqid_ms=0;
        if($result1 && mysql_numrows($result1)>0)
        {
                $unqidstr = mysql_result($result1,$i,"ms_weed_id");
                $uniqid_ms = (int)$unqidstr;
                //$unqid = $unqid + 1;
				
	    }
		else{
			$uniqid_ms=1;
		}
	    
        $weedphotos_name=$uniqid_ms.".jpg";
		if($first_time)
		{
		
		//echo $insertquery;
			//$weedphotos_name=$unqid_image.".jpg";
			$image_des_dir=$_cur_des_dir.$weedphotos_name;
			//echo $image_des_dir; 
		   // $imageurl=$image_dir.$weedphotos_name;
                //$imageurl="\"".$imageurl."\"";
                //$image="\"".$image."\"";
           // $content = file_get_contents($image_src_dir);
            //Store in the filesystem.
           // $fp = fopen($image_des_dir, "w");
           // fwrite($fp, $content);
            // fclose($fp);
			 
			  if (!copy($image_src_dir, $image_des_dir)) { 
                    // //echo "failed to copy $file...\n"; 
					echo json_encode(array('message' => 'error'));
					 $deletequery="delete from ms_new_weeds where ms_weed_id=".$uniqid_ms;
					 $result = mysql_query($deletequery);
                     $deletequery="delete from dt_new_weeds where ms_weed_id=".$uniqid_ms;	
                     $result = mysql_query($deletequery);					
					 //return;
                        } 
             //echo $image;
            // echo $imageurl;	
			
		$insertquery="update ms_new_weeds set weedphotos_name='".$weedphotos_name."'  where ms_weed_id=".$uniqid_ms;
		//echo $insertquery;
        $result = mysql_query($insertquery);        
        // if($result)
        // {
                // echo json_encode(array('message' => 'success'));
        // }
        // else
        // {
                // echo json_encode(array('message' => 'error'));
        // }	
		}
		
		$insertquery="INSERT INTO dt_new_weeds (common_name, latin_name, weed_type, life_cycle, season, site, prof_control, home_control,"; 
		$insertquery=$insertquery."transferred, ms_weed_id) VALUES ('".$common_name."', '".$latin_name."',"; 
		$insertquery=$insertquery."'".$weed_type."', '".$life_cycle."', '".$season."', '".$site."', '".$prof_control."', '".$home_control."', ";; 
		$insertquery=$insertquery."0, ".$uniqid_ms.")";
		$result1=mysql_query($insertquery);
		
		if($result1)
        {
                echo json_encode(array('message' => 'success'));
        }
        else
        {
                echo json_encode(array('message' => 'error'));
        }	
		
		
		
		mysql_close();
		
		
		function makeDir($path)
         {
          $ret = mkdir($path); // use @mkdir if you want to suppress warnings/errors
          return $ret === true || is_dir($path);
         }
 //this is stopped for temporary basis and when we are ready we will start this to create hit in amazon mechanical turk
 //exec ("/usr/bin/php hitcreate.php $imageurl $identification_id >/dev/null &");
?>
