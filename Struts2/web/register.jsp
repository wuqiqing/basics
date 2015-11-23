<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<hr>
<h2>注册页面之代码校验参数</h2>
<h5>
    <s:fielderror></s:fielderror>
</h5>

<form action="${pageContext.request.contextPath }/login/loginActionValidation!register" method="post">
    用户名<input type="text" name="name"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="注册">
</form>
<hr>
<h2>注册页面之XML校验参数</h2>
<h5>
    <s:fielderror></s:fielderror>
</h5>

<form action="${pageContext.request.contextPath }/register/userAction_register" method="post">
    用户名<input type="text" name="name"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="注册">
</form>
</body>
</html>