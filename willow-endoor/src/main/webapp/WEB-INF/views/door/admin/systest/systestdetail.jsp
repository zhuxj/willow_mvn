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
    <title>查看测试</title>
    <script type="text/javascript" src="${resourceRoot}/door/admin/js/systest/systestdetail.js"></script>
</head>
<body>
<form id="sysTestForm">
    <input type="hidden" id="objId" name="objId" value="${sysTest.objId}">
    <input type="hidden" id="userId" name="userId" value="${sysTest.userId}">
    <input type="hidden" id="createTime" name="createTime" value="${sysTest.createTime}">
    <div class="formlist">
        <table>
            <tbody>
                <tr>
                    <th width="150">菜单名称:</th>
                    <td>${sysTest.menuName}</td>
                </tr>
                <tr>
                    <th width="150">菜单编码:</th>
                    <td>${sysTest.menuCode}</td>
                </tr>
                <tr>
                    <th width="150">父菜单:</th>
                    <td>${sysTest.parentMenuId}</td>
                </tr>
                <tr>
                    <th width="150">排序号:</th>
                    <td>${sysTest.orderNo}</td>
                </tr>
                <tr>
                    <th width="150">菜单类型:</th>
                    <td>${sysTest.menuType}</td>
                </tr>
                <tr>
                    <th width="150">节点类型:</th>
                    <td>${sysTest.nodeType}</td>
                </tr>
                <tr>
                    <th width="150">菜单链接:</th>
                    <td>${sysTest.url}</td>
                </tr>
                <tr>
                    <th width="150">节点图标:</th>
                    <td>${sysTest.icon}</td>
                </tr>
            <tr>
                <th></th>
                <td>
                    <span class="btn btn03" id="_back"><a href="javascript:void(0)"><b>返回</b></a></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>