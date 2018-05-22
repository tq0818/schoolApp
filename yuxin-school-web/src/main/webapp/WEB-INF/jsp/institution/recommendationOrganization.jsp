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
    <title>推荐机构管理</title>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/recommendationOrganization.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>--%>
    <div class="right-side set-system" style="width: 100%">
        <div class="mainbackground nopadding" style="margin: 0 10px;">
            <div class="heading">
                <h2 class="h5">推荐机构管理</h2>
                <span class="line"></span>
            </div>
            <div id="recommendBtn" class="changeBtn">
                <span>推荐位置</span>
                <a href="##" class="btn btn-default recommendType btn-mb btn-primary">首页分类推荐</a>
                <a href="##" class="btn btn-default btn-mb">首页列表推荐</a>
            </div>
            <div class="classificationRecommendation">
                <div style="margin: 30px 0 10px 50px;">
                    <span>推荐分类管理</span>
                    <a href="/InsInfoBase/recommendationHomeC/0" class="btn btn-primary" style="float: right;">添加推荐分类</a>
                </div>
                <div class="user-list">
                    <table class="table table-center" >
                        <tr data-buy="true">
                            <th width="3%">序号</th>
                            <th width="12%">推荐分类</th>
                            <th width="5%">所属级别</th>
                            <th width="5%">排序</th>
                            <th width="5%">操作</th>
                        </tr>
                        <tbody id="typeTbody">

                        </tbody>
                    </table>
                    <div class="pages paginationType pagination">
                    </div>
                </div>
            </div>
            <div class="listRecommendation">
                <div>
                    <div class="marginTop10 changeBtn" id="recommendBtnContainor" >
                        <span>推荐分类</span>

                        <span>
                            <a href="/InsInfoBase/recommendationHomeC/1" style="color: red;">管理</a>
                        </span>
                    </div>
                    <div  class="marginTop10 changeBtn" id="recommendStatusContainor">
                        <span>推荐状态</span>
                        <a href="##" class="btn recommendStatusBtn btn-default btn-primary btn-mb">全部</a>
                        <a href="##" class="btn recommendStatusBtn btn-default btn-mb">已推荐</a>
                        <a href="##" class="btn recommendStatusBtn btn-default btn-mb">未推荐</a>
                    </div>
                    <div  class="marginTop10" >
                        <input type="text" id="recommendName" maxlength="30" placeholder="请输入机构名称">
                        <a href="##" class="btn btnSearch btn-primary btn-sm">搜索</a>
                    </div>
                </div>
                <div class="user-list">
                    <table class="table table-center" >
                        <tr data-buy="true">
                            <th width="3%">序号</th>
                            <th width="12%">机构名称</th>
                            <th width="5%">所属分类</th>
                            <th width="5%">分类级别</th>
                            <th width="5%">推荐状态</th>
                            <th width="5%">排序</th>
                            <th width="30%">操作</th>
                        </tr>
                        <tbody id="indexRecommendTbody"></tbody>


                    </table>
                    <div class="pages pagination paginationIndexRecommend">
                    </div>
                </div>
            </div>

        </div>
    </div>
    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
    <!--  ajax加载中div结束 -->



    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
        <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/recommendationOrganizationList.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/recommendationOrganization.js"></script>
        <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/recommendationOrganizationType.js"></script>

</body>
</html>