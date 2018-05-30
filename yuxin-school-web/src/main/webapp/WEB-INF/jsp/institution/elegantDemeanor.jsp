<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>风采管理</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mine.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <style>
        .gobal-progress{display: none !important;} 
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/elegantDemeanor.css">

</head>
<body>
<input type="hidden" id="x" name="x" value="0"/>
<input type="hidden" id="y" name="y" value="0"/>
<input type="hidden" id="w" name="w" value="0"/>
<input type="hidden" id="h" name="h" value="0"/>
<input type="hidden" id="x2" name="x2" value="0"/>
<input type="hidden" id="y2" name="y2" value="0"/>
<%-- <input type="hidden" id="pageNo" value='${pageNo}'/>
<input type="hidden" id="rowCount" value='${rowCount}'/> --%>
<input type="hidden" id="institutionId" value='${institutionId}'/>
<input type="hidden" id="updateId" value=""/>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">风采管理</h2>
                <span class="line"></span>
            </div>
            <div class="courseUnderLine">
                <div style="height: 260px;" class="imgList">
                    <span class="labelName">封面图片:</span>
                    <ul style="display: inline-block;">
                    	<c:choose >
                            <c:when test="${coverInfo.id == null}">
	                           	<li class="addImg mienShow" id="addCover">
		                            <i class="icon iconfont"></i>
		                        </li>
                            </c:when>
                            <c:otherwise>
                            	<li>
		                            <img src="${coverInfo.imgUrl}" alt="" style="width: 100%;height: 100%" class="imgClick">
		                            <input id="coverId" value="${coverInfo.id}" type="hidden"/>
		                            <span class="imgInfo">学校建筑内部图</span>
		                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">封面图片</a>
		                            <div class="listBg">
		                                <a href="javascript:void(0)" class="btn btn-success btn-sm btnUpdateCover" data-value="${coverInfo.imgUrl}">修改</a>
		                            </div>
		                        </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
                <br/>
                <div style="height: 260px;" class="imgList">
                    <span  class="labelName"> 视频:</span>
                    <input id="videoId" value="${videoId}" type="hidden" />
                    <input id="oldVideoId" value="${videoId}" type="hidden"/>
                    <ul style="display: inline-block;">
                    	<c:choose>
                    		<c:when test="${videoInfo.id == null}">
                    			 <li class="addImg mienShow" id="videoUp" >
		                            <i class="icon iconfont"></i>
		                        </li>
							</c:when>
							<c:otherwise>
								<li>
		                            <img src="${videoInfo.imgUrl}" alt="" style="width: 100%;height:100%"  class="imgClick" id="videoInfoImg">
		                             <input id="videoInfoId" value="${videoInfo.id}" type="hidden"/>
		                             <input id="videoInfoName" value="${videoInfo.name}" type="hidden"/>
		                             <input id="videoInfoContent" value="${videoInfo.content}" type="hidden"/>
		                            <span class="imgInfo">学校建筑内部图</span>
		                            <a href="javascript:void(0)" class="btn btn-success btn-sm rightShow">视频</a>
		                            <div class="listBg">
		                                <a href="javascript:deleteVideo(${videoInfo.id})" class="btn btn-warning btn-sm deleteBtn">删除</a>
		                                <a href="javascript:void(0)" class="btn btn-success btn-sm btnVideoUpdate">修改</a>
		                            </div>
		                        </li>
							</c:otherwise>
						</c:choose>
                    </ul>
                </div>
                <br/>
                <div class="imgList" id="insStyleInfo">

               	</div>
               	 <div class="pages pagination">
     			</div>   
            </div>

        </div>


    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>

<!-- 图片放大 -->

<img class="bigImg" src="">	



