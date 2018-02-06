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
        	<li code="earlyLitre"><a href="<%=rootPath %>/riseschoolback/earlyLitre">学校管理</a></li>
        	<%--</shiro:hasPermission>--%>
        </ul>
    </div>
</div>