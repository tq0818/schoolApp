function toOnsaleEdit(ids,zhiboFlag,flag){
	$.ajax({
		url : rootPath + "/simpleClasses/showAppShelvesEdit",
		type : "post",
		data:{"ids":ids,"zhiboFlag":zhiboFlag,"editFlag":flag},
		beforeSend:function(XMLHttpRequest){
           /* $(".loading").show();
            $(".loading-bg").show();*/
        },
		success : function(result) {
			$("#shelvesList").html("");
			$("#shelvesList").html(result);
		},
		 complete:function(XMLHttpRequest,textStatus){
		/*		$(".loading").hide();
	            $(".loading-bg").hide();*/
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
	$("#myForm").attr("action",rootPath+"/editSimpleCourse/editClassTypeMessage").submit();
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
function stopClassOnsale(id){
	$.confirm("您确定要下架此课程?下架后学员将无法再报名此课程。",function(a){
		if(a==true){
			$.ajax({
				url : rootPath + "/simpleClasses/stopClassOnsale",
				type : "post",
				data : {"id":id},
				success : function(result) {
					if("1"==result){
                        $.confirm("成功",function () {
							reloadCurrunt();
                        });
						// Form.showAllShelvesClssType('all','courseCaId');
					}else{
						alert("失败")
					}
				}
			});
		}else{
			return;
		}
	});
}

function reLoadAppClass(){
	var datas = {};
	fillKnowPro(datas);
	fillGrade(datas);
	fillSubject(datas);
	fillKnowPro(datas);
	fillKnow(datas);
	fillStage(datas);
	fillType(datas);
	queryClassTypesShelves(datas);
}

function fillCategory(datas){
	var categoryid = '';
	$("#courseCaId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			categoryid=$(this).attr("data-code");
		}
	});
	if(categoryid!='all'){
		datas.categoryid=categoryid;
	}
}

function fillGrade(datas){
	var gradeid = '';
	$("#gradeId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			gradeid=$(this).attr("data-code");
		}
	});
	if(gradeid!='all'){
		datas.gradeid=gradeid;
	}
}

function fillSubject(datas){
	var subjectid = '';
	$("#subjectId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			subjectid=$(this).attr("data-code");
		}
	});
	if(subjectid!='all'){
		datas.subjectid=subjectid;
	}
}

function fillKnowPro(datas){
	var knowledgeProid = '';
	$("#kwonProId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			knowledgeProid=$(this).attr("data-code");
		}
	});
	if(knowledgeProid!='all'){
		datas.knowledgeProid=knowledgeProid;
	}
}

function fillKnow(datas){
	var knowledgeid = '';
	$("#knowId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			knowledgeid=$(this).attr("data-code");
		}
	});
	if(knowledgeid!='all'){
		datas.knowledgeid=knowledgeid;
	}
}

function fillStage(datas){
	var stageid = '';
	$("#stageId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			stageid=$(this).attr("data-code");
		}
	});
	if(stageid!='all'){
		datas.stageid=stageid;
	}
}

function fillType(datas){
	var typeCode = '';
	$("#typeId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			typeCode=$(this).attr("data-code");
		}
	});
	if(typeCode!='all'){
		datas.typeCode=typeCode;
	}
}