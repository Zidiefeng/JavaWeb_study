<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>

<%--定义一个变量score，赋值99--%>
<c:set var="score" value="99"/>

<c:choose>
    <c:when test="${score>=90}">
        优秀
    </c:when>
    <c:when test="${score>=80}">
        一般
    </c:when>
    <c:when test="${score>=70}">
        良好
    </c:when>
    <c:when test="${score<=60}">
        烂
    </c:when>


</c:choose>

</body>
</html>