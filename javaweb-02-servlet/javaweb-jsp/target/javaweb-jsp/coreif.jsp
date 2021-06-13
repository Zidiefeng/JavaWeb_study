<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--引入JSTL核心标签库我们才能使用JSTL标签--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>
<hr>

<h4>if测试</h4>
<form action="coreif.jsp" method="get">
    <%--el表达式获取表单中的data，用${param.参数名}--%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" name="登录">
</form>


<%--如果是admin，登陆成功--%>

<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="欢迎登录"/>
</c:if>

<c:out value="${isAdmin}"/>
</body>
</html>