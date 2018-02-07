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
</head>
<body>
<input type="hidden" id="dimFlag" value="${dimFlag}">
<table class="table table-center" id="tableList">
    <tr data-buy="true">
        <th width="5%">序号</th>
        <th width="16%">学校名称</th>
        <th width="8%">招生类型</th>
        <th width="8%">省份</th>
        <th width="8%">市</th>
        <th width="8%">区</th>
        <th width="8%">创建时间</th>
        <th width="10%">上下架状态</th>
        <th width="8%">置顶状态</th>
        <th width="20">操作</th>
       <%-- <th style="display:none" width="0%"></th>--%>
    </tr>
    <c:forEach items="${result}" var="detail" varStatus="status">
        <tr data-buy="true" onclick="setUserNameAndId('${detail.userId}','${detail.userName}')">
            <td>${status.index+1}</td>
            <td>${detail.schoolName}</td>
            <td>${detail.enrollmentName}</td>
            <td>${detail.provinceName}</td>
            <td>${detail.cityName}</td>
            <td>${detail.districtName}</td>
            <td><fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd"/></td>
            <td><c:if test="${detail.isShalve == 0}">未上架</c:if><c:if test="${detail.isShalve == 1}">已上架</c:if></td>
            <td><c:if test="${detail.isTop == 0}">未置顶</c:if><c:if test="${detail.isTop == 1}">已置顶</c:if></td>
            <td class="slink">
                <c:if test="${detail.isShalve == 1}">
                    <a class="offShelf"  href="javaScript:updateRiseSchool('${detail.id}',0,'');" data-value="0">下架</a>|
                </c:if>
                <c:if test="${detail.isShalve == 0}">
                    <a class="upShelf"  href="javaScript:updateRiseSchool('${detail.id}',1,'');" data-value="1">上架</a>|
                </c:if>
                <c:if test="${detail.isTop == 0}">
                    <a class="top"  href="javaScript:updateRiseSchool('${detail.id}','',1);" data-value="1">置顶</a>|
                </c:if>
                <c:if test="${detail.isTop == 1}">
                    <a class="down"  href="javaScript:updateRiseSchool('${detail.id}','',0);" data-value="0">取消置顶</a>|
                </c:if>
                <a class="countManagement"   href="##">账号管理</a>|
                <a class="more" href="#3">管理</a>
                <ul class="none box" style="display: none;">
                    <li><a class=""  href="javaScript:loalUrl(0,'${detail.id}');">基本信息</a></li>
                    <li><a class=""   href="javaScript:loalUrl(1,'${detail.id}');">学校详情</a></li>
                    <li><a class=""  href="javaScript:loalUrl(2,'${detail.id}');">学校风采</a></li>
                    <li><a href="javaScript:loalUrl(3,'${detail.id}');">升学</a></li>
                </ul>
            </td>
            <td style="display:none" id="${detail.id}">${detail.id}</td>
        </tr>
    </c:forEach>


   <%-- <c:choose>
        <c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
            <tr><td colspan="15">暂无数据</td></tr>
        </c:when>
        <c:otherwise>
            <tr><td colspan="14">暂无数据</td></tr>
        </c:otherwise>
    </c:choose>--%>


</table>
<div class="pages pagination">

</div>
</body>
</html>
<script>
    $(function () {
        //点击账号管理弹窗弹窗
        $('.countManagement').click(function () {
            $('.opacityPopup').fadeIn();
            $('.countPopup').fadeIn();
        });
    });
    //分页
    $(".pagination").pagination('${rowCount}',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page: '${pageNo - 1}',
            link_to: "javascript:void(0)",
            num_display_entries: 8,
            items_per_page: 12,
            num_edge_entries: 1,
            callback: function (page, jq) {
                var pageNo = page + 1;
                if ($("#dimFlag").val() == 1){
                    queryDimRiseSchoolInfo(pageNo);
                }else {
                    queryRiseSchoolInfo(pageNo);
                }

            }
        }
    );

</script>
