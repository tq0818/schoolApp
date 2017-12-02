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
                    <th width="10%">序号</th>
                    <th width="20%">名称</th>
                    <th width="20%">描述</th>
                    <th width="20%">状态</th>
                    <th width="30%">操作</th>
                </tr>
               <c:forEach var="m" items="${msgPage.data}" varStatus="status">
	                <tr>
	                	<td>${status.index+1}</td>
	                	<td>${m.bannerName }</td>
	                	<td>${m.bannerDescribe }</td>
	                	<td>禁用</td>
	                	<td>
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