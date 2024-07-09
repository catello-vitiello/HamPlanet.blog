/*###############################################################################################*/
/*###                         FUNZIONE PER CAMBIARE ICONA DELL'USERNAME                       ###*/
/*###############################################################################################*/
var numUser = 0;
var inWrite = false;
function changeIconUser(numero){

    var userCampo = document.getElementById("username");
    var icona = document.getElementById("img_username");

    numUser = numero;

    //QUANDO CI PASSO SOPRA
    if (numUser == 0){
        icona.src = "./Icon/userReg_WHITE.png";
    }

    //QUANDO PERDE MI TOLGO DA SOPRA
    else if (numUser == 1 && !inWrite){
        icona.src = "./Icon/userReg_BLACK.png";
    }

    //QUANDO CLICCO
    else if (numUser == 2){
        icona.src = "./Icon/userReg_WHITE.png";
        inWrite = true;
    }

    //QUANDO PERDE IL FOCUS
    else if (numUser == 3){
        if (userCampo.value == ""){
            icona.src = "./Icon/userReg_BLACK.png";
            inWrite = false;
        }
    }
    
}



/********************************************************************************************************************************************************/



/*###############################################################################################*/
/*###                         FUNZIONE PER CAMBIARE ICONA DELL'EMAIL                          ###*/
/*###############################################################################################*/
var num = 0;
var email_bool = false;
function changeIcon(numero){
    var email = document.getElementById('email').value;
    var letter = document.getElementById('img_email');

    num = numero;

    /**##################################### ZERO ################################## */
    if (num == 0){
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
            letter.src = "./Icon/email_accept_GREEN.png";
        }else if (email === '' && email_bool == false){
            letter.src = "./Icon/email_default_WHITE.png";
        }else if (email !== '' && !/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
            letter.src = "./Icon/email_reject_RED.png";
        }

        
    }

    /**##################################### UNO ################################## */
    else if (num == 1){
        if (email === '' && email_bool == false){
            letter.src = "./Icon/email_default_BLACK.png";
        }
        else if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)) {
            letter.src = "./Icon/email_accept_GREEN.png";
        }else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)|| email ==='') {
            letter.src = "./Icon/email_reject_RED.png";
        }
    }
    

    /**##################################### DUE ################################## */
    else if (num == 2){
        letter.src = "./Icon/email_default_WHITE.png";
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
            letter.src = "./Icon/email_accept_GREEN.png";
        }else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email) && email !== ''){
            letter.src = "./Icon/email_reject_RED.png";
        }else if (email === ''){
            letter.src = "./Icon/email_reject_RED.png"
        }else{
            letter.src = "./Icon/email_default_WHITE.png";
        }
        email_bool = true;
    }


    /**##################################### TRE ################################## */  
    else if (num == 3){
        if(email === ''){
            letter.src = "./Icon/email_default_BLACK.png";
        }
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
            letter.src = "./Icon/email_accept_GREEN.png";
        }else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email) && email !== ''){
            letter.src = "./Icon/email_reject_RED.png";
        }
        email_bool = false;
    }

    /**##################################### QUATTRO ################################## */  
    else if (num == 4){
        if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
            letter.src = "./Icon/email_accept_GREEN.png";
        }else if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email) || email === ''){
            letter.src = "./Icon/email_reject_RED.png";
        }
    }

}



/********************************************************************************************************************************************************/
 


