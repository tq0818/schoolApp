<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<style>
    .left-side li.active{background: #3faafb !important;}
</style>
<!-- 二级导航 -->
<div class="left-side">
    <div class="left-side-title">
        <%--<c:if test="${userType != 'INSTITUTION_MANAGE'}">
            <i class="icon iconfont" style="float: left;color: #cdcccc;font-size: 20px;margin-left: 2px;" id="backUp">&#xe650;</i>
        </c:if>
--%>
        <%--<em title='${company.companyName }' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 200px;display : inline-block;'>${company.companyName }</em>--%>
        <em title='${ins.name}' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 190px;display : inline-block;'>${ins.name}</em>
        <!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
    </div>
    <ul id="course_manage" class="system_managelist">
        <li class="subentry" code="essential" mark="/InsInfoBase/findInsById?id=${ins.id}">基本信息管理</li>
        <li class="subentry" code="elegantDemeano" mark="/institutionStyle/queryInstitutionStyle?relationId=${ins.id}">风采管理</li>
        <li class="subentry" code="course" mark="/institutionClassType/classTypeMain/${ins.id}">课程管理</li>
        <li class="subentry" code="teacherFamous " mark="/InsInfoBase/famousTeacher/${ins.id}">名师管理</li>
        <li class="subentry" code="evaluate" mark="/comment/insCommentIndex?id=${ins.id}">评论管理</li>
    </ul>
</div>
<script>
    $(document).ready(function(){
        //点击左侧菜单
        $("#course_manage").on('click','li',function(){
            var url=$(this).attr("mark");
            window.location.href=rootPath+url;
        });
        //返回
        $(".hcancle").on('click',function(){
            window.location.href=rootPath+"/company/companyService";
            /*window.history.go(-1);*/
        });
//        $selectSubMenu("org_service");
        //        二级菜单加active
        $selectSubMenu('organizationIndex');
    });
    function $selectSubMenus(code) {
        $(".overflow").find(".system_managelist").find("li").each(function() {
            if ($(this).attr("code") == code) {
                $(this).addClass("active").siblings("li").removeClass("active");
            }
        })
    }
    //点击返回，返回上一级
    $('#backUp').click(function () {
        window.history.go(-1);
    });
</script>



