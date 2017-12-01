
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>首页banner图设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    <script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<%--已上架课程列表--%>
<div id="modelList" class="pageRecommendtionBg">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5" style="display: inline-block;margin: 10px 0;">banner管理</h2>
            <div class="bannerButton">
                <span><a href="javascript:;" class="btn btn-primary " >启用</a></span>
                <span><a href="javascript:;" class="btn btn-primary " >禁用</a></span>
                <span><a href="javascript:;" class="btn btn-primary changeOrder" >更改排序</a></span>
                <span><a href="javascript:;" class="btn btn-primary " >添加banner</a></span>
            </div>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <table class="table table-center" id="tableList">
                <tbody>
                <tr>
                    <th width="10%">序号</th>
                    <th width="20%">名称</th>
                    <th width="20%">描述</th>
                    <th width="20%">状态</th>
                    <th width="30%">操作</th>
                </tr>
                <tr>
                    <td>1<i class="icon iconfont">&#xe617;</i></td>
                    <td>巴拉巴拉</td>
                    <td>巴拉巴拉巴拉巴拉巴拉巴拉</td>
                    <td>已启用</td>
                    <td>
                        <a href="##" class="btn btn-danger forbidBanner">禁用</a>
                        <a href="##" class="btn btn-warning">修改</a>
                        <a href="##" class="btn btn-success">查看</a>
                    </td>
                </tr>
                <tr>
                    <td>2<i class="icon iconfont">&#xe61a;</i></td>
                    <td>巴拉巴拉</td>
                    <td>巴拉巴拉巴拉巴拉巴拉巴拉</td>
                    <td>已启用</td>
                    <td>
                        <a href="##" class="btn btn-danger forbidBanner">禁用</a>
                        <a href="##" class="btn btn-warning">修改</a>
                        <a href="##" class="btn btn-success">查看</a>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div class="pages">
        <ul class="pagination"></ul>
    </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!-- ajax加载中div结束 -->

<script>
//    点击禁用，弹窗提示
    $('.forbidBanner').click(function () {
        alert("只有启用banner才能支持排序！");
    });
//    点击更改培训
    $('.changeOrder').click(function () {
        if($(this).html()=='更改排序'){
            $(this).html('保存排序');
        }else {
            $(this).html('更改排序');
        }
    });
//    分页
$(".pagination").pagination('${courseList.rowCount}', {
    next_text : "下一页",
    prev_text : "上一页",
    current_page :'${courseList.pageNo-1}',
    link_to : "javascript:void(0)",
    num_display_entries : 8,
    items_per_page : '${courseList.pageSize}',
    num_edge_entries : 1,
    callback:function(page,jq){
        var pageNo = page + 1;
        reloadCurrunt(pageNo);
    }
});
</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('comBannerIndex');
    });
</script>
</body>
</html>
