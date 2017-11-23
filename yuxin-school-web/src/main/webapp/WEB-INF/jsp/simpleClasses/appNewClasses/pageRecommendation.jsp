
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>首页推荐列表</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
<%--已上架课程分类筛选--%>
<div class="u-wrap classes">
    <div class="mainbackground nopadding">
        <div class="classes-type">
            <p class="c">
                <span class="t-title">课程分类</span>
                <span class="t-content" id="courseCaId">
                     <a href="javascript:Form.showAllShelvesClssType('${modelId}','courseCaId');" data-code="${modelId}" class="btn btn-mini btn-default  btn-success">${modelName}</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">学段</span>
                <span class="t-content" id="gradeId">
                     <a href="javascript:Form.showAllShelvesClssType('all','gradeId');"  data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <c:forEach items="${grades}" var="grade">
                           <a href="javascript:Form.showAllShelvesClssType('${grade.id}','gradeId');" data-code="${grade.id}" class="btn btn-mini btn-default">${grade.name}</a>
                    </c:forEach>
                </span>
            </p>
            <p class="c">
                <span class="t-title">学科</span>
                <span class="t-content" id="subjectId">
                    <a href="javascript:Form.showAllShelvesClssType('all','subjectId');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">知识点专题</span>
                <span class="t-content" id="kwonProId">
                    <a href="javascript:Form.showAllShelvesClssType('all','kwonProId');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">知识点</span>
                <span class="t-content" id="knowId">
                	<a href="javascript:Form.showAllShelvesClssType('all','knowId');" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">阶段</span>
                <span class="t-content" id="stageId">
                	<a href="Form.showAllShelvesClssType('all','stageId');" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <c:forEach items="${stages}" var="stage">
                        <a href="javascript:Form.showAllShelvesClssType('${stage.id}','typeId');" data-code="${stage.id}" class="btn btn-mini btn-default">${stage.name}</a>
                    </c:forEach>
                </span>
            </p>
            <p class="c">
                <span class="t-title">类型</span>
                <span class="t-content" id="typeId">
                	<a href="javascript:Form.showAllShelvesClssType('all','typeId');" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <c:forEach items="${types}" var="type">
                        <a href="javascript:Form.showAllShelvesClssType('${type.id}','typeId');" data-code="${type.id}" class="btn btn-mini btn-default">${type.name}</a>
                    </c:forEach>
                </span>
            </p>
        </div>
    </div>
</div>

<%--已上架课程列表--%>

<div id="modelList">
<div class="mainbackground nopadding">
    <div class="heading">
        <h2 class="h5" style="display: inline-block;">已上架课程</h2>
        <div style="margin-top: 10px;text-align:right;padding:0 10px;display: inline-block;margin-left: 75%;">
            <span><a href="javascript:;" class="btn btn-primary signUpMany" id="batchRecommendation">批量推荐</a></span>
        </div>
        <span class="line"></span>
    </div>
    <div class="user-list">
        <table class="table table-center" id="tableList">
            <tbody>
            <tr>
                <th width="1%"><input type="checkbox" class="checkboxAll"></th>
                <th width="5%">课程图片</th>
                <th width="5%">课程名称</th>
                <th width="10%">学段</th>
                <th width="5%">学科</th>
                <th width="10%">知识点专题</th>
                <th width="10%">知识点</th>
                <th width="5%">阶段</th>
                <th width="6%">类型</th>
                <th width="7%">上架时间</th>
                <th width="8%">直播时间</th>
                <th width="3%">学习人数</th>
                <th width="3%">价格</th>
                <th width="3%">实际价格</th>
                <th width="20%">操作</th>
            </tr>
        </table>

        <div class="pages pagination"></div>
    </div><input type="hidden" id="rowCount" value="68266"><input type="hidden" id="pageNo" value="1"><input type="hidden" id="maxCount" value="999999999">
</div>
<%--弹出框--%>
<div class="popupContainerRecommendation">
    <span class="closePopupContainer">x</span>
    <div class="toRecommon">
        <div id="toRecommon">
            <div class="recommendationSection recommendBatchRecommendation">
                <label for="">推荐学段</label>
                <div id="gradeList">
                    <a href="javascript:void(0)" id="all" class="btn btn-default">全部</a>
                    <c:forEach items="${secondItem }" var="second" varStatus="status">
                           <a href="javascript:void(0);" id="${second.itemCode}" class="btn btn-default">${second.itemName }</a>
                   </c:forEach>
                </div>
                <div class="sureBatchRecommendation">
                    <button class="btn btn-success ">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="popupOpacity"></div>

