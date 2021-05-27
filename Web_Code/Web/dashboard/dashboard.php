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
    <?php
        include 'include_head.php';
        include '../configuration/db.php';
    ?>
   
<body>
    <div class="app-container app-theme-white body-tabs-shadow fixed-header fixed-sidebar">
        <?php include 'include_header.php'; ?>
        <?php include 'include_theme_settings.php'; ?>
        

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
                
                <?php include 'include_side_nav.php'; ?>
                
            </div>
            <div class="app-main__outer">
                <div class="app-main__inner">
                    <div class="tabs-animation">
                        <div class="mb-3 card">
                            <div class="card-header-tab card-header">
                                <div class="card-header-title font-size-lg text-capitalize font-weight-normal">
                                    <i class="header-icon lnr-charts icon-gradient bg-happy-green"> </i> Website Analysis
                                </div>
                            </div>
                            <div class="no-gutters row">
                                <div class="col-sm-6 col-md-4 col-xl-4">
                                    <div class="card no-shadow rm-border bg-transparent widget-chart text-left">
                                        <div class="icon-wrapper rounded-circle">
                                            <div class="icon-wrapper-bg opacity-9 bg-danger"></div>
                                            <i class="pe-7s-users text-white"></i></div>
                                        <div class="widget-chart-content">
                                            <div class="widget-subheading">Number of Assistant</div>
                                            <?php
                                                $students = mysqli_num_rows(mysqli_query ( $link , "SELECT * FROM assistant ;" ));
                                                if ( $students > 0 )
                                                {
                                                    print '<div class="widget-numbers">'.$students.'</div>';
                                                    mysqli_free_result($result);
                                                }
                                                else {
                                                    print '<div class="widget-numbers">0</div>';
                                                }
                                            ?>
                                        </div>
                                    </div>
                                    <div class="divider m-0 d-md-none d-sm-block"></div>
                                </div>
                                <div class="col-sm-12 col-md-4 col-xl-4">
                                    <div class="card no-shadow rm-border bg-transparent widget-chart text-left">
                                        <div class="icon-wrapper rounded-circle">
                                            <div class="icon-wrapper-bg opacity-9 bg-success"></div>
                                            <i class="pe-7s-users text-white"></i></div>
                                        <div class="widget-chart-content">
                                            <div class="widget-subheading">Number of Users</div>
                                            <?php
                                                $tutors = mysqli_num_rows(mysqli_query ( $link , "SELECT * FROM user;" ));
                                                if ( $tutors > 0 )
                                                {
                                                    print '<div class="widget-numbers">'.$tutors.'</div>';
                                                    mysqli_free_result($result);
                                                }
                                                else {
                                                    print '<div class="widget-numbers">0</div>';
                                                }
                                            ?>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-6 col-md-4 col-xl-4">
                                    <div class="card no-shadow rm-border bg-transparent widget-chart text-left">
                                        <div class="icon-wrapper rounded-circle">
                                            <div class="icon-wrapper-bg opacity-10 bg-warning"></div>
                                            <i class="pe-7s-id text-dark opacity-8"></i></div>
                                        <div class="widget-chart-content">
                                            <div class="widget-subheading">Number of Feedback</div>
                                            <?php
                                                if ( $result = mysqli_query ( $link , "SELECT * FROM feedback" ))
                                                {
                                                    print '<div class="widget-numbers">'.mysqli_num_rows($result).'</div>';
                                                    mysqli_free_result($result);
                                                }
                                                else {
                                                    print '<div class="widget-numbers">0</div>';
                                                }
                                            ?>
                                        </div>
                                    </div>
                                    <div class="divider m-0 d-md-none d-sm-block"></div>
                                </div>
                            </div>
                            <div class="text-center d-block p-3 card-footer">
                                <button class="btn-pill btn-shadow btn-wide fsize-1 btn btn-primary btn-lg">
                                    <span class="mr-2 opacity-7">
                                        <i class="icon icon-anim-pulse ion-ios-analytics-outline"></i>
                                    </span>
                                    <span class="mr-1"></span>
                                </button>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-xl-3">
                                <div class="card mb-3 widget-chart widget-chart2 text-left card-btm-border card-shadow-success border-success">
                                    <div class="widget-chat-wrapper-outer">
                                        <div class="widget-chart-content pt-3 pl-3 pb-1">
                                            <div class="widget-chart-flex">
                                                <div class="widget-numbers">
                                                    <div class="widget-chart-flex">
                                                        <?php
                                                            $results = mysqli_num_rows(mysqli_query ( $link , "SELECT DISTINCT image_id from slider;" ));
                                                            if ( $result > 0 )
                                                            {
                                                                print '<div class="fsize-4"><span>'.$results.'</span></div>';
                                                                mysqli_free_result($result);
                                                            }
                                                            else {
                                                                print '<div class="fsize-4"><span>0</span></div>';
                                                            }
                                                        ?>
                                                    </div>
                                                </div>
                                            </div>
                                            <h6 class="widget-subheading mb-0 opacity-5">Number of Home Slider</h6></div>
                                        <div class="no-gutters widget-chart-wrapper mt-3 mb-3 pl-2 he-auto row">
                                            <div class="col-md-9">
                                                <div id="dashboard-sparklines-1"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xl-3">
                                <div class="card mb-3 widget-chart widget-chart2 text-left card-btm-border card-shadow-primary border-primary">
                                    <div class="widget-chat-wrapper-outer">
                                        <div class="widget-chart-content pt-3 pl-3 pb-1">
                                            <div class="widget-chart-flex">
                                                <div class="widget-numbers">
                                                    <div class="widget-chart-flex">
                                                        <?php
                                                            $results = mysqli_num_rows(mysqli_query ( $link , "SELECT DISTINCT report_id from report;" ));
                                                            if ( $result > 0 )
                                                            {
                                                                print '<div class="fsize-4"><span>'.$results.'</span></div>';
                                                                mysqli_free_result($result);
                                                            }
                                                            else {
                                                                print '<div class="fsize-4"><span>0</span></div>';
                                                            }
                                                        ?>
                                                    </div>
                                                </div>
                                            </div>
                                            <h6 class="widget-subheading mb-0 opacity-5">Number of Report</h6></div>
                                        <div class="no-gutters widget-chart-wrapper mt-3 mb-3 pl-2 he-auto row">
                                            <div class="col-md-9">
                                                <div id="dashboard-sparklines-2"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xl-3">
                                <div class="card mb-3 widget-chart widget-chart2 text-left card-btm-border card-shadow-warning border-warning">
                                    <div class="widget-chat-wrapper-outer">
                                        <div class="widget-chart-content pt-3 pl-3 pb-1">
                                            <div class="widget-chart-flex">
                                                <div class="widget-numbers">
                                                    <div class="widget-chart-flex">
                                                        <?php
                                                            $results = mysqli_num_rows(mysqli_query ( $link , "SELECT DISTINCT hourly_work_id from hourly_work;" ));
                                                            if ( $result > 0 )
                                                            {
                                                                print '<div class="fsize-4"><span>'.$results.'</span></div>';
                                                                mysqli_free_result($result);
                                                            }
                                                            else {
                                                                print '<div class="fsize-4"><span>0</span></div>';
                                                            }
                                                        ?>
                                                    </div>
                                                </div>
                                            </div>
                                            <h6 class="widget-subheading mb-0 opacity-5">Number of Hourly Work</h6></div>
                                        <div class="no-gutters widget-chart-wrapper mt-3 mb-3 pl-2 he-auto row">
                                            <div class="col-md-9">
                                                <div id="dashboard-sparklines-3"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6 col-xl-3">
                                <div class="card mb-3 widget-chart widget-chart2 text-left card-btm-border card-shadow-danger border-danger">
                                    <div class="widget-chat-wrapper-outer">
                                        <div class="widget-chart-content pt-3 pl-3 pb-1">
                                            <div class="widget-chart-flex">
                                                <div class="widget-numbers">
                                                    <div class="widget-chart-flex">
                                                        <?php
                                                            $results = mysqli_num_rows(mysqli_query ( $link , "SELECT DISTINCT specialty_id from specialty;" ));
                                                            if ( $result > 0 )
                                                            {
                                                                print '<div class="fsize-4"><span>'.$results.'</span></div>';
                                                                mysqli_free_result($result);
                                                            }
                                                            else {
                                                                print '<div class="fsize-4"><span>0</span></div>';
                                                            }
                                                        ?>
                                                    </div>
                                                </div>
                                            </div>
                                            <h6 class="widget-subheading mb-0 opacity-5">Number of Specialty</h6></div>
                                        <div class="no-gutters widget-chart-wrapper mt-3 mb-3 pl-2 he-auto row">
                                            <div class="col-md-9">
                                                <div id="dashboard-sparklines-4"></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="card mb-3">
                            <div class="card-header-tab card-header">
                                <div class="card-header-title font-size-lg text-capitalize font-weight-normal"><i class="header-icon lnr-laptop-phone mr-3 text-muted opacity-6"> </i>Admin Uses My Assistant</div>
                            </div>
                            <div class="card-body">
                                <table style="width: 100%;" id="example" class="table table-hover table-striped table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Photo</th>
                                            <th>Full Name</th>
                                            <th>User Name</th>
                                            <th>Last Login</th>
                                            <th>Role</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php
                                             $query = "SELECT * FROM admin";
                                            $result = mysqli_query($link , $query);
                                            if ($result = mysqli_query($link, $query)) {
                                                if (mysqli_num_rows($result) > 0) {
                                                    while ($row = mysqli_fetch_array($result)) {
                                                        print '
                                                        <tr>
                                                          <td scope="row"> <img src="'.$row["photo"].'" width=14%; hight=14%;> </td>
                                                            <td>'.$row['full_name'].'</td>
                                                            <td>'.$row['username'].'</td>
                                                            <td>'.$row['last_login'].'</td>
                                                            <td>'.$row['role'].'</td>
                                                        </tr>
                                                        ';
                                                    }
                                                }
                                            }
                                        
                                        ?>
                                        
                                    </tbody>
                                    <tfoot>
                                       <tr>
                                            <th>Photo</th>
                                            <th>Full Nmae</th>
                                            <th>User Name</th>
                                            <th>Last Login</th>
                                            <th>Role</th>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                        
                    </div>
                </div>
                <?php include 'include_footer.php'; ?>
            </div>
        </div>
    </div>

    <?php include 'include_right_drawer.php'; ?>

    <div class="app-drawer-overlay d-none animated fadeIn"></div>
</body>

</html>