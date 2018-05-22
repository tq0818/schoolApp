var jcrop_apis;
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
        $("#targetStyle").attr("src","").attr("style","");
        if (jcrop_apis){
            jcrop_apis.destroy();
        }
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



    //初始化切图函数

    var boundx, boundy, $preview, $preview2, $preview3, $pcnt, $pcnt2, $pcnt3, $pimg, $pimg2, $pimg3, $img,
        xsize, xsize2, xsize3, ysize, ysize2, ysize3, minHeight, maxHeight, minWidth, maxWidth, $scale, sourceHeight, sourceWidth;
    $.init=function(initW,initH,picFlag) {
        $img = $("#targetStyle");

        //初始化比列
        $scale = 200/200;
        maxHeight = 200;
        maxWidth = 200;
        minHeight = 20;
        minWidth = 20;

        sourceWidth=initW;
        sourceHeight=initH;
        var initSize=resizePic();
        var jcrop_api;
        var scale = parseInt(sourceWidth) / parseInt(sourceHeight)

        jc=$img.Jcrop({
            onChange : showCoords,
            onSelect : showCoords,
            onRelease: clearCoords,
            aspectRatio : $scale,
            allowMove : true,
            bgColor : "#f2f2f2",
            borderOpacity : 0.4,
            maxSize : [ initSize.w, initSize.h ],
            minSize : [ minWidth, minHeight ],
            bgFade : true,
            allowSelect : false,
            allowResize : true,
            sideHandles : false
        }, function() {
            jc=jcrop_api = this;
            var bounds = this.getBounds();
            console.log(bounds);
            boundx = bounds[0];
            boundy = bounds[1];
            var scale = parseInt(sourceWidth) / parseInt(sourceHeight);// 长宽比例
            var size = resizePic();
            if (scale > $scale) {
                jcrop_api.animateTo([0,0,boundx*$scale,boundy*$scale],function () {

                })
            } else {
                jcrop_api.animateTo([0,0,boundx*scale,boundy*scale],function () {

                })

            }
        });
        jcrop_apis = jcrop_api;

    }

    function showCoords(c) {
        $('#x').val(c.x);
        $('#y').val(c.y);
        $('#w').val(c.w);
        $('#h').val(c.h);
    }

    function clearCoords() {
        $('#x').val('');
        $('#y').val('');
        $('#w').val('');
        $('#h').val('');
    }

    function resizePic() {
        var h, w, ml, mt;
        var scale = parseInt(sourceWidth) / parseInt(sourceHeight);// 长宽比例
        if (scale > $scale) {
            // 过宽,宽为100%，高按比例缩
            h = maxWidth * sourceHeight/ sourceWidth;
            w = maxWidth;
            ml = 0;
            mt = (maxHeight - h) / 2;
            $img.css("height", h+"px").css("width", w+"px");
            // 改左侧图大小
            $('.jcrop-holder').find("img").css("height", h + "px").css("width", w + "px");

        } else {
            // 过高,高为100%，宽按比例缩
            h = maxHeight;
            w = maxHeight * sourceWidth/sourceHeight;
            ml = (maxWidth - w) / 2;
            mt = 0;
            $img.css("height", h+"px").css("width", w+"px");
            // 改左侧图大小
            $('.jcrop-holder').find("img").css("height", h + "px").css("width", w + "px");

        }
        var c = {};
        c.w = w;
        c.h = h;
        c.ml = ml;
        c.mt = mt;
        return c;
    }

    $(document).ready(function() {
//		$.init(500,280);
    })

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
            $("#target").attr("src",result.imgUrl);
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
    $("#imgUrl").val('');
    $("#target").attr("src","");
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
            pageSize:12
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

