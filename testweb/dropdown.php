<?php 

$folder = '../noisyimages/imageusingdifferentnoisedistortion/'; 

echo '<form action="" method="post">'."\n".'<select name="image">'."\n". 
     dropdown(image_filenames($folder), @$_POST['image']). 
     '</select>'."\n".'</form>'; 

function image_filenames($dir) 
{ 
    $handle = @opendir($dir) 
        or die("I cannot open the directory '<b>$dir</b>' for reading."); 
    $images = array(); 
    while (false !== ($file = readdir($handle))) 
    { 
        if (eregi('\.(jpg|gif|png)$', $file)) 
        { 
            $images[] = $file; 
        } 
    } 
    closedir($handle); 
    return $images; 
} 

function dropdown($options_array, $selected = null) 
{ 
    $return = null; 
    foreach($options_array as $option) 
    { 
        $return .= '<option value="'.$option.'"'. 
                   (($option == $selected) ? ' selected="selected"' : null ). 
                   '>'.$option.'</option>'."\n"; 
    } 
    return $return; 
} 

?>