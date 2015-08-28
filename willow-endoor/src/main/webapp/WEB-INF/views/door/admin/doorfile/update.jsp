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
    <title>更新文件信息</title>
    <script type="text/javascript" src="${resourceRoot}/door/admin/js/doorfile/update.js"></script>
</head>
<body>
<form id="doorFileForm">
    <input type="hidden" id="objId" name="objId" value="${doorFile.objId}">
    <input type="hidden" id="userId" name="userId" value="${doorFile.userId}">
    <input type="hidden" id="createTime" name="createTime" value="${doorFile.createTime}">

    <input type="hidden" class="ipt_250" name="fileId" id="fileId"  value="${doorFile.fileId}">
    <input type="hidden" class="ipt_250" name="fileType" id="fileType"  value="${doorFile.fileType}">
    <input type="hidden" class="ipt_250" name="fileSize" id="fileSize"  value="${doorFile.fileSize}">
    <input type="hidden" class="ipt_250" name="fileIdEn" id="fileIdEn"  value="${doorFile.fileIdEn}">
    <input type="hidden" class="ipt_250" name="fileTypeEn" id="fileTypeEn"  value="${doorFile.fileTypeEn}">
    <input type="hidden" class="ipt_250" name="fileSizeEn" id="fileSizeEn"  value="${doorFile.fileSizeEn}">

    <div class="formlist">
        <table>
            <tbody>
                <tr>
                    <th width="150"> <em class="fstar">*</em>中文文件名称：</th>
                    <td><input class="ipt_250" name="fileName" id="fileName" value="${doorFile.fileName}">
                       <span class="btn_upload iconbg" style="float: left;position: absolute;margin-left: 10px"><input id="fileToUpload" type="file" name="fileToUpload" size="1"
                                                                                                                       class="" onchange="gloUploadDoorFile()"></span>
                    </td>
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
                    <th width="150"> <em class="fstar">*</em>英文文件名称：</th>
                    <td><input class="ipt_250" name="fileNameEn" id="fileNameEn" value="${doorFile.fileNameEn}">
                     <span class="btn_upload iconbg" style="float: left;position: absolute;margin-left: 10px"><input id="fileToUploadEn" type="file" name="fileToUploadEn" size="1"
                                                                                                                     class="" onchange="gloUploadDoorFileEn()"></span>
                    </td>
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
                    <th width="150"> 产品分类：</th>
                    <td>
                        <select class="ipt_250" name="catalogId" id="catalogId">
                            <c:forEach var="cat" items="${fileCatalogs}">
                                <option value="${cat.objId}" <c:if test="${cat.objId==doorFile.catalogId}">selected</c:if> >${cat.catalogName}</option>
                            </c:forEach>
                        </select>

                    </td>
                </tr>
            <tr>
                <th></th>
                <td>
                    <span class="btn btn01" id="updateDoorFile"><a href="javascript:void(0)"><b>保存</b></a></span>
                    <span class="btn btn03" id="_back"><a href="javascript:void(0)"><b>返回</b></a></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>