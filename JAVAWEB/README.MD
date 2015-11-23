#：如何让Servlet在服务器启动时，加载创建对象 ，调用init
       配置: <load-on-startup>2</load-on-startup>
#<init-param>
    servlet 初始化的时候设置的信息
#<context-param>
    全局设置的信息
#servlet上下文对象
    1、ServletContext 每个工程对应一个  容器启动时候 自动创建 容器停止 该对象自动销毁
    2、获得ServletContext对象 ----- > servletConfig.getServletContext();  或者this.getServletContext();
#web.xml中配置错误页面
     见module
#如何让一个jsp页面引入另外一个jsp
     引入另外的jsp片段
     <jsp:include page="index.jsp"></jsp:include>
     引入另外的jsp页面
     <%@include file="/xxx.jsp"%>
#jstl标签库
    <%@taglib prefix="资源地址简称  标签的简称 "  uri="标签库所在地址  "%>
    <C:if....
    <C:foreach...
#el表达式
    ${requestScope.hashCode()}
    ${requestScope.hashCode()}
        ${sessionScope}
        ${page}
        ${applicationScope}
    支持运算符 （==  < > != empty(判断是否为空)）     ${(empty num)?"空值":"不为空" }//  不为空
    获取四大内置对象数据 (pageContext  request  session  application)
