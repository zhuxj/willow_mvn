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
    <title>更新订单信息</title>
    <script type="text/javascript" src="${resourceRoot}/door/admin/js/doororder/update.js"></script>
</head>
<body>
<form id="doorOrderForm">
    <input type="hidden" id="objId" name="objId" value="${doorOrder.objId}">
    <input type="hidden" id="userId" name="userId" value="${doorOrder.userId}">
    <input type="hidden" id="createTime" name="createTime" value="${doorOrder.createTime}">
    <input type="hidden" class="ipt_250" name="productId" id="productId" value="${doorOrder.productId}">
    <input type="hidden" class="ipt_250" name="orderStatus" id="orderStatus" value="${doorOrder.orderStatus}">

    <div class="formlist">
        <table>
            <tbody>
            <tr>
                <th width="150"><em class="fstar">*</em>产品名称：</th>
                <td>
                    ${product.productName}
                </td>
            </tr>
            <tr>
                <th width="150"><em class="fstar">*</em>订单编号：</th>
                <td>${doorOrder.orderNo}</td>
            </tr>
            <tr>
                <th width="150"><em class="fstar">*</em>订单数量：</th>
                <td><input class="ipt_250" name="orderNum" id="orderNum" value="${doorOrder.orderNum}"></td>
            </tr>
            <tr>
                <th width="150"><em class="fstar">*</em>联系人：</th>
                <td><input class="ipt_250" name="contactName" id="contactName" value="${doorOrder.contactName}"></td>
            </tr>
            <tr>
                <th width="150"><em class="fstar">*</em>联系地址：</th>
                <td><input class="ipt_250" name="contactAddress" id="contactAddress"
                           value="${doorOrder.contactAddress}"></td>
            </tr>
            <tr>
                <th width="150"><em class="fstar">*</em>联系手机：</th>
                <td><input class="ipt_250" name="contactPhone" id="contactPhone" value="${doorOrder.contactPhone}"></td>
            </tr>
            <tr>
                <th width="150"> 联系电话：</th>
                <td><input class="ipt_250" name="contactTel" id="contactTel" value="${doorOrder.contactTel}"></td>
            </tr>
            <tr>
                <th width="150"><em class="fstar">*</em>电子邮箱：</th>
                <td><input class="ipt_250" name="contactEmail" id="contactEmail" value="${doorOrder.contactEmail}"></td>
            </tr>
            <tr>
                <th width="150"> QQ：</th>
                <td><input class="ipt_250" name="contactQq" id="contactQq" value="${doorOrder.contactQq}"></td>
            </tr>
            <tr>
                <th width="150"> 备注：</th>
                <td><input class="ipt_250" name="contactMemo" id="contactMemo" value="${doorOrder.contactMemo}"></td>
            </tr>
            <tr>
                <th></th>
                <td>
                    <span class="btn btn01" id="updateDoorOrder"><a href="javascript:void(0)"><b>保存</b></a></span>
                    <span class="btn btn03" id="_back"><a href="javascript:void(0)"><b>返回</b></a></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</form>
</body>
</html>