/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-03-10
*作    者： 朱贤俊
*/
$(document).ready(function () {
    var sysTestGrid = $.egrid({
        container:'listSysTestDiv', //放置表格的DIV
        url:'/admin/systest/query', //表格数据获取的URL
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
                {column:'menuName', label:'菜单名称', width:'36px'},
                {column:'menuCode', label:'菜单编码', width:'36px'},
                {column:'parentMenuId', label:'父菜单', width:'36px'},
                {column:'orderNo', label:'排序号', width:'10px'},
                {column:'menuType', label:'菜单类型', width:'10px'},
                {column:'nodeType', label:'节点类型', width:'36px'},
                {column:'url', label:'菜单链接', width:'36px'},
                {column:'icon', label:'节点图标', width:'36px'},
            {column:'func', label:'操作', align:'center', width:'50px', headerCls:"cmp_tanle_tdc", cellCss:"cmp_tanle_tdc", actions:[
            {label:'编辑', action:function (record, keyField, grid, colNo, rowNo, cell) {
                top.jq.workgroundManager.openPage({url:"/admin/systest/updatePage?objId=" + record.objId,
                onChanged:function () {
                sysTestGrid.refresh();
                }
                });
            }
            },
            {label:'查看', action:function (record, keyField, grid, colNo, rowNo, cell) {
                top.jq.workgroundManager.openPage({url:"/admin/systest/detailPage?objId=" + record.objId,
                onChanged:function () {
                sysTestGrid.refresh();
                }
                });
            }
            }
            ]}
        ],
        toolbars:[
        {label:'删除', action:function () {
            var values = sysTestGrid.getCheckBoxValues();
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
                    url:"/admin/systest/batchDel",
                    data:{objIds:objIdArr.join(",")},
                    dataType:"json",
                    type:"post",
                    success:function (result) {
                        if (result.success == "1") {
                        sysTestGrid.refresh();
                    }
                }
                })
            }
          }
       }
        ]
    });
    $("#addSysTest").click(function () {
        top.jq.workgroundManager.openPage({url:"/admin/systest/addPage",
        onChanged:function () {
              sysTestGrid.refresh();
        }
        });
    });
    $("#queryOk").click(function () {
        var formdata = $("#querySysTestForm").serializeJson();
        sysTestGrid.query({params:formdata});
    });

    $("#queryReset").click(function () {
        $("#querySysTestForm").reSetForm();
    });


})