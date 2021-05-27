<?php

    include "../../configuration/db.php";
    $user_id = $_GET["user_id"];
    $assistant_id = $_GET["assistant_id"];
    $description = $_GET["description"];
    $whatsapp_phone = $_GET["whatsapp_phone"];
    $location = $_GET["location"];
    $specialty_id= $_GET["specialty_id"];
    $hourly_work_id= $_GET["hourly_work_id"];
       
    $insert = "INSERT INTO `req_assistant` (`user_id`, `assistant_id`, `description`, `whatsapp_phone`, `location`, `status`, `specialty_id`, `hourly_work_id`) 
    VALUES ('$user_id', '$assistant_id', '$description', '$whatsapp_phone', '$location', '0', '$specialty_id', '$hourly_work_id');";
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
