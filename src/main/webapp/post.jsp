<%@ page import="profile.entity.UtenteEntity" %>
<%@ page import="profile.entity.UtenteEntity.Role" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="css/post.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">



</head>
<body id="postpage">

<%@include file="header.jsp"%>


<div class="post-body">
    <div class="post-box">
        <div class = "divisione_content">
            <c:if test="${empty post}">
                <c:redirect url="/Home" />
            </c:if>


            <div class="div-divcontent post-div" >
                <input type="hidden" id="postId" name="postId" value="${post.id}">
                <div class="title-div" >
                    <span class="title" id="name" name="Titolo Articolo">${post.nomePost}</span>
                    <% if (user.getRuoloEnum().equals(Role.supervisore)){ %>
                        <button id="deletePostButton" type="button" class="btn btn-danger btn-delete" onclick="deletePost()">
                            <span aria-hidden="true">&times;</span>
                            <span class="visually-hidden">Elimina</span>
                        </button>
                    <% } %>
                </div>
                <div class="img-post-wrp">
                    <img class="postimg" src="files/images/post/${post.id}.jpeg" alt="">
                </div>
                <div class="card__footer">
                    <div class="user">
                        <img class="cw-img" src="files/images/profile/${post.idContent_Writer}.jpeg" alt="user__image" class="user__image">
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
            <%if (user.getRuoloEnum().equals(Role.utente)){%>
            <div class = "div-divcontent">
                <button id="likeButton" onclick="toggleLike()" class="like">
                    <c:if test="${post.isLiked}">

                        <img class="likeIcon" id="likeIcon" src="Icon/like.png"  alt="like">
                    </c:if>
                    <c:if test="${!post.isLiked}">
                        <img class="likeIcon" id="likeIcon" src="Icon/nolike.png" alt="like">
                    </c:if>
                </button>
            </div>
            <%}%>


            <div class="container comments-section">
                <!-- Titolo -->
                <h2 class="text-center mb-4">Commenti</h2>

                <% if (!user.getRuoloEnum().equals(Role.supervisore)){ %>
                <form class="form" id="form_commento" method="post">
                    <!-- Form per Inserire un Nuovo Commento (SOPRA la Lista) -->
                    <div class="card shadow mb-4">
                        <div class="card-body">
                            <!-- Campo Contenuto del Commento -->
                            <div class="mb-3">

                                <label for="post"></label>
                                <input id="post" type="hidden" value="${post.id}">
                                <label for="commento_area" class="form-label"> Commenta</label>
                                <textarea class="form-control" id="commento_area" rows="4" placeholder="Scrivi il tuo commento qui..."></textarea>
                            </div>
                            <!-- Pulsante di Invio -->
                            <div class="text-center">
                                <button id="commentoSubmit" type="submit" class="btn btn-primary">Invia Commento</button>
                            </div>
                        </div>
                    </div>

                </form>

                <%}%>

                <!-- Sezione Lista dei Commenti -->
                <div id="comments-container">
                    <!-- I commenti verranno aggiunti dinamicamente qui -->
                </div>
            </div>

        </div>
</div>
</div>

<!-- Modal per la conferma -->
<div class="modal fade" id="confirmationModal" tabindex="-1" role="dialog" aria-labelledby="confirmationModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmationModalLabel">Conferma Eliminazione</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Sei sicuro di voler eliminare questo elemento? L'operazione è irreversibile.
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" id="cancelDelete" data-dismiss="modal">Annulla</button>
                <button type="button" class="btn btn-danger" id="confirmDelete">Conferma</button>
            </div>
        </div>
    </div>
</div>


<%@include file="footer.html"%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
    <script src="js/post.js"></script>
</body>
</html>