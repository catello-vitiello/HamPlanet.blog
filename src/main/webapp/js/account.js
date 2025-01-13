
const passwordInput = document.getElementById('password');

passwordInput.addEventListener('mouseenter', () => {
    passwordInput.type = 'text';
});

passwordInput.addEventListener('mouseleave', () => {
    passwordInput.type = 'password';
});


if ($("#file-input".length > 0) && $("#previewImg").length > 0) {
    // Selezioni degli elementi
    const fileInput = document.getElementById('file-input');
    const profileImg = document.getElementById('previewImg');
    var imgchange = false;


    // Apre il selettore file cliccando sull'immagine
    profileImg.addEventListener('click', () => {
        fileInput.click();
    });

    // Quando l'utente seleziona un file
    fileInput.addEventListener('change', event => {
        const file = event.target.files[0];

        if (file) {
            // Crea un URL temporaneo per l'anteprima
            const imgPreview = URL.createObjectURL(file);
            profileImg.src = imgPreview;
            imgchange = true;


        }
    });
}

function deleteProfile(){
    event.preventDefault()
    $.ajax({
        url: 'DeleteProfile',
        method: 'POST',
        dataType: 'json',
        success: function (response) {
            if (response.success) {
                window.location.href = 'http://localhost:8585/HamPlanet-blog/login.jsp';
            }else {
                alert("errore nell'eliminazione del profilo");
            }
        }
    })
}

$(document).ready(function(){

    var ruolo = $('#user-account').attr('class');
    var profile = {};
    var userName = $('#userName').val();
    var password = $('#password').val();
    profile.userName = userName;
    profile.password = password;
    if (ruolo === 'content_writer'){

        profile.descrizione = $('#descrizione').val();
    }


    // Get references agli elementi
    let modal = document.getElementById("successModal");
    let span = document.getElementsByClassName("close")[0];



    // Quando l'utente clicca sulla 'x', chiudi il modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // Quando l'utente clicca fuori dal modal, chiudilo
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }


    $("#updatebutton").on('click', function () {
        var formData = new FormData();
        for(let key in profile){
            let tmp = $('#'+ key).val();
            if (profile[key] !== tmp){
                formData .append(key, tmp);
            }
        }
        if (imgchange){
            let file = $('#file-input')[0];
            formData.append('cover', file.files[0]);
        }

        $.ajax({
            url: 'UpdateProfile',
            method: 'POST',
            dataType: 'json',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                if (response.result) {
                    modal.style.display = "block"
                }else {
                    alert("errore nell'update del profilo");
                }
            },
            error: function (xhr, status, error) {
                alert("errore nell'update del profilo");
            }
        })



    });

})
