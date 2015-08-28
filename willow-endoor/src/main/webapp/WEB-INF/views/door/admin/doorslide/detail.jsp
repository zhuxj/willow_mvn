<%
/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-01-12
*作    者： 朱贤俊
*/
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>查看幻灯片</title>
    <script type="text/javascript" src="${resourceRoot}/door/admin/js/doorslide/detail.js"></script>
</head>
<body>
<form id="doorSlideForm">
    <input type="hidden" id="objId" name="objId" value="${doorSlide.objId}">
    <input type="hidden" id="userId" name="userId" value="${doorSlide.userId}">
    <input type="hidden" id="createTime" name="createTime" value="${doorSlide.createTime}">
    <div class="formlist">
        <table>
            <tbody>
                <tr>
                    <th width="150">中文图片:</th>
                    <td>
                        <div id="fileDiv" >
                            <img width="245px" height="75px" src="/image/${doorSlide.fileId}.jpg" alt="预览图"/>
                        </div>
                    </td>
                </tr>
                <tr>
                    <th width="150">英文图片:</th>
                    <td>
                        <div id="fileDivEn" >
                            <img width="245px" height="75px" src="/image/${doorSlide.fileIdEn}.jpg" alt="预览图"/>
                        </div>
                    </td>
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