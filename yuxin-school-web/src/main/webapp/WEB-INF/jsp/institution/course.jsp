<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>课程管理</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mine.css">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
    <style>
        .gobal-progress{display: none !important;} 
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/course.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">课程管理</h2>
                <span class="line"></span>
            </div>
            <div>
                <div class="chooseBtn">
                    <span>上下架状态</span>
                    <a href="##" class="btn btn-default btn-primary btn-mb">全部</a>
                    <a href="##" class="btn btn-default  btn-mb">已上架</a>
                    <a href="##" class="btn btn-default  btn-mb">已下架</a>
                </div>
                <div class="imgList" style="width: 80%;margin: auto;display: block;float: none;">
                    <ul>
                        <li class="addImg mienShow" id="">
                            <i class="icon iconfont"></i>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">取消推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm ">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">取消推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm ">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm ">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">取消推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm ">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">取消推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm ">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm ">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="pages pagination" style="padding-top: 10px;">

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






<script src="<%=rootPath %>/javascripts/riseschool/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/course.js"></script>



</body>
</html>
