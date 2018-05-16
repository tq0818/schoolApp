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
    <style type="text/css">
        .pages li.disabled{padding:0px;}
        .userVideoListNew select{width: 180px;margin-right: 10px;}
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/classification.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>--%>
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
                        <th width="25%">二级分类</th>
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
            <img src="../../../images/institution/1.jpg" alt="">
            <br/>
            <a href="javascript:void(0);" class="btnFile">更改图标<input type="file"></a>
        </div>
        <div class="addTypeBtn">
            <a href="javascript:void(0);" class="btn btn-primary btn-xs closeAddType">取消</a>
            <a href="javascript:void(0);" class="btn btn-primary btn-xs" id="addConfirm">确认添加</a>
        </div>
    </div>
    <%--一级分类详情--%>
    <%--<div class="addType detailFirstPopup">
            <h5>一级分类详情</h5>
            <div style="margin-top: 10px;">
                <span>分类名称:</span>
                <input type="text" style="width: 260px;height: 20px;">
            </div>
            <div>
                <span>分类图标:</span>
                <img src="../../../images/institution/1.jpg" alt="">
                <br/>
                <a href="##" class="btnFile">更改图标<input type="file"></a>
            </div>
            <div class="addTypeBtn">
                <a href="##" class="btn btn-primary btn-xs closeAddType">取消</a>
                <a href="##" class="btn btn-primary btn-xs closeAddType">确认添加</a>
            </div>
        </div>--%>
    <%--添加二级分类--%>
    <%--<div class="addType addSeconPopup">
            <h5>添加二级分类</h5>
            <div style="margin-top: 10px;">
                <span>分类名称:</span>
                <input type="text" style="width: 260px;height: 20px;">
            </div>
            <div>
                <span>分类图标:</span>
                <img src="../../../images/institution/1.jpg" alt="">
                <br/>
                <a href="##" class="btnFile">更改图标<input type="file"></a>
            </div>
            <div class="addTypeBtn">
                <a href="##" class="btn btn-primary btn-xs closeAddType">取消</a>
                <a href="##" class="btn btn-primary btn-xs closeAddType">确认添加</a>
            </div>
        </div>--%>
    <%--二级分类详情--%>
   <%-- <div class="addType detailSeconPopup">
            <h5>二级分类详情</h5>
            <div style="margin-top: 10px;">
                <span>分类名称:</span>
                <input type="text" style="width: 260px;height: 20px;">
            </div>
            <div>
                <span>分类图标:</span>
                <img src="../../../images/institution/1.jpg" alt="">
                <br/>
                <a href="##" class="btnFile">更改图标<input type="file"></a>
            </div>
            <div class="addTypeBtn">
                <a href="##" class="btn btn-primary btn-xs closeAddType">取消</a>
                <a href="##" class="btn btn-primary btn-xs closeAddType">确认添加</a>
            </div>
        </div>--%>



    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/classification.js"></script>

</body>
</html>