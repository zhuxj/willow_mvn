/**
 *版权声明：贤俊工作室 版权所有 违者必究
 *日    期： 2012-12-21
 *作    者： 朱贤俊
 */
$(document).ready(function () {
    /**
     * 初始化编辑器
     */
    var editor = $.texteditor({
        textArea:'#productDesc',
        mode:'goods'
    });

    var editorEn = $.texteditor({
        textArea:'#productDescEn',
        mode:'goods'
    });

    function updateProduct() {
        editor.sync();
        editorEn.sync();
        var obj = $("#productForm").serializeJson();
        $.localAjax({
            url:"/admin/product/update",
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

    var config = {
        reportMode:"alert",
        formDiv:"productForm",
        props:[
            {
                name:"productSn",
                label:"产品编号",
                trim:true,
                required:true
            },
            {
                name:"productName",
                label:"产品中文名称",
                trim:true,
                required:true
            },
            {
                name:"productNameEn",
                label:"产品英文名称",
                trim:true,
                required:true
            },
            {
                name:"productNorms",
                label:"产品中文规格",
                trim:true,
                required:true
            },
            {
                name:"productNormsEn",
                label:"产品英文规格",
                trim:true,
                required:true
            },
            {
                name:"productVersion",
                label:"产品型号",
                trim:true,
                required:true
            },
            {
                name:"catalogId",
                label:"产品分类",
                trim:true,
                required:false
            },
            {
                name:"productDesc",
                label:"产品中文详情",
                trim:true,
                required:false
            },
            {
                name:"productDescEn",
                label:"产品英文详情",
                trim:true,
                required:false
            },
            {
                name:"productImage",
                label:"产品图片",
                trim:true,
                required:false
            },
            {
                name:"browseTime",
                label:"浏览次数",
                trim:true,
                required:false, dataType:"int"
            }
        ]
    }
    var checkValid = $.checkValid(config);//构建验证对象


    $("#updateProduct").click(function () {
        if (checkValid.checkAll()) {
            updateProduct();
        }
    });

    $("#_back").click(function () {
        top.jq.workgroundManager.returnPage(true);
    });

    /**
     * 上传图片
     */
    function uploadProductImage() {
        $.imageUpload({
            fileElementId:'fileToUpload',
            data:{fileSort:"3"},
            successFun:function (data) {
                setView(data.imageId);
            }
        });
    }

    gloUploadProductImage = uploadProductImage;

    preView();
    /**
     * 默认显示图片
     */
    function preView() {
        var imageId = $("#productImage").val();
        if (imageId != "") {
            setView(imageId);
        }
    }

    /**
     * 设置预览图片
     * @param imageId
     */
    function setView(imageId) {
        var _preView = $("#preViewImage");
        _preView.empty();
        $("#productImage").val(imageId);
        var _image = $('<img height="200px" width="200px" src="/image/' + imageId + '.jpg" alt="预览图"/>').appendTo(_preView);
    }


})