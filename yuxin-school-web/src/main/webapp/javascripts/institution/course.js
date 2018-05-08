$(function () {
    //分页
    $(".pagination").pagination('',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page:'',
            link_to: "",
            num_display_entries: 6,
            items_per_page: 6,
            num_edge_entries: 1,
            callback: function (page) {

            }
        }
    );
    //    左侧active切换
    $selectSubMenus('course');
    //图片移入显示隐藏的按钮和文字
    $('.imgList').find('li').mouseover(function () {
        $(this).children('.imgInfo').show();
        $(this).children('.listBg').show();
    });
    $('.imgList').find('li').mouseleave(function () {
        $(this).children('.imgInfo').hide();
        $(this).children('.listBg').hide();
    });
    //筛选
    $('.chooseBtn').children('a').click(function () {
        if(!$(this).hasClass('btn-primary')){
            $(this).addClass('btn-primary').siblings('a').removeClass('btn-primary');
        }
    });
});