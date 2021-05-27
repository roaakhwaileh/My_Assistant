<?php
    session_start();
    $current_admin = $_SESSION['admin'];
    $current_admin_id = $_SESSION['admin_id'];
    $current_admin_name = $_SESSION['full_name'];
    $current_admin_permissions = $_SESSION['permissions'];
    $current_admin_photo = $_SESSION['photo'];
    $current_admin_role = $_SESSION['role'];
    include '../configuration/db.php';
?>
<?php
 
    $_SESSION['admin'] = false;
    $_SESSION['admin_id'] = false;
    $_SESSION['name'] = false;
    $_SESSION['permissions'] = false;
    $_SESSION['photo'] = false;
    $_SESSION['role'] = false;
    
    unset ($_SESSION['admin']);
    unset ($_SESSION['admin_id']);
    unset ($_SESSION['full_name']);
    unset ($_SESSION['permissions']);
    unset ($_SESSION['photo']);
    unset ($_SESSION['role']);
    
    session_destroy ();
    echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/","_self"); </script>';
?> 