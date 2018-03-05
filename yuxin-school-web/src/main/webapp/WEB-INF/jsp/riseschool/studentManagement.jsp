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
<input type="hidden" value="1" id="timeOrder"/>
<jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitre.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding ">
        <div class="heading"> 
            <h2 class="h5">学员管理</h2>
            <span class="line"></span>
        </div>
        <form method="post" id="searchForm">
			<div class="searchFormArea">
				<div class="isCheck">
					<label for="">审核状态</label>
					<a href="javascript:void(0)" class="btn btn-primary btn-sm" data-value="">全部</a>
					<a href="javascript:void(0)" class="btn btn-default btn-sm" data-value="1">待审核</a>
					<a href="javascript:void(0)" class="btn btn-default btn-sm" data-value="2">已通过</a>
					<a href="javascript:void(0)" class="btn btn-default btn-sm" data-value="0">未通过</a>
				</div>
			</div>

			<div style="margin: 10px 0">
					<label for="" >申请学校</label>
					<select name="" id="schoolName" style="margin-left: 10px;width: 300px;">
						<option value="-1">请选择学校</option>
						<option value="0">全部</option>
						<option value="c1">七中</option>
					</select>
				</div>

			<div style="margin-top: 10px;">
				<label style="margin-right: 10px;">提交时间</label>
				<span>
					<input type="text" name="startTime" class="date-picker from" readonly style="cursor: default;"/>
					<em>到</em>
					<input type="text" name="endTime" class="date-picker to" readonly style="cursor: default;"/>
				</span>
			</div>
			<div style="margin: 10px 0;">
				<input type="text" placeholder="姓名" id="studentName">
				<input type="text" placeholder="手机号" id="mobile">
				<input type="text" placeholder="学生编号" id="studentNo">
				<input type="text" placeholder="毕业学校" id="SchoolTag">
				<span style="margin-left: 10px;"><a href="javascript:;" class="btn btn-primary searchStudentManagement">搜索</a></span>
				<span style="float: right"><a href="javascript:void(0);" class="btn btn-primary">导出用户</a></span>
			</div>
        </form>
        <div class="user-list earlyLitreDetail">

        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
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
    $(".pagination").pagination('${rowCount}',
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
    );

    //二级导航
    $(function () {
        $selectSubMenu('studentManagement');
    });

</script>
</body>
</html>