<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/businessEntry.css">
</head>
<body>
<input type="hidden" id="pageNo" value='${pageNo}'/>
<input type="hidden" id="rowCount" value='${rowCount}'/>
    <table class="table table-center" id="tableList">
        <tr data-buy="true">
            <th width="3%">序号</th>
            <th width="5%">手机号</th>
            <th width="5%">机构名称</th>
            <th width="5%">提交时间</th>
            <th width="5%">处理状态</th>
            <th width="5%">备注</th>
            <th width="5%">操作</th>
        </tr>
        <c:forEach var="merchant" items="${result}" varStatus="status">
        	<tr>
	            <td>${status.index+ 1}</td>
	            <td>${merchant.mobile}</td>
	            <td><pre>${merchant.insName}</pre></td>
	            <td><fmt:formatDate value="${merchant.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	            <c:choose>
	            	<c:when test="${merchant.dealStatus == 0}">
	            		 <td>未处理</td>
	            	</c:when>
	            	<c:otherwise>
	            		 <td>已处理</td>
	            	</c:otherwise>
	            </c:choose>
	            <td class="note" title="${merchant.note}">
	            	 <c:if test="${fn:length(merchant.note)>30 }">  
                         ${fn:substring(merchant.note, 0, 30)}...
                    </c:if>
                     <c:if test="${fn:length(merchant.note)<=30 }">  
                        ${merchant.note}
                   	</c:if>
	            </td>
	            <td>
	                <a href="javascript:updateMerchantEntry(${merchant.id},${merchant.dealStatus})" class="updateStatus">切换状态</a>|
	                <a href="javascript:void(0)" id="${merchant.id}" value="${merchant.note}" class="addRemarks">添加备注</a>
	            </td>
        	</tr>
        </c:forEach>
    </table>
     <c:if test="${rowCount == 0 }">
     	<table style="width: 100%;">
     		<tr><th>暂无数据</th></tr>
     	</table>
     </c:if>
     <div class="pages pageNa">
     </div>
   <%--  <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script> --%>
</body>
</html>
<script>
	$(".pageNa").pagination('${rowCount}', {
		maxentries:'${rowCount}',
	    next_text : "下一页",
	    prev_text : "上一页",
	    current_page :'${pageNo - 1}',
	    link_to : "javascript:void(0)",
	    num_display_entries :10,
	    items_per_page : 10,
	    num_edge_entries : 0,
	    callback:function(page){
	        var pageNo = page + 1;
	        if($("#dimFlag").val() != null || $("#dimFlag").val() != ""){
	            dimMerchantEntry(pageNo);
	        }else{
	            queryMerchantEntry(pageNo);
	        }
	    }
	});

//添加备注弹窗     //点击备注，弹出弹窗
$('.addRemarks').click(function () {
    $('.remarks').show();
    //将id设置到页面上，以便更新时直接获取
    console.log("id:"+$(this).attr("id")+","+"内容:"+$(this).attr("value"));
    $("#updateId").val($(this).attr("id"));
    $("#content").val($(this).attr("value"));
});

</script>