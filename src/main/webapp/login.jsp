<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGIN</title>
</head>
<body>

	<fieldset>
		<legend>Login form</legend>
		<form action="LoginCWS" method="post">
			<label>EMAIL: </label><input type="email" name="email"><br>
			<label>PASSWORD: </label><input type="password" name="pass"><br>
		
			<input type="submit" value="LOGIN">
			<br>
			<input type="reset" value="RESET">
		</form>
	</fieldset>
	
	<hr>
		
		<c:if test="${ not empty cw }">
				<h4>DATI CONTENT WRITER</h4>
				<p>ID: <c:out value="${ cw.id }" /> </p>
				<p>UserName: <c:out value="${ cw.userName }" /></p>
				<p>E-mail: <c:out value="${ cw.email }" /></p>
				<p>Competenze: <c:out value="${ cw.competenze }" /></p>
				
				<hr>
				
				<form action="ToUpdateCW" method="post">
					<input type="text" name="id_" value="<c:out value="${ cw.id }" />" hidden>
					<input type="submit" value="MODIFICA I DATI PERSONALI">
				</form>
				
				<hr>
				
				<form action="LogoutS" method="post">
					<input type="submit" value="LOGOUT">
				</form>
				
			</c:if>
		
	<hr>
	<a href="index.jsp">HOME</a>
	
</body>
</html>