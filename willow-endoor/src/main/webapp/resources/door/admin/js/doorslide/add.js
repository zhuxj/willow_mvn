/**
 *版权声明：贤俊工作室 版权所有 违者必究
 *日    期： 2013-01-12
 *作    者： 朱贤俊
 */
var gloUploadSlideImage;
var gloUploadSlideImageEn;
$(document).ready(function () {
    function uploadSlideImage() {
        $.imageUpload({
            fileElementId:'fileToUpload',
            data:{fileSort:"3"},
            successFun:function (data) {
                var _preView = $("#fileDiv");
                _preView.empty();
                $("#fileId").val(data.imageId);
                var _image = $('<img width="245px" height="75px"  src="/image/' + data.imageId + '.jpg" alt="预览图"/>').appendTo(_preView);
            }
        });
    }

    gloUploadSlideImage = uploadSlideImage;
    function uploadSlideImageEn() {
        $.imageUpload({
            fileElementId:'fileToUploadEn',
            data:{fileSort:"3"},
            successFun:function (data) {
                var _preView = $("#fileDivEn");
                _preView.empty();
                $("#fileIdEn").val(data.imageId);
                var _image = $('<img width="245px" height="75px"  src="/image/' + data.imageId + '.jpg" alt="预览图"/>').appendTo(_preView);
            }
        });
    }
    gloUploadSlideImageEn = uploadSlideImageEn;


    function saveDoorSlide() {
        var obj = $("#doorSlideForm").serializeJson();
        $.localAjax({
            url:"/admin/doorslide/save",
            data:obj,
            dataType:"json",
            type:"POST",
            success:function (result) {
                if (result.success == "1") {
                    $.success("增加成功！", true, 3000);
                    top.jq.workgroundManager.returnPage(true);
                } else {
                    alert(result.msg);
                }
            }
        })
    }

    var config = {
        reportMode:"alert",
        formDiv:"doorSlideForm",
        props:[
            {
                name:"fileId",
                label:"中文图片",
                trim:true,
                required:true
            },
            {
                name:"fileIdEn",
                label:"英文图片",
                trim:true,
                required:true
            }
        ]
    }
    var checkValid = $.checkValid(config);//构建验证对象
    $("#saveDoorSlide").click(function () {
        if (checkValid.checkAll()) {
            saveDoorSlide();
        }
    });

    $("#_back").click(function () {
        top.jq.workgroundManager.returnPage(true);
    });

})