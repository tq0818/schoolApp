
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>小升初banner图设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    <script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body style="position:relative;">
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<%--已上架课程列表--%>
<div id="modelList" class="pageRecommendtionBg">
    <jsp:include page="/WEB-INF/jsp/menu/menu_bannerLeft.jsp"></jsp:include>
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5" style="display: inline-block;margin: 10px 0;">banner管理</h2>
            <div class="bannerButton">
                <span><button onclick="qiyong()" class="btn btn-primary " >启用</button></span>
                <span><button onclick="jinyong()" class="btn btn-primary " >禁用</button></span>
                <!-- <span><a href="##" class="btn btn-primary changeOrder" >更改排序</a></span> -->
                <span><a href="<%=rootPath %>/Banner/addBanner/1" class="btn btn-primary " >添加banner</a></span>
            </div>
            <span class="line"></span>
        </div>
        <div class="user-list" id="tableList">
            
        </div>
        <div class="user-list" id="tableList1" style="display: none">
            
        </div>
    </div>
   
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!-- ajax加载中div结束 -->

<script>
	$(document).ready(function() { 
		qiyong();
	
	}); 
//    点击禁用，弹窗提示
    $('.forbidBanner').click(function () {
        alert("只有启用banner才能支持排序！");
    });
//    点击更改培训
    $('.changeOrder').click(function () {
        if($(this).html()=='更改排序'){
            $(this).html('保存排序');
        }else {
            $(this).html('更改排序');
        }
    });

	function jinyong(){
		$.ajax({
			url: rootPath + "/Banner/jinYong",
			type:"post",
			data:{bannerType:1},
			dataType:"html",
			success:function(data){
				$('#tableList1').html(data);
			 	var myTable= document.getElementById("tableList1"); 
			 	myTable.style.display="block";
			 	var myTable1= document.getElementById("tableList"); 
			 	myTable1.style.display ="none";
			}
		});
		
	}
	function qiyong(){
		$.ajax({
			url: rootPath + "/Banner/qiYong",
			type:"post",
			data:{bannerType:1},
			dataType:"html",
			success:function(data){
				$('#tableList').html(data);
			}
		});
		 var myTable1= document.getElementById("tableList"); 
		 myTable1.style.display="block";
		 var myTable= document.getElementById("tableList1"); 
		 myTable.style.display ="none";
	}
</script>
<script>
    //        二级菜单加active

//        $selectSubMenu('riseSchoolBanner');
        $selectSubMenus('riseSchoolBanner');

</script>
</body>
</html>
