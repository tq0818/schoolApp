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
   <%-- <script type="text/javascript">
        $(function(){
            $selectSubMenu('special_topic');
            loadSpecialList(1);
        });
        function loadSpecialList(pageNum){
            var url = "<%=rootPath%>/appNewClasses/recommendSpecialList";
            var data = {"pageNum":pageNum,"pageSize":12}
            $('#specialList').load(url,data)
        }


    </script>--%>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>

<div class="u-wrap set-system">
    <div class="Y_background">
        <div class="Y_head Y_clear">
            <h2 class="h5 fl">首页推荐专题列表</h2>
            <span class="line"></span>
            <%--<span class="rb fr">--%>
                    <%--<a href="<%=rootPath %>/commodity/toAddSpecialPage"  class="btn btn-mini btn-primary"><em class="iconfont">&#xe606;</em>新增专题</a>--%>
            <%--</span>--%>
        </div>
        <div>
            <table class="table table-center">
                <tbody>
                <tr>
                    <th>序号</th>
                    <th>专题名称</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${specialList}" var="special" >
                    <tr>
                            <%-- <c:if test="${special.originType  eq 1 }">--%>
                        <td>${special.sout }</td>
                        <td>${special.name}</td>
                        <td>
                            <a href="<%=rootPath %>/appNewClasses/pageRecommendation?modelId=${special.id}_${special.itemOneCode}" class="btn btn-mini btn-primary eidtRecommondList">编辑</a>
                        </td>
                    </tr>
                    <%-- </c:if>--%>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="specialList">

        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->


<%--<script>
    $('.eidtRecommondList').click(function(){
        ///appNewClasses/pageRecommendation
        var modelId = $(this).attr('data-id');
        var modelName = encodeURI($(this).attr('data-name'));
        $.ajax({
            type : 'get',
            url : '/appNewClasses/pageRecommendation',
            data : {
                modelId : modelId,
                modelName : modelName
            },
            success : function(data){
                console.log(data);
            }
        });
    });
</script>--%>


<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('getModelList');
    });
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/system/newsManage.js"></script>
</body>
</html>