$(function () {
    //选中二级菜单
    $selectSubMenu('classification');
    //分页插件
    $(".pagination").pagination('', {
        next_text : "下一页",
        prev_text : "上一页",
        current_page : 1,
        link_to : "javascript:void(0)",
        num_display_entries : 8,
        items_per_page : 12,
        num_edge_entries : 1,
        callback:function(page){
            var pageNo = page + 1;

        }
    });
    //关闭弹窗
    $('.closeAddType').click(function () {
        $('.addType').hide();
        $('.commonPopup').remove();
    });
    // 添加一级分类弹窗
    $('.addFirstBtn').click(function () {
        $('.addFirstPopup').show();
        $.commonPopup();
    });
    //一级弹窗详情
    $('.detailFirstPopupBtn').click(function () {
        $('.detailFirstPopup').show();
        $.commonPopup();
    });
    //二级分类弹窗
    $('.addSeconPopupBtn').click(function () {
        $('.addSeconPopup').show();
        $.commonPopup();
    });
    //二级分类详情
    $('.detailSeconPopupBtn').click(function () {
        $('.detailSeconPopup').show();
        $.commonPopup();
    });



});