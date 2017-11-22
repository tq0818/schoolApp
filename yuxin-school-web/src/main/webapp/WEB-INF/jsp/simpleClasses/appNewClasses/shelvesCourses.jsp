
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>已上架课程</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body style="position:relative;">
    <!-- 二级导航 -->
    <jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
    <%--已上架课程分类筛选--%>
    <div class="u-wrap classes">
        <div class="mainbackground nopadding">
        <div class="classes-type">
            <p class="c">
                <span class="t-title">课程分类</span>
                <span class="t-content" id="courseCaId">
                    <a href="javascript:Form.showAllShelvesClssType('all','courseCaId');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <c:forEach items="${firstMenus}" var="menu">
                        <a href="javascript:Form.showAllShelvesClssType('${menu.id}','courseCaId');" data-code="${menu.id}" class="btn btn-mini btn-default">${menu.name}</a>
                    </c:forEach>
                </span>
            </p>
            <p class="c">
                <span class="t-title">学段</span>
                <span class="t-content" id="gradeId">
                     <a href="javascript:Form.showAllShelvesClssType('all','gradeId');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">学科</span>
                <span class="t-content" id="subjectId">
                    <a href="javascript:Form.showAllShelvesClssType('all','subjectId');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">知识点专题</span>
                <span class="t-content" id="kwonProId">
                    <a href="javascript:Form.showAllShelvesClssType('all','kwonProId');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">知识点</span>
                <span class="t-content" id="knowId">
                	<a href="javascript:Form.showAllShelvesClssType('all','knowId');" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">阶段</span>
                <span class="t-content" id="stageId">
                	<a href="Form.showAllShelvesClssType('all','stageId');" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">类型</span>
                <span class="t-content" id="typeId">
                	<a href="javascript:Form.showAllShelvesClssType('all','typeId');" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
        </div>
    </div>
    </div>
    <div class="u-wrap classes">
        <div class="mainbackground nopadding alreadyCourse">
			<div id="ShelvesCourseDetailList">
			
			</div>
        </div>
    </div>

    <script type="text/javascript" src="<%=rootPath %>/javascripts/app/shelvesCourses.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>
