//学校风采
$(function () {
    //点击置顶
    $('.imgTop').click(function () {
        if($(this).html()=='置顶'){
            $(this).html('取消置顶');
            $(this).parents('div').siblings('.rightShow').fadeIn();
        }else {
            $(this).html('置顶');
            $(this).parents('div').siblings('.rightShow').fadeOut();
        }
    });
    // 点击删除
    $('.imgDelete').click(function () {
        $.confirm('确认删除？',function (status) {
            if(status){
                $.msg("删除成功！");
            }
        })
    });
    //点击新增
    $('.addImg').click(function () {

    });


    // 弹窗中，取消、保存隐藏弹窗
    $('.mienHide').click(function () {
            $('.opacityPopup').fadeOut();
            $('.mienPopup').fadeOut();
    });
    //弹出弹窗
    $('.mienShow').click(function () {
        $('.opacityPopup').fadeIn();
        $('.mienPopup').fadeIn();
    });
    //封面hover效果
    $('.imgList li').mouseover(function () {
        $(this).children('.imgInfo').show();
        $(this).children('.listBg').show();
    });
    $('.imgList li').mouseleave(function () {
        $(this).children('.imgInfo').hide();
        $(this).children('.listBg').hide();
        return false;
    });

});