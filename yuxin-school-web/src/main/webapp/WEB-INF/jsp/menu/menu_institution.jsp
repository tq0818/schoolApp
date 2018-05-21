<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe6c5;</i>机构</a>
        <ul class="nav nav-left navspace">
            <%--<shiro:hasPermission name="statistics_all">--%>
               <%--&lt;%&ndash; <li code="statistics_all"><a href="<%=request.getContextPath() %>/query/statistics/index">总览</a></li>&ndash;%&gt;--%>
            <%--</shiro:hasPermission>--%>
            <%--<shiro:hasPermission name="statistics_all_detail">--%>
               <%-- <li code="statistics_all_detail"><a href="<%=request.getContextPath() %>/query/statistics/studentList">查询统计</a></li>--%>
                <shiro:hasPermission name="student_head">
                    <li code="classification"><a href="<%=request.getContextPath() %>/InsInfoBase/classification">机构分类管理</a></li>
                    <li code="organizationIndex"><a href="<%=request.getContextPath() %>/InsInfoBase/organizationIndex">机构管理</a></li>
                    <li code="recommendation"><a href="<%=request.getContextPath() %>/InsInfoBase/recommendationOrganization">推荐机构管理</a></li>
                    <li code="student"><a href="<%=request.getContextPath() %>/InsStudent/studentManagementIndex">学员管理</a></li>
                    <li code="review"><a href="<%=request.getContextPath() %>/comment/commentIndex">机构评论</a></li>
                </shiro:hasPermission>
                <shiro:hasPermission name="INSTITUTION_MANAGE">
                    <li code="organizationIndex"><a href="<%=request.getContextPath() %>/InsInfoBase/findInsById?id=${insId}">机构管理</a></li>
                    <li code="student"><a href="<%=request.getContextPath() %>/InsStudent/studentManagementIndex">学员管理</a></li>
                    <%--<li code="review"><a href="<%=request.getContextPath() %>/comment/commentIndex">机构评论</a></li>--%>
                </shiro:hasPermission>

            <%--</shiro:hasPermission>--%>
        </ul>
    </div>
</div>
<style>
    .set-system{
        margin-top: 0 ;
    }
</style>
<script>
    $(function() {
        $selectMenu('mechanism');
//        $selectSubMenu('watchInfoList');
    })
</script>