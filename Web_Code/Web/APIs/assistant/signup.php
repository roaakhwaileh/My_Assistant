<?php

    include "../../configuration/db.php";
    $fullname = $_POST["fullname"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $phone = $_POST["phone"];
    $gender = $_POST["gender"];
    $birthdate = $_POST["birthdate"];
    $experiences = $_POST["experiences"];
    $photo= $_POST["photo"];
    $personal= $_POST["personal_identification"];
    $disease= $_POST["disease_freedom"];
    $details_specialty=$_POST["details_specialty"];
    $value = explode(",", $details_specialty);
       

     if (isset($_POST['photo'])) {
        $target_dir = "../../dashboard/assets/images/assistant/";
        $imagestore = rand() . "_" . time() . ".jpeg";
        $target_dir = $target_dir . "/" . $imagestore;
        file_put_contents($target_dir, base64_decode($photo));
        $result = [];
        $url_photo = "https://myassistant.jaml46.net/dashboard/assets/images/assistant/" . $imagestore;
       }
       
     if (isset($_POST['personal_identification'])) {
        $target_dir = "../../dashboard/assets/images/personal_identification/";
        $imagestore = rand() . "_" . time() . ".jpeg";
        $target_dir = $target_dir . "/" . $imagestore;
        file_put_contents($target_dir, base64_decode($personal));
        $result = [];
        $url_personal = "https://myassistant.jaml46.net/dashboard/assets/images/personal_identification/" . $imagestore;
       }  
        
     if (isset($_POST['disease_freedom'])) {
        $target_dir = "../../dashboard/assets/images/disease_freedom/";
        $imagestore = rand() . "_" . time() . ".jpeg";
        $target_dir = $target_dir . "/" . $imagestore;
        file_put_contents($target_dir, base64_decode($disease));
        $result = [];
        $url_disease = "https://myassistant.jaml46.net/dashboard/assets/images/disease_freedom/" . $imagestore;
       }  



    $insert = "INSERT INTO `assistant` (`fullname`, `email`, `password`, `phone`, `gender`, `photo`, `birthdate`, `personal_identification`, `disease_freedom`, `status`, `reg_date`, `rating`) 
    VALUES ('$fullname', '$email', '$password', '$phone', '$gender', '$url_photo', '$birthdate', '$url_personal', '$url_disease', 'inactive', '$current_date', '0');";
    $result = mysqli_query( $link, $insert );
    if ( $result )
    {
        $lastid = mysqli_insert_id($link);
        foreach ($value as $str) {
                                            $insert1 = "INSERT INTO `details_specialty` (`assistant_id`, `specialty_id`) VALUES ('$lastid', '$str');";
                                            $result1 = mysqli_query( $link, $insert1 );
                                            if ( $result1 )
                                            {
                                                $rows["id"] = 1;
                                                $rows["data"] = 1;
                                               
                                            }
                                            else
                                            {
                                                $rows["id"] = 0;
                                                $rows["data"] = 0;
                                            }
         
         
        }
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
