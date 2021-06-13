<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<h2>login</h2>


<div style="text-align:center">
    <form action="${pageContext.request.contextPath}/login" method="post">
        username: <input type="text" name = "username"> <br>
        password: <input type="password" name="password"> <br>
        hobby:
        <input type="checkbox" name="hobby" value="girl">girl
        <input type="checkbox" name="hobby" value="coding">coding
        <input type="checkbox" name="hobby" value="sing">sing
        <input type="checkbox" name="hobby" value="movie">movie
        <br>

        <input type="submit">
    </form>
</div>

</body>
</html>
