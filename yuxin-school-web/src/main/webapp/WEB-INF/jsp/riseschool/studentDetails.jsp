<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>学员详情</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css">
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/studentDetails.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
	<style type="text/css">
		.tips{
			color:red;
		}
		.earlyLitreDetail{
			padding-bottom : 100px;
		}
        /*table{*/
            /*word-wrap: break-word; word-break: break-all;*/
        /*}*/
		.detailSite{
			width: 130px;
			overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
		}
		.experienceList:nth-of-type(n+2){margin-left: 228px !important;}
		.household{overflow: hidden;
			text-overflow:ellipsis;
			white-space: nowrap;
			display: inline-block;
			width: 350px;
		}
    </style>
</head>

<body>
<input type="hidden" value="${isDelete }" id="isDelete"/>
<input type="hidden" value="${registConfig.mobileFlag }" id="mobileSet"/>
<input type="hidden" value="${registConfig.usernameFlag }" id="userNameSet"/>
<input type="hidden" value="${address }" id="addreSet"/>
<input type="hidden" value="${proxyOrgRole }" id="proxyOrgRole"/>
<input type="hidden" value="${id}" id="stuId"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitre.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding " style="height: 1000px;">
		<div class="infoBox1">
			<div class="heading" style="border-left: none;">
				<h2 class="h5">学生信息</h2>
				<span class="line"></span>
			</div>
			<div>
				<div class="studentDetails">
					<ul>
						<li>
							<label for="">姓名</label>
							<span style="margin-left: 200px;">${riseStudentVo.studentName }</span>
						</li>
						<li>
							<label for="">手机号</label>
							<span style="margin-left: 188px;">${riseStudentVo.mobile}</span>
						</li>
						<li>
							<label for="">身份证号 </label>
							<span style="margin-left: 178px;">${riseStudentVo.idNo}</span>
						</li>
						<li class="clickImg">
							<label for="">户籍照片</label>
							<img src="${riseStudentVo.censusUrl}" alt="" style="margin-left: 178px;">
							<img src="${riseStudentVo.headUrl}" alt="" style="margin-left: 20px;">
							<img src="${riseStudentVo.selfUrl}" alt="" style="margin-left: 20px;">
						</li>
					</ul>
					<ul>
						<li>
							<label for="">性别</label>
							<span style="margin-left: 200px;">${riseStudentVo.sex}</span>
						</li>
						<li>
							<label for="">出生日期</label>
							<span style="margin-left: 175px;">${riseStudentVo.birthday}</span>
						</li>
						<li>
							<label for="" style="float: left;">户籍所在地</label>
							<span style="margin-left: 165px;" class="household">${riseStudentVo.censusAddress}&nbsp;&nbsp;${riseStudentVo.censusDetAddress}</span>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="infoBox2">
			<div class="heading" style="border-left: none;">
				<h2 class="h5">教育经历</h2>
				<span class="line"></span>
			</div>
			<div>
				<div class="studentDetails">
					<ul>
						<li>
							<label for="">毕业学校</label>
							<c:forEach items="${experienceList}" var="list">
								<span style="margin-left: 175px;" class="experienceList">
								${list.provinceName}&nbsp;&nbsp;
								${list.cityName}&nbsp;&nbsp;
								${list.districtName}&nbsp;&nbsp;
								${list.schoolName}
								</span>
								<br/>
							</c:forEach>
						</li>
						<li>
							<label for="">成都数字学校测评成绩</label>
							<c:if test="${grade ne -1}">
							<span style="margin-left: 105px;">${grade}分</span>
							</c:if>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="infoBox3">
			<div class="heading" style="border-left: none;">
				<h2 class="h5">家长信息</h2>
				<span class="line"></span>
			</div>
			<div>
				<div class="studentDetails">
					<ul>
						<li>
							<label for="">姓名</label>
							<span style="margin-left: 200px;">${riseStudentVo.curator}</span>
						</li>
						<li>
							<label for="">手机号</label>
							<span style="margin-left: 188px;">${riseStudentVo.curatorMobile}</span>
						</li>
					</ul>
					<ul>
						<li>
							<label for="">关系</label>
							<c:if test="${riseStudentVo.curatorRelation eq 0}">
								<span style="margin-left: 200px;">父子</span>
							</c:if>
							<c:if test="${riseStudentVo.curatorRelation eq 1}">
								<span style="margin-left: 200px;">父女</span>
							</c:if>
							<c:if test="${riseStudentVo.curatorRelation eq 2}">
								<span style="margin-left: 200px;">母子</span>
							</c:if>
							<c:if test="${riseStudentVo.curatorRelation eq 3}">
								<span style="margin-left: 200px;">母女</span>
							</c:if>
							<c:if test="${riseStudentVo.curatorRelation eq 4}">
								<span style="margin-left: 200px;">其他</span>
							</c:if>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div class="infoBox4">
			<div class="heading" style="border-left: none;">
				<h2 class="h5">个人荣誉</h2>
				<span class="line"></span>
			</div>
			<div>
				<div class="studentDetails">
					<ul>
					<c:forEach items="${honorList}" var="list">
						<li>
							<span>${list.honorContent}</span>
						</li>
					</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<c:if test="${riseStudentVo.isCheck eq 1}">
		<div class="infoBox5">
			<a href="javascript:void(0)" class="btn btn-mb btn-primary pass">通过</a>
			<a href="javascript:void(0)" class="btn btn-mb btn-default noPass">不通过</a>
		</div>
		</c:if>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->

<%--通过确认--%>
<div class="opacityPopup"></div>
<div class="confirmPopup">
	<div>是否确认通过该学生的申请？</div>
	<a href="javascript:void(0)" class="cancel hidePopup">取消</a>
	<a href="javascript:void(0)" class="confirmPass hidePopup studentDetailPass">确认通过</a>
</div>

<%--不通过原因选择--%>
<div class="reason">
    <h5>不通过原因</h5>
    <ul>
    	<c:forEach items="${noPassList}" var="list">
			<li>
				<li><label for=""><input type="checkbox" name="noPassReason" value="${list.reason}"><span>${list.reason}</span></label></li>
			</li>
		</c:forEach>
        <li><label for=""><input type="checkbox" name="noPassReason" value="其他"><span>其他</span></label></li>
    </ul>
    <textarea name="" id="otherReason" cols="30" rows="10" class="descriptWord"placeholder="请输入原因，最多60个字。"
    maxlength="60"></textarea>
    <div class="btnGroup">
        <a href="javascript:void(0)" class="btn btn-sm btn-default hidePopup">取消</a>
        <a href="javascript:void(0)" class="btn btn-sm btn-primary hidePopup studentDetailNoPass">确定</a>
    </div>
</div>

<%--点击户籍图片弹出大图--%>
<img src="" alt="" class="bigImage" id='bigImage'>

<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript" src="<%=rootPath %>/javascripts/riseschool/studentlist.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<%--<script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils"></script>--%>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/selectStudentGroup.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/riseschool/jm-qi.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/riseschool/studentDetails.js"></script>

<script>
    //二级导航
    $(function () {
        $selectSubMenu('studentManagement');
    });
</script>

</body>
</html>