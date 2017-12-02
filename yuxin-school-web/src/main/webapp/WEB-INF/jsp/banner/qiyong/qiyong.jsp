<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
    <ul>
    	
	       <li>
	        <table class="table table-center "id="table1" >
                <tbody>
                <tr >
                    <th width="10%">序号</th>
                    <th width="20%">名称</th>
                    <th width="20%">描述</th>
                    <th width="20%">状态</th>
                    <th width="30%">操作</th>
                </tr>
                <c:forEach var="m" items="${msgPage}" varStatus="status">
	                <tr>
	                	<td>${status.index+1}
	                	<c:if test="${!status.last}"><i onclick="desc(${m.id},${m.orderByNum},0)" class="icon iconfont desc">&#xe617;</i></c:if><!-- 下降图标 -->
	                	<c:if test="${status.index !=0}"><i onclick="desc(${m.id},${m.orderByNum},1)" class="icon iconfont asc">&#xe61a;</i></c:if><!-- 上升图标 -->
	                	</td>
	                	<td>${m.bannerName }</td>
	                	<td>${m.bannerDescribe }</td>
	                	<td>启用</td>
	                	<td>
	                		<a href='javascript:;' onclick="changeStatu(${m.id})" class='btn btn-danger forbidBanner'>禁用</a>
	                   		<a href='<%=rootPath %>/Banner/editBanner/${m.id}'  class='btn btn-warning'>修改</a>
	                   		<a href='<%=rootPath %>/Banner/seachDetail/${m.id}' target="_blank" class='btn btn-success'>查看</a>
                   		</td>
	                </tr>
               </c:forEach>
            </table>
	       </li>
   </ul>
<script type="text/javascript">
	function changeStatu(id){
		$.ajax({
			url: rootPath + "/Banner/changeStatu",
			type:"post",
			data:{"id":id,"biaoshi": 1},
			dataType:"html",
			success:function(data){
				$('#tableList').html(data);
			}
		});
	}
	function desc(id,orderNum,paixu){
		$.ajax({
			url: rootPath + "/Banner/sort",
			type:"post",
			data:{"id":id,"biaoshi": paixu,"orderByNum":orderNum},//1上升 
			dataType:"html",
			success:function(data){
				alert("排序保存成功");
				$('#tableList').html(data);
			}
		});
	}
</script>