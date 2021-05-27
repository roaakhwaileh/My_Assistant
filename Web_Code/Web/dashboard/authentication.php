<?php

    session_start();
    include '../configuration/db.php';
    $user = $_POST['username'];
    $pass = $_POST['password'];
    
    $query = "SELECT * FROM admin where username = '$user' and password = '$pass' and status = 'active';";
    $result = mysqli_query($link , $query);
    if ($link === false)
    {
        echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/","_self"); </script>';
        return;
    }
    if ($result = mysqli_query($link, $query)) {
        if (mysqli_num_rows($result) > 0) {
            while ($row = mysqli_fetch_array($result)) {
                $user_s = $row['username'];
                $pass_s = $row['password'];
                $admin_id = $row['admin_id'];
                $name = $row['full_name'];
                $permissions = $row['permissions'];
                $photo = $row['photo'];
                $role = $row['role'];
            }
            mysqli_free_result($result);
        } else { echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/","_self"); </script>'; return; }
        } else { echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/","_self"); </script>'; return; }
    mysqli_close($link);
    
    if ($user == $user_s && $pass === $pass_s ){
       include '../configuration/db.php'; 
        $query = "UPDATE admin SET last_login = '$current_datetime' WHERE admin_id = '$admin_id';";
        $result = mysqli_query($link , $query);
        
        session_start();
        $_SESSION['admin'] = 'YES';
        $_SESSION['admin_id'] = $admin_id;
        $_SESSION['full_name'] = $name;
        $_SESSION['permissions'] = $permissions;
        $_SESSION['photo'] = $photo;
        $_SESSION['role'] = $role;
        
        //mysqli_close($link);
        // echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/session","_self"); </script>';
        echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/dashboard","_self"); </script>';

    } else {
        echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/","_self"); </script>';
    }
?>