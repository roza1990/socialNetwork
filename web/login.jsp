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
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    Email: <input type="text" name="email" /><br>
    Password: <input type="password" name="password" /><br>
    <input type="submit" value="Login"><br><br>
    <a href="/register.jsp">Register</a>

</form>
</body>
</html>
