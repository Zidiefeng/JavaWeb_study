<html>
<body>


<%--jsp 表达式--%>
<%= new java.util.Date() %>

<%--horizontal line--%>
<hr>


<%--jsp script--%>
<%
    int sum =0;
    for(int i = 1; i<=100; i++){
        sum+=i;
    }
    out.println("<h1>Sum = "+sum+"</h1>");
%>


<hr>


<%
 int x = 10;
 out.println(x);
%>
<p>a sentence</p>
<%
 x= 10;
%>


<hr>


<%
 for (int i=0; i<5; i++){
%>
<h3>hi, <%=i%> </h3>
<%
}
%>

<hr>

<%!
    static{
     System.out.println("Loading Servlet");
    }

    private int globalVar= 0;

    public void kaikai(){
        System.out.println("进入了方法");
    }
%>



</body>
</html>
