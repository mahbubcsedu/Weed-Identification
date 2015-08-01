<?php

//$QUERY_STRING="http://".$_SERVER['HTTP_HOST'].$_SERVER['REQUEST_URI'];
//echo $_SERVER['REQUEST_URI'];
$QUERY_STRING="method=Notify
&Signature=vH6ZbE0NhkF/hfNyxz2OgmzXYKs=
&Timestamp=2006-05-23T23:22:30Z
&Version=2006-05-05
&Event.1.EventType=AssignmentAccepted
&Event.1.EventTime=2006-04-21T18:55:23Z
&Event.1.HITTypeId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL
&Event.1.HITId=2NKNJQ2172Z9ST88HYCCYEBU7JCQY2
&Event.1.AssignmentId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL
&Event.2.EventType=AssignmentReturned
&Event.2.EventTime=2006-04-21T18:55:23Z
&Event.2.HITTypeId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL
&Event.2.HITId=2NKNJQ2172Z9ST88HYCCYEBU7JCQY2
&Event.2.AssignmentId=27R2RCJK63ZVX68T6YLIDQ9JLQ47WL";
$QUERY_STRING=substr($_SERVER['REQUEST_URI'],strrpos($_SERVER['REQUEST_URI'], "?")+1);
$a = explode('&', $QUERY_STRING);
 require_once('db.inc.php');
$i = 0;
while ($i < count($a)) {
    $b = split('=', $a[$i]);
    echo 'Value for parameter ', htmlspecialchars(urldecode($b[0])),
         ' is ', htmlspecialchars(urldecode($b[1])), "<br />\n";
 //   echo 'testing '.substr(htmlspecialchars(urldecode($b[0])),8);
            if($i>=4){
		   if(substr(htmlspecialchars(urldecode($b[0])),8)=="EventType")
		   $eventtype=htmlspecialchars(urldecode($b[1]));
		   else if(substr(htmlspecialchars(urldecode($b[0])),8)=="EventTime")
		   $eventtime=htmlspecialchars(urldecode($b[1]));
		   else if(substr(htmlspecialchars(urldecode($b[0])),8)=="HITTypeId")
		   $hittypeid=htmlspecialchars(urldecode($b[1]));
		   else if(substr(htmlspecialchars(urldecode($b[0])),8)=="HITId")
		   $hitid=htmlspecialchars(urldecode($b[1]));
		   else if(substr(htmlspecialchars(urldecode($b[0])),8)=="AssignmentId")
		   $assignmentid=htmlspecialchars(urldecode($b[1]));
                  }
echo $i;
if($i>7  && ($i-3)%5 == 0){
        //require_once('db.inc.php');
       //  echo $hitid .$hittypeid .$eventtime .$eventtype .$assignmentid;

      $insertquery="insert into hitmanagement(hitid,hittypeid,eventtime,eventtype,assignmentid) values('$hitid','$hittypeid','$eventtime','$eventtype','$assignmentid')";
      $result = mysql_query($insertquery);

        if($result){
                      echo "success"; echo $hitid; 
		      $cmd = '/home/mahbabur/reviewResult.sh '.$hitid; // the linux command
                      $files = array(); // init the output array
                      echo $cmd;
                      exec($cmd, $files); // execute the linux command and put the output in output array
		      echo '<pre>';
                      print_r($files);
                      echo '</pre>';  
		          }
                 else 
                     echo "failed to insert";
     //   {
         //       $query="SELECT * from farmer_profile where user_name='$username' AND fpassword='$fpassword'";
         //       $result = mysql_query($query);

       //         $row = mysql_fetch_array( $result );
       //         $arr = array('farmer_id' => $row['farmer_id'], 'first_name' => $row['first_name'], 'last_name' => $row['last_name'], 'error' => '');
        //        echo json_encode($arr);
      //  }
      //  else
      //  {
      //          echo json_encode(array('error' => 'Sorry, somebody already has that username. Please choose another'));
      //  }

     // mysql_close();

}
//echo $eventtype;   
 $i++;
}
 mysql_close();
//echo $eventtype;
//echo $eventtime;
$myFile = "/var/www/smsdb/weed_app/executable/requestfile.txt";
$fh = fopen($myFile, 'a') or die("can't open file");
$stringData = $QUERY_STRING;
fwrite($fh, $stringData);
//$stringData = "Tracy Tanner\n";
//fwrite($fh, $stringData);
fclose($fh);

echo '<pre>';
//print_r($code);
print_r($a);
echo '</pre>';


?>
