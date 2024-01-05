<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1><%= "TEST CONTENT WRITER" %></h1>

    <form action="CWControl" method="post">
        <h3>SELEZIONA UNA OPZIONE</h3>
        <select name="service">
            <option value="ALL">Stampa tutti i Content_Writer</option>
            <option value="ID">Ricerca per ID</option>
            <option value="ADD">Inserisci nuovo Content Writer</option>
            <option value="DELETE">Elimina un Content Writer tramite ID</option>
            <option value="MERGE">Aggiorna un Content Writer</option>
        </select>
        <br><br>
        <label>Inserisci id: </label>
        <input type="text" name="id">
        <br><br>
        <h3>NUOVO UTENTE</h3>
        <br>
        <label>Inserisci userName: </label>
        <input type="text" name="userName">
        <br>
        <label>Inserisci email: </label>
        <input type="email" name="email">
        <br>
        <label>Inserisci password: </label>
        <input type="password" name="pass">
        <br>
        <label>Inserisci competenze: </label>
        <input type="text" name="comp">
        <br><br>
        <input type="submit" value="TEST">&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" value="RESET">
    </form>

</body>
</html>