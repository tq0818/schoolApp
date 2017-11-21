<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>编辑上架课程</title>
<%--    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />

    <link href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>


    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classedit.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css"/>
    <script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/plupload.full.min.js"></script>
    <script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/i18n/zh_CN.js"></script>
    <script type="text/javascript" src="http://cdn.staticfile.org/Plupload/2.1.1/moxie.js"></script>
    <script type="text/javascript" src="http://cdn.staticfile.org/jquery/2.2.1/jquery.js"></script>
    <script type="text/javascript" src="http://cdn.staticfile.org/qiniu-js-sdk/1.0.14-beta/qiniu.js"></script>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
    <div class="u-wrap classes">
        <div class="informationEditHeader">
            <div class="informationEditImg">
                <img src="/images/1.jpg" alt="">
                <div class="informationEditChoose">
                    <a href="##" ><input type="file">选择图片</a>
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
                    <label>价格：</label><input type="text" value="${searchAndResult.originalPrice}">
                </li>
                <li>
                    <label>实际价格：</label><input type="text" value="${searchAndResult.realPrice}">
                </li>
                <c:if test="${searchAndResult.liveFlag == '1'}">
                    <li>
                        <label>直播开始时间：</label><input type="text" value="${searchAndResult.lessonDate} ${searchAndResult.lessonTimeStart}:00" disabled="disabled">
                    </li>
                </c:if>
                <li>
                    <label>课程标签：</label><input type="text" id="labDesc">
                </li>
            </ul>
        </div>
        <div class="submitCourse">
            <button class="btn btn-success" onclick="toShelves('1');">立即上架</button>
            <button class="btn btn-warning" onclick="toShelves('0');">预约上架</button>
            <input type="text" placeholder="指定上架时间" id="shelvesTime">
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

<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/informationEditing.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/DateUtils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
</body>
</html>
