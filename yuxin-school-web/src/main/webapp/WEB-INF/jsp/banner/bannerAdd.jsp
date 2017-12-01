
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>首页banner图设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
</head>
<body style="position:relative;">
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<%--已上架课程列表--%>
<div id="modelList" class="pageRecommendtionBg">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5" style="display: inline-block;margin: 10px 0;">banner添加</h2>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <div class="findQuestion">
                <div>
                    <span>banner图片:</span>
                    <img src="/images/1.jpg" alt="" class="bannerImg">
                    <div><a href="##" class="changeBanner"><input type="file">更换图片</a></div>
                </div>
                <div class="checkBoxBtn">
                    <span>名称:</span>
                    <input type="text" class="bannerInput">
                </div>
                <div class="checkBoxBtn">
                    <span>描述:</span>
                    <input type="text" class="bannerInput">
                </div>
                <div class="contentBox">
                    <span>内容:</span>
                    <div id="ckecktor">
                        <textarea id="newsContents" class="msg-content"></textarea>
                    </div>
                </div>

                <div class="putQuestion bannerBtnGroup">
                    <button  type="button" class="btn btn-default"  >预览</button>
                    <button  type="button" class="btn btn-success"  >保存</button>
                    <button  type="button" class="btn btn-danger"  >取消</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('comBannerIndex');
    });
</script>
</body>
</html>
