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
    <title>机构分类管理</title>
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
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/classification.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap query overflow">
    <%--<jsp:include page="/WEB-INF/jsp/menu/menu_statistics_query.jsp"></jsp:include>--%>
    <div class="right-side set-system" style="width: 100%">
        <div class="mainbackground nopadding" style="margin: 0 10px;">
            <div class="heading">
                <h2 class="h5">机构管理</h2>
                <a href="" class="merchant">商家入驻申请<span>(9)</span></a>
                <span class="line"></span>
            </div>
            <form method="post" id="searchForm" class="userVideoListNew">

                    <div>
                        <span>区域筛选</span>
                        <select name="" id="">
                            <option value=""></option>
                        </select>
                        <select name="" id="">
                            <option value=""></option>
                        </select>
                        <select name="" id="">
                            <option value=""></option>
                        </select>
                    </div>
                    <div class="marginTop10">
                        <span>分类筛选</span>
                        <select name="" id="">
                            <option value=""></option>
                        </select>
                        <select name="" id="">
                            <option value=""></option>
                        </select>
                    </div>
                    <div class="marginTop10 changeBtn" >
                        <span>认证状态</span>
                        <a href="##" class="btn btn-default btn-primary btn-mb">全部</a>
                        <a href="##" class="btn btn-default btn-mb">已认证</a>
                        <a href="##" class="btn btn-default btn-mb">未认证</a>
                    </div>
                    <div  class="marginTop10 changeBtn">
                        <span>推荐状态</span>
                        <a href="##" class="btn btn-default btn-primary btn-mb">全部</a>
                        <a href="##" class="btn btn-default btn-mb">已推荐</a>
                        <a href="##" class="btn btn-default btn-mb">未推荐</a>
                    </div>
                    <div  class="marginTop10 changeBtn" >
                        <span>上下架&nbsp&nbsp&nbsp</span>
                        <a href="##" class="btn btn-default btn-primary btn-mb">全部</a>
                        <a href="##" class="btn btn-default btn-mb">已上架</a>
                        <a href="##" class="btn btn-default btn-mb">未上架</a>
                    </div>
                    <div class="margin10 marginTop10" >
                        <span class="text">创建时间</span>
                        <input type="text" name="startTime" class="date-picker from" value="${startTime}" readonly/>
                        <em>到</em>
                        <input type="text" name="endTime" class="date-picker to" value="${endTime}" readonly/>
                        <input type="text" id="username" name="username" placeholder="请输入机构名称"/>
                        <span><a href="javascript:;" class="btn btn-primary searchContents" style="margin: 0 20px;">查询</a></span>
                        <span style="float: right"><a href="javascript:;" class="btn btn-primary addOrganization" style="margin: 0 20px;">添加机构</a></span>
                    </div>
            </form>
            <div class="user-list">
                <table class="table table-center" id="tableList">
                    <tr data-buy="true">
                        <th width="3%">序号</th>
                        <th width="12%">机构名称</th>
                        <th width="5%">省份</th>
                        <th width="5%">市</th>
                        <th width="5%">区</th>
                        <th width="5%">一级分类</th>
                        <th width="30%">二级分类</th>
                        <th width="5%">上下架状态</th>
                        <th width="5%">认证状态</th>
                        <th width="15%">操作</th>

                    </tr>
                    <tr data-buy="false">
                        <td>1</td>
                        <td>九方美术培训学校</td>
                        <td>四川</td>
                        <td>成都</td>
                        <td>高新</td>
                        <td>
                            <p>美术培训</p>
                        </td>
                        <td>
                            <p>绘画</p>
                        </td>
                        <td class="frameLowerReal">已上架</td>
                        <td class="authenticationReal">已认证</td>
                        <td class="slink">
                            <a href="##" class="frameLower">下架</a>|
                            <a href="##" class="authentication">取消认证</a>|
                            <a href="##" class="countManage">账号管理</a>|
                            <a href="##" class="manageBtn">管理</a>
                            <ul class="none box" style="display: none;">
                                <li><a href="">基本信息</a></li>
                                <li><a href="">风采管理</a></li>
                                <li><a href="">课程管理</a></li>
                                <li><a href="">名师管理</a></li>
                                <li><a href="">评论管理</a></li>
                            </ul>
                        </td>
                    </tr>
                    <tr data-buy="false">
                        <td>1</td>
                        <td>九方美术培训学校</td>
                        <td>四川</td>
                        <td>成都</td>
                        <td>高新</td>
                        <td>
                            <p>美术培训</p>
                            <p>美术培训</p>
                        </td>
                        <td>
                            <p>绘画</p>
                            <p>绘画</p>
                        </td>
                        <td class="frameLowerReal">已上架</td>
                        <td class="authenticationReal">已认证</td>
                        <td class="slink">
                            <a href="##" class="frameLower">下架</a>|
                            <a href="##" class="authentication">取消认证</a>|
                            <a href="##" class="countManage">账号管理</a>|
                            <a href="##" class="manageBtn">管理</a>
                            <ul class="none box" style="display: none;">
                                <li><a href="">基本信息</a></li>
                                <li><a href="">风采管理</a></li>
                                <li><a href="">课程管理</a></li>
                                <li><a href="">名师管理</a></li>
                                <li><a href="">评论管理</a></li>
                            </ul>
                        </td>
                    </tr>
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
    <%--添加机构弹窗--%>
    <div class="addingMechanism">
        <h5>添加机构</h5>
        <div style="margin-top: 0;">
            <span class="mechanismName">机构名称：</span>
            <input type="text" style="width: 460px;" maxlength="20">
        </div>
        <div id="orgType">
            <div>
                <span class="mechanismName">机构分类：</span>
                <select name="" id="">
                    <option value="">请选择一级分类</option>
                </select>
                <select name="" id="">
                    <option value="">请选择二级分类</option>
                </select>
                <span class="iconBtn addType">+</span>
            </div>
            <div style="padding-left: 80px;margin-top: 6px;">
                <select name="" id="">
                    <option value="">请选择一级分类</option>
                </select>
                <select name="" id="">
                    <option value="">请选择二级分类</option>
                </select>
                <span class="iconBtn deleteType">-</span>
            </div>

        </div>
        <div>
            <span class="mechanismName">机构地址：</span>
            <select name="" id="">
                <option value="">请选择省份</option>
            </select>
            <select name="" id="">
                <option value="">请选择市</option>
            </select>
            <select name="" id="">
                <option value="">请选择区</option>
            </select>
            <br/>
            <input type="text" placeholder="请输入详细地址" style="margin-left: 80px;margin-top: 14px;width: 460px;" maxlength="50" >
        </div>
        <div>
            <span>机构账号：</span>
            <input type="text" placeholder="请输入学校管理员账号">
            <span style="color: #ff0000;">初始密码为：111111</span>
        </div>
        <div>
            <span style="float: left;">联系电话：</span>
            <div style="display: inline-block" id="listMachine">
                <div>
                    <input type="text" placeholder="区号" style="width: 30px;">-
                    <input type="text" placeholder="请输入座机号">
                    <span class="iconBtn addMachine">+</span>
                </div>
                <div>
                    <input type="text" placeholder="区号" style="width: 30px;">-
                    <input type="text" placeholder="请输入座机号">
                    <span class="iconBtn deleteMachine">-</span>
                </div>
            </div>
            <div id="listPhone">
                <div style="margin-left: 73px;margin-top: 14px;">
                    <input type="text" style="width: 440px;" placeholder="请输入手机号">
                    <span class="iconBtn addPhone" >+</span>
                </div>
                <div style="margin-left: 73px;margin-top: 14px;">
                    <input type="text" style="width: 440px;" placeholder="请输入手机号">
                    <span class="iconBtn deletePhone" >-</span>
                </div>
            </div>

        </div>
        <div>
            <span>系统标签：</span>
            <span href="##" class="systemBtn">
                <span class="systemLabel">你的名字么</span>
                <i class="icon iconfont deleteBtn">&#xe610;</i>
            </span>
            <span href="##" class="systemBtn">
                <span class="systemLabel">我的名字没</span>
                <i class="icon iconfont deleteBtn">&#xe610;</i>
            </span>
            <span class="iconBtn addSystem">+</span>
        </div>
        <div>
            <span>是否属于连锁机构：</span>
            <a href="##"><input type="radio" name="org" value="1">是</a>
            <a href="##"><input type="radio" name="org" value="0" checked>否</a>
        </div>
        <div class="orgBtn">
            <a href="##" class="btn btn-primary btn-mb closeMechanism">取消</a>
            <a href="##" class="btn btn-primary btn-mb closeMechanism">确认添加</a>
        </div>
    </div>
    <%--账号管理弹窗    --%>
    <div class="countPopup createCount">
        <p>该账号还未创建账号，是否立即创建？</p>
        <div class="countPopupBtn">
            <a href="##" class="btn btn-primary btn-mb closeCountPopup">取消</a>
            <a href="##" class="btn btn-primary btn-mb closeCountPopup">立即创建</a>
        </div>
    </div>
    <div class="countPopup sureCount">
            <h5>创建账号</h5>
            <p>机构账号：<input type="text" placeholder="请输入学校管理员账号"></p>
            <span>初始密码为：111111</span>
        <div class="countPopupBtn">
            <a href="##" class="btn btn-primary btn-mb closeCountPopup">取消</a>
            <a href="##" class="btn btn-primary btn-mb closeCountPopup">确定创建</a>
        </div>
    </div>
    <div  class="countPopup editCount">
        <h5>修改密码</h5>
        <p>账号：cdsywgyxx</p>
        <p>密码：<input type="password" ></p>
        <div class="countPopupBtn">
            <a href="##" class="btn btn-primary btn-mb closeCountPopup">取消</a>
            <a href="##" class="btn btn-primary btn-mb closeCountPopup">保存</a>
        </div>
    </div>


    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/common/DateUtils.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>

    <script type="text/javascript" src="<%=rootPath%>/javascripts/institution/classification.js"></script>

</body>
</html>