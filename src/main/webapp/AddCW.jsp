<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Test CW Registration</title>
</head>
<body>
    <h1><%= "TEST REGISTRATION" %></h1>
    
    <div>Per registrazione utente basta non compilare il campo relativo alle competenze<br></div>

    <form action="SignUp" method="post">
        <br><br>
        <h3>NUOVO CONTENT_WRITER</h3>
        <br>
        <label>Inserisci userName: </label>
        <input type="text" name="userName">
        <br>
        <label>Inserisci email: </label>
        <input type="email" name="email">
        <br>
        <label>Inserisci password: </label>
        <input type="password" name="passwd">
        <br>
        <label>Inserisci competenze: </label>
        <input type="text" name="comp">
        <br><br>
        <input type="submit" value="TEST">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" value="RESET">
    </form>

	<br><br>
	<a href="index.jsp">HOME</a>

</body>
</html>