/*###############################################################################################*/
/*###                      FUNZIONE PER CAMBIARE L'ICONA DELLA PASSWORD                       ###*/
/*###############################################################################################*/
var num = 0;
var pass_bool = false;
function changeColor(numero){

    
    var box = document.getElementById('pwd');
    var input = document.getElementById('pwd').value;
    var eyes = document.getElementById('img_pass');

    

    num = numero;

    /**##################################### ZERO ################################## */
    if (num == 0){
        if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
            eyes.src = "./Icon/eyes_show_WHITE.png";
        }
        else if (eyes.src.toString("./Icon/eyes_hidden_BLACK.png") && box.type == "text"){
            eyes.src = "./Icon/eyes_hidden_WHITE.png";
        }
    }


    /**##################################### UNO ################################## */
    else if (num == 1){
        if (input === '' && pass_bool == false){
            if ( eyes.src.toString("eyes_show_WHITE.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_BLACK.png";
            }
            else if (eyes.src.toString("eyes_hidden_WHITE.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_BLACK.png";
            }
        }
        else{
            if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_WHITE.png";
            }
            else if (eyes.src.toString("eyes_hidden_BLACK.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_WHITE.png";
            }
        }
    }
    


    /**##################################### DUE ################################## */
    else if (num == 2){
        if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
            eyes.src = "./Icon/eyes_show_WHITE.png";
        }
        else if (eyes.src.toString("eyes_hidden_BLACK.png") && box.type == "text"){
            eyes.src = "./Icon/eyes_hidden_WHITE.png";
        }
        pass_bool = true;
    }



    /**##################################### TRE ################################## */  
    else if (num == 3){
        if(input === ''){
            if ( eyes.src.toString("eyes_show_WHITE.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_BLACK.png";
            }
            else if (eyes.src.toString("eyes_hidden_WHITE.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_BLACK.png";
            }
        }
        else{
            if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_WHITE.png";
            }
            else if (eyes.src.toString("eyes_hidden_BLACK.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_WHITE.png";
            }
        }
        pass_bool = false;
    }
    
}



/********************************************************************************************************************************************************/




/*###############################################################################################*/
/*###                 FUNZIONE PER CAMBIARE L'ICONA DELLA CONFERMA PASSWORD                   ###*/
/*###############################################################################################*/
var numConf = 0;
var passConf_bool = false;
function changeColorRepeat(numero){

    
    var box = document.getElementById('pwdRepeat');
    var input = document.getElementById('pwdRepeat').value;
    var eyes = document.getElementById('img_confPass');

    

    numConf = numero;

    /**##################################### ZERO ################################## */
    if (numConf == 0){

        if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
            eyes.src = "./Icon/eyes_show_WHITE.png";
        }
        else if (eyes.src.toString("eyes_hidden_BLACK.png") && box.type == "text"){
            eyes.src = "./Icon/eyes_hidden_WHITE.png";
        }
    }


    /**##################################### UNO ################################## */
    else if (numConf == 1){
        if (input === '' && !passConf_bool){
            if ( eyes.src.toString("eyes_show_WHITE.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_BLACK.png";
            }
            else if (eyes.src.toString("eyes_hidden_WHITE.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_BLACK.png";
            }
        }
        else{
            if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_WHITE.png";
            }
            else if (eyes.src.toString("eyes_hidden_BLACK.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_WHITE.png";
            }
        }
    }
    


    /**##################################### DUE ################################## */
    else if (numConf == 2){
        if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
            eyes.src = "./Icon/eyes_show_WHITE.png";
        }
        else if (eyes.src.toString("eyes_hidden_BLACK.png") && box.type == "text"){
            eyes.src = "./Icon/eyes_hidden_WHITE.png";
        }
        passConf_bool = true;
    }



    /**##################################### TRE ################################## */  
    else if (numConf == 3){
        if(input === ''){
            if ( eyes.src.toString("eyes_show_WHITE.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_BLACK.png";
            }
            else if (eyes.src.toString("eyes_hidden_WHITE.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_BLACK.png";
            }
        }
        else{
            if ( eyes.src.toString("eyes_show_BLACK.png") && box.type == "password"){
                eyes.src = "./Icon/eyes_show_WHITE.png";
            }
            else if (eyes.src.toString("eyes_hidden_BLACK.png") && box.type == "text"){
                eyes.src = "./Icon/eyes_hidden_WHITE.png";
            }
        }
        passConf_bool = false;
    }
    
}


/*################################################################################################*/
/*###                      FUNZIONE PER MOSTRARE/NASCONDERE LA PASSWORD                        ###*/
/*################################################################################################*/
function showPwd(tipo){

    if (tipo == 1){
        var input = document.getElementById('pwd');
        var eyes = document.getElementById('img_pass');
    }
    else if(tipo == 2){
        var input = document.getElementById('pwdRepeat');
        var eyes = document.getElementById('img_confPass');
    }


    if(input.value === ''){
        if(input.type == "password"){
            if (eyes.src.toString("eyes_show_BLACK.png")){
                eyes.src = "./Icon/eyes_hidden_BLACK.png";
            }
            else{
                eyes.src = "./Icon/eyes_hidden_WHITE.png";
            }
            input.type = "text";
        }
        else{
            if (eyes.src.toString("eyes_hidden_BLACK.png")){
                eyes.src = "./Icon/eyes_show_BLACK.png";
            }
            else{
                eyes.src = "./Icon/eyes_show_WHITE.png";
            }
            input.type = "password";
        }
    }else{
        if(input.type == "password"){
            if (eyes.src.toString("eyes_show_BLACK.png")){
                eyes.src = "./Icon/eyes_hidden_WHITE.png";
            }
            else{
                eyes.src = "./Icon/eyes_hidden_BLACK.png";
            }
            input.type = "text";
        }
        else{
            if (eyes.src.toString("eyes_hidden_BLACK.png")){
                eyes.src = "./Icon/eyes_show_WHITE.png";
            }
            else{
                eyes.src = "./Icon/eyes_show_BLACK.png";
            }
            input.type = "password";
        }
    }
    
}




/*################################################################################################*/
/*###                      FUNZIONE PER MOSTRARE/NASCONDERE TEXTAREA                           ###*/
/*################################################################################################*/
function showCampoText (){
    
    var spunta = document.getElementById("toggler-1");
    var textBox = document.getElementById("textBox");
    var descri = document.getElementById("descriptionCW");


    if (spunta.checked){
        textBox.style.height = '60px';
        descri.style.display = 'block';
    }
    else {
        textBox.style.height = '0px';
        setTimeout(function(){
            descri.style.display = 'none';
        },350);

    }


}

