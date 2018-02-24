//学校风采
$(function () {

    //样式一和样式二的换
    $('#styleBtn a').click(function () {
        console.log(1);
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
        //点击时改变图片裁剪框
        var url = $("#target").attr("src");
        jcrop_apis.destroy();
        $("#target").trigger("change");
        $(".jcrop-holder").find("img").attr("src",url);
        $("#imgData").removeAttr("type").attr("type","file");
    });
    //点击置顶
    $('.imgTop').click(function () {
        if($(this).html()=='置顶'){
            $(this).html('取消置顶');
            $(this).parents('div').siblings('.rightShow').fadeIn();
        }else {
            $(this).html('置顶');
            $(this).parents('div').siblings('.rightShow').fadeOut();
        }
    });
    // 点击删除
    $('.imgDelete').click(function () {
        $.confirm('确认删除？',function (status) {
            if(status){
                $.msg("删除成功！");
            }
        })
    });

    // 弹窗中，取消、保存隐藏弹窗
    $('.mienHide').click(function () {
        $('.opacityPopup').fadeOut();
        $('.commonPopup').fadeOut();
        $('.coverPopup').fadeOut();
    });
    //弹出弹窗
    $('.mienShow').click(function () {
        //点击时，清空之前的图片
        $('.opacityPopup').fadeIn();
        $('.commonPopup').fadeIn();

        //标记不同的弹窗，为一个标志赋值表示不同的操作
        if (jcrop_apis){
            jcrop_apis.destroy();
        }
        var windowFlag = '';
        if ($(this).hasClass('addImg')){
            windowFlag = '1';
            $(".uploadImage").find("img").attr("src","/manage/images/1.jpg");
            $("#imgDiscrible").val('');
        }else if ($(this).hasClass('imgChange')){
            $(".uploadImage").find("img").attr("src",$(this).parent(".listBg").siblings("img").attr("src")).attr("style","");
            //横图
            if($(this).attr("imgType")=="2"){
                $(".uploadImage").find("img").attr("style","width: 300px;height: auto;");
            }else{
                $(".uploadImage").find("img").attr("style","width: auto;height: 300px;");
            }
            $("#imgDiscrible").val($(this).parent(".listBg").siblings("span").text());
            windowFlag = '2';
            var updateId = $(this).attr("data-value");
            $("#updateId").val(updateId);
        }
        $("#windowFlag").val(windowFlag);
    });
    //封面图片弹窗
    $('.coverShow').click(function () {
        $('.opacityPopup').fadeIn();
        $('.coverPopup').fadeIn();
        var windowFlag = '';
        //封面图标记窗口
        if($(this).hasClass('coverChange')){
            $(".uploadImageStyle").find("img").attr("src",$(".coverImg").find("img").attr("src")).attr("style","").attr("style","width: 400px;height: auto;");
            windowFlag = '4';
            var updateId = $(this).attr("data-value");
            $("#updateId").val(updateId);
        }else if($(this).hasClass('coverAdd')){
            windowFlag = '3';
        }
        $("#windowFlag").val(windowFlag);
    });


    //封面hover效果
    $('.imgList li').mouseover(function () {
        $(this).children('.imgInfo').show();
        $(this).children('.listBg').show();
    });
    $('.imgList li').mouseleave(function () {
        $(this).children('.imgInfo').hide();
        $(this).children('.listBg').hide();
        return false;
    });

    //剪切图
    $(".uploadImage").on("change","#target", function() {
        var theImage = new Image();
        console.log($(this).attr("src"));
        theImage.src = $(this).attr("src");
        if (theImage.complete) {
            sourceHeight = theImage.height;
            sourceWidth = theImage.width;
            $.init(sourceWidth, sourceHeight,1);
        } else {
            theImage.onload = function () {
                sourceHeight = theImage.height;
                sourceWidth = theImage.width;
                $.init(sourceWidth, sourceHeight,1);
            };
        };

    });

    //剪切图
    $(".uploadImageStyle").on("change","#targetStyle", function() {
        var theImage = new Image();
        console.log($(this).attr("src"));
        theImage.src = $(this).attr("src");
        if (theImage.complete) {
            sourceHeight = theImage.height;
            sourceWidth = theImage.width;
            $.init(sourceWidth, sourceHeight,2);
        } else {
            theImage.onload = function () {
                sourceHeight = theImage.height;
                sourceWidth = theImage.width;
                $.init(sourceWidth, sourceHeight,2);
            };
        };

    });

});

//上传临时图片 1为风采 2为封面
function savePic(saveFlag) {
    //选择的时候应先清空，
    if (saveFlag == 1){
        $("#target").attr("src","");
    }else {
        $("#targetStyle").attr("src","");
    }
    // $(".jcrop-holder").attr("style","display:block");
    // $($($(".jcrop-holder").find("div")[0]).find("div")[0]).hide();
    $.ajaxFileUpload({
        url : rootPath+"/riseSchoolStyle/upRiseSchoolStyleImg",
        secureuri : false,// 安全协议
        async : false,
        fileElementId : saveFlag == 1?'imgData':'imgDataStyle',
        dataType:'json',
        type : "POST",
        success : function(data) {
            //显示图片
            // $("#sourcePic").attr("src",data.url);
            if (data.flag == 1){
                if (saveFlag == 1){
                    $("#target").attr("src",data.realPath);
                    $("#target").trigger("change");
                    $(".jcrop-holder").find("img").attr("src",data.realPath);
                    $("#imgData").removeAttr("type").attr("type","file");
                }else {
                    $("#targetStyle").attr("src",data.realPath);
                    $("#targetStyle").trigger("change");
                    $(".jcrop-holder").find("img").attr("src",data.realPath);
                }
            }
        },
        error:function(arg1,arg2,arg3){
            //console.log(arg1);
        },
        saveFlag:saveFlag
        // loadingEle:  saveFlag == 1?'#target':'#targetStyle',
        // fileName: 'imgData'
    });
}

//上传剪切图,返回真实地址并插入数据库中
function saveCutPic(saveFlag) {
    var windowFlag = $("#windowFlag").val();
    var id = $("#updateId").val();
    //上传截取后的图片
    $.ajax({
        url : rootPath + "/riseSchoolStyle/saveCutPic",
        data : {
            path : saveFlag == 1?$("#target").attr("src"):$("#targetStyle").attr("src"),
            x : $("#x").val(),
            y : $("#y").val(),
            w : $("#w").val(),
            h : $("#h").val(),
            imgDiscrible:$("#imgDiscrible").val(),
            riseSchoolId:$("#riseSchoolId").val(),
            windowFlag:windowFlag,
            updateId:id,
            cssStyle:$("#btnOne").hasClass("btn-primary")?0:1
        },
        type : "post",
        dataType : "json",
        success : function(data) {
            // chooseOnePic(data.picOriginalUrl,
            //     data.realPath);
            //上传成功则重新查询
            if (data.flag == 1){
                queryRiseSchoolStyle(1);
                if(saveFlag==1){
                    $("#target").attr("src","");
                }else{
                    $("#targetStyle").attr("src","");
                }
            }else {
                $.msg(data.msg);
            }
        }
    })
    $("#chooseDiv").css("display", "none");
    $("#stopDiv").css("display", "none");
    return;
}

//查询学校风采信息
function queryRiseSchoolStyle(pageNo) {
    $.ajax({
        url: rootPath + "/riseSchoolStyle/queryRiseSchoolStyle",
        data: {"page":pageNo,
            "pagesize":6,
            "riseSchoolId":$("#riseSchoolId").val(),
            "isCover":0
        },beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        dataType: "html",
        success: function (data) {
            $(".loading").hide();
            $(".loading-bg").hide();
            $(".imgList").html("").html(data);
        }
    });

}

//删除图片
function deleteRiseSchoolStyle(styleId) {
    if (!confirm("是否确认删除该图片")){
        return ;
    }
    $.ajax({
        url: rootPath + "/riseSchoolStyle/deleteRiseSchoolStyle",
        data: {id:styleId
        },
        dataType: "json",
        success: function (data) {
            if (data.flag == 1){
                //刷新
                $.msg(data.msg);
                queryRiseSchoolStyle(1);
            }else {
                $.msg(data.msg);
            }
        }
    });
}

//更新置顶
function updateIsTop(styleId,isTop) {
    $.ajax({
        url: rootPath + "/riseSchoolStyle/updateRiseSchoolStyle",
        data: {id:styleId,
            isTop:isTop
        },
        dataType: "json",
        success: function (data) {
            if (data.flag == 1){
                //刷新
                $.msg(data.msg);
                queryRiseSchoolStyle(1);
            }else {
                $.msg(data.msg);
            }
        }
    });
}

