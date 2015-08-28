<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><#if customDirectives.isEnglish>${webSite.websiteTitleEn}<#else>${webSite.websiteTitle}</#if></title>
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
    <script src="${resourceRoot}/door/front/js/template_01/product.js" type="text/javascript"></script>
</head>
<body>
<div id="wrapper">
    <form action="/product" method="get" id="searchForm">
        <input type="hidden" name="totalCount" id="totalCount" value="${productPage.totalCount}"/>
        <input type="hidden" name="pageNo" id="pageNo" value="${productPage.pageParam.pageNo}"/>
        <input type="hidden" name="catalogId" id="catalogId" value="${product.catalogId!}"/>
    </form>
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
                            <div class="CaseBlock">
                                <ul>
                                <#list productPage.result as product>
                                    <div class="albumblock">
                                        <div class="inner"><a href="/product/${product.objId}"
                                                              target="_blank"
                                                              title="${customDirectives.getMessage(product.productName!,product.productNameEn!)}"><img
                                                src="/image/${product.productImage!}.jpg">

                                            <div class="albumtitle">${customDirectives.getMessage(product.productName!,product.productNameEn!)}</div>
                                        </a></div>
                                    </div>
                                </#list>
                                </ul>
                            </div>
                            <div class="clearfix"></div>
                            <div id="pager" class="t_page ColorLink"></div>
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
