<?php

    include("../../configuration/db.php");
    $fullname = $_GET["fullname"];
    $email = $_GET["email"];
    $phone = $_GET["phone"];
    $massage = $_GET["massage"];
    
    $insert = "INSERT INTO `feedback_app` (`fullname`, `email`, `phone`, `massage`) VALUES ('$fullname', '$email', '$phone', '$massage');";
    $result = mysqli_query( $link , $insert );
    if( $result )
    {
        $rows["id"] = 1;
        $rows["data"] = 1;
    }
    else
    {
        $rows["id"] = 0;
        $rows["data"] = 0;
    }
  
  echo json_encode($rows, JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
   
?>