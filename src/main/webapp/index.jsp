<%@ page import="java.util.Collection" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="post.servlet.RetrievePost" %>
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HamPlanet.Blog</title>

    <link rel="stylesheet" href="./FileCSS/index.css">
</head>
<body>

    <!-- HEADER -->
    <jsp:include page="./header.jsp"></jsp:include>

    <%

        Collection<?> posts = (Collection<?>) request.getAttribute("collection");
        if(posts == null){
            response.sendRedirect("retrivePosts");
            return;
        }

%>

    <div class="container">
        <div class="grid">

            <%
                if(posts != null && posts.size()>0){
                    Iterator<?> iterator = posts.iterator();
                    while(iterator.hasNext()){
                        JSONObject bean = (JSONObject) iterator.next();
            %>

            <div class="post">
                <img src="https://via.placeholder.com/300x200" alt="Post Image">
                <div class="post-content">
                    <h3 class="post-title"> <%= bean.get("name") %> </h3>
                    <p class="post-description"> <%= bean.get("body") %> </p>

                    <form action="Post" method="post">
                        <input type="text" hidden="hidden" name="postId" value="<%= bean.get("id") %>" >
                        <button TYPE="submit" >OPEN IN POST VIEW</button>
                    </form>
                </div>
            </div>

            <%
                    }
                }
            %>

        </div>
    </div>
</body>
</html>
