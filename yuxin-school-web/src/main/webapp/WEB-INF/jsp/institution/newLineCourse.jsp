<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新增线下课程</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mine.css">s
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fonts/iconfont.css">
    <style>
        .gobal-progress {
            display: none !important;
        }

        .mienPopup{display: none;width: 740px;height: 440px;position: fixed;left: 50%;top: 47%;margin-left: -400px;margin-top: -250px;
            background: #fff;padding: 20px 30px 0;}
        .mienBtn{position: absolute;bottom: 20px;left: 0;text-align: center;width: 100% !important;}
        .mienBtn a{margin: 0 60px;}
        .coverPopup{width: 400px;margin-left: -200px;}
        .coverPopup .uploadImage{width: 100%;}
        .chooseImg{display: inline-block;width: 60px;height: 26px;text-align: center;line-height: 26px;position: relative;color: #fff;
            border: 1px solid #e0dfe3;border-radius: 3px;margin: 5px 0;}
        .chooseImg input{position: absolute;width: 100%;height: 100%;left: 0;top: 0;opacity: 0;}
        .coverPopup{z-index: 1000;}
        .mienPopup>div {
            width: 100%;
        }
         
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

                        <input type="hidden" id="hidTop"/>
                        <br/>
                        <form id="uploadForm" method="post" enctype="multipart/form-data">
                            <a href="javascript:void(0);" class="addPic addPicFace" style="margin-top: 5px;margin-bottom: 20px;margin-left: 120px;"
                               onclick="popAddImg(1);">
                                添加封面
                                <%--<input type="file" name="imgData" accept="image/jpg, image/jpeg,image/png,image/bmp">--%>
                            </a>
                        </form>
                    </div>
                </div>
                <div class="imgList">
                    <span>课程风采:</span>
                    <ul id="styleContainer" style="display: inline-block;">
                        <li class="addImg mienShow openPopup" id="">
                            <i class="icon iconfont"></i>
                        </li>

                    </ul>
                </div>
                <div style="margin-top: 0;">
                    <span class="mechanismName">课程名称:</span>
                    <input id="className" type="text" style="width: 460px;margin-left: 10px;" maxlength="30">
                </div>
                <div>
                    <span>描     述:</span>
                    <input id="classSummary" type="text" maxlength="30" style="width: 460px;margin-left: 15px;">
                </div>
                <div>
                    <span>标签:</span>
                    <div id="labelContainer" for="" style="display: inline-block;margin-left: 15px;">
                            <span id="spanContainerLabel" style="display: inline">

                            </span>
                        <span class="iconBtn addSystem addSystemBtn">+</span>
                    </div>

                </div>
                <div>
                    <span>价格(元):</span>
                    <input type="text" id="classPrice" maxlength="9" placeholder="0.00"
                           style="width: 460px;margin-left: 15px;">
                </div>
                <div>
                    <span>是否限定预约人数:</span>
                    <input type="radio" name="classLimitNum" id="classLimitNumYes" class="limitYes" value="1" style="margin-left: 15px;">是
                    <input type="radio" name="classLimitNum" id="classLimitNumNo" class="limitNo" value="0" checked="checked">否
                </div>
                <div style="display: none" id="limitContainer">
                    <span><b style="color:red;">*</b>限定预约人数:</span>
                    <input type="text" id="classPersonLimit" maxlength="9" style="width: 460px;margin-left: 15px;">
                </div>
                <div>
                    <span class="mechanismName"><b style="color:red;">*</b>课程详情:</span>
                    <p>
                        <textarea name="" id="classDetail" style="width: 710px;height: 186px;
                        border: 1px solid #aeaeae;margin-left: 10px;margin-top: 10px;" maxlength="300"></textarea>
                    </p>
                </div>
                <div class="orgBtn">
                    <a href="javascript:void(0)" class="btn btn-primary btn-mb closeMechanism closeMechanismCancel">取消</a>
                    <a href="javascript:void(0)" class="btn btn-primary btn-mb closeMechanism closeMechanismCommit">保存</a>
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
    <!-- 封面图片上传 -->
    <div class="elePic" id='cover' style="height: 500px;">
        <h5>上传图片</h5>
        <div>
                <span id="coverReturn">

                </span>
            <input type="hidden" id="hidCoverTop" value="用来放置图片地址"/>
            <input type="hidden" id="hidCoverFid" value="用来放置id"/>
            <input type="hidden" id="hidCoverSort" value="用来存放这是第几个"/>
            <br/>
            <form id="uploadFormCover" method="post" enctype="multipart/form-data">
                <a href="javascript:void(0);" class="addPic" style="margin-top: 5px;margin-bottom: 20px;margin-left: 210px;"
                   onclick="popAddImg();">
                    添加图片
                    <%--<input type="file" id="fileUploadInput" name="imgData"--%>
                           <%--accept="image/jpg, image/jpeg,image/png,image/bmp">--%>
                </a>
            </form>
        </div>
        <div class="eleBtn">
            <a href="javascript:void(0)" class="btn btn-primary closeElePicCancel">取消</a>
            <a href="javascript:void(0)" class="btn btn-primary  closeElePicCommit">保存</a>
        </div>
    </div>


    <%--切图弹窗--%>
    <div class="mienPopup coverPopup">
        <div class="uploadImageStyle">
            <label for="" id="imgTittle">选择课程封面：</label>
            <img src="" alt="" id="targetStyle" style="width: 400px;height: 300px;">
            <a href="javascript:void(0);" class="chooseImg">
                选择图片
                <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgDataStyle" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic()" value="重新选择文件"/>
            </a>
        </div>
        <div class="mienBtn">
            <a href="javascript:void(0)" class="btn btn-sm btn-danger mienHide">取消</a>
            <a href="javascript:void(0)" class="btn btn-sm btn-success mienHide" onclick="saveCutPic();">确定</a>
        </div>
    </div>
        <input type="hidden" id="imgType"/>

    <input type="hidden" id="x" name="x" value="0"/>
    <input type="hidden" id="y" name="y" value="0"/>
    <input type="hidden" id="w" name="w" value="0"/>
    <input type="hidden" id="h" name="h" value="0"/>

    <input id="insId" type="hidden" value="${insId}">
    <input id="underLineId" type="hidden" value="${underLineId}">

    <script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/ajaxfileuploadR.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/insBaseCut.js"></script>
    <script src="<%=rootPath %>/javascripts/institution/newLineCourse.js"></script>
    <script src="<%=rootPath %>/javascripts/institution/newLineCourseListener.js"></script>
    <script src="<%=rootPath %>/javascripts/json2.js"></script>


</body>
</html>
