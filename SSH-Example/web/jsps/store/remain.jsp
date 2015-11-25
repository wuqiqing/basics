<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>货物统计</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/css/maple.css'/>"></link>
    <style type="text/css">
        .tx td {
            padding: 3px;
        }

        .store {
            width: 100%;
            border: 1px solid gray;
            border-collapse: collapse;
        }

        .store td {
            border: 1px solid gray;
            padding: 3px;
        }

        .store a {
            text-decoration: underline;
            color: blue;
        }
    </style>
    <!-- 引入jquery库 -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <script type="text/javascript">
        $(function () {
            //目标：在页面加载之后填充仓库列表
            //发起ajax请求，查询所有仓库
            //4个参数
            //$.post(请求的url，请求的参数，回调函数，返回结果类型);
            //请求的参数支持两种：json({key:value})，传统的（"name=aa&age=18"）
            $.post("${pageContext.request.contextPath }/store_findAllStoreAjax.action", function (data) {

                //data是转换后（转成json对象）的数据，dom对象-json数组对象
                $(data).each(function () {
                    //每一个小的json对象-dom对象
                    //组装option
                    var option = $("<option value='" + this.id + "'>" + this.name + "</option>");
                    $("#storeid").append(option);
                });

            });

        });
    </script>
</head>
<body>
<table border="0" class="tx" width="100%">
    <tr>
        <td>当前位置&gt;&gt;首页&gt;&gt;货物库存</td>
    </tr>
</table>
<br>
<table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td rowspan="1">
            <s:form action="goods_listpage" namespace="/" method="post" name="select">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tx" align="center">
                    <colgroup>
                        <col width="20%" align="right">
                        <col width="*%" align="left">
                    </colgroup>
                    <tr>
                        <td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2" align="left">
                            <b>查询条件：</b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            简记码：
                        </td>
                        <td>
                            <s:textfield name="nm" cssClass="tx"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            货物名称：
                        </td>
                        <td>
                            <s:textfield name="name" cssClass="tx"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            选择仓库：
                        </td>
                        <td>
                            <select class="tx" style="width:120px;" name="store.id" id="storeid">
                                <option value="">--请选择--</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right" style="padding-top:10px;">
                            <input class="tx" style="width:120px;margin-right:30px;" type="button"
                                   onclick="document.forms[0].submit();" value="查询">
                        </td>
                    </tr>
                </table>
            </s:form>
        </td>
        <td valign="top" width="20%">
            <table width="100%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                    <td background="<c:url value='/picture/loginpage.gif'/>" align="center"><br>
                        <font color="red">操作步骤</font>
                    </td>
                </tr>
                <tr>
                    <td background="<c:url value='/picture/bg1.jpg'/>" style="padding-left:10px;">
                        1.显示所有货物的库存情况
                        <br/>
                        2.根据条件查询某种货的库存情况
                        <br/>
                        3.出入库完成后显示某种货物的库存情况
                    </td>
                </tr>
                <tr>
                    <td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr valign="top">
        <td rowspan="2">
            <form action="" method="post" name="select">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tx" align="center">
                    <colgroup>
                        <col width="20%" align="right">
                        <col width="*%" align="left">
                    </colgroup>
                    <tr>
                        <td bgcolor="a0c0c0" style="padding-left:10px;" colspan="1" align="left">
                            <b>货物库存：</b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table class="store">
                                <tr style="background:#D2E9FF;text-align: center;">
                                    <td>简记码</td>
                                    <td>名称</td>
                                    <td>计量单位</td>
                                    <td>库存数量</td>
                                    <td>所在仓库</td>
                                    <td>操作</td>
                                </tr>
                                <s:iterator value="result.pageDataList" var="goods">
                                    <tr>
                                        <td>${nm }</td>
                                        <td>${name }</td>
                                        <td><s:property value="unit"/></td>
                                        <td>${amount }</td>
                                        <td>${store.name}</td>
                                        <td>
                                            <a href="<c:url value='/jsps/save/save.jsp'/>">入库</a>
                                            <a href="<c:url value='/jsps/out/out.jsp'/>">出库</a>
                                            <a href="<c:url value='/jsps/his/his.jsp'/>">历史记录</a>
                                        </td>
                                    </tr>
                                </s:iterator>

                            </table>
                        </td>
                    </tr>
                </table>
                <div align="right">
                    <!-- 当前页面如果不是第一页，则显示首页和上一页 -->
                    <s:if test="result.page>1">
                        <a href="${pageContext.request.contextPath }/goods_listpage.action?page=1${result.parameterUrl}">首页</a>
                        <a href="${pageContext.request.contextPath }/goods_listpage.action?page=${result.page-1}${result.parameterUrl}">上一页</a>
                    </s:if>

                    <s:iterator begin="result.begin" end="result.end" var="i">
                        <a href="${pageContext.request.contextPath }/goods_listpage.action?page=${i}${result.parameterUrl}">[${i}]</a>
                    </s:iterator>

                    <!-- 下一页和尾页页码的显示
                    当前页码如果小于最后一页
                     -->
                    <s:if test="result.page<result.end">
                        <a href="${pageContext.request.contextPath }/goods_listpage.action?page=${result.page+1}${result.parameterUrl}">下一页</a>
                        <a href="${pageContext.request.contextPath }/goods_listpage.action?page=${result.totalPage}${result.parameterUrl}">尾页</a>
                    </s:if>


                    <input type="text" size="2" name="page"/>
                    <input type="button" value="go" size="2"/>
                </div>
            </form>
        </td>
    </tr>
</table>
</body>
</html>

