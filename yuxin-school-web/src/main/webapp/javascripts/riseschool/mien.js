//学校风采
$(function () {
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
    //点击新增
    $('.addImg').click(function () {

    });


    // 弹窗中，取消、保存隐藏弹窗
    $('.mienHide').click(function () {
            $('.opacityPopup').fadeOut();
            $('.commonPopup').fadeOut();
            $('.coverPopup').fadeOut();
    });
    //弹出弹窗
    $('.mienShow').click(function () {
        $('.opacityPopup').fadeIn();
        $('.commonPopup').fadeIn();
        //标记不同的弹窗，为一个标志赋值表示不同的操作
        var windowFlag = '';
        if($(this).hasClass('coverChange')){
            windowFlag = '4';
            var updateId = $(this).attr("data-value");
            $("#updateId").val(updateId);
        }else if($(this).hasClass('coverAdd')){
            windowFlag = '3';
        }else if ($(this).hasClass('addImg')){
            windowFlag = '1';
        }else if ($(this).hasClass('imgChange')){
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
            $.init(sourceWidth, sourceHeight);
        } else {
            theImage.onload = function () {
                sourceHeight = theImage.height;
                sourceWidth = theImage.width;
                $.init(sourceWidth, sourceHeight);
            };
        };

    });

});

//上传临时图片
function savePic() {
    //选择的时候应先清空，
    $("#target").attr("src","");
    $.ajaxFileUpload({
        url : rootPath+"/riseSchoolStyle/upRiseSchoolStyleImg",
        secureuri : false,// 安全协议
        async : false,
        fileElementId : 'imgData',
        dataType:'json',
        type : "POST",
        success : function(data) {
            //显示图片
            // $("#sourcePic").attr("src",data.url);
            if (data.flag == 1){
                $("#target").attr("src",data.realPath);
                $("#target").trigger("change");
                $(".jcrop-holder").find("img").attr("src",data.realPath);
            }
        },
        error:function(arg1,arg2,arg3){
            //console.log(arg1);
        },
        loadingEle: '#target',
        fileName: 'imgData'
    });
}

//上传剪切图,返回真实地址并插入数据库中
function saveCutPic() {
    var windowFlag = $("#windowFlag").val();
    var id = $("#updateId").val();
    //上传截取后的图片
    $.ajax({
        url : rootPath + "/riseSchoolStyle/saveCutPic",
        data : {
            path : $("#target").attr("src"),
            x : $("#x").val(),
            y : $("#y").val(),
            w : $("#w").val(),
            h : $("#h").val(),
            imgDiscrible:$("#imgDiscrible").val(),
            riseSchoolId:$("#riseSchoolId").val(),
            windowFlag:windowFlag,
            updateId:id
        },
        type : "post",
        dataType : "json",
        success : function(data) {
            // chooseOnePic(data.picOriginalUrl,
            //     data.realPath);
            //上传成功则重新查询
            if (data.flag == 1){
                queryRiseSchoolStyle(1);
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
        },
        dataType: "html",
        success: function (data) {
            $(".imgList").html("").html(data);
        }
    });
    
}

//删除图片
function deleteRiseSchoolStyle(styleId) {
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