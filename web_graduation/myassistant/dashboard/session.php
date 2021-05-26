<?php
    session_start();
    if ($_SESSION['admin'] = 'YES') {
        $current_admin = $_SESSION['admin'];
        $current_admin_id = $_SESSION['admin_id'];
        $current_admin_name = $_SESSION['full_name'];
        $current_admin_permissions = $_SESSION['permissions'];
        $current_admin_photo = $_SESSION['photo'];
        $current_admin_role = $_SESSION['role'];
    } else {
        echo '<script type="text/javascript"> window.open("https://www.majd-lab.com/dashboard/index","_self"); </script>';
        exit;
    }
?>