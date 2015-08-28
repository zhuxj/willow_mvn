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
</head>
<body>
<div id="wrapper">
    <!--head start-->
    <div id="head">
    ${customDirectives.loadModule("top",extMap)}
    </div>
    <!--head end-->
    <div id="body">
        <div id="InnerBanner"></div>
        <div class="HeightTab clearfix"></div>
        <div class="inner">
            <div class="left">
                <div class="Sbox">
                    <div class="topic">${customDirectives.getMessage("栏目导航","Vavigation")}&nbsp;&nbsp;&nbsp;</div>
                    <div class="blank">
                        <ul>
                        <#list doorArticles as d>
                            <li><a href="/${d.articleCode}">${customDirectives.getMessage(d.articleTitle!,d.articleTitleSn!)}</a></li>
                        </#list>
                        </ul>
                    </div>
                </div>
                <div class="HeightTab clearfix"></div>
            </div>
            <div class="right">
                <div class="HeightTab clearfix"></div>
                <div class="main">
                    <div class="content">
                        <div class="maincontent clearfix">
                        ${customDirectives.getMessage(doorArticle.articleContent!,doorArticle.articleContentEn!)}
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