<%--视频上传弹窗--%>
<div class="videoUpload">
    <h5>视频</h5>
    <div>
        <span style="padding-left: 6px;">视频封面:</span>
        <%--<p style="width: 304px;height: 304px;border: 1px solid #e4e4e4;margin: auto;text-align: center;overflow: auto;">--%>
        <p style="width: 260px;height: 260px;border: 1px solid #e4e4e4;margin: auto;text-align: center;overflow: auto;">
            <img src="" alt="" style="width: 300px;height: 300px;" id="targetVideo">
        </p>
        <a href="javascript:void(0)" class="addPic" style="margin-left: 165px;margin-top: 5px;">
           	<span>添加图片</span>
           <input type="file" class="btn btn-mini btn-primary" id="imgDataVideo" name="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(1)" value="重新选择文件"/>
        </a>
    </div>
    <div>
        <span class="videoIntro">视频名称:</span>
        <input type="text" style="width: 248px;height: 18px;" class="videoStyle" maxlength="30">
        <!-- <div id="videoFile"> -->
	        <form id="fileupload" method="POST" enctype="multipart/form-data" action="" style="margin-left: 329px;margin-top: -29px">
				 <a href="javascript:void(0)" class="addVideo">
	            +
	            	<input type="file" name="file" accept=".avi,.mp4*,.asf,.sdx,.wmv,.rmvb,.3gp,.mkv,.flv,.f4v,.rm,.ra,.ram,.mpg,.pgeg,.mpe,.vob,.dat,.mov,.3gp,.mts" id="filebutton" class="filebutton" multiple="" value="">
	        	</a>
				<table class="table table-hover table-center operate_vedio_table L-table L-table-hover" style="margin-left:-263px;margin-top:10px;">
					<!-- <thead>
		 						<tr>
		 							<th width="480">文件名</th>
		 							<th width="100">大小</th>
		 							<th width="300">状态</th>
		 							<th width="190">操作</th>
		 						</tr>
					</thead> -->
					<tbody class="files">
		            </tbody>
				</table>
			</form>
		<!-- </div> -->
    </div>
    <div>
        <span class="videoIntro">视频描述:</span>
        <textarea name="" placeholder="请输入视频描述(最多200个字)" class="writeWord" maxlength="200" id="videoContent"></textarea>
    </div>
    <div class="eleBtn">
        <a href="javascript:void(0)" class="btn btn-primary closeVideoUpload">取消</a>
        <a href="javascript:void(0)" class="btn btn-primary closeVideoUpload btnSaveCutPic" onClick="saveCutPic(1)">保存</a>
    </div>
</div>
<%--风采上传弹窗--%>
<div class="elePic" id='elegant'>
    <h5>风采图片</h5>
    <div>
        <p style="width: 300px;height: 300px;border: 1px solid #e4e4e4;margin: auto;text-align: center;overflow: auto;">
            <img src="" alt="" style="margin: auto;width: 300px;height: 300px;" id="targetStyle" class="picStyle">
        </p>
        <a href="javascript:void(0)" class="addPic" style="margin-top: 5px;margin-bottom: 20px;">
            <span>添加图片</span>
           <input type="file" class="btn btn-mini btn-primary" id="imgDataStyle" name="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(2)" value="重新选择文件"/>
        </a>
    </div>
    <div>
        <span >图片描述:</span>
        <textarea name="" placeholder="请输入图片描述(最多60个字)" class="writeWord" maxlength="60" id="styleContent"></textarea>
    </div>
    <div class="eleBtn">
        <a href="javascript:void(0)" class="btn btn-primary closeElePic">取消</a>
        <a href="javascript:void(0)" class="btn btn-primary closeElePic btnSaveCutPic" onClick="saveCutPic(2)">保存</a>
    </div>
