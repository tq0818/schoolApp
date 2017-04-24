$(document).ready(function(){
	//$(".footer").addClass("footer-fixed");
	$(".sc-type").on('click','a.btn',function(){
		var status=$(this).hasClass('btn-success');
		if(!status){
			$(this).addClass('btn-success').siblings('a').removeClass('btn-success');
		}
	});
	$("#cycleUrl").on("mouseenter", function() {
		$(this).attr("placeholder","");
	}).on("mouseleave", function() {
		$(this).attr("placeholder","请输入完整的网页地址");
	});
	$selectMenu('org_service');
	var schoolId="";
	$("#schoolList").find("a").each(function(i){
		if($(this).hasClass("btn-success")){
			schoolId=$(this).attr("mark");
		}
	});
	queryCycleData(schoolId);
	 $("input.shangchuan").on('click',function(){
		  var _this = $(this).parents('div.left').next().find('.set-infos').find('a.btn-submit');
          if ( !(_this.hasClass('.btn-primary')) ){
              _this.addClass('btn-primary').removeClass('btn-gray');
          }
	  })

});

//li位置向下移动
function changeStatus(id,sort){
	var schoolId="";
	$("#schoolList").find("a").each(function(i){
		if($(this).hasClass("btn-success")){
			schoolId=$(this).attr("mark");
		}
	});
	var liOne=$("#liStatus"+sort);
	var liTwo=$("#liStatus"+sort).next();
	liTwo.after(liOne);
	var liTwoSort=liTwo.attr("_sort");
	var liTwo=liTwo.attr("id1");
	$.ajax({
		type: "post",
		url:  rootPath+"/sysCyclePic/change",
		data: {"id1" : id,"sortOne" : sort,"liTwo" : liTwo, "liTwoSort" : liTwoSort},
		success: function(result){
			queryCycleData(schoolId);
		}
	});
}  

//li位置向上移动
function upStatus(id,sort){
	var schoolId="";
	$("#schoolList").find("a").each(function(i){
		if($(this).hasClass("btn-success")){
			schoolId=$(this).attr("mark");
		}
	});
	var liOne=$("#liStatus"+sort);
	var liTwo=$("#liStatus"+sort).prev();
	liOne.after(liTwo);
	var liTwoSort=liTwo.attr("_sort")
	var liTwo=liTwo.attr("id1");
	$.ajax({
		type: "post",
		url:  rootPath+"/sysCyclePic/change",
		data: {"id1" : id,"sortOne" : sort,"liTwo" : liTwo, "liTwoSort" : liTwoSort},
		success: function(result){
			queryCycleData(schoolId);
		}
	});
}           
            
//异步加载数据
function queryCycleData(schoolId){
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/sysCyclePic/queryCycleDatas", 
		  data:{"schoolId":schoolId, "picType":"openClass"},
		  success: function(result){
			$("#cycleDataLists li").not(":first").remove();
			$("#cycleDataLists").append(result);
			var count=0;
			$("#cycleDataLists").find("li").each(function(i){
				count++;
			});
			if(count==2){
				$("#cycleDataLists").find("li:eq(1)").find("div.direction").css("display","none");
			}
		  }
	  });
}

//添加轮播图
function addCyclePic(){
	var schoolId="";
	$("#schoolList").find("a").each(function(i){
		if($(this).hasClass("btn-success")){
			schoolId=$(this).attr("mark");
		}
	});
	if(schoolId==""){
		$.msg("请先添加分校");
		return;
	}
	var clickUrl=$("#cycleUrl").val();
	if(clickUrl!=""){
		if(clickUrl.indexOf("http://")!=-1){
			$.msg("输入链接无效");
			return;
		}
		clickUrl="http://"+clickUrl;
		var str = clickUrl.match(/http:\/\/.+/);
		if(str==null){
			$.msg("输入链接无效");
			return;
		}
	}else{
		clickUrl="";
	}
	
	var valid_flag=0;
	var status=$("#addStatus").attr("class");
	var imgData=$("#imgData").val();
	if(status=="i open"){
		valid_flag=1;
	}
	var picUrl=$("#imgObject").attr("ids");
	if(picUrl==""){
		$.msg("图片不能为空");
		return;
	}
	$.ajax({
		type: "post",
		url:  rootPath+"/sysCyclePic/addCycles",
		data : {"clickUrl" :clickUrl,"validFlag" : valid_flag,"schoolId":schoolId,"picUrl":picUrl, "picType":"openClass"},
		success: function(result){
			queryCycleData(schoolId);
			$("#cycleUrl").val('');
			$(".add-focus").css("display","none");
			clearData();
		}
	});
}

