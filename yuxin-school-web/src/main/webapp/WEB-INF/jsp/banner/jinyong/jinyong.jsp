<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
    <ul>
<input type="hidden" value="${msgPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${msgPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${msgPage.pageSize }" id="pageSize"/>	
	       <li>
	        <table class="table table-center "id="table" >
                <tbody>
                <tr id="tr1">
					<th width="5%">序号</th>
					<th width="25%">名称</th>
					<th width="35%">描述</th>
					<th width="5%">状态</th>
					<th width="20%">操作</th>
                </tr>
               <c:forEach var="m" items="${msgPage.data}" varStatus="status">
	                <tr>
	                	<td>${status.index+1}</td>
						<c:choose>
							<c:when test="${fn:length(m.bannerName)>20}">
								<td width="15%" title="${m.bannerName}">${fn:substring(m.bannerName,0,20)}...</td>
							</c:when>
							<c:otherwise>
								<td width="15%">${m.bannerName}</td>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${fn:length(m.bannerDescribe)>40}">
								<td width="45%" title="${m.bannerDescribe}">${fn:substring(m.bannerDescribe,0,40)}...</td>
							</c:when>
							<c:otherwise>
								<td width="45%">${m.bannerDescribe}</td>
							</c:otherwise>
						</c:choose>
	                	<%--<td>${m.bannerName }</td>
	                	<td>${m.bannerDescribe }</td>--%>
	                	<td width="5%">禁用</td>
	                	<td width="20%">
	                		<a href='javascript:;' onclick="changeStatu(${m.id})" class='btn btn-danger forbidBanner'>启用</a>
	                   		<a href='<%=rootPath %>/Banner/editBanner/${m.id}'  class='btn btn-warning'>修改</a>
	                   		<a href='##' class='btn btn-success'>查看</a>
                   		</td>
	                </tr>
               </c:forEach>
            </table>
	       </li>
   </ul>
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
				selDetail(pageNo);
			}
		});
		
		
	});
	function changeStatu(id){
		$.ajax({
			url: rootPath + "/Banner/changeStatu",
			type:"post",
			data:{"id":id,"biaoshi": 0},
			dataType:"html",
			success:function(data){
				$('#tableList1').html(data);
			}
		});
	}
	
</script>