<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.lang.reflect.Array" %><%--
  Created by IntelliJ IDEA.
  User: sunka
  Date: 5/27/2021
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%
    ArrayList<String> people = new ArrayList();
    people.add(0,"张三");
    people.add(1,"李四");
    people.add(2,"王五");
    people.add(3,"赵六");
    people.add(4,"田七");
    request.setAttribute("list",people);
//    也可以放在session里面，但是request用完就没了，更节省资源，所以还是选择request
%>

<c:forEach var="person" items="${list}">
    <c:out value="${person}"/> <br>
</c:forEach>

<hr>
<c:forEach begin="1" end="3" step="2" var="person" items="${list}">
    <c:out value="${person}"/> <br>
</c:forEach>
</body>
</html>
