<?php
    session_start();
    if ( $_SESSION['admin'] == 'YES') {
        echo '<script type="text/javascript"> window.open("https://myassistant.jaml46.net/dashboard/dashboard","_self"); </script>';
        exit;
    } else {
        
    }
?>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Content-Language" content="en">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>My Assistant | Dashboard</title>
    <link rel="icon" href="./assets/images/logo.png" type="image/png">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no"/>
    <script type="text/javascript" src="./assets/scripts/script.js"></script>
    <meta name="msapplication-tap-highlight" content="no">
    <link href="./style.css" rel="stylesheet">
</head>

<div class="app-container app-theme-white body-tabs-shadow">
        <div class="app-container">
            <div class="h-100 bg-plum-plate bg-animation">
                <div class="d-flex h-100 justify-content-center align-items-center">
                    <div class="mx-auto app-login-box col-md-8">
                        <div class="modal-dialog w-100 mx-auto">
                            <div class="modal-content">
                                <div class="modal-body">
                                    <div class="h5 modal-title text-center">
                                        <img style="width: 55%;" src="https://myassistant.jaml46.net/dashboard/assets/images/logo.png" >
                                        <h4 class="mt-2">
                                            <div>Welcome Admin To My Assistant,</div>
                                            <span>Please sign in to your account below.</span>
                                        </h4>
                                    </div>
                                    <form class="" method="POST" action = "authentication">
                                        <div class="form-row">
                                            <div class="col-md-12">
                                                <div class="position-relative form-group">
                                                    <input name="username" id="exampleEmail" placeholder="Username ..." type="text" class="form-control">
                                                </div>
                                            </div>
                                            <div class="col-md-12">
                                                <div class="position-relative form-group">
                                                    <input name="password" id="examplePassword" placeholder="Password ..." type="password" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                         <div class="modal-footer clearfix">
                                          <input type="submit" value="Login" class="form-control">
                                        </div>
                                         
                                    </form>
                                    
                                </div>
                               
                            </div>
                        </div>
                        <div class="text-center text-white opacity-8 mt-3">Copyright © My Assistant</div>
                    </div>
                </div>
            </div>
        </div>
</div>

</body>
</html>
