<!DOCTYPE html>
<html>
    <head>
        <title>Registrazione</title> <!--Crea il tuo account HamPlanet-->    
        <link rel="stylesheet" href="Registrazione.css">
        <link rel="shortcut icon" href="#">           <!--AGGIUNGERE PATH ICONA SOPRA-->
        <script type="text/javascript" src="LogReg.js"></script>
    </head>
    
    <body>
    
        <div class="form-box">
            <form action="ClienteControl" method="post">

                <!--TITOLO-->
                <h2 id="titolo">Crea il tuo ID<br>Ham Planet Blog</h2> <!--Crea il tuo ID Ham Planet-->
                
                <!--INFO-->
                <p id="info">L'ID Ham Planet ti permette di accedere a tutti i servizi Ham Planet Blog.</p>
                
                <!--LINK TO LOGIN-->
                <div class="forLog">
                    <p>Hai già un ID Ham Planet Blog? <a href="Login.jsp">Accedi qui ></a></p>
                </div>
                

                <!--USERNAME-->
                <div class="inputbox">
                    <input id="username" onpointerover="changeIconUser(0)"
                                      onpointerleave="changeIconUser(1)"
                                      onclick="changeIconUser(2)"
                                      onblur="changeIconUser(3)"
                                      type="text" name="email" autocomplete="off" required>
                    <div class="testo">
                        <label class="uno">U</label>
                        <label class="due">s</label>
                        <label class="tre">e</label>
                        <label class="quattro">r</label>
                        <label class="cinque">n</label>
                        <label class="sei">a</label>
                        <label class="sette">m</label>
                        <label class="otto">e</label>
                    </div>
                    <img id="img_username" src="userReg_BLACK.png" alt="show" height="40px">
                </div>
                


                
                

                <!--EMAIL-->
                <div class="inputbox">
                    <input id="email" onpointerover="changeIcon(0)"
                                      onpointerleave="changeIcon(1)"
                                      onclick="changeIcon(2)"
                                      onblur="changeIcon(3)"
                                      oninput="changeIcon(4)"
                                      type="text" name="email" autocomplete="off" required>
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
                    <img id="img_pass" src="eyes_show_BLACK.png" alt="show" onclick="showPwd(1)">
                </div>

            

                
                <!--CONFERMA PASSWORD-->
                <div class="inputbox">
                    <input id="pwdRepeat"   onpointerover="changeColorRepeat(0)"
                                            onpointerleave="changeColorRepeat(1)"
                                            onclick="changeColorRepeat(2)"
                                            onblur="changeColorRepeat(3)"
                                            type="password" required>
                    <div class="testo">
                        <label class="uno">C</label>
                        <label class="due">o</label>
                        <label class="tre">n</label>
                        <label class="quattro">f</label>
                        <label class="cinque">e</label>
                        <label class="sei">r</label>
                        <label class="sette">m</label>
                        <label class="otto">a</label>
                        &nbsp;
                        <label class="nove">P</label>
                        <label class="dieci">a</label>
                        <label class="undici">s</label>
                        <label class="dodici">s</label>
                        <label class="tredici">w</label>
                        <label class="quattordici">o</label>
                        <label class="quindici">r</label>
                        <label class="sedici">d</label>
                    </div>
                    <img id="img_confPass" src="eyes_show_BLACK.png" alt="show" onclick="showPwd(2)">
                </div>

                

                <div class="toggler">
                    <input id="toggler-1" name="toggler-1" type="checkbox" value="1" onchange="showCampoText()">
                    <label for="toggler-1">
                        <svg class="toggler-on" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                            <polyline class="path check" points="100.2,40.2 51.5,88.8 29.8,67.5"></polyline>
                        </svg>
                        <svg class="toggler-off" version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 130.2 130.2">
                            <line class="path line" x1="34.4" y1="34.4" x2="95.8" y2="95.8"></line>
                            <line class="path line" x1="95.8" y1="34.4" x2="34.4" y2="95.8"></line>
                        </svg>
                    </label>

                    <p>Per la richiesta di Content Writer spunta la casella</p>
                </div>


                <div id="textBox">
                    <textarea id="descriptionCW" placeholder="Breve presentazione (max 250 caratteri)" maxlength="200" rows="3" cols="20"></textarea>
                </div>



                <!--BOTTONE REGISTRAZIONE-->
                <button id="buttonReg" type="submit">Registrati</button>
            
            </form>
        
    </body>
    
</html>
