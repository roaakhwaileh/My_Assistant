<?php
    
    include("../../configuration/db.php");
    
    $user_id = $_POST["user_id"];
    $photo=$_POST['photo'];
     $rows = array();
    if(isset($_POST['photo']))
    {
    	$target_dir = "../../dashboard/assets/images/user/";
    	$imagestore = rand()."_".time().".jpeg";
    	$target_dir = $target_dir."/".$imagestore;
    	file_put_contents( $target_dir , base64_decode($photo) );
    	
    	$finalurl = "https://myassistant.jaml46.net/dashboard/assets/images/user/" . $imagestore ;
        
        $query = "update user set photo = '$finalurl' where user_id = '$user_id'";
        $result = mysqli_query( $link , $query);
        
        
        if ( $result )
        {
       
            $rows['id'] = "1";
            $rows['data'] = "1";
            $rows['photo']="$finalurl";
            
        }
        else
        { 
            $rows['id'] = "0";
            $rows['data'] = "0";
            $rows['photo']= "0";
        }
} 
echo json_encode($rows, JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);
?>