<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>风采管理</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mine.css">
   <%--  <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script> --%>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/plugins/jcrop/css/jquery.Jcrop.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/mbox.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
    <style>
        .gobal-progress{display: none !important;} 
    </style>
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/institution/elegantDemeanor.css">
</head>
<body>
<input type="hidden" id="pageNo" value='${pageNo}'/>
<input type="hidden" id="rowCount" value='${rowCount}'/>
<input type="hidden" id="relationId" value='${relationId}'/>
      <span class="labelName">风采展示:</span>
      <ul style="display: inline-block;width: auto;" class="newUl">
          <li class="addImg mienShow" id="eleShow">
              <i class="icon iconfont"></i>
          </li>
          <c:forEach var="schoolStyle" items="${result}" varStatus="status">
          	<li>
               <img src="${schoolStyle.imgUrl}" alt="" style="width: 100%;height: 100%"  class="imgClick">
              <span class="imgInfo">${schoolStyle.content}</span>
              <c:if test="${schoolStyle.isTop == 1}"><a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">已置顶</a></c:if>
              <c:if test="${schoolStyle.isTop == 0}"><a href="javascript:void(0)" class="btn btn-primary btn-sm rightShow">未置顶</a></c:if>
              <div class="listBg">
                  <c:if test="${schoolStyle.isTop == 1}"><a href="javaScript:void(0)" class="btn btn-warning btn-sm imgTop btnUpdateTop" data-value="0">取消置顶</a></c:if>
                  <c:if test="${schoolStyle.isTop == 0}"><a href="javaScript:void(0)" class="btn btn-warning btn-sm imgTop btnUpdateTop" data-value="1">置顶</a></c:if>
                  <a href="javaScript:void(0)" class="btn btn-success btn-sm imgDelete">删除</a>
                  <a href="javascript:void(0)" class="btn btn-success btn-sm imgChange mienShow btnStyleUpdate" data-value="${schoolStyle.id}" >修改</a>
              </div>
              </li>
          </c:forEach>
      </ul>
	  
</body>
</html>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script>
 $(function(){
	 console.log("总数"+$("#rowCount").val());
	 console.log("页码 "+$("#pageNo").val());
	 $(".pagination").pagination('${rowCount}', {
	        next_text : "下一页",
	        prev_text : "上一页",
	        current_page :'${pageNo - 1}',
	        link_to : "javascript:void(0)",
	        num_display_entries : 9,
	        items_per_page : 9,
	        num_edge_entries : 1,
	        callback:function(page,jq){
	        	console.log("page:"+page);
	            var pageNo = page + 1;
	            console.log(pageNo);
	            queryInstitutionStyle(pageNo);
	        }
	    }); 
 });
    
    //点击风采上传弹窗
    $('#eleShow').click(function () {
        $('#elegant').show();
        $("#imgDataStyle").siblings().text("").text("添加图片");
    });
    $('.closeElePic').click(function () {
        $('#elegant').hide();
        //移除剪切图插件对象
        if (jcrop_apis){
            jcrop_apis.destroy();
        }
        //关闭后将添加图片的格式框恢复原状
        $(".picStyle").attr("src","").attr("style","").attr("style","margin: auto;width: 300px;height: 300px;");
        $(".writeWord").val("");
    });
    //点击修改风采 btnStyleUpdate
    $('.btnStyleUpdate').click(function () {
        $('#elegant').show();
        $("#updateId").attr("value",$(this).attr("data-value"));
        console.log($(this).parent());
        $("#targetStyle").attr("src",$(this).parent().siblings(".imgClick").attr("src")).attr("style","");
        $("#styleContent").val($(this).parent().siblings(".imgInfo").text());
	      //初始化xywh
	   	 $("#x").val("0");
	   	 $("#y").val("0");
	   	 $("#w").val("0");
	   	 $("#h").val("0");
	   	 $("#imgDataStyle").siblings().text("").text("更改图片");
    });
    
    //为图片添加点击事件,以便图片方大
    $('.imgClick').dblclick(function(){
    	var url = $(this).attr('src');
    	$('.bigImg').show().attr('src',url);
    	return false;
    });
	//置顶和取消置顶
	$(".btnUpdateTop").click(function(){
		//更新id
		var id = $(this).parent().find(".btnStyleUpdate").attr("data-value");
		//
		var isTop = $(this).attr("data-value");
		console.log(id,isTop);
		$.ajax({
	          url: rootPath + "/institutionStyle/updateIsTop",
	          data: {"id":id,
	              "isTop":isTop
	          },
	          dataType: "json",
	          success: function (data) {
	              if(data == "success"){
	            	  queryInstitutionStyle(1);
	              }
	          }
	      });
	});
	
	//删除风采
	$(".imgDelete").click(function(){
		var id = $(this).parent().find(".btnStyleUpdate").attr("data-value");
		$.confirm("是否删除该图片?",function(data){
			if(data){
				var pageNo = $("#pageNo").val();
				var relationId = $("#relationId").val();
				console.log("当前页"+pageNo+","+relationId);
				$.ajax({
			          url: rootPath + "/institutionStyle/deleteStyle",
			          data: {"primaryId":id,
			        	  "pageNo":pageNo,
			        	  "relationId":relationId
			          },
			          dataType: "json",
			          success: function (data) {
			              if(data == "success"){
			            	  queryInstitutionStyle(pageNo);
			              }else if(data == "reset"){//刷新
			            	  window.location.href=rootPath +"/institutionStyle/queryInstitutionStyle?relationId="+relationId; 
			              }
			          }
			      });
			}
		});
	});
  //查询风采
  function queryInstitutionStyle(pageNo){
  	$.ajax({
          url: rootPath + "/institutionStyle/queryInsStyle",
          data: {"page":pageNo,
              "pagesize":9,
              "relationId":$("#relationId").val(),
          },beforeSend: function (XMLHttpRequest) {
          },
          dataType: "html",
          success: function (data) {
              $("#insStyleInfo").html("").html(data);
          }
      });
  }
  
 </script>