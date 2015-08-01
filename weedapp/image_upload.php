<?php

    $base=$_REQUEST['image'];
     $binary=base64_decode($base);
    header('Content-Type: bitmap; charset=utf-8');
    $file = fopen('Image_Capture/uploaded_image.jpg', 'wb');
    fwrite($file, $binary);
    fclose($file);
    echo 'Image upload complete!!, Please check your php file directory……';

?>