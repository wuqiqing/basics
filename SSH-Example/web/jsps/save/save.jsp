<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>收货登记</title>
    <link rel="stylesheet" type="text/css"
          href="<c:url value='/css/maple.css'/>"></link>
    <style type="text/css">
        .tx td {
            padding: 3px;
        }
    </style>
    <!-- 引入jquery库 -->
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
    <!-- 引入jqueryui插件：顺序，必须在jquery之后 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/js/jqueryui/jquery-ui-1.9.2.custom.js"></script>
    <!-- 引入插件样式 -->
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath }/js/jqueryui/css/smoothness/jquery-ui-1.9.2.custom.css">
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

            //简记码异步查询加载表单数据
            //分析：给简记码的加上一个离焦事件
            $("input[name='nm']").blur(function () {
                //异步请求数据
                $.post("${pageContext.request.contextPath }/goods_findByNmAjax.action", {"nm": $(this).val()}, function (data) {
                    //data
                    //判断
                    //如果没有返回结果要清除表单
                    if (data == null) {
                        $("input[name='id']").val("");
                        $("input[name='name']").val("");
                        $("input[name='unit']").val("").removeAttr("readonly");
                        $("#storeid").val("");
                    } else {
                        //填充表单数据
                        $("input[name='id']").val(data.id);
                        $("input[name='name']").val(data.name);
                        $("input[name='unit']").val(data.unit).attr("readonly", "readonly");
                        $("#storeid").val(data.store.id);

                    }
                });

            });

            //自动提示
            //1.简单方式:缺点：数据需要一次性加载，如果数据量比较大，就不合适。
            //$("input[name='name']").autocomplete({
            //    source: [ "c++", "java", "php", "coldfusion", "javascript", "asp", "ruby" ]
            //});
            //2.自定义数据源方式：动态的加载部分所需要的数据显示即可。好处，数据量小。
            $("input[name='name']").autocomplete({
                source: function (request, response) {
                    //只要用户输入值了就开始清除
                    $("input[name='id']").val("");
                    //$("input[name='nm']").val("");
                    $("input[name='unit']").val("").removeAttr("readonly");
                    $("#storeid").val("");


                    //异步请求查询数据
                    $.post("${pageContext.request.contextPath }/goods_findByNameLikeAjax.action", {"name": request.term}, function (data) {
                        response(data);
                    });
                },
                select: function (event, ui) {
                    //插件的选择事件
                    //目标：填充表单
                    //经过api的分析得知，只需要调用ui.item就可以拿到选中的那个json对象
                    //填充表单数据
                    $("input[name='id']").val(ui.item.id);
                    $("input[name='nm']").val(ui.item.nm);
                    $("input[name='unit']").val(ui.item.unit).attr("readonly", "readonly");
                    $("#storeid").val(ui.item.store.id);
                }
            });


        });

    </script>
</head>
<body>
<!-- 中间内容（开始） -->
<table border="0" class="tx" width="100%">
    <tr>
        <td>当前位置&gt;&gt;首页&gt;&gt;入库</td>
    </tr>
</table>
<br>
<table border="0" width="100%" cellpadding="0" cellspacing="0">
    <tr valign="top">
        <td rowspan="2">
            <s:form action="goods_save" method="post" name="select">
                <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tx" align="center">
                    <colgroup>
                        <col width="20%" align="right">
                        <col width="*%" align="left">
                    </colgroup>
                    <tr>
                        <td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2" align="left">
                            <b>货物入库登记：</b>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            简记码：
                        </td>
                        <td>
                            <!-- 隐藏域 -->
                            <s:hidden name="id" cssClass="tx"/>
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
                            计量单位：
                        </td>
                        <td>
                            <s:textfield name="unit" cssClass="tx"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            入库数量：
                        </td>
                        <td>
                            <s:textfield name="amount" cssClass="tx"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            选择仓库：
                        </td>
                        <td>
                            <!--
                            当数据开始封装的时候，先调用goods.getStore()-判断，store是否为空，struts会自动new strore
                            然后，自动调用goods.setStore(store)将store进行初始化，
                            然后，再调用store.setId(id)进行数据封装。
                              -->
                            <select class="tx" style="width:120px;" name="store.id" id="storeid">

                            </select>
                            (此信息从数据库中加载)
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center" style="padding-top:10px;">
                            <input class="tx" style="width:120px;margin-right:30px;" type="button"
                                   onclick="document.forms[0].submit();" value="入库">
                            <input class="tx" style="width:120px;margin-right:30px;" type="reset" value="取消">
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
                        1.输入简记码从数据库<br/>查询是否已经存在此
                        <br/>货物
                        <br/>
                        2.没有则直接输入货物名称
                        <br>
                        3.从数据库选择仓库
                        <br>
                        4.向仓库中新添加或是追加货物
                        <br/>
                        5.记录入库历史记录
                    </td>
                </tr>
                <tr>
                    <td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
                </tr>
            </table>
    </tr>
</table>
</body>
</html>

