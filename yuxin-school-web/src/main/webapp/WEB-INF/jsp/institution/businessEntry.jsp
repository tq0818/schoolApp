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
    <title>商家入驻申请</title>
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
     <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/businessEntry.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>--%>
    <div class="right-side set-system" style="width: 100%">
        <div class="mainbackground nopadding" style="margin: 0 10px;">
            <div class="heading">
                <h2 class="h5">商家入驻申请</h2>
                <span class="line"></span>
            </div>
            <input id="dimFlag" value="0" type="hidden" />
            <form method="post" id="searchForm" class="userVideoListNew">
                    <div class="marginTop10 changeBtn" >
                        <span>处理状态</span>
                        <a href="javascript:void(0)" class="btn btn-default btn-primary" data-value="2">全部</a>
                        <a href="javascript:void(0)" class="btn btn-default " data-value="1">已处理</a>
                        <a href="javascript:void(0)" class="btn btn-default " data-value="0">未处理</a>
                    </div>
                    <div class="marginTop10  "  >
                        <span>申请时间</span>

                        <input type="text" name="startTime" id="startTime" class="date-picker from" />
                        <em>到</em>
                        <input type="text" name="endTime" id="endTime" class="date-picker to" />

                        <input type="text" placeholder="手机号" id="dimMobile"/>
                        <input type="text" placeholder="机构名称" id="dimInstitu"/>
                        <span><a href="javascript:dimMerchantEntry(1);" class="btn btn-primary" style="margin: 0 20px;">搜索</a></span>

                    </div>
            </form>
            <div class="user-list">
            </div>
        </div>
    </div>

    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
    <!--  ajax加载中div结束 -->
    <%--添加备注弹窗    --%>
    <div class="remarks">
        <textarea name="" id="content" placeholder="请输入备注信息"></textarea>
        <input id="updateId" type="hidden" value=""/>
        <div class="remarksBtn">
            <a href="javascript:void(0)" class="btn btn-primary">取消</a>
            <a href="javascript:updateMerchantEntryT()" class="btn btn-primary" id="updateNote">保存</a>
        </div>
    </div>

    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>

    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/businessEntry.js"></script>

</body>
</html>