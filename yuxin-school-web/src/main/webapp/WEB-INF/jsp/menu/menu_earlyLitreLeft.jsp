<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
<div class="left-side">
    <div class="left-side-title">
        <i class="icon iconfont" style="float: left;color: #cdcccc;font-size: 20px;margin-left: 2px;" id="backUp">&#xe650;</i>
        <%--<em title='${company.companyName }' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 200px;display : inline-block;'>${company.companyName }</em>--%>
        <em title='${schoolName}' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 200px;display : inline-block;'>${schoolName}</em>
        <!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
    </div>
    <ul id="course_manage" class="system_managelist">
        <li class="subentry" code="essential" mark="/riseschoolback/essential?schoolId=${riseSchoolId}&schoolName=${schoolName}">基本信息</li>
        <li class="subentry" code="schoolDetails" mark="/riseschoolback/schoolDetails?schoolId=${riseSchoolId}&schoolName=${schoolName}">学校详情</li>
        <li class="subentry" code="mien" mark="/riseschoolback/mien?schoolId=${riseSchoolId}&schoolName=${schoolName}">学校风采</li>
        <li class="subentry" code="upgradeSchools" mark="/riseschoolback/upgradeSchools?schoolId=${riseSchoolId}&schoolName=${schoolName}">升学</li>
        <li class="subentry" code="dynamic" mark="/riseschoolback/dynamic?schoolId=${riseSchoolId}&schoolName=${schoolName}">动态</li>
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
        $selectSubMenu('earlyLitre');
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

