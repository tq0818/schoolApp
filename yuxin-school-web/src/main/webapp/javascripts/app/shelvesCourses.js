/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function($){
	
	var Form={
			init : function(){
				var $this=this;
				$selectMenu("course_class_type");
				//$(".footer").addClass("footer-fixed");
				$(".t-content").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success');
					if(!status){
						 _this.addClass('btn-success').siblings('a').removeClass('btn-success');
					}
				});
				//全选 取消全选
	            $(".checkboxAll").on('change', function () {
	                if ($(this).prop("checked")) {
	                    $("#tableList").find(".signUpMany").prop("checked", true);
	                } else {
	                    $("#tableList").find(".signUpMany").prop("checked", false);
	                }
	            });
			},
			queryshelvesCoursesApp : function(page){

	            var datas = {"page": page};
	            var categoryName = '',
	                gradeName = '',
	                subjectName = '',
	                knowledgeName = '',
	                knowledgeProName = '',
	                stageName = '',
	                typeCode = '';

	            $("#courseCaId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    categoryName = $(this).attr("data-code");
	                }
	            });
	            if (categoryName != 'all') {
	                datas.categoryName = categoryName;
	            }
	            $("#gradeId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    gradeName = $(this).attr("data-code");
	                }
	            });
	            if (gradeName != 'all') {
	                datas.gradeName = gradeName;
	            }
	            $("#subjectId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    subjectName = $(this).attr("data-code");
	                }
	            });
	            if (subjectName != 'all') {
	                datas.subjectName = subjectName;
	            }
	            $("#kwonProId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    knowledgeName = $(this).attr("data-code");
	                }
	            });
	            if (knowledgeName != 'all') {
	                datas.knowledgeName = knowledgeName;
	            }
	            $("#knowId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    knowledgeProName = $(this).attr("data-code");
	                }
	            });
	            if (knowledgeProName != 'all') {
	                datas.knowledgeProName = knowledgeProName;
	            }
	            $("#stageId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    stageName = $(this).attr("data-code");
	                }
	            });
	            if (stageName != 'all') {
	                datas.stageName = stageName;
	            }
	            $("#typeId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    typeCode = $(this).attr("data-code");
	                }
	            });
	            if (typeCode != 'all') {
	                datas.typeCode = typeCode;
	            }
	            datas.page = page ? page : 1;
	            datas.pageSize=$("#selectCounts").val() || 10;
	            $(".user-list").find("table").find("tr:gt(0)").remove();
	            $.ajax({
	                url: rootPath + "/specialModel/getModelListByIds",
	                type: "post",
	                data: datas,
	                beforeSend: function (XMLHttpRequest) {
	                    $(".loading").show();
	                    $(".loading-bg").show();
	                },
	                success: function (jsonData) {
	                	if (jsonData.data.length == 0) {
                        	 $(".user-list")
	                                .find("table")
	                                .append(
	                                '<tr><td colspan="14">没有查找到数据</td></tr>');
                        }
	                	$.each(jsonData.data,function (i, stu) {
                            $(".user-list").find("table").append('<tr>'+
                                    '<td><input type="checkbox" class="signUpMany" value="'+stu.shelves_id+'"></td>'+
                                    '<td><img src="'+stu.coverUrl+'" alt="" class="shelvesIcon"></td>'+
                                    '<td>'+stu.name+'</td>'+
                                    '<td>'+stu.gradeName+'</td>'+
                                    '<td>'+stu.subjectName+'</td>'+
                                    '<td>'+stu.knowProName+'</td>'+
                                    '<td>'+stu.knowName+'</td>'+
                                    '<td>'+stu.stageName+'</td>'+
                                    '<td>'+stu.typeName+'</td>'+
                                    '<td>'+stu.shelvesTime+'</td>'+
                                    '<td>'+stu.lessonTime+'</td>'+
                                    '<td>'+stu.actualNum+'</td>'+
                                    '<td>'+stu.realPrice+'</td>'+
                                    '<td>'+stu.salePrice+'</td>'+
                                    '<td>'+
                                        '<input type="text" class="'+stu.shelves_id+'" name="recommendationNum">'+
                    					'<button class="btn btn-success btn-sm">确定</button>'+
                    					'<button class="btn btn-danger btn-sm">取消</button>'+
                                    '</td>'+
                                '</tr>');
                                    
                            });
	                	 $("#rowCount").remove();
	                     $("#pageNo").remove();
	                     if (jsonData.rowCount >$("#selectCounts").val()) {
	                            $(".pagination").pagination(jsonData.rowCount,
	                                {
	                                    next_text: "下一页",
	                                    prev_text: "上一页",
	                                    current_page: jsonData.pageNo - 1,
	                                    link_to: "javascript:void(0)",
	                                    num_display_entries: 8,
	                                    items_per_page: jsonData.pageSize,
	                                    num_edge_entries: 1,
	                                    callback: function (page, jq) {
	                                        var pageNo = page + 1;
	                                        $this.queryshelvesCoursesApp(pageNo);
	                                    }
	                                });
	                            $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
	                            $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:student.searchCount()">'+
	                					' <option value="10">10</option>'+
	                					' <option value="20">20</option>'+
	                					' <option value="30">30</option>'+
	                					' <option value="50">50</option>'+
	                					' <option value="100">100</option>'+
	                					' </select> 条   ');
	                            $("#selectCount").val($("#selectCounts").val());
	                        } else {
	                            $(".pagination").html('');
	                        }
	                },
	                complete: function (XMLHttpRequest, textStatus) {
	                    $(".loading").hide();
	                    $(".loading-bg").hide();
	                }
	            });
			},
			showAllShelvesClssType : function(id,typeStr){
				var datas = {};
				datas.typeStr=typeStr;
				datas.typeId=id;
				var categoryid = '',
				gradeid = ''
				subjectid = '',
				knowledgeProid = '',
				knowledgeid = '',
				stageid = '',
				typeCode = '';
				datas.knowledgeid=knowledgeid;
				var allHtml = '<a href="javascript:Form.showAllShelvesClssType(\'all\',\'allToAll\');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>';
				if("courseCaId"==typeStr  && 'all'==id){
					$("#gradeId").html(allHtml.replace("allToAll","gradeId"));
					$("#subjectId").html(allHtml.replace("allToAll","subjectId"));
					$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
					$("#knowId").html(allHtml.replace("allToAll","kwonId"));
					//查询课程信息
					queryClassTypesShelves(datas);
					return;
				}else if("gradeId"==typeStr  && 'all'==id){
					$("#subjectId").html(allHtml.replace("allToAll","subjectId"));
					$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
					$("#knowId").html(allHtml.replace("allToAll","kwonId"));
					//查询课程信息
					fillCategory(datas);
					fillStage(datas);
					fillType(datas);
					queryClassTypesShelves(datas);
					return;
				}else if("subjectId"==typeStr  && 'all'==id){
					$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
					$("#knowId").html(allHtml.replace("allToAll","kwonId"));
					//查询课程信息
					fillGrade(datas);
					fillStage(datas);
					fillType(datas);
					queryClassTypesShelves(datas);
					return;
				}else if("kwonProId"==typeStr  && 'all'==id){
					$("#knowId").html(allHtml.replace("allToAll","knowId"));
					//查询课程信息
					fillSubject(datas);
					fillStage(datas);
					fillType(datas);
					queryClassTypesShelves(datas);
					return;
				}else if("knowId"==typeStr  && 'all'==id){
					fillKnowPro(datas);
					fillStage(datas);
					fillType(datas);
					queryClassTypesShelves(datas);
					return;
				}else if("stageId"==typeStr || "typeId"==typeStr){
					fillKnowPro(datas);
					fillGrade(datas);
					fillSubject(datas);
					fillKnowPro(datas);
					fillKnow(datas);
					fillStage(datas);
					fillType(datas);
					queryClassTypesShelves(datas);
					return;
				}
				$.ajax({
					url :  rootPath +"/simpleClasses/querySlibMenu",
					type : "post",
					data : {"parentId":id,"typeId":typeStr},
					success : function(result) {
						var data = result.comm;
						var stages = result.stages;
						var types = result.types;
						var html='';
						if("courseCaId"==typeStr){
							//更新学段
							html +=allHtml.replace("allToAll","gradeId");
							for(var i=0;i<data.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'gradeId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
							}
							$("#gradeId").html(html);
							$("#subjectId").html(allHtml.replace("allToAll","subjectId"));
							$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
							$("#kwonId").html(allHtml.replace("allToAll","kwonId"));
							//更新阶段和类型
							html='';
							html +=allHtml.replace("allToAll","stageId");
							for(var i=0;i<stages.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+stages[i].id+'\',\'stageId\');" data-code=\''+stages[i].id+'\' class="btn btn-mini btn-default">'+stages[i].name+'</a>';
							}
							$("#stageId").html(html);
							html='';
							html +=allHtml.replace("allToAll","typeId");
							for(var i=0;i<types.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+types[i].id+'\',\'typeId\');" data-code=\''+types[i].id+'\' class="btn btn-mini btn-default">'+types[i].name+'</a>';
							}
							$("#typeId").html(html);
							$("#courseCaId").find("a").each(function(i){
								if($(this).hasClass('btn-success')){
									categoryid=$(this).attr("data-code");
								}
							});
							fillCategory(datas);
							queryClassTypesShelves(datas);
						}else if("gradeId"==typeStr){
							//跟新科目
							html +=allHtml.replace("allToAll","subjectId");
							for(var i=0;i<data.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'subjectId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
							}
							$("#subjectId").html(html);
							$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
							$("#kwonId").html(allHtml.replace("allToAll","kwonId"));
							//查询课程信息
							fillGrade(datas);
							fillStage(datas);
							fillType(datas);
							queryClassTypesShelves(datas);
						}else if("subjectId"==typeStr){
							//更新知识点专题
							html +=allHtml.replace("allToAll","kwonProId");
							for(var i=0;i<data.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'kwonProId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
							}
							$("#kwonProId").html(html);
							$("#kwonId").html(allHtml.replace("allToAll","kwonId"));
							//查询课程信息
							fillSubject(datas);
							fillStage(datas);
							fillType(datas);
							queryClassTypesShelves(datas);
						}else if("kwonProId"==typeStr){
							//更新知识点
							html +=allHtml.replace("allToAll","knowId");
							for(var i=0;i<data.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'knowId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
							}
							$("#knowId").html(html);
							//查询课程信息
							fillKnowPro(datas);
							fillStage(datas);
							fillType(datas);
							queryClassTypesShelves(datas);
						}else if("knowId"==typeStr){
							//查询课程信息
							fillKnow(datas);
							fillStage(datas);
							fillType(datas);
							queryClassTypesShelves(datas);
						}
						else if("#stageId"==typeStr){
							//查询课程信息
							fillCategory(datas);
							fillGrade(datas);
							fillSubject(datas);
							fillKnowPro(datas);
							fillKnow(datas);
							fillStage(datas);
							fillType(datas);
							queryClassTypesShelves(datas);
						}
						Form.queryshelvesCoursesApp(1);
					}
				});
			},
		}
	$(document).ready(function(){		
		Form.init();
		Form.queryshelvesCoursesApp(1);
	})
	window.Form=Form;
})(jQuery)

//根据条件查询已经上架课程信息
function queryClassTypesShelves(datas){
	//查询课程信息
	$.ajax({
		url : rootPath + "/shelvesCourse/findShelvesCourseByapge",
		type : "post",
		data:datas,
		beforeSend:function(XMLHttpRequest){
			$(".loading").show();
			$(".loading-bg").show();
		},
		success : function(result) {
			$("#ShelvesCourseDetailList").html(result);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
			$(".loading-bg").hide();
		}
	});
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


