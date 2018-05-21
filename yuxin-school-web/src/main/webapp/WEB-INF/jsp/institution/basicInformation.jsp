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
    <input type="hidden" value="${ins.id}" id="insId">
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">基本信息管理</h2>
                <span class="line"></span>
            </div>
            <div class="addingMechanism" style="display: block;">
                    <div style="margin-top: 0;">
                        <span class="mechanismName">机构名称：</span>
                        <input type="text" style="width: 460px;" maxlength="20" value="${ins.name}" id="insName" onblur="value=value.replace(/[^\u4E00-\u9FA5]/g,'')" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\u4E00-\u9FA5]/g,''))">
                    </div>
                    <div id="orgType">
                        <span class="mechanismName">机构分类：</span>
                        <c:if test="${catSize == 1}">
                                <c:forEach items="${categoryVos}" var="cate" varStatus="vs">
                                <div style="padding-left: 80px;margin-top: -17px;">
                                    <select name="" class="findFistCategorys">
                                        <option value="">请选择一级分类</option>
                                        <c:forEach items="${fistCategorys}" var="fistCat">
                                            <c:if test="${cate.oneLevelId == fistCat.id}">
                                                <option value="${fistCat.id}" selected>${fistCat.codeName}</option>
                                            </c:if>
                                            <c:if test="${cate.oneLevelId != fistCat.id}">
                                                <option value="${fistCat.id}" >${fistCat.codeName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                    <select name="" class="findSecondCategorys">
                                        <option value="">请选择二级分类</option>
                                        <c:forEach items="${secondCategorys}" var="second">
                                            <c:if test="${cate.oneLevelId eq second.parentId}">
                                                <c:if test="${cate.twoLevelId == second.id}">
                                                    <option value="${second.id}" selected>${second.codeName}</option>
                                                </c:if>
                                                <c:if test="${cate.twoLevelId != second.id}">
                                                    <option value="${second.id}">${second.codeName}</option>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>

                                    </select>
                                    <span class="iconBtn addType">+</span>
                                </div>
                            </c:forEach>
                        </c:if>
                        <c:if test="${catSize != 1}">
                            <c:forEach items="${categoryVos}" var="cate" varStatus="vs">
                                <c:choose>
                                    <c:when test="${vs.count==fn:length(categoryVos)}">
                                        <div style="padding-left: 80px;margin-top: 6px;">
                                            <select name="" class="findFistCategorys">
                                                <option value="">请选择一级分类</option>
                                                <c:forEach items="${fistCategorys}" var="fistCat">
                                                    <c:if test="${cate.oneLevelId == fistCat.id}">
                                                        <option value="${fistCat.id}" selected>${fistCat.codeName}</option>
                                                    </c:if>
                                                    <c:if test="${cate.oneLevelId != fistCat.id}">
                                                        <option value="${fistCat.id}" >${fistCat.codeName}</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <select name="" class="findSecondCategorys">
                                                <option value="">请选择二级分类</option>
                                                <c:forEach items="${secondCategorys}" var="second">
                                                    <c:if test="${cate.oneLevelId == second.parentId}">
                                                        <c:if test="${cate.twoLevelId == second.id}">
                                                            <option value="${second.id}" selected>${second.codeName}</option>
                                                        </c:if>
                                                        <c:if test="${cate.twoLevelId != second.id}">
                                                            <option value="${second.id}">${second.codeName}</option>
                                                        </c:if>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                            <span class="iconBtn deleteType">-</span>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <c:if test="${vs.index == 0}">
                                            <div style="padding-left: 80px;margin-top: -17px;">
                                                <select name="" class="findFistCategorys">
                                                    <option value="">请选择一级分类</option>
                                                    <c:forEach items="${fistCategorys}" var="fistCat">
                                                        <c:if test="${cate.oneLevelId == fistCat.id}">
                                                            <option value="${fistCat.id}" selected>${fistCat.codeName}</option>
                                                        </c:if>
                                                        <c:if test="${cate.oneLevelId != fistCat.id}">
                                                            <option value="${fistCat.id}" >${fistCat.codeName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <select name="" class="findSecondCategorys">
                                                    <option value="">请选择二级分类</option>
                                                    <c:forEach items="${secondCategorys}" var="second">
                                                        <c:if test="${cate.oneLevelId == second.parentId}">
                                                            <c:if test="${cate.twoLevelId == second.id}">
                                                                <option value="${second.id}" selected>${second.codeName}</option>
                                                            </c:if>
                                                            <c:if test="${cate.twoLevelId != second.id}">
                                                                <option value="${second.id}">${second.codeName}</option>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <span class="iconBtn addType">+</span>
                                            </div>
                                        </c:if>
                                        <c:if test="${vs.index > 0}">
                                            <div style="padding-left: 80px;margin-top: 6px;">
                                                <select name="" class="findFistCategorys">
                                                    <option value="">请选择一级分类</option>
                                                    <c:forEach items="${fistCategorys}" var="fistCat">
                                                        <c:if test="${cate.oneLevelId == fistCat.id}">
                                                            <option value="${fistCat.id}" selected>${fistCat.codeName}</option>
                                                        </c:if>
                                                        <c:if test="${cate.oneLevelId != fistCat.id}">
                                                            <option value="${fistCat.id}" >${fistCat.codeName}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <select name="" class="findSecondCategorys">
                                                    <option value="">请选择二级分类</option>
                                                    <c:forEach items="${secondCategorys}" var="second">
                                                        <c:if test="${cate.oneLevelId == second.parentId}">
                                                            <c:if test="${cate.twoLevelId == second.id}">
                                                                <option value="${second.id}" selected>${second.codeName}</option>
                                                            </c:if>
                                                            <c:if test="${cate.twoLevelId != second.id}">
                                                                <option value="${second.id}">${second.codeName}</option>
                                                            </c:if>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                                <span class="iconBtn deleteType">-</span>
                                            </div>
                                        </c:if>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </c:if>

                    </div>
                    <div>
                        <span class="mechanismName">机构地址：</span>
                        <input value="${ins.province}" style="display: none" id="eduAreaCode">
                        <select name="eduArea" id="eduArea" onchange="queryRiseSchoolDict(1)">
                           <option value="">请选择省份</option>
                           <option value="${ins.province}" selected >${ins.provinceName}</option>
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
                        <input type="text" id="address" placeholder="请输入详细地址" style="margin-left: 80px;margin-top: 14px;width: 460px;" maxlength="50" value="${ins.address}" >
                    </div>
                    <div>
                        <span style="float: left;">联系电话：</span>
                        <div style="display: inline-block" id="listMachine">
                            <c:if test="${tellSize == 0}">
                                <div        >
                                    <input type="text" placeholder="区号" style="width: 30px;" onkeyup="value=value.replace(/[^\d]/g,'')">-
                                    <input type="text" placeholder="请输入座机号" class="telephone" onkeyup="value=value.replace(/[^\d]/g,'')">
                                    <span class="iconBtn addMachine" >+</span>
                                </div>
                            </c:if>
                            <c:forEach items="${tells}" var="tell" varStatus="vs">
                                <div>
                                     <input type="text" placeholder="" style="width: 30px;" value="${ fn:split(tell, '-')[0] }" onkeyup="value=value.replace(/[^\d]/g,'')">-
                                     <input type="text" placeholder="请输入座机号" class="telephone" value="${ fn:split(tell, '-')[1] }" onkeyup="value=value.replace(/[^\d]/g,'')">
                                    <c:if test="${vs.count == 1}">
                                        <span class="iconBtn addMachine" >+</span>
                                    </c:if>
                                    <c:if test="${vs.count > 1}">
                                        <c:choose>
                                            <c:when test="${vs.count==fn:length(tells)}">
                                                <span class="iconBtn deleteMachine">-</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="iconBtn addMachine" >+</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                        <div id="listPhone" style="padding-left: 70px;">
                            <c:if test="${mobileSize == 0}">
                                <div style="margin-top: 5px;">
                                    <input type="text" style="width: 440px;" placeholder="请输入手机号" class="phoneNum" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'')">
                                    <span class="iconBtn addPhone" >+</span>
                                </div>
                            </c:if>
                            <c:forEach items="${mobiles}" var="mobile" varStatus="vs">
                                <div style="margin-top: 5px;">
                                    <input type="text" style="width: 440px;" placeholder="请输入手机号" maxlength="11" class="phoneNum" value="${mobile}" onkeyup="value=value.replace(/[^\d]/g,'')">
                                    <c:if test="${vs.count == 1}">
                                        <span class="iconBtn addPhone" >+</span>
                                    </c:if>
                                    <c:if test="${vs.count > 1}">
                                        <c:choose>
                                            <c:when test="${vs.count==fn:length(mobiles)}">
                                                <span class="iconBtn deletePhone" >-</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="iconBtn addPhone" >+</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div>
                        <span>系统标签：</span>
                            <c:forEach var="label" items="${sysLabel}">
                             <span href="##" class="systemBtn">
                                <input class="systemLabel sysLabel" label-id="${label.id}" value="${label.labelName}" maxlength="5">
                                <i class="icon iconfont deleteBtn">&#xe610;</i>
                            </span>
                            </c:forEach>
                        <span class="iconBtn addSystem">+</span>
                    </div>
                <div>
                    <span>自定义标签：</span>

                    <c:forEach var="custom" items="${customLabel}">
                        <span href="##" class="customLabel">
                            <input class="systemLabel cusLabel" label-id="${custom.id}" value="${custom.labelName}">
                            <i class="icon iconfont deleteCustomLabel">&#xe610;</i>
                    </span>
                    </c:forEach>
                    <span class="iconBtn customLabelBtn">+</span>
                </div>
                <div>
                            <span>特色服务：</span>
                    <c:forEach var="spe" items="${specialSer}" varStatus="vs">
                                <c:choose>
                                    <c:when test="${vs.count==0}">

                                    </c:when>
                                    <c:otherwise>
                                        <span href="##" class="specialService">
                                            <img src="${spe.imgUrl}" alt="" class="iconPic" id="${spe.id}">
                                             <input class="systemLabel iconPicName" label-id="${spe.id}" value="${spe.labelName}" maxlength="5">
                                            <i class="icon iconfont deletespecialService">&#xe610;</i>
                                        </span>
                                    </c:otherwise>
                                </c:choose>

                    </c:forEach>
                            <span class="iconBtn specialServiceBtn">+</span>
                </div>
                <div>
                    <span>预约服务：<a href="##" style="color: #a1a1a1;font-size: 14px;">该服务内容用于展示在机构首页，让用户知晓预约的礼品</a></span>
                    <p>
                        <textarea name="" id="reservService" maxlength="30" style="width: 390px;height: 84px;
                        border: 1px solid #aeaeae;margin-left: 70px;margin-top: 10px;" placeholder="请输入提供的预约服务内容" >${ins.reservService}</textarea>
                    </p>
                </div>
                <div class="orgBtn">
                        <a href="##" class="btn btn-primary btn-mb closeMechanism">取消</a>
                        <a href="##" class="btn btn-primary btn-mb closeMechanism updateIns">保存</a>
                </div>

        </div>
        </div>
    </div>
    <div class="iconList">
            <div style="height: 250px;" class="imgDiv">
            </div>
            <div class="pages pagination">
            </div>
    </div>


        <%--特色服务图片弹窗--%>
        <div class="mienPopup coverPopup" style="width: 234px; height: 240px; margin-left: -135px;z-index: 1000;">
            <div class="uploadImageStyle">
                <label for="">分类图片：</label>
                <img src="" alt="" id="targetStyle" style="width: 400px;height: 300px;">
                <a href="javascript:void(0);" class="chooseImg">
                    <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgDataStyle" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic()" value="重新选择文件"/>
                </a>
            </div>
            <div class="mienBtn" style="bottom: 10px; left: 26px; text-align: center;width: 80% !important;">
                <a href="javascript:void(0)" class="btn btn-danger mienHide" style="margin: 0 20px">取消</a>
                <a href="javascript:void(0)" class="btn btn-success mienHide" onclick="saveCutPic();" style="margin: 0 20px">确定</a>
            </div>
        </div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>

<input type="hidden" id="x" name="x" value="0"/>
<input type="hidden" id="y" name="y" value="0"/>
<input type="hidden" id="w" name="w" value="0"/>
<input type="hidden" id="h" name="h" value="0"/>
<input type="hidden" id="imgUrl"/>

<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/institution/ajaxfileuploadR.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/institution/cutPic.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath %>/javascripts/institution/basicInformation.js"></script>




</body>
</html>
