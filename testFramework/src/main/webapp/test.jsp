<%@ page import="andrana.hah" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Employe</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<%
    hah test=(hah)request.getAttribute("key");%>
    <p>nom de l'employe:</p>
   <% out.println(test.getNom());%>
    <p>prenom de l'employe:</p>
    <%out.println(test.getPrenom());
%>
</body>
</html>