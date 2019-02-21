<%--
  Created by IntelliJ IDEA.
  User: roza
  Date: 2/16/19
  Time: 9:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="/register" method="post" enctype="multipart/form-data">
    name: <input type="text" name="name"><br>
    surname: <input type="text" name="surname"><br>
    email: <input type="text" name="email"><br>
    password: <input type="password" name="password"><br>
    image: <input type="file" name="picture"><br>
    <input type="submit" value="Register"><br><br>

    <a href="/login.jsp">Login</a>
</form>
</body>
</html>
