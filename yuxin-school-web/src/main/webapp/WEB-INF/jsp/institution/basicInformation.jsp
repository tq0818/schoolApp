<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>基本信息管理</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mine.css">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fonts/iconfont.css">
    <style>
        .gobal-progress{display: none !important;} 
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/basicInformation.css">
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_institution.jsp"></jsp:include>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_institutionLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">基本信息管理</h2>
                <span class="line"></span>
            </div>
            <div class="addingMechanism" style="display: block;">
                    <div style="margin-top: 0;">
                        <span class="mechanismName">机构名称：</span>
                        <input type="text" style="width: 460px;" maxlength="20" value="${ins.name}">
                    </div>
                    <div id="orgType">
                        <c:forEach items="${categoryVos}" var="cate" varStatus="vs">
                            <c:choose>
                                <c:when test="${vs.count==fn:length(categoryVos)}">
                                    <div style="padding-left: 80px;margin-top: 6px;">
                                        <select name="" id="findFistCategorys">
                                            <option value="">请选择一级分类</option>
                                            <option value="${cate.oneLevelId}" selected>${cate.firstcodeName}</option>
                                        </select>
                                        <select name="" id="findSecondCategorys">
                                            <option value="">请选择二级分类</option>
                                            <option value="${cate.twoLevelId}" selected>${cate.secondcodeName}</option>
                                        </select>
                                        <span class="iconBtn deleteType">-</span>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div>
                                        <span class="mechanismName">机构分类：</span>
                                        <select name="" id="findFistCategorys2">
                                            <option value="">请选择一级分类</option>
                                            <option value="${cate.oneLevelId}" selected>${cate.firstcodeName}</option>
                                        </select>
                                        <select name="" id="findSecondCategorys2">
                                            <option value="">请选择二级分类</option>
                                            <option value="${cate.twoLevelId}" selected>${cate.secondcodeName}</option>
                                        </select>
                                        <span class="iconBtn addType">+</span>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>


                        <%--<div>
                            <span class="mechanismName">机构分类：</span>
                            <select name="" id="findFistCategorys">
                                <option value="">请选择一级分类</option>
                            </select>
                            <select name="" id="findSecondCategorys">
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
                        </div>--%>
                    </div>
                    <div>
                        <span class="mechanismName">机构地址：</span>
                        <select name="eduArea" id="eduArea" onchange="queryRiseSchoolDict(1)">
                           <option value="">请选择省份</option>
                           <option value="${ins.province}" selected>${ins.provinceName}</option>
                        </select>
                        <select name="eduSchool" id="eduSchool" onchange="queryRiseSchoolDict(2)">
                            <option value="">请选择市</option>
                            <option value="${ins.city}" selected>${ins.cityName}</option>
                        </select>
                        <select id="registStatus" name="status">
                            <option value="">请选择区</option>
                            <option value="${ins.area}" selected>${ins.areaName}</option>
                        </select>
                        <br/>
                        <input type="text" placeholder="请输入详细地址" style="margin-left: 80px;margin-top: 14px;width: 460px;" maxlength="50" value="${ins.address}" >
                    </div>
                    <div>
                        <span style="float: left;">联系电话：</span>
                        <div style="display: inline-block" id="listMachine">
                            <c:forEach items="${tells}" var="tell" varStatus="vs">
                                <div>
                                     <input type="text" placeholder="" style="width: 30px;" value="${ fn:split(tell, '-')[0] }">-
                                     <input type="text" placeholder="请输入座机号" value="${ fn:split(tell, '-')[1] }">
                                </div>
                                <c:choose>
                                    <c:when test="${vs.count==fn:length(tells)}">
                                        <span class="iconBtn deleteMachine">-</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="iconBtn addMachine">+</span>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                        <div id="listPhone">
                            <c:forEach items="${mobiles}" var="mobile" varStatus="vs">
                                <input type="text" style="width: 440px;" placeholder="请输入手机号" value="${mobile}">
                                <c:choose>
                                    <c:when test="${vs.count==fn:length(mobiles)}">
                                        <span class="iconBtn deleteMachine">-</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="iconBtn addMachine">+</span>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </div>
                    </div>
                    <div>
                        <span>系统标签：</span>
                            <c:forEach var="label" items="${sysLabel}">
                             <span href="##" class="systemBtn">
                                <input class="systemLabel" label-id="${label.id}" value="${label.labelName}">
                                <i class="icon iconfont deleteBtn">&#xe610;</i>
                            </span>
                            </c:forEach>
                        <span class="iconBtn addSystem">+</span>
                    </div>
                <div>
                    <span>自定义标签：</span>

                    <c:forEach var="custom" items="${customLabel}">
                        <span href="##" class="customLabel">
                            <input class="systemLabel" label-id="${custom.id}" value="${custom.labelName}">
                            <i class="icon iconfont deleteCustomLabel">&#xe610;</i>
                    </span>
                    </c:forEach>
                    <span class="iconBtn customLabelBtn">+</span>
                </div>
                <div>
                            <span>特色服务：</span>
                    <c:forEach var="spe" items="${specialSer}">
                             <span href="##" class="specialService">
                                <img src="../../../images/institution/1.jpg" alt="" class="iconPic" >
                                <input class="systemLabel" label-id="${spe.id}" value="${spe.labelName}">
                                <i class="icon iconfont deletespecialService">&#xe610;</i>
                            </span>
                    </c:forEach>
                            <span class="iconBtn specialServiceBtn">+</span>
                </div>
                <div>
                    <span>预约服务：<a href="##" style="color: #a1a1a1;font-size: 14px;">该服务内容用于展示在机构首页，让用户知晓预约的礼品</a></span>
                    <p>
                        <textarea name="" id="" maxlength="30" style="width: 390px;height: 84px;
                        border: 1px solid #aeaeae;margin-left: 70px;margin-top: 10px;" placeholder="请输入提供的预约服务内容"></textarea>
                    </p>
                </div>
                <div class="orgBtn">
                        <a href="##" class="btn btn-primary btn-mb closeMechanism">取消</a>
                        <a href="##" class="btn btn-primary btn-mb closeMechanism">保存</a>
                </div>


        </div>
        </div>
    </div>
    <div class="iconList">
            <div style="height: 34px;line-height: 34px;padding-left: 10px;font-size: 14px;">
                <span>上传特色服务图标</span>
                <i class="icon iconfont closeIconList" style="float: right;margin: 10px;">&#xe610;</i>
            </div>
            <div style="height: 250px;" class="imgDiv">
                <a href="##" class="uploadImg">+<input type="file"></a>
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >

                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >

                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >

                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >
                <img src="../../../images/institution/1.jpg" alt="" class="iconListImg" >

            </div>
            <div class="pages pagination">
            </div>
    </div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>






<script src="<%=rootPath %>/javascripts/riseschool/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/basicInformation.js"></script>



</body>
</html>
