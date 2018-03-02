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
        <form method="post" id="searchForm">
			<div class="searchFormArea">
				<div class="enrolment">
					<label for="">审核状态</label>
					<a href="##" class="btn btn-primary btn-sm" >全部</a>
					<a href="##" class="btn btn-default btn-sm" >待审核</a>
					<a href="##" class="btn btn-default btn-sm" >已通过</a>
					<a href="##" class="btn btn-default btn-sm" >未通过</a>
				</div>
			</div>

			<div style="margin: 10px 0">
					<label for="" >申请学校</label>
					<select name="" id="" style="margin-left: 10px;width: 300px;">
						<option value=""></option>
						<option value="">全部</option>
						<option value="">七中</option>
					</select>
				</div>

			<div style="margin-top: 10px;">
				<label style="margin-right: 10px;">提交时间</label>
				<span>
					<input type="text" name="startTime" class="date-picker from" readonly style="cursor: default;"/>
					<em>到</em>
					<input type="text" name="endTime" class="date-picker to" readonly style="cursor: default;"/>
				</span>
				<%--<c:if test="${address==1}">--%>
				<%--<span style="padding:0 15px;" id="caddress">--%>
					<%--<select id="prov" name="province"></select> --%>
					<%--<select id="city" name="city"></select>--%>
					<%--<select id="dist" name="county"></select>--%>
				<%--</span>--%>
				<%--</c:if>--%>
				<%--<c:if test="${userorg_roleopenflag==1 }">--%>
				<%--<shiro:hasAnyRoles name="机构管理员,代理机构">--%>
				<%--<input type="text" placeholder='请输入学校名称' id="schoolShortName"/>--%>
				<%--</shiro:hasAnyRoles>--%>
				<%--</c:if>--%>

			</div>
			<div style="margin: 10px 0;">
				<input type="text" placeholder="姓名">
				<input type="text" placeholder="手机号">
				<input type="text" placeholder="学生编号">
				<input type="text" placeholder="毕业学校">
				<span style="margin-left: 10px;"><a href="javascript:;" class="btn btn-primary ">搜索</a></span>
				<span style="float: right"><a href="javascript:void(0);" class="btn btn-primary">导出用户</a></span>
			</div>
			<%--<div style="margin-top: 10px;text-align:right;padding:0 10px;">--%>
				<%----%>
			<%--</div>--%>
        </form>
        <div class="user-list earlyLitreDetail">
          	<table class="table table-center" id="tableList">
				<tr data-buy="true">
					<th width="5%">序号</th>
					<th width="5%">姓名</th>
					<th width="5%">性别</th>
					<th width="11%">毕业学校</th>
					<th width="11%">申请学校</th>
					<th width="8%">手机号</th>
					<th width="8%">出生日期</th>
					<th width="10%">户籍详细地址</th>
					<th width="9%">
						提交时间
						<i class="icon iconfont">&#xe617;</i>
					</th>
					<th width="8%">审核状态</th>
					<th width="9%">学生编号</th>
					<th width="20%">操作</th>
				<%--	<th style="display:none" width="0%"></th>--%>
				</tr>
				<tr>
					<td>1</td>
					<td>杨君君</td>
					<td>女</td>
					<td>成都三原外国语小学</td>
					<td>成都七中</td>
					<td>18623235314</td>
					<td>1992-1-29</td>
					<td>
						<div class="detailSite">
							三二四医院小区三二四医院小区医院小区医
							院小区医院小区三二四医院小区三二四医院小区医院小区医院小区医院小区
						</div>
					</td>
					<td>2018-3-3 12:31</td>
					<td>待审核</td>
					<td>18 21 10000 12</td>
					<td>
						<a href="##">通过</a>|
						<a href="##">不通过</a>|
						<a href="##">查看</a>
					</td>
				</tr>
				<%--<c:forEach items="${result}" var="detail" varStatus="status">--%>
					<%--<tr data-buy="true" onclick="setUserNameAndId('${detail.userId}','${detail.userName}')">--%>
						<%--<td>${status.index+1}</td>--%>
						<%--<td>${detail.schoolName}</td>--%>
						<%--<td>${detail.enrollmentName}</td>--%>
						<%--<td>${detail.provinceName}</td>--%>
						<%--<td>${detail.cityName}</td>--%>
						<%--<td>${detail.districtName}</td>--%>
						<%--<td><fmt:formatDate value="${detail.createTime}" pattern="yyyy-MM-dd"/></td>--%>
						<%--<td><c:if test="${detail.isShalve == 0}">未上架</c:if><c:if test="${detail.isShalve == 1}">已上架</c:if></td>--%>
						<%--<td><c:if test="${detail.isTop == 0}">未置顶</c:if><c:if test="${detail.isTop == 1}">已置顶</c:if></td>--%>
						<%--<td class="slink">--%>
							<%--<c:if test="${detail.isShalve == 1}">--%>
								<%--<a class="offShelf"  href="javaScript:updateRiseSchool('${detail.id}',0,'');" data-value="0">下架</a>|--%>
							<%--</c:if>--%>
							<%--<c:if test="${detail.isShalve == 0}">--%>
								<%--<a class="upShelf"  href="javaScript:updateRiseSchool('${detail.id}',1,'');" data-value="1">上架</a>|--%>
							<%--</c:if>--%>
							<%--<c:if test="${detail.isTop == 0}">--%>
								<%--<a class="top"  href="javaScript:updateRiseSchool('${detail.id}','',1);" data-value="1">置顶</a>|--%>
							<%--</c:if>--%>
							<%--<c:if test="${detail.isTop == 1}">--%>
								<%--<a class="down"  href="javaScript:updateRiseSchool('${detail.id}','',0);" data-value="0">取消置顶</a>|--%>
							<%--</c:if>--%>
							<%--<a class="countManagement"   href="##">账号管理</a>|--%>
							<%--<a class="more" href="#3">管理</a>--%>
							<%--<ul class="none box" style="display: none;">--%>
								<%--<li><a class=""  href="javaScript:loalUrl(0,'${detail.id}','${detail.schoolName}');">基本信息</a></li>--%>
								<%--<li><a class=""   href="javaScript:loalUrl(1,'${detail.id}','${detail.schoolName}');">学校详情</a></li>--%>
								<%--<li><a class=""  href="javaScript:loalUrl(2,'${detail.id}','${detail.schoolName}');">学校风采</a></li>--%>
								<%--<li><a href="javaScript:loalUrl(3,'${detail.id}','${detail.schoolName}');">升学</a></li>--%>
							<%--</ul>--%>
						<%--</td>--%>
						<%--<td style="display:none" id="${detail.id}">${detail.id}</td>--%>
					<%--</tr>--%>
				<%--</c:forEach>--%>


				<%--<c:choose>
					<c:when test="${userorg_roleopenflag==1 && proxyOrgRole ==1 }">
						<tr><td colspan="15">暂无数据</td></tr>
					</c:when>
					<c:otherwise>
						<tr><td colspan="14">暂无数据</td></tr>
					</c:otherwise>
				</c:choose>--%>
				
				
		</table>
			<div class="pages pagination">

			</div>
        </div>
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
	<a href="##">取消</a>
	<a href="##">确认通过</a>
</div>


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