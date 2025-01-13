


$('input[type=radio][name=selection]').change(function() {

    const cw_section =  `<input id="img" type="file" accept="image/jpeg" onchange="previewImage(event)" />
            <div class="image-preview" id="imagePreview">
                <img id="previewImg" src="" alt="Anteprima immagine" />
            </div>
            <label for="img" class="upload-label">
                Carica Immagine 
            </label>
            <div id="img-err"></div>`;

    const tokenstr = `<div id="div_token" class="input-container ic2">
                <input id="token" class="input" type="text" placeholder=" " />
                <div class="cut3"></div>
                <label for="token" class="placeholder">Token</label>
                </div>`;

    const cw_bio = `<div class="input-container ic2">
        <textarea id="descrizione" class="input" placeholder=" " ></textarea>
        <div class="cut4"></div>
        <label for="descrizione" class="placeholder">Descrizione</label>
    </div>`;

    const imgdiv = $("#img-section");
    const biodiv = $("#bio-section");
    const tokendiv = $("#token-section");

    if (this.value === 'user') {
        imgdiv.empty();
        biodiv.empty();
        tokendiv.empty();
    }
    else if (this.value === 'contentwriter') {
        tokendiv.empty();
        imgdiv.append(cw_section);
        biodiv.append(cw_bio);
    }else if (this.value === 'supervisor') {
        biodiv.empty();
        imgdiv.empty();
        tokendiv.append(tokenstr);
    }
});

function mostraBootstrapModalConRedirect(messaggio, durata, url) {


    const modalMessage = $('#modalMessage');
    const countdownTimer = document.getElementById('countdownTimer');

    // Imposta il messaggio e mostra il modal
    modalMessage.textContent = messaggio;


    let tempoRimasto = durata / 1000; // Converti durata in secondi
    countdownTimer.textContent = tempoRimasto;

    const modalInstance = new bootstrap.Modal(document.getElementById("redirectModal"), {});
    modalInstance.show(); // Mostra il modal

    // Countdown e reindirizzamento
    const intervallo = setInterval(() => {
        tempoRimasto--;
        countdownTimer.textContent = tempoRimasto;

        if (tempoRimasto <= 0) {
            clearInterval(intervallo);
            modalInstance.hide();
            window.location.href = url; // Reindirizza
        }
    }, 1000);
}


function previewImage(event) {
    const input = event.target;
    const previewContainer = document.getElementById('imagePreview');
    const previewImage = document.getElementById('previewImg');
    const placeholder = previewContainer.querySelector('.placeholder');

    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result;
            previewImage.style.display = 'block';
            placeholder.style.display = 'none';
        };

        reader.readAsDataURL(input.files[0]);
    } else {
        previewImage.src = '';
        previewImage.style.display = 'none';
        placeholder.style.display = 'block';
    }
}

function validateEmail(email) {

    let regex = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    if(email.match(regex)) {
        $("#invalid-email").empty();
        return true;
    }
    else {
        $("#invalid-email").html("Invalid email format!");
        return false;
    }
}

function validatePassword(password) {


    let regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$%^&*!]).{8,32}$/;
    if(password.match(regex)) {
        $("#invalid-psw").empty();
        return true;
    }
    else {
        $("#invalid-psw").html("Invalid password format!");
        return false;
    }
}



function signup(){

    let formData = new FormData();

    let email = $("#email").val()
    let password = $("#password").val()

    formData.append('username', $("#username").val());
    formData.append('email', email);
    formData.append('password', password);


    let radio = $('input[type=radio][name=selection]:checked').val();

    if (radio === 'contentwriter'){
        formData.append('comp', $('#descrizione').val());
        let fileInput = $('#img')[0];
        if (fileInput.files.length > 0) {
            if (fileInput.files[0].type !== 'image/jpeg'){
                $('#img-err').html('Il file deve essere un’immagine JPEG.');
                return;
            }
            formData.append('cover', fileInput.files[0]); // Nome del campo e file
        } else {
            $('#img-err').html('Seleziona un file prima di inviarlo.');
            return;
        }
    }else if (radio === 'supervisor') {
        formData.append('token', $('#token').val());
    }

    console.log(formData.get('email'));
    if (validateEmail(email) && validatePassword(password)){


        $.ajax({
            url: 'Signup',
            method: 'POST',
            dataType: 'json',
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                if (response.result) {
                    mostraBootstrapModalConRedirect(
                        "Grazie per esserti registrato!",
                        5000, // 5 secondi
                        "./login.jsp" // URL della home
                    );
                }else if (response.errore){
                    $("#invalid-email").html(response.errore);
                }
            },
            error: function (xhr, status, error) {

            }

        })
    }

}

