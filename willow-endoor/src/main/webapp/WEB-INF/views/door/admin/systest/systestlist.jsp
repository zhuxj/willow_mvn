<%
/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-03-10
*作    者： 朱贤俊
*/
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>测试列表</title>
    <script type="text/javascript"
            src="${resourceRoot}/door/admin/js/systest/systestlist.js"></script>
</head>
<body>
<div class="opbar clearfix">
    <div>
        <div>
            <form name="querySysTestForm" id="querySysTestForm">
                菜单名称：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="menuName"/>
                菜单编码：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="menuCode"/>
                父菜单：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="parentMenuId"/>
                排序号：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="orderNo"/>
                菜单类型：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="menuType"/>
                节点类型：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="nodeType"/>
                菜单链接：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="url"/>
                节点图标：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="icon"/>
            </form>
        </div>
        <div style="text-align: center" class="mart_5">
            <span class="btnbg btn_search" id="queryOk"><a class="btnbg" href="javascript:void(0)">查询</a></span>
            <span class="btnbg btn_search" id="queryReset"><a class="btnbg" href="javascript:void(0)">重置</a></span>
        </div>
    </div>
    <div class="fl">
        <a id="addSysTest" name="addSysTest" href="javascript:void(0)"
           class="btn_add02 btnbg">
            <span class="btnbg"><i class="iconbg"></i>添加</span>
        </a>
    </div>
    <div class="fr">

    </div>
</div>
<div id="listSysTestDiv"></div>
</body>
</html>