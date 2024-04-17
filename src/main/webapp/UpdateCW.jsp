<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UPDATE CONTENT WRITER</title>
</head>
<body>

	<fieldset>
		<legend>Update CW</legend>
		
		<form action="UpdateCWS" method="post">
		
		<c:if test="${ not empty id }">
		<label>ID: </label>
		<input type="text" name="id" value="${ id }" readonly>
		</c:if>
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
		
	</fieldset>

</body>
</html>