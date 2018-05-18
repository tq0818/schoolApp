<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>机构分类管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <style type="text/css">
        .pages li.disabled{padding:0px;}
        .userVideoListNew select{width: 180px;margin-right: 10px;}




        .mienPopup{display: none;width: 740px;height: 440px;position: fixed;left: 50%;top: 50%;margin-left: -400px;margin-top: -250px;
            background: #fff;padding: 20px 30px 0;}
        .mienBtn{position: absolute;bottom: 20px;left: 0;text-align: center;width: 100% !important;}
        .mienBtn a{margin: 0 60px;}
        .coverPopup{width: 400px;margin-left: -200px;}
        .coverPopup .uploadImage{width: 100%;}
        .chooseImg{display: inline-block;width: 60px;height: 26px;text-align: center;line-height: 26px;position: relative;color: #fff;
        border: 1px solid #e0dfe3;border-radius: 3px;margin: 5px 0;}
        .chooseImg input{position: absolute;width: 100%;height: 100%;left: 0;top: 0;opacity: 0;}
        .coverPopup{z-index: 1000;}
        /*.mienPopup{display: block}*/
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/classification.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <div class="right-side set-system" style="width: 100%">
        <div class="mainbackground nopadding" style="margin: 0 10px;">
            <div class="heading">
                <h2 class="h5">机构分类管理</h2>
                <span class="line"></span>
            </div>
                <span style="font-size: 16px;margin-left: 50px;">分类管理</span>
                <a href="javascript:void(0);"  class="btn btn-mb btn-primary addFirstBtn" style="float: right;">添加一级分类</a>
            <div class="user-list">
                 <table class="table table-center" id="tableList">
                    <tr data-buy="true">
                        <th width="3%">序号</th>
                        <th width="5%">一级分类</th>
                        <th width="10%">二级分类</th>
                        <th width="5%">启用状态</th>
                        <th width="15%">操作</th>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
    <!--  ajax加载中div结束 -->
    <%--添加一级分类    --%>
    <div class="addType addFirstPopup">
        <h5 id="tittle">添加一级分类</h5>
        <div style="margin-top: 10px;">
            <span>分类名称:</span>
            <input type="text" style="width: 260px;height: 20px;" maxlength="5" id="insCatName">
        </div>
        <div>
            <span>分类图标:</span>
            <img id="target" src="" alt="">
            <br/>
            <a href="javascript:void(0);" class="btnFile"  >选择图标</a>
        </div>
        <div class="addTypeBtn">
            <a href="javascript:void(0);" class="btn btn-primary btn-xs closeAddType">取消</a>
            <a href="javascript:void(0);" class="btn btn-primary btn-xs" id="addConfirm">确认添加</a>
        </div>
    </div>

    <%--分类图片弹窗--%>
    <div class="mienPopup coverPopup">
        <div class="uploadImageStyle">
            <label for="">分类图片：</label>
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

    <input type="hidden" id="x" name="x" value="0"/>
    <input type="hidden" id="y" name="y" value="0"/>
    <input type="hidden" id="w" name="w" value="0"/>
    <input type="hidden" id="h" name="h" value="0"/>
    <input type="hidden" id="imgUrl"/>

    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/ajaxfileuploadR.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/riseschool/jm-qi.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/classification.js"></script>


</body>
</html>