<%
/**
*版权声明：${codeGenConfig.developerConfig.company!} 版权所有 违者必究
*日    期： ${date!}
*作    者： ${codeGenConfig.developerConfig.developer!}
*/
%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>查看${codeGenConfig.table.tableName!}</title>
    <script type="text/javascript" src="${r"${resourceRoot}"}${codeGenConfig.codeGenFileConfig.jsDir!}/${codeGenConfig.table.simplePackageVar!}detail.js"></script>
</head>
<body>
<form id="${codeGenConfig.table.classVariable!}Form">
    <input type="hidden" id="objId" name="objId" value="${r'${'}${codeGenConfig.table.classVariable!}.objId${r'}'}">
    <input type="hidden" id="userId" name="userId" value="${r'${'}${codeGenConfig.table.classVariable!}.userId${r'}'}">
    <input type="hidden" id="createTime" name="createTime" value="${r'${'}${codeGenConfig.table.classVariable!}.createTime${r'}'}">
    <div class="formlist">
        <table>
            <tbody>
            <#list tableClass.fieldColumns as fieldColumn>
                <#if  !fieldColumn.isIncludeField>
                <tr>
                    <th width="150">${fieldColumn.propName!}:</th>
                    <td>${r'${'}${codeGenConfig.table.classVariable!}.${fieldColumn.javaProperty!}${r'}'}</td>
                </tr>
                </#if>
            </#list>
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