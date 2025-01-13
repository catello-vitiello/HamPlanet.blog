const inputs = document.querySelectorAll(".input");


function addcl(){
    let parent = this.parentNode.parentNode;
    parent.classList.add("focus");
}

function remcl(){
    let parent = this.parentNode.parentNode;
    if(this.value == ""){
        parent.classList.remove("focus");
    }
}


inputs.forEach(input => {
    input.addEventListener("focus", addcl);
    input.addEventListener("blur", remcl);
});

function showLoading(){
    $("#loading").css("display", "flex");
}

function hideLoading(){
    $("#loading").css("display", "none");
}

function login() {
event.preventDefault();

    let email = document.getElementById("email").value;
    //let password = btoa(document.getElementById("password").children[1].value); //Base64
    let password = document.getElementById("password").value;


    showLoading();
    $.ajax({
        url: 'Login',
        method: 'POST',
        data: { email: email, password: password },
        dataType: 'json',
        success: function(data) {

            hideLoading();

            const home =  "./home.jsp";
            let outcome = data.login;
            if(outcome){
                window.location.href = home;
            }
            else {
                const error= "Email or password entered are incorrect!";
                $("#error-message").html(error);

            }
        },
        error: function() {
                hideLoading();
            $("#error-message").html("Problemi nell'esecuzione della richiesta: nella risposta nel tempo limite");

        }
    });


}
