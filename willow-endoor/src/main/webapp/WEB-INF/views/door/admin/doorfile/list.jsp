<%
/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-01-11
*作    者： 朱贤俊
*/
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>文件信息列表</title>
    <script type="text/javascript"
            src="${resourceRoot}/door/admin/js/doorfile/list.js"></script>
</head>
<body>
<div class="opbar clearfix">
    <div>
        <div>
            <form name="queryDoorFileForm" id="queryDoorFileForm">
                中文文件名称：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="fileName"/>
                英文文件名称：<input class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="fileNameEn"/>
                产品分类：
                <select class="ipt_100 mart_5" style="width: 90px;padding: 2px;"name="catalogId">
                    <option value="">请选择</option>
                    <c:forEach var="cat" items="${fileCatalogs}">
                        <option value="${cat.objId}" <c:if test="${cat.objId==doorFile.catalogId}">selected</c:if> >${cat.catalogName}</option>
                    </c:forEach>
                </select>

            </form>
        </div>
        <div style="text-align: center" class="mart_5">
            <span class="btnbg btn_search" id="queryOk"><a class="btnbg" href="javascript:void(0)">查询</a></span>
            <span class="btnbg btn_search" id="queryReset"><a class="btnbg" href="javascript:void(0)">重置</a></span>
        </div>
    </div>
    <div class="fl">
        <a id="addDoorFile" name="addDoorFile" href="javascript:void(0)"
           class="btn_add02 btnbg">
            <span class="btnbg"><i class="iconbg"></i>添加</span>
        </a>
    </div>
    <div class="fr">

    </div>
</div>
<div id="listDoorFileDiv"></div>
</body>
</html>