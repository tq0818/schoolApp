/**
 *已上架课程
 **/
// 全选
$(function () {
    //全选
    $('.checkboxAll').click(function () {
       if($(this).prop('checked')){
           $('.signUpMany').prop('checked',true);
       }else {
           $('.signUpMany').prop('checked',false);
       }
    });
    //关闭弹窗
    $('.closePopupContainer').click(function () {
        $('.popupContainer').hide();
        $('.popupOpacity').hide();
    });
    //点击编辑打开弹窗
    $('.eidtShelvesCourses').click(function () {
        $('.popupContainer').show();
        $('.popupOpacity').show();
    });
});