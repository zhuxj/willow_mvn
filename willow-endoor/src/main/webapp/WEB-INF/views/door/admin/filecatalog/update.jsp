<%
/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-01-11
*作    者： 朱贤俊
*/
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>更新订单信息</title>
    <script type="text/javascript" src="${resourceRoot}/door/admin/js/filecatalog/update.js"></script>
</head>
<body>
<form id="fileCatalogForm">
    <input type="hidden" id="objId" name="objId" value="${fileCatalog.objId}">
    <input type="hidden" id="userId" name="userId" value="${fileCatalog.userId}">
    <input type="hidden" id="createTime" name="createTime" value="${fileCatalog.createTime}">
    <div class="formlist">
        <table>
            <tbody>
                <tr>
                    <th width="150"> <em class="fstar">*</em>分类名称：</th>
                    <td><input class="ipt_250" name="catalogName" id="catalogName" value="${fileCatalog.catalogName}"></td>
                </tr>
                <tr>
                    <th width="150"> <em class="fstar">*</em>分类英文名称：</th>
                    <td><input class="ipt_250" name="catalogNameEn" id="catalogNameEn" value="${fileCatalog.catalogNameEn}"></td>
                </tr>
            <tr>
                <th></th>
                <td>
                    <span class="btn btn01" id="updateFileCatalog"><a href="javascript:void(0)"><b>保存</b></a></span>
                    <span class="btn btn03" id="_back"><a href="javascript:void(0)"><b>返回</b></a></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>