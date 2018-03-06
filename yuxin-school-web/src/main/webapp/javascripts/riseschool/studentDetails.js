//通过弹窗
$('.pass').click(function () {
    $('.opacityPopup').show();
    $('.confirmPopup').show();
    return false;
});
//隐藏弹窗
$('.hidePopup').click(function () {
    $('.opacityPopup').hide();
    $('.confirmPopup').hide();
    $('.reason').hide();
});
//不通过弹窗
$('.noPass').click(function () {
    $('.opacityPopup').show();
    $('.reason').show();
    return false;
});

//点击图片放大
$('.clickImg').click(function () {
    $('.bigImage').show();
    $('.opacityPopup').show();
    return false;
});
//点击其他地方关闭大图
$(document).click(function () {
    $('.bigImage').hide();
    $('.opacityPopup').hide();
});
