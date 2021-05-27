<?php

 include("../../configuration/db.php");
 
 $assistant_id=$_GET['assistant_id'];
 
 $select = "SELECT a.*,b.specialty_name FROM hourly_work a INNER JOIN specialty b ON (a.specialty_id=b.specialty_id) WHERE `assistant_id` = '$assistant_id'";
 $result = mysqli_query( $link , $select );
 if (mysqli_num_rows( $result ) >0 )
  {
        $rows = array();
        while($temp = mysqli_fetch_array($result))
        {
            array_push($rows,array( "hourly_work_id"=>$temp["hourly_work_id"] , "assistant_id"=>$temp["assistant_id"], "specialty_id"=>$temp["specialty_id"], "specialty_name"=>$temp["specialty_name"], "price"=>$temp["price"]));
        }
        
  } 
  
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
?>

