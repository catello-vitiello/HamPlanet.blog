<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Test CW ID</title>
</head>
<body>
    <h1><%= "TEST CONTENT WRITER GET ID" %></h1>

    <form action=GetCWS method="post">
        <br>
        <label>Inserisci id: </label>
        <input type="text" name="id">
        <input type="submit" value="TEST">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" value="RESET">
    </form>

	<br><br>
	<a href="index.jsp">HOME</a>

</body>
</html>