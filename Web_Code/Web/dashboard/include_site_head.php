<?php session_start(); ?>

<?php
    if ( $_SESSION['admin'] == 'YES' || $_SESSION['admin'] == "YES" || $_SESSION['admin'] === 'YES' || $_SESSION['admin'] === "YES" ) {
        $cuttent_admin = $_SESSION['admin'];
        $cuttent_admin_id = $_SESSION['admin_id'];
        $cuttent_admin_name = $_SESSION['full_name'];
        $cuttent_admin_permissions = $_SESSION['permissions'];
        $cuttent_admin_photo = $_SESSION['photo'];
        $cuttent_admin_role = $_SESSION['role'];
    }
    else {
        //echo '<script type="text/javascript"> window.open("https://gasnas.com/baytstudy/control/","_self"); </script>';
        
        $cuttent_admin = $_SESSION['admin'];
        $cuttent_admin_id = $_SESSION['admin_id'];
        $cuttent_admin_name = $_SESSION['full_name'];
        $cuttent_admin_permissions = $_SESSION['permissions'];
        $cuttent_admin_photo = $_SESSION['photo'];
        $cuttent_admin_role = $_SESSION['role'];
        
        print "<br><br> Admin : " . $cuttent_admin;
        print "<br><br> Admin id : " . $cuttent_admin_id;
        print "<br><br> Admin name : " . $cuttent_admin_name;
        print "<br><br> Admin permissions : " . $cuttent_admin_permissions;
        print "<br><br> Admin photo : " . $cuttent_admin_photo;
        print "<br><br> Admin role : " . $cuttent_admin_role;
        
        exit;
    }
?>

<!--<head>-->
<!--    <meta charset="utf-8">-->
<!--    <meta http-equiv="X-UA-Compatible" content="IE=edge">-->
<!--    <meta http-equiv="Content-Language" content="en">-->
<!--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>-->
<!--    <title>Bayt Study Platform | Dashboard</title>-->
<!--    <link rel="icon" href="./assets/images/fav.png" type="image/png">    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>-->
<!--    <script type="text/javascript" src="./assets/scripts/script.js"></script>-->
<!--    <meta name="msapplication-tap-highlight" content="no">-->
<!--    <link href="./style.css" rel="stylesheet">-->
<!--</head>-->