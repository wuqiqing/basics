<%--
  Created by IntelliJ IDEA.
  User: 常晓虎
  Date: 2015/11/23
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="index.jsp" %>

<html>
<head>
    <meta http-equiv="refresh" content="10;url=<%=request.getContextPath()%>/index.jsp">

    <title>404页面</title>

</head>
<body>
<jsp:include page="index.jsp"></jsp:include>
</body>
</html>
