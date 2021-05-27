 <?php
       include '../../../configuration/db.php'; 
       $assistant_id = $_GET["id"];
       $html = "";
       $status1="active";
       $status2="inactive";
       $sql = "SELECT a.*,b.fullname FROM feedback a INNER JOIN assistant b ON (a.assistant_id=b.assistant_id) where a.assistant_id='$assistant_id'";
       if ($result = mysqli_query($link, $sql))
       {
           if (mysqli_num_rows($result) > 0)
           {
               while ($row = mysqli_fetch_array($result))
               {
                   $html .= "<tr>";
                   $html .='<td>'.$row['fullname'].' </td>';
                   $html .= '<td>'.$row['title'].' </td>';
                   $html .= '<td>'.$row['text'].' </td>';
                   $html .= '<td>'.$row['datetime'].' </td>';
                   $html .= "</tr>";
                }
mysqli_free_result($result);
            }
            else
            {
                $html = "No Data To assistant";
            }
   }
   else
   {
       $html = "error";
   }
   
echo $html;
?>