$(function(){
    /**
     * btn显示隐藏
     */
    $('body').on('mouseover',' #styleContainer li',function () {
        $(this).children('.listBg').show();
    });
    $('body').on('mouseleave',' #styleContainer li',function () {
        $(this).children('.listBg').hide();
    });

    /**
     *初始化截图
     */
    $(".uploadImageStyle").on("change","#targetStyle", function() {

        var imgType = $("#imgType").val();

        var theImage = new Image();
        theImage.src = $(this).attr("src");
        if (theImage.complete) {
            sourceHeight = theImage.height;
            sourceWidth = theImage.width;
            $.init(sourceWidth, sourceHeight,imgType?3:4);
        } else {
            theImage.onload = function () {
                sourceHeight = theImage.height;
                sourceWidth = theImage.width;
                $.init(sourceWidth, sourceHeight,imgType?3:4);
            };
        };

    });

    /**
     * 隐藏及销毁截图
     */
        //隐藏
    $('.mienHide').click(function(){
        $('.coverPopup').hide();
        $("#targetStyle").attr("src","").attr("style","");
        $('.commonPopup').hide();
        if (jcrop_apis){
            jcrop_apis.destroy();
        }
    });
});


function addCourseListener(){
    //新增弹窗
    $('.openPopup').click(function () {
        var num = $("#styleContainer").find('li').length - 1;
        if (num >= MAX_STYLE && !$(this).hasClass('alterBtn')) {
            $.msg('已超过课程风采数量');
            return;
        }
        $("#hidCoverTop").val('');
        $('#hidCoverFid').val('');
        $('#hidCoverSort').val('');
        $('#coverReturn').html('');
        popAddImg();
        //$('#cover').show();
    });

    //弹出层取消按钮事件
    $('.closeElePicCancel').click(function () {
        $('#cover').hide();
    });

    //弹出层确定按钮事件
    $('.closeElePicCommit').click(function () {
        var imgUrl = $("#hidCoverTop").val();
        var id = $("#hidCoverFid").val();
        var index = $('#hidCoverSort').val();
        if (imgUrl == null || imgUrl == '') {
            $.msg('请上传图片');
            return;
        }

        if ( (id == null || id == '') && index == '' ) {
            //新增风采
            var num = $("#styleContainer").find('li').length - 1;
            if (num >= MAX_STYLE) {
                $.msg('已超过课程风采数量');
                return;
            }
            var showImg = $('#coverReturn').html();
            var subscript = null;

            //新增风采
            var html = `
                    <li data-i="${num}">
                        <span>${showImg}</span>
                        <input type="hidden" value="${imgUrl}"  />
                        <span class="imgInfo"></span>
                        <div class="listBg">
                        <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn deleteBtnStyle">删除</a>
                           <a href="javascript:void(0)" data-id=""  class="btn btn-success alterBtn btn-sm openPopup">修改</a>
                        </div>
                    </li>
           `;

            $("#styleContainer").append(html);

            $('#fileUploadInput').val('');

            $('#cover').hide();

            //添加完节点后添加监听器
            $('.deleteBtnStyle').click(function () {
                var dom = this;
                $.confirm('是否确定删除该风采图片?',function (data) {
                    if(data){
                        $(dom).parent('div').parent('li').remove();
                        //刷新li的data-i
                        flushLiDataI();
                    }
                })
            })

            $('.alterBtn').click(function () {
                //获取当前元素的li根节点
                var baseNode = $(this).parent('div').parent('li');
                //获取span中的img的src
                var url = $(baseNode).find('span').eq(0).find('img')[0].src;
                //弹出层中显示图片
                $('#coverReturn').html("<img src='" + url + "' alt=\"\" style=\"width: 100%; max-height: 260px;\">");
                //保存图片的相对路径
                $("#hidCoverTop").val($(baseNode).find('input').eq(0).val());
                //保存映射id
                $('#hidCoverFid').val($(this).attr('data-id'));
                //保存当前修改的是哪一个li，用于点击确认的时候更新数据
                $('#hidCoverSort').val($(baseNode).eq(0).attr('data-i'));
                $('#cover').show();
            });

            //刷新li的data-i
            flushLiDataI();

        } else {
            //修改风采
            //console.log('执行修改风采...');
            var i = $('#hidCoverSort').val();

            $("#styleContainer").find('li').eq(parseInt(i) + 1).find('span').eq(0).html($('#coverReturn').html())
            $("#styleContainer").find('li').eq(parseInt(i) + 1).find('input').eq(0).val($("#hidCoverTop").val())

            $('#cover').hide();


        }


    })

//监听课程人数限定监听器
    $(":radio").click(function () {
        if ($(this).val() == 1) {
            $('#limitContainer').show();
        } else {
            $('#limitContainer').hide();
        }
    })

    //
    //添加限制人数监听器，>= 0
    $('#classPersonLimit').bind('input propertychange', 'input' , function(){
        personNumLimit();
    })


    //添加价格输入监听器
    $('#classPrice').bind('input propertychange', 'input' , function(){
        priceLimit();
    })

    // 课程名称事件监听
    /*$('#className').bind('input propertychange', 'input' , function(){
        classNameLimit();
    })
*/



    //课程说明事件监听
   /* $('textarea').bind('input propertychange', function(){
        var val = $('#classDetail').val();
        //不能输入全是空格的字符串
        if(trim(val) == ''){
            $('#classDetail').val('');
            return;
        }

        //字符串末尾连续多个空格
        if(val.length - trim(val).length > 1){
            $('#classDetail').val(val.substr(0,val.length - 1));
            return;
        }

        if(!nameTest.test(trim(val))){
            $('#classDetail').val(val.substr(0,val.length - 1));
        }
    })*/

    //添加课程标签
    $('.addSystemBtn').click(function () {
        var len = $('#labelContainer').find('.systemBtn').length;
        if (len >= MAX_LABEL) {
            $(this).hide();
        } else {
            var html = `
                        <span href="##" class="systemBtn">
                             <input class="systemLabel" maxlength="5" label-id="" value="">
                             <i class="icon iconfont deleteBtn deleteLabelBtn">&#xe610;</i>
                        </span>
                       
             `;
            $('#spanContainerLabel').append(html);

            if (len + 1 >= MAX_LABEL) {
                $(this).hide();
            }


            $('.deleteLabelBtn').click(function () {
                $(this).parent('span').remove();
                var len = $('#labelContainer').find('.systemBtn').length;
                if (len < MAX_LABEL) {
                    $('.addSystemBtn').show();
                }

            })

        }
    })


    //删除风采图片事件监听
    $('.deleteBtnStyle').click(function () {
        var dom = this;
        $.confirm('是否确定删除该风采图片?',function (data) {
            if(data){
                $(dom).parent('div').parent('li').remove();
                //刷新li的data-i
                flushLiDataI();
            }
        })
    })

    $('.alterBtn').click(function () {
        //获取当前元素的li根节点
        var baseNode = $(this).parent('div').parent('li');
        //获取span中的img的src
        var url = $(baseNode).find('span').eq(0).find('img')[0].src;
        //弹出层中显示图片
        $('#coverReturn').html("<img src='" + url + "' alt=\"\" style=\"width: 100%; max-height: 260px;\">");
        //保存图片的相对路径
        $("#hidCoverTop").val($(baseNode).find('input').eq(0).val());
        //保存映射id
        $('#hidCoverFid').val($(this).attr('data-id'));
        //保存当前修改的是哪一个li，用于点击确认的时候更新数据
        $('#hidCoverSort').val($(baseNode).eq(0).attr('data-i'));
        $('#cover').show();
    });

    //课程标签删除监听器
    $('.deleteLabelBtn').click(function () {
        $(this).parent('span').remove();
        var len = $('#labelContainer').find('.systemBtn').length;
        if (len < MAX_LABEL) {
            $('.addSystemBtn').show();
        }

    })

    //取消按钮监听
    $('.closeMechanismCancel').click(function(){

        $(".loading").show();
        $(".loading-bg").show();
       // window.history.back()
       window.location.href = rootPath+'/institutionClassType/classTypeMain/'+$("#insId").val();

    })


}


