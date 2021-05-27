<?php

 include("../../configuration/db.php");
 $user_id = $_GET["user_id"];
 
 $select = "SELECT a.*,b.fullname AS user_name,c.fullname AS assistant_name,d.specialty_name FROM req_assistant a INNER JOIN user b on (a.user_id=b.user_id) INNER JOIN assistant c 
 ON (a.assistant_id=c.assistant_id) INNER JOIN specialty d ON (a.specialty_id=d.specialty_id) WHERE a.user_id='$user_id'";
 $result = mysqli_query( $link , $select );
 $rows = array();
 if (mysqli_num_rows( $result ) >0 )
  {
        while($temp = mysqli_fetch_array( $result ))
        {
            array_push($rows,array("req_assistant_id"=>$temp["req_assistant_id"] , "user_id"=>$temp["user_id"] ,
             "assistant_id"=>$temp["assistant_id"], "description"=>$temp["description"], "whatsapp_phone"=>$temp["whatsapp_phone"], "location"=>$temp["location"],
             "status"=>$temp["status"], "specialty_id"=>$temp["specialty_id"], "hourly_work_id"=>$temp["hourly_work_id"], "user_name"=>$temp["user_name"],
             "assistant_name"=>$temp["assistant_name"], "specialty_name"=>$temp["specialty_name"]));
        }
       
  } 
  else 
  {
          $rows["id"] = 0;
          $rows["data"] = 0;
  }
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
   
?>

