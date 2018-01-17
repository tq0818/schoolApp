<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<table class="table table-center allOrderList" >
	<tr>
		<th width="3%">序号</th>
		<th width="10%">查询结果</th>
	</tr>
	<c:forEach var="st" items="${queryResult}" varStatus="vs">
	<tr>
		<td>${vs.count}</td>
		<td>${st.excuteResult}</td>
	</tr>
	</c:forEach>
