//动态
$(function () {
    //点击修改
    $('.change').click(function () {
        $('.opacityPopup').fadeIn();
        $('.changeNews').fadeIn();
    });
    //点击确定
    $('.addNewsBtnSave').click(function () {
        $('.opacityPopup').fadeOut();
        $('.addNews').fadeOut();
    });
    //点击取消
    $('.addNewsBtnCancel').click(function () {
        $('.opacityPopup').fadeOut();
        $('.addNews').fadeOut();
    });
    //点击查看
    $('.check').click(function () {
        $('.opacityPopup').fadeIn();
        $('.checkNews').fadeIn();
    });
    //点击删除
    $('.delete').click(function () {
        $.confirm('是否删除该条动态？',function (data) {
            if(data){
                console.log('已删除');
            }
        })
    });
});