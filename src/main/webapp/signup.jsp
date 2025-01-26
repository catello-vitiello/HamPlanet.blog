<%--
  Created by IntelliJ IDEA.
  User: giovanni red
  Date: 09/01/2025
  Time: 10:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HamPlanet.blog-signup</title>
    <link rel="stylesheet" href="css/signup.css">

</head>
<body>



<div class="form">

    
    <div class="title">
        <a href="login.jsp"  rel="noopener" class="invisible-button"><i class="fa-regular fa-circle-left"></i></a>
        Benvenuto su HamPlanet.blog</div>
    <div class="subtitle">Crea il tuo account!</div>

    <div id="choice-user">
        <div class="form-check">
            <input class="form-check-input" type="radio" name="selection" id="user" value="user" checked>
            <label class="form-check-label" for="user">
                User
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="selection" id="contentwriter" value="contentwriter">
            <label class="form-check-label" for="contentwriter">
                Content Writer
            </label>
        </div>
        <div class="form-check">
            <input class="form-check-input" type="radio" name="selection" id="supervisor" value="supervisor">
            <label class="form-check-label" for="supervisor">
                Supervisor
            </label>
        </div>
    </div>

    <div id="img-section" class="upload-container">


    </div>


    <div class="input-container ic1">
        <input id="username" class="input" type="text" placeholder=" " />
        <div class="cut1"></div>
        <label for="username" class="placeholder">Username</label>
    </div>
    <div class="input-container ic2">
        <input id="email" class="input" type="text" placeholder=" " />
        <div class="cut2"></div>
        <label for="email" class="placeholder">Email</label>
        <div id="invalid-email"></div>
    </div>
    <div class="input-container ic2">
        <input id="password" class="input" type="text" placeholder=" " />
        <div class="cut3"></div>
        <label for="password" class="placeholder">password</label>
        <div id="invalid-psw"></div>
    </div>

    <div id="token-section"></div>
    <div id="bio-section"></div>


    <button id="signupButton" type="submit" class="submit" onclick="signup()">submit</button>
</div>

<!-- Bootstrap Modal -->
<div class="modal fade" id="redirectModal" tabindex="-1" aria-labelledby="redirectModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="redirectModalLabel">Registrazione effettuata con successo</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p id="modalMessage">Grazie per esserti registrato!</p>
                <p id="modalCountdown">Verrai reindirizzato tra <span id="countdownTimer"></span> secondi...</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Chiudi</button>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.html"%>


<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="js/signup.js"></script>

</body>
</html>