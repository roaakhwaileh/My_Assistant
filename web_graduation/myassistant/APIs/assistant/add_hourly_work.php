<?php

    include "../../configuration/db.php";
    
    $assistant_id = $_GET["assistant_id"];
    $specialty_id = $_GET["specialty_id"];
    $price = $_GET["price"];
    
    $select="select * from hourly_work where assistant_id='$assistant_id' && specialty_id='$specialty_id'";
    $result=mysqli_query($link,$select);
    $row=mysqli_fetch_assoc($result);
    $db_assistant_id=$row['assistant_id'];
    $db_specialty_id=$row['specialty_id'];
    
    if($db_assistant_id == $assistant_id && $db_specialty_id==$specialty_id){
        
      $delete="UPDATE `hourly_work` SET `price`='$price'  WHERE `assistant_id`='$assistant_id' && `specialty_id`='$specialty_id'";
      $result1=mysqli_query($link,$delete);
        if($result1)
        {
            $rows["id"] = 1;
            $rows["data"] = 1;
            
        }
        
        else{
        $rows['id']=0;
        $rows['data']=0;
            
        }  
      
        
    }else{
    
    $insert = "INSERT INTO `hourly_work` (`assistant_id`, `specialty_id`, `price`) VALUES ('$assistant_id', '$specialty_id', '$price');";
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
    }
    
    echo json_encode($rows, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

?>

