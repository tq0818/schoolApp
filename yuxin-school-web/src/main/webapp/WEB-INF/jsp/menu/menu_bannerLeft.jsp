<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
<div class="left-side">
    <div class="left-side-title">
        <i class="icon iconfont" style="float: left;color: #cdcccc;font-size: 20px;margin-left: 2px;" id="backUp">&#xe650;</i>
        <%--<em title='${company.companyName }' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 200px;display : inline-block;'>${company.companyName }</em>--%>
        <em title='Banner管理' style='overflow: hidden;text-overflow:ellipsis;white-space: nowrap;width: 200px;display : inline-block;'>Banner管理</em>
        <!-- <span class="iconfont return-pic hcancle">&#xe650;</span> -->
    </div>
    <ul id="course_manage" class="system_managelist">
        <li class="subentry" code="comBannerIndex" mark="/Banner/comBannerIndex">首页Banner</li>
        <li class="subentry" code="riseSchoolBanner" mark="/Banner/riseBannerIndex">小升初Banner</li>
        <li class="subentry" code="acrcoBanner" mark="/Banner/acrcoBannerIndex">首页通栏Banner</li>
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
        $(".pageRecommendtionBg").find(".system_managelist").find("li").each(function() {
            if ($(this).attr("code") == code) {
                $(this).addClass("active").siblings("li").removeClass("active");
            }
        })
    }

</script>

