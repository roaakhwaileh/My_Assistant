 <?php
       include '../../../configuration/db.php'; 
       $assistant_id = $_GET["id"];
       $html = "";
       $status1="active";
       $status2="inactive";
       $sql = "SELECT a.*,b.fullname,c.specialty_name FROM hourly_work a INNER JOIN assistant b ON (a.assistant_id=b.assistant_id) 
       INNER JOIN specialty c ON (a.specialty_id=c.specialty_id) where a.assistant_id='$assistant_id'";
       if ($result = mysqli_query($link, $sql))
       {
           if (mysqli_num_rows($result) > 0)
           {
               while ($row = mysqli_fetch_array($result))
               {
                   $html .= "<tr>";
                   $html .='<td>'.$row['fullname'].' </td>';
                   $html .= '<td>'.$row['specialty_name'].' </td>';
                   $html .= '<td>'.$row['price'].' </td>';
                   $html .= "</tr>";
                }
mysqli_free_result($result);
            }
            else
            {
                $html = "No Data To User";
            }
   }
   else
   {
       $html = "error";
   }
   
echo $html;
?>