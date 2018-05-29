var nameTest = /^[\u4e00-\u9fa5a-zA-Z0-9\s]+$/;

$(function () {
    //    左侧active切换
    $selectSubMenus('teacherFamous ');
    //系统标签增加和删除
    $('.addSystem').click(function () {
        let systemLength = $('.systemBtn').length;
        if(systemLength < 5){
            var _html =
                ` <span href="##" class="systemBtn">
                                <input class="systemLabel" maxlength="5" >
                                <i class="icon iconfont deleteBtn">&#xe610;</i>
                  </span>
            `;
            $(this).before(_html);
            if(systemLength == 4){
                $(this).hide();
            }
        }
    });
    $('body').on('click','.deleteBtn',function () {
        $(this).parents('.systemBtn').remove();
        let systemBtnLength = $('.systemBtn').length;
        if(systemBtnLength < 5){
            $('.addSystem').show();
        }
    });


    // 添加老师名称监听器
    /*$('#teacherName').bind('input propertychange', 'input' , function(){
        nameFileter('teacherName');
    })*/




    //毕业学校监听器
    /*$('#teacherSchool').bind('input propertychange', 'input' , function(){
        nameFileter('teacherSchool');
       /!* var val = $('#teacherSchool').val();
        //不能输入全是空格的字符串
        if(trim(val) == ''){
            $('#teacherSchool').val('');
            return;
        }

        //字符串末尾连续多个空格
        if(val.length - trim(val).length > 1){
            $('#teacherSchool').val(val.substr(0,val.length - 1));
            return;
        }

        if(!nameTest.test(trim(val))){
            $('#teacherSchool').val(val.substr(0,val.length - 1));
        }*!/
    })*/



    $('.closeMechanismCancel').click(function(){
        window.location.href = '/InsInfoBase/famousTeacher/'+getInsId();
    });


    getTeacherInfo();


    /**
     *初始化截图
     */
    $(".uploadImageStyle").on("change","#targetStyle", function() {
        var theImage = new Image();
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

    /**
     * 隐藏及销毁截图
     */
        //隐藏
    $('.mienHide').click(function(){
        $('.coverPopup').hide();
        $('.commonPopup').hide();
        $("#targetStyle").attr("src","").attr("style","");
        if (jcrop_apis){
            jcrop_apis.destroy();
        }
    });





});


function nameFileter(id){
    var val = $('#'+id).val();
    //不能输入全是空格的字符串
    if(trim(val) == ''){
        $('#'+id).val('');
        return;
    }

    //字符串末尾连续多个空格
    if(val.length - trim(val).length > 1){
        $('#'+id).val(val.substr(0,val.length - 1));
        nameFileter(id);
    }

    if(!nameTest.test(trim(val))){
        $('#'+id).val(val.substr(0,val.length - 1));
        nameFileter(id);
    }
}


function getTeacherInfo(){
    var tid = getTeacherId();
    if(tid == ''){
        return;
    }

    $.post(rootPath + '/institutionTeacher/teacherInfo',{tid:tid,insId:getInsId()},function(json){
        //console.log(json);
        if(json.status == 1){
            $("#teacherName").val(json.data.name);
            $("#teacherSchool").val(json.data.school);
            $("#teacherName").val(json.data.name);
            $("#hidId").val(json.data.id);;
            $("#summary").val(json.data.desc);

            $('#imgTop').html("<img src='"+json.data.fullUrl+"' alt=\"\" style=\"width: 100px;height: 100px;border-radius: 50px;\">");
            $('#hidHeadImg').val(json.data.headUrl);

            var labelList = json.data.labels;
            var html = '';
            for(var i in labelList){
                html += "<span href=\"##\" class=\"systemBtn\"><input class=\"systemLabel\" maxlength=\"5\" label-id='"+labelList[i].id+"' value='"+labelList[i].name+"' ><i class=\"icon iconfont deleteBtn\">&#xe610;</i>\n                  </span>";
            }

            $('.iconBtn').before(html);

            if(labelList != null && labelList.length >= 5){
                $('.iconBtn').hide();
            }


            $('.addPic').html('更换头像');
            $('#teacherTitle').html('老师详情');

        }
    })


}



//点击提交按钮
var isClick = 0;


$('.closeMechanismCommit').click(function(){

        var labelsInfo = getLabels();
        if(labelsInfo == '标签重复'){
            $.msg('标签重复');
            return;
        }

        var param = {
            headUrl:$("#hidHeadImg").val(),
            name:$("#teacherName").val(),
            school:$("#teacherSchool").val(),
            label:getLabels(),
            desc:$('#summary').val(),
            id:$("#hidId").val(),
            insId:getInsId()
        }


        var name = param.name;
        name = name.split(" ").join("");
        if(name.length == 0){
            $.msg("请填写老师名称");
            return;
        }

        if(param.name == ''){

            $.msg('请填写老师名称');
            return;
        }


        var nameSchool = param.school;
        nameSchool = nameSchool.split(" ").join("");
        if(nameSchool.length == 0){
            $.msg("请填写老师毕业院校");
            return;
        }
        if(param.school == ''){
            $.msg('请填写老师毕业院校');
            return;
        }



        if(param.label == null){
            $.msg('请不要添加空白标签');
            return;
        }

        var teacher = param.desc;
        teacher = teacher.split(" ").join("");
        if(teacher.length == 0){
            $.msg("请填写老师简介");
            return;
        }
        if(param.desc == ''){
            $.msg('请填写老师简介');
            return;
        }

        var url = (param.id == '' ? '/institutionTeacher/addTeacher' : '/institutionTeacher/updateTeacher');

        $.post(rootPath+url,param,function(json) {
            // console.log(json);
            if(json.status == 1){
                $.msg('操作成功');
                window.history.go(-1);
                //window.location.href = rootPath + "/InsInfoBase/famousTeacher/"+getInsId();
            }else{
                isClick = 0;
                $.msg('操作失败');
            }
        })
});


function getLabels(){
    var labels = new Array();
    var labelSpans = $("#teacherLabelsContainer").find('.systemBtn');

    for(var i = 0;i<labelSpans.length;i++){
        labels.push({
            id:$(labelSpans[i]).find('.systemLabel').eq(0).attr('label-id'),
            name:$(labelSpans[i]).find('.systemLabel').eq(0).val()
        })

        if(trim(labels[i].name) == ''){
            return null;
        }

    }

    //标签判定重复
    for(var i in labels){
        for(var j in labels){
            if(i != j && labels[i].name == labels[j].name){
                return '标签重复';
            }
        }
    }


return JSON.stringify(labels);
}

function getTeacherId() {
    return getRequest().tid == undefined ? '' :getRequest().tid ;
}

function getInsId() {
    return getRequest().id;
}

function getRequest() {
    var url = window.location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {

            theRequest[strs[i].split("=")[0]]=decodeURI(strs[i].split("=")[1]);

        }
    }
    return theRequest;
}


//去掉字符串两端的空格
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}


