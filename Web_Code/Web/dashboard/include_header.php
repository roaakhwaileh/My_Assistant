<?php include 'configuration/db.php'; ?>
<div class="app-header header-shadow">
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
    <div class="app-header__content">
        <div class="app-header-left">
            <div class="search-wrapper">
                <div class="input-holder">
                    <input type="text" class="search-input" placeholder="Type to search">
                    <button class="search-icon"><span></span></button>
                </div>
                <button class="close"></button>
            </div>
            <ul class="header-megamenu nav">
                <li class="btn-group nav-item">
                   
                    <div tabindex="-1" role="menu" aria-hidden="true" class="rm-pointers dropdown-menu">
                        <div class="dropdown-menu-header">
                            <div class="dropdown-menu-header-inner bg-secondary">
                                <div class="menu-header-image opacity-5" style="background-image: url('assets/images/dropdown-header/abstract2.jpg');"></div>
                                <div class="menu-header-content">
                                    <h5 class="menu-header-title">Website Style</h5>
                                    <h6 class="menu-header-subtitle">Change Website style</h6>
                                </div>
                            </div>
                        </div>
                        <div class="scroll-area-xs">
                            <div class="scrollbar-container">
                                <h6 tabindex="-1" class="dropdown-header">English Version</h6>
                                <button type="button" tabindex="0" class="dropdown-item">Change Cover Photo</button>
                                <button type="button" tabindex="0" class="dropdown-item">Change Main Title</button>
                                <button type="button" tabindex="0" class="dropdown-item">Change Subtitle</button>
                                <div tabindex="-1" class="dropdown-divider"></div>
                                <h6 tabindex="-1" class="dropdown-header">Arabic Version</h6>
                                <button type="button" tabindex="0" class="dropdown-item">Change Cover Photo</button>
                                <button type="button" tabindex="0" class="dropdown-item">Change Main Title</button>
                                <button type="button" tabindex="0" class="dropdown-item">Change Subtitle</button>
                            </div>
                        </div>
                        <ul class="nav flex-column">
                            <li class="nav-item-divider nav-item"></li>
                            <li class="nav-item-btn nav-item">
                                <button class="btn-wide btn-shadow btn btn-danger btn-sm">Cancel</button>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <div class="app-header-right">
            <div class="header-dots">
                <div class="dropdown">
                    
                    <div tabindex="-1" role="menu" aria-hidden="true" class="dropdown-menu-xl rm-pointers dropdown-menu dropdown-menu-right">
                        <div class="dropdown-menu-header">
                            <div class="dropdown-menu-header-inner bg-plum-plate">
                                <div class="menu-header-image" style="background-image: url('assets/images/dropdown-header/abstract4.jpg');"></div>
                                <div class="menu-header-content text-white">
                                    <h5 class="menu-header-title">Quick Access</h5>
                                </div>
                            </div>
                        </div>
                        <div class="grid-menu grid-menu-xl grid-menu-3col">
                            <div class="no-gutters row">
                                <div class="col-sm-6 col-xl-4">
                                    <button class="btn-icon-vertical btn-square btn-transition btn btn-outline-link">
                                        <i class="pe-7s-world icon-gradient bg-night-fade btn-icon-wrapper btn-icon-lg mb-3"></i> Website EN
                                    </button>
                                </div>
                                <div class="col-sm-6 col-xl-4">
                                    <button class="btn-icon-vertical btn-square btn-transition btn btn-outline-link">
                                        <i class="pe-7s-browser icon-gradient bg-night-fade btn-icon-wrapper btn-icon-lg mb-3"> </i> Website AR
                                    </button>
                                </div>
                                <div class="col-sm-6 col-xl-4">
                                    <button class="btn-icon-vertical btn-square btn-transition btn btn-outline-link">
                                        <i class="pe-7s-hourglass icon-gradient bg-night-fade btn-icon-wrapper btn-icon-lg mb-3"> </i> Webmail
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="dropdown">
                    
                    <div tabindex="-1" role="menu" aria-hidden="true" class="dropdown-menu-xl rm-pointers dropdown-menu dropdown-menu-right">
                        <div class="dropdown-menu-header mb-0">
                            <div class="dropdown-menu-header-inner bg-deep-blue">
                                <div class="menu-header-image opacity-1" style="background-image: url('assets/images/dropdown-header/city3.jpg');"></div>
                                <div class="menu-header-content text-dark">
                                    <h5 class="menu-header-title">Notifications</h5>
                                </div>
                            </div>
                        </div>
                        <ul class="tabs-animated-shadow tabs-animated nav nav-justified tabs-shadow-bordered p-3">
                            <li class="nav-item">
                                <a role="tab" class="nav-link" data-toggle="tab" href="#tab-errors-header">
                                    <span>Check System Errors</span>
                                </a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tab-messages-header" role="tabpanel">
                                <div class="scroll-area-sm">
                                    <div class="scrollbar-container">
                                        <div class="p-3">
                                            <div class="notifications-box">
                                                <div class="vertical-time-simple vertical-without-time vertical-timeline vertical-timeline--one-column">

                                                    <div class="vertical-timeline-item dot-success vertical-timeline-element"><div><span class="vertical-timeline-element-icon bounce-in"></span>
                                                        <div class="vertical-timeline-element-content bounce-in"><h4 class="timeline-title">
                                                            Build the production release
                                                        </h4><span class="vertical-timeline-element-date"></span></div></div>
                                                    </div>

                                                    <div class="vertical-timeline-item dot-success vertical-timeline-element"><div><span class="vertical-timeline-element-icon bounce-in"></span>
                                                        <div class="vertical-timeline-element-content bounce-in"><h4 class="timeline-title">
                                                            Build the production release
                                                        </h4><span class="vertical-timeline-element-date"></span></div></div>
                                                    </div>

                                                    <div class="vertical-timeline-item dot-success vertical-timeline-element"><div><span class="vertical-timeline-element-icon bounce-in"></span>
                                                        <div class="vertical-timeline-element-content bounce-in"><h4 class="timeline-title">
                                                            Build the production release
                                                        </h4><span class="vertical-timeline-element-date"></span></div></div>
                                                    </div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab-errors-header" role="tabpanel">
                                <div class="scroll-area-sm">
                                    <div class="scrollbar-container">
                                        <div class="no-results pt-3 pb-0">
                                            <div class="swal2-icon swal2-success swal2-animate-success-icon">
                                                <div class="swal2-success-circular-line-left" style="background-color: rgb(255, 255, 255);"></div>
                                                <span class="swal2-success-line-tip"></span>
                                                <span class="swal2-success-line-long"></span>
                                                <div class="swal2-success-ring"></div>
                                                <div class="swal2-success-fix" style="background-color: rgb(255, 255, 255);"></div>
                                                <div class="swal2-success-circular-line-right" style="background-color: rgb(255, 255, 255);"></div>
                                            </div>
                                            <div class="results-subtitle">All caught up!</div>
                                            <div class="results-title">There are no system errors!</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <ul class="nav flex-column">
                            <li class="nav-item-divider nav-item"></li>
                            <li class="nav-item-btn text-center nav-item">
                                <button class="btn-shadow btn-wide btn-pill btn btn-focus btn-sm">View Latest Changes</button>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="dropdown">
                   
                    <div tabindex="-1" role="menu" aria-hidden="true" class="dropdown-menu-xl rm-pointers dropdown-menu dropdown-menu-right">
                        <div class="dropdown-menu-header">
                            <div class="dropdown-menu-header-inner bg-premium-dark">
                                <div class="menu-header-image" style="background-image: url('assets/images/dropdown-header/abstract4.jpg');"></div>
                                <div class="menu-header-content text-white">
                                    <h5 class="menu-header-title">Users Opened Bayt Study
                                </h5>
                                    <h6 class="menu-header-subtitle">Visitors Overview
                                </h6>
                                </div>
                            </div>
                        </div>
                        <div class="widget-chart">
                            <div class="widget-chart-content">
                                <div class="icon-wrapper rounded-circle">
                                    <div class="icon-wrapper-bg opacity-9 bg-focus">
                                    </div>
                                    <i class="lnr-users text-white">
                                </i>
                                </div>
                                <?php
                                    $today = mysqli_num_rows(mysqli_query ( $link , "SELECT * FROM visitors where action_date = '$current_date';" ));
                                    $total = mysqli_num_rows(mysqli_query ( $link , "SELECT * FROM visitors;" ));
                                ?>
                                <div class="widget-numbers">
                                    <span>Today : <?php print $today ?></span>
                                </div>
                                <div class="widget-subheading pt-2">
                                    Total Number of visitors
                                </div>
                                <div class="widget-description text-danger">
                                    <span class="pr-1"><span>
                                    <?php print $total ?>
                                    </span></span></div>
                            </div>
                            <div class="widget-chart-wrapper">
                                <div id="dashboard-sparkline-carousel-3-pop"></div>
                            </div>
                        </div>
                        <ul class="nav flex-column">
                            <li class="nav-item-divider mt-0 nav-item">
                            </li>
                            <li class="nav-item-btn text-center nav-item">
                                <button class="btn-shine btn-wide btn-pill btn btn-warning btn-sm">
                                    <i class="fa fa-cog fa-spin mr-2">
                                </i> View Details
                                </button>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="header-btn-lg pr-0">
                <div class="widget-content p-0">
                    <div class="widget-content-wrapper">
                        <div class="widget-content-left">
                            <div class="btn-group">
                                <a data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="p-0 btn">
                                    <?php print '<img width="42" class="rounded-circle" src="'.$current_admin_photo.'">'; ?>
                                    <i class="fa fa-angle-down ml-2 opacity-8"></i>
                                </a>
                                <div tabindex="-1" role="menu" aria-hidden="true" class="rm-pointers dropdown-menu-lg dropdown-menu dropdown-menu-right">
                                    <div class="dropdown-menu-header">
                                        <div class="dropdown-menu-header-inner bg-info">
                                            <div class="menu-header-image opacity-2" style="background-image: url('assets/images/dropdown-header/city3.jpg');"></div>
                                            <div class="menu-header-content text-left">
                                                <div class="widget-content p-0">
                                                    <div class="widget-content-wrapper">
                                                        <div class="widget-content-left mr-3">
                                                            <?php print '<img width="42" class="rounded-circle" src="'.$current_admin_photo.'" alt="">'; ?>
                                                        </div>
                                                        <div class="widget-content-left">
                                                            <div class="widget-heading">
                                                                <?php print $current_admin_name ?>
                                                            </div>
                                                            <div class="widget-subheading opacity-8">
                                                                <?php print $current_admin_role ?>
                                                            </div>
                                                        </div>
                                                        <div class="widget-content-right mr-2">
                                                            <a class="btn-pill btn-shadow btn-shine btn btn-focus" href="https://myassistant.jaml46.net/dashboard/logout">Logout</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="scroll-area-xs" style="height: 80px;">
                                        <div class="scrollbar-container ps">
                                            <ul class="nav flex-column">
                                                <li class="nav-item-header nav-item">Activity</li>
                                                <li class="nav-item"><a href="https://myassistant.jaml46.net/dashboard/dashboard" class="nav-link">Home Page</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <ul class="nav flex-column">
                                        <li class="nav-item-divider nav-item">
                                        </li>
                                        <li class="nav-item-btn text-center nav-item">
                                            
                                                
                                            
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="widget-content-left  ml-3 header-user-info">
                            <div class="widget-heading">
                                <?php print $current_admin_name ?>
                            </div>
                            <div class="widget-subheading">
                                <?php print $current_admin_role ?>
                            </div>
                        </div>
                        <div class="widget-content-right header-user-info ml-3">
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="header-btn-lg">
              
            </div>
        </div>
    </div>
</div>