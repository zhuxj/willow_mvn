/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-01-09
*作    者： 朱贤俊
*/
$(document).ready(function () {
    function saveDoorOrder() {
        var obj = $("#doorOrderForm").serializeJson();
        $.localAjax({
            url:"/admin/doororder/save",
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
    var config={
    reportMode:"alert",
    formDiv:"doorOrderForm",
    props:[
        {
            name:"productId",
            label:"产品ID",
            trim:true,
            required:true
        },
        {
            name:"orderNo",
            label:"订单编号",
            trim:true,
            required:true
        },
        {
            name:"orderNum",
            label:"订单数量",
            trim:true,
            required:true
            ,dataType:"int"
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
            required:false
        },
        {
            name:"contactEmail",
            label:"电子邮箱",
            trim:true,
            required:true
        },
        {
            name:"contactQq",
            label:"QQ",
            trim:true,
            required:false
        },
        {
            name:"orderStatus",
            label:"订单状态",
            trim:true,
            required:false
        },
        {
            name:"contactMemo",
            label:"备注",
            trim:true,
            required:false
        }
    ]
    }
    var checkValid = $.checkValid(config);//构建验证对象
    $("#saveDoorOrder").click(function () {
        if(checkValid.checkAll()){
            saveDoorOrder();
        }
    });

    $("#_back").click(function () {
        top.jq.workgroundManager.returnPage(true);
    });

})