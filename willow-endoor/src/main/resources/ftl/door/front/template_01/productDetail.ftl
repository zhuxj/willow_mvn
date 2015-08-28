<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>${customDirectives.getMessage(product.productName,product.productNameEn)}-<#if customDirectives.isEnglish>${webSite.websiteTitleEn}<#else>${webSite.websiteTitle}</#if></title>
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
    <script src="${resourceRoot}/door/front/js/template_01/productDetail.js" type="text/javascript"></script>
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
                            <div class="ProInfo">
                                <div class="image"><a href="/product/${product.objId!}" rel="clearbox" title="${customDirectives.getMessage(product.productName!,product.productNameEn!)}"><img src="/image/${product.productImage!}.jpg" height="270" width="270"></a></div>
                                <div class="column">
                                    <div class="title"><h3>${customDirectives.getMessage(product.productName!,product.productNameEn!)}</h3></div>
                                    <div class="infos">${customDirectives.getMessage("更新","Update")}：${customDirectives.formartTime(product.updateTime!)}</div>
                                    <ul>
                                        <li><span>${customDirectives.getMessage("产品规格","Size")}：</span>&nbsp;&nbsp;&nbsp;${customDirectives.getMessage(product.productNorms!,product.productNormsEn!)}</li>
                                        <li><span>${customDirectives.getMessage("产品型号","Model")}：</span>&nbsp;&nbsp;&nbsp;${product.productVersion!}</li>
                                        <li><span>${customDirectives.getMessage("点击量","Views")}：</span>&nbsp;&nbsp;&nbsp;${product.browseTime!}</li>
                                        <li><a href="/order/${product.objId!}" id="buy"><img src="${resourceRoot}/door/front/skins/template_01/images/${customDirectives.getMessage("order_img.gif","order_img_en.gif")}" alt="${customDirectives.getMessage("在线订购","ORDER")}"></a></li>
                                    </ul>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                            <div class="maincontent clearfix">
                                <div class="IntroTitle">${customDirectives.getMessage("产品介绍","Introduction")}</div>
                                ${customDirectives.getMessage(product.productDesc!,product.productDescEn!)}
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
