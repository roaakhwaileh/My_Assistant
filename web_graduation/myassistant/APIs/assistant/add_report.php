<?php

    include "../../configuration/db.php";
    
    $assistant_id = $_GET["assistant_id"]; //user report
    $user_id = $_GET["user_id"];           //from assistant
    $text = $_GET["text"];
    
    
                            
    $select="SELECT fullname FROM `user` WHERE user_id='$user_id'";
    $result=mysqli_query($link,$select);
    $row=mysqli_fetch_assoc($result);
    $fullname=$row['fullname'];
    
                            
    $insert = "INSERT INTO `report` (`user_id`, `assistant_id`, `name`, `text`, `send_date`) VALUES ('-1', '$assistant_id', '$fullname', '$text', '$current_date');";
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
