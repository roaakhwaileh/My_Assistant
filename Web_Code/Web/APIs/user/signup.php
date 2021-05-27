<?php

    include "../../configuration/db.php";
    $fullname = $_POST["fullname"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $phone = $_POST["phone"];
    $gender = $_POST["gender"];
    $birthdate = $_POST["birthdate"];
    $photo= $_POST["photo"];

     if (isset($_POST['photo'])) {
        $target_dir = "../../dashboard/assets/images/user/";
        $imagestore = rand() . "_" . time() . ".jpeg";
        $target_dir = $target_dir . "/" . $imagestore;
        file_put_contents($target_dir, base64_decode($photo));
        $result = [];
        $url_photo = "https://myassistant.jaml46.net/dashboard/assets/images/user/" . $imagestore;
       }
       
       
    $insert = "INSERT INTO `user` ( `fullname`, `email`, `password`, `phone`, `status`, `photo`, `birthdate`, `gender`, `reg_date`, `rating`) 
    VALUES ( '$fullname', '$email', '$password', '$phone', 'active', '$url_photo', '$birthdate', '$gender', '$current_date', '0');";
    $result = mysqli_query( $link, $insert );
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
    
    echo json_encode($rows, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

?>
