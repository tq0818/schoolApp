<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>添加老师</title>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/addFamousTeacher.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">添加老师</h2>
                <span class="line"></span>
            </div>
            <div class="addingMechanism" style="display: block;">
                <div>
                    <span>老师头像</span>
                    <div style="padding-left: 100px;">
                        <img src="../../../images/institution/1.jpg" alt="" style="width: 100px;height: 100px;border-radius: 50px;">
                        <br/>
                        <a href="##" class="addPic">
                            添加头像
                            <input type="file" style="">
                        </a>
                    </div>
                </div>
                <div>
                    <span class="mechanismName">老师名称：</span>
                    <input type="text" style="width: 460px;" maxlength="10">
                </div>
                <div>
                    <span class="mechanismName">毕业院校：</span>
                    <input type="text" style="width: 460px;" maxlength="30">
                </div>
                <div>
                    <span>老师标签：</span>
                             <span href="##" class="systemBtn">
                                <input class="systemLabel">
                                <i class="icon iconfont deleteBtn">&#xe610;</i>
                            </span>
                    <span class="iconBtn addSystem">+</span>
                </div>
                <div>
                    <span class="mechanismName">老师简介：</span>
                    <p>
                        <textarea name="" id="" maxlength="300"  style="width: 390px;height: 84px;
                        border: 1px solid #aeaeae;margin-left: 70px;margin-top: 10px;" ></textarea>
                    </p>
                </div>
                <div class="orgBtn">
                    <a href="##" class="btn btn-primary btn-mb closeMechanism">取消</a>
                    <a href="##" class="btn btn-primary btn-mb closeMechanism">保存</a>
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
    <script src="<%=rootPath %>/javascripts/institution/addFamousTeacher.js"></script>



</body>
</html>
