<?php

    header('Access-Control-Allow-Origin: *');
                          /*                    (username)        (password)                (db)      */
    $link = mysqli_connect("localhost", "jamlnet_myassistant", "JqADuEL}Q#xN", "jamlnet_myassistant");
    mysqli_set_charset($link, "utf8");
    
    date_default_timezone_set("Asia/Amman");
      
    $current_time = date("h:m:s", time()) ;
    $current_date = date("Y-m-d", time());
    $current_datetime = date("d/m/Y h:i:s A", time()) ;

    if ($link === false) {
        echo "0";
        return;
    }

?>


