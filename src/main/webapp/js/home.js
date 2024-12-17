$(document).ready(function() {
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
});
