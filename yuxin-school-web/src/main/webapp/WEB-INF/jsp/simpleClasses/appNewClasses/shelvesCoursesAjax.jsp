
<%--
  Created by IntelliJ IDEA.
  User: z
  Date: 2017/11/19
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--已上架课程列表--%>
<html lang="en">
<head>
 <%@include file="/decorators/import.jsp" %>
 
</head>
    <div class="heading">
        <h2 class="h5" style="display: inline-block;">已上架课程</h2>
        <div class="batchRelease">
            <span><a href="javascript:;" class="btn btn-primary signUpMany">批量下架</a></span>
        </div>
        <span class="line"></span>
    </div>
    <div class="user-list">
        <table class="table table-center">
            <tbody>
            <tr>
                <th width="1%"><input type="checkbox" class="checkboxAll"></th>
                <th width="6%">课程图片</th>
                <th width="5%">课程名称</th>
                <th width="5%">学段</th>
                <th width="5%">学科</th>
                <th width="10%">知识点专题</th>
                <th width="10%">知识点</th>
                <th width="5%">阶段</th>
                <th width="6%">类型</th>
                <th width="8%">上架时间</th>
                <th width="8%">直播时间</th>
                <th width="5%">学习人数</th>
                <th width="5%">价格</th>
                <th width="5%">实际价格</th>
                <th width="20%">操作</th>
            </tr>
            <c:forEach items="${courseList}" var="course" varStatus="status"> 
            <tr>
                <td><input type="checkbox" class="signUpMany" uname="sdsdsd" value=""></td>
                <c:choose>
                	<c:when test="${course.imgUrl eq ''}">  
                		<td><img src="${course.imgUrl}" alt="" class="shelvesIcon"></td>
                 	</c:when>
                 	<c:otherwise> 
                		<td><img src="${course.cover}" alt="" class="shelvesIcon"></td>
                	</c:otherwise>
				</c:choose>
                <td>${course.lessonName}</td>
                <td>${course.itemSecondName}</td>
                <td>${course.itemThirdName}</td>
                <td>${course.itemFourthName}</td>
                <td>${course.itemTag}</td>
                <td>${course.tagName}</td>
                <td>${course.typeCode}</td>
                <td>${course.shelvesTime}</td>
				<td>${course.lessonDate}${course.lessonTimeStart}</td>
                <td>${course.actualNum}</td>
                <c:choose>
                	<c:when test="${course.appPrice eq ''}">  
                		<td>${course.appPrice}</td>
                 	</c:when>
                 	<c:otherwise> 
                		<td>${course.originalPrice}</td>
                	</c:otherwise>
				</c:choose>
				<c:choose>
                	<c:when test="${course.appPrice eq ''}">  
                		<td>${course.salePrice}</td>
                 	</c:when>
                 	<c:otherwise> 
                		<td>${course.realPrice}</td>
                	</c:otherwise>
				</c:choose>
                <td>
                    <span><a href="javascript:;" class="btn btn-primary btn-sm">下架</a></span>
                    <span><a href="javascript: toRcommon('${course.courseCaId}','${course.liveFlag}','${course.id}');" class="btn btn-primary btn-sm recommendCourse">推荐</a></span>
                    <span><a href="javascript:toOnsaleEdit('${course.appId}_${course.id}','${course.liveFlag}');" class="btn btn-primary btn-sm editCourse">编辑</a></span>
                </td>
            </tr>
             </c:forEach> 
            </tbody>
        </table>

        <div class="pages pagination"></div>
    </div><input type="hidden" id="rowCount" value="68266"><input type="hidden" id="pageNo" value="1"><input type="hidden" id="maxCount" value="999999999">
<%--弹出框--%>
<div class="popupContainer">
    <span class="closePopupContainer">x</span>
    <div class="toRcommon">
        <div id="toRcommon">

        </div>
    </div>
</div>
<div class="popupOpacity"></div>

<%--弹出框--%>
<div class="popupContainerEdit">
    <span class="closePopupContainer">x</span>
    <div class="shelves">
        <div id="shelvesList">

        </div>
    </div>
</div>
<script>
    $('.editCourse').click(function(){
        $('.popupContainerEdit').show();
        $('.popupOpacity').show();
    });
    $('.recommendCourse').click(function(){
        $('.popupContainer').show();
        $('.popupOpacity').show();
    });

    $('.closePopupContainer').click(function(){
        $('.popupContainerEdit').hide();
    });



    function toRcommon(categerorId,zhiboFlag,commodityId){

        $.ajax({
            url :"/appNewClasses/homeRecommendation",
            type : "post",
            data:{"categerorId":categerorId,"zhiboFlag":zhiboFlag,"commodityId":commodityId},
            beforeSend:function(XMLHttpRequest){
                $(".loading").show();
                $(".loading-bg").show();
            },
            success : function(result) {
                $("#toRecommon").html(result);
            },
            complete:function(XMLHttpRequest,textStatus){
                $(".loading").hide();
                $(".loading-bg").hide();
            }
        });
    }

</script>


<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/shelvesCoursesAjax.js"></script>

</html>