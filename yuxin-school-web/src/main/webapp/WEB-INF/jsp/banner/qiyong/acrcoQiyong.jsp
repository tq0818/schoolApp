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
					<th width="7%">序号</th>
					<th width="20%">名称</th>
					<th width="35%">描述</th>
					<th width="5%">状态</th>
					<th width="27%">操作</th>
                </tr>
               <c:forEach var="m" items="${msgPage.data}" varStatus="status">
	                <tr>
	                	<td width="7%">${status.index+1}</td>
						<c:choose>
							<c:when test="${fn:length(m.bannerName)>20}">
								<td width="20%" title="${m.bannerName}">${fn:substring(m.bannerName,0,20)}...</td>
							</c:when>
							<c:otherwise>
								<td width="20%">${m.bannerName}</td>
							</c:otherwise>
						</c:choose>

						<c:choose>
							<c:when test="${fn:length(m.bannerDescribe)>40}">
								<td width="40%" title="${m.bannerDescribe}">${fn:substring(m.bannerDescribe,0,40)}...</td>
							</c:when>
							<c:otherwise>
								<td width="40%">${m.bannerDescribe}</td>
							</c:otherwise>
						</c:choose>
	                	<%--<td>${m.bannerName }</td>
	                	<td>${m.bannerDescribe }</td>--%>
	                	<c:if test="${m.isState eq 0}">
	                		<td width="5%">禁用</td>
	                	</c:if>
	                	<c:if test="${m.isState eq 1}">
	                		<td width="5%">启用</td>
	                	</c:if>
	                	
	                	
	                	<td width="27%">
	                	<c:if test="${m.isState eq 1}">
	                		<a href='javascript:;' onclick="confirmPopup('确定禁用该banner？',${m.id},1)" class='btn btn-danger forbidBanner'>禁用</a>
	                   	</c:if>
	                	<c:if test="${m.isState eq 0}">
	                		<a href='javascript:;' onclick="confirmPopup('确定启用该banner，并替换当前banner？',${m.id},0)" class='btn btn-danger forbidBanner'>启用</a>
	                   	</c:if>
	                   		<a href='<%=rootPath %>/Banner/editBanner/${m.id}'  class='btn btn-warning'>修改</a>
	                   		<%-- <a href='<%=rootPath %>/Banner/seachDetail/${m.id}' target="_blank" class='btn btn-success'>查看</a> --%>
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
	function confirmPopup(str,id,biaoshi){
		$.confirm(str,function(b){
			if(b==true){
				$.ajax({
					url: rootPath + "/Banner/changeStatuAcrco",
					type:"post",
					dataType:"html",
					data:{"id":id,"biaoshi": biaoshi},
					success:function(data){
						 window.location.reload(); 
					}
				});
			}else{
				return; 
			}
		});
 	}
	
</script>