/**
*版权声明：贤俊工作室 版权所有 违者必究
*日    期： 2012-12-21
*作    者： 朱贤俊
*/
$(document).ready(function () {
    var articleType=$("#articleType").val();
    var doorArticleGrid = $.egrid({
        container:'listDoorArticleDiv', //放置表格的DIV
        url:'/admin/doorarticle/query', //表格数据获取的URL
        params:{articleType:articleType}, //数据查询条件
        sorts:{
        sortFieldName:'create_time',
        sortType:'desc'
        }, //排序条件
        pageSize:10, //每页展现数据数
        autoLoad:true, //是否表格创建完自动加载数据
        showTableIndex:false, //是否显示表格行索引
        keyColumn:'objId',
        columns:[
                {column:'articleCode', label:'文章编码', width:'50px'},
                {column:'articleTitle', label:'文章中文标题', width:'50px'},
                {column:'articleTitleSn', label:'文章英文标题', width:'50px'},
                {column:'orderNo', label:'序号', width:'10px'},
            {column:'func', label:'操作', align:'center', width:'50px', headerCls:"cmp_tanle_tdc", cellCss:"cmp_tanle_tdc", actions:[
            {label:'编辑', action:function (record, keyField, grid, colNo, rowNo, cell) {
                top.jq.workgroundManager.openPage({url:"/admin/doorarticle/updatePage?objId=" + record.objId,
                onChanged:function () {
                doorArticleGrid.refresh();
                }
                });
            }
            },
            {label:'查看', action:function (record, keyField, grid, colNo, rowNo, cell) {
                top.jq.workgroundManager.openPage({url:"/admin/doorarticle/detailPage?objId=" + record.objId,
                onChanged:function () {
                doorArticleGrid.refresh();
                }
                });
            }
            }
            ]}
        ],
        toolbars:[
        {label:'删除', action:function () {
            var values = doorArticleGrid.getCheckBoxValues();
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
                    url:"/admin/doorarticle/batchDel",
                    data:{objIds:objIdArr.join(",")},
                    dataType:"json",
                    type:"post",
                    success:function (result) {
                        if (result.success == "1") {
                        doorArticleGrid.refresh();
                    }
                }
                })
            }
          }
       }
        ]
    });
    $("#addDoorArticle").click(function () {
        top.jq.workgroundManager.openPage({url:"/admin/doorarticle/addPage/"+articleType,
        onChanged:function () {
              doorArticleGrid.refresh();
        }
        });
    });
    $("#queryOk").click(function () {
        var formdata = $("#queryDoorArticleForm").serializeJson();
        doorArticleGrid.query({params:formdata});
    });

    $("#queryReset").click(function () {
        $("#queryDoorArticleForm").reSetForm();
    });


})