<%@ page import="profile.entity.UtenteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" rel="noopener">
</head>
<body>



<header>
    <a href="${pageContext.request.contextPath}/home.jsp" class="logo"><img alt="Hamplanet" src="Icon/OnlyLogo_RED.svg" width="100px" height="50px"></a>
    <span class="space"></span>
    <ul class="slider-menu">
        <%
                UtenteEntity user = (UtenteEntity) session.getAttribute("profile");
                if (user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){
                %>
            <li id="newpost-link" class="drop"><a href="newpost.jsp"><i class="fa-solid fa-pen-nib"></i> </a> </li>
        <%}%>
        <li id="profile-link" class="drop"><a href="./account.jsp"><i class="bi bi-person-fill"></i></a>
        <li id="logout-link" class="drop"><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>

    </ul>
</header>

</body>
</html>