<?php
    session_start();
    if ( $_SESSION['admin'] == 'YES') {
        $current_admin = $_SESSION['admin'];
        $current_admin_id = $_SESSION['admin_id'];
        $current_admin_name = $_SESSION['full_name'];
        $current_admin_permissions = $_SESSION['permissions'];
        $current_admin_photo = $_SESSION['photo'];
        $current_admin_role = $_SESSION['role'];
    } else {
        echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/","_self"); </script>';
        exit;
    }
?>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>My Assistant | Dashboard</title>
    <link rel="icon" href="../../assets/images/logo.png" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
    <meta name="description" content="This is an example dashboard created using build-in elements and components.">
    <meta name="msapplication-tap-highlight" content="no">
    <link href="../../style.css" rel="stylesheet">
</head>

<body>
    <div class="app-container app-theme-white body-tabs-shadow fixed-header fixed-sidebar">
        <?php include '../../include_header.php'; ?>
        <?php include '../../include_theme_settings.php'; ?>
        <?php include '../../../configuration/db.php';?>
        
        
        <div class="app-main">
            <div class="app-sidebar sidebar-shadow">
                <div class="app-header__logo">
                    <div class="logo-src"></div>
                    <div class="header__pane ml-auto">
                        <div>
                            <button type="button" class="hamburger close-sidebar-btn hamburger--elastic" data-class="closed-sidebar">
                                <span class="hamburger-box">
                                    <span class="hamburger-inner"></span>
                                </span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="app-header__mobile-menu">
                    <div>
                        <button type="button" class="hamburger hamburger--elastic mobile-toggle-nav">
                            <span class="hamburger-box">
                                <span class="hamburger-inner"></span>
                            </span>
                        </button>
                    </div>
                </div>
                <div class="app-header__menu">
                    <span>
                        <button type="button" class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
                            <span class="btn-icon-wrapper">
                                <i class="fa fa-ellipsis-v fa-w-6"></i>
                            </span>
                    </button>
                    </span>
                </div>
                
                <?php include '../../include_side_nav.php'; ?>
                
            </div>
            <div class="app-main__outer">
                <div class="app-main__inner">
                    <div class="tabs-animation">
                        <div class="card mb-3">
                            <div class="card-header-tab card-header">
                                <div class="card-header-title font-size-lg text-capitalize font-weight-normal"><i class="header-icon lnr-laptop-phone mr-3 text-muted opacity-6"></i>
                                <?php print "Active User In My Assistant"; ?>
                                </div>
                            </div>
                            <div class="card-body">
                                <table style="width: 100%;" id="example" class="table table-hover table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Image</th>
                                            <th>Name</th>
                                            <th>Phone</th>
                                            <th>Email</th>
                                            <th>Password</th>
                                            <th>Birthdate</th>
                                            <th>Reg Data</th>
                                            <th>Status</th>
                                            <th>Gender</th>
                                            <th>Rating</th>
                                            <th>Update Status</th>
                                            <th>Delete</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php
                                             $status1="active";
                                            $status2="inactive";
                                            $sql = "SELECT * FROM `user` WHERE status='active'";
                                            if ($result = mysqli_query($link, $sql)) {
                                                if (mysqli_num_rows($result) > 0) {
                                                    while ($row = mysqli_fetch_array($result)) {
                                                        echo ("<tr>");
                                                            $id = $row['user_id'];
                                                            echo'<td scope="row"> <img src="'.$row["photo"].'" width=70%; hight=70%;></td>';
                                                            echo ('<td>'.$row['fullname'].' </td>');
                                                            echo ('<td>'.$row['phone'].' </td>');
                                                            echo ('<td>'.$row['email'].' </td>');             
                                                            echo ('<td>'.$row['password'].' </td>');             
                                                            echo ('<td>'.$row['birthdate'].' </td>'); 
                                                            echo ('<td>'.$row['reg_date'].' </td>');
                                                            echo ('<td>'.$row['status'].' </td>');
                                                            echo ('<td>'.$row['gender'].' </td>');
                                                            echo ('<td>'.$row['rating'].' </td>');
                                                            echo ('<td><a class="btn btn-success" href="https://myassistant.jaml46.net/dashboard/pages/system/active_user?id='.$row['user_id'].'&status='.$status1.'">Active</a><a class="btn btn-danger" href="https://myassistant.jaml46.net/dashboard/pages/system/active_user?id='.$row['user_id'].'&status='.$status2.'">Inactive</a></td>');
                                                            echo ('<td><button class="btn btn-sm btn-danger"><a style="color:#fff;" href="#" onclick="deleteAlert('.$id.')">Delete</a></button></td>');
                                                        
                                                        echo ("</tr>");

                                                    }
                                                    mysqli_free_result($result);
                                                }
                                            }
                                        ?>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <?php include '../../include_footer.php'; ?>
            </div>
        </div>
    </div>

    <?php include '../../include_right_drawer.php'; ?>

    <div class="app-drawer-overlay d-none animated fadeIn"></div>
    <script type="text/javascript" src="../../assets/scripts/script.js"></script>
     <!--Sweet alert-->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
    <script>
     function deleteAlert(id)
        {
           Swal.fire({
                      title: 'Are you sure?',
                      text: "You won't be able to revert this!",
                      icon: 'warning',
                      showCancelButton: true,
                      confirmButtonColor: '#3085d6',
                      cancelButtonColor: '#d33',
                      confirmButtonText: 'Yes, delete it!'
                    }).then((result) => {
                      if (result.isConfirmed)
                      {
                          $.ajax({
                                url:"https://myassistant.jaml46.net/dashboard/pages/system/delete_user?id="+id,
                                method:"post",
                                dataType:"text",
                                success:function(data)
                                { 
                                   
                                   if(data == "yes")
                                   {
                                        Swal.fire({
                                          title: 'Deleted!',
                                          text: "Your file has been deleted.",
                                          icon: 'success',
                                          showCancelButton: false,
                                          confirmButtonColor: '#3085d6',
                                          cancelButtonColor: '#d33',
                                          confirmButtonText: 'OK'
                                        }).then((result) => {
                                          if (result.isConfirmed) {
                                            window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_user_info","_self");        
                                          }
                                        })
                                    
                                   }
                                   else
                                   {
                                        Swal.fire({
                                          title: 'Deleted!',
                                          text: "Your file has been deleted.",
                                          icon: 'success',
                                          showCancelButton: false,
                                          confirmButtonColor: '#3085d6',
                                          cancelButtonColor: '#d33',
                                          confirmButtonText: 'OK'
                                        }).then((result) => {
                                          if (result.isConfirmed) {
                                            window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_user_info","_self");        
                                          }
                                        })
                                       
                                   }
                                }
                            });  
                        }
                          
                      
                    })
                    
        }
    </script>
</body>

</html>