<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增线下课程</title>
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

    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/basicInformation.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/elegantDemeanor.css">

    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/newLineCourse.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">新增线下课程</h2>
                <span class="line"></span>
            </div>
            <div class="addingMechanism" style="display: block;" id="newCourse">
                    <div>
                        <span>课程封面:</span>
                        <div>
                            <span id="imgTop"></span>

                            <input type="hidden" id="hidTop" />
                            <br/>
                            <form id="uploadForm" method="post" enctype="multipart/form-data" >
                                <a href="##" class="addPic" style="margin-top: 5px;margin-bottom: 20px;margin-left: 210px;" onchange="fileChange()" >
                                    添加图片
                                    <input type="file" name="imgData" accept="image/jpg, image/jpeg,image/png,image/bmp">
                                </a>
                            </form>
                        </div>
                    </div>
                    <div  class="imgList">
                        <span>课程风采:</span>
                        <ul style="display: inline-block;margin-left: 15px;">
                            <li class="addImg mienShow" id="">
                                <i class="icon iconfont"></i>
                            </li>
                            <li>
                                <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                                <span class="imgInfo">学校建筑内部图</span>
                               <%-- <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">置顶</a>--%>
                                <div class="listBg">
                                    <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                    <a href="javascript:void(0)" class="btn btn-success btn-sm ">修改</a>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div style="margin-top: 0;">
                        <span class="mechanismName">课程名称:</span>
                        <input type="text" style="width: 460px;margin-left: 10px;" maxlength="20" >
                    </div>
                    <div>
                        <span>描     述:</span>
                        <input type="text" style="width: 460px;margin-left: 15px;">
                    </div>
                    <div>
                        <span>标签:</span>
                        <div for="" style="display: inline-block;margin-left: 15px;">
                              <span href="##" class="systemBtn">
                                <input class="systemLabel" label-id="${label.id}" value="${label.labelName}">
                                <i class="icon iconfont deleteBtn">&#xe610;</i>
                            </span>
                            <span class="iconBtn addSystem">+</span>
                        </div>

                    </div>
                    <div>
                        <span>价格(元):</span>
                        <input type="text" placeholder="0.00" style="width: 460px;margin-left: 15px;">
                    </div>
                    <div>
                        <span>是否限定预约人数:</span>
                        <input type="radio" name="num" value="1" style="margin-left: 15px;">是
                        <input type="radio" name="num" value="1">否
                    </div>
                    <div>
                    <span class="mechanismName">课程详情:</span>
                    <p>
                        <textarea name="" id="" style="width: 710px;height: 186px;
                        border: 1px solid #aeaeae;margin-left: 10px;margin-top: 10px;" ></textarea>
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
<%--上传图片弹窗--%>
<div>

</div>



        <input id="underLineId" type="hidden" value="${underLineId}">
<script src="<%=rootPath %>/javascripts/riseschool/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/newLineCourse.js"></script>


</body>
</html>
