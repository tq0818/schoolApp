<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>名师管理</title>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/famousTeacher.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5" style="display: inline-block;margin-bottom: 10px;">名师管理</h2>
                <a href="##" class="btn btn-primary" style="float: right;">添加教师</a>
                <span class="line"></span>
            </div>
            <div>

                <div class="teacherList">
                    <div class="headerTeacher">
                        <span style="margin-left: 20px;">唐老师</span>
                        <a href="##" class='btn btn-default delete' style="float: right;margin: 3px 50px 0 0;">删除</a>
                        <a href="" class='btn btn-default' style="float: right;margin: 3px 20px 0 0;">老师详情</a>
                    </div>
                    <div  class="contentTeacher">
                        <div>
                            <label>姓名:</label>
                            <span>唐老师</span>
                        </div>
                        <div>
                            <a href="##" class="btn btn-default">标签一</a>
                            <a href="##" class="btn btn-default">标签一</a>
                            <a href="##" class="btn btn-default">标签一</a>
                        </div>
                        <div>
                            <label>毕业院校:</label>
                            <span>中央美术学院</span>
                        </div>
                    </div>
                </div>
                <div class="teacherList">
                    <div class="headerTeacher">
                        <span style="margin-left: 20px;">唐老师</span>
                        <a href="##" class='btn btn-default delete' style="float: right;margin: 3px 50px 0 0;">删除</a>
                        <a href="" class='btn btn-default' style="float: right;margin: 3px 20px 0 0;">老师详情</a>
                    </div>
                    <div  class="contentTeacher">
                        <div>
                            <label>姓名:</label>
                            <span>唐老师</span>
                        </div>
                        <div>
                            <a href="##" class="btn btn-default">标签一</a>
                            <a href="##" class="btn btn-default">标签一</a>
                            <a href="##" class="btn btn-default">标签一</a>
                        </div>
                        <div>
                            <label>毕业院校:</label>
                            <span>中央美术学院</span>
                        </div>
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






<script src="<%=rootPath %>/javascripts/riseschool/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/riseschool/cutPic.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/famousTeacher.js"></script>



</body>
</html>