/*         if(!confirm("您确认禁用该分类?")){
             return;
         }*/

        var msg = '';
        if('1'==enable){
            msg = '您确认禁用该分类?';
        }else{
            msg = '您确认启用该分类?';
        }

        $.confirm(msg,function(b){
            if(b){
                $("#firtId_"+id).find("a").each(function(){
                    ids+=$(this).attr("id").split("_")[0]+",";
                });
                ids+=id;
                goUpdateData(ids,codeName,flag,enable,imgUrl);
            }
        });


        //$.mbox({
        //    area: [ "450px", "auto" ], //弹框大小
        //    border: [ 0, .5, "#666" ],
        //    dialog: {
        //        msg: "您确认禁用该分类?",
        //        btns: 2,   //1: 只有一个按钮   2：两个按钮  3：没有按钮 提示框
        //        type: 2,   //1:对钩   2：问号  3：叹号
        //        btn: [ "确定", "取消"],  //自定义按钮
        //        yes: function() {  //点击左侧按钮：成功
        //            $("#firtId_"+id).find("a").each(function(){
        //                ids+=$(this).attr("id").split("_")[0]+",";
        //            });
        //            ids+=id;
        //            goUpdateData(ids,codeName,flag,enable,imgUrl);
        //        },
        //        no: function() { //点击右侧按钮：失败
        //            return false;
        //        }
        //    }
        //});
    }else{
        ids = id;
        codeName = $("#insCatName").val();
        //校验分类名称 长度5  只允许输入文本 标点包括英文状态下的'/.
        if(!codeName || !$.trim(codeName)){
            alert("请输入分类名称");
            return;
        }
        var regex = new RegExp("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9]|[.']){1,10}$");
        var res = regex.test(codeName);
        if(!res){
            alert("分类名称只支持英文/汉字/英文状态下的.和'/数字");
            return;
        }
        var imgUrl = $("#imgUrl").val();
        goUpdateData(ids,codeName,flag,enable,imgUrl);
    }

}

function goUpdateData(ids,codeName,flag,enable,imgUrl){
    $.ajax({
        type:"POST",
        url: rootPath + "/insCateManage/querySingleInsCateByName",
        data: {
            id:ids,
            codeName:codeName,
            flag:flag
        },
        dataType: "json",
        success: function (data) {
            if(data.flag=='1'){
                alert("该分类名称已经存在");
            }else{
                $.ajax({
                    type:"POST",
                    url: rootPath + "/insCateManage/updateInsCate",
                    data: {
                        flag:flag,
                        ids:ids,
                        enable:enable,
                        codeName:codeName,
                        imgUrl:imgUrl
                    },
                    dataType: "json",
                    success: function (data) {
                        if(data.flag=='1'){
                            queryAllData($("#pageNo").val());
                            hideTk();
                        }else{
                            if(flag=='1'){
                                alert("禁用失败请稍后再试");
                            }
                        }
                    }
                });
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
    //校验分类名称 长度5  只允许输入文本 标点包括英文状态下的'/.
    if(!codeName || !$.trim(codeName)){
        alert("请输入分类名称");
        return;
    }
    var regex = new RegExp("^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9]|[.']){1,10}$");
    var res = regex.test(codeName);
    if(!res){
        alert("分类名称只支持英文/汉字/英文状态下的.和'/数字");
        return;
    }
    var imgUrl = $("#imgUrl").val();

    //校验分类名称是否重复
    $.ajax({
        type:"POST",
        url: rootPath + "/insCateManage/querySingleInsCateByName",
        data: {
            codeName:codeName
        },
        dataType: "json",
        success: function (data) {
            if(data.flag=='1'){
                alert("该分类名称已经存在");
            }else{
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
function saveCutPic() {
    //判断图片是否为空或则是未更改就进行保存
    if (!$("#targetStyle").attr("src")){
        $.msg("未选择图片");
        return ;
    }
    var temp = $("#targetStyle").attr("style").split(";");
    //处理剪切框的宽高
    dealWidthAndHeight(temp);
    //上传截取后的图片
    $.ajax({
        url : rootPath + "/insCateManage/saveCutPic",
        data : {
            path :$("#targetStyle").attr("src"),
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
                $("#target").attr("src",data.header+data.realPath);
                $("#imgUrl").val(data.realPath);
            }else {
               // $.msg(data.msg);
            }
            $("#targetStyle").attr("src","");
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
        }
    })
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
