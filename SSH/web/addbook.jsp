<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加图书</title>
</head>
<body>
<!-- 添加图书表单 -->
<%--<form action="${pageContext.request.contextPath}/book_add.action"></form>
--%>
<s:form action="book_add" namespace="/" method="post">
    图书的名称：<s:textfield name="name"/><br/>
    图书的价格：<s:textfield name="price"/><br/>
    <s:submit value="保存"/>
</s:form>

</body>
</html>