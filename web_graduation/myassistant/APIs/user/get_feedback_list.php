<?php

 include("../../configuration/db.php");
 
 $assistant_id = $_GET["assistant_id"];
 
 $select = "SELECT * FROM `feedback` WHERE `assistant_id`='$assistant_id' && `user_id`='-1'";
 $result = mysqli_query( $link , $select );
 $rows = array();
 if (mysqli_num_rows( $result ) >0 )
  {
        while($temp = mysqli_fetch_array( $result ))
        {
            array_push($rows,array("title"=>$temp["title"] , "text"=>$temp["text"] ,"datetime"=>$temp["datetime"]));
        }
       
  } 
  else 
  {
          $rows["id"] = 0;
          $rows["data"] = 0;
  }
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
   
?>