</div>
<input type="hidden" id="selectCounts" value="10">
<script type="text/javascript">
    $(document).ready(function(){
        $.ajax({
            url : rootPath + "/companyServiceStatic/queryCompanyNoServices",
            type : "post",
            dataType : 'json',
            success : function(jsonData) {
                var count=jsonData.length;
                var html="";
                if(count==1){
                    $.each(jsonData,function(i,item){
                        if(item.groupCode=='SERVICE_LIVE'){
                            html+='<ul class="tabsn c4"><li class="b2">录播</li>'+
                                '<li class="b3">面授</li>'+
                                '<li class="b4">混合</li>'+
                                '<li class="b5">其他</li></ul>';
                        }else if(item.groupCode=='SERVICE_VIDEO'){
                            html+='<ul class="tabsn c4"><li class="b1">直播</li>'+
                                '<li class="b3">面授</li>'+
                                '<li class="b4">混合</li>'+
                                '<li class="b5">其他</li></ul>';
                        }else if(item.groupCode=='SERVICE_FACE'){
                            html+='<ul class="tabsn c4"><li class="b1">直播</li>'+
                                '<li class="b2">录播</li>'+
                                '<li class="b4">混合</li>'+
                                '<li class="b5">其他</li></ul>';
                        }
                    });
                }else if(count==2){
                    var num1,num2;
                    $.each(jsonData,function(i,item){
                        if(i==0){
                            num1=item.groupCode;
                        }else{
                            num2=item.groupCode;
                        }
                    })
                    if((num1=="SERVICE_LIVE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_LIVE")){
                        html+='<ul class="tabsn c2"><li class="b3">面授</li>'+
                            '<li class="b5">其他</li></ul>';
                    }
                    if((num1=="SERVICE_LIVE"&&num2=="SERVICE_FACE")||(num1=="SERVICE_FACE"&&num2=="SERVICE_LIVE")){
                        html+='<ul class="tabsn c2"><li class="b2">录播</li>'+
                            '<li class="b5">其他</li></ul>';
                    }
                    if((num1=="SERVICE_FACE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_FACE")){
                        html+='<ul class="tabsn c2"><li class="b1">直播</li>'+
                            '<li class="b5">其他</li></ul>';
                    }
                }else if(count==3){
                    html+='<ul class="tabsn c8">'+
                        '<li class="b5">其他</li></ul>';
                }else{
                    html+='<ul class="tabsn"><li class="b1">直播</li>'+
                        '<li class="b2">录播</li>'+
                        '<li class="b3">面授</li>'+
                        '<li class="b4">混合</li>'+
                        '<li class="b5">其他</li></ul>';
                }
                $("#lsOne").append(html);
            }
        });
        $("body").css("position","relative")
    });
    $('#batchRecommendation').click(function () {
        $('.popupContainerRecommendation').show();
        $('.popupOpacity').show();
    });
    $('.closePopupContainer').click(function () {
        $('.popupContainerRecommendation').hide();
    });
    //点击确定推荐学段，隐藏弹窗
    $('.sureBatchRecommendation').children('button').click(function () {
        $('.popupContainerRecommendation').hide();
        $('.popupOpacity').hide();
    });
    //    推荐学段复选
    $('#gradeList').children('a').click(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            $('#gradeList').children('a').eq(0).removeClass('active');
        }else {
            $(this).addClass('active');
        }
    });
    //    点击全部，则全部选中
    $('#gradeList').children('a').eq(0).click(function(){

        var allChildren = $('#gradeList').children('a');

        //如果全部有active则删除全部的选中，否则全部选中
        if($(this).hasClass('active')){
            for(var i=1; i<allChildren.length;i++){
                allChildren.eq(i).addClass('active');
            }
        }else{
            for(var i=1; i<allChildren.length;i++){
                allChildren.eq(i).removeClass('active');
            }
        }
    });

    function saveRecommond(){
        var ids = "";
        var appId="1";
        $("#gradeList").find("a").each(function(){
            if($(this).hasClass("active")){
                ids+=$(this).attr("id")+",";
            }
        });
        if(""==ids){
            alert("请选择推荐学段");
            return;
        }
        var sort=$("#sort").val();
        if(sort){
            //判断是否填写数字
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(sort)){
                alert("请输入数字!");
            }
            return;
        }
        $.ajax({
            url : rootPath +"/appNewClasses/insertRcommondInfo",
            type : "post",
            data : {"ids":ids,"sort":sort,"appId":appId},
            success : function(result) {
                if("1"==result){
                    alert("推荐成功")
                    $('.popupContainer').hide();
                    $('.popupOpacity').hide();
                }else{
                    alert("推荐失败")
                }
            }
        });
    }
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/app/shelvesCourses.js"></script>
<%--<script type="text/javascript" src="<%=rootPath %>/javascripts/class/classIndex.js"></script>--%>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>
