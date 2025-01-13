<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Create a Post</title>

    <link href="css/newpost.css" rel="stylesheet">
    <!-- Include Bootstrap CSS -->
    <link href="https://fonts.googleapis.com/css2?family=Quicksand:wght@300..700&display=swap" rel="stylesheet">

</head>
<body id="newpost-page">
<%@include file="header.jsp"%>

<%


    if (!user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){
        response.sendRedirect("/home.jsp");
    }
%>

<div class="home-container">
    <div class="form-container">
        <h1>Create a New Post</h1>
        <form  method="POST" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="title" class="form-label">Title</label>
                <input type="text" id="title" name="title" required>
            </div>

            <div class="mb-3">
                <label for="photo" class="form-label">Photo</label>
                <input type="file" id="photo" name="photo" accept="image/jpeg" required>
            </div>

            <div class="mb-3">
                <label for="content" class="form-label">Content</label>
                <textarea id="content" name="content" rows="5" required></textarea>
            </div>

            <div class="text-center">
                <button id="upload-post" type="submit" onclick="newpost()">Submit</button>
            </div>
        </form>
    </div>
</div>
<%@include file="footer.html"%>


<!-- Include Bootstrap JavaScript -->
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="js/newpost.js"></script>
</body>
</html>
