<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>编辑上架课程</title>
    <%--    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
        <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
        <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />


        <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>--%>


    <%--<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <%--<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>--%>
    <%--<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">--%>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/informationEditing.js"></script>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/jedate.css">
    <script src="<%=rootPath %>/javascripts/plus/jquery.jedate.min.js"></script>
    <style>
        sb {
            color: red;
        }

        .classification>li {
            margin: 10px 15px 30px 0  !important;
            width: 22.5%  !important;

        }
    </style>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
<div class="u-wrap classes">
    <div class="informationEditHeader">
        <div class="informationEditImg">
            <c:choose>
                <c:when test="${searchAndResult.cover != ''}">
                    <img src="${commodityPicUrl }${searchAndResult.cover}" id="pic" alt="">
                </c:when>
                <c:otherwise>
                    <img src="/images/pic_default.png" id="pic" alt="">
                </c:otherwise>
            </c:choose>

            <div class="informationEditChoose">
                <a href="##"><input type="file" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico">选择图片</a>
            </div>
        </div>
        <div class="informationEditDetail">
            <ul>
                <li>
                    <label>课程名称:</label>
                    <span>
                             <c:choose>
                                 <c:when test="${fn:length(searchAndResult.name)>15}">
                                     ${fn:substring(searchAndResult.name, 0, 15)}...
                                 </c:when>
                                 <c:otherwise>
                                     ${searchAndResult.name }
                                 </c:otherwise>
                             </c:choose>
                        </span>
                </li>
                <li>
                    <label>教师:</label>
                    <span>${searchAndResult.teacherName}</span>
                </li>
                <li>
                    <label>学校:</label>
                    <span>${searchAndResult.schoolName}</span>
                </li>
                <c:if test="${searchAndResult.liveFlag eq 1}">
                    <li>
                        <label>时长:</label>
                        <span>${searchAndResult.lessonDate} ${searchAndResult.lessonTimeStart}~${searchAndResult.lessonDate} ${searchAndResult.lessonTimeEnd}</span>
                    </li>
                </c:if>
                <c:if test="${searchAndResult.liveFlag ne 1}">
                    <li>
                        <label>时长:</label>
                        <span>${searchAndResult.lessonDate}</span>
                    </li>
                </c:if>
                <li>
                    <label>学习人数:</label>
                    <span>${searchAndResult.actualNum}人学习</span>
                </li>
            </ul>
            <a href="##" class="courseDetail btn btn-default"
               onclick="queryClassDetailsa(${searchAndResult.id});">课程详情</a>
        </div>
    </div>
    <div>
        <ul class="classification">
            <li>
                <label>课程分类</label>
                <select id="courseCaIdList" onchange="chooseSlibMenu($(this));">
                    <c:forEach var="menu" items="${firstMenus}">
                        <c:choose>
                            <c:when test="${searchAndResult.categoryId == menu.id}">
                                <option value="${menu.id}" selected>${menu.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${menu.id}">${menu.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <sb>*</sb>
            </li>
            <li>
                <label>学段</label>
                <select id="gradeIdList" onchange="chooseSlibMenu($(this));">
                    <c:forEach var="menu" items="${secondeMenus}" varStatus="status">
                        <c:choose>
                            <c:when test="${searchAndResult.gradeId == menu.id}">
                                <option value="${menu.id}" selected>${menu.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${menu.id}">${menu.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <sb>*</sb>
            </li>
            <li>
                <label>学科</label>
                <select id="subjectIdList" onchange="chooseSlibMenu($(this));">
                    <c:forEach var="menu" items="${thirdMenus}">
                        <c:choose>
                            <c:when test="${searchAndResult.subjectId == menu.id}">
                                <option value="${menu.id}" selected>${menu.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${menu.id}">${menu.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <sb>*</sb>
            </li>
            <li>
                <label>知识点专题</label>
                <select id="kwonProIdList" onchange="chooseSlibMenu($(this));">
                    <c:forEach var="menu" items="${forthMenus}">
                        <c:choose>
                            <c:when test="${searchAndResult.kwonProId == menu.id}">
                                <option value="${menu.id}" selected>${menu.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${menu.id}">${menu.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <sb>*</sb>
            </li>
            <li>
                <label>知识点</label>
                <select id="knowIdList" onchange="chooseSlibMenu($(this));">
                    <c:forEach var="menu" items="${fifthMenus}">
                        <c:choose>
                            <c:when test="${searchAndResult.knowId == menu.id}">
                                <option value="${menu.id}" selected>${menu.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${menu.id}">${menu.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <sb>*</sb>
            </li>
            <li>
                <label>阶段</label>
                <select id="stageIdList">
                    <c:forEach var="menu" items="${jieduanMenus}">
                        <c:choose>
                            <c:when test="${searchAndResult.stageId == menu.id}">
                                <option value="${menu.id}" selected>${menu.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${menu.id}">${menu.name}</option>
                            </c:otherwise>
                        </c:choose>

                    </c:forEach>
                </select>
            </li>
            <li>
                <label>类型</label>
                <select id="typeIdList">
                    <c:forEach var="menu" items="${leixingMenus}">
                        <c:choose>
                            <c:when test="${searchAndResult.typeId == menu.id}">
                                <option value="${menu.id}" selected>${menu.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${menu.id}">${menu.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </li>
        </ul>
    </div>
    <div>
        <ul class="screeningConditions">
            <li style="display:none">
                <label>课程标签：</label><input type="text" id="labDesc" value="${searchAndResult.labDesc}" >
            </li>
            <li>
                <label>价格：</label>
                <c:choose>
                    <c:when test="${searchAndResult.appPrice == null}">
                       <input type="text" id="appPrice" value="${searchAndResult.originalPrice}">
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="appPrice" value="${searchAndResult.appPrice}">
                    </c:otherwise>
                </c:choose>
            </li>
            <li>
                <label>实际价格：</label>
                 <c:choose>
                    <c:when test="${searchAndResult.salePrice == null}">
                        <input type="text" id="salePrice" value="${searchAndResult.realPrice}">
                    </c:when>
                    <c:otherwise>
                        <input type="text" id="salePrice" value="${searchAndResult.salePrice}">
                    </c:otherwise>
                </c:choose>
            </li>
            <c:if test="${searchAndResult.liveFlag == '1'}">
                <li>
                    <label>开始时间：</label><input type="text"
                                               value="${searchAndResult.lessonDate} ${searchAndResult.lessonTimeStart}:00"
                                               disabled="disabled">
                </li>
            </c:if>
        </ul>
    </div>
    <div class="submitCourse">
        <button class="btn btn-success" onclick="toShelves('1','${editFlag}');">立即上架</button>
        <button class="btn btn-warning" onclick="toShelves('0','${editFlag}');">预约上架</button>
        <input type="hidden" id="shelvesTimes" value="${searchAndResult.reserveTime}"/>
        <input type="text" placeholder="指定上架时间" id="shelvesTime" readonly/>
    </div>
    <script>
        var time = $("#shelvesTimes").val();
        time = time.replace(".0", "")
        $("#shelvesTime").val(time);
    </script>
</div>
<c:if test="${searchAndResult.liveFlag==1 }">
    <input id="labe" name="lab" value="live" type="hidden"/>
</c:if>
<c:if test="${searchAndResult.videoFlag==1 }">
    <input id="labe" name="lab" value="video" type="hidden"/>
</c:if>
<c:if test="${searchAndResult.faceFlag==1 }">
    <input id="labe" name="lab" value="face" type="hidden"/>
</c:if>
<c:if test="${searchAndResult.liveFlag==0&&searchAndResult.videoFlag==0&&allCommdotity.faceFlag==0 }">
    <input id="labe" name="lab" value="remote" type="hidden"/>
</c:if>
<div id="shelvesInfo">
    <input name="id" type="hidden" id="commodityId" value="${commodityId}"/>
    <input name="appId" type="hidden" id="appId" value="${appId}"/>
</div>
<form method="post" id="myForma">

</form>


<form method="post" id="myForm">

</form>
<script>
    $.jeDate("#shelvesTime", {
        format: "YYYY-MM-DD hh:mm:ss",
        isTime: true,
        minDate: "2014-09-19 00:00:00"
    })

    function queryClassDetailsa(id) {
        $("#myForma").html("");
        var input = "<input type='hidden' value='" + id + "' name='id'/><input type='hidden' value='" + $("#labe").val() + "' name='lable'/>";
        $("#myForma").html(input);
        $("#myForma").attr("action", "<%=rootPath %>/editSimpleCourse/editClassTypeMessage").submit();
    }


</script>
<script>
//    图片预览
    var fileEle = document.getElementById('imgData');
    var imgEle = document.getElementById('pic');
    fileEle.onchange = function(e) {
        var file1 = e.target.files[0];
        var url1 = window.URL.createObjectURL(file1);
        imgEle.src = url1;
        savePic();
    }
</script>

</body>
</html>
