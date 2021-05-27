<?php

 include("../../configuration/db.php");
 $email = $_GET["email"];
 $password = $_GET["password"];
 
 $select = "select * from assistant where email ='$email' and password ='$password' and status = 'active'";
 $result = mysqli_query( $link , $select );
 $temp = mysqli_fetch_array( $result );
 $assistant_id = $temp["assistant_id"];
 $rows = array();
 if (mysqli_num_rows( $result ) >0 )
  {
        $rows["id"] = '1';
        $rows["data"] = '1';
        $rows["assistant_id"] = "$assistant_id";
        
  } 
  else 
  {
        $rows["id"] = '0';
        $rows["data"] = '0';
        $rows["assistant_id"] = '0';
  }
  
  echo json_encode($rows, JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
   
?>