<!DOCTYPE html>
<html>
    <head>
        <title>Gestisci il tuo ID HamPlanet</title>
        <link rel="stylesheet" href="./FileCSS/Login.css">
        <script type="text/javascript" src="FileJavaScript/LogReg.js"></script>
        <link rel="shortcut icon" href="Icon/1_Logo_Red.png">
    </head>

<!-- ##############   BODY   ##############-->
    <body>
        <div class="form-box">
            <form action="LoginS" method="post">

                <!--LOGO-->
                <!--<img id="logo" src="D:\Document\catello\HamPlanet\Logo\Logo_v3.1_Black.png" alt="Ham Planet" height="130x">-->
                <img id="logo" src="D:\Document\catello\HamPlanet\Logo\logo_v3.1_BLACK.svg" alt="HamPlanet">
                
                <!--TITOLO-->
                <h2 id="titolo">Accedi</h2>
                <!--INFO-->
                <p id="sott">Gestisci il tuo account Ham Planet Blog.</p>
                

                <!--EMAIL-->
                <div class="inputbox">
                    <input id="email" onpointerover="changeIcon(0)"
                                      onpointerleave="changeIcon(1)"
                                      onclick="changeIcon(2)"
                                      onblur="changeIcon(3)"
                                      oninput="changeIcon(4)"
                                      type="text" name="email" required>
                    <div class="testo">
                        <label class="uno">E</label>
                        <label class="due">m</label>
                        <label class="tre">a</label>
                        <label class="quattro">i</label>
                        <label class="cinque">l</label>
                    </div>
                    <img id="img_email" src="email_default_BLACK.png" alt="show" height="40px">
                </div>

                <!--PASSWORD-->
                <div class="inputbox">
                    <input id="pwd" onpointerover="changeColor(0)"
                                    onpointerleave="changeColor(1)"
                                    onclick="changeColor(2)"
                                    onblur="changeColor(3)"
                                    type="password" name="pass" required>
                    <div class="testo">
                        <label class="uno">P</label>
                        <label class="due">a</label>
                        <label class="tre">s</label>
                        <label class="quattro">s</label>
                        <label class="cinque">w</label>
                        <label class="sei">o</label>
                        <label class="sette">r</label>
                        <label class="otto">d</label>
                    </div>
                    <img id="img_pass" src="eyes_show_BLACK.png" alt="show" onclick="showPwd(1)" height="10px">
                </div>


                <!--BOTTONE DI ACCESSO-->
                <button id="buttonLogin" type="submit">Accedi</button>
                

                <!--LINK SE NON HAI UN ACCOUONT-->
                <div class="forReg">
                    <p>Non hai un account? <a href="Registrazione.jsp">Crea subito il tuo.</a></p>
                </div>

            </form>
        </div>
    </body>
</html>