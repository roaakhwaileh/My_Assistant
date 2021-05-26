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
        <?php include '../../../configuration/db.php'; ?>
       

<?php
           
          if(isset($_POST['submit'])){
              
                $redirect=$_POST['redirect'];
              
                $rand = rand(1000000,9999999);
                $newfilename = "image_slidr_$rand";
                if(isset($_FILES['photo'])){
                      $errors= array();
                      $file_name = $_FILES['photo']['name'];
                      $file_size =$_FILES['photo']['size'];
                      $file_tmp =$_FILES['photo']['tmp_name'];
                      $file_type=$_FILES['photo']['type'];
                      $file_ext = strtolower(end(explode('.',$_FILES['photo']['name'])));
                      $expensions= array("jpeg","jpg","png");
                      if(file_exists($file_name)) {
                        echo "Sorry, file already exists.";
                        }
                      if(in_array($file_ext,$expensions)=== false){
                         $errors[]="extension not allowed, please choose a JPEG or PNG file.";
                      }
                
                      if(empty($errors)==true){
                          
                        move_uploaded_file($file_tmp,"../../assets/images/image_slider/".$newfilename.".".$file_ext);
                        $url = "https://myassistant.jaml46.net/dashboard/assets/images/image_slider/".$newfilename.".".$file_ext;
                      }
                
                      else{
                         print_r($errors);
                      }
                   }
       
                $query = "INSERT INTO `slider` (`image_url`, `redirect_url`, `added_date`, `status`) VALUES ('$url', '$redirect', '$current_datetime', 'active');";
                
                $result = mysqli_query($link , $query);
                 if($result)
                {
                
               echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_Manage_slider","_self"); </script>';
                    
                }
                else
                {
                    echo" <script> alert('Add non successfully') </script> ";
                    echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/pages/people/view_Manage_slider","_self"); </script>';
                    
                }
                
            }
      ?>
      
      
      
      
      
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
                    <div class="main-card mb-3 card">
                        <div class="card-body">
                            <h5 class="card-title">Add Slider Image</h5>
                            <form id="signupForm" class="col-md-10 mx-auto" method="POST" action="" enctype="multipart/form-data">

                                <div class="form-group">
                                    <label for="photo">Slider Image</label>
                                    <div>
                                        <input required  type="file" class="form-control" id="photo" name="photo" placeholder=""/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="redirect">Redirect Url</label>
                                    <div>
                                        <input required  type="text" class="form-control" id="redirect" name="redirect" placeholder="Enter Redirect Url"/>
                                    </div>
                                </div>
                                
                                <div style="text-align:center;" class="center form-group">
                                    <button style="text-align:center;" type="submit" value="submit" name="submit" class="btn btn-primary">Add Slider Image</button>
                                </div>
                            </form>
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
    
    
</body>

</html>