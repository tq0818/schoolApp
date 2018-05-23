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
    <title>首页推荐分类管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/javascripts/itemTree/script/ztree/metroStyle.css">
    <style type="text/css">
        .pages li.disabled{padding:0px;}
        .userVideoListNew select{width: 180px;margin-right: 10px;}
        .right-side{overflow: auto;}
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/recommendationHomeC.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>--%>
    <div class="right-side set-system" style="width: 100%">
        <div class="mainbackground nopadding" style="margin: 0 10px;">
            <div class="heading">
                <h2 class="h5">首页推荐分类管理</h2>
                <span class="line"></span>
            </div>
            <div>
                <div class="tree_cntr">
                    <%--<input id="addCatg" type="button" value="添加分类">--%>
                        <ul id="ztree" class="ztree" style="margin-top: 10px; width: 200px; height: 150px;">
                        </ul>
                </div>
            </div>
            <div class="btnGroup" style="text-align: center;margin-top: 50px;">
                <a href="##" class="btn cancelBtn btn-primary">取消</a>
                <a href="##"  class="btn commit btn-primary">保存</a>
            </div>
        </div>
    </div>
    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
    <!--  ajax加载中div结束 -->

        <input id="recommendType" type="hidden" value="${recommendType}">

    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script src="<%=rootPath %>/javascripts/itemTree/script/ztree/jquery.ztree.all.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/recommendationHomeC.js"></script>
        <script type="text/javascript" src="<%=rootPath%>/javascripts/json2.js></script>

</body>
</html>