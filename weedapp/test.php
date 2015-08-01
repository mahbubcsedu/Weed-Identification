
<?php 
  $cur_dir=getcwd();
    $phpfile=$cur_dir."/send_notification.php";
    $cmd="php ".$phpfile." expert 305";
//echo $cmd;
    echo exec($cmd);
?>