
<?php
  include '../../../configuration/db.php';
  
  
  $id=$_GET['id'];
  $status=$_GET['status'];
  $update="update slider set status='$status' where image_id='$id'";
  if(mysqli_query($link,$update))
  {
   echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_Manage_slider","_self"); </script>';
  }
  else
  {
      echo" <script> alert('update non_successfully') </script> ";
      echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_Manage_slider","_self"); </script>';
  }
?>


