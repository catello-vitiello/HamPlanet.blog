<%@ page import="profile.entity.UtenteEntity" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="FileCSS/post.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">



</head>
<body>


<div class="post-box">
    <div class = "divisione_content">
        <c:if test="${empty post}">
            <c:redirect url="/Home" />
        </c:if>


        <div class="div-divcontent post-div" >
            <input type="hidden" id="postId" name="postId" value="${post.id}">
            <h1 class="title" id="name" name="Titolo Articolo">${post.nomePost}</h1>
            <div>
                <img class="postimg" src="files/images/post/${post.id}.jpeg" alt="">
            </div>
            <div class="card__footer">
                <div class="user">
                    <img src="https://i.pravatar.cc/40?img=1" alt="user__image" class="user__image">
                    <div class="user__info">
                        <h5 id="cw-${post.idContent_Writer}">Unknown</h5>
                    </div>
                </div>
            </div>
            <p class="text_box" id="descrizione" name="Descrizione">
                ${post.testo}
            </p><br><br>



        </div>



    </div>
    <div class = divisionde_content>
        <div class = "div-divcontent">
            <a onclick="" class="like">

                <img src="Icon/like.png" alt="like">
            </a>
        </div>


        <div class="container comments-section">
            <!-- Titolo -->
            <h2 class="text-center mb-4">Commenti</h2>

            <form class="form" id="form_commento" method="post">
                <!-- Form per Inserire un Nuovo Commento (SOPRA la Lista) -->
                <div class="card shadow mb-4">
                    <div class="card-body">
                        <!-- Campo Contenuto del Commento -->
                        <div class="mb-3">

                            <label for="post"></label>
                            <input id="post" type="hidden" value="${post.id}">
                            <label for="commento" class="form-label"> Commenta</label>
                            <textarea class="form-control" id="commento" rows="4" placeholder="Scrivi il tuo commento qui..."></textarea>
                        </div>
                        <!-- Pulsante di Invio -->
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Invia Commento</button>
                        </div>
                    </div>
                </div>

            </form>

            <!-- Sezione Lista dei Commenti -->
            <div id="comments-container">
                <!-- I commenti verranno aggiunti dinamicamente qui -->
            </div>
        </div>

    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="FileJavaScript/post.js"></script>


</body>
</html>