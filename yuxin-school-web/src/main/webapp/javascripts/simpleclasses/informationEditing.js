function toOnsaleEdit(ids,zhiboFlag){

	$.ajax({
		url : rootPath + "/simpleClasses/showAppShelvesEdit",
		type : "post",
		data:{"ids":ids,"zhiboFlag":zhiboFlag},
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success : function(result) {
			$("#bbbbbbbb").html(result);
		},
		 complete:function(XMLHttpRequest,textStatus){
				$(".loading").hide();
	            $(".loading-bg").hide();
	     }
	});
}
function chooseSlibMenu(obj){
	var id = obj.attr("id");
	var parentId = obj.val();

	$.ajax({
		url : rootPath +"/simpleClasses/querySlibMenu",
		type : "post",
		data : {"parentId":parentId,"typeId":id},
		success : function(result) {

			var data = result.comm;
			var stages = result.stages;
			var types = result.types;

			if("courseCaId"==id){
				$("#gradeId").html("");
				var html='';
				for(var i=0;i<data.length;i++){
					if(i==0){
						html+=' <option value="'+data[i].id+'" selected="selected">'+data[i].name+'</option>';
					}else{
						html+=' <option value="'+data[i].id+'">'+data[i].name+'</option>';
					}
				}
				$("#gradeId").html(html);
				chooseSlibMenu($("#gradeId"));

				//阶段
				$("#stageId").html("");
				var html='';
				for(var i=0;i<stages.length;i++){
					if(i==0){
						html+=' <option value="'+stages[i].id+'" selected="selected">'+stages[i].name+'</option>';
					}else{
						html+=' <option value="'+stages[i].id+'">'+stages[i].name+'</option>';
					}
				}
				$("#stageId").html(html);

				//类型
				$("#typeId").html("");
				var html='';
				for(var i=0;i<types.length;i++){
					if(i==0){
						html+=' <option value="'+types[i].id+'" selected="selected">'+types[i].name+'</option>';
					}else{
						html+=' <option value="'+types[i].id+'">'+types[i].name+'</option>';
					}
				}
				$("#typeId").html(html);


			}else if("gradeId"==id){
				$("#subjectId").html("");
				var html='';
				for(var i=0;i<data.length;i++){
					if(i==0){
						html+=' <option value="'+data[i].id+'" selected="selected">'+data[i].name+'</option>';
					}else{
						html+=' <option value="'+data[i].id+'">'+data[i].name+'</option>';
					}
				}
				$("#subjectId").html(html);
				chooseSlibMenu($("#subjectId"));
			}else if("subjectId"==id){
				$("#kwonProId").html("");
				var html='';
				for(var i=0;i<data.length;i++){
					if(i==0){
						html+=' <option value="'+data[i].id+'" selected="selected">'+data[i].name+'</option>';
					}else{
						html+=' <option value="'+data[i].id+'">'+data[i].name+'</option>';
					}
				}
				$("#kwonProId").html(html);
				chooseSlibMenu($("#kwonProId"));
			}else if("kwonProId"==id){
				$("#knowId").html("");
				var html='';
				for(var i=0;i<data.length;i++){
					if(i==0){
						html+=' <option value="'+data[i].id+'" selected="selected">'+data[i].name+'</option>';
					}else{
						html+=' <option value="'+data[i].id+'">'+data[i].name+'</option>';
					}
				}
				$("#knowId").html(html);
			}
		}
	});
}

function queryClassDetails(id){
	$("#myForm").html("");
	var input="<input type='hidden' value='"+id+"' name='id'/><input type='hidden' value='"+$("#lab").val()+"' name='lable'/>";
	$("#myForm").html(input);
	$("#myForm").attr("action","/editSimpleCourse/editClassTypeMessage").submit();
}


function toShelves(flag){
	var courseCaId = $("#courseCaId").val();
	var gradeId = $("#gradeId").val();
	var subjectId = $("#subjectId").val();
	var kwonProId = $("#kwonProId").val();
	var knowId = $("#knowId").val();
	var stageId = $("#stageId").val();
	var typeId = $("#typeId").val();
	var id = $("#commodityId").val();
	var appId = $("#appId").val();
	var shelvesTime = $("#shelvesTime").val();
	var labDesc = $("#labDesc").val();
	if("0"==flag && ""== $.trim(shelvesTime) ){
		alert("预约上架时间不能为空");
		return;
	}

	$.ajax({
		url : rootPath +"/simpleClasses/insertShelvesInfo",
		type : "post",
		data : {"id":id,"appId":appId,"courseCaId":courseCaId,"gradeId":gradeId,"subjectId":subjectId,"kwonProId":kwonProId,"knowId":knowId,"stageId":stageId,"typeId":typeId,"shelvesFlag":flag,"shelvesTime":shelvesTime,"labDesc":labDesc},
		success : function(result) {
			if("1"==result){
				alert("成功")
				$('.popupContainer').hide();
				$('.popupOpacity').hide();
				Form.queryAllCommdityByItemNew(1);

			}else{
				alert("失败")
			}
		}
	});			
}


