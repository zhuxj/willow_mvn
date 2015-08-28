<%
    /**
     *版权声明：贤俊工作室 版权所有 违者必究
     *日    期： 2013-01-09
     *作    者： 朱贤俊
     */
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>订单信息列表</title>
    <script type="text/javascript"
            src="${resourceRoot}/door/admin/js/doororder/list.js"></script>
</head>
<body>
<div class="opbar clearfix">
    <div>
        <div>
            <form name="queryDoorOrderForm" id="queryDoorOrderForm">
                订单编号：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;" name="orderNo"/>
                联系人：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;" name="contactName"/>
                联系手机：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;" name="contactPhone"/>
                联系电话：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;" name="contactTel"/>
                电子邮箱：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;" name="contactEmail"/>
                订单状态：
                <select name="orderStatus"  class="ipt_100 mart_5" style="width: 90px;padding: 2px;">
                    <option value="">请选择</option>
                    <option value="1">未处理</option>
                    <option value="9">已处理</option>
                </select>
            </form>
        </div>
        <div style="text-align: center" class="mart_5">
            <span class="btnbg btn_search" id="queryOk"><a class="btnbg" href="javascript:void(0)">查询</a></span>
            <span class="btnbg btn_search" id="queryReset"><a class="btnbg" href="javascript:void(0)">重置</a></span>
        </div>
    </div>
    <div class="fl">
        <%--<a id="addDoorOrder" name="addDoorOrder" href="javascript:void(0)"--%>
           <%--class="btn_add02 btnbg">--%>
            <%--<span class="btnbg"><i class="iconbg"></i>添加</span>--%>
        <%--</a>--%>
    </div>
    <div class="fr">

    </div>
</div>
<div id="listDoorOrderDiv"></div>
</body>
</html>