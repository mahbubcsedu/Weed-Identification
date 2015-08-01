<?php
$folder = '../noisyimages/imageusingdifferentnoisedistortion/'; 
   
    $item = $_GET["imageid"];
    if ($item == "")
    {
        $item = 1;
    }

   
   ?>
    <select id='gallery_id' name='gallery_id' onChange="window.location='dropdown2.php?imageid='+this.value" style='width:200px;' >
    <?

    echo '<option name="image" >'."\n". 
     dropdown(image_filenames($folder), @$_POST['imageid']). 
     '</option>'
   
  ?>
    </select>
    </td>
<?
    echo "<td colspan='2' rowspan='2'>";
?>
    <img height="120" width="80" src=' <?=$folder.$item?> '/></td>
 <?
 



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