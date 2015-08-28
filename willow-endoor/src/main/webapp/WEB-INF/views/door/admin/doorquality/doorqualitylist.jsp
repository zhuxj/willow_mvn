<%
/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-02-02
*作    者： 朱贤俊
*/
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>质检中心列表</title>
    <script type="text/javascript"
            src="${resourceRoot}/door/admin/js/doorquality/doorqualitylist.js"></script>
</head>
<body>
<div class="opbar clearfix">
    <div>
        <div>
            <form name="queryDoorQualityForm" id="queryDoorQualityForm">
                中文名称：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="qualityName"/>
                英文名称：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="qualityNameEn"/>
            </form>
        </div>
        <div style="text-align: center" class="mart_5">
            <span class="btnbg btn_search" id="queryOk"><a class="btnbg" href="javascript:void(0)">查询</a></span>
            <span class="btnbg btn_search" id="queryReset"><a class="btnbg" href="javascript:void(0)">重置</a></span>
        </div>
    </div>
    <div class="fl">
        <a id="addDoorQuality" name="addDoorQuality" href="javascript:void(0)"
           class="btn_add02 btnbg">
            <span class="btnbg"><i class="iconbg"></i>添加</span>
        </a>
    </div>
    <div class="fr">

    </div>
</div>
<div id="listDoorQualityDiv"></div>
</body>
</html>