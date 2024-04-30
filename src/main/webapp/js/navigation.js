
function navigate(id, type) {

    switch (type) {
        case "HOME":
            home();
            break;
        case "PROFILE":
            navToProfile(id, false);
            break;
        case "POST":
            navToPost(id, false);
            break;
        case "USER":
            navToUser(id, false);
            break;
    }
}

function prev() {
    $.post("PrevPage", {}, function(data){
        let id = data.id[0];
        let type = data.type[0];
        if(type == null) return;
        navigate(id, type);
    });
}

function next() {
    $.post("NextPage", {}, function(data){
        let id = data.id[0];
        let type = data.type[0];
        if(type == null) return;
        navigate(id, type);
    });
}

function home() {
    $("#center").load("homepage.jsp");
}

function navToUser(id, new_page) {
    $("#center").load("loading.jsp");
    $.post("GetUser", {id: String(id), new_page: String(new_page)}, function(data){
        document.getElementById("style-to-change").setAttribute("href", "css/user.css");
        $("#center").load("user.jsp");
    });
}

function navToProfile(id, new_page) {
    $("#center").load("loading.jsp");
    $.post("GetPlaylist", {id: String(id), new_page: String(new_page)}, function(data){
        document.getElementById("style-to-change").setAttribute("href", "css/playlist.css");
        $("#center").load("playlist.jsp");
    });
}




function navToPost(id, new_page) {
    $("#center").load("loading.jsp");
    $.post("PostS", {id: String(id), new_page: String(new_page)}, function(data){
        document.getElementById("style-to-change").setAttribute("href", "css/album.css");
        $("#center").load("album.jsp");
    });
}


function standby(img) {
    img.src = "files/0.jpg";
}