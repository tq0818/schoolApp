<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>通知内容</title>
    <%@include file="/decorators/import.jsp" %>
     <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/student.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/select2.min.css" />
	<script type="text/javascript" src="<%=rootPath%>/javascripts/student.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/select2.full.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/notice/createNotice.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/student/notice/appoint.js"></script>
    <style type="text/css">
    	.font-style{
    		font-size:0;
    	}
    	.font-style a{
    		margin-right:4px;
    	}
    	.select2{
    		width:150px!important;
    		    height: 28px!important;
    		overflow:hidden;
    	}
    	.select2-selection{
    	 height: 28px!important;
    	 
    	}
    	.c-content select{
    		width:150px;
    		    height: 28px;
    		        padding: 0.15em 8px .35em;
    	}
    	.main-content .lj-tops{display: inline-block;color:#999;}
    </style>
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
		 .nameTitle{display: inline-block;}
		 .icon{color: red;}
		 .userList{width: 366px;position: absolute;left: 153px;top: 398px;display: none;}
		 .userList li{height: 30px;line-height: 30px;background: #fff;padding-left: 10px;}
		 .userList li.active{background: #0e90d2;color: #fff;}
		 .student .notice-main{position: relative;}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp" />
	<input type="hidden" value="${count }" id="msgCount" />
	<div class="u-wrap student new-student">
		<div class="mainbackground">
			<div class="heading">
				<h2 class="h5">新建学员通知</h2>
				<span class="line"></span>
			</div>
			<div class="main-content">
				<div class="notice-main">
					<p class="c con-tzbt">
						<span class="c-title">通知标题：</span> <span class="c-content long">
							<input type="text" id="title" maxlength="15">
						</span>
						<span style="color:red;">* 最多15个字</span>
					</p>
					<p class="c con-coverimg">
						<span class="c-title">订阅文章封面：</span>
						<span class="c-content"> 
							<span class="view">
	                            <img id="commdotityPic" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" realPath="" alt="订阅图片">
	                        </span>
	                        <span class="btns"><a href="javascript:;" class="btn btn-default btn-upload">选择封面</a></span>
                        </span>
					</p>
					<p class="c">
						<span class="c-title">通知方式：</span> <span class="c-content font-style">
							<a href="javascript:;"
							class="btn btn-mini btn-method btn-primary"
							data-type="STUDENT_MESSAGE_MOBILE">短信通知</a> <a
							href="javascript:;" class="btn btn-mini btn-method btn-default"
							data-type="STUDENT_MESSAGE_WEB">站内信通知</a>
							<!-- <a href="javascript:;" class="btn btn-mini btn-method btn-default"
							data-type="STUDENT_MESSAGE_EMAIL">邮件通知</a>  -->
							<a	href="javascript:;" class="btn btn-mini btn-method btn-default"
							data-type="STUDENT_MESSAGE_DINGYUE">订阅文章</a>
						</span>
					</p>
					<p class="c sendMsgType">
						<span class="c-title">通知类型：</span>
						<span class="c-content font-style">
							<a href="javascript:;" class="btn btn-mini btn-type btn-primary"
							data-type="STUDENT_MESSAGE_CLASSTYPE">指定通知</a>
	                        <%-- <c:if test="${classMoreStatus == 1 }">
								<a
								href="javascript:;" class="btn btn-mini btn-type btn-default"
								data-type="STUDENT_MESSAGE_MODULENO">班号通知</a>
	                        </c:if> 
							<a
							href="javascript:;" class="btn btn-mini btn-type btn-default"
							data-type="STUDENT_MESSAGE_SPECIAL">指定通知</a>
							<a
							href="javascript:;" class="btn btn-mini btn-type btn-default"
							data-type="STUDENT_MESSAGE_GROUP">学员分组通知</a> --%>
						</span>
					</p>
					<!--年级  -->
					<p class="c sendGrade articlesList articleNew" style="display: block;">
						<span class="c-title">学段：</span> 
						<button class="btn btn-mini btn-grade btn-default"	data-type="ALL_GRADE">全部</button>
						<c:forEach items="${gradeCodeItems }" var="grade">
							<button class="btn btn-mini btn-grade btn-default"	data-type="${grade.code }">${grade.name }</button>
						</c:forEach>
					</p>


					<%--短信-指定通知--%>
					<!-- 课程或者班号 -->
					<p class="c templete" >
						<span class="c-title" >发送模板：</span>
						<span class="c-content">
							<input type="text" style="width: 150px;border-radius: 0;" id="messageId">
						</span>

					</p>

					<p class="c sendStuMsg ">
						<input type="radio" name="chooseBtn" value="0" style="margin-left: 35px;" checked>
						<span class="c-title" style="width: 36px;">分类：</span>
						<span class="c-content"> <select
							id="one" style="width: 100px;">
								<c:forEach var="o" items="${oneItem }">
									<option value="${o.id }" data-code="${o.itemCode}">${o.itemName }</option>
								</c:forEach>
						</select>
						</span> <span class="c-title">学段：</span> <span class="c-content">
							<select id="two" style="width: 100px;">
						</select>
						</span>
						<span class="c-title">学科：</span> <span class="c-content">
							<select id="three" style="width: 100px;">
						</select>
						</span>

					</p>
					<p class="c sendStuMsg ">

						<span class="c-title" id="classTitle">课程：</span> <span
							class="c-content"> <select id="class"
							class="js-example-basic-single">
						</select>
						</span> 
						<span>课次 :</span>
							<span class="c-content"> <select id="classLesson">
						</select>
						<%--<span class="c-title">学员数量：</span> <span class="c-content btn-view">--%>
						<%--</span>--%>
								<%--</span>--%>
					</p>



					<%--发送给部分用户--%>
					<p class="c">
						<input type="radio" name="chooseBtn" value="1" style="margin-left: 35px;">
						<span class="c-title">发送给部分用户</span>
					</p>
					<p class="c">
						<span class="c-title" >省份：</span>
						<span class="c-content">
							<select name="eduArea" id="eduArea" onchange="queryRiseSchoolDict(1)">
							<option value="">请选择省份</option>
							</select>
						</span>
						<span>市 :</span>
						<span class="c-content">
							<select name="eduSchool" id="eduSchool" onchange="queryRiseSchoolDict(2)">
							<option value="">请选择市</option>
							</select>
						</span>
						<span>地区 :</span>
						<span class="c-content">
							<select id="registStatus" name="status" onchange="querySchoolName()">
							<option value="">请选择区</option>
							</select>
						</span>
					</p>
					<p class="c">
						<span class="c-title">学校 :</span>
						<span class="c-content">
							<select id="schoolName" style="width: 400px;">
							<option value="">请选择学校</option>
							</select>
						</span>
					</p>
					<p class="c">
						<span class="c-title">学段 :</span>
						<span class="c-content">
							<select id="step" onchange="queryRiseSchoolYear()">
								<option value="STEP_01">小学</option>
								<option value="STEP_02">初中</option>
								<option value="STEP_03">高中</option>
							</select>
						</span>
						<span class="c-title">入学年份 :</span>
						<span class="c-content">
							<select id="stepYear">
							</select>
						</span>
					</p>
					<%--发送到指定用户--%>
					<p class="c">
						<input type="radio" name="chooseBtn" value="2" style="margin-left: 35px;">
						<span class="c-title">发送到指定用户</span>
						<span class="c-content">
							<input type="text" style="width: 348px;border-radius: 0;" id="userListInput">
							<ul class="userList">
								
							</ul>
						</span>
					</p>
					<p class="c">
						<span class="c-title">已选用户:</span><br/>
						<span style="display: inline-block;margin-left: 95px;" class="userListInfo" >
							<%--<span class="c-content">--%>
								<%--<label for="" class="nameTitle">张三</label>--%>
								<%--<span>15829854854</span>--%>
								<%--<i class="icon iconfont iconDelete">&#xe610;</i>--%>
							<%--</span>--%>
						</span>


					</p>











					<p class="c sendStuMsg sendStuNum">
						<span class="c-title">发送学员：</span> <span class="c-content"
							id="sendStu"></span>人
					</p>
					
					<!-- 指定通知 -->
					<p class="c phoneHint">
						<span class="c-title">输入手机号：</span><br> <span
							class="c-content l-content"> <textarea class="msg-content"
								id="phone" onkeyup="javascript:valida();"></textarea>
						</span>
					</p>
					<p class="c phoneHint">
						<span class="c-title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<span class="c-content"><span
							style="color: red; float: right;">手机号之间用英文的逗号【，】分隔开</span></span>
					</p>
					<p class="c emailHint" style="display:none;">
						<span class="c-title">输入邮箱：</span><br> <span
							class="c-content l-content"> <textarea class="msg-content"
								id="email" onkeyup="javascript:valida();"></textarea>
						</span>
					</p>
					<p class="c emailHint" style="display:none;">
						<span class="c-title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
						<span class="c-content"><span
							style="color: red; float: right;">邮箱之间用英文的逗号【，】分隔开</span></span>
					</p>
					<!-- 分组 -->
					<p class="c stuGroup" style="display:none;">
						<span class="c-title">一级分组：</span> 
						<select id="studentG1_edit" name="studentGroup1_edit" onchange="javaScript:selectGroup2(this,'_edit');"><option value="" selected="selected">全部</option></select>
						<span class="c-title">二级分组：</span>
						<select id="studentG2_edit" name="studentGroup2_edit" onchange="javaScript:selGroupStu(this);"></select>
					</p>
					<p class="c stuGroup" style="display:none;">
						<span class="c-title">学员数量：</span> <span class="c-content" id="groupStuCount"></span>
						<span class="c-title">发送学员：</span> <span class="c-content" id="_sendStu"></span>人
					</p>
					
					<p class="c emailTitle" style="display:none;">
						<span class="c-title">邮件标题：</span> <input id="email_title" type="text"/>
					</p>
					<!-- 发送内容 -->
					<p class="c con-fsnr">
						<span class="c-title">发送内容：</span><br> <span
						class="c-content l-content" id="messageContent"> <textarea
							id="msgcount" class="msg-content" onkeydown="valida();"
							onkeyup="valida();" onkeypress="valida();" maxlength="140"></textarea>
						</span>
					</p>	
					<!-- 站内信 --> 
					<div id="ckecktor" style="padding-left: 100px;margin-bottom: 20px;"> 
						<textarea id="newsContents" class="msg-content"></textarea>
					</div>
					<!-- 邮件 -->
					 <div id="email_ckecktor" style="display:none;padding-left: 100px;margin-bottom: 20px;"> 
						<textarea id="email_newsContents" class="msg-content"></textarea>
					</div> 
					<!--订阅文章 -->
					<div id="dingyue_ckecktor" style="display:none;padding-left: 100px;margin-bottom: 20px;"> 
						<textarea id="dingyue_newsContents" class="msg-content"></textarea>
					</div>
					<!-- 发送条数 -->
					<p class="c zhan">
						<span class="c-title">消耗短信：</span> <span class="c-content"><span
							id="useMsg"></span> <em style="font-size: inherit;color: #999;padding-right:20px;padding-left:20px;">剩余短信：<span id="Surplus">${!empty count?count:0 } 条</span></em><span
							style="color: red; float: right;" class="tips-txt">已输入<span id="write"></span>个字符，单条短信70个字符
						</span></span>&nbsp;<span style="color:red;" class="tips-txt">* 最多140个字</span>
						<%--<span class="hurry-Notice">--%>
							<%--<label class="lj-tops"><input value="1" class="hurryNotice" type="checkbox">直播口令通知</label>--%>
						<%----%>
						<%--<label class="lj-tops"><input value="2" class="hurryNotice" type="checkbox">故障通知</label>--%>
						<%--</span>--%>
						
					</p>
					<p class="c use_email" style="display:none;">
						<span class="c-title">消耗邮件：</span> <span class="c-content"><span
							id="useEmailMsg"></span> <em style="font-size: inherit;color: #999;padding-right:20px;padding-left:20px;">剩余邮件：<span id="hasEmail">${emailCount }</span></em>
					</p>
					
				    <%--新添订阅文章 start--%>
	                <p class="c dingyueChooseBtn">
	                    <span class="c-title">请选择：</span>
	                    <input type="radio" name="signup_vote" value="0">
	                    <button class="btn btn-primary">我要报名</button>
	                    <a href="##" class="numPerson">人数限制</a>
	                    <input type="text" id="limitStuNum">
	                </p>
	                <p class="c dingyueChooseBtn">
	                    <span class="c-title"></span>
	                    <input type="radio" name="signup_vote" value="1">
	                    <button class="btn btn-primary">赞成</button>
	                    <button class="btn btn-default">反对</button>
	                </p>
					<!--  -->
					<p class="text-center">
						<span class="sendBtn">
							<a href="javascript:;" class="btn btn-sm btn-primary btn-send">发送通知</a>
						</span>
						<span class="dingyueSendBtn">
							<a href="javascript:;" class="btn btn-sm btn-warning btn-publish">发布</a>
	                     	<input type="checkbox" id="isNeedSend">是否推送
	                    </span>
					</p>
				</div>
			</div>
		</div>
	</div>

<!--  -->

<div class="upload-layer none" id="chooseDiv" style="width:1080px;height: 550px;">
    <div class="upload-title">
        <h2 class="h5">上传封面</h2>
        <i class="iconfont close">&#xe610;</i>
    </div>
    <div class="pic-upload">
        <p class="tips">
        	 <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic()" value="重新选择文件"/>
          	<!--<a href="javascript:;" class="btn btn-mini btn-primary">重新选择文件</a>--> 
          	<div style="margin-top: 5px;">建议上传的图片尺寸为：516*282px </div> 
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
            <a href="javascript:;" class="btn btn-default close">取消</a>
        </p>
    </div>
</div>
<div class="add-layer-bg none" id="stopDiv"></div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
	<script type="text/javascript" src="<%=rootPath%>/plugins/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
     <script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/class/addClassTypeOnsale.js"></script>
	<script type="text/javascript">
		var classMoreStatus = '${classMoreStatus}';//多班号是否开启
	
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
		
		
		
		var email_editor = CKEDITOR.replace('email_newsContents');
		email_editor.config.width="570px";
		email_editor.config.toolbar = [
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
		email_editor.config.baseFloatZIndex = 10100;
		email_editor.config.customConfig = 'config.js';
		
		var dingyue_ckecktor = CKEDITOR.replace('dingyue_newsContents');
		dingyue_ckecktor.config.width="570px";
		dingyue_ckecktor.config.toolbar = [
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
		dingyue_ckecktor.config.baseFloatZIndex = 10100;
		dingyue_ckecktor.config.customConfig = 'config.js';
		
		$(".lj-tops").bind("click",function(){
			
			var _checkbox= $(this).siblings().find("input");
			if(_checkbox.is(":checked")){
				_checkbox.attr('checked',false);
			}
		});
		
		//上传截取后的图片
		function classTypePic() {
			$.ajax({
						url : rootPath + "/student/saveCutPic",
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
</body>
</html>