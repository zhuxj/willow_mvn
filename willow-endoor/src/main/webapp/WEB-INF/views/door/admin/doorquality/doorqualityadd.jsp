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
    <title>增加质检中心</title>
    <script type="text/javascript" src="${resourceRoot}/door/admin/js/doorquality/doorqualityadd.js"></script>
</head>
<body>
<form id="doorQualityForm">
    <input type="hidden" class="ipt_250" name="fileId" id="fileId">
    <input type="hidden" class="ipt_250" name="fileIdEn" id="fileIdEn">
    <div class="formlist">
        <table>
            <tbody>
                <tr>
                    <th width="150">
                    中文名称：
                    </th>
                    <td><input class="ipt_250" name="qualityName" id="qualityName"></td>
                </tr>
                <tr>
                    <th width="150">
                    中文图片：
                    </th>
                    <td>
                        <div>
                        <span class="btn_upload iconbg"><input
                                id="fileToUpload" type="file" name="fileToUpload" size="1"
                                class="" onchange="gloUploadImage()"></span>
                            <span class="fgray7 fl"> 图片的最佳尺寸：714px*255px</span>
                        </div>
                        <div id="fileDiv" style="margin-top: 30px"></div>

                    </td>
                </tr>
                <tr>
                    <th width="150">
                    英文名称：
                    </th>
                    <td><input class="ipt_250" name="qualityNameEn" id="qualityNameEn"></td>
                </tr>
                <tr>
                    <th width="150">
                    英文图片：
                    </th>
                    <td>
                        <div>
                     <span class="btn_upload iconbg"><input
                             id="fileToUploadEn" type="file" name="fileToUploadEn" size="1"
                             class="" onchange="gloUploadImageEn()"></span>
                            <span class="fgray7 fl"> 图片的最佳尺寸：714px*255px</span>
                        </div>
                        <div id="fileDivEn" style="margin-top: 30px"></div>
                    </td>
                </tr>
            <tr>
                <th></th>
                <td>
                    <span class="btn btn01" id="saveDoorQuality"><a href="javascript:void(0)"><b>保存</b></a></span>
                    <span class="btn btn03" id="_back"><a href="javascript:void(0)"><b>返回</b></a></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>