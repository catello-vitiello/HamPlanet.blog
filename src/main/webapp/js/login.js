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

function login() {
event.preventDefault();

    let email = document.getElementById("email").value;
    //let password = btoa(document.getElementById("password").children[1].value); //Base64
    let password = document.getElementById("password").value;



    $.ajax({
        url: 'Login',
        method: 'POST',
        data: { email: email, password: password },
        dataType: 'json',
        success: function(data) {
            console.log("risposta" + data.login);
            const home =  "./home.jsp";
            let outcome = data.login;
            if(outcome){
                window.location.href = home;
            }
            else {
                let error = document.getElementById("error-message");
                error.innerHTML = "Email or password entered are incorrect!";
            }
        },
        error: function() {

                alert("Problemi nell'esecuzione della richiesta: nella risposta nel tempo limite");

        }
    });


}
