<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>学员管理</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css" />
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link href="<%=rootPath%>/stylesheets/query.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/query/statistics.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fonts/iconfont.css">
    <style type="text/css">
        .pages li.disabled{padding:0px;}
        .userVideoListNew select{width: 180px;margin-right: 10px;}
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/studentManagement.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>--%>
    <div class="right-side set-system" style="width: 100%">
        <div class="mainbackground nopadding" style="margin: 0 10px;">
            <div class="heading">
                <h2 class="h5">学员管理</h2>
                <span class="line"></span>
            </div>
            <form method="post" id="searchForm" class="userVideoListNew">
                    <div class="marginTop10 changeBtn" id="status">
                        <span>处理状态</span>
                        <a href="##" class="btn btn-default btn-primary status" data-flag="">全部</a>
                        <a href="##" class="btn btn-default status" data-flag="1">已处理</a>
                        <a href="##" class="btn btn-default status" data-flag="0">未处理</a>
                    </div>
                    <div class="marginTop10">
                        <span>预约机构</span>
                        <select id="reServApplyInsId" onchange="findReServApplyClassByInsId()">
                            <option value="">请选择机构</option>
                            <c:forEach var="ins" items="${insList}">
                                <option value="${ins.relationId}">${ins.relationName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="marginTop10">
                        <span>预约课程</span>
                        <select name="" id="reServApplyClass" onchange="findReServApplyListByClassId()">
                            <option value="">请选择课程</option>
                        </select>
                    </div>
                    <div class="marginTop10  "  >
                        <span>预约时间</span>

                        <input type="text" name="startTime" id="startTime" class="date-picker from"  readonly/>
                        <em>到</em>
                        <input type="text" name="endTime" id="endTime" class="date-picker to"  readonly/>

                        <input type="text" id="mobile" placeholder="手机号"/>
                        <span><a href="javascript:void(0);" class="btn btn-primary search" style="margin: 0 20px;">搜索</a></span>
                        <span style="float: right;"><a href="javascript:void(0);" class="btn btn-primary" style="margin: 0 20px;">导出用户</a></span>
                    </div>
            </form>
            <div class="user-list">
                <table class="table table-center" id="tableList">
                    <tr data-buy="true">
                        <th width="3%">序号</th>
                        <th width="5%">手机号</th>
                        <th width="5%">预约机构</th>
                        <th width="5%">预约课程</th>
                        <th width="5%">课程价格(元)</th>
                        <th width="5%">提交时间</th>
                        <th width="5%">处理状态</th>
                        <th width="5%">备注</th>
                        <th width="5%">操作</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>18623235314</td>
                        <td>九方美术培训学校</td>
                        <td>课程名称一</td>
                        <td>20.00</td>
                        <td>2018-3-3 12:31</td>
                        <td>未处理</td>
                        <td class="addRemarks">备注</td>
                        <td>
                            <a href="##">切换状态</a>|
                            <a href="##" class="addRemarks">添加备注</a>
                        </td>
                    </tr>

                </table>
                <div class="pages pagination">
                </div>
            </div>
        </div>
    </div>
        <input type="hidden" id="selectCounts" value="10">
    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
    <!--  ajax加载中div结束 -->
    <%--添加备注弹窗    --%>
    <div class="remarks">
        <textarea name="" id="not" placeholder="请输入备注信息"></textarea>
        <div class="remarksBtn">
            <a href="##" class="btn btn-primary">取消</a>
            <a href="##" class="btn btn-primary addNot" id="addNote" data-id="">保存</a>
        </div>
    </div>

    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>

    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/studentManagement.js"></script>

</body>
</html>