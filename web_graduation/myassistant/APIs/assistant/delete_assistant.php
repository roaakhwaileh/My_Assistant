<?php include '../../configuration/db.php'; ?>
<?php 
   
    $assistant_id=$_GET['assistant_id'];
    
    $delete="DELETE FROM `assistant` WHERE `assistant_id`='$assistant_id'";
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