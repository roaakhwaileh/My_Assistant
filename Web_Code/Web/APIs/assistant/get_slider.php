<?php

 include("../../configuration/db.php");
 
 $select = "SELECT * FROM `slider` WHERE status='active'";
 $result = mysqli_query( $link , $select );
 if (mysqli_num_rows( $result ) >0 )
  {
        $rows = array();
        while($temp = mysqli_fetch_array($result))
        {
            array_push($rows,array( "image_id"=>$temp["image_id"] , "image_url"=>$temp["image_url"], "redirect_url"=>$temp["redirect_url"], "image_url"=>$temp["image_url"]));
        }
        
  } 
  else 
  {
        $rows["id"] = 0;
        $rows["data"] = 0;
  }
  
  echo json_encode( $rows , JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
   
?>
