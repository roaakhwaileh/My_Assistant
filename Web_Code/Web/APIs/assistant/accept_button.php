<?php
    
    include("../../configuration/db.php");
    
    $req_assistant_id = $_GET["req_assistant_id"];
    
    $update = "UPDATE `req_assistant` SET `status`='1' WHERE `req_assistant_id`='$req_assistant_id'";
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