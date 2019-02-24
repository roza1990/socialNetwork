<%--
  Created by IntelliJ IDEA.
  User: roza
  Date: 2/22/19
  Time: 2:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Message</title>
</head>
<body>
<c:forEach var="message" items="${requestScope.get('usersMessage')}">
    <h4>
        ${message.userName}    ${message.smsDate}
    </h4>
    <h3>
              ${message.sms} <br>
                  <c:if test="${message.file != null && message.file ne ''}">
                  <img src="/getImage?picName=${message.file}" width="60"/>

                  </c:if>
    </h3>

</c:forEach>
<form action="/user/message" method="post" enctype="multipart/form-data">

    <input  type="hidden" name="friendId" value="<c:out value="${requestScope.userSms.id}"/>"><br>
    <input  type="hidden" name="friendName" value="<c:out value="${requestScope.userSms.name}"/>"><br>


    <input type="text" name="sms">
    <%--<textarea rows="4" cols="50" name="sms">--%>
<%--Enter text here...</textarea>--%>
    <br>
    <input type="file" name="picture">
    <br>
    <input type="submit" value="Message"><br><br>
    <a href="/user/userHome">Go back</a>
</form>

</body>
</html>
