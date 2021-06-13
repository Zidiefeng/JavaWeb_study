<%--
  Created by IntelliJ IDEA.
  User: sunka
  Date: 5/28/2021
  Time: 9:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <h1>当前有 <span style="color:blue"><%=this.getServletConfig().getServletContext().getAttribute("OnlineCount")%> </span>人在线</h1>
  </body>
</html>
