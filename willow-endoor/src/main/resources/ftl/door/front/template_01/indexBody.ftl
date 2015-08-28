<!--body start-->
<div id="body">
    <!--MainBlock start-->
    <div class="MainBlock">
        <!--left start-->
        <div class="right">
            <div class="topic">
                <div class="TopicTitle"><a
                        href="/about"><#if customDirectives.isEnglish>${about.articleTitleSn!}<#else>${about.articleTitle!}</#if></a>
                </div>
                <div class="TopicMore"><a href="/about"><img
                        src="${resourceRoot}/door/front/skins/template_01/images/more.png"></a></div>
            </div>
            <div class="img"><a href="/about" target="_blank"><img
                    src="${resourceRoot}/door/front/skins/template_01/images/com.jpg"
                    width="200" height="100"
                    alt="<#if customDirectives.isEnglish>${about.articleTitleEn!}<#else>${about.articleTitle!}</#if>"></a>
            </div>
            <div class="txt ColorLink"><p>
                　　${content!}<a
                    href="/about" target="_blank"><#if customDirectives.isEnglish>detail<#else>详情</#if>&gt;&gt;</a></p></div>
            <div class=" clearfix"></div>
        </div>
        <!--left end-->
        <div class="WidthTab2"></div>

        <!--right start-->
        <div class="left">
            <div class="topic">
                <div class="TopicTitle"><a href="/quality">${customDirectives.getMessage("质检中心","Quality")}</a></div>
                <div class="TopicMore"><a href="/quality">
                    <img src="${resourceRoot}/door/front/skins/template_01/images/more.png"></a></div>
            </div>
            <div class="clearfix"></div>
            <ul></ul>
            <div class="w_ctr">
                <div class="JQ-slide">
                    <ul class="JQ-slide-content">
                    <#list doorQualities as doorQualitie>
                        <li><img
                                src="/image/${customDirectives.getMessage(doorQualitie.fileId!,doorQualitie.fileIdEn!)}.jpg"
                                width="714px" height="300px"
                                alt="${customDirectives.getMessage(doorQualitie.qualityName!,doorQualitie.qualityNameEn!)}"><span>${customDirectives.getMessage(doorQualitie.qualityName!,doorQualitie.qualityNameEn!)}</span>
                        </li>
                    </#list>
                    </ul>
                    <ul class="JQ-slide-nav">
                    <#list doorQualities as doorQualitie>
                        <li <#if doorQualitie_index==0>class="on"</#if>>${doorQualitie_index+1}</li>
                    </#list>
                    </ul>

                </div>
            </div>
        </div>
        <!--right end-->

        <!--right2 start-->
    <#--<div class="right2">-->
    <#--<div class="topic">-->
    <#--<div class="TopicTitle"><a href="/">下载中心</a></div>-->
    <#--<div class="TopicMore"><a href="/"><img-->
    <#--src="${resourceRoot}/door/front/skins/template_01/images/more.png"></a></div>-->
    <#--</div>-->
    <#--<div class="txt ColorLink">-->
    <#--<table class="MBlockTable" width="100%" border="0" cellspacing="0" cellpadding="0">-->
    <#--<tbody>-->
    <#--<tr>-->
    <#--<td width="80%" class="ListTitle">-->
    <#--<a href="" target="_blank" title="">-->
    <#--中国电信下月-->
    <#--</a>-->
    <#--</td>-->
    <#--<td width="20%"><span>下载</span></td>-->
    <#--</tr>-->
    <#--</tbody>-->
    <#--</table>-->
    <#--</div>-->
    <#--<div class=" clearfix"></div>-->
    <#--</div>-->
        <!--right2 end-->
        <div class="clearfix"></div>

    </div>
    <!--MainBlock end-->

    <div class="HeightTab2 clearfix"></div>

    <!--MainBlock3 start-->
    <div class="MainBlock3">
        <!--right start-->
        <div class="left">
            <div class="TabBlock">
                <div class="menu" id="index_productCat">
                    <ul>
                    <#--tabactive-->
                    <#list productCatalogList as productCatalog>
                        <li>
                            <a title="${customDirectives.getMessage(productCatalog.catalogName!,productCatalog.catalogNameEn!)}"
                               id="tab_${productCatalog.objId!}" objId="${productCatalog.objId!}" href="/product?catalogId=${productCatalog.objId}"
                               class="tab  <#if productCatalog_index==0>tabactive</#if>">${customDirectives.getMessage(productCatalog.catalogName!,productCatalog.catalogNameEn!)}</a>
                        </li>
                    </#list>
                    </ul>
                </div>
            <#list productCatalogList as productCatalog>
                <div id="tabcontent_${productCatalog.objId!}" class="tabcontent"
                     style="display: <#if productCatalog_index==0>block;<#else>none;</#if>">
                    <div class="blk_29">
                        <div class="LeftBotton"></div>
                        <div class="Cont" id="ISL_Cont_1" style="width: 888px; overflow: hidden;">
                            <div pruductSize="${productCatalog.productList?size}"
                                 style="position:absolute;float: left;overflow: hidden; zoom: 1; width: #{productCatalog.productList?size * 222}px;left: 0px;"
                                 class="productDiv">
                                <#list productCatalog.productList as product>
                                    <div class="box"><a class="imgBorder" href="/product/${product.objId}"
                                                        target="_blank"
                                                        title="${customDirectives.getMessage(product.productName!,product.productNameEn!)}"><img
                                            width="163px" height="163px"
                                            alt="${customDirectives.getMessage(product.productName!,product.productNameEn!)}"
                                            src="/image/${product.productImage!}.jpg">

                                        <p>${customDirectives.getMessage(product.productName!,product.productNameEn!)}</p>
                                    </a></div>
                                </#list>
                            </div>
                        </div>
                        <div class="RightBotton"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </#list>
            </div>
        </div>
        <!--right end-->
        <div class="clearfix"></div>
    </div>
    <!--MainBlock end-->
    <!--links start-->
<#--<div id="Links">-->
<#--<span>友情链接：</span>-->
<#--<a href="http://www.baidu.com" target="_blank" rel="nofollow">百度</a><a-->
<#--</div>-->
    <!--links end-->

</div>
<!--body end-->
<script type="text/javascript">
    $(document).ready(function () {
        $("#index_productCat .tab").hover(function () {
            $("#index_productCat .tab").removeClass("tabactive");
            $(this).addClass("tabactive");
            $(".tabcontent").hide();
            $("#tabcontent_" + $(this).attr("objId")).show();
        })

        var step = 888;   //移动一次888px；
        var one = 222;
        var position = 0;//当前标签left值

        //商品左右移动
        $(".LeftBotton").click(function () {
            var productDiv = $(this).next().find(".productDiv");
            position = parseInt(productDiv.css("left"));
            if (position >= 0) {
                position = 0;
            } else {
                position = position + step;
            }
            productDiv.animate({left:position }, "slow");
        });
        $(".RightBotton").click(function () {
            var productDiv = $(this).prev().find(".productDiv");
            position = parseInt(productDiv.css("left"));
            var temp = position + (one * parseInt(productDiv.attr("pruductSize")));
            if (temp > 0 && temp < one + 4) {
                position = 0;
            } else {
                position = position - step;
            }
            productDiv.animate({left:position }, "slow");
        });


    })
</script>