//修改轮播图
function updateSysCyclePic(id,ele){
	var status;
	$(".is-save"+id).find("p:last").find("a.btn-submit").each(function(){
		status=$(this).hasClass("btn-gray");
	});
	if(status){
		return;
	}
	var schoolId="";
	$("#schoolList").find("a").each(function(i){
		if($(this).hasClass("btn-success")){
			schoolId=$(this).attr("mark");
		}
	});
	var clickUrl=$("#cycleUrl"+id).val();
	if(clickUrl!=""){
		if(clickUrl.indexOf("http://")!=-1){
			$.msg("输入链接无效");
			return;
		}
		clickUrl="http://"+clickUrl;
		var str = clickUrl.match(/http:\/\/.+/);
		if(str==null){
			$.msg("输入链接无效");
			return;
		}
	}else{
		clickUrl="";
	}
	var valid_flag=0;
	var status=$("#addStatus"+id).attr("class");
	if(status=="i open"){
		valid_flag=1;
	}
	$.ajax({
		type: "post",
		url:  rootPath+"/sysCyclePic/updateSysCycles",
		data : {"id" :id, "clickUrl" :clickUrl,"validFlag" : valid_flag,"schoolId":schoolId,"picUrl":$("#imgOne"+id).attr("ids")},
		success: function(result){
			$.msg("修改成功");
			queryCycleData(schoolId);
		}
	});
}

//删除轮播图
function deleteSysCyclePic(id,sort){
	var schoolId="";
	$("#schoolList").find("a").each(function(i){
		if($(this).hasClass("btn-success")){
			schoolId=$(this).attr("mark");
		}
	});
	$.confirm("您确定要删除吗?",function(b){
		if(b==true){
			$("#liStatus"+sort).remove();
			 $.ajax({ 
				  type: "post", 
				  url: rootPath+"/sysCyclePic/delSysCycle", 
				  data: {"id" : id},
				  success: function(result){
					  queryCycleData(schoolId);
				  }
			  });
		}else{
			return; 
		}
	});
}

//清空数据
function clearData(){
	$("#cycleUrl").val('');
	$("#imgObject").attr("src","");
	$("#imgObject").attr("ids","");
	$("#leftCont").find("p.tips-msg").removeClass("none");
}
//上传图片
function changeCyclePic(type,ele){
	if(type=="so"){
		$.ajaxFileUpload({
			url : rootPath+"/sysCyclePic/UploadCycles;"+ window["sessionName"] + "=" + window["sessionId"],
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgDatas',
			dataType:'json',
			type : "POST",
			success : function(data) {
				$("#leftCont").find("p.tips-msg").addClass("none");
				console.log(data.picPath);
				console.log(data.url);
				$("#imgObject").attr("src",data.url);
				$("#imgObject").attr("ids",data.picPath);
			},
			error : function(resp,msg,err){
				console.log(resp);
			},
			loadingEle: $(ele).parents(".focus-content").find(".left"),
			fileName: 'imgData',
			sessionId: $("#sessionId").attr("value")
		});
	}else{
		$.ajaxFileUpload({
			url : rootPath+"/sysCyclePic/UploadCycles;"+ window["sessionName"] + "=" + window["sessionId"],
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgData',
			dataType:'json',
			type : "POST",
			success : function(data) {
				$("#leftCont").find("p.tips-msg").addClass("none");
				console.log(data.picPath);
				console.log(data.url);
				$("#imgObject").attr("src",data.url);
				$("#imgObject").attr("ids",data.picPath);
			},
			error : function(resp,msg,err){
				console.log(resp);
			},
			loadingEle: $(ele).parents(".focus-content").find(".left"),
			fileName: 'imgData'
		});
	}
		
}

function updateCyclePic(id,ele){
	$.ajaxFileUpload({
		url : rootPath+"/sysCyclePic/UploadCycles;"+ window["sessionName"] + "=" + window["sessionId"],
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgData'+id,
		dataType:'json',
		type : "POST",
		data : {"id":id},
		success : function(data) {
			console.log(data.picPath);
			console.log(data.url);
			if(data=="null"){
				alert("上传图片失败!");
			}
			$("#imgOne"+id).css("background-image","url("+data.url+")");
			$("#imgOne"+id).attr("ids",data.picPath);
		},
		error : function(resp,msg,err){
			console.log(resp);
		},
		loadingEle: $(ele).parents(".focus-content").find(".left img"),
		fileName: 'imgData'
	});
}