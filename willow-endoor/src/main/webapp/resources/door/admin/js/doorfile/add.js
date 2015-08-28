/**
 *版权声明：贤俊工作室 版权所有 违者必究
 *日    期： 2013-01-11
 *作    者： 朱贤俊
 */
var gloUploadDoorFile;
var gloUploadDoorFileEn;
$(document).ready(function () {
    function saveDoorFile() {
        var obj = $("#doorFileForm").serializeJson();
        $.localAjax({
            url:"/admin/doorfile/save",
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
        formDiv:"doorFileForm",
        props:[
            {
                name:"fileName",
                label:"中文文件名称",
                trim:true,
                required:true
            },
            {
                name:"fileId",
                label:"中文文件ID",
                trim:true,
                required:false
            },
            {
                name:"fileType",
                label:"中文文件类型",
                trim:true,
                required:false
            },
            {
                name:"fileSize",
                label:"中文文件大小",
                trim:true,
                required:false, dataType:"int"
            },
            {
                name:"fileNameEn",
                label:"英文文件名称",
                trim:true,
                required:true
            },
            {
                name:"fileIdEn",
                label:"英文文件ID",
                trim:true,
                required:false
            },
            {
                name:"fileTypeEn",
                label:"英文文件类型",
                trim:true,
                required:false
            },
            {
                name:"fileSizeEn",
                label:"英文文件大小",
                trim:true,
                required:false, dataType:"int"
            },
            {
                name:"catalogId",
                label:"产品分类",
                trim:true,
                required:false
            }
        ]
    }
    var checkValid = $.checkValid(config);//构建验证对象
    $("#saveDoorFile").click(function () {
        if (checkValid.checkAll()) {
            saveDoorFile();
        }
    });

    $("#_back").click(function () {
        top.jq.workgroundManager.returnPage(true);
    });


    /**
     * 上传文件
     */
    function uploadDoorFile() {
        debugger
        $.fileUpload({
            fileElementId:'fileToUpload',
            data:{fileSort:"3"},
            successFun:function (data) {
                var fileId = data.fileId;
                var fileType = data.fileType;
                var fileSize = data.fileSize;
                var fileName = data.fileName;
                $("#fileId").val(fileId);
                $("#fileType").val(fileType);
                $("#fileSize").val(fileSize);
                $("#fileName").val(fileName);
                $("#fileDiv").html('<a target="_blank" href="/file/' + fileId + '" >' + fileName + '</a>')
            }
        });
    }

    gloUploadDoorFile = uploadDoorFile;


    /**
     * 上传文件
     */
    function uploadDoorFileEn() {
        $.fileUpload({
            fileElementId:'fileToUploadEn',
            data:{fileSort:"3"},
            successFun:function (data) {
                var fileId = data.fileId;
                var fileType = data.fileType;
                var fileSize = data.fileSize;
                var fileName = data.fileName;
                $("#fileIdEn").val(fileId);
                $("#fileTypeEn").val(fileType);
                $("#fileSizeEn").val(fileSize);
                $("#fileNameEn").val(fileName);
                $("#fileDivEn").html('<a target="_blank" href="/file/' + fileId + '" >' + fileName + '</a>')
            }
        });
    }

    gloUploadDoorFileEn = uploadDoorFileEn;


})