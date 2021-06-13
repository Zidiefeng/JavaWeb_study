<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>

    <%@include file = "common/header.jsp"%>
    <h1>body of webpage</h1>
    <%@include file = "common/footer.jsp"%>

    <jsp:include page= "/common/header.jsp"/>
    <h1>body of webpage</h1>
    <jsp:include page= "/common/footer.jsp"/>

</body>
</html>
