/**
 *已上架课程
 **/
// 全选
$(function () {
    $('.checkboxAll').click(function () {
       if($(this).prop('checked')){
           $('.signUpMany').prop('checked',true);
       }else {
           $('.signUpMany').prop('checked',false);
       }
    })
});