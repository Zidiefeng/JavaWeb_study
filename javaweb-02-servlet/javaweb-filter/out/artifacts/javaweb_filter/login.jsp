<%--
  Created by IntelliJ IDEA.
  User: sunka
  Date: 5/30/2021
  Time: 1:34 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Log In</h1>
<br>
<form action="${pageContext.request.contextPath}/servlet/login" method="post">
    <input type="text" name="username">
    <input type="submit">
</form>

</body>
</html>
