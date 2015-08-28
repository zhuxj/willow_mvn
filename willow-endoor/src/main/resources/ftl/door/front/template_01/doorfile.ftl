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
    <script src="${resourceRoot}/door/front/js/template_01/doorfile.js" type="text/javascript"></script>
</head>
<body>
<div id="wrapper">
    <form action="/download" method="get" id="searchForm">
        <input type="hidden" name="totalCount" id="totalCount" value="${doorFilePage.totalCount}"/>
        <input type="hidden" name="pageNo" id="pageNo" value="${doorFilePage.pageParam.pageNo}"/>
        <input type="hidden" name="catalogId" id="catalogId" value="${doorFilePage.catalogId!}"/>
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
                    <div class="topic">${customDirectives.getMessage("文件分类","download")}</div>
                    <div class="ClassNav">
                        <div class="NavTree">
                            <ul>
                            <#list fileCatalogs as fileCatalog>
                                <li>
                                    <a href="/download?catalogId=${fileCatalog.objId}">${customDirectives.getMessage(fileCatalog.catalogName!,fileCatalog.catalogNameEn!)}</a>
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
                    <div class="ArticleList">
                        <ul></ul>
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tbody>
                            <#list doorFilePage.result as doorFile>
                            <tr>
                                <td width="80%" class="fw_t">·<a href="/file/${customDirectives.getMessage(doorFile.fileId!,doorFile.fileIdEn!)}"
                                                                 target="_blank">${customDirectives.getMessage(doorFile.fileName!,doorFile.fileNameEn!)}</a></td>
                                <td width="20%" class="fw_s">[${customDirectives.formartTime(doorFile.createTime!)}]</td>
                            </tr>
                            </#list>
                            </tbody>
                        </table>

                        <div class="clearfix"></div>
                        <div id="pager" class="t_page ColorLink"></div>
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
