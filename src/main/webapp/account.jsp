<%@ page import="profile.entity.UtenteEntity" %><%--
  Created by IntelliJ IDEA.
  User: enzo2
  Date: 06/01/2025
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">
    <style>
        body {
            background: linear-gradient(to right, rgb(158, 237, 209), rgb(65, 166, 135));
        }
    </style>
</head>
<body >

<%@include file="header.jsp"%>

<div id="user-account" class="<%=user.getRuolo()%>">

    <div class="form">
        <%if (user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){%>



        <div class="img-wrap">
            <div class="image-preview" id="imagePreview">
                <img class="propic" id="previewImg" src="files/images/profile/<%=user.getId()%>.jpeg" alt="Anteprima immagine" />
                <label class="upload-btn icon-btn" for="file-input"><i class="fa-solid fa-pencil"></i></label>
                <!-- Input nascosto -->
                <input id="file-input" style="display: none" type="file" accept="image/jpeg">
            </div>

        </div>

        <%}%>
        <form>
            <div class = "divisione_content">

                <label class="center" for="userName">Nome Utente:</label>
                <input class="container-account" type="text" id="userName" name="userName" value="<%=user.getUserName()%>"><br><br>
                <label class="center" for="password">Password:</label>

                <input class="password-container" type="password" id="password" class="password-input" value="" placeholder="Insert new password">


                <%if (user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){%>
                    <label class="center" for="descrizione">Descrizione:</label>
                    <textarea  class="description-container" id="descrizione" name="Descrizione"><%=user.getCompetenze()%></textarea><br><br>
                <%}%>
            </div>
        </form>
        <div class="buttons-row">
            <input class="save-changes" type="button" id="updatebutton" value="Conferma modifiche" ><br><br>
            <input class="delate-profile" type="button" id="elimina" value="Elimina profilo" onclick="deleteProfile()">
        </div>
    </div>
</div>
<!-- Il Modal -->
<div id="successModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Update effettuato con successo!</h2>
        <p>La tua operazione è stata completata senza errori.</p>
    </div>
</div>


<%@include file="footer.html"%>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/account.js"></script>
</body>
</html>