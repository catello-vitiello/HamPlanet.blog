
const user = {}
const roles = {'supervisore': 'supervisore', 'utente': 'utente', 'contentwriter': 'content_writer'};
const postId = $("#postId").val();

// Funzione per caricare i commenti dal server
function loadComments() {


    $.ajax({
        url: 'CommentsPostS',
        type: 'POST',
        data:{postID: postId},
        dataType: 'json',
        success: function(response) {
            let container = $('#comments-container');
            container.empty();
            let comments = response.comments;
            for (const x in comments) {
                let commento = comments[x];

                console.log(user);
                if (user.role === roles.supervisore){
                    container.append(`<div class="card comment-card shadow-sm">
                        <div class="card-body" id="commento-${commento.id}">
                            <h6 class="card-subtitle mb-2 username">${commento.username}</h6>
                            <p class="card-text">${commento.commento}</p>
                            <button type="button" class="btn btn-danger btn-delete" onclick="deleteComment(${commento.id})">
                                <span aria-hidden="true">&times;</span>
                                <span class="visually-hidden">Elimina</span>
                            </button>
                        </div>
                    </div>`);
                }else {
                    container.append(`<div class="card comment-card shadow-sm">
                        <div class="card-body" id="commento-${commento.id}">
                            <h6 class="card-subtitle mb-2 username">${commento.username}</h6>
                            <p class="card-text">${commento.commento}</p>
                        </div>
                    </div>`);
                }
            }
        },
        error: function(xhr, status, error) {
            console.error('Errore caricamento commenti:', error);
        }
    });
}



function deleteComment(id){

    $.ajax({
        url: 'DeleteComment',
        method: 'POST',
        data: {'postID': postId, 'commentoID': id},
        dataType: 'json',
        success: function (response) {
            if (response.outcome === true) {
                loadComments();
            }
        }

    })
}

function attachCommentForm(){
    let form = document.getElementById("form_commento");
    form.addEventListener('submit', function (event) {
        event.preventDefault();

        let commento = $("#commento").val();



        if (commento) {
            $.ajax({
                url: 'AddCommentoS',
                method: 'POST',
                data: {'commento': commento, 'postID': postId},
                dataType: 'json',
                success: function (response) {
                    if (response.outcome === true) {
                        loadComments(); // Aggiorna la lista dei commenti
                        // Resetta i campi del form
                        document.getElementById('post').value = '';
                        document.getElementById('commento').value = '';

                    } else {
                        alert("errore caricamento messaggio");
                    }

                }, error: function (xhr, status, error) {
                    console.error('Errore invio commento:', error)
                }
            })


        } else {
            alert("compila il form");
        }
    });
}

function deletePost(){
    $.ajax({
        url: 'DeletePost',
        method: 'POST',
        data: {'postId': postId},
        dataType: 'json',
        success: function (response) {
            if (response.result === true) {
                window.location.href = 'http://localhost:8585/HamPlanet-blog/home.jsp';
            }
        }

    })
}

$(document).ready(function(){

    $.ajax({
        url: 'Profile',
        type: 'POST',
        dataType: 'json',
        success: function(data){

            Object.assign(user, {"username": data.user,
                                        "id": data.id,
                                        "role": data.role});
            if (data.role === roles.utente || data.role === roles.contentwriter) {
                attachCommentForm();

            }
        }

    })

    loadComments();




})


// Seleziona tutti gli elementi con la classe user__info
$(".user__info").each(function() {
    // Trova l'elemento h5 all'interno di questo div
    const h5 = $(this).find("h5");

    // Prendi l'id dell'elemento h5
    const id = h5.attr("id");

    // Estrai la parte dopo "cw-"
    const idContentWriter = id.replace("cw-", "");

    // Effettua l'AJAX call usando il valore estratto
    $.ajax({
        url: `./ContentWriter`,
        data:{id: idContentWriter},// URL dell'API
        method: "POST", // Metodo HTTP
        success: function(data) {
            // Gestisci la risposta, ad esempio aggiornando il testo dell'h5
            h5.text(data.name || "Unknown");
        },
        error: function(err) {
            console.error(`Errore con ID ${idContentWriter}:`, err);
        }
    });


});


