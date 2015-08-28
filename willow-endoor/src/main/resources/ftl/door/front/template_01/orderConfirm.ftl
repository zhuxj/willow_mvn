<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>${customDirectives.getMessage(product.productName,product.productNameEn)}
        -<#if customDirectives.isEnglish>${webSite.websiteTitleEn}<#else>${webSite.websiteTitle}</#if></title>
    <meta name="keywords"
          content="<#if customDirectives.isEnglish>${webSite.websiteKeywordEn}<#else>${webSite.websiteKeyword}</#if>">
    <meta name="description"
          content="<#if customDirectives.isEnglish>${webSite.websiteDescEn}<#else>${webSite.websiteDesc}</#if>">
<#include "*/common.ftl">
<#if customDirectives.isEnglish>
    <link type="text/css" rel="stylesheet" href="${resourceRoot}/door/front/skins/template_01/css/en_inner.css"/>
<#else>
    <link type="text/css" rel="stylesheet" href="${resourceRoot}/door/front/skins/template_01/css/inner.css"/>
</#if>
    <script src="${resourceRoot}/door/front/js/template_01/orderConfirm.js" type="text/javascript"></script>
</head>
<body>
<div id="wrapper">
    <!--head start-->
    <div id="head">
    ${customDirectives.loadModule("top",extMap)}
    </div>
    <!--head end-->
    <!--head end-->
    <div id="body">
        <div id="InnerBanner"></div>
        <div class="HeightTab clearfix"></div>
        <div class="inner">
            <div class="left">
                <div class="Sbox">
                    <div class="topic">${customDirectives.getMessage("公司产品","product")}</div>
                    <div class="ClassNav">
                        <div class="NavTree">
                            <ul>
                            <#list productCatalogs as productCatalog>
                                <li>
                                    <a href="/product?catalogId=${productCatalog.objId}">${customDirectives.getMessage(productCatalog.catalogName!,productCatalog.catalogNameEn!)}</a>
                                </li>
                            </#list>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="HeightTab clearfix"></div>
            </div>
            <div class="right">
                <div class="HeightTab clearfix"></div>
                <div class="main">
                    <div class="content">
                        <div class="MorePro">
                            <div class="FeedBack">


                                <div class="commentbox">
                                    <form id="form1" name="form1" method="post" action="/order/createOrder">
                                        <input type="hidden" name="productId" value="${product.objId!}">
                                        <input type="hidden" name="tempId" id="tempId" value="${tempId!}">
                                        <table id="commentform" width="600" border="0" align="center" cellpadding="0"
                                               cellspacing="0">
                                            <tbody>
                                            <tr>
                                                <td>&nbsp;&nbsp;&nbsp;${customDirectives.getMessage("订购产品","Product")}</td>
                                                <td><span
                                                        class="OrderName">${customDirectives.getMessage(product.productName!,product.productNameEn!)}</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><span class="FontRed">*</span>&nbsp;${customDirectives.getMessage("订购数量","Count")}</td>
                                                <td><input name="orderNum" type="text" id="orderNum" size="10"
                                                           maxlength="10" value="1"></td>
                                            </tr>
                                            <tr>
                                                <td><span class="FontRed">*</span>&nbsp;${customDirectives.getMessage("联系人","Your name")}</td>
                                                <td><input name="contactName" type="text" id="contactName" size="30"
                                                           maxlength="30"></td>
                                            </tr>
                                            <tr>
                                                <td><span class="FontRed">*</span>&nbsp;${customDirectives.getMessage("联系地址","Your address")}</td>
                                                <td><input name="contactAddress" type="text" id="contactAddress"
                                                           size="30" maxlength="30"></td>
                                            </tr>
                                            <tr>
                                                <td><span class="FontRed">*</span>&nbsp;${customDirectives.getMessage("联系手机","Your phone")}</td>
                                                <td><input name="contactPhone" type="text" id="contactPhone" size="30"
                                                           maxlength="30"></td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;&nbsp;&nbsp;${customDirectives.getMessage("联系电话","Your tel")}</td>
                                                <td><input name="contactTel" type="text" id="contactTel" size="30"
                                                           maxlength="30"></td>
                                            </tr>
                                            <tr>
                                                <td><span class="FontRed">*</span>&nbsp;${customDirectives.getMessage("电子邮件","Your Email")}</td>
                                                <td><input name="contactEmail" type="text" id="contactEmail" size="30"
                                                           maxlength="80"></td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;&nbsp;&nbsp;QQ</td>
                                                <td><input name="contactQq" type="text" id="contactQq" size="30"
                                                           maxlength="30"></td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;&nbsp;&nbsp;${customDirectives.getMessage("备注","Memo")}</td>
                                                <td>
                                                    <textarea name="contactMemo" cols="60" rows="5" value=""></textarea>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;&nbsp;&nbsp;${customDirectives.getMessage("验证码","Verify code")}</td>
                                                <td><input name="verycode" id="verycode" maxlength="5" size="10"> <span
                                                        class="FontRed">*</span><img
                                                        src="/verycode/getImageCode?tempId=${tempId!}" width="55"
                                                        onclick="this.src=this.src+'&'+new Date().getTime()"
                                                        alt="${customDirectives.getMessage("图片看不清？点击重新得到验证码","click for new code")}"
                                                        style="cursor:pointer;"></td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td><input class="Cbutton" type="button" id="orderSubmit"
                                                           value="${customDirectives.getMessage("立即订购","Order Now")}"
                                                           onclick="javascript:void(0)"></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="HeightTab clearfix"></div>
${customDirectives.loadModule("bottom",extMap)}
</div>
</body>
</html>
