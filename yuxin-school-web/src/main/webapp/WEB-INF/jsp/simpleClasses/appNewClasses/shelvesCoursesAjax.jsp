
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
            <span><a href="javascript:;" class="btn btn-primary signUpMany" id="batchRelease">批量下架</a></span>
        </div>
        <span class="line"></span>
    </div>
    <div class="user-list">
        <table class="table table-center" id="batchReleaseList">
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
            <c:forEach items="${courseList.data}" var="course" varStatus="status">
            <tr>
                <td><input type="checkbox" class="signUpMany" uname="sdsdsd" value="" data-id="${course.appId }"></td>
                <c:choose>
                	<c:when test="${course.imgUrl != ''}">
                		<td><img src="${commodityPicUrl}${course.imgUrl}" alt="" class="shelvesIcon"></td>
                 	</c:when>
                 	<c:otherwise> 
                		<td><img src="${commodityPicUrl}${course.cover}" alt="" class="shelvesIcon"></td>
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
                    <a href="javascript:stopClassOnsale(${course.appId });" class="btn btn-primary btn-sm">下架</a>
                    <span><a href="javascript: toRcommon('${course.courseCaId}','${course.liveFlag}','${course.id}');" class="btn btn-primary btn-sm recommendCourse">推荐</a></span>
                    <span><a href="javascript:toOnsaleEdit('${course.appId}_${course.id}','${course.liveFlag}','1');" class="btn btn-primary btn-sm editCourse">编辑上架</a></span>
                </td>
            </tr>

             </c:forEach> 
            </tbody>
        </table>

        <div class="pages">
            <ul class="pagination"></ul>
        </div>
    </div>
<%--弹出框--%>
<div class="popupContainer">
    <span class="closePopupContainer">x</span>
    <div class="toRecommon">
        <div id="toRecommon">

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
<input id="typeIds" type="hidden" value="${typeId}"/>
<input id="typeStr" type="hidden" value="${typeStr}"/>
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
        reloadCurrunt();
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


    function reloadCurrunt(){
        var datas = {};
        fillCategory(datas);
        fillGrade(datas);
        fillSubject(datas);
        fillKnowPro(datas);
        fillKnow(datas);
        fillStage(datas);
        fillType(datas);
        queryClassTypesShelves(datas);
    }


</script>

<script type="text/javascript" src="<%=rootPath %>/javascripts/simpleclasses/shelvesCoursesAjax.js"></script>
<script>
    //批量下架調用接口
    $('#batchRelease').click(function(){
        //判断哪些复选框被选中
        var batchReleaseList = $('#batchReleaseList').find('tr');
        var batchReleaseArray = [];
        var batchReleaseListId = '';
        var batchReleaseListInput = '';

        for(var i=1;i<batchReleaseList.length;i++){
            batchReleaseListInput = batchReleaseList.eq(i).find('.signUpMany');
            if(batchReleaseListInput.prop('checked')){
                batchReleaseListId = batchReleaseListInput.attr('data-id');
                batchReleaseArray.push(Number(batchReleaseListId));
            }
        }

        if(batchReleaseArray.length>0){
            $.confirm("确认下架所选课程？",function(s){
                if(s==true){
                    $.ajax({
                        type : 'post',

                        url : rootPath + '/simpleClasses/stopClassOnsaleAll',
                        data : {
                            "batchReleaseArray" : batchReleaseArray
                        },
                        dataType : 'json',
                        traditional: true,
                        success : function(data){
                            if(data=='1'){
                                alert('下架课程成功！')
                            }else {
                                alert("下架课程失败!");
                            }
                        }
                    });
                }
            });
        }else {
            alert("请勾选要下架的课程！");
        }

    });
    $(document).ready(function(){
        $(".pagination").pagination('${courseList.rowCount}', {
            next_text : "下一页",
            prev_text : "上一页",
            current_page :'${courseList.pageNo-1}',
            link_to : "javascript:void(0)",
            num_display_entries : 8,
            items_per_page : '${courseList.pageSize}',
            num_edge_entries : 1,
            callback:function(page,jq){
                var pageNo = page + 1;
                if($("#searchName").val()){
                    Form.queryCommodityByName(pageNo);
                }else{
                    Form.queryAllCommdityByItemNew(pageNo);
                }
            }
        });
    });
</script>
</html>