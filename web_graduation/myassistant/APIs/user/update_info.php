<?php
    
    include("../../configuration/db.php");
    
    $user_id = $_GET["user_id"];
    $fullname = $_GET["fullname"];
    $email = $_GET["email"];
    $password = $_GET["password"];
    $phone = $_GET["phone"];
    $birthdate = $_GET["birthdate"];
    $gender = $_GET["gender"];
    
    $update = "UPDATE `user` SET `fullname`='$fullname',`email`='$email',`password`='$password',`phone`='$phone',`birthdate`='$birthdate',`gender`='$gender' WHERE `user_id`='$user_id'";
    $result = mysqli_query( $link , $update );
    $rows = array();
    if ( $result )
    {
        $rows["id"] = 1;
        $rows["data"] = 1;
    } 
    else 
    {
        $rows["id"] = 0;
        $rows["data"] = 0;
    }
      
    
    echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
?>