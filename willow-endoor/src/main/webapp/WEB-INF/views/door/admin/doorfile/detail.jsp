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
    <title>查看文件信息</title>
    <script type="text/javascript" src="${resourceRoot}/door/admin/js/doorfile/detail.js"></script>
</head>
<body>
<form id="doorFileForm">
    <input type="hidden" id="objId" name="objId" value="${doorFile.objId}">
    <input type="hidden" id="userId" name="userId" value="${doorFile.userId}">
    <input type="hidden" id="createTime" name="createTime" value="${doorFile.createTime}">
    <div class="formlist">
        <table>
            <tbody>
                <tr>
                    <th width="150">中文文件名称:</th>
                    <td>${doorFile.fileName}</td>
                </tr>
                <tr>
                    <th width="150">
                        <em class="fstar">*</em>中文文件：
                    </th>
                    <td>
                        <span id="fileDiv">
                            <a href="/file/${doorFile.fileId}.${doorFile.fileType}" target="_blank">${doorFile.fileName}</a>
                        </span>
                    </td>
                </tr>
                <tr>
                    <th width="150">英文文件名称:</th>
                    <td>${doorFile.fileNameEn}</td>
                </tr>
                <tr>
                    <th width="150">
                        <em class="fstar">*</em>英文文件：
                    </th>
                    <td>
                        <span id="fileDivEn"><a href="/file/${doorFile.fileIdEn}.${doorFile.fileTypeEn}" target="_blank">${doorFile.fileNameEn}</a></span>
                    </td>
                </tr>
                <tr>
                    <th width="150">产品分类:</th>
                    <td>${fileCatalog.catalogName}</td>
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