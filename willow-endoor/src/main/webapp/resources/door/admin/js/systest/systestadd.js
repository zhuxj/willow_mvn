/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-03-10
*作    者： 朱贤俊
*/
$(document).ready(function () {
    function saveSysTest() {
        var obj = $("#sysTestForm").serializeJson();
        $.localAjax({
            url:"/admin/systest/save",
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
    formDiv:"sysTestForm",
    props:[
        {
            name:"menuName",
            label:"菜单名称",
            trim:true,
            required:false
        },
        {
            name:"menuCode",
            label:"菜单编码",
            trim:true,
            required:false
        },
        {
            name:"parentMenuId",
            label:"父菜单",
            trim:true,
            required:false
        },
        {
            name:"orderNo",
            label:"排序号",
            trim:true,
            required:false
            ,dataType:"int"
        },
        {
            name:"menuType",
            label:"菜单类型",
            trim:true,
            required:false
        },
        {
            name:"nodeType",
            label:"节点类型",
            trim:true,
            required:false
        },
        {
            name:"url",
            label:"菜单链接",
            trim:true,
            required:false
        },
        {
            name:"icon",
            label:"节点图标",
            trim:true,
            required:false
        }
    ]
    }
    var checkValid = $.checkValid(config);//构建验证对象
    $("#saveSysTest").click(function () {
        if(checkValid.checkAll()){
            saveSysTest();
        }
    });

    $("#_back").click(function () {
        top.jq.workgroundManager.returnPage(true);
    });

})