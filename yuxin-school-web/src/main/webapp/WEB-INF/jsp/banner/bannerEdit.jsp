
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>首页banner图设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
 <style type="text/css">
.p1 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
/*	padding: 6px;
 	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
}

.p2 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
/*	padding: 6px;
 	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
}

.p3 {
	display: block;
	position: absolute;
	z-index: 2000;
	top: 10px;
	right: -280px;
/* 	padding: 6px;
	border: 1px rgba(0, 0, 0, .4) solid;
	background-color: white;
	-webkit-border-radius: 6px;
	-moz-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	-moz-box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2);
	box-shadow: 1px 1px 5px 2px rgba(0, 0, 0, 0.2); */
}

.p1 .preview-container {
	width: 446px;
	height: 241px;
	overflow: hidden;
}

.p2 .preview-container {
	width: 255px;
	height: 138px;
	overflow: hidden;
}

.p3 .preview-container {
	width: 181px;
	height: 96px;
	overflow: hidden;
}
#cke_newsContents{width: 900px !important;margin-left: 150px;}
#cke_1_top,#cke_1_bottom{margin-right: 0 !important;}
.contentBox{height: auto !important;}
</style>
</head>
<body style="position:relative;">
<input type="hidden" value="${bannerType }" id="bannerType"/>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<%--已上架课程列表--%>
<div id="modelList" class="pageRecommendtionBg">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5" style="display: inline-block;margin: 10px 0;">banner修改</h2>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <div class="findQuestion">
                <div>
                    <span>banner图片:</span>
                	<span class="c-content bannerImg">
							<span class="view">
	                            <img id="commdotityPic" src="${msgPage.bannerImgUrl}" realPath="${msgPage.realyBannerImgUrl}" >
	                        </span>
	                        <span class="btns"><a href="javascript:;" class="btn btn-default btn-upload">选择封面</a></span>
                        </span>
                </div>
                <div class="checkBoxBtn">
                    <span>名称:</span>
                    <input type="text" name="bannerName" id="bannerName" value="${msgPage.bannerName }" class="bannerInput" maxlength="32" placeholder="最长可输入32个字符">
                    <input type="hidden" name="bannerId" id="bannerId" value="${msgPage.id}" >
                </div>
                <div class="checkBoxBtn">
                    <span>描述:</span>
                    <input type="text" name="bannerDescribe" id="bannerDescribe" value="${msgPage.bannerDescribe }" class="bannerInput" maxlength="255" placeholder="最长可输入255个字符">
                </div>
                <div class="contentBox">
                    <span>内容:</span>
                        <textarea id="newsContents" id="bannerContent"  name="bannerContent" class="msg-content">${msgPage.bannerContent }</textarea>
                    </div>
                </div>

                <div class="putQuestion bannerBtnGroup">
                	<a href='#' onclick="yulan()"  id="yulan" class='btn btn-success'>预览</a>
                    <button  onclick="save()" type="button" class="btn btn-success"  >保存</button>
                    <button onclick="history.go(-1)" type="button"  class="btn btn-danger"  >取消</button>
                </div>
            </div>
        </div>
    </div>
</div>
<form method="post" id="hiddenForm" target="_blank" >
	<input type="hidden" id="hiddenBannerContent" name="bannerContent">
</form>
<div class="upload-layer none" id="chooseDiv" style="width:1080px;height: 550px;">
    <div class="upload-title">
        <h2 class="h5">上传封面</h2>
        <i class="iconfont close">&#xe610;</i>
    </div>
    <div class="pic-upload">
        <p class="tips">
        	 <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic()" value="重新选择文件"/>
          	<!--<a href="javascript:;" class="btn btn-mini btn-primary">重新选择文件</a>--> 
          	<div style="margin-top: 5px;">建议上传的图片尺寸为：720*420px </div> 
        </p>
        <div class="upload-content" style="padding:10px;">
        <div class="attributes none">
        	 <input type="hidden" id="x" name="x" value="0"/>
            <input type="hidden" id="y" name="y" value="0"/>
            <input type="hidden" id="w" name="w" value="0"/>
            <input type="hidden" id="h" name="h" value="0"/>
            <input type="hidden" id="x2" name="x2" value="0"/>
            <input type="hidden" id="y2" name="y2" value="0"/>
        </div>
        	<div class="pic" style="width:516px;height:282px;background-color:#f2f2f2;">
        		 <img id="target" src="" />
            </div>
            <div class="upload-big p1" style="width:456px;">
            	<div class="preview-container" style="margin:0 auto;">
                <img src="">
                </div>
            </div>
            <div class="upload-sm p2">
            	<div class="preview-container">
                <img src="">
                </div>
            </div>
            <div class="upload-mini p3">
           		<div class="preview-container">
                <img src="">
                </div>
            </div>
        </div>
        <p class="text-center">
            <a href="javascript:classTypePic();" class="btn btn-primary">确定</a>
        </p>
    </div>
</div>
<div class="add-layer-bg none" id="stopDiv"></div>
<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<script type="text/javascript" src="<%=rootPath%>/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/addClassTypeOnsale.js"></script>
	<script type="text/javascript">
	
	function yulan(){
   	 CKupdate();
   	 var bannerContent=editor.document.getBody().getHtml();
   	 if(null!=bannerContent && '<p><br></p>'!=bannerContent){
   		 
				$("#hiddenBannerContent").val(bannerContent);
				$("#hiddenForm").attr("action",rootPath+"/Banner/yulan").submit();
				
   	 }else{
   		 alert("预览内容不能为空");
   	 }
   }
	
	$(function(){
		$(".btn-upload").on('click',function(){
			$("#chooseDiv").css("display", "block");
			$("#stopDiv").css("display", "block");
		});
 		// 弹层处理
 	      $('.upload-layer')
 	          .on('click','i.close',function(){
 	              $('.upload-layer').fadeOut(200,function(){
 	                  $('.add-layer-bg').fadeOut(200);
 	              });
 	          })
 	          // 取消
 	          .on('click','.btn-cancel',function(){
 	              $(this).parents('.pic-upload').fadeOut(200,function(){
 	                 // alert('这个仅作示例，为了展示列表')
 	            	  $('.upload-layer').css({'height':'481px'});
 	              })
 	          })
 	          .on('click','li.add',function(){
 	              $('.pic-upload').fadeIn(200,function(){
 	                  //alert('仅作示例，具体根据实际情况自行修改！')
 	            	  $('.upload-layer').css({'height':'540px'});
 	              })
 	          });
		});
	
	    //处理CKEDITOR的值
		function CKupdate() {
			for (instance in CKEDITOR.instances) {
				CKEDITOR.instances[instance].updateElement();
			}
		}
		var editor = CKEDITOR.replace('newsContents');
		editor.config.width="570px";
		editor.config.toolbar = [
				[ 'mode', 'document', 'doctools' ],
				[ 'Source', '-', 'NewPage' ],
				[ 'basicstyles', 'cleanup' ],
				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
						'Superscript' ],
				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],
				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
						'JustifyLeft', 'JustifyCenter', 'JustifyRight',
						'JustifyBlock' ], [ 'Link', 'Unlink' ],
				[ 'Image', 'Table' ],
				[ 'Styles', 'Format', 'Font', 'FontSize' ],
				[ 'TextColor', 'BGColor' ], [ 'Maximize' ], [ '-' ]];
		editor.config.baseFloatZIndex = 10100;
		editor.config.customConfig = 'config.js';
		
		$(".lj-tops").bind("click",function(){
			
			var _checkbox= $(this).siblings().find("input");
			if(_checkbox.is(":checked")){
				_checkbox.attr('checked',false);
			}
		});
		/* function goback(){
			window.location.href = "comBannerIndex";
		} */
		function save(){
			 var bannerImgUrl=$("#commdotityPic").attr("realPath");
			 if(null==bannerImgUrl || ''==bannerImgUrl){
				 alert("banner图不能为空");
				 return;
			 }
	    	 var bannerName=$("#bannerName").val();
	    	 var id=$("#bannerId").val();
	    	 var bannerDescribe=$("#bannerDescribe").val();
	    	 CKupdate();
	    	 var bannerContent=editor.document.getBody().getHtml();
	    	 var bannerType = $("#bannerType").val();
	    	 if(null!=bannerContent && '<p><br></p>'!=bannerContent){
	    		 $.ajax({
	 	 			url: rootPath + "/Banner/update",
	 	 			type:"post",
	 	 			data:{"id":id,"bannerName":bannerName,"bannerContent" : bannerContent,"bannerDescribe":bannerDescribe,"bannerImgUrl" :bannerImgUrl},
	 	 			dataType:"json",
	 	 			success:function(data){
	 	 				if(data.msg == 'success'){
	 	 					alert("保存成功");
	 	 					if(bannerType == 0){
	 	 						window.location.href = "<%=rootPath %>/Banner/comBannerIndex";
	 	 					}else{
	 	 						window.location.href = "<%=rootPath %>/Banner/riseBannerIndex";
	 	 					}
	 	 				}
	 	 			}
	 	 		});
	    	 }else{
	    		 alert("内容不能为空");
	    	 }
	    	 
	    } 
		//上传截取后的图片
		function classTypePic() {
			$.ajax({
						url : rootPath + "/Banner/saveCutPic",
						data : {
							path : $("#target").attr("src"),
							x : $("#x").val(),
							y : $("#y").val(),
							w : $("#w").val(),
							h : $("#h").val(),
							itemOneid : $("#itemOneid").val()
						},
						type : "post",
						dataType : "json",
						success : function(data) {
							chooseOnePic(data.picOriginalUrl,
									data.realPath);
						}
					})
			$("#chooseDiv").css("display", "none");
			$("#stopDiv").css("display", "none");
			return;
		}
		//选择封面
		function chooseOnePic(url,path){
			$("#chooseDiv").css("display","none");
			$("#stopDiv").css("display","none");
			$("#commdotityPic").attr({"src":url,"realPath":path});
		}
		
		$(".pic").on("change","#target", function() {
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
		//选择图片
		function savePic(){
				$.ajaxFileUpload({
				url : rootPath+"/simpleClasses/savePic;"+ window["sessionName"] + "=" + window["sessionId"],
				secureuri : false,// 安全协议
				async : false,
				fileElementId : 'imgData',
				dataType:'json',
				type : "POST",
				success : function(data) {
				  $("#sourcePic").attr("src",data.url);
				  $("#target").parent().html('<img id="target" src="'+data.url+'" style="width:516px;height:282px;"/>');
			      $("#target").trigger("change");
			      $(".p1 img").attr("src",data.url);
			      $(".p2 img").attr("src",data.url);
			      $(".p3 img").attr("src",data.url);
				},
				error:function(arg1,arg2,arg3){
					//console.log(arg1);
				},
				loadingEle: '#target',
				fileName: 'imgData'
			});
		}
	</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('comBannerIndex');
    });
</script>
</body>
</html>
