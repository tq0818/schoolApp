<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript">
$(function(){
	$selectMenu('earlyLitre');
})
</script>
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe61b;</i>小升初</a>
        <ul class="nav nav-left navspace">
        	<%--<shiro:hasPermission name="student_manage">--%>
            <%--<c:choose>
                <c:when test="${userType eq 'RISE_SCHOOL_MANAGER'}">
                    <li code="earlyLitre"><a href="<%=rootPath %>/riseschoolback/essential?schoolId=${schoolId}&schoolName=${schoolName}">学校管理</a></li>
                </c:when>
                <c:otherwise>
                    <li code="earlyLitre"><a href="<%=rootPath %>/riseschoolback/earlyLitre">学校管理</a></li>
                </c:otherwise>
            </c:choose>--%>
                <li code="earlyLitre"><a href="<%=rootPath %>/riseschoolback/earlyLitre">学校管理</a></li>
                <li code="studentManagement"><a href="<%=rootPath %>/riseschoolback/studentManagement">学员管理</a></li>
        	<%--</shiro:hasPermission>--%>
        </ul>
    </div>
</div>