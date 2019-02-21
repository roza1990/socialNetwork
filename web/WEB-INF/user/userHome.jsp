<%@ page import="model.User" %>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: roza
  Date: 2/16/19
  Time: 9:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<%
    User u = (User) session.getAttribute("user");
    // List<User> users = (List<User>) request.getAttribute("users");
    //List<User> usersByRequest = (List<User>) request.getAttribute("usersByRequest");
   // List<User> usersFriends = (List<User>) request.getAttribute("usersFriends");
    if (u != null) {
%>

Welcome <%= u.getName() %> <%= u.getSurname() %>

<img src="/getImage?picName=<%=u.getPicUrl()%>" width="60"/><br>

<a href="/logout">Logout</a>
<% } %>
<br>
<br>

<br>
<br>

<br>
<br>

<div style="width:90%;margin:0 auto">

    <table border="1" style="width:90%">
        <h1>UserList</h1>
        <tr>
            <td>Id</td>
            <td>Name</td>

            <td>Email</td>
            <td>Image</td>
            <td>Action</td>

        </tr>

        <c:forEach var="user" items="${requestScope.get('users')}">

        <tr>
            <td>${user.id}
            </td>
            <td>${user.name}
            </td>


            <td>${user.email}
            </td>
            <td><img src="/getImage?picName=${user.picUrl}" width="60"/>
            </td>

            <td>
                <a href="/user/sendFriendRequest?id=${user.id}">Send Request</a>

            </td>


        </tr>
        </c:forEach>


    </table>

    <%--<%if (usersByRequest != null ) {%>--%>
    <table border="1" style="width:90%">

        <h1>Send request List</h1>
        <tr>
            <td>Name
            </td>
            <td>Image
            </td>
            <td>Action</td>

        </tr>

        <%--<% for (User byRequest : usersByRequest) {%>--%>
<c:forEach var="byRequest" items="${requestScope.get('usersByRequest')}">
        <tr>
            <td>${byRequest.name}
            </td>
            <td><img src="/getImage?picName=${byRequest.picUrl}" width="60"/>
            <td><a href="/user/sendAcceptRequest?id=${byRequest.id}">Accept Request</a>
                || <a href="/user/sendRejectRequest?id=${byRequest.id}">Reject Request</a></td>

        </tr>
</c:forEach>
        <%--<%}%>--%>

    </table>

    <%--<%}%>--%>


    <%--<%if (usersFriends != null   ) {%>--%>
    <table border="1" style="width:90%">

        <h1>Friend List</h1>
        <tr>
            <td>Name
            </td>
            <td>Surname</td>
            <td>Action</td>
            <td>Send Message</td>

        </tr>

        <%--<% for (User friends : usersFriends) {%>--%>
<c:forEach var="friends" items="${requestScope.get('usersFriends')}">
        <tr>
            <td>${friends.name}
            </td>
            <td>${friends.surname}</td>
            <td><img src="/getImage?picName=${friends.picUrl}" width="60"/>
            <td><a href="/user/remove?id=${friends.id}">Remove from Friend list</a></td>
            <td><a href="/user/sendSms?id=${friends.id}">Message</a></td>

        </tr>
        <%--<%}%>--%>
  </c:forEach>


    </table>
    <%--<%}%>--%>
</div>

<br>


</body>
</html>
