<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>基本信息</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/essential.css">

</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitre.jsp"/>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitreLeft.jsp"></jsp:include>
    <input type="hidden" id="schoolId" value="${schoolId}">
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">基本信息</h2>
                <span class="line"></span>
            </div>
            <div class="schoolDetailsContent essentialInfo">
                <div class="essentialInfoContent">
                    <div>
                        <label for="" class="noMargin">学校名称：</label>
                        <span id="schoolName">${result.schoolName}</span>
                    </div>
                    <div>
                        <label for="" class="noMargin">招生方式：</label>
                        <span id="enRollMent">${result.enrollmentName}</span>
                    </div>
                    <div class="schoolSite">
                        <label for="" class="noMargin">学校地址：</label>
                        <select name="" id="province" onclick="queryRiseSchoolDict(1)">
                            <option value="">${result.provinceName}</option>
                        </select>
                        <select name="" id="city" onclick="queryRiseSchoolDict(2)">
                            <option value="">${result.cityName}</option>
                        </select>
                        <select name="" id="area">
                            <option value="">${result.districtName}</option>
                        </select>
                        <br/><input type="text" id="schoolAddress" placeholder="请输入详细地址" maxlength="60" value="${result.detailAddress}">
                    </div>
                    <div>
                        <label for="">学校网址：</label>
                        <input type="text" id="schoolWeb" value="${result.schoolWeb}">
                    </div>
                    <div>
                        <label for="">学校传真：</label>
                        <input type="text" id="schoolFax" value="${result.schoolFax}">
                    </div>
                    <div>
                        <label for="">公交路线：</label>
                        <input type="text" id="busRoad" maxlength="200" value="${result.busRoad}">
                    </div>
                    <div>
                        <label for="">收藏基数：</label>
                        <input type="text" id="collectBaseCount" maxlength="5" placeholder="请输入0-10000" value="${result.baseNum}">
                    </div>
                    <div class="countPopupBtn">
                        <a href="##" class="btn btn-sm btn-primary countPopupCancel">取消</a>
                        <a href="javaScript:updateRiseSchoolInfo();" class="btn btn-sm btn-primary countPopupSave">保存</a>
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
    $selectSubMenus('essential');
</script>
<script>
    $('.countPopupCancel').click(function () {
        history.go(-1);
    });
</script>
</body>
</html>
