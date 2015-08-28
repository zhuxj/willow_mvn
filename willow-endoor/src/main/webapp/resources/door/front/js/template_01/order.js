/**
 *版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2013
 *日    期： 13-1-9
 *作    者： 朱贤俊
 */
$(document).ready(function () {
    $("#productCat").change(function () {
        var catalogId = $(this).val();
        var data = {};
        data.catalogId = catalogId;
        $.localAjax({
            url:"/product/queryProductsByCatalogId",
            data:data,
            dataType:'json',
            type:'post',
            success:function (result) {
                var productIdSel = $("#productId");
                productIdSel.empty();
                $("<option value=''>请选择</option>").appendTo(productIdSel);
                var products = result.products;
                $.each(products, function (idx, product) {
                    debugger
                    var name = isEnglish() ? product.productNameEn : product.productName
                    productIdSel.append($("<option value='" + product.objId + "'>" + name + "</option>"));
                });
            }
        });
    })


    var data = {};
    var config = {
        reportMode:"alert",
        formDiv:"form1",
        props:[
            {
                name:"productId",
                label:"商品信息",
                trim:true,
                required:true
            },
            {
                name:"orderNum",
                label:"订单数量",
                trim:true,
                required:true,
                dataType:"int"
            },
            {
                name:"contactName",
                label:"联系人",
                trim:true,
                required:true
            },
            {
                name:"contactAddress",
                label:"联系地址",
                trim:true,
                required:true
            },
            {
                name:"contactPhone",
                label:"联系手机",
                trim:true,
                required:true
            },
            {
                name:"contactTel",
                label:"联系电话",
                trim:true,
                required:false,
                dataType:"tel"
            },
            {
                name:"contactEmail",
                label:"电子邮箱",
                trim:true,
                required:true,
                dataType:"email"
            },
            {
                name:"contactQq",
                label:"QQ",
                trim:true,
                required:false
            },
            {
                name:"contactMemo",
                label:"备注",
                trim:true,
                required:false,
                lengthRange:{min:0, max:300, unit:"char"}
            }
        ]
    }
    var checkValid = $.checkValid(config);//构建验证对象

    $("#orderSubmit").click(function () {
        var data = {};
        data.tempId = $("#tempId").val();
        data.verycode = $("#verycode").val();
        if (data.verycode == "") {
            alert("验证码不能为空");
            return;
        }
        if (checkValid.checkAll()) {
            $.localAjax({
                url:"/verycode/checkVerycode",
                data:data,
                dataType:'json',
                type:'post',
                success:function (result) {
                    if (result.isTrue == "true") {
                        var formData = $("#form1").serializeJson();
                        $.localAjax({
                            url:"/order/createOrder",
                            data:formData,
                            dataType:'json',
                            type:'post',
                            success:function (result) {
                                if (result.success == "1") {
                                    alert("订单创建成功!");
                                    document.location = "/order";
                                } else {
                                    alert("订单创建失败!");
                                }
                            }
                        });
                    } else {
                        alert("验证码不正确");
                    }
                }
            });
        }
    })
})