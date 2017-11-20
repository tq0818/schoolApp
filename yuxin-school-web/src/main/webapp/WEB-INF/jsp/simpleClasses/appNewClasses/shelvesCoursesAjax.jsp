
<%--
  Created by IntelliJ IDEA.
  User: z
  Date: 2017/11/19
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--已上架课程列表--%>
<html lang="eeee">
<head>
 <%@include file="/decorators/import.jsp" %>
</head>
<div class="mainbackground nopadding">
    <div class="heading">
        <h2 class="h5" style="display: inline-block;">已上架课程</h2>
        <div style="margin-top: 10px;text-align:right;padding:0 10px;display: inline-block;margin-left: 75%;">
            <span><a href="javascript:;" class="btn btn-primary signUpMany">批量下架</a></span>
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
                <th width="8%">类型</th>
                <th width="6%">上架时间</th>
                <th width="6%">直播时间</th>
                <th width="3%">学习人数</th>
                <th width="3%">价格</th>
                <th width="3%">实际价格</th>
                <th width="20%">操作</th>
            </tr>
            <c:forEach items="${courseList}" var="course" varStatus="status"> 
            <tr>
                <td><input type="checkbox" class="signUpMany" uname="sdsdsd" value=""></td>
                <td><img src="/images/1.jpg" alt="" class="shelvesIcon"></td>
                <td>${course.name}</td>
                <td>${course.gradeName}</td>
                <td>${course.subjectName}</td>
                <td>${course.knowledgeName}</td>
                <td>${course.knowledgeProName}</td>
                <td>${course.stageName}</td>
                <td>${course.typeCode}</td>
                <td>${course.shelvesTime}</td>
                <td>${course.reserveTime}</td>
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
</html>  