function savePic() {
    //改变图片时清空图片路径
    $("#targetStyle").attr("src","");
    var fileStr = $("#imgDataStyle").val();
    var  lowwerFileStr =  fileStr.toLowerCase();
    //.jpg,.jpeg,.gif,.png,.bmp,.ico
    if(!(lowwerFileStr.indexOf(".jpg")==(fileStr.length-4)
        ||lowwerFileStr.indexOf(".jpeg")==(fileStr.length-5)
        ||lowwerFileStr.indexOf(".png")==(fileStr.length-4)
        ||lowwerFileStr.indexOf(".bmp")==(fileStr.length-4)
            )){
        $("#imgDataStyle").val('');
        alert("上传文件仅支持以下格式:.jpg,.jpeg,.png,.bmp");
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


/*    $.ajax({
        url: rootPath + '/institutionTeacher/uploadImgs',　　　　　　　　　　//上传地址
        type: 'POST',
        cache: false,
        data: new FormData($('#uploadForm')[0]),　　　　　　　　　　　　　//表单数据
        processData: false,
        contentType: false,
        success: function (json) {
            console.log(json);
            if (json.status == 1) {
                $('#imgTop').html("<img src='"+json.url+"' alt=\"\" style=\"width: 100px;height: 100px;border-radius: 50px;\">");
                //  $('#imgTop').html("<img src='../../../images/institution/1.jpg'  alt=\"\" style=\"width: 150px;height: 100px;margin-left: 15px;\">");
                $('#hidHeadImg').val(json.picPath);
            } else if (json.status == 0) {
                return;
            } else {
                $.msg(json.msg);
            }


        }
    });*/

}

function popAddImg(){
    $(".coverPopup").show();
    $.commonPopup();
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
            h : $("#h").val(),
            insFlag:1
        },
        type : "post",
        dataType : "json",
        success : function(data) {
            //上传成功则重新查询
            if (data.flag == 1){
                //上传成功返回路径
                $("#hidHeadImg").val(data.realPath);
                $('#imgTop').html("<img src='"+data.header+data.realPath+"' alt=\"\" style=\"width: 100px;height: 100px;border-radius: 50px;\">");
                $('.addPic').html('更换头像');
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