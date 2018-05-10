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
                        <li class="addImg mienShow" id="">
                            <i class="icon iconfont"></i>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">封面图片</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                    </ul>

                </div>
                <br/>
                <div style="height: 260px;" class="imgList">
                    <span  class="labelName"> 视频:</span>
                    <ul style="display: inline-block;">
                        <li class="addImg mienShow" id="videoUp">
                            <i class="icon iconfont"></i>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-success btn-sm rightShow">视频</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <br/>
                <div class="imgList">
                    <span class="labelName">风采展示:</span>
                    <ul style="display: inline-block;width: 1160px;">
                        <li class="addImg mienShow" id="eleShow">
                            <i class="icon iconfont"></i>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                            </div>
                        </li>
                    </ul>
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

<%--视频上传弹窗--%>
<div class="videoUpload">
    <h5>视频</h5>
    <div>
        <span style="padding-left: 6px;">视频封面:</span>
        <img src="../../../images/institution/1.jpg" alt="" style="width: 136px;height: 116px;">
        <br/>
        <a href="##" class="addPic" style="margin-left: 97px;margin-top: 5px;">
            添加图片
            <input type="file">
        </a>
    </div>
    <div>
        <span class="videoIntro">视频名称:</span>
        <input type="text" style="width: 248px;height: 24px;">
        <a href="##" class="addVideo">
            +
            <input type="file">
        </a>
    </div>
    <div>
        <span class="videoIntro">视频描述:</span>
        <textarea name="" placeholder="请输入视频描述(最多200个字)" class="writeWord" maxlength="200"></textarea>
    </div>
    <div class="eleBtn">
        <a href="##" class="btn btn-primary closeVideoUpload">取消</a>
        <a href="##" class="btn btn-primary closeVideoUpload">保存</a>
    </div>
</div>
<%--风采上传弹窗--%>
<div class="elePic">
    <h5>风采图片</h5>
    <div>
        <img src="../../../images/institution/1.jpg" alt="" style="width: 300px;height: 300px;">
        <br/>
        <a href="##" class="addPic" style="margin-top: 5px;margin-bottom: 20px;">
            添加图片
            <input type="file">
        </a>
    </div>
    <div>
        <span >图片描述:</span>
        <textarea name="" placeholder="请输入图片描述(最多60个字)" class="writeWord" maxlength="60"></textarea>
    </div>
    <div class="eleBtn">
        <a href="##" class="btn btn-primary closeElePic">取消</a>
        <a href="##" class="btn btn-primary closeElePic">保存</a>
    </div>
</div>




<script src="<%=rootPath %>/javascripts/riseschool/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/elegantDemeanor.js"></script>



</body>
</html>
