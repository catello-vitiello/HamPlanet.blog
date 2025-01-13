

function newpost(){

    var title = $('#title').val();
    var text = $('#content').val();
    var fileinput = $('#photo')[0];
    let formData = new FormData();
    formData.append('title', title);
    formData.append('text', text);
    formData.append('cover', fileinput.files[0]);

    event.preventDefault()
    $.ajax({
        url:"AddPost",
        method:"POST",
        dataType:"json",
        data: formData,
        processData: false,
        contentType: false,
        success: function (response) {
            if (response.result) {
                window.location.href= "./home.jsp";
            }else
                alert("errore nell'update del post");
        },
        error: function (response) {
            alert("errore nell'update del post");
        }

    })


}