<?php

    include "../../configuration/db.php";
    
    $assistant_id = $_GET["assistant_id"]; //assistant report
    $user_id = $_GET["user_id"];           //from user
    $text = $_GET["text"];
    
    
                            
    $select="SELECT fullname FROM `assistant` WHERE assistant_id='$assistant_id'";
    $result=mysqli_query($link,$select);
    $row=mysqli_fetch_assoc($result);
    $fullname=$row['fullname'];
    
                            
    $insert = "INSERT INTO `report` (`user_id`, `assistant_id`, `name`, `text`, `send_date`) VALUES ('$user_id', '-1', '$fullname', '$text', '$current_date');";
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
