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
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/evaluation.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5" style="display: inline-block;">评价管理</h2>
                <span class="evaTitle active" style="">机构评价</span>
                <span class="evaTitle" >课程评价</span>
                <span class="line"></span>
            </div>
            <div class="evaScreen">
                <div style="padding-left: 13px;margin: 30px 0;" >
                    <span style="font-size: 16px;margin-right: 30px;">课程名称</span>
                    <a href="##" class="btn btn-default btn-primary">课程名称一</a>
                    <a href="##" class="btn btn-default">课程名称一</a>
                    <a href="##" class="btn btn-default">课程名称一</a>
                    <a href="##" class="btn btn-default">课程名称一</a>
                </div>
                <div style="padding-left: 13px;" >
                    <span style="font-size: 16px;margin-right: 30px;">审核状态</span>
                    <a href="##" class="btn btn-default btn-primary">全部</a>
                    <a href="##" class="btn btn-default">待审核</a>
                    <a href="##" class="btn btn-default">审核通过</a>
                </div>
                <ul class="comment_all">
                    <li class="Y_clear">
                        <div class="headpic">
                            <img src="/images/teachers.png" alt="" width="50" height="50">
                        </div>
                        <div class="Y_backcomment_content">
                            <div class="word Y_clear">
                                <span>f739aab6：</span>
                                <span class="wordcontent" style="word-break:break-all">很不错！孩子很喜欢陶老师讲的课</span>
                            </div>
                            <p class="Y_time Y_mt10">
                                <span>2018-05-06 </span>
                                <span>22:12</span>
                                <span>评分:</span>
                                <span class="Y_mr10" style="color: #fb9f1b;">
                                    <i class="iconfont"></i><i class="iconfont"></i><i class="iconfont"></i><i class="iconfont"></i><i class="iconfont"></i>
                                </span>
                                <span>老师:<a href="javascript:void(0);" class="teacherName" teacherid="1083">陶菲</a></span>
                            </p>
                        </div>
                        <button class="delete " id="">删除</button>
                    </li>
                </ul>
                <div class="pages pagination"></div>
            </div>
            <div class="curriculum "></div>
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
<script src="<%=rootPath %>/javascripts/institution/evaluation.js"></script>



</body>
</html>
