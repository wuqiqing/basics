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
<!-- 绑定目标资源文件 -->
<h2>
    <s:text name="page.form.show.login"></s:text>
    之代码校验参数
    &nbsp;
    <s:fielderror></s:fielderror>
</h2>

<form action="${pageContext.request.contextPath }/login/loginActionValidation!login" method="post">
    用户名<input type="text" name="name"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="登陆">
</form>

<h2>登陆页面之XML校验参数
    &nbsp;
    <s:fielderror></s:fielderror>
</h2>

<form action="${pageContext.request.contextPath }/register/userAction_login" method="post">
    用户名<input type="text" name="name"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="登陆">
</form>


<hr>
<!-- 请求参数封装的案例 -->
<h2>登陆页面</h2>

<form action="${pageContext.request.contextPath }/login/loginActionByAttribute" method="post">
    用户名<input type="text" name="name"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="登陆">
</form>
<hr>
<h2>登陆页面</h2>

<form action="${pageContext.request.contextPath }/login/loginActionByObject" method="post">
    用户名<input type="text" name="user.name"><br>
    密码<input type="password" name="user.password"><br>
    <input type="submit" value="登陆">
</form>
<hr>
<h2>登陆页面</h2>

<form action="${pageContext.request.contextPath }/login/loginActionByModel" method="post">
    用户名<input type="text" name="name"><br>
    密码<input type="password" name="password"><br>
    <input type="submit" value="登陆">
</form>
</body>
</html>