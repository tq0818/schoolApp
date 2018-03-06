<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<!doctype html>
<html lang="zh-cn">
<html>
<head>
    <title>Title</title>
    <style>
        table{
            word-wrap: break-word; word-break: break-all;
        }
    </style>
</head>
<body>
<input type="hidden" id="dimFlag" value="${dimFlag}">
<input type="hidden" id="passPageNo" value="${data.pageNo}">
<table class="table table-center" id="tableList">
		<tr data-buy="true">
			<th width="5%">序号</th>
			<th width="5%">姓名</th>
			<th width="5%">性别</th>
			<th width="11%">毕业学校</th>
			<th width="11%">申请学校</th>
			<th width="8%">手机号</th>
			<th width="8%">出生日期</th>
			<th width="10%">户籍详细地址</th>
			<th width="9%" class="btn-sort">
				提交时间
			</th>
			<th width="8%">审核状态</th>
			<th width="9%">学生编号</th>
			<th width="20%">操作</th>
		<%--	<th style="display:none" width="0%"></th>--%>
		</tr>
		<c:forEach items="${data.data}" var="list" varStatus="status">
		<tr>
			<td>${status.index+1}</td>
			<td>${list.studentName}</td>
			<td>${list.sex}</td>
			<td>${list.schoolTag}</td>
			<td>${list.schoolName}</td>
			<td>${list.mobile}</td>
			<td>${list.birthday}</td>
			<td>
				<div class="detailSite">
					${list.censusDetAddress}
				</div>
			</td>
			<td><fmt:formatDate value="${list.putTime}"  pattern="yyyy-MM-dd HH:mm"/></td> 
			<c:if test="${list.isCheck eq 1}">
			<td>待审核</td>
			</c:if>
			<c:if test="${list.isCheck eq 2}">
			<td>已通过</td>
			</c:if>
			<c:if test="${list.isCheck eq 0}">
			<td>未通过</td>
			</c:if>
			<td>${list.studentNo}</td>
			<td>
					<a href="javascript:void(0)" class="pass" id="${list.id}">通过</a>|
					<a href="javascript:void(0)" class="noPass" id="${list.id}">不通过</a>|
					<a href="javascript:watch(${list.id})">查看</a>
				<%-- <c:choose>
					<c:when test="${list.isCheck eq 1}">
					<a href="javascript:void(0)" class="pass" id="${list.id}">通过</a>|
					<a href="javascript:void(0)" class="noPass" id="${list.id}">不通过</a>|
					<a href="javascript:watch(${list.id})">查看</a>
					</c:when>
					<c:otherwise>
					<a href="javascript:watch(${list.id})">查看</a>
					</c:otherwise>
				</c:choose> --%>
			</td>
		</tr>
		</c:forEach>
</table>
<div class="pages pagination">

</div>
<%--通过确认--%>
<div class="opacityPopup"></div>
<div class="confirmPopup">
	<div>是否确认通过该学生的申请？</div>
	<a href="javascript:void(0)" class="cancel hidePopup">取消</a>
	<a href="javascript:pass()" class="confirmPass hidePopup">确认通过</a>
</div>

<%--不通过原因选择--%>
<div class="reason">
    <h5>不通过原因</h5>
    <ul>
        <li><input type="checkbox"><span>原因一</span></li>
        <li><input type="checkbox"><span>原因二</span></li>
        <li><input type="checkbox"><span>原因三</span></li>
        <li><input type="checkbox"><span>原因四</span></li>
        <li><input type="checkbox"><span>其他</span></li>
    </ul>
    <textarea name="" id="" cols="30" rows="10" class="descriptWord"placeholder="请输入原因，最多60个字。"
    maxlength="60"></textarea>
    <div class="btnGroup">
        <a href="javascript:void(0)" class="btn btn-sm btn-default hidePopup">取消</a>
        <a href="javascript:noPass()" class="btn btn-sm btn-primary hidePopup">确定</a>
    </div>
</div>
</body>
</html>
<script>
    //分页
    $(".pagination").pagination('${data.rowCount}',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page: '${data.pageNo - 1}',
            link_to: "javascript:void(0)",
            num_display_entries: 8,
            items_per_page: 10,
            num_edge_entries: 1,
            callback: function (page, jq) {
                var pageNo = page + 1;
                queryStudentApply(pageNo);

            }
        }
    );
  //提交时间排序
    $('table').on('click','.btn-sort',function () {
    	var timeOrder = $("#timeOrder").val();
    	if(timeOrder == 1){
    		$("#timeOrder").val(2);
    	}else{
    		$("#timeOrder").val(1);
    	}
    	queryStudentApply(1);
    });
  //点击通过显示弹窗
  var passId;
  var noPassId;
     $('.pass').click(function () {
    	 passId = $(this).attr('id'); 
        $('.confirmPopup').fadeIn();
        $('.opacityPopup').fadeIn();
    });
    //点击取消或者确认通过隐藏弹窗
    $('.hidePopup').click(function () {
        $('.confirmPopup').fadeOut();
        $('.opacityPopup').fadeOut();
        $('.reason').fadeOut();
    });

    //点击不通过弹出弹窗
    $('.noPass').click(function () {
    	noPassId = $(this).attr('id') 
        $('.reason').fadeIn();
        $('.opacityPopup').fadeIn();
    });
</script>
