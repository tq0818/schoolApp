
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
                <span class="t-content" id="itemOneCodeList">
                     <a href="javascript:Form.showAllShelvesClssType('${menu.id}','courseCaId');" data-code="MIN_UP" class="btn btn-mini btn-default  btn-success">${modelName}</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">学段</span>
                <span class="t-content" id="itemSecondCodeList">
                     <a href="javascript:Form.showAllShelvesClssType('all','gradeId');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <c:forEach items="${grades}" var="grade">

                           <a href="javascript:Form.showAllShelvesClssType('${grade.id}','gradeId');" data-code="MIN_UP" class="btn btn-mini btn-default">${grade.name}</a>
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
                        <a href="javascript:Form.showAllShelvesClssType('${stage.id}','typeId');" data-code="MIN_UP" class="btn btn-mini btn-default">${stage.name}</a>
                    </c:forEach>
                </span>
            </p>
            <p class="c">
                <span class="t-title">类型</span>
                <span class="t-content" id="typeId">
                	<a href="javascript:Form.showAllShelvesClssType('all','typeId');" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <c:forEach items="${types}" var="type">
                        <a href="javascript:Form.showAllShelvesClssType('${type.id}','typeId');" data-code="MIN_UP" class="btn btn-mini btn-default">${type.name}</a>
                    </c:forEach>
                </span>
            </p>
        </div>
    </div>
</div>

<%--已上架课程列表--%>

<div id="modelList">

</div>
<%--<div class="mainbackground nopadding">
    <div class="heading">
        <h2 class="h5" style="display: inline-block;">已上架课程</h2>
        <div style="margin-top: 10px;text-align:right;padding:0 10px;display: inline-block;margin-left: 75%;">
            <span><a href="javascript:;" class="btn btn-primary signUpMany">批量推荐</a></span>
        </div>
        <span class="line"></span>
    </div>

    <div class="u-wrap classes">
        <div class="mainbackground nopadding">

        </div>



    </div><input type="hidden" id="rowCount" value="68266">
    <input type="hidden" id="pageNo" value="1">
    <input type="hidden" id="maxCount" value="999999999">
</div>--%>

  <%--  <div class="user-list">
        <table class="table table-center" id="tableList">
            <tbody>
            <tr data-buy="true">
                <th width="1%"><input type="checkbox" class="checkboxAll"></th>
                <th width="5%">课程图片</th>
                <th width="5%">课程名称</th>
                <th width="10%">学段</th>
                <th width="5%">学科</th>
                <th width="10%">知识点专题</th>
                <th width="10%">知识点</th>
                <th width="5%">阶段</th>
                <th width="8%">类型</th>
                <th width="6%">上架时间</th>
                <th width="6%">直播时间</th>
                <th width="5%">学习人数</th>
                <th width="5%">价格</th>
                <th width="5%">实际价格</th>
                <th width="15%">推荐顺序</th>
            </tr>
            <tr data-buy="false">
                <td><input type="checkbox" class="signUpMany" uname="sdsdsd" value=""></td>
                <td><img src="/images/1.jpg" alt="" class="shelvesIcon"></td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>2017-11-14</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>1</td>
                <td>
                    <input type="text" class="recommendationNum">
                    <button class="btn btn-success btn-sm">确定</button>
                    <button class="btn btn-danger btn-sm">取消</button>
                </td>
            </tr>
            </tbody>
        </table>

        <div class="pages pagination"></div>
    </div><input type="hidden" id="rowCount" value="68266"><input type="hidden" id="pageNo" value="1"><input type="hidden" id="maxCount" value="999999999">
--%>
</div>

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
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/app/pageRecommendation.js"></script>
<%--<script type="text/javascript" src="<%=rootPath %>/javascripts/class/classIndex.js"></script>--%>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>
