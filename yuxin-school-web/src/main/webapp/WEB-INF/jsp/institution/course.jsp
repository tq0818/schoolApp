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
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
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
                <h2 class="h5 courseLine active" style="display: inline-block;cursor: pointer;">线下课程管理</h2>
                <span class="courseLine" style="font-size: 18px;color: #333;cursor: pointer;">在线课程管理</span>
                <span class="line"></span>
            </div>
            <div class="courseUnderLine">
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
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">取消推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                        <li>
                            <img src="../../../images/institution/1.jpg" alt="" style="width: 100%;height: auto">
                            <span class="imgInfo">学校建筑内部图</span>
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">推荐</a>
                            <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm ">下架</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm " >管理</a>
                            </div>
                        </li>
                    </ul>
                </div>
                <div class="pages pagination" style="padding-top: 10px;">

                </div>
            </div>
            <div class="courseOnLine">
                <span style="color: #a1a1a1;">功能说明：该功能用将卓鹿的“在线课程”和当前机构进行关联，便于用户查看属于该机构的在线视频课程</span>
                <div class="chooseBtn">
                    <span>上下架状态</span>
                    <a href="##" class="btn btn-default btn-primary btn-mb">全部</a>
                    <a href="##" class="btn btn-default  btn-mb">已上架</a>
                    <a href="##" class="btn btn-default  btn-mb">已下架</a>
                </div>
                <a href="##" class="btn btn-primary btn-mb addCourse" style="float: right;margin-right: 200px;margin-bottom: 10px;">添加课程</a>
                <div style="width: 80%;margin: auto;">
                    <table class="table table-center" id="tableList">
                        <tbody>
                        <tr data-buy="true">
                            <th width="3%">序号</th>
                            <th width="12%">课程名称</th>
                            <th width="5%">上架状态</th>
                            <th width="5%">排序</th>
                            <th width="5%">操作</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>如何写好作文</td>
                            <td>已上架</td>
                            <td>1</td>
                            <td>上架</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="pages pagination paginationOnLine" style="padding-top: 10px;">
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
<%--添加课程弹窗--%>
<div class="addClassPopup">
    <h5>
        添加课程
        <a href="##" class="btn btn-primary btn-xs closeCoursePopup">关闭</a>
    </h5>
    <div style="text-align: center;margin: 10px;">
        <span>课程名称：</span>
        <input type="text" placeholder="请输入课程名称" style="width: 300px;height: 25px;margin-right: 5px;">
        <a href="##" class="btn btn-sm btn-primary">搜索</a>
    </div>
    <div>

        <div style="width: 80%;margin: auto;">
            <span style="color: #868686;">搜索结果：</span>
                <table class="table table-center" >
                    <tbody>
                    <tr>
                        <th width="3%">序号</th>
                        <th width="12%">课程名称</th>
                        <th width="12%">学科</th>
                        <th width="15%">所属学校</th>
                        <th width="5%">操作</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>行程问题</td>
                        <td>物理</td>
                        <td>成都芳草附属小学</td>
                        <td><a href="##" class="btn btn-primary btn-xs addConfirm">添加</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>

    </div>

</div>





<script src="<%=rootPath %>/javascripts/riseschool/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/course.js"></script>



</body>
</html>
