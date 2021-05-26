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
                            <h5 class="card-title">View Feedback assistant</h5>
                            <form id="signupForm" class="col-md-10 mx-auto" method="POST" action="" enctype="multipart/form-data">
                               <div class="form-group">
                                    <label for="user_name" >Select assistant</label>
                                    <select required name="user_name" class="form-control" id="user_name">
                                        <option value="" selected disabled>Select assistant</option>
                                        <?php
                                            $sql = "SELECT * FROM assistant";
                                            if ($result = mysqli_query($link, $sql)) {
                                                if (mysqli_num_rows($result) > 0) {
                                                    while ($row = mysqli_fetch_array($result)) {
                                                        echo ("<tr>");
                                                            $assistant_id = $row['assistant_id'];
                                                            $name= $row['fullname'];
                                                            print '<option value="'.$assistant_id.'">'.$name.'</option>';
                                                        echo ("</tr>");
                                                    }
                                                    mysqli_free_result($result);
                                                } else {  }
                                                } else {  }
                                        ?>
                                    </select>
                                </div>
                          <!--    Table      -->
                            <div class="card-body">
                                            <table style="width: 100%;" id="example" class="table table-hover table-striped table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>Form assistant</th>
                                                        <th>Title</th>
                                                        <th>Text</th>
                                                        <th>Date Time</th>
                                                   </tr>
                                                </thead>
                                                <tbody id="result">
                                                   
                                    
                                  </tbody>
                                 </table>
                                </div>
                        <!--   Table      -->
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
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
</body>
</html>
<!--select rewards-->
<script>
    $("#user_name").change(function(){
        var id = $("#user_name").val();
        $.ajax({
            url :"select_feedback_assistant?id="+id,
            method :"post",
            dataType :"text",
            success:function(data)
            {
                if(data == "error")
                {
                    $("#result").html(data);
                }
                else
                {
                    $("#result").html(data);    
                }
                
            }
            
        });
    });
</script>