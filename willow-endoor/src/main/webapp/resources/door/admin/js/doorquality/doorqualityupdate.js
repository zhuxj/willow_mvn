/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-02-02
*作    者： 朱贤俊
*/
var gloUploadImage;
var gloUploadImageEn;
$(document).ready(function () {
    function uploadImage() {
        $.imageUpload({
            fileElementId:'fileToUpload',
            data:{fileSort:"3"},
            successFun:function (data) {
                var _preView = $("#fileDiv");
                _preView.empty();
                $("#fileId").val(data.imageId);
                var _image = $('<img width="100px" height="100px"  src="/image/' + data.imageId + '.jpg" alt="预览图"/>').appendTo(_preView);
            }
        });
    }

    gloUploadImage = uploadImage;
    function uploadImageEn() {
        $.imageUpload({
            fileElementId:'fileToUploadEn',
            data:{fileSort:"3"},
            successFun:function (data) {
                var _preView = $("#fileDivEn");
                _preView.empty();
                $("#fileIdEn").val(data.imageId);
                var _image = $('<img width="100px" height="100px"  src="/image/' + data.imageId + '.jpg" alt="预览图"/>').appendTo(_preView);
            }
        });
    }
    gloUploadImageEn = uploadImageEn;

    function updateDoorQuality() {
        var obj = $("#doorQualityForm").serializeJson();
        $.localAjax({
        url:"/admin/doorquality/update",
        data:obj,
        dataType:"json",
        type:"POST",
        success:function (result) {
            if (result.success == "1") {
                $.success("更新成功！", true, 3000);
                top.jq.workgroundManager.returnPage(true);
            } else {
                alert(result.msg);
            }
            }
        })
    }

    var config={
    reportMode:"alert",
    formDiv:"doorQualityForm",
    props:[
        {
            name:"qualityName",
            label:"中文名称",
            trim:true,
            required:false
        },
        {
            name:"fileId",
            label:"中文图片ID",
            trim:true,
            required:false
        },
        {
            name:"qualityNameEn",
            label:"中文名称",
            trim:true,
            required:false
        },
        {
            name:"fileIdEn",
            label:"英文图片ID",
            trim:true,
            required:false
        }
    ]
    }
    var checkValid = $.checkValid(config);//构建验证对象


    $("#updateDoorQuality").click(function () {
    if(checkValid.checkAll()){
        updateDoorQuality();
    }
    });

    $("#_back").click(function () {
         top.jq.workgroundManager.returnPage(true);
    });

})