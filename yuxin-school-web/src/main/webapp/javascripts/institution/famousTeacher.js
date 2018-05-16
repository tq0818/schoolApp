$(function () {
    //    左侧active切换
    $selectSubMenus('teacherFamous ');
    //点击删除弹窗提醒
    $('.delete').click(function () {
        $.confirm("是否确定删除该老师？",function (data) {
            if(data){

            }
        });
    });
});