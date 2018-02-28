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
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">

    <style>
        .gobal-progress{display: none !important;} 
        /*.uploadImage .jcrop-holder{margin-top: 0!important;width: 400px !important;height: 300px !important; }*/
        /*.uploadImage img,.jcrop-tracke,.uploadImageStyle img,.jcrop-holder{width: 300px !important;height: 300px!important;margin-top: 0!important;}*/
    </style>
</head>
<body>
<%--<input type="hidden" id="pageSize" value='${pageSize}'/>--%>
<input type="hidden" id="pageNo" value='${pageNo}'/>
<input type="hidden" id="rowCount" value='${rowCount}'/>
<input type="hidden" id="riseSchoolId" value='${schoolId}'/>
<input type="hidden" id="windowFlag" value=""/>
<input type="hidden" id="updateId" value=""/>
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
                        <li class="addImg mienShow" id="">
                            <i class="icon iconfont">&#xe606;</i>
                        </li>
                        <li class="coverImg" id="${coverVo.id}" >
                            <img src="${coverVo.imgUrl}" alt="" style="width: 100%;height: auto">
                            <a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">封面图片</a>
                            <div class="listBg">
                                <c:choose>
                                    <c:when test="${coverVo.imgUrl == null or coverVo.imgUrl == ''}">
                                        <a href="javascript:void(0)" class="btn btn-success btn-sm coverAdd coverShow">添加</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="javascript:void(0)" class="btn btn-warning btn-sm coverChange coverShow" data-value="${coverVo.id}" imgType="${coverVo.imgType}">修改</a>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </li>
                        <c:forEach var="schoolStyle" items="${result}" varStatus="status">
                            <c:choose>
                                <c:when test="${status.index + 1 == 2 or status.index + 1 == 6}">
                                    <li id="${schoolStyle.id}" class="noMargin">
                                </c:when>
                                <c:otherwise>
                                    <li id="${schoolStyle.id}">
                                </c:otherwise>
                            </c:choose>
                            <img src="${schoolStyle.imgUrl}" alt=""
                            <c:choose  >
                                <c:when test="${schoolStyle.imgType eq '2'}">
                                    style="width: 100%;height: auto"
                                </c:when>
                                <c:otherwise>
                                    style="width: auto;height: 100%"
                                </c:otherwise>
                            </c:choose>
                            >
                            <span class="imgInfo">${schoolStyle.imgDiscrible}</span>
                            <c:if test="${schoolStyle.isTop == 1}"><a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">已置顶</a></c:if>
                            <c:if test="${schoolStyle.isTop == 0}"><a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">未置顶</a></c:if>
                            <div class="listBg">
                                <c:if test="${schoolStyle.isTop == 1}"><a href="javaScript:updateIsTop('${schoolStyle.id}',0)" class="btn btn-warning btn-sm imgTop">取消置顶</a></c:if>
                                <c:if test="${schoolStyle.isTop == 0}"><a href="javaScript:updateIsTop('${schoolStyle.id}',1)" class="btn btn-warning btn-sm imgTop">置顶</a></c:if>
                                <a href="javaScript:deleteRiseSchoolStyle('${schoolStyle.id}')" class="btn btn-success btn-sm imgDelete">删除</a>
                                <a href="javascript:void(0)" class="btn btn-success btn-sm imgChange mienShow" data-value="${schoolStyle.id}" imgType="${schoolStyle.imgType}">修改</a>
                            </div>
                            </li>
                        </c:forEach>

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
<input type="hidden" id="x" name="x" value="0"/>
<input type="hidden" id="y" name="y" value="0"/>
<input type="hidden" id="w" name="w" value="0"/>
<input type="hidden" id="h" name="h" value="0"/>
<input type="hidden" id="x2" name="x2" value="0"/>
<input type="hidden" id="y2" name="y2" value="0"/>
<%--新增，修改图片弹窗--%>
<div class="opacityPopup"></div>
<div class="mienPopup commonPopup">
    <div class="uploadImage">
        <label for="">风采图片：</label>

        <img src="" alt="" id="target" style="width: 300px;height: 300px;">
        <%--<a href="javascript:void(0)" class="btn btn-mb btn-success">上传图片</a>--%>
        <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgData" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(1)" value="重新选择文件"/>
    </div>
    <div id="styleBtn">
        <a href="javascript:void(0)" class="btn btn-sm btn-primary" id="btnOne" style="display: none">样式一</a>
        <a href="javascript:void(0)" class="btn btn-sm btn-default" id="btnTwo" style="display: none">样式二</a>
    </div>
    <div class="imgDescripe">
        <label for="">图片描述：</label>
        <textarea  id="imgDiscrible" name="" placeholder="请输入图片描述(最多60个字)"  maxlength="60"></textarea>
    </div>
    <div class="mienBtn">
        <a href="javascript:void(0)" class="btn btn-sm btn-danger mienHide1">取消</a>
        <a href="javascript:void(0)" class="btn btn-sm btn-success mienHide" onclick="saveCutPic(1)">保存</a>
    </div>
</div>

<%--封面弹窗--%>
<div class="mienPopup coverPopup">
    <div class="uploadImageStyle">
        <label for="">封面图片：</label>
        <img src="" alt="" id="targetStyle" style="width: 400px;height: 300px;">
        <%--<a href="javascript:void(0)" class="btn btn-mb btn-success">上传图片</a>--%>
        <input type="file" class="btn btn-mini btn-primary" name="imgData" id="imgDataStyle" accept=".jpg,.jpeg,.gif,.png,.bmp,.ico" onchange="savePic(2)" value="重新选择文件"/>
    </div>
    <div class="mienBtn">
        <a href="javascript:void(0)" class="btn btn-sm btn-danger mienHide1">取消</a>
        <a href="javascript:void(0)" class="btn btn-sm btn-success mienHide" onclick="saveCutPic(2)">保存</a>
    </div>
</div>

<script src="<%=rootPath %>/javascripts/riseschool/mien.js"></script>
<script src="<%=rootPath %>/javascripts/riseschool/cutPic.js"></script>
<script src="<%=rootPath %>/javascripts/riseschool/ajaxfileuploadR.js"></script>
<script src="<%=rootPath %>/plugins/jcrop/js/jquery.Jcrop.js"></script>
<script src="<%=rootPath %>/javascripts/plus/jquery.units.js"></script>
<script src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/riseschool/jm-qi.js"></script>

<script>
    //分页
     $(".pagination").pagination('${rowCount}',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page:'${pageNo - 1}',
            link_to: "javascript:void(0)",
            num_display_entries: 6,
            items_per_page: 6,
            num_edge_entries: 1,
            callback: function (page, jq) {
                var pageNo = page + 1;
                queryRiseSchoolStyle(pageNo);
            }
        }
    );
//    左侧active切换
    $selectSubMenus('mien');
</script>

</body>
</html>
