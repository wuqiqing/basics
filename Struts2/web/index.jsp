<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
<h2>图书管理</h2>
<a href="${pageContext.request.contextPath }/book/bookAction!addBook">添加图书</a>&nbsp;
<a href="${pageContext.request.contextPath }/book/bookAction!deleteBook">删除图书</a>&nbsp;
<a href="${pageContext.request.contextPath }/book/bookAction!updateBook">修改图书</a>&nbsp;
<a href="${pageContext.request.contextPath }/book/bookAction!queryBook">查询图书</a>&nbsp;
<hr>
<h2>转发</h2>
<a href="${pageContext.request.contextPath }/jump/dispacherAction!dispatcherMethod">转发 dispatcher</a>&nbsp;
<h2>重定向</h2>
<a href="${pageContext.request.contextPath }/jump/redirectAction!redirect">重定向 redirect</a>&nbsp;


<a href="${pageContext.request.contextPath }/book/bookAction_addBook">添加图书</a>&nbsp;
<a href="${pageContext.request.contextPath }/book/bookAction_deleteBook">删除图书</a>&nbsp;
<a href="${pageContext.request.contextPath }/book/bookAction_updateBook">修改图书</a>&nbsp;
<a href="${pageContext.request.contextPath }/book/bookAction_queryBook">查询图书</a>&nbsp;
</body>
</html>
