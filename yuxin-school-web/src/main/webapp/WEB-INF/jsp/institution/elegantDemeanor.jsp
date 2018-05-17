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
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
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
		                            <img src="${coverInfo.imgUrl}" alt="" style="width: 100%;height: auto" class="imgClick">
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
                    <ul style="display: inline-block;">
                    	<c:choose>
                    		<c:when test="${videoInfo.id == null}">
                    			 <li class="addImg mienShow" id="videoUp" >
		                            <i class="icon iconfont"></i>
		                        </li>
							</c:when>
							<c:otherwise>
								<li>
		                            <img src="${videoInfo.imgUrl}" alt="" style="width: 100%;height: auto"  class="imgClick">
		                             <input id="videoId" value="${videoInfo.id}" type="hidden"/>
		                            <span class="imgInfo">学校建筑内部图</span>
		                            <a href="javascript:void(0)" class="btn btn-success btn-sm rightShow">视频</a>
		                            <div class="listBg">
		                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
		                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
		                            </div>
		                        </li>
							</c:otherwise>
						</c:choose>
                    </ul>
                </div>
                <br/>
                <div class="imgList" id="insStyleInfo">
                
               	</div>
                <div class="pages pagination" style="padding-top: 10px;">

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
        <img src="" alt="" style="width: 136px;height: 116px;" id="targetVideo">
        <br/>
        <a href="##" class="addPic" style="margin-left: 97px;margin-top: 5px;">
            选择图片
           <input type="file" class="btn btn-mini btn-primary" id="imgDataVideo" name="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(1)" value="重新选择文件"/>
        </a>
    </div>
    <div>
        <span class="videoIntro">视频名称:</span>
        <input type="text" style="width: 248px;height: 24px;" class="videoStlye">
        <a href="##" class="addVideo">
            +
            <input type="file">
        </a>
    </div>
    <div>
        <span class="videoIntro">视频描述:</span>
        <textarea name="" placeholder="请输入视频描述(最多200个字)" class="writeWord" maxlength="200" id="videoContent"></textarea>
    </div>
    <div class="eleBtn">
        <a href="##" class="btn btn-primary closeVideoUpload">取消</a>
        <a href="##" class="btn btn-primary closeVideoUpload btnSaveCutPic" onClick="saveCutPic(1)">保存</a>
    </div>
</div>
<%--风采上传弹窗--%>
<div class="elePic" id='elegant'>
    <h5>风采图片</h5>
    <div>
        <img src="" alt="" style="width: 300px;height: 300px;" id="targetStyle" class="picStyle">
        <br/>
        <a href="##" class="addPic" style="margin-top: 5px;margin-bottom: 20px;">
            选择图片
           <input type="file" class="btn btn-mini btn-primary" id="imgDataStyle" name="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(2)" value="重新选择文件"/>
        </a>
    </div>
    <div>
        <span >图片描述:</span>
        <textarea name="" placeholder="请输入图片描述(最多60个字)" class="writeWord" maxlength="60" id="styleContent"></textarea>
    </div>
    <div class="eleBtn">
        <a href="##" class="btn btn-primary closeElePic">取消</a>
        <a href="##" class="btn btn-primary closeElePic btnSaveCutPic" onClick="saveCutPic(2)">保存</a>
    </div>
</div>
<!-- 封面图片上传 -->
<div class="elePic" id='cover' style="height: 500px;">
    <h5>封面图片</h5>
    <div>
        <img src="" alt="" style="width: 300px;height: 300px;" id="target" class="picStyle">
        <br/>
        <a href="##" class="addPic" style="margin-top: 5px;margin-bottom: 20px;">
           选择图片
           <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(0)" value="重新选择文件"/>
        </a>
    </div>
    <div class="eleBtn">
        <a href="##" class="btn btn-primary closeElePic">取消</a>
        <a href="##" class="btn btn-primary closeElePic btnSaveCutPic" onClick="saveCutPic(0)">保存</a>
    </div>
</div>

<script src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script src="<%=rootPath %>/javascripts/institution/cutPic.js"></script>
<script src="<%=rootPath %>/javascripts/institution/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/elegantDemeanor.js"></script>
<script>
	$(function(){
		$.ajax({
	        url: rootPath + "/institutionStyle/queryInsStyle",
	        data: {"page":0,
	            "pagesize":9,
	            "relationId":$("#institutionId").val(),
	        },beforeSend: function (XMLHttpRequest) {
	           /* $(".loading").show();
	            $(".loading-bg").show();*/
	        },
	        dataType: "html",
	        success: function (data) {
//	            $(".loading").hide();
//	            $(".loading-bg").hide();
	            $("#insStyleInfo").html("").html(data);
	        }
	    });
	});
</script>


</body>
</html>
