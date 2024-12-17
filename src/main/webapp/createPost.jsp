<%@ page import="profile.entity.UtenteEntity" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Post</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .form-container {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f9f9f9;
        }
        .form-container h2 {
            text-align: center;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input[type="text"],
        .form-group textarea {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .form-group input[type="file"] {
            display: block;
            width: 100%;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%
    session = request.getSession(false);
    UtenteEntity user = (UtenteEntity) session.getAttribute("profile");
    if (!user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){
        response.sendRedirect("./home.jsp");
    }

%>

<div class="form-container">
    <h2>Create a New Post</h2>
    <form action="${pageContext.request.contextPath}/AddPost" method="post" enctype="multipart/form-data">
        <!-- Campo per il titolo -->
        <div class="form-group">
            <label for="title">Post Title</label>
            <input type="text" id="title" name="title" placeholder="Enter the title of the post" required>
        </div>

        <!-- Campo per il contenuto -->
        <div class="form-group">
            <label for="content">Post Content</label>
            <textarea id="content" name="text" rows="5" placeholder="Write the content of your post" required></textarea>
        </div>

        <!-- Campo per il caricamento di un'immagine -->
        <div class="form-group">
            <label for="image">Upload Image</label>
            <input type="file" id="image" name="cover" accept="image/*" required>
        </div>

        <!-- Pulsante di invio -->
        <div class="form-group">
            <button type="submit" value="Submit">Create Post</button>
        </div>
    </form>
</div>

</body>
</html>
