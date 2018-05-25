<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>评论管理</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mine.css">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fonts/iconfont.css">
    <style>
        .gobal-progress{display: none !important;} 
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/teacher.css">
    <style type="text/css">
        .iconfont{
            font-size: 13px;
            cursor: auto;
        }
        .left{
            text-align: right;
        }
        .checkStatus li{
            display: inline-block;
            margin: 0 10px;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/evaluationList.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>--%>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5" style="display: inline-block;">评论管理</h2>
                <span class="evaTitle insComment" style="">机构评论</span>
                <span class="evaTitle insClassComment" >课程评论</span>
                <span class="line"></span>
            </div>
            <div class="evaScreen">
                <div style="padding-left: 13px;" id ="status">
                    <span style="font-size: 16px;margin-right: 30px;">审核状态</span>
                    <a href="javascript:void(0)" class="btn btn-default btn-primary" data-review="">全部</a>
                    <a href="javascript:void(0)" class="btn btn-default" data-review="0">待审核</a>
                    <a href="javascript:void(0)" class="btn btn-default" data-review="1">审核通过</a>
                </div>
                <ul class="comment_all">

                </ul>
                <div class="pages pagination"></div>
            </div>


            <div class="curriculum ">
                <div style="padding-left: 13px;margin: 30px 0;" id="curriculumClass" >
                    <span style="font-size: 16px;margin-right: 30px;">课程名称</span>
                    <a href="javascript:void(0)" class="btn btn-default btn-primary">全部</a>
                    <c:forEach var="cla" items="${classTypeVos}">
                        <a href="javascript:void(0)" class="btn btn-default"  data-classId="${cla.id}">${cla.name}</a>
                    </c:forEach>
                </div>
                <div style="padding-left: 13px;" id="curriculumState">
                    <span style="font-size: 16px;margin-right: 30px;">审核状态</span>
                    <a href="javascript:void(0)" class="btn btn-default btn-primary" data-review="">全部</a>
                    <a href="javascript:void(0)" class="btn btn-default" data-review="0">待审核</a>
                    <a href="javascript:void(0)" class="btn btn-default" data-review="1">审核通过</a>
                </div>
                <ul class="comment_all">

                </ul>
                <div class="pages paginationClass"></div>
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
<script src="<%=rootPath %>/javascripts/riseschool/cutPic.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/evaluationList.js"></script>



</body>
</html>
