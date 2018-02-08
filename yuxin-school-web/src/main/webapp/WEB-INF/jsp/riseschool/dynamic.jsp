<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>动态</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/dynamic.css">
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

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
                <h2 class="h5">动态</h2>
                <a href="##" class="btn btn-primary btn-mb addNewDynamic">新增</a>
                <span class="line"></span>
            </div>
            <div class="schoolDetailsContent essentialInfo" >
                    <ul id='dataList'>

                        <li>
                        <c:forEach items="${result.data}" var="dynamic">
                            <ul class="dynamicList">
                                <li>${dynamic.tittle}</li>
                                <li class="dashedLi"><span class="dashed"></span></li>
                                <li>${dynamic.updateTime}</li>
                                <li class="dynamicBtn">
                                    <a href="##" class="btn btn-primary btn-sm check" id="${dynamic.id}">查看</a>
                                    <a href="##" class="btn btn-success btn-sm change" id="${dynamic.id}">修改</a>
                                    <a href="##" class="btn btn-danger btn-sm delete" id="${dynamic.id}">删除</a>
                                </li>
                            </ul>
                        </c:forEach>    
                        </li>

                    </ul>
            </div>
            <div class="pages">
                <ul class="pagination">

                </ul>
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
<%--点击修改--%>
<div class="opacityPopup"></div>
<div class="addNews changeNews">
    <h5>修改动态</h5>
    <div class="addNewsTitle">
        <label for="">动态标题：</label>
        <input type="text" id="changeTitle" placeholder="最多12个字" maxlength="12">
    </div>
    <div>
        <label for="">正&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp文：</label>
        <textarea name="" id="changeContent" placeholder="最多2000个字" maxlength="2000"></textarea>
    </div>
    <div class="addNewsBtn">
        <a href="##" class="btn btn-sm btn-danger addNewsBtnCancel">取消</a>
        <a href="##" class="btn btn-sm btn-success addNewsBtnUpdate">确定</a>
    </div>
</div>
<%--点击新增--%>
<div class="addNews saveNews">
    <h5>新增动态</h5>
    <div class="addNewsTitle">
        <label for="">动态标题：</label> 
        <input type="text" id="saveTitle" placeholder="最多12个字" maxlength="12">
    </div>
    <div>
        <label for="">正&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp文：</label>
        <textarea name="" id="saveContent" placeholder="最多2000个字" maxlength="2000"></textarea>
    </div>
    <div class="addNewsBtn">
        <a href="##" class="btn btn-sm btn-danger addNewsBtnCancel">取消</a>
        <a href="##" class="btn btn-sm btn-success addNewsBtnSave">确定</a>
    </div>
</div>

<%--点击查看--%>
<div class="addNews checkNews">
    <h5>查看动态</h5>
    <div class="addNewsTitle">
        <label for="">动态标题：</label>
        <span class="titleCheck"></span>
    </div>
    <div>
        <label for="">正&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp文：</label>
        <span class="contentCheck">
        </span>
    </div>
    <div class="addNewsBtn">
        <a href="##" class="btn btn-sm btn-success addNewsBtncheck">确定</a>
    </div>
</div>

<script src="<%=rootPath %>/javascripts/riseschool/dynamic.js"></script>
<script>
    //分页
    /* $(".pagination").pagination('',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page: '',
            link_to: "javascript:void(0)",
            num_display_entries: 8,
            items_per_page: 1,
            num_edge_entries: '',
            callback: function (page, jq) {
                var pageNo = page + 1;

            }
        }
    ); */
//    左侧active切换
    $selectSubMenus('dynamic');
</script>

</body>
</html>
