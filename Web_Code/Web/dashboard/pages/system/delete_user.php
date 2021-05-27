<?php

   include '../../../configuration/db.php'; 
  
  $html;
  $id=$_GET['id'];
  $delete="DELETE FROM `user` WHERE user_id='$id'";
  
 if(mysqli_query($link,$delete))
  {
    $html = "yes";
  }
  else
  {
      $html = "no";
  }
  
  echo $html;
?>
