// 学校详情
$(function () {
    var menuList = $('.headContent').find('li');
    //点击保存，获取menu的名称
    $('.btnSave').click(function () {
        for(var i= 0;i<menuList.length;i++){
            if(menuList.eq(i).hasClass('active')){
                console.log(menuList.eq(i).children('a').html());
            }
        }
    });
    //点击menu，切换class
    menuList.click(function () {
        $(this).addClass('active');
        $(this).siblings('li').removeClass('active');
    });
});