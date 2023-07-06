<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form method="post" action="bob">
    <p>nom:</p>
<input type="text" name="nom" id="t1">
    <p>prenom:</p>
    <input type="text" name="prenom" id="">

    <input type="submit" value="OK">
</form>
</body>
</html>