$(function () {
    //    左侧active切换
    $selectSubMenus('course');

});

function getUnderLineClassInfo(){
    var id = $("#underLineId").val();
    if(id <= 0){
        $(".h5").html("新增线下课程");




    }else{
        $(".h5").html("线下课程信息");
    }
}