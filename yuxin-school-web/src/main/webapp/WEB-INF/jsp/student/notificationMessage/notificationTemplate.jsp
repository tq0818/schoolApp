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
                    <th width="10%" >模板ID</th>
                    <th width="10%" >模板标题</th>
                    <th width="20%" >发布时间</th>
                    <th width="40%" >模板内容</th>
                    <th width="20%" >状态操作</th>
                </tr>
                <c:forEach items="${pageFinder.data}" var="ntv">
                    <tr>
                        <td>${ntv.noticeCode}</td>
                        <td>${ntv.noticeTopic}</td>
                        <td id="time_${ntv.id}">${ntv.publishTime}</td>
                        <c:choose>
                            <c:when test="${fn:length(ntv.noticeContent)>25}">
                                <td title="${ntv.noticeContent}" id="content_${ntv.id}">${fn:substring(ntv.noticeContent, 0, 25)}...</td>
                            </c:when>
                            <c:otherwise>
                                <td id="content_${ntv.id}">${ntv.noticeContent}</td>
                            </c:otherwise>

                        </c:choose>

                        <td>
                            <c:choose>
                                <c:when test="${ntv.noticeStatus eq 1}">
                                    <button updateId="${ntv.id}" status="${ntv.noticeStatus}" class="btn btn-warning forbidBtn">禁用</button>
                                </c:when>
                                <c:otherwise>
                                    <button updateId="${ntv.id}" status="${ntv.noticeStatus}" class="btn btn-success forbidBtn">启用</button>
                                </c:otherwise>
                            </c:choose>
                            <button class="btn btn-primary editNoticeBtn " id="${ntv.id}">编辑</button>
                        </td>
                    </tr>
                </c:forEach>
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
    <span class="closePopupContainer" onclick="cancelPopup();">x</span>
    <div class="noticePopup">
        <h5>模板编辑:</h5>
        <textarea cols="30" name="noticeArea" rows="10" class="noticeArea" id="noticeArea" editId="" onfocus="clearContent($(this));" onblur="fillContent($(this))">示例:感谢(aa)报名参加(bb)课程，请您按时(cc)登录直播课程学习。</textarea>
        <ul>
            <li><button class="btn btn-primary" onclick="fillTemplate($(this));" code="(aa)">用户名称(aa)</button></li>
            <li><button class="btn btn-primary" onclick="fillTemplate($(this));" code="(bb)">课程名称(bb)</button></li>
            <li><button class="btn btn-primary" onclick="fillTemplate($(this));" code="(ff)">课次名称(gg)</button></li>
            <li><button class="btn btn-primary" onclick="fillTemplate($(this));" code="(cc)">上课时间(cc)</button></li>
            <li><button class="btn btn-primary" onclick="fillTemplate($(this));" code="(dd)">积分变更名目(dd)</button></li>
            <li><button class="btn btn-primary" onclick="fillTemplate($(this));" code="(ee)">变化积分值(ee)</button></li>
            <li><button class="btn btn-primary" onclick="fillTemplate($(this));" code="(ff)">现有积分值(ff)</button></li>
        </ul>
        <div class="noticePopupBtn">
            <button class="btn btn-success noticePopupSave">保存</button>
            <button class="btn btn-warning noticePopupCancel" onclick="cancelPopup();">取消</button>
        </div>
    </div>
</div>
<div class="popupOpacity"></div>
<form id="nextPage" method="post">
    <input id="pageNo" type="hidden" name="page">
</form>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
var example = "示例:感谢(aa)报名参加(bb)课程，请您按时(cc)登录直播课程学习。";
//    分页
$(".pagination").pagination('${pageFinder.rowCount}', {
    next_text : "下一页",
    prev_text : "上一页",
    current_page : '${pageFinder.pageNo-1}',
    link_to : "javascript:;",
    num_display_entries : 5,
    items_per_page : '${pageFinder.pageSize}',
    num_edge_entries : 1,
    callback : function(page, jq) {
        var pageNo = page + 1;
        $("#pageNo").val(pageNo);
        console.log(pageNo);
        $("#nextPage").attr("action","<%=rootPath %>"+"/student/notificationTemplate").submit();

    }
});
//点击编辑框下面的按钮填充代码
function fillTemplate(obj){
    var noticeArea = $("#noticeArea").val();
    if($.trim(noticeArea)==example){
        noticeArea="";
    }
    var code = obj.attr("code");
    noticeArea+=code;
    $("#noticeArea").val(noticeArea).focus();
}

//禁用和启用
$('.forbidBtn').click(function(){
        var obj = $(this);
        var noticeStatus = obj.attr("status");
        var noticeMsg = "";
        if("1"==noticeStatus){
            noticeMsg="您确定要禁用该条模版?";
        }else{
            noticeMsg="您确定要启用该条模版?";
        }
        if(!window.confirm(noticeMsg)){
            return;
        }
        var id = $(this).attr("updateId");

        $.ajax({
            url :  "<%=rootPath %>"+"/student/updateNoticeTemplatStatus",
            type : "post",
            data : {"id":id,"noticeStatus":noticeStatus},
            success : function(data) {
                var time = data.nowDate;
                var result = data.status;
                if(obj.html()=='禁用'){
                    if(result=="1"){
                        obj.removeClass('btn-warning');
                        obj.addClass('btn-success');
                        obj.html('启用');
                        obj.attr("status","0");
                        alert("操作成功");
                    }else{
                        alert("操作失败");
                    }
                }else{
                    if(result=="1"){
                        obj.removeClass('btn-success');
                        obj.addClass('btn-warning');
                        obj.html('禁用');
                        obj.attr("status","1");
                        $("#time_"+id).html(time);
                        alert("操作成功");
                    }else{
                        alert("操作失败");
                    }

                }
            }
        });
    });

//点击编辑弹出弹窗
$('.editNoticeBtn').click(function(){
    $('.popupContainer').show();
    $('.popupOpacity').show();
    $("#noticeArea").attr("editId",$(this).attr("id"));
});

//点击保存关闭弹窗，点击取消关闭弹窗
$('.noticePopupSave').click(function(){
    if($("#noticeArea").val()){
        var editId = $("#noticeArea").attr("editId");
        console.log(editId);
        var tempContent = $("#noticeArea").val();
        if(example==tempContent){
            alert("请填写好模版内容后点击保存数据");
            return;
        }
        $.ajax({
            url :  "<%=rootPath %>"+"/student/updateNoticeTemplateContent",
            type : "post",
            data : {"id":editId,"noticeContent":tempContent},
            success : function(result) {
               if("1"==result){
                   if(tempContent.length>25){
                       $("#content_"+editId).attr("title",tempContent);
                       tempContent=tempContent.substring(0,25)+"...";
                   }
                   $("#content_"+editId).html(tempContent);
                   $('.popupContainer').hide();
                   $('.popupOpacity').hide();
                   alert("保存成功");
                   $("#noticeArea").val(example);
                   $("#noticeArea").attr("editId","");
               }else{
                   alert("保存失败");
               }
            }
        });
    }else{
        alert("请填写好模版内容后点击保存数据");
        return;
    }
});
//取消弹框
function cancelPopup(){
    $('.popupContainer').hide();
    $('.popupOpacity').hide();
    $("#noticeArea").val(example);
    $("#noticeArea").attr("editId","");
}

//清楚内容
function clearContent(obj){
    if($.trim(obj.val())==example){
        obj.val("");
    }
}
//填充编辑框内容
function fillContent(obj){
   if($.trim(obj.val())==""){
       obj.val(example);
   }

}
</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('notificationTemplate');
    });
</script>
</body>
</html>