<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<html>
<head>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath%>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <script src="<%=rootPath%>/plugins/jcrop/js/jquery.Jcrop.js"></script>
    <script src="<%=rootPath%>/javascripts/riseschool/cutPic.js"></script>
    <title>
    </title>
</head>
<body>
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
                        <a href="javascript:void(0)" class="btn btn-warning btn-sm coverChange coverShow" data-value="${coverVo.id}">修改</a>
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
</body>
</html>

<script>
    $(function () {
        //弹出弹窗
        $('.mienShow').click(function () {
            $('.opacityPopup').fadeIn();
            $('.commonPopup').fadeIn();
            //标记不同的弹窗，为一个标志赋值表示不同的操作
            var windowFlag = '';
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
            $("#imgData").removeAttr("type").attr("type","file");
            if ($(this).hasClass('addImg')){
                $(".uploadImage").find("img").attr("src","").attr("style","width: 300px;height: 300px;");
                $("#imgDiscrible").val('');
                windowFlag = '1';
            }else if ($(this).hasClass('imgChange')){
//                jcrop_apis.destroy();
                $(".uploadImage").find("img").attr("src",$(this).parent(".listBg").siblings("img").attr("src")).attr("style","");
                //横图
                if($(this).attr("imgType")=="2"){
                    $(".uploadImage").find("img").attr("style","width: 300px;height: auto;");
                }else{
                    $(".uploadImage").find("img").attr("style","width: auto;height: 300px;");
                }
                $("#imgDiscrible").val($(this).parent(".listBg").siblings("span").text());
                windowFlag = '2';
                var updateId = $(this).attr("data-value");
                $("#updateId").val(updateId);
            }
            $("#windowFlag").val(windowFlag);
        });
        //封面hover效果
        $('.imgList li').mouseover(function () {
            $(this).children('.imgInfo').show();
            $(this).children('.listBg').show();
        });
        $('.imgList li').mouseleave(function () {
            $(this).children('.imgInfo').hide();
            $(this).children('.listBg').hide();
            return false;
        });

        //封面图片弹窗
        $('.coverShow').click(function () {
            $('.opacityPopup').fadeIn();
            $('.coverPopup').fadeIn();
            var windowFlag = '';
            if (jcrop_apis){
                jcrop_apis.destroy();
            }
            //清除上一次图片的名字
            $("#imgDataStyle").removeAttr("type").attr("type","file");
            //封面图标记窗口
            if($(this).hasClass('coverChange')){
//                $(".jcrop-holder").attr("style","display:none");
                $(".uploadImageStyle").find("img").attr("src",$(".coverImg").find("img").attr("src")).attr("style","").attr("style","width: 400px;height: auto;");
                windowFlag = '4';
                var updateId = $(this).attr("data-value");
                $("#updateId").val(updateId);
            }else if($(this).hasClass('coverAdd')){
                $(".uploadImageStyle").find("img").attr("src","").attr("style","").attr("style","width: 400px;height: 300px;");
                windowFlag = '3';
            }
            $("#windowFlag").val(windowFlag);
        });
    });
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
</script>
