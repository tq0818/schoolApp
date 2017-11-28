<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>提问</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link href="<%=rootPath %>/plugins/editor/froala_editor.min.css" rel="stylesheet" type="text/css">
    <link href="<%=rootPath %>/plugins/editor/froala_page.min.css" rel="stylesheet" type="text/css">
    <script src="<%=rootPath %>/plugins/editor/froala_editor.min.js"></script>


</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding putQuestionLeft">
        <div class="heading">
            <h2 class="h5">发布问题</h2>
            <span class="line"></span>
        </div>
        <div class="findQuestion">
            <div>
                <span>标题:</span>
                <input type="text" placeholder="请输入问题标题" class="inputQuestion">
            </div>
            <div class="checkBoxBtn">
                <span>系统标签:</span>
                <button class="btn btn-default">美丽</button>
                <button class="btn btn-default">美丽</button>
                <button class="btn btn-default">美丽</button>
            </div>
            <div class="checkBoxBtn">
                <span>自定义标签:</span>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <button class="btn btn-default">语文</button>
                <input type="text" class="customTag">
            </div>
            <div class="contentBox">
                <span>内容:</span>
                <section id="editor">
                    <div id='edit' style="margin-top: 30px;">

                    </div>
                </section>
            </div>
            <div>
                <span>积分打赏:</span>
                <input type="text" class="PointsReward" placeholder="积分打赏必填，不能超过系统预设积分值">
            </div>
            <div class="putQuestion">
                <button class="btn btn-success">提问</button>
            </div>
        </div>
    </div>
    <div class="mainbackground nopadding putQuestionRight">
        <div class="heading">
            <h2 class="h5">一周热门问题</h2>
            <span class="line"></span>
        </div>
        <div class="questionList">
            <ul>
                <li><a href="##">不过写作业怎么办？</a></li>
                <li><a href="##">4年级该学什么课？</a></li>
                <li><a href="##">1+1=？</a></li>
                <li><a href="##">如何修改个人资料？</a></li>
                <li><a href="##">9+9+9+9？</a></li>
            </ul>
        </div>
        <div class="forbidQuestion">
            <h4>问答禁止这些提问</h4>
            <ul>
                <li>1.禁止发布求职、交易、推广、广告类与问答无关信息将一律清理。</li>
                <li>2.尽可能详细描述您的问题，如标题与内容不符或与问答无关的信息将被关闭。</li>
                <li>3.问答截屏用户一律冻结账号。</li>
            </ul>
        </div>

    </div>
</div>

<script>
//    标签选择
    $('.checkBoxBtn').find('button').click(function () {
        if($(this).hasClass('btn-primary')){
            $(this).addClass('btn-default');
            $(this).removeClass('btn-primary');
        }else{
            $(this).addClass('btn-primary');
            $(this).removeClass('btn-default');
        }
    });



</script>
<script>
//    编辑器
    $(function(){
        $('#edit').editable({inlineMode: false, alwaysBlank: true})
    });
</script>
</body>
</html>