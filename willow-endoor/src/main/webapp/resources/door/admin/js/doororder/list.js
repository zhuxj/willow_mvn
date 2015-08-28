/**
 *版权声明：贤俊工作室 版权所有 违者必究
 *日    期： 2013-01-09
 *作    者： 朱贤俊
 */
$(document).ready(function () {
    var doorOrderGrid = $.egrid({
        container:'listDoorOrderDiv', //放置表格的DIV
        url:'/admin/doororder/query', //表格数据获取的URL
        params:{}, //数据查询条件
        sorts:{
            sortFieldName:'create_time',
            sortType:'desc'
        }, //排序条件
        pageSize:10, //每页展现数据数
        autoLoad:true, //是否表格创建完自动加载数据
        showTableIndex:false, //是否显示表格行索引
        keyColumn:'objId',
        columns:[
            {column:'product.productName', label:'产品名称', width:'36px'},
            {column:'orderNo', label:'订单编号', width:'15px'},
            {column:'orderNum', label:'订单数量', width:'20px'},
            {column:'contactName', label:'联系人', width:'50px'},
            {column:'contactAddress', label:'联系地址', width:'100px'},
            {column:'contactPhone', label:'联系手机', width:'50px'},
            {column:'contactTel', label:'联系电话', width:'50px'},
            {column:'contactEmail', label:'电子邮箱', width:'50px'},
            {column:'contactQq', label:'QQ', width:'50px'},
            {column:'displayOrderStatus', label:'订单状态', width:'30px'},
            {column:'func', label:'操作', align:'center', width:'80px', headerCls:"cmp_tanle_tdc", cellCss:"cmp_tanle_tdc", actions:[
                {label:'处理', action:function (record, keyField, grid, colNo, rowNo, cell) {
                    if (record.orderStatus == "9") {
                        alert("订单已处理");
                        return;
                    }
                    if (confirm("确定修改为已处理状态吗?")) {
                        var data = {};
                        data.objId = record.objId;
                        data.orderStatus = "9";
                        $.localAjax({
                            url:"/admin/doororder/updateByIdSelective",
                            data:data,
                            dataType:'json',
                            type:'post',
                            success:function (result) {
                                $.success("处理成功！", true, 3000);
                                doorOrderGrid.refresh();
                            }
                        });
                    }
                }
                },
                {label:'编辑', action:function (record, keyField, grid, colNo, rowNo, cell) {
                    if (record.orderStatus == "9") {
                        alert("订单已处理,不能修改!");
                        return;
                    }
                    top.jq.workgroundManager.openPage({url:"/admin/doororder/updatePage?objId=" + record.objId,
                        onChanged:function () {
                            doorOrderGrid.refresh();
                        }
                    });
                }
                },
                {label:'查看', action:function (record, keyField, grid, colNo, rowNo, cell) {
                    top.jq.workgroundManager.openPage({url:"/admin/doororder/detailPage?objId=" + record.objId,
                        onChanged:function () {
                            doorOrderGrid.refresh();
                        }
                    });
                }
                }
            ]}
        ],
        toolbars:[
            {label:'删除', action:function () {
                var values = doorOrderGrid.getCheckBoxValues();
                if (values.length == 0) {
                    alert("请先选择记录!");
                    return false;
                }
                var objIdArr = [];
                $.each(values, function (idx, obj) {
                    objIdArr.push(obj.objId);
                })
                if (confirm("确定要删除？")) {
                    $.localAjax({
                        url:"/admin/doororder/batchDel",
                        data:{objIds:objIdArr.join(",")},
                        dataType:"json",
                        type:"post",
                        success:function (result) {
                            if (result.success == "1") {
                                doorOrderGrid.refresh();
                            }
                        }
                    })
                }
            }
            }
        ]
    });
    $("#addDoorOrder").click(function () {
        top.jq.workgroundManager.openPage({url:"/admin/doororder/addPage",
            onChanged:function () {
                doorOrderGrid.refresh();
            }
        });
    });
    $("#queryOk").click(function () {
        var formdata = $("#queryDoorOrderForm").serializeJson();
        doorOrderGrid.query({params:formdata});
    });

    $("#queryReset").click(function () {
        $("#queryDoorOrderForm").reSetForm();
    });


})