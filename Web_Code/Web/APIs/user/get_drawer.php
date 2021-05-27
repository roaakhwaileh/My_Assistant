<?php

 include("../../configuration/db.php");
 $user_id = $_GET["user_id"];
 
 $select = "SELECT * FROM `user` WHERE `user_id`='$user_id'";
 $result = mysqli_query( $link , $select );
 $rows = array();
 if (mysqli_num_rows( $result ) >0 )
  {
        while($temp = mysqli_fetch_array( $result ))
        {
            array_push($rows,array("fullname"=>$temp["fullname"], "photo"=>$temp["photo"]));
        }
       
  } 
  else 
  {
          $rows["id"] = 0;
          $rows["data"] = 0;
  }
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
   
?>

