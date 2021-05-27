<?php

 include("../../configuration/db.php");
 $assistant_id = $_GET["assistant_id"];
 
 $select = "SELECT * FROM `assistant` WHERE `assistant_id`='$assistant_id'";
 $result = mysqli_query( $link , $select );
 $rows = array();
 if (mysqli_num_rows( $result ) >0 )
  {
        while($temp = mysqli_fetch_array( $result ))
        {
            array_push($rows,array("fullname"=>$temp["fullname"] , "email"=>$temp["email"] ,
             "phone"=>$temp["phone"], "photo"=>$temp["photo"], "birthdate"=>$temp["birthdate"], "gender"=>$temp["gender"], "rating"=>$temp["rating"], "personal_identification"=>$temp["personal_identification"], "disease_freedom"=>$temp["disease_freedom"]));
        }
       
  } 
  else 
  {
          $rows["id"] = 0;
          $rows["data"] = 0;
  }
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
   
?>

