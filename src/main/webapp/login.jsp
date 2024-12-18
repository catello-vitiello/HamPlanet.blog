<!DOCTYPE html>
<html>
<head>
    <title>Animated Login Form</title>

    <link href="FileCSS/Login.css" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<img class="wave" src="https://raw.githubusercontent.com/sefyudem/Responsive-Login-Form/master/img/wave.png">
<div class="container">
    <div class="img">
        <img src="https://raw.githubusercontent.com/sefyudem/Responsive-Login-Form/master/img/bg.svg">
    </div>
    <div class="login-content">
        <form >
            <img src="https://raw.githubusercontent.com/sefyudem/Responsive-Login-Form/master/img/avatar.svg">
            <h2 class="title">HamPlanet.blog Login</h2>
            <div class="input-div one">
                <div class="i">
                    <i class="fas fa-user"></i>
                </div>
                <div class="div">
                    <h5>Email</h5>
                    <label for="email"></label><input id="email" name="email" type="text" class="input">
                </div>
            </div>
            <div class="input-div pass">
                <div class="i">
                    <i class="fas fa-lock"></i>
                </div>
                <div class="div">
                    <h5>Password</h5>
                    <input id="password" name="password" type="password" class="input">
                </div>
            </div>
            <div style="display: inline">
            <p> non hai un account?</p>
            <a href="#">Sign Up</a>
            </div>
            <input type="submit" class="btn submit" onclick="login()">
        </form>
    </div>
</div>

</body>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>

<script src="js/login.js"></script>

</html>