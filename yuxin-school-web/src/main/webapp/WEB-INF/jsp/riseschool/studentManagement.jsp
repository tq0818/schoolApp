<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
 <%@include file="/decorators/import.jsp" %>
    <title>学员管理</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css">
	<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
	<link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/studentManagement.css">
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
    </style>
</head>

<body>
<input type="hidden" value="${isDelete }" id="isDelete"/>
<input type="hidden" value="${registConfig.mobileFlag }" id="mobileSet"/>
<input type="hidden" value="${registConfig.usernameFlag }" id="userNameSet"/>
<input type="hidden" value="${address }" id="addreSet"/>
<input type="hidden" value="${proxyOrgRole }" id="proxyOrgRole"/>
<input type="hidden" value="${userorg_roleopenflag }" id="userorg_roleopenflag"/>
<input type="hidden" value="${rowCount }" id="rowCount"/>
<input type="hidden" value="${pageNo}" id="pageNo"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitre.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding ">
        <div class="heading"> 
            <h2 class="h5">学员管理</h2>
            <span class="line"></span>
        </div>
        <form method="post" id="searchFormStudent">
        	<input type="hidden"  id="isCheckBtn" name="isCheck" value=""/>
			<div class="searchFormArea">
				<div class="isCheck">
					<label for="">审核状态</label>
					<a href="javascript:void(0)"  class="btn btn-primary btn-sm" data-value="" >全部</a>
					<a href="javascript:void(0)"  class="btn btn-default btn-sm" data-value="1" >待审核</a>
					<a href="javascript:void(0)"  class="btn btn-default btn-sm" data-value="2" >已通过</a>
					<a href="javascript:void(0)"  class="btn btn-default btn-sm" data-value="0" >未通过</a>
				</div>
			</div>
			<c:choose>
			<c:when test="${userType eq 'RISE_SCHOOL_MANAGER'}">
				<input type="hidden"  id="schoolName" name="schoolName" value="${riseSchoolIdStudent }"/>
			</c:when>
			<c:otherwise>
			<div style="margin: 10px 0">
					<label for="" >申请学校</label>
					<select name="schoolName" id="schoolName" style="margin-left: 15px;width: 300px;position: absolute;"
							onmousedown="if(this.options.length>6){this.size=7}" onblur="this.size=0" onchange="this.size=0" >
						<option value="-1">请选择学校</option>
						<option value="0">全部</option>
						<c:forEach items="${list}" var="list" >
							<option value="${list.id}" data-id="${list.id}">${list.schoolName}</option>
						</c:forEach>
					</select>
			</div>
			</c:otherwise>
			</c:choose>
			<div style="margin-top: 10px;padding: 10px 0;">
				<label style="margin-right: 12px;">提交时间</label>
				<span>
					<input type="text" name="startTime" class="date-picker from"  style="cursor: default;"/>
					<em>到</em>
					<input type="text" name="endTime" class="date-picker to"  style="cursor: default;"/>
				</span>
			</div>
			<div style="margin: 10px 0;">
				<input type="text" placeholder="姓名" id="studentName" name="studentName">
				<input type="text" placeholder="手机号" id="mobile" name="mobile">
				<input type="text" placeholder="学生编号" id="studentNo" name="studentNo">
				<input type="text" placeholder="毕业学校" id="SchoolTag" name="SchoolTag">
				<input type="hidden" value="1" id="timeOrder" name="timeOrder"/>
				<span style="margin-left: 10px;"><a href="javascript:;" class="btn btn-primary searchStudentManagement">搜索</a></span>
				<span style="float: right"><a href="javascript:void(0);" class="btn btn-primary exportexcleStudent">导出用户</a></span>
			</div>
        </form>
        <div class="user-list earlyLitreDetail">
			<table class="table table-center" id="tableList">
			<tr data-buy="true">
				<th width="4%">序号</th>
				<th width="7%">姓名</th>
				<th width="5%">性别</th>
				<th width="11%">毕业学校</th>
				<c:if test="${userType ne 'RISE_SCHOOL_MANAGER'}">
				<th width="11%">申请学校</th>
				</c:if>
				<th width="8%">手机号</th>
				<th width="8%">出生日期</th>
				<th width="10%">户籍详细地址</th>
				<th width="10%" class="btn-sort">
					提交时间
				</th>
				<th width="6%">审核状态</th>
				<th width="9%">学生编号</th>
				<th width="20%">操作</th>
			</tr>
		</table>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<form action="rootPath+/riseStudentSchoolTag/studentDetails" method=post name=formx1 style='display:none'>

</form>
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->




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
<script type="text/javascript" src="<%=rootPath%>/javascripts/riseschool/studentManagement.js"></script>

<script>
	//分页
    /* $(".pagination").pagination('${rowCount}',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page: '${pageNo - 1}',
            link_to: "javascript:void(0)",
            num_display_entries: 8,
            items_per_page: 10,
            num_edge_entries: 1,
            callback: function (page, jq) {
                var pageNo = page + 1;
                queryRiseSchoolInfo(pageNo);
            }
        }
    ); */

    //二级导航
    $(function () {
        $selectSubMenu('studentManagement');
    });

</script>
</body>
</html>