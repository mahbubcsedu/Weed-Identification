<?php

foreach ($_GET as $key => $value) { eval("\$" . $key . " = \"" . $value . "\";");}

$expert_id = $_REQUEST["expertID"];
$audio = $_REQUEST["audio"];
$notes = $_REQUEST["comments"];
$identification_id = $_REQUEST["identificationID"];

$image_id1 = $_REQUEST["imageID1"];
$image_id2 = $_REQUEST["imageID2"];
$image_id3 = $_REQUEST["imageID3"];

$rank_id1 = $_REQUEST["rankID1"];
$rank_id2 = $_REQUEST["rankID2"];
$rank_id3 = $_REQUEST["rankID3"];


//if($code == "54m3xuzm97z30rdfsloegjizvzgga12bshptv59o")
//{
	require_once('db.inc.php');
	$r = mysql_query("SHOW TABLE STATUS LIKE 'expert_response' ");
	$row = mysql_fetch_array($r);
	$unq_res_id = $row['Auto_increment'];
	mysql_free_result($r);
	
	
	//   $selectquery= "SELECT * FROM expert_response ORDER BY expert_speechID DESC LIMIT 1";
	// $result1=mysql_query($selectquery);
	//$uniq_response_id=null;
	//if($audio){
		//      if($result1 && mysql_numrows($result1)>0)
		//      {
			//               $unqidstr = mysql_result($result1,$i,"expert_speechID");
			//              $unqid = (int)$unqidstr;
			//				//if(($_FILES["file"]["type"] == "audio/mp4")
			//              //     || ($_FILES["file"]["type"] == "audio/3gp")){
				//               $unqid = $unqid + 1;
				//				$audio_exists=1;
			//}
			//else
			//{$unqid = 0;
				//$audio_exists=0;
			//}
		//	     }
		//		 else $unqid=1;
	// }
	/*
	$selectquery= "SELECT * FROM expert_response ORDER BY expert_response_id DESC LIMIT 1";
	$result2=mysql_query($selectquery);
	
	if($result2 && mysql_numrows($result2)>0)
	{
		$uniq_response_id = mysql_result($result2,$i,"expert_response_id");
		$unq_res_id = (int)$uniq_response_id;
		$unq_res_id = $unq_res_id + 1;
		echo $unq_res_id;
	}
	else $unq_res_id=1;//if for first time
	*/
	
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
		//$audio_id=$uniq_response_id;
		$ext = findexts ($_FILES['audio']['name']) ;
		$audio_id="response".$unq_res_id.".".$ext;		$cur_dir=getcwd();		$target_path_audio=$cur_dir."/Audio_Capture/";
	//	$target_path_audio="/var/www/smsdb/weed_app/weedapp/Audio_Capture/";
		$target_path_audio = $target_path_audio."response".$unq_res_id.".".$ext;
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
	echo "$unq_res_id";
	$datetime = date("Y-m-d H:i:s");
	$insertquery="INSERT INTO expert_response(expert_id,identification_id,  response_date, expert_comments, expert_speechID) values ('$expert_id', '$identification_id',  '$datetime', '$notes','$audio_id')";
	$result3 = mysql_query($insertquery);
	//$weedphotoname="photo".$unqid.".jpg";
	if($result3)
	{
		echo "success insert expert response";
		$updatequery="update identification set newid=1 where identification_id=$identification_id";
		$result4 = mysql_query($updatequery);
		if($result4)
		{
			echo "success updated identification";
		}
		else
		{
			echo "error updating identification";
		}
		
		if($image_id1!=0){
			$insert_rank="insert into rank (expert_response_id,weed_id,rank) values ('$unq_res_id','$image_id1','$rank_id1')";
			$result_franking = mysql_query($insert_rank);
			
			if($result_franking)
			{
				echo "success inserted rank 1";
			}
			else
			{
				echo "error in rank 1";
			}
		}
		if($image_id2!=0){
			$insert_rank="insert into rank (expert_response_id,weed_id,rank) values ('$unq_res_id','$image_id2','$rank_id2')";
			$result_sranking = mysql_query($insert_rank);
			if($result_sranking)
			{
				echo "success inserted rank 2";
			}
			else
			{
				echo "error in rank 2";
			}
		}
		if($image_id3!=0){
			$insert_rank="insert into rank (expert_response_id,weed_id,rank) values ('$unq_res_id','$image_id3','$rank_id3')";
			$result_tranking = mysql_query($insert_rank);
			if($result_tranking)
			{
				echo "success inserted rank 3";
			}
			else
			{
				echo "error in rank 3";
			}
		}
		//$result5 = mysql_query($insert_rank);
		
		
		
	}
	else
	{
		echo "error in response ";
	}
	
	
	
	mysql_close();
//}
          $cur_dir=getcwd();
         $phpfile=$cur_dir."/send_notification.php";
         $cmd="php ".$phpfile." expert ".$identification_id;
//echo $cmd;
         echo exec($cmd);
// if($audio){
	
	// $target_path_audio="/var/www/smsdb/weed_app/weedapp/Expert_Speech/";
	// $target_path_audio = $target_path_audio.basename( $_FILES['audio']['name']);
	
	// if(move_uploaded_file($_FILES['audio']['tmp_name'], $target_path_audio)) {
		
		// echo "The file ".  basename( $_FILES['audio']['name']).
		
		// " has been uploaded";
		
	// } else{
		
		// echo "There was an error uploading the file, please try again!";
		
		// echo "filename: " .  basename( $_FILES['audio']['name']);
		
		// echo "target_path: " .$target_path_audio;
		
	// }
// }
//}
$comments=$_REQUEST['comments'];
echo $comments;
?>
