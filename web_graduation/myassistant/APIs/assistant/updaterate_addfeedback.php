<?php
    
    include("../../configuration/db.php");
    
    $assistant_id = $_GET["assistant_id"];
    $rating = $_GET["rating"];
    $title = $_GET["title"];
    $text = $_GET["text"];
    
    
    $update = "UPDATE `assistant` SET `rating`= '$rating' WHERE `assistant_id`='$assistant_id'";
    $result = mysqli_query( $link , $update );
    $rows = array();
    if ( $result )
    {
            
            
            $insert = "INSERT INTO `feedback` (`title`, `text`, `user_id`, `assistant_id`, `datetime`) VALUES ('$title', '$text', '-1', '$assistant_id', '$current_date');";
            $result1 = mysqli_query( $link, $insert );
            $rows = array();
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
    else 
    {
        $rows["id"] = 0;
        $rows["data"] = 0;
    }
      
    
    echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
?>