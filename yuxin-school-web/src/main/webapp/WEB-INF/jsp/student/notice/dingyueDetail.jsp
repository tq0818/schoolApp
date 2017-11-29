<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${msgPage.rowCount }" id="rowCount" name="rowCount"/>
<input type="hidden" value="${msgPage.pageNo }" id="pageNo" name="pageNo"/>
<table class="table table-center" id="tableList">
	<tr>
		<th width="15%" >序号</th>
		<th width="15%" >手机号</th>
		<th width="15%" >用户名称</th>
		<th width="15%" >报名</th>
		<th width="15%" >同意</th>
		<th width="15%" >反对</th>
	</tr>
	<c:if test="${not empty msgPage.data }">
		<c:forEach var="m" items="${msgPage.data }" varStatus="count">
			<tr>
				<td>${(msgPage.pageNo-1)*msgPage.pageSize+count.index+1 }</td>
				<td>${m.telNum }</td>
				<td>${m.userName }</td>
				<td>${m.isSignUp }</td>
				<td>
					<c:if test="${m.isAgree eq '1'}">1</c:if>
				</td>
				<td>
					<c:if test="${m.isAgree eq '0'}">1</c:if>
				</td>
			</tr>
		</c:forEach>
	</c:if>
</table>    
<script type="text/javascript">
	$(function(){
		$(".pagination").html("");
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
				selDingyueDetail(pageNo);
			}
		});
		
		$(".btn-result").each(function(){
			$(this).click(function(){
				var objform = document.createElement("form");
				document.body.appendChild(objform);
				objform.action =rootPath +"/classModuleLesson/selMessage";
				objform.method = "post";
				
				var payId = document.createElement("input");
				payId.type = "hidden";
				objform.appendChild(payId);
				payId.value = $(this).attr("data-id");
				payId.name = "msgId";
				
				objform.submit();
			});
		});
	});
</script>