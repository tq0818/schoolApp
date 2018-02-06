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
                            <a href="##">学校简称或俗称</a>
                        <i class="icon iconfont iconDetails">&#xe611;</i>
                        </li>
                        <li>
                            <a href="##">办学类别和层次</a>
                        </li>
                        <li>
                            <a href="##">学校历史及概况</a>
                        </li>
                        <li>
                            <a href="##">办学理念</a>
                        </li>
                        <li>
                            <a href="##">校园规模和硬件</a>
                        </li>
                        <li class="noMargin">
                            <a href="##">教学师资及成绩</a>
                        </li>
                        <li>
                            <a href="##">教学特色</a>
                        </li>
                        <li>
                            <a href="##">住校情况</a>
                        </li>
                        <li>
                            <a href="##">就餐情况</a>
                        </li>
                        <li>
                            <a href="##">托管及接送情况</a>
                        </li>
                        <li>
                            <a href="##">费用情况</a>
                        </li>
                        <li class="noMargin">
                            <a href="##">奖学金</a>
                        </li>
                    </ul>
                </div>
                <div class="footerContent">
                    <textarea name="" class="footerContentDetail" placeholder="最多2000个字"></textarea>
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

<script src="<%=rootPath %>/javascripts/riseschool/schoolDetails.js"></script>
<script>
//    左侧active切换
    $selectSubMenus('schoolDetails');
</script>
</body>
</html>
