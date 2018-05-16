<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${not empty insManageData.data}">
        <table class="table table-center" id="tableList">
            <tr data-buy="true">
                <th width="3%">序号</th>
                <th width="5%">一级分类</th>
                <th width="25%">二级分类</th>
                <th width="5%">启用状态</th>
                <th width="15%">操作</th>
            </tr>
            <c:forEach items="${insManageData.data}" var="insData" varStatus="vs">
                <tr data-buy="false" >
                    <td>${vs.count}</td>
                    <td>${insData.codeName}</td>
                    <td id="firtId_${insData.id}">
                        <c:set var="secDatas" value="${fn:split(insData.secondCate,',')}"></c:set>
                        <c:forEach var="secData" items="${secDatas}" varStatus="vsIndex">
                            <c:choose>
                                <c:when test="${not empty secData}">
                                    <a href="javascript:void(0);" onclick="openDetails('2','${fn:split(secData,"@")[0]}');" id="${fn:split(secData,"@")[0]}_sec" class="btn btn-default btn-xs detailSeconPopupBtn">${fn:split(secData,"@")[1]}</a>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </td>
                    <c:choose>
                        <c:when test="${insData.isEnable eq 1}">
                            <td>已启用</td>
                        </c:when>
                        <c:otherwise>
                            <td>已禁用</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <a href="javascript:void(0);" class="detailFirstPopupBtn" onclick="openDetails('1','${insData.id}');" id="${insData.id}_details">详情</a>|
                        <a href="javascript:void(0);" class="addSeconPopupBtn" onclick="openSecAdd('${insData.id}');" id="${insData.id}_addSec">添加二级分类</a>|
                        <c:choose>
                            <c:when test="${insData.isEnable eq 1}">
                                <a href="javascript:void(0);" onclick="updatedata('1','${insData.id}','${insData.isEnable}');" id="${insData.id}_qinyong">禁用</a>
                            </c:when>
                            <c:otherwise>
                                <a href="javascript:void(0);" onclick="updatedata('1','${insData.id}','${insData.isEnable}');" id="${insData.id}_jinyong">启用</a>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>
        </table>
        <div class="pages pagination">

        </div>
    </c:when>
    <c:otherwise>
        <table class="table table-center">
            <tr data-buy="true">
                <th width="3%">序号</th>
                <th width="5%">一级分类</th>
                <th width="25%">二级分类</th>
                <th width="5%">启用状态</th>
                <th width="15%">操作</th>
            </tr>
            <tr>
                <td colspan="5">没有数据</td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>
<input id="pageNo" value="${pageNo}" type="hidden"/>
<script>
    //分页插件
    $(".pagination").pagination('${count}', {
        next_text : "下一页",
        prev_text : "上一页",
        current_page : '${pageNo-1}',
        link_to : "javascript:void(0)",
        num_display_entries : 8,
        items_per_page : 5,
        num_edge_entries : 1,
        callback:function(page){
            var pageNo = page + 1;
            queryAllData(pageNo);
        }
    });
</script>
