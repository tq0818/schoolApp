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
                <h2 class="h5">学校风采</h2>
                <span class="line"></span>
            </div>
            <div class="mienContent">
                <%--<div class="addImg">--%>
                    <%--<i class="icon iconfont">&#xe606;</i>--%>
                <%--</div>--%>
                <div class="imgList">
                    <ul >
                        <li class="addImg mienShow">
                            <i class="icon iconfont">&#xe606;</i>
                        </li>
                        <li class="coverImg">
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                            <a href="##" class="btn btn-primary btn-sm rightShow">封面图片</a>
                            <div class="listBg">
                                <a href="##" class="btn btn-warning btn-sm coverChange mienShow">修改</a>
                                <a href="##" class="btn btn-success btn-sm coverAdd mienShow">添加</a>
                            </div>
                        </li>
                        <li>
                            <img src="<%=rootPath %>/images/1.jpg" alt="">
                            <span class="imgInfo">是那只孤飞的是那只孤飞的是那只孤飞的</span>
                            <a href="##" class="btn btn-primary btn-sm rightShow">已置顶</a>
                            <div class="listBg">
                                <a href="##" class="btn btn-warning btn-sm imgTop">取消置顶</a>
                                <a href="##" class="btn btn-success btn-sm imgDelete">删除</a>
                                <a href="##" class="btn btn-success btn-sm imgChange mienShow">修改</a>
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

        <div class="pages">
            <ul class="pagination">

            </ul>
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

<%--新增，修改图片弹窗--%>
<div class="opacityPopup"></div>
<div class="mienPopup">
    <div class="uploadImage">
        <label for="">风采图片：</label>
        <img src="<%=rootPath %>/images/1.jpg" alt="">
        <a href="##" class="btn btn-mb btn-success">上传图片</a>
    </div>
    <div class="imgDescripe">
        <label for="">图片描述：</label>
        <textarea name="" placeholder="请输入图片描述(最多60个字)"  maxlength="60"></textarea>
    </div>
    <div class="mienBtn">
        <a href="##" class="btn btn-sm btn-danger mienHide">取消</a>
        <a href="##" class="btn btn-sm btn-success mienHide">保存</a>
    </div>
</div>

<script src="<%=rootPath %>/javascripts/riseschool/mien.js"></script>
<script>
    //分页
     $(".pagination").pagination('',
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
    );
//    左侧active切换
    $selectSubMenus('mien');
</script>

</body>
</html>
