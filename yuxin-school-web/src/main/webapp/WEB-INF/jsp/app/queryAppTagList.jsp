<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>首页推荐专题列表</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/teacher.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>

</head>

<body>
<%--<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>--%>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap set-system">
    <div class="Y_background">
        <div class="Y_head Y_clear">
            <h2 class="h5 fl">APP标识管理</h2>
            <span class="line"></span>
        </div>
        <div>
            <table class="table table-center">
                <tbody>
                <tr>
                    <th>序号</th>
                    <th>标题</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${appTags}" var="appTag" varStatus="vs">
                    <tr>
                        <th>${vs.count}</th>
                        <th>${appTag.name}</th>
                        <th id="${appTag.id}">
                            <c:choose>
                                <c:when test="${appTag.isOpen eq 1}">已开启</c:when>
                                <c:when test="${appTag.isOpen eq 0}">已关闭</c:when>
                            </c:choose>
                        </th>
                        <th>
                            <c:choose>
                                <c:when test="${appTag.isOpen eq 1}">
                                    <a href="javaScript:changeStatus(${appTag.id});" id="switch_${appTag.id}" switchId="${appTag.id}" isOpen="${appTag.isOpen}" class="btn btn-mini btn-primary eidtRecommondList">关闭</a>
                                </c:when>
                                <c:when test="${appTag.isOpen eq 0}">
                                    <a href="javaScript:changeStatus(${appTag.id});" id="switch_${appTag.id}" switchId="${appTag.id}" isOpen="${appTag.isOpen}" class="btn btn-mini btn-primary eidtRecommondList">开启</a>
                                </c:when>
                            </c:choose>
                        </th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%--<div id="specialList">
            <table class="table table-center">
                <tbody>


                </tbody>
            </table>
        </div>--%>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->

<script>
    $(function () {
        $selectSubMenu('queryAppTagList');
    });

    function changeStatus(swithId){
        var isOpen = $("#switch_"+swithId).attr("isOpen");
        var switchId = $("#switch_"+swithId).attr("switchId");
        console.log(isOpen+"--"+switchId);
        $.ajax({
            type:"post",
            url:rootPath+"/appTagManage/changeTagSwitch",
            data:{"isOpen":isOpen,"id":switchId},
            dataType:"json",
            success:function(data){
                if(data=="OK"){
                    if(0==isOpen){
                        alert("开启成功");
                        $("#switch_"+swithId).attr("isOpen","1");
                        $("#switch_"+swithId).html("关闭");
                        $("#"+switchId).html("已开启");
                    }else{
                        alert("关闭成功");
                        $("#switch_"+swithId).attr("isOpen","0");
                        $("#switch_"+swithId).html("开启");
                        $("#"+switchId).html("已关闭");
                    }
                }else{
                    if(0==isOpen){
                        alert("开启失败");
                    }else{
                        alert("关闭失败");
                    }
                }
            }
        });
    }
</script>
</body>
</html>