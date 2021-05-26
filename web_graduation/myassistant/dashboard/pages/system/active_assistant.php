
<?php
  include '../../../configuration/db.php';
  
  
  $id=$_GET['id'];
  $status=$_GET['status'];
  $update="update assistant set status='$status' where assistant_id='$id'";
  if(mysqli_query($link,$update))
  {  
      if($status=='active'){echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_assistant_info","_self"); </script>';}
      else{echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_assistant_inactive","_self"); </script>';}
  }
  else
  {
      echo" <script> alert('update non_successfully') </script> ";
      echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_assistant_info","_self"); </script>';
  }
?>


