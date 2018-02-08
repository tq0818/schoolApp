<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>学校风采</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mine.css">

</head>
<body>
<input type="hidden" id="pageSize" value='${result.pageSize}'/>
<input type="hidden" id="pageNo" value='${result.pageNo}'/>
<input type="hidden" id="rowCount" value='${result.rowCount}'/>
<input type="hidden" id="riseSchoolId" value='${riseSchoolId}'/>
<jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitre.jsp"/>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitreLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">学校风采</h2>
                <span class="line"></span>
            </div>
            <div class="mienContent">
                <%--<div class="addImg">--%>
                    <%--<i class="icon iconfont">&#xe606;</i>--%>
                <%--</div>--%>
                <div class="imgList">
                    <ul >
                        <li class="addImg">
                            <i class="icon iconfont">&#xe606;</i>
                        </li>
                        <li>
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                            <a href="##" class="btn btn-primary btn-sm rightShow">封面图片</a>
                            <div class="listBg">
                                <a href="##" class="btn btn-warning btn-sm coverChange">修改</a>
                                <a href="##" class="btn btn-success btn-sm coverAdd">添加</a>
                            </div>
                        </li>
                        <li>
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                            <a href="##" class="btn btn-primary btn-sm rightShow">已置顶</a>
                            <div class="listBg">
                                <a href="##" class="btn btn-warning btn-sm imgTop">置顶</a>
                                <a href="##" class="btn btn-success btn-sm imgAdd">删除</a>
                                <a href="##" class="btn btn-success btn-sm imgChange">修改</a>
                            </div>
                        </li>
                        <li class="noMargin">
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                        </li>
                        <li>
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                        </li>
                        <li>
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                        </li>
                        <li>
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                        </li>
                        <li class="noMargin">
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>


<script src="<%=rootPath %>/javascripts/riseschool/mien.js"></script>
<script>
//    左侧active切换
    $selectSubMenus('mien');
</script>

</body>
</html>
