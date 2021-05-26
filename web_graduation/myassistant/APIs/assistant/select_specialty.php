<?php

 include("../../configuration/db.php");
 
 $select = "SELECT * FROM `specialty`";
 $result = mysqli_query( $link , $select );
 if (mysqli_num_rows( $result ) >0 )
  {
        $rows = array();
        while($temp = mysqli_fetch_array($result))
        {
            array_push($rows,array( "specialty_id"=>$temp["specialty_id"] , "specialty_name"=>$temp["specialty_name"]));
        }
        
  } 
  else 
  {
        $rows["id"] = 0;
        $rows["data"] = 0;
  }
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
?>