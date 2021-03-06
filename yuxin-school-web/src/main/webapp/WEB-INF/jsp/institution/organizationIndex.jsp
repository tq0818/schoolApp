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
    <title>机构管理首页</title>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/organizationIndex.css">
</head>
<body onload="refresh()">
<input id="hasOneLevelId" value="${chooseParams.oneLevelId}" type="hidden"/>
<input id="hasTwoLevelId" value="${chooseParams.twoLevelId}" type="hidden"/>
<input id="hasIsCertified" value="${chooseParams.isCertified}" type="hidden"/>
<input id="hasIsShelves" value="${chooseParams.isShelves}" type="hidden"/>
<input id="hasPage" value="${chooseParams.page}" type="hidden"/>
<input id="hasPageSize" value="${chooseParams.pageSize}" type="hidden"/>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>--%>

    <div class="right-side set-system" style="width: 100%">
        <div class="mainbackground nopadding" style="margin: 0 10px;padding-bottom: 100px;">
            <div class="heading">
                <h2 class="h5">机构管理</h2>
                <a href="/InsInfoBase/businessEntry" class="merchant">商家入驻申请<span>(${count})</span></a>
                <span class="line"></span>
            </div>
            <form method="post" id="searchForm" class="userVideoListNew">
                    <div>
                        <span>区域筛选</span>
                        <select name="eduArea" id="eduArea" onchange="queryRiseSchoolDict(1)">
                            <option value="">请选择省份</option>
                            <c:choose>
                                <c:when test="${empty chooseParams.province}">
                                    <option value="510000">四川省</option>
                                </c:when>
                                <c:otherwise>
                                    <option selected value="510000">四川省</option>
                                </c:otherwise>
                            </c:choose>

                        </select>

                        <select name="eduSchool" id="eduSchool" onchange="queryRiseSchoolDict(2)">
                            <option value="">请选择市</option>
                            <c:forEach items="${cityList}" var="city">
                                <c:choose>
                                    <c:when test="${chooseParams.city == city.itemCode}">
                                        <option selected value="${city.itemCode}">${city.itemName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${city.itemCode}">${city.itemName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                        <select id="registStatus" name="status" onchange="queryInsData()" >
                            <option value="">请选择区</option>
                            <c:forEach items="${areaList}" var="area">
                                <c:choose>
                                    <c:when test="${chooseParams.area == area.itemCode}">
                                        <option selected value="${area.itemCode}">${area.itemName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${area.itemCode}">${area.itemName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="marginTop10">
                        <span>分类筛选</span>
                        <select name="" id="findFistCategorys">
                            <option value="">请选择一级分类</option>
                        </select>

                        <select name="" id="findSecondCategorys">
                            <option value="">请选择二级分类</option>
                        </select>
                    </div>
                    <div class="marginTop10 changeBtn " id="isCertified" >
                        <span>认证状态</span>

                        <a href="javascript:void(0)" class="btn btn-default btn-mb  <c:if test="${empty chooseParams.isCertified}"> btn-primary </c:if>">全部</a>
                        <a href="javascript:void(0)" class="btn btn-default btn-mb  <c:if test="${chooseParams.isCertified eq 1}"> btn-primary </c:if>">已认证</a>
                        <a href="javascript:void(0)" class="btn btn-default btn-mb  <c:if test="${chooseParams.isCertified eq 0}"> btn-primary </c:if>">未认证</a>
                    </div>

                    <div  class="marginTop10 changeBtn" id="isShelves">
                        <span>上下架&nbsp&nbsp&nbsp</span>
                        <a href="javascript:void(0)" class="btn btn-default  <c:if test="${empty chooseParams.isShelves}"> btn-primary </c:if> btn-mb">全部</a>
                        <a href="javascript:void(0)" class="btn btn-default btn-mb  <c:if test="${chooseParams.isShelves eq 1}"> btn-primary </c:if>">已上架</a>
                        <a href="javascript:void(0)" class="btn btn-default btn-mb  <c:if test="${chooseParams.isShelves eq 0}"> btn-primary </c:if>">未上架</a>
                    </div>
                    <div class="margin10 marginTop10" >
                        <span class="text">创建时间</span>
                        <input type="text" name="startTime" id="startTime" class="date-picker from" readonly />
                        <em>到</em>
                        <input type="text" name="endTime" id="endTime" class="date-picker to"  readonly />
                        <input type="text" id="insName" name="username" placeholder="请输入机构名称" />
                        <span><a href="javascript:;" class="btn btn-primary searchContents" style="margin: 0 20px;">查询</a></span>
                        <span style="float: right"><a href="javascript:;" class="btn btn-primary addOrganization" style="margin: 0 20px;">添加机构</a></span>
                    </div>
            </form>

            <div class="user-list">
                <table class="table table-center" id="tableList">
                    <tr data-buy="true">
                        <th width="3%">序号</th>
                        <th width="13%">机构名称</th>
                        <th width="5%">省份</th>
                        <th width="5%">市</th>
                        <th width="5%">区</th>
                        <th width="6%">一级分类</th>
                        <th width="25%">二级分类</th>
                        <th width="5%">上下架状态</th>
                        <th width="5%">认证状态</th>
                        <th width="18%">操作</th>
                    </tr>

                </table>
                <div class="pages pagination">
                </div>
            </div>
        </div>
    </div>
        <input type="hidden" id="selectCounts" value="${empty chooseParams.pageSize or chooseParams.pageSize eq 0 ? 10 : chooseParams.pageSize}">
    <!-- ajax加载中div开始 -->
    <div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
    <!--  ajax加载中div结束 -->
    <%--添加机构弹窗--%>
    <div class="addingMechanism">
        <h5>添加机构</h5>
        <div style="margin-top: 0;">
            <span class="mechanismName">机构名称：</span>
            <%--<input type="text" style="width: 460px;" maxlength="20" id="name" onblur="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))">--%>
            <input type="text" style="width: 460px;" maxlength="20" id="name">
        </div>
        <div id="orgType">
            <div class="catType">
                <span class="mechanismName">机构分类：</span>
                <select name=""  class="findFistCategorys2">
                    <option value="">请选择一级分类</option>
                </select>
                <select name=""  class="findSecondCategorys2">
                    <option value="">请选择二级分类</option>
                </select>
                <span class="iconBtn addType">+</span>
            </div>


        </div>
        <div class="siteChoose">
            <span class="mechanismName">机构地址：</span>
            <select name="eduArea" id="eduArea2" onchange="queryRiseSchoolDict2(1)">
                <option value="">请选择省份</option>
                <option value="510000">四川省</option>
            </select>
            <select name="eduSchool" id="eduSchool2" onchange="queryRiseSchoolDict2(2)">
                <option value="">请选择市</option>
            </select>
            <select id="registStatus2" name="status">
                <option value="">请选择区</option>
            </select>
            <br/>
            <input type="text" id="address" placeholder="请输入详细地址" style="margin-left: 80px;margin-top: 14px;width: 460px;" maxlength="50" >
        </div>
        <div>
            <span>机构账号：</span>
            <input type="text" id="userName" placeholder="请输入机构管理员账号" maxlength="20">
            <span style="color: #ff0000;">初始密码为：111111</span>
        </div>
        <div>
            <span style="float: left;">联系电话：</span>
            <div style="display: inline-block" id="listMachine">
                <div class="mobile">
                    <input type="text" placeholder="区号" style="width: 30px;" class="telephone" onkeyup="value=value.replace(/[^\d]/g,'')">-
                    <input type="text" placeholder="请输入座机号" class="telephone" onkeyup="value=value.replace(/[^\d]/g,'')">
                    <span class="iconBtn addMachine">+</span>
                </div>
                <%--<div>--%>
                    <%--<input type="text" placeholder="区号" style="width: 30px;">---%>
                    <%--<input type="text" placeholder="请输入座机号">--%>
                    <%--<span class="iconBtn deleteMachine">-</span>--%>
                <%--</div>--%>
            </div>
            <div id="listPhone">
                <div class="phone">
                    <input type="text"  placeholder="请输入手机号" maxlength="11" class="phoneNum" onkeyup="value=value.replace(/[^\d]/g,'')">
                    <span class="iconBtn addPhone" >+</span>
                </div>
                <%--<div>--%>
                    <%--<input type="text" placeholder="请输入手机号">--%>
                    <%--<span class="iconBtn deletePhone" >-</span>--%>
                <%--</div>--%>
            </div>

        </div>
        <div>
            <span>系统标签：</span>
            <%--<span href="javascript:void(0)" class="systemBtn">--%>
                <%--<input class="systemLabel" value="你的名字么" maxlength="5">--%>
                <%--<i class="icon iconfont deleteBtn">&#xe610;</i>--%>
            <%--</span>--%>
            <%--<span href="javascript:void(0)" class="systemBtn">
                <input class="systemLabel" value="sb">
                <i class="icon iconfont deleteBtn">&#xe610;</i>
            </span>--%>
            <span class="iconBtn addSystem">+</span>
        </div>
        <div id="orgs">
            <span>是否属于连锁机构：</span>
            <a href="javascript:void(0)"><input type="radio" name="org" id="isOrg" value="1" >是</a>
            <a href="javascript:void(0)"><input type="radio" name="org" id="noOrg" value="0" checked="true">否</a>
        </div>
        <div class="orgBtn">
            <a href="javascript:void(0)" class="btn btn-primary btn-mb closeMechanism">取消</a>
            <a href="javascript:void(0)" class="btn btn-primary btn-mb addMechanism">确认添加</a>
        </div>
    </div>
    <%--账号管理弹窗    --%>
    <div class="countPopup createCount">
        <p>该机构还未创建账号，是否立即创建？</p>
        <div class="countPopupBtn">
            <a href="javascript:void(0)" class="btn btn-primary btn-mb closeCountPopup canclImmediateCreation">取消</a>
            <a href="javascript:void(0)" class="btn btn-primary btn-mb closeCountPopup immediateCreation">立即创建</a>
        </div>
    </div>
    <div class="countPopup sureCount cureatManageUser">
            <h5>创建账号</h5>
            <p>机构账号：<input type="text" placeholder="请输入机构管理员账号" id="manageUser" maxlength="20"></p>
            <span>初始密码为：111111</span>
        <div class="countPopupBtn ">
            <a href="javascript:void(0)" class="btn btn-primary btn-mb closeCountPopup cabcelManageUser">取消</a>
            <a href="javascript:void(0)" class="btn btn-primary btn-mb closeCountPopup manageUser">确定创建</a>
        </div>
    </div>
    <div  class="countPopup editCount">
        <h5>修改密码</h5>
        <input type="hidden" id="userId" value="">
        <input id="insUserName" type="hidden" value="">
        <p id="user">账号：cdsywgyxx</p>
        <p>密码：<input type="password" id="updataPwd" maxlength="18"></p>
        <div class="countPopupBtn">
            <a href="javascript:void(0)" class="btn btn-primary btn-mb closeCountPopup cancelManageUser">取消</a>
            <a href="javascript:void(0)" class="btn btn-primary btn-mb closeCountPopup updateManageUser">保存</a>
        </div>
    </div>


    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/organizationIndex.js"></script>


</body>

</html>