function  clearCourseListenner(){
    $('.openPopup').unbind();
    $('.closeElePicCancel').unbind();
    $('.closeElePicCommit').unbind();
    $('.deleteLabelBtn').unbind();
    $('.addSystemBtn').unbind();
    $('textarea').unbind();
    $('#className').unbind();
    $('#classPrice').unbind();
    $(":radio").unbind();
    $('.deleteBtn').unbind();
    $('.alterBtn').unbind();
    $('.deleteLabelBtn').unbind();
}

//处理获取的图片像素
function dealWidthAndHeight(temp){

    var w = 0;
    var h = 0;
    for(var i=0;i<temp.length;i++){
        if(temp[i].indexOf("width")!=-1){
            w = temp[i].split(":")[1].replace("px","");
        }
        if(temp[i].indexOf("height")!=-1){
            h = temp[i].split(":")[1].replace("px","");
        }
    }
    if (parseFloat($("#w").val()) > parseFloat(w)){
        $("#w").val(w);
    }
    if (parseFloat($("#h").val()) > parseFloat(h)){
        $("#h").val(h);
    }
}

/**
 * 返回切图真实路径
 * @param saveFlag
 */
function saveCutPic() {
    //判断图片是否为空或则是未更改就进行保存
    if (!$("#targetStyle").attr("src")){
        $.msg("未选择图片");
        return ;
    }
    var temp = $("#targetStyle").attr("style").split(";");
    //处理剪切框的宽高
    dealWidthAndHeight(temp);
    var imgType = $("#imgType").val();
   // console.log($("#targetStyle").attr("src"));
    //上传截取后的图片
    $.ajax({
        url : rootPath + "/insCateManage/saveCutPic",
        data : {
            path :$("#targetStyle").attr("src"),
            x : $("#x").val(),
            y : $("#y").val(),
            w : $("#w").val(),
            h : $("#h").val(),
            insFlag:imgType?3:4
        },
        type : "post",
        dataType : "json",
        success : function(data) {
            //上传成功则重新查询
            if (data.flag == 1){
                //上传成功返回路径

                if(imgType){
                    $('#imgTop').html("<img src='" +data.header+data.realPath + "'  alt=\"\" style=\"width: 150px;height: auto;margin-left: 15px;\">");
                    $('#hidTop').val(data.realPath);
                    $('.addPicFace').html('更换封面');
                }else{
                    $('#coverReturn').html("<img src='" +data.header+data.realPath + "'  alt=\"\" style=\"width: 100%; height: auto;\">");
                    $('#hidCoverTop').val(data.realPath);
                    $('.closeElePicCommit').click();
                }
            }else {
                 //$.msg(data.msg);后台没有定义msg
            }
            $("#targetStyle").attr("src","");
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
        }
    })
}

function popAddImg(imgType){
    $(".coverPopup").show();
    $("#imgType").val(imgType);
    $.commonPopup();
    if(imgType){
        $("#imgTittle").html("上传课程封面图-建议上传图片尺寸为180*120px");
    }else{
        $("#imgTittle").html("上传课程风采图-建议上传图片尺寸为750*300px");
    }
}

function savePic() {
    //改变图片时清空图片路径
    $("#targetStyle").attr("src","");
    if (jcrop_apis){
        jcrop_apis.destroy();
    }
   // console.log($("#targetStyle").attr("src"));
    var fileStr = $("#imgDataStyle").val();
    var  lowwerFileStr =  fileStr.toLowerCase();
    //.jpg,.jpeg,.gif,.png,.bmp,.ico
    if(!(lowwerFileStr.indexOf(".jpg")==(fileStr.length-4)
            ||lowwerFileStr.indexOf(".jpeg")==(fileStr.length-5)

            ||lowwerFileStr.indexOf(".png")==(fileStr.length-4)
            ||lowwerFileStr.indexOf(".bmp")==(fileStr.length-4)
            ||lowwerFileStr.indexOf(".ico")==(fileStr.length-4)
        )){
        alert("上传文件仅支持以下格式:.jpg,.jpeg,.png,.bmp,.ico");
       //closeMechanismCancel
        $("#imgDataStyle").val('');
        /*if(!$("#targetStyle").attr("src")){

        }*/

        return;
    }
    $.ajaxFileUpload({
        url : rootPath+"/riseSchoolStyle/upRiseSchoolStyleImg",
        secureuri : false,// 安全协议
        async : false,
        fileElementId :'imgDataStyle',
        dataType:'json',
        type : "POST",
        success : function(data) {
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
            if (data.flag == 1){
                $("#targetStyle").attr("src",data.realPath);
                $("#targetStyle").trigger("change");
                $(".jcrop-holder").find("img").attr("src",data.realPath);
            }
        },
        error:function(arg1,arg2,arg3){

        }
    });
}