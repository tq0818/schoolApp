$(function () {
    //选中二级菜单
    $selectSubMenu('classification');
    queryAllData(1);
    //关闭弹窗
    $('.closeAddType').click(function () {
        $('.addType').hide();
        $('.commonPopup').remove();
    });
    // 添加一级分类弹窗
    $('.addFirstBtn').click(function () {
        fillData("添加一级分类");
        $("#addConfirm").attr("onclick","addData();");
    });
    $(".btnFile").click(function(){
        $(".coverPopup").show();
    });
    //选择icon插件

    //隐藏
    $('.mienHide').click(function(){
        $('.coverPopup').hide();
        $("#targetStyle").attr("src","");
    });

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




    //一级弹窗详情
/*    $('.detailFirstPopupBtn').click(function () {
        fillData("一级分类详情");
    });*/
    //二级分类弹窗
/*    $('.addSeconPopupBtn').click(function () {
        fillData("添加二级分类");
    });*/
    //二级分类详情
/*    $('.detailSeconPopupBtn').click(function () {
        fillData("二级分类详情");
    });*/
});
/**
 * 查看详情
 * @param level
 */
function openDetails(level,updateId){
    //一级
    if('1'==level){
        fillData("一级分类详情");
    }else {//二级
        fillData("二级分类详情");
    }
    //绑定更新函数
    $("#addConfirm").attr("onclick","updatedata('2',"+updateId+",'')");
    //查询分类基本信息
    $.ajax({
        type:"POST",
        url: rootPath + "/insCateManage/querySingleInsCate/"+updateId,
        data: {},
        dataType: "json",
        success: function (data) {
            var result = data.result;
            //填充名称
            $("#insCatName").val(result.codeName);
            //TODO 填充图片
        }
    });


}

/**
 * 添加二级分类
 */
function openSecAdd(parentId){
    fillData("添加二级分类");
    $("#addConfirm").attr("onclick","addData("+parentId+");");
}

/**
 * 填充弹窗数据
 * @param tittle
 */
function fillData(tittle){
    $("#tittle").html("").html(tittle);
    //清理上次操作填充数据
    $("#insCatName").val('');
    //TODO 还需要新增部分清理数据操作
    $('.addType').show();
    $.commonPopup();
}


/**
 * 查询所有分类数据
 * @param pageNo
 */
function queryAllData(pageNo){
    $.ajax({
        type:"POST",
        url: rootPath + "/insCateManage/queryAllInsCate",
        data: {
            page:pageNo,
            pageSize:5
        },beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        dataType: "html",
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            $(".user-list").html("").html(data);
        }
    });
}
/**
 * 修改分类信息
 * flag
 * 1:更新使用禁用状态
 */
function updatedata(flag,id,enable){

    //获取一级id下所有二级id
    var ids = '';
    var codeName = '';
    if('1'==flag){

        if(!confirm("您确认禁用该分类?")){
            return;
        }

        $("#firtId_"+id).find("a").each(function(){
            ids+=$(this).attr("id").split("_")[0]+",";
        });
        ids+=id;
    }else{
        ids = id;
        codeName = $("#insCatName").val();
        // TODO 校验分类名称 长度5  只允许输入文本 标点包括英文状态下的'/.
        if(!codeName){
            alert("请输入分类名称");
            return;
        }
    }
    $.ajax({
        type:"POST",
        url: rootPath + "/insCateManage/updateInsCate",
        data: {
           flag:flag,
            ids:ids,
            enable:enable,
            codeName:codeName
        },
        dataType: "json",
        success: function (data) {
            if(data.flag=='1'){
                queryAllData($("#pageNo").val());
                hideTk();
            }else{
                if(flag=='1'){
                    $.msg("禁用失败请稍后再试");
                }
            }
        }
    });
}

/**
 * 添加分类信息
 * parentId is null the first
 * else the second
 */
function addData(parentId){

    var codeName = $("#insCatName").val();
    // TODO 校验分类名称 长度5  只允许输入文本 标点包括英文状态下的'/.
    if(!codeName){
        alert("请输入分类名称");
        return;
    }
    var imgUrl = '';
    $.ajax({
        type:"POST",
        url: rootPath + "/insCateManage/saveInsCate",
        data: {
            codeName:codeName,
            imgUrl:imgUrl,
            parentId:parentId
        },
        dataType: "json",
        success: function (data) {
            if(data.flag=='1'){
                queryAllData($("#pageNo").val());
                hideTk();
            }else{
                if(flag=='1'){
                    $.msg("禁用失败请稍后再试");
                }
            }
        }
    });
}

function hideTk(){
    $('.addType').hide();
    $('.commonPopup').remove();
}

/**
 * 保存临时图片
 * @param saveFlag
 */
function savePic() {
    //改变图片时清空图片路径
    $("#targetStyle").attr("src","");
    console.log(111);
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

/**
 * 返回切图真实路径
 * @param saveFlag
 */
function saveCutPic(saveFlag) {
    //判断图片是否为空或则是未更改就进行保存
    if (!$("#targetStyle").attr("src")){
        $.msg("未选择图片");
        return ;
    }
    //上传截取后的图片
    $.ajax({
        url : rootPath + "/insCateManage/saveCutPic",
        data : {
            path : ("#targetStyle").attr("src"),
            x : $("#x").val(),
            y : $("#y").val(),
            w : $("#w").val(),
            h : $("#h").val()
        },
        type : "post",
        dataType : "json",
        success : function(data) {
            //上传成功则重新查询
            if (data.flag == 1){
                //上传成功返回路径
                ("#target").attr("src",data.realPath);
            }else {
               // $.msg(data.msg);
            }
            ("#targetStyle").attr("src","");
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
        }
    })
}