</div>
<!-- 封面图片上传 -->
<div class="elePic" id='cover' style="height: 500px;">
    <h5>封面图片</h5>
    <div>
        <p style="width: 300px;height: 300px;border: 1px solid #e4e4e4;margin: auto;text-align: center;overflow: auto;">
            <img src="" alt="" style="width: 300px;height: 300px;" id="target" class="picStyle">
        </p>
        <a href="javascript:void(0)" class="addPic" style="margin-top: 5px;margin-bottom: 20px;">
          <span>添加图片</span>
           <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(0)" value="重新选择文件"/>
        </a>
    </div>
    <div class="eleBtn">
        <a href="javascript:void(0)" class="btn btn-primary closeElePic">取消</a>
        <a href="javascript:void(0)" class="btn btn-primary closeElePic btnSaveCutPic" onClick="saveCutPic(0)">保存</a>
    </div>
</div>

<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
<script src="http://v.polyv.net/file/plug-in2/js/polyv-upload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/ydUpload/yunduo.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/operate/showTc.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/operate.js?_=1.0"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/onlynum.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/letv/letvUpload.js?v=1.0"></script>

<script type="text/javascript" src="<%=rootPath%>/plugins/ccUpload/upload_files/h.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/encapsulated_getJson.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/msgPrompt.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.ui.widget.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.iframe-transport.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.fileupload.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/tmpl.min.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.fileupload-process.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/jquery.fileupload-ui.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/crypt.js"></script>
<script src="<%=rootPath%>/plugins/ccUpload/upload_files/spark-md5.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>

<script src="<%=rootPath %>/javascripts/institution/upload6.js"></script>
<script id="template-upload" type="text/x-tmpl">
		{% for (var i=0, file; file=o.files[i]; i++) {
			var getFileType = function (file) {
                return file.name.split(".").pop();
            };
		 var key = [getFileType(file), file.size, (file.lastModifiedDate==null)?0:file.lastModifiedDate.getTime()].join('_');
		 %}
		    <tr class="template-upload fade" id="{%=key%}" style="height:30px">
		        <td style="display:none">
		            <p class="name">{%=file.name%}</p>
		            <strong class="error text-danger"></strong>
		        </td>
		        <td style="display:none">
		            <p class="size">处理中...</p>
		        </td>
				<td class="progressbar">
					<div style="width:150px; display:inline-block;border: 1px solid;" class="progress progress-striped active mb0 tc_rel" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0">
						<div class="progress-bar progress-bar-success" style="width:0%;height:20px"></div>
						<div style="margin-top: -16px;" class="tc tc_pos upload_percent progress-u">0%</div>
					</div>
					<span class="rate" style="vertical-align:super;display:none; "></span>
				</td>
		        <td class="operate" style="display:none">
		            {% if (!i && !o.options.autoUpload) { %}
		                <button class="btn btn-primary btn-xs start" style="margin-top:3px;" disabled>
		                    <i class="glyphicon glyphicon-upload"></i>
		                    <span>开始上传</span>
		                </button>
		            {% } %}
		            {% if (!i) { %}
		                <button class="btn btn-warning btn-xs cancel" id="cancel" onclick="cancle(this)" style="margin-top:3px;">
		                    <i class="glyphicon glyphicon-ban-circle"></i>
		                    <span>取消</span>
		                </button>
		            {% } %}
					{% if (!i) { %}
		                <button class="btn btn-primary btn-xs" id="pause" style="margin-top:3px;">
		                    <i class="glyphicon glyphicon-ban-circle"></i>
		                    <span>暂停</span>
		                </button>
		            {% } %}
					{% if (!i) { %}
		                <button class="btn btn-primary btn-xs" id="resume" style="display:none; margin-top:3px;">
		                    <i class="glyphicon glyphicon-upload"></i>
		                    <span>续传</span>
		                </button>
		            {% } %}
		        </td>
				<td width="0%" style="display:none">
		            <input id="isPause" value="false">
		        </td>
		    </tr>
		{% } %}
	</script>

<script src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script src="<%=rootPath %>/javascripts/institution/cutPic.js"></script>
<script src="<%=rootPath %>/javascripts/institution/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/elegantDemeanor.js"></script>

<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</body>
</html>
