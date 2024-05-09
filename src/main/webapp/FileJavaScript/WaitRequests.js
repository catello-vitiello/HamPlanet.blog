
/*##################################################################################*/
/*############################### INGRANDIRE BOTTONI ###############################*/
/*##################################################################################*/
function changeSize(element){ 

    var id = element.id.substring(0, 6);
    var codeId = element.id.substring(6);

    var boxAccept = document.getElementById('accept' + codeId);
    var boxReject = document.getElementById('reject' + codeId);

    if(id == 'accept'){
        boxAccept.style.zIndex = '1';
        setTimeout(() => {
            boxAccept.style.right = '1vh';
            boxAccept.style.width = '17vh';
        }, 100);
        
    }

    else if (id == 'reject'){
        boxReject.style.zIndex = '1';
        setTimeout(() => {
            boxReject.style.right = '1vh';
            boxReject.style.width = '17vh';
        }, 100);
    }

}


/*##################################################################################*/
/*############################ VECCHIA SIZE DEI BOTTONI ############################*/
/*##################################################################################*/
function backSize(element){

    var id = element.id.substring(0, 6);
    var codeId = element.id.substring(6);

    var boxAccept = document.getElementById('accept' + codeId);
    var boxReject = document.getElementById('reject' + codeId)


    setTimeout(() =>{
        boxAccept.style.width = '8vh';
        boxAccept.style.right = '10vh';

        boxReject.style.zIndex = '0';
        boxReject.style.width = '8vh';
    }, 100)
    
    setTimeout(() => {
        boxAccept.style.zIndex = '0';
    }, 150);
    

    

}




/*##################################################################################*/
/*############################## OPEN DESCRIPTION BOX ##############################*/
/*##################################################################################*/
function openDescription(element){

    var id = element.id.substring(8);
    var requestBox = document.getElementById('requestBox' + id);

    requestBox.style.height = '28%'

}


/*##################################################################################*/
/*############################# CLOSE DESCRIPTION BOX ##############################*/
/*##################################################################################*/
function closeDescription(element){

    var id = element.id.substring(8);
    var requestBox = document.getElementById('requestBox' + id);

    requestBox.style.height = '10%'
}