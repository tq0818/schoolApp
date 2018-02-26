<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学校详情</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">

</head>
<body>
<input type="hidden" value="${riseSchoolId }" id="riseSchoolId" />
<jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitre.jsp"/>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitreLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">学校详情</h2>
                <span class="line"></span>
            </div>
            <div class="schoolDetailsContent">
                <div class="headContent">
                    <ul>
                        <li class="active">
                            <a href="javascript:void(0)">学校简称或俗称</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '学校简称或俗称'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">办学类别和层次</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '办学类别和层次'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">学校历史及概况</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '学校历史及概况'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">办学理念</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '办学理念'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">校园规模和硬件</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '校园规模和硬件'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li class="noMargin">
                            <a href="javascript:void(0)">教学师资及成绩</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '教学师资及成绩'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">教学特色</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '教学特色'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">住校情况</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '住校情况'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">就餐情况</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '就餐情况'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">托管及接送情况</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '托管及接送情况'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="javascript:void(0)">费用情况</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '费用情况'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li class="noMargin">
                            <a href="javascript:void(0)">奖学金</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '奖学金'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                    </ul>
                </div>
                <div class="footerContent">
                    <textarea name="" id='footerContentDetail' class="footerContentDetail" placeholder="最多2000个字" >${riseSchoolDetailsUp.itemDiscrible }</textarea>
                    <div class="btnStyle">
                        <a href="##" class="btnCancel">取消</a>
                        <a href="javascript:void(0)" class="btnSave">保存</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>

<script src="<%=rootPath %>/javascripts/riseschool/schoolDetails.js"></script>
<script>
//    左侧active切换
    $selectSubMenus('schoolDetails');
</script>
<script>
    $('.btnCancel').click(function () {
    	window.history.go(-1);
    });
</script>
</body>
</html>
