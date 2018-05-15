$(function () {
    //    左侧active切换
    $selectSubMenus('course');
    //新增弹窗
    $('.openPopup').click(function () {
        $("#hidCoverTop").val('');
        $('#hidCoverFid').val('');
        $('#cover').show();
    });
    /*$('.closeElePic').click(function () {
        $('#cover').hide();
    });*/

    $('.closeElePicCommit').click(function(){
        var imgUrl = $("#hidCoverTop").val();
        var id = $("#hidCoverFid").val();
        if(imgUrl == null || imgUrl == ''){
            $.msg('请上传图片');
            return;
        }

        if(id == null || id == '' ){
            var num = $("#styleContainer").find('li').length - 1;

            //新增风采
           var html = `
                    <li data-i="${num}">
                        <span><img src="${imgUrl}" alt="" style="width: 100%;height: auto"></span>
                        <input type="hidden"  />
                        <span class="imgInfo"></span>
                        <div class="listBg">
                        <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                           <a href="javascript:void(0)" data-i=""  class="btn btn-success alterBtn btn-sm openPopup">修改</a>
                        </div>
                    </li>
           `;
        }else{
            //修改风采
        }


    })


    $('.alterBtn').click(function(){
        console.log($(this).attr('data-i'));
        var baseNode = $(this).parent('div').parent('li') ;
        console.log($(baseNode).find('span').eq(0).find('img')[0].src);
        //获取span中的img的src
        var url = $(baseNode).find('span').eq(0).find('img')[0].src;
        console.log(url);
        $('#coverReturn').html("<img src='"+url+"' alt=\"\" style=\"width: 300px;height: 300px;\">");

        console.log($(baseNode).find('input').eq(0).val());
        $("#hidCoverTop").val($(baseNode).find('input').eq(0).val());
        $('#hidCoverFid').val($(this).attr('data-i'));


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
       // console.log("changed "+new Date());
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

function fileChangeCover(){
    $.ajax({
        url: rootPath+'/institutionClassType/uploadImgs',　　　　　　　　　　//上传地址
        type: 'POST',
        cache: false,
        data: new FormData($('#uploadForm')[0]),　　　　　　　　　　　　　//表单数据
        processData: false,
        contentType: false,
        success:function(json){
            if(json.status == 1){
                $('#coverReturn').html("<img src='"+json.url+"'  alt=\"\" style=\"width: 150px;height: 100px;margin-left: 15px;\">");
                $('#hidCoverTop').val(json.picPath);
            }else{
                $.msg(json.msg);
            }

        }
    });
}


function openChange(dom){

}

