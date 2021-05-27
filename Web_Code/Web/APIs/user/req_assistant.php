<?php

 include("../../configuration/db.php");
 $assistant_id = $_GET["assistant_id"];
 
 $select = "SELECT a.*,c.*,d.* FROM assistant a INNER JOIN details_specialty b ON (a.assistant_id=b.assistant_id) 
 INNER JOIN specialty c ON (b.specialty_id=c.specialty_id) INNER JOIN hourly_work d ON (b.assistant_id=d.assistant_id) AND (b.specialty_id=d.specialty_id)";
 $result = mysqli_query( $link , $select );
 $rows = array();
 if (mysqli_num_rows( $result ) >0 )
  {
        while($temp = mysqli_fetch_array( $result ))
        {
            array_push($rows,array("assistant_id"=>$temp["assistant_id"] , "fullname"=>$temp["fullname"], "phone"=>$temp["phone"],"photo"=>$temp["photo"], "specialty_id"=>$temp["specialty_id"], "specialty_name"=>$temp["specialty_name"]
            , "hourly_work_id"=>$temp["hourly_work_id"], "price"=>$temp["price"]));
        }
       
  } 
  else 
  {
          $rows["id"] = 0;
          $rows["data"] = 0;
  }
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
   
?>
