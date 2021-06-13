<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>


<h1>This is jsptag 1</h1>
<jsp:forward page="/jsptag2.jsp">
    <jsp:param name="name" value="kaikai"></jsp:param>
    <jsp:param name="age" value="3"></jsp:param>
</jsp:forward>

</body>
</html>