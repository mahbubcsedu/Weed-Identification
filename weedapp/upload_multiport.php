<?php

// Where the file is going to be placed

//$target_path_image = "/var/www/smsdb/weed_app/weedapp/Audio_Capture/";
$target_path_audio="/var/www/smsdb/weed_app/weedapp/Audio_Capture/";
 

/* Add the original filename to our target path.

Result is "uploads/filename.extension" */

//$target_path_image = $target_path_image.basename( $_FILES['image']['name']); 
$target_path_audio = $target_path_audio.basename( $_FILES['audio']['name']); 

 
$base=$_REQUEST['image'];
    $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');
    $file = fopen('Image_Capture/uploaded_image.jpg', 'wb');
    fwrite($file, $binary);
    fclose($file);
    echo 'Image upload complete!!, Please check your php file directory……';

if(move_uploaded_file($_FILES['audio']['tmp_name'], $target_path_audio)) {

    echo "The file ".  basename( $_FILES['audio']['name']).

    " has been uploaded";

} else{

    echo "There was an error uploading the file, please try again!";

    echo "filename: " .  basename( $_FILES['audio']['name']);

    echo "target_path: " .$target_path_audio;

}
 $comments=$_REQUEST['comments'];
 echo $comments;
?>