/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2013-01-11
*作    者： 朱贤俊
*/
$(document).ready(function () {
    var fileCatalogGrid = $.egrid({
        container:'listFileCatalogDiv', //放置表格的DIV
        url:'/admin/filecatalog/query', //表格数据获取的URL
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
                {column:'catalogName', label:'分类名称', width:'50px'},
                {column:'catalogNameEn', label:'分类英文名称', width:'50px'},
            {column:'func', label:'操作', align:'center', width:'50px', headerCls:"cmp_tanle_tdc", cellCss:"cmp_tanle_tdc", actions:[
            {label:'编辑', action:function (record, keyField, grid, colNo, rowNo, cell) {
                top.jq.workgroundManager.openPage({url:"/admin/filecatalog/updatePage?objId=" + record.objId,
                onChanged:function () {
                fileCatalogGrid.refresh();
                }
                });
            }
            },
            {label:'查看', action:function (record, keyField, grid, colNo, rowNo, cell) {
                top.jq.workgroundManager.openPage({url:"/admin/filecatalog/detailPage?objId=" + record.objId,
                onChanged:function () {
                fileCatalogGrid.refresh();
                }
                });
            }
            }
            ]}
        ],
        toolbars:[
        {label:'删除', action:function () {
            var values = fileCatalogGrid.getCheckBoxValues();
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
                    url:"/admin/filecatalog/batchDel",
                    data:{objIds:objIdArr.join(",")},
                    dataType:"json",
                    type:"post",
                    success:function (result) {
                        if (result.success == "1") {
                        fileCatalogGrid.refresh();
                    }
                }
                })
            }
          }
       }
        ]
    });
    $("#addFileCatalog").click(function () {
        top.jq.workgroundManager.openPage({url:"/admin/filecatalog/addPage",
        onChanged:function () {
              fileCatalogGrid.refresh();
        }
        });
    });
    $("#queryOk").click(function () {
        var formdata = $("#queryFileCatalogForm").serializeJson();
        fileCatalogGrid.query({params:formdata});
    });

    $("#queryReset").click(function () {
        $("#queryFileCatalogForm").reSetForm();
    });


})