
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>编辑上架课程</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />

    <link href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>


    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
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
                        <span>XXXXXXXX</span>
                    </li>
                    <li>
                        <label>课程名称:</label>
                        <span>XXXXXXXX</span>
                    </li>
                    <li>
                        <label>课程名称:</label>
                        <span>XXXXXXXX</span>
                    </li>
                </ul>
                <a href="##" class="courseDetail btn btn-default">课程详情</a>
            </div>
        </div>
        <div>
            <ul class="classification">
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                    </ul>
                </li>
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                    </ul>
                </li>
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                    </ul>
                </li>
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                    </ul>
                </li>
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                    </ul>
                </li>
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                    </ul>
                </li>
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                        <li>
                            <input type="button" value="语文">
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div>
            <ul class="screeningConditions">
                <li>
                    <label>价格：</label><input type="text">
                </li>
                <li>
                    <label>价格：</label><input type="text">
                </li>
                <li>
                    <label>价格：</label><input type="text">
                </li>
                <li>
                    <label>价格：</label><input type="text">
                </li>
            </ul>
        </div>
        <div class="submitCourse">
            <button class="btn btn-success">立即上架</button>
            <button class="btn btn-warning">预约上架</button>
            <input type="text" placeholder="指定上架时间" id="choiceTimeUpload">
        </div>
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
        $('#choiceTimeUpload').datetimepicker();
    });
</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/class/classIndex.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>
