
$(function () {
    //    左侧active切换
    $selectSubMenus('elegantDemeano');
    //点击视频上传出视频上传弹窗
    $('#videoUp').click(function () {
        $('.videoUpload').show();
    });
    $('.closeVideoUpload').click(function () {
    	//如果是保存，则需要判断必录项是否存在值，存在则隐藏，反之则不隐藏
    	if($(this).hasClass("btnSaveCutPic")){
    		var videoName = $(".videoStyle").val();
    		var content = $("#videoContent").val();
    		var videoId = $("videoId").val();
    		if(videoName == null || videoName == '' 
    		  || content == null || content == ''
    		  || videoId == null || videoId == ''){
    		  return ;
    		}
    	}
        $('.videoUpload').hide();
        //移除剪切图插件对象
        if (jcrop_apis){
            jcrop_apis.destroy();
        }
        $("#targetVideo").attr("src","").attr("style","").attr("style","width: 136px;height: 116px;");
        $(".writeWord").val("");
        $(".videoStyle").val("");
        //取消保存的时候，如果是新增则删除视频信息，如果是修改，则需要判断是否上传过视频
        var oldVideoId = $("#oldVideoId").val();
        var videoId = $("#videoId").val();
        if(videoId != oldVideoId && videoId !=0 &&videoId !=''&&videoId !=null ){
        	$.ajax({
                url : rootPath + "/institutionStyle/delVideo",
                data : {
                	"relationId":videoId
                },
                type : "post",
                dataType : "json",
                success : function(data) {
                	//刷新一下页面
            		window.location.href=rootPath +"/institutionStyle/queryInstitutionStyle?relationId="+$("#institutionId").val();
                }
            });
        }
    });
    
    $(".btnVideoUpdate").click(function(){
    	 $('.videoUpload').show();
    	 $(".videoStyle").val($("#videoInfoName").val());
         $(".writeWord").val($("#videoInfoContent").val());
         $("#targetVideo").attr("src",$("#videoInfoImg").attr("src"));
         //将更新id设置到updateId上去
    	 $("#updateId").val($("#videoInfoId").val());
         //初始化xywh
    	 $("#x").val("0");
    	 $("#y").val("0");
    	 $("#w").val("0");
    	 $("#h").val("0");
    });

    //点击风采上传弹窗
    $('#eleShow').click(function () {
        $('#elegant').show();
    });
    $('.closeElePic').click(function () {
        $('#elegant').hide();
    });
    //点击封面弹窗
    $('#addCover').click(function () {
        $('#cover').show();
    });
    $('.closeElePic').click(function () {
        $('#cover').hide();
    });
    //点击修改封面弹窗
    $(".btnUpdateCover").click(function () {
    	console.log($(this).attr("data-value"));
    	 $('#cover').show();
    	 //将原有图片设置上去
    	 $('#target').attr("src",$(this).attr("data-value"));
    	 //将更新id设置到updateId上去
    	 $("#updateId").val($("#coverId").val());
    	 //初始化xywh
    	 $("#x").val("0");
    	 $("#y").val("0");
    	 $("#w").val("0");
    	 $("#h").val("0");
    });
    //关闭大图
    $(document).click(function(){
    	$('.bigImg').hide();
    });
    
    $('.bigImg').click(function(){
    	return false;
    });
    
    //为图片添加点击事件,以便图片方大
    $('.imgClick').click(function(){
    	var url = $(this).attr('src');
    	$('.bigImg').show().attr('src',url);
    	return false;
    });
    // 0 封面剪切图 1是视频剪切图 2是风采剪切图
    //.on("change","#targetVideo",imgInit(1)).on("change","#targetStyle",imgInit(2));
    //剪切图
    $(".elePic").on("change","#target", function() {
        var theImage = new Image();
        console.log($(this).attr("src"));
        theImage.src = $(this).attr("src");
        if (theImage.complete) {
            sourceHeight = theImage.height;
            sourceWidth = theImage.width;
            $.init(sourceWidth, sourceHeight,0);
        } else {
            theImage.onload = function () {
                sourceHeight = theImage.height;
                sourceWidth = theImage.width;
                $.init(sourceWidth, sourceHeight,0);
            };
        }

    }).on("change","#targetStyle", function() {
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
	    }
    });
  //videoUpload
    $(".videoUpload").on("change","#targetVideo", function() {
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
        }

    });
});

//上传临时图片 2为风采  1为视频 0为封面
function savePic(saveFlag) {
	var fileStr = saveFlag == 0?$("#imgData").val():saveFlag==1?$("#imgDataVideo").val():$("#imgDataStyle").val();
    if(picFormat(fileStr)){
    	saveFlag == 0?$("#target").attr("src",""):saveFlag==1?$("#targetVideo").attr("src",""):$("#targetStyle").attr("src","");
        return ;
    }
    //选择的时候应先清空，
    if (saveFlag == 0){
        $("#target").attr("src","");
    }else if(saveFlag == 1){
        $("#targetVideo").attr("src","");
    }else{
    	$("#targetStyle").attr("src","");
    }
    console.log($("#imgDataStyle"));
    $.ajaxFileUpload({
        url : rootPath+"/institutionStyle/upLoadInsStyleImg",
        secureuri : false,// 安全协议
        async : false,
        fileElementId : saveFlag == 0?'imgData':saveFlag == 1?'imgDataVideo':'imgDataStyle',
        dataType:'json',
        type : "POST",
        success : function(data) {
            //上传成功移除插件
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
            if (data.flag == 1){
                if (saveFlag == 0){
                    $("#target").attr("src",data.realPath);
                    $("#target").trigger("change");
                    $(".jcrop-holder").find("img").attr("src",data.realPath);
                }else if(saveFlag == 1){
                    $("#targetVideo").attr("src",data.realPath);
                    $("#targetVideo").trigger("change");
                    $(".jcrop-holder").find("img").attr("src",data.realPath);
                }else{
                	 $("#targetStyle").attr("src",data.realPath);
                     $("#targetStyle").trigger("change");
                     $(".jcrop-holder").find("img").attr("src",data.realPath);
                }
            }

        },
        error:function(arg1,arg2,arg3){
        },
        saveFlag:saveFlag
        // loadingEle:  saveFlag == 1?'#target':'#targetStyle',
        // fileName: 'imgData'
    });
}

