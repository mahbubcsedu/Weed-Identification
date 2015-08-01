<?php
// $QUERY_STRING="method=Notify&Signature=DEPRECATED&Timestamp=2013-04-28T16:15:08Z&Version=2006-05-05&Event.1.EventType=AssignmentSubmitted&Event.1.EventTime=2013-04-28T16:15:03Z&Event.1.HITTypeId=2XFMEGOOGQSC599CGGJELUUU0MVDD7&Event.1.HITId=2ZKKYEPLSP753OD82XUODHQEYPCQWX&Event.1.AssignmentId=2VQHVI44OS2K4XZFTSQSEAP5C4KZ5H";
$QUERY_STRING=substr($_SERVER['REQUEST_URI'],strrpos($_SERVER['REQUEST_URI'], "?")+1);
//$a = explode('&', $str);//
//print_r(parse_url($str));//echo $_GET['method'];
parse_str($QUERY_STRING, $a);//split('=', $a[$i]);//print_r($a);array_shift($a);array_shift($a);array_shift($a);array_shift($a);echo $a["Event_1_EventType"];


$i=0;
$event_number=1;
while($i<count($a['method'])){
	if(trim($a["Event_".$event_number."_EventType"]) == "AssignmentSubmitted")
	{	
	$hitid=trim($a["Event_".$event_number."_HITId"]);
	$assignid=trim($a["Event_".$event_number."_AssignmentId"]);
	$cmd="/var/www/smsdb/weed_app/executable/update_assign.sh 2 ".trim($a["Event_".$event_number."_HITId"])." ".trim($a["Event_".$event_number."_AssignmentId"]); 
    $files=array();
    exec($cmd,$files);
    echo $cmd;
	//java -jar weedid_amt.jar 2  trim($a["Event_".$event_number."_HITId"]) trim($a["Event_".$event_number."_AssignmentId"]);
	//java -jar weedid_amt.jar trim($a["Event_".$event_number."_HITId"]) 1 3600;
	$event_number++;
	
}else{
	echo "nothing to do";
}

$i++;
}
//print_r($a);
$myFile = "/var/www/smsdb/weed_app/executable/requestfile3.txt";
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
