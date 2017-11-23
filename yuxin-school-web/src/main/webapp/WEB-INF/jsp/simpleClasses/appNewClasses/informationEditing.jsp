<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>编辑上架课程</title>
<%--    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />


    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>--%>



    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/informationEditing.js"></script>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/jedate.css">
    <script src="<%=rootPath %>/javascripts/plus/jquery.jedate.min.js"></script>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
    <div class="u-wrap classes">
        <div class="informationEditHeader">
            <div class="informationEditImg">
                <img src="${commodityPicUrl }${searchAndResult.cover}" id="pic" alt="">
                <div class="informationEditChoose">
                    <a href="##" ><input type="file" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic();">选择图片</a>
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
                <a href="##" class="courseDetail btn btn-default" onclick="queryClassDetails(${searchAndResult.id});">课程详情</a>
            </div>
        </div>
        <div>
            <ul class="classification">
                <li>
                    <label>课程分类</label>

                           <select id="courseCaId" onchange="chooseSlibMenu($(this));">
                               <c:forEach var="menu" items="${firstMenus}">
                                   <option value="${menu.id}">${menu.name}</option>
                               </c:forEach>
                           </select>

                </li>
                <li>
                    <label>学段</label>

                        <select id="gradeId" onchange="chooseSlibMenu($(this));">
                            <c:forEach var="menu" items="${secondeMenus}" varStatus="status">
                                <option value="${menu.id}">${menu.name}</option>
                            </c:forEach>
                        </select>

                </li>
                <li>
                    <label>学科</label>

                            <select id="subjectId" onchange="chooseSlibMenu($(this));">
                                <c:forEach var="menu" items="${thirdMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>

                </li>
                <li>
                    <label>知识点专题</label>

                            <select id="kwonProId" onchange="chooseSlibMenu($(this));">
                                <c:forEach var="menu" items="${forthMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>

                </li>
                <li>
                    <label>知识点</label>

                            <select id="knowId" onchange="chooseSlibMenu($(this));">
                                <c:forEach var="menu" items="${fifthMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>

                </li>
                <li>
                    <label>阶段</label>

                            <select id="stageId">
                                <c:forEach var="menu" items="${jieduanMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>

                </li>
                <li>
                    <label>类型</label>

                            <select id="typeId">
                                <c:forEach var="menu" items="${leixingMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>

                </li>
            </ul>
        </div>
        <div>
            <ul class="screeningConditions">
                <li>
                    <label>课程标签：</label><input type="text" id="labDesc" value="${searchAndResult.labDesc}">
                </li>
                <li>
                    <label>价格：</label><input type="text" id="appPrice" value="${searchAndResult.originalPrice}">
                </li>
                <li>
                    <label>实际价格：</label><input type="text" id="salePrice" value="${searchAndResult.realPrice}">
                </li>
                <c:if test="${searchAndResult.liveFlag == '1'}">
                    <li>
                        <label>开始时间：</label><input type="text" value="${searchAndResult.lessonDate} ${searchAndResult.lessonTimeStart}:00" disabled="disabled">
                    </li>
                </c:if>
            </ul>
        </div>
        <div class="submitCourse">
            <button class="btn btn-success" onclick="toShelves('1');">立即上架</button>
            <button class="btn btn-warning" onclick="toShelves('0');">预约上架</button>
            <input type="text" placeholder="指定上架时间" id="shelvesTime" value="${searchAndResult.reserveTime}" readonly>
        </div>
    </div>
        <c:if test="${searchAndResult.liveFlag==1 }">
            <input id="lab" name="lab" value="live" type="hidden"/>
        </c:if>
        <c:if test="${searchAndResult.videoFlag==1 }">
            <input id="lab" name="lab" value="video" type="hidden"/>
        </c:if>
        <c:if test="${searchAndResult.faceFlag==1 }">
            <input id="lab" name="lab" value="face" type="hidden"/>
        </c:if>
        <c:if test="${searchAndResult.liveFlag==0&&searchAndResult.videoFlag==0&&allCommdotity.faceFlag==0 }">
            <input id="lab" name="lab" value="remote" type="hidden"/>
        </c:if>
        <div id="shelvesInfo">
            <input name="id" type="hidden" id="commodityId" value="${searchAndResult.id}"/>
            <input name="appId" type="hidden" id="appId" value="${searchAndResult.appId}"/>
        </div>
    <form method="post" id="myForm">

    </form>

<script>
    $.jeDate("#shelvesTime",{
        format:"YYYY-MM-DD hh:mm:ss",
        isTime:true,
        minDate:"2014-09-19 00:00:00"
    })



</script>

</body>
</html>
