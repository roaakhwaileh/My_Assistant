<?php include '../../configuration/db.php'; ?>
<?php 
   
    $user_id=$_GET['user_id'];
    
    $delete="DELETE FROM `user` WHERE `user_id`='$user_id'";
    $result1=mysqli_query($link,$delete);
        if($result1)
        {
        
        $rows['id']='1';
        $rows['data']='1';
            
        }
        
        else{
        $rows['id']='0';
        $rows['data']='0';
            
        }
   
         echo json_encode($rows, JSON_UNESCAPED_UNICODE|JSON_PRETTY_PRINT);

?>