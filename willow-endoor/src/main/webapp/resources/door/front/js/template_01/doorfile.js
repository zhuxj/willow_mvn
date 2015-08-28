/**
 *版权声明：厦门中图壹购信息技术有限公司 版权所有 违者必究 2013
 *日    期： 13-1-6
 *作    者： 朱贤俊
 */
$(document).ready(function () {
    init();
    function init() {
        self._page = $.paginationPlug({pageDivId:"pager", itemsPerPage:20, showPageNos:7, maxPages:200, cssStyle:"2", pageCallback:pageCallBack});
        var totalCount = $("#totalCount").val().replace(",", "");
        self._page.showPagination(parseInt(totalCount, 10), parseInt($("#pageNo").val(), 10));
    }

    function pageCallBack(pageNo) {
        setFeildAndSearch("pageNo", pageNo);
        return false;
    }

    function setFeildAndSearch(id, val) {
        $("#" + id).val(val);
        $("#searchForm").submit();
    }

})