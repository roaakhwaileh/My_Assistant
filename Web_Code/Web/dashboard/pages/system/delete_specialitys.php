<?php

   include '../../../configuration/db.php'; 
  
  $html;
  $id=$_GET['id'];
  $delete="DELETE FROM `specialty` WHERE specialty_id='$id'";
  
 if(mysqli_query($link,$delete))
  {
    $html = "yas";
  }
  else
  {
      $html = "no";
  }
  
  echo $html;
?>
