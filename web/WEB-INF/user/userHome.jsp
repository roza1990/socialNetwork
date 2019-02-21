<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
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
    List<User> users = (List<User>) request.getAttribute("users");
    List<User> usersByRequest = (List<User>) request.getAttribute("usersByRequest");
    List<User> usersFriends = (List<User>) request.getAttribute("usersFriends");
    if (u != null) {
%>
Welcome <%= u.getName() %> <%= u.getSurname() %>

<a href="/logout">Logout</a>
<% } %>
<br>
<br>
<%= u.getName() %><br>
<img src="/getImage?picName=<%=u.getPicUrl()%>" width="60"/><br>
<br>
<br>

<br>
<br>
<div style="width:90%;margin:0 auto">

    <table border="1" style="">
        <h1>UserList</h1>
        <tr>
            <td>Id</td>
            <td>Name</td>

            <td>Email</td>
            <td>Image</td>
            <td>Action</td>

        </tr>

        <% for (User user : users) {%>
        <tr>
            <td><%= user.getId() %>
            </td>
            <td><%= user.getName() %>
            </td>


            <td><%= user.getEmail()%>
            </td>
            <td><img src="/getImage?picName=<%=user.getPicUrl()%>" width="60"/>
            </td>

            <td>
                <a href="/user/sendFriendRequest?id=<%= user.getId() %>">Send Request</a>

            </td>


        </tr>
        <%}%>


    </table>
    <%if (usersByRequest != null ) {%>
    <table border="1" style="">

        <h1>Send request List</h1>
        <tr>
            <td>Name
            </td>
            <td>Action</td>

        </tr>

        <% for (User byRequest : usersByRequest) {%>
        <tr>
            <td><%= byRequest.getName() %>
            </td>
            <td><a href="/user/sendAcceptRequest?id=<%= byRequest.getId() %>">Accept Request</a>
                || <a href="/user/sendRejectRequest?id=<%= byRequest.getId() %>">Reject Request</a></td>

        </tr>

        <%}%>

    </table>

    <%}%>


    <%if (usersFriends != null   ) {%>
    <table border="1" style="">

        <h1>Friend List</h1>
        <tr>
            <td>Name
            </td>
            <td>Surname</td>
            <td>Action</td>

        </tr>

        <% for (User friends : usersFriends) {%>
        <tr>
            <td><%= friends.getName() %>
            </td>
            <td><%= friends.getSurname() %></td>
            <td><a href="/user/remove?id=<%= friends.getId() %>">Remove from Friend list</a></td>

        </tr>
        <%}%>


    </table>
    <%}%>
</div>

<br>


</body>
</html>