var countAdd = 0;//用于处理重复提交
//上传剪切图,返回真实地址并插入数据库中
function saveCutPic(saveFlag) {
    var id = $("#updateId").val();
//    console.log($("#institutionId").val());
    //判断图片是否为空或则是未更改就进行保存
    if (saveFlag == 0){//风采图，反之则是封面
        //判断图片是否为空或则是未更改就进行保存
        if (!$("#target").attr("src")){
            $.msg("未选择图片");
            return ;
        }
        var temp = $("#target").attr("style").split(";");
        //处理剪切框的宽高
        dealWidthAndHeight(temp);
    }else if(saveFlag == 1){
        //判断视频名称和视频描述是否为空
        if(!$(".videoStyle").val()||!$("#videoContent").val()){
        	$.msg("有必录项未录入");
        	return false;
        }
        var videoIds = $("#videoId").val();
        if(videoIds == null || videoIds == '' || videoIds ==0){
        	$.msg("未上传视频");
        	return false;
        }
    }else {
        //判断图片是否为空或则是未更改就进行保存
        if (!$("#targetStyle").attr("src")){
            $.msg("未选择图片");
            return ;
        }
    }
    //处理重复提交
  	if(countAdd != 0){
  		alert("请勿重复提交");
  		return ;
  	}
  	countAdd++;

    //上传截取后的图片
    $.ajax({
        url : rootPath + "/institutionStyle/saveCutPic",
        data : {
            path : saveFlag == 0?$("#target").attr("src"):saveFlag == 1?$("#targetVideo").attr("src"):$("#targetStyle").attr("src"),
            x : $("#x").val(),
            y : $("#y").val(),
            w : $("#w").val(),
            h : $("#h").val(),
            content:saveFlag == 1?$("#videoContent").val():saveFlag == 2?$("#styleContent").val():"",
            relationId:$("#institutionId").val(),
            sourceFlag:0,//机构风采
            type:saveFlag,// 0 封面 1视频 2风采
            updateId:id,
            videoName:saveFlag == 1?$(".videoStyle").val():"",
            videoId:saveFlag == 1?$("#videoId").val():"",
            saveFlag:saveFlag,
            oldVideoId:saveFlag == 1?$("#oldVideoId").val():""
        },
        type : "post",
        dataType : "json",
        success : function(data) {
        	countAdd = 0;
            //上传成功则重新查询
            if (data.flag == 1){
            	if(saveFlag != 2){
            		//刷新一下页面
            		window.location.href=rootPath +"/institutionStyle/queryInstitutionStyle?relationId="+$("#institutionId").val();
            	}else{
            		queryInstitutionStyle(1);
            	}
            }else {
                $.msg(data.msg);
            }
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
        }
    });
    $("#chooseDiv").css("display", "none");
    $("#stopDiv").css("display", "none");
    return;
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

//图片初始函数
function imgInit(flag) {
    var theImage = new Image();
    console.log($(this).attr("src"));
    theImage.src = $(this).attr("src");
    if (theImage.complete) {
        sourceHeight = theImage.height;
        sourceWidth = theImage.width;
        $.init(sourceWidth, sourceHeight,flag);
    } else {
        theImage.onload = function () {
            sourceHeight = theImage.height;
            sourceWidth = theImage.width;
            $.init(sourceWidth, sourceHeight,flag);
        };
    };

}

//删除视频
function deleteVideo(videoInfoId){
	//获取到视频的id，上方参数只是视频风采id
	var videoId = $("#videoId").val();
	$.ajax({
        url: rootPath + "/institutionStyle/deleteStyle",
        data: {"primaryId":videoInfoId,
        	   "deleteFlag":1,
        	   "videoId":videoId
        },
        dataType: "json",
        success: function (data) {
            if(data == "success"){
            	//刷新页面
            	//刷新一下页面
        		window.location.href=rootPath +"/institutionStyle/queryInstitutionStyle?relationId="+$("#institutionId").val();
            }
        }
    });
}

//图片格式
function picFormat(fileStr){
	if(!(fileStr.indexOf(".jpg")==(fileStr.length-4)
            ||fileStr.indexOf(".jpeg")==(fileStr.length-5)
            ||fileStr.indexOf(".png")==(fileStr.length-4)
            ||fileStr.indexOf(".bmp")==(fileStr.length-4)
            ||fileStr.indexOf(".ico")==(fileStr.length-4))){
            alert("上传封面仅仅支持以下格式:.jpg,.jpeg,.png,.bmp,.ico");
            return true;
    }
	return false;
}