<?php
    
    include("../../configuration/db.php");
    
    $assistant_id = $_GET["assistant_id"];
    $fullname = $_GET["fullname"];
    $email = $_GET["email"];
    $phone = $_GET["phone"];
    $birthdate = $_GET["birthdate"];
    $gender = $_GET["gender"];
    
    $update = "UPDATE `assistant` SET `fullname`='$fullname',`email`='$email',`phone`='$phone',`gender`='$gender',`birthdate`='$birthdate' WHERE `assistant_id`='$assistant_id'";
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