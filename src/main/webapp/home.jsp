<%--
  Created by IntelliJ IDEA.
  User: Kirjia
  Date: 02/10/2024
  Time: 19:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>HamPlanet.blog Home</title>
    <!-- Includi Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/home.css" rel="stylesheet">
</head>
<body id="homepage">

<%@include file="header.jsp"%>
<div class="home-container">
    <div class="container mt-5">
        <h1 class="text-center">HamPlanet.blog</h1>
        <div class="row row-cols-1 row-cols-md-3 g-2 g-md-4">
            <c:if test="${empty posts}">
                <c:redirect url="/Home" />
            </c:if>
            <!-- Ciclo JSTL per iterare sui post passati dalla servlet -->
            <c:forEach var="post" items="${posts}">
                <div class="col">
                    <div class="card">
                        <div class="card__header">
                            <a class="link-to-post" href="http://localhost:8585/HamPlanet-blog/Post?postId=${post.id}">
                                <img src="files/images/post/${post.id}.jpeg" alt="error">

                            </a>
                        </div>
                        <div class="card__body">

                            <h4>${post.nomePost}</h4>
                            <p>${post.testo}</p>
                        </div>
                        <div class="card__footer">
                            <div class="user">
                                <img class="cw-img user__image" src="files/images/profile/${post.idContent_Writer}.jpeg" alt="user__image" >
                                <div class="user__info">
                                    <h5 id="cw-${post.idContent_Writer}">Unknown</h5>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="footer.html"%>

<!-- Includi Bootstrap JS e dipendenze (se necessario) -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="js/home.js"></script>
</body>
</html>
