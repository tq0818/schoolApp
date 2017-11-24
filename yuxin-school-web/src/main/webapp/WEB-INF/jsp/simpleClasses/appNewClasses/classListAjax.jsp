
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
<div class="mainbackground nopadding">
    <div class="heading">
        <h2 class="h5" style="display: inline-block;">已上架课程</h2>
        <div style="margin-top: 10px;text-align:right;padding:0 10px;display: inline-block;margin-left: 75%;">
            <span><a href="javascript:;" class="btn btn-primary signUpMany" id="batchRecommendation">批量推荐</a></span>
        </div>
        <span class="line"></span>
    </div>
    <div class="user-list">
        <table class="table table-center">
            <tbody>
            <tr>
                <th width="1%"><input type="checkbox" class="checkboxAll"></th>
                <th width="5%">课程图片</th>
                <th width="5%">课程名称</th>
                <th width="10%">学段</th>
                <th width="5%">学科</th>
                <th width="10%">知识点专题</th>
                <th width="10%">知识点</th>
                <th width="5%">阶段</th>
                <th width="6%">类型</th>
                <th width="7%">上架时间</th>
                <th width="8%">直播时间</th>
                <th width="3%">学习人数</th>
                <th width="3%">价格</th>
                <th width="3%">实际价格</th>
                <th width="20%">操作</th>
            </tr>
            <c:forEach items="${commList}" var="course" varStatus="status">
            <tr>
                <td><input type="checkbox" class="signUpMany" uname="sdsdsd" value=""></td>
                <td><img src="/images/1.jpg" alt="" class="shelvesIcon"></td>
                <td>${course.name}</td>
                <td>${course.gradeName}</td>
                <td>${course.subjectName}</td>
                <td>${course.knowledgeName}</td>
                <td>${course.knowledgeProName}</td>
                <td>${course.stageName}</td>
                <c:if test="${course.typeCode=='CLASS_TYPE_NOMAL'}">
                <td>普通</td>
                </c:if>
                <td><fmt:formatDate value="${course.shelvesTime}" pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${course.reserveTime}" pattern="yyyy-MM-dd" />-${course.reserveHour}</td>
                <td>${course.buyNum}</td>
                <td>${course.salePrice}</td>
                <td>${course.appprice}</td>
                <td>
                    <span><a href="javascript:;" class="btn btn-primary btn-sm">下架</a></span>
                    <span><a href="javascript:;" class="btn btn-primary btn-sm">推荐</a></span>
                    <span><a href="/appNewClasses/InformationEditing" class="btn btn-primary btn-sm">编辑</a></span>
                </td>
            </tr>
             </c:forEach> 
            </tbody>
        </table>

        <div class="pages pagination"></div>
    </div><input type="hidden" id="rowCount" value="68266"><input type="hidden" id="pageNo" value="1"><input type="hidden" id="maxCount" value="999999999">
</div>
<%--弹出框--%>
<div class="popupContainerRecommendation">
    <span class="closePopupContainer">x</span>
    <div class="toRecommon">
        <div id="toRecommon">
            <div class="recommendationSection recommendBatchRecommendation">
                <label for="">推荐学段</label>
                <div id="gradeList">
                    <a href="javascript:void(0)" id="all" class="btn btn-default">全部</a>
                    <a href="javascript:void(0)" id="1" class="btn btn-default">一年级</a>
                    <a href="javascript:void(0)" id="2" class="btn btn-default">二年级</a>
                    <a href="javascript:void(0)" id="3" class="btn btn-default">三年级</a>
                    <a href="javascript:void(0)" id="4" class="btn btn-default">四年级</a>
                    <a href="javascript:void(0)" id="5" class="btn btn-default">五年级</a>
                    <a href="javascript:void(0)" id="6" class="btn btn-default">六年级</a>
                    <a href="javascript:void(0)" id="7" class="btn btn-default">初一</a>
                    <a href="javascript:void(0)" id="8" class="btn btn-default">初二</a>
                    <a href="javascript:void(0)" id="9" class="btn btn-default">初三</a>
                    <a href="javascript:void(0)" id="10" class="btn btn-default">高一</a>
                    <a href="javascript:void(0)" id="11" class="btn btn-default">高二</a>
                    <a href="javascript:void(0)" id="12" class="btn btn-default">高三</a>
                </div>
                <div class="sureBatchRecommendation">
                    <button class="btn btn-success ">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="popupOpacity"></div>

<script>
    $('#batchRecommendation').click(function () {
        $('.popupContainerRecommendation').show();
        $('.popupOpacity').show();
    });
    $('.closePopupContainer').click(function () {
        $('.popupContainerRecommendation').hide();
    });
    //点击确定推荐学段，隐藏弹窗
    $('.sureBatchRecommendation').children('button').click(function () {
        $('.popupContainerRecommendation').hide();
        $('.popupOpacity').hide();
    });
</script>
<script>
    //    推荐学段复选
    $('#gradeList').children('a').click(function(){
        if($(this).hasClass('active')){
            $(this).removeClass('active');
            $('#gradeList').children('a').eq(0).removeClass('active');
        }else {
            $(this).addClass('active');
        }
    });
    //    点击全部，则全部选中
    $('#gradeList').children('a').eq(0).click(function(){

        var allChildren = $('#gradeList').children('a');

        //如果全部有active则删除全部的选中，否则全部选中
        if($(this).hasClass('active')){
            for(var i=1; i<allChildren.length;i++){
                allChildren.eq(i).addClass('active');
            }
        }else{
            for(var i=1; i<allChildren.length;i++){
                allChildren.eq(i).removeClass('active');
            }
        }
    });

    function saveRecommond(){
        var ids = "";
        var appId="1";
        $("#gradeList").find("a").each(function(){
            if($(this).hasClass("active")){
                ids+=$(this).attr("id")+",";
            }
        });
        if(""==ids){
            alert("请选择推荐学段");
            return;
        }
        var sort=$("#sort").val();
        if(sort){
            //判断是否填写数字
            var reg = new RegExp("^[0-9]*$");
            if(!reg.test(sort)){
                alert("请输入数字!");
            }
            return;
        }
        $.ajax({
            url : rootPath +"/appNewClasses/insertRcommondInfo",
            type : "post",
            data : {"ids":ids,"sort":sort,"appId":appId},
            success : function(result) {
                if("1"==result){
                    alert("推荐成功")
                    $('.popupContainer').hide();
                    $('.popupOpacity').hide();
                }else{
                    alert("推荐失败")
                }
            }
        });
    }


</script>

</html>  