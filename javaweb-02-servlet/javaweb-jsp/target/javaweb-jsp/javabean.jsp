<%--
  Created by IntelliJ IDEA.
  User: sunka
  Date: 5/27/2021
  Time: 11:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.kaitan.pojo.People" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
//    People people = new People();
//    people.setAddress();
//    people.setId();
//    people.setAge();
//    people.setName();
%>
<jsp:useBean id="people" class="com.kaitan.pojo.People" scope="page"/>
<jsp:setProperty name="people" property="address" value="西安"/>
<jsp:setProperty name="people" property="id" value="1"/>
<jsp:setProperty name="people" property="age" value="111"/>
<jsp:setProperty name="people" property="name" value="kaikai"/>

name: <jsp:getProperty name="people" property="name"/>
id: <jsp:getProperty name="people" property="id"/>
age: <jsp:getProperty name="people" property="age"/>
address: <jsp:getProperty name="people" property="address"/>
</body>
</html>
