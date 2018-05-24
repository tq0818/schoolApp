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
                <h2 class="h5" id="teacherTitle">添加老师</h2>
                <span class="line"></span>
            </div>
            <div class="addingMechanism" style="display: block;">
                <div>
                    <span>老师头像</span>
                    <div style="padding-left: 100px;">
                        <span id="imgTop">

                        </span>

                        <br/>

                        <form id="uploadForm" method="post" enctype="multipart/form-data">
                            <input type="hidden" id="hidHeadImg" >
                            <a href="javascript:void(0);" class="addPic"   onclick="popAddImg();">
                                添加头像
                                <%--<input type="file" name="imgData" style="" accept="image/jpg, image/jpeg,image/png,image/bmp">--%>
                            </a>

                        </form>


                    </div>
                </div>
                <div>
                    <span class="mechanismName">老师名称：</span>
                    <input type="text" id="teacherName" style="width: 460px;" maxlength="10">
                </div>
                <div>
                    <span class="mechanismName">毕业院校：</span>
                    <input type="text" id="teacherSchool" style="width: 460px;" maxlength="30">
                </div>
                <div>
                    <span>老师标签：</span>
                            <span id="teacherLabelsContainer">
                                <span class="iconBtn addSystem">+</span>
                            </span>
                </div>
                <div>
                    <span class="mechanismName">老师简介:</span>
                    <p>
                        <textarea name="" id="summary" maxlength="300"  style="width: 390px;height: 84px;
                        border: 1px solid #aeaeae;margin-left: 70px;margin-top: 10px;" ></textarea>
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

        <%--分类图片弹窗--%>
        <div class="mienPopup coverPopup">
            <div class="uploadImageStyle">
                <label for="">选择老师头像-建议上传图片尺寸为100*100px：</label>
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

<input type="hidden" id="hidId" />
        <input type="hidden" id="x" name="x" value="0"/>
        <input type="hidden" id="y" name="y" value="0"/>
        <input type="hidden" id="w" name="w" value="0"/>
        <input type="hidden" id="h" name="h" value="0"/>


    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/ajaxfileuploadR.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/insBaseCut.js"></script>
    <script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
    <script src="<%=rootPath %>/javascripts/institution/addFamousTeacher.js"></script>
    <script src="<%=rootPath %>/javascripts/json2.js"></script>


</body>
</html>
