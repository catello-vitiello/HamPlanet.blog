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
    <title>Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/account.css">
    <style>
        body {
            background: linear-gradient(to right, rgba(140,209,229,255), rgba(129,144,220,255));
        }
    </style>
</head>
<body>

<%@include file="header.html"%>

<% UtenteEntity user = (UtenteEntity) session.getAttribute("profile"); %>

<div class="form">
    <img  class="propic" src = "files/images/post/1.jpeg">
    <form>
        <div class = "divisione_content">

            <label class="center" for="name">Nome Utente:</label>
            <input class="container-account" type="text" id="name" name="Nome" value="<%=user.getUserName()%>"><br><br>
            <label class="center" for="name">Password:</label>

            <input class="password-container" type="password" id="password" class="password-input" value="" placeholder="Insert new password">


            <%if (user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){%>
                <label class="center" for="name">Descrizione:</label>
                <textarea  class="description-container" id="descrizione" name="Descrizione"><%=user.getCompetenze()%></textarea><br><br>
            <%}%>
        </div>
        <div class="buttons-row">
            <input class="save-changes" type="submit" id="submit" value="Conferma modifiche"><br><br>
            <input class="delate-profile" type="submit" id="elimina" value="Elimina profilo">
        </div>
    </form>
</div>



<%@include file="footer.html"%>



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/account.js"></script>
</body>
</html>