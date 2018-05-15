$(function () {
    //    左侧active切换
    $selectSubMenus('course');
    //新增弹窗
    $('.openPopup').click(function () {
        $('#cover').show();
    });
    $('.closeElePic').click(function () {
        $('#cover').hide();
    });
});

function getUnderLineClassInfo(){
    var id = $("#underLineId").val();
    if(id <= 0){
        $(".h5").html("新增线下课程");




    }else{
        $(".h5").html("线下课程信息");
    }
}


function fileChange(){
        console.log("changed "+new Date());
        $.ajax({
            url: rootPath+'/institutionClassType/uploadImgs',　　　　　　　　　　//上传地址
            type: 'POST',
            cache: false,
            data: new FormData($('#uploadForm')[0]),　　　　　　　　　　　　　//表单数据
            processData: false,
            contentType: false,
            success:function(json){
                console.log(json);
                if(json.status == 1){
                    $('#imgTop').html("<img src='"+json.url+"'  alt=\"\" style=\"width: 150px;height: 100px;margin-left: 15px;\">");
                  //  $('#imgTop').html("<img src='../../../images/institution/1.jpg'  alt=\"\" style=\"width: 150px;height: 100px;margin-left: 15px;\">");
                    $('#hidTop').val(json.picPath);
                }else{
                    $.msg(json.msg);
                }

            }
        });

}
