$(function () {
    //    左侧active切换
    $selectSubMenus('evaluate');
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
    //tab切换
    $('.evaTitle').mouseover(function () {
        $(this).addClass('active');
        $(this).siblings('span').removeClass('active');
    });
    //筛选样式切换
    $('.evaScreen').children('div').children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
    });
    //机构评价和课程评价tab切换
    $('.evaTitle').click(function () {
        if($(this).html()=='机构评价'){
            $('.evaScreen').show();
            $('.curriculum').hide();
        }else {
            $('.evaScreen').hide();
            $('.curriculum').show();
        }
    });
    //删除按钮弹窗
    $('.delete').click(function () {
        $.confirm("您是否确定删除该条评论？",function (data) {
            if(data){
                console.log("删除成功");

            }
        });
    });
});