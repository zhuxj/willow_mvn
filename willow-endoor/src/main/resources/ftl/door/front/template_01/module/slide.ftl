<!--focus start-->
<div id="FocusBG">
    <div id="Focus">
        <ul style="left: 0px; width: 3920px;">
        <#list doorSlides as doorSlide>
            <li>
                <a href="javascript:void(0)" target="_blank">
                    <img src="/image/${customDirectives.getMessage(doorSlide.fileId!,doorSlide.fileIdEn!)}.jpg"
                         alt="没有图片">
                </a>
            </li>
        </#list>
        </ul>
    </div>
</div>
<!--foncus end-->
<div class="HeightTab clearfix"></div>