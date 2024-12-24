// Funzione per caricare i commenti dal server
function loadComments() {
    const id = $("#postId").val();

    $.ajax({
        url: 'CommentsPostS', // GET alla servlet
        type: 'POST',
        data:{postID: id},
        dataType: 'json', // Aspettiamo un JSON come risposta
        success: function(response) {
            let container = $('#comments-container');
            container.empty();
            let comments = response.comments;
            for (const x in comments) {
                let commento = comments[x];

                container.append(`<div class="card comment-card shadow-sm">
                        <div class="card-body" id="commento-${commento.id}">
                            <h6 class="card-subtitle mb-2 username">${commento.username}</h6>
                            <p class="card-text">${commento.commento}</p>
                        </div>
                    </div>`);

            }
        },
        error: function(xhr, status, error) {
            console.error('Errore caricamento commenti:', error);
        }
    });
}

const form = document.getElementById("form_commento");

form.addEventListener('submit', function(event) {
    event.preventDefault();

    let commento = $("#commento").val();
    let post = $("#postId").val();


    if (commento) {
        $.ajax({
            url: 'AddCommentoS',
            method: 'POST',
            data: {commento: commento, postID: post},
            dataType: 'json',
            success: function(response) {
                if (response.outcome === true) {
                    loadComments(); // Aggiorna la lista dei commenti
                    // Resetta i campi del form
                    document.getElementById('post').value = '';
                    document.getElementById('commento').value = '';
                } else {
                    alert("errore caricamento messaggio");
                }

            },error: function(xhr, status, error) {
                console.error('Errore invio commento:', error)
            }
        })


    }else {
        alert("compila il form");
    }
});


// Carica i commenti al caricamento della pagina
$(document).ready(loadComments());

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