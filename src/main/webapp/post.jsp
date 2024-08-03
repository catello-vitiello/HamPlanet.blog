<%@ page import="java.util.Collection" %>
<%@ page import="post.entity.PostEntity" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="commento.entity.CommentoEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Visualizza Post</title>

    <link rel="stylesheet" href="./FileCSS/post.css">

</head>
<body>

<%

    JSONObject post = (JSONObject) request.getAttribute("post");
    if(post == null){
        response.sendRedirect("Post");
        return;
    }

%>

<div class="post-container">
        <img src="https://via.placeholder.com/600x400" alt="Post Image" class="post-image">
        <c:if test="${ not empty post }">
        <div class="post-content">
            <h2 class="post-title"> ${ post.get("title") } </h2>
            <p class="post-body"> ${ post.get("text") } </p>
        </div>
        </c:if>
        <div class="like-section">
            <button class="like-button" onclick="toggleLike()">
                <span id="like-icon"> <img src="Icon/like.png"> </span>
                <span id="like-count">0</span> Mi piace
            </button>
        </div>
        <div class="comments-section">
            <h3>Commenti</h3>

            <div id="comments-list">
                <%
                    if(post != null){
                        JSONArray commenti = (JSONArray) post.get("commenti");
                        int i=0;
                        while(i < commenti.length()){
                            String bean = commenti.get(i) + "\n";
                            i++;
                %>
                    <div>
                    <%= bean %>
                    </div>
                <%
                        }
                    }
                %>
            </div>
            <form class="comment-form">
                <textarea id="comment-text" rows="3" placeholder="Aggiungi un commento..." required></textarea>
                <button type="submit">Pubblica</button>
            </form>
        </div>
    </div>
</body>
</html>
