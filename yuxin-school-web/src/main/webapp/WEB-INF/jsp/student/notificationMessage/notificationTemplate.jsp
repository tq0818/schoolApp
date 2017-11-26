<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>通知模板管理</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">通知模板管理</h2>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <table class="table table-center">
                <tr data-buy="true">
                    <th width="10%" title="">模板ID</th>
                    <th width="20%" title="">模板标题</th>
                    <th width="10%" title="">发布时间</th>
                    <th width="40%" title="">模板内容</th>
                    <th width="20%" title="">状态操作</th>
                </tr>
                <tr>
                    <td>M001</td>
                    <td>课程报名自动通知</td>
                    <td>2017/11/01 18:39</td>
                    <td>xxxxxxx</td>
                    <td>
                        <button class="btn btn-warning forbidBtn">禁用</button>
                        <button class="btn btn-primary editNoticeBtn">编辑</button>
                    </td>
                </tr>
                <tr>
                    <td>M001</td>
                    <td>课程报名自动通知</td>
                    <td>2017/11/01 18:39</td>
                    <td>xxxxxxx</td>
                    <td>
                        <button class="btn btn-warning forbidBtn">禁用</button>
                        <button class="btn btn-primary editNoticeBtn">编辑</button>
                    </td>
                </tr>
            </table>
            <div class="pages ">
                <ul class="pagination">

                </ul>
            </div>
        </div>
    </div>
</div>

<%--弹出框--%>
<div class="popupContainer">
    <span class="closePopupContainer">x</span>
    <div class="noticePopup">
        <h5>模板编辑:</h5>
        <textarea name="" id="" cols="30" rows="10" class="noticeArea"></textarea>
        <ul>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
            <li><button class="btn btn-primary">课程名称(bb)</button></li>
        </ul>
        <div class="noticePopupBtn">
            <button class="btn btn-success noticePopupSave">保存</button>
            <button class="btn btn-warning noticePopupCancel">取消</button>
        </div>
    </div>
</div>
<div class="popupOpacity"></div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
//    分页
$(".pagination").pagination($("#rowCount").val(), {
    next_text : "下一页",
    prev_text : "上一页",
    current_page : ($("#pageNo").val() - 1),
    link_to : "javascript:;",
    num_display_entries : 5,
    items_per_page : $("#pageSize").val(),
    num_edge_entries : 1,
    callback : function(page, jq) {
        var pageNo = page + 1;
    }
});

//禁用和启用
$('.forbidBtn').click(function(){
        if($(this).html()=='禁用'){
            $(this).html('启用');
        }else{
            $(this).html('禁用');
        }
    });

//点击编辑弹出弹窗
$('.editNoticeBtn').click(function(){
    $('.popupContainer').show();
    $('.popupOpacity').show();
});

//点击保存关闭弹窗，点击取消关闭弹窗
$('.noticePopupSave').click(function(){
    $('.popupContainer').hide();
    $('.popupOpacity').hide();
});
$('.noticePopupCancel').click(function(){
    $('.popupContainer').hide();
    $('.popupOpacity').hide();
});

</script>
</body>
</html>