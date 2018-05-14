$(function () {
    //选中二级菜单
    $selectSubMenu('organizationIndex');
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
    //添加备注弹窗     //点击备注，弹出弹窗
    $('.addRemarks').click(function () {
        $('.remarks').show();
    });
    $('.remarksBtn').children('a').click(function () {
        $('.remarks').hide();
    });
    //tab切换
    $('.changeBtn').children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
    });

});