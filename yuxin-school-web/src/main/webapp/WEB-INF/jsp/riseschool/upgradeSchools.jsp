<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>升学</title>
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
                <h2 class="h5">升学</h2>
                <span class="line"></span>
            </div>
            <div class="schoolDetailsContent">
                <div class="headContent">
                    <ul>
                        <li class="active">
                            <a href="##">招生方式</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '招生方式'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="##">分班和在校生情况</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '分班和在校生情况'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="##">初升高走向</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '初升高走向'}">
	                        <i class="icon iconfont iconDetails">&#xe611;</i>
	                        </c:if>
	                        </c:forEach>
                        </li>
                        <li>
                            <a href="##">往年升学情况和分数</a>
                            <c:forEach items="${riseDetails}" var="riseDetail" varStatus="status">
                            <c:if test="${riseDetail.itemName eq '往年升学情况和分数'}">
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
                        <a href="##" class="btnSave">保存</a>
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

<script src="<%=rootPath %>/javascripts/riseschool/upgradeSchools.js"></script>
<script>
//    左侧active切换
    $selectSubMenus('upgradeSchools');
</script>
</body>
</html>
