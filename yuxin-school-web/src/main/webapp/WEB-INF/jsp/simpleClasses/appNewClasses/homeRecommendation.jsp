<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>首页推荐</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css"/>

    <link href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>


    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
<%--<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>--%>
<div class="u-wrap classes">
    <div class="informationEditHeader">
        <div class="informationEditImg">
            <img src="${commodityPicUrl }${searchAndResult.cover}" alt="">
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
               onclick="queryClassDetails(${searchAndResult.id});">课程详情</a>
        </div>
    </div>
    <div>
        <div>
            <label for="">推荐位置</label>
            <c:forEach items="${firstMenu}" var="recSite">
                <c:choose>
                    <c:when test="${recomSite.categoryId eq recSite.id}">
                        <a href="javascript:void(0)" id="${recSite.id}" class="btn btn-default recommendLocation btn-success">${recSite.name}</a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:void(0)" id="${recSite.id}" class="btn btn-default recommendLocation">${recSite.name}</a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <div class="recommendationSection">
            <label for="">推荐学段</label>
            <div id="gradeList">
                <a href="javascript:void(0)" id="all" class="btn btn-default">全部</a>
                <a href="javascript:void(0)" id="1" class="btn btn-default">一年级</a>
                <a href="javascript:void(0)" id="2" class="btn btn-default">二年级</a>
                <a href="javascript:void(0)" id="3" class="btn btn-default">三年级</a>
                <a href="javascript:void(0)" id="4" class="btn btn-default">四年级</a>
                <a href="javascript:void(0)" id="5" class="btn btn-default">五年级</a>
                <a href="javascript:void(0)" id="6" class="btn btn-default">六年级</a>
                <a href="javascript:void(0)" id="7" class="btn btn-default">初一</a>
                <a href="javascript:void(0)" id="8" class="btn btn-default">初二</a>
                <a href="javascript:void(0)" id="9" class="btn btn-default">初三</a>
                <a href="javascript:void(0)" id="10" class="btn btn-default">高一</a>
                <a href="javascript:void(0)" id="11" class="btn btn-default">高二</a>
                <a href="javascript:void(0)" id="12" class="btn btn-default">高三</a>
            </div>
        </div>
        <div>
            <label for="">推荐顺序</label>
            <input type="text" id="sort" value="${searchAndResult.sort}" class="orderRecommend">
        </div>
        <div class="uploadRecommend">
            <button class="btn btn-success" onclick="saveRecommond('${searchAndResult.appId}');">提交推荐</button>
        </div>
    </div>
</div>
<form method="post" id="myForm">

</form>
<c:forEach items="${gardeIdList}" var="garde">
    <input type="hidden" class="gradeId" value="${garde.gradeId}"/>
</c:forEach>

<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script>

    //请求状态
    var isLoading = false;
    //        复写数据到页面
    var gradeIdArray = [];
    var grade = $(".gradeId");
    var allChildren = $('#gradeList').children('a');
    if (grade.length > 0) {
        if (grade.length == 12) {
            //如果全部有active则删除全部的选中，否则全部选中
            for (var i = 0; i < allChildren.length; i++) {
                allChildren.eq(i).addClass('active');
            }
        } else {
            for (var i = 1; i <= grade.length; i++) {
               /* allChildren.eq(i).addClass('active');*/
                allChildren.each(function(){
                    if($(grade[0]).val()==$(this).attr("id")){
                        $(this).addClass('active');
                    }
                });
            }
        }
    }
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/shelvesCoursesAjax.js"></script>
<script>
    //    推荐学段复选
    $('#gradeList').children('a').click(function () {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active');
            $('#gradeList').children('a').eq(0).removeClass('active');
        } else {
            $(this).addClass('active');
        }
    });
    //    点击全部，则全部选中
    $('#gradeList').children('a').eq(0).click(function () {

        var allChildren = $('#gradeList').children('a');

        //如果全部有active则删除全部的选中，否则全部选中
        if ($(this).hasClass('active')) {
            for (var i = 1; i < allChildren.length; i++) {
                allChildren.eq(i).addClass('active');
            }
        } else {
            for (var i = 1; i < allChildren.length; i++) {
                allChildren.eq(i).removeClass('active');
            }
        }
    });
    $(".recommendLocation").click(function(){
        if($(this).hasClass("btn-success")){
            $(this).removeClass("btn-success");
        }else{
            $(".recommendLocation").removeClass("btn-success");
            $(this).addClass("btn-success");
        }

    });

    function saveRecommond(appId) {
        var ids = "";
        var appId = appId;
        $("#gradeList").find("a:not(:first-child)").each(function () {
            if ($(this).hasClass("active")) {
                ids += $(this).attr("id") + ",";
            }
        });
        var categoryId = '';
        $(".recommendLocation").each(function(){
            if($(this).hasClass("btn-success")){
                categoryId = $(this).attr("id");
            }
        });
        if(!categoryId){
            alert("请选择推荐位置");
            return;
        }
        if (ids == "") {
            alert("请选择推荐学段");
            return;
        }
        var sort = $("#sort").val();
        if (sort) {
            //判断是否填写数字
            var reg = new RegExp("^[0-9]*$");
            if (!reg.test(sort)) {
                alert("请输入数字!");
                return;
            }
        }
        //请求状态为请求中则返回
        if(isLoading){
            alert("网络繁忙，请稍等！");
            return;
        }
        //改变请求状态
        isLoading = true;
        $.ajax({
            url: rootPath + "/appNewClasses/insertRcommondInfo",
            type: "post",
            data: {"ids": ids, "sort": sort, "appId": appId,"categoryId":categoryId},
            success: function (result) {
                if ("1" == result) {
                    alert("推荐成功")
                    $('.popupContainer').hide();
                    $('.popupOpacity').hide();
                } else {
                    alert("推荐失败")
                }
                //重置请求状态
                isLoading = false;
            }
        });
    }


</script>
</body>
</html>
