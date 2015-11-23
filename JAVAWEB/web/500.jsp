<%--
  Created by IntelliJ IDEA.
  User: 常晓虎
  Date: 2015/11/23
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>500错误页面</title>
</head>
<body>

<%
    if (exception != null) {
        out.print(exception.getMessage());
        if (exception.getCause() != null) {
            out.print(exception.getCause().getMessage());
        }
    }

%>
</body>
</html>
