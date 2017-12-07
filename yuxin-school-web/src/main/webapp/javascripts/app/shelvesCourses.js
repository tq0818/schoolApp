/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function($){
    //请求状态
    var isLoading = false;
	var Form={
			init : function(){
				var $this=this;
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
			submitBatchClassApp:function(){
				var data={};
				var grade_ids='';
				$("#gradeList").find("a").each(function (i) {
	                if ($(this).hasClass('active')) {
	                	if(grade_ids==''){
	                		if($(this).attr("id")=='all'||$(this).attr("id")==undefined||$(this).attr("id")=='') return;
	                		grade_ids= $(this).attr("id");
	                	}else{
	                		grade_ids+=','+$(this).attr("id");
	                	}
	                }
	            });
				
				var classids='';
				$("#tableList").find(".signUpMany").each(function(i){
					if($(this).prop("checked")){
						if(classids==''){
							if($(this).val()=='all'||$(this).val()==undefined||$(this).val()=='') return;
							classids=$(this).val();
						}else
							classids+=','+$(this).val();
					}
				});
				if(grade_ids==''){
					$.msg('未选择年级');
					return;
				}
                //请求状态为请求中则返回
                if(isLoading){
                    alert("网络繁忙，请稍等！");
                    return;
                }
                //改变请求状态
                isLoading = true;
				data.gradeIds=grade_ids;
				data.appShelvesIds=classids;
				$.ajax({
	                url: rootPath + "/specialModel/insertOrupdateTuiJian",
	                type: "post",
	                data: data,
	                beforeSend: function (XMLHttpRequest) {
	                    $(".loading").show();
	                    $(".loading-bg").show();
	                },
	                success: function (jsonData) {
	                	if(jsonData){
	                		$.msg('保存成功');
	                		$("#gradeList").find("a").each(function (i){
	                			$(this).removeClass('active');
	        	            });
	                		$('.popupContainerRecommendation').hide();
	                        $('.popupOpacity').hide();
	                	}else{
	                		$.msg('保存失败');
	                		$('.popupContainerRecommendation').show();
	                        $('.popupOpacity').show();
	                	}

                        //重置请求状态
                        isLoading = false;
	                },
	                complete: function (XMLHttpRequest, textStatus) {
	                    $(".loading").hide();
	                    $(".loading-bg").hide();
	                }
	            });
			},
			updateFirstRecommend:function(obj,str){
				var app_shelves_id=$(obj).attr('id');
				var textId="recommendationNum_"+app_shelves_id;
				var sort=$("#"+textId).val();
				if(app_shelves_id=='all'||app_shelves_id==undefined||app_shelves_id==''){
					return;
				}
				if(str=="update"){
					var r = /^\+?[1-9][0-9]*$/;
					var flag=r.test(sort); 
					if(!flag){
						$.msg('输入数字必须为整数');
						return;
					}
				}
				if(str=="delete"){
					sort=""
				}
				var data={};
				data.appShelvesId=app_shelves_id;
				data.sort=sort;
                //请求状态为请求中则返回
                if(isLoading){
                    alert("网络繁忙，请稍等！");
                    return;
                }
                //改变请求状态
                isLoading = true;
				$.ajax({
	                url: rootPath + "/specialModel/updateFirstRecommendationNum",
	                type: "post",
	                data: data,
	                beforeSend: function (XMLHttpRequest) {
	                    $(".loading").show();
	                    $(".loading-bg").show();
	                },
	                success: function (jsonData) {
	                	if(jsonData){
	                		$.msg('保存成功');
	                		if(str=="delete"){
	                			$("#"+textId).val("");
	                		}
	                	}else{
	                		$.msg('保存失败');
	                	}
                        //重置请求状态
                        isLoading = false;
	                },
	                complete: function (XMLHttpRequest, textStatus) {
	                    $(".loading").hide();
	                    $(".loading-bg").hide();
	                }
	            });
			},
			searchCount: function(){
	        	$("#selectCounts").val($("#selectCount").val());
	        	Form.queryshelvesCoursesApp();
	        },

			queryshelvesCoursesApp : function(page){

	            var datas = {};
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
	                datas.subjectid = subjectName;
	            }
	            $("#kwonProId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    knowledgeName = $(this).attr("data-code");
	                }
	            });
	            if (knowledgeName != 'all') {
	                datas.knowledgeProid = knowledgeName;
	            }
	            $("#knowId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
						knowledgeName = $(this).attr("data-code");
	                }
	            });
	            if (knowledgeName != 'all') {
	                datas.knowledgeid = knowledgeName;
	            }
	            $("#stageId").find("a").each(function (i) {
	                if ($(this).hasClass('btn-success')) {
	                    stageName = $(this).attr("data-code");
	                }
	            });
	            if (stageName != 'all') {
	                datas.stageid = stageName;
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
	            datas.modelCode=$("#modelCode").val();
	            datas.modelId=$("#modelId").val();
	            $(".user-list").find("table").find("tr:gt(0)").remove();

                //请求状态为请求中则返回
                if(isLoading){
                    alert("网络繁忙，请稍等！");
                    return;
                }
                //改变请求状态
                isLoading = true;
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
	                		if(stu.sort==null) stu.sort='';
	                		if(stu.name==null) stu.name="";
	                		if(stu.gradeName==null) stu.gradeName="";
	                		if(stu.subjectName==null) stu.subjectName="";
	                		if(stu.knowProName==null) stu.knowProName="";
	                		if(stu.knowName==null) stu.knowName="";
	                		if(stu.stageName==null) stu.stageName="";
	                		if(stu.typeName==null) stu.typeName="";
	                		if(stu.shelvesTime==null) stu.shelvesTime="";
	                		if(stu.lessonTime==null) stu.lessonTime="";
	                		if(stu.actualNum==null) stu.actualNum="";
	                		if(stu.realPrice==null) stu.realPrice="";
	                		if(stu.salePrice==null) stu.salePrice="";
                            $(".user-list").find("table").append('<tr>'+
                                    '<td><input type="checkbox" class="signUpMany" value="'+stu.shelves_id+'"></td>'+
                                    /*'<td><img src="'+stu.coverUrl+'" alt="" class="shelvesIcon"></td>'+*/
                                    '<td>'+stu.name+'</td>'+
                                    '<td>'+stu.gradeName+'</td>'+
                                    '<td>'+stu.subjectName+'</td>'+
                                    '<td>'+stu.knowProName+'</td>'+
                                    '<td>'+stu.knowName+'</td>'+
                                    /*'<td>'+stu.stageName+'</td>'+
                                    '<td>'+stu.typeName+'</td>'+*/
                                    '<td>'+stu.shelvesTime+'</td>'+
                                    '<td>'+stu.lessonTime+'</td>'+
                                    '<td>'+stu.actualNum+'</td>'+
                                    '<td>'+stu.realPrice+'</td>'+
                                    '<td>'+stu.salePrice+'</td>'+
                                    '<td>'+
                                        '<input type="text" class="recommendationNum" id="recommendationNum_'+stu.shelves_id+'" value="'+stu.sort+'">'+
                    					'<i class="btn-ico btn-gou" id="'+stu.shelves_id+'" onclick="Form.updateFirstRecommend(this,\'update\')">√</i>'+
                    					'<i class="btn-ico btn-cha" id="'+stu.shelves_id+'" onclick="Form.updateFirstRecommend(this,\'delete\')">X</i>'+
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
	                                        Form.queryshelvesCoursesApp(pageNo);
	                                    }
	                                });
	                            $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
//	                            $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:Form.searchCount()">'+
//	                					' <option value="10">10</option>'+
//	                					' <option value="20">20</option>'+
//	                					' <option value="30">30</option>'+
//	                					' <option value="50">50</option>'+
//	                					' <option value="100">100</option>'+
//	                					' </select> 条   ');
	                            $("#selectCount").val($("#selectCounts").val());
	                        } else {
	                            $(".pagination").html('');
	                        }

                        //重置请求状态
                        isLoading = false;
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
				gradeid = '',
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
					Form.queryshelvesCoursesApp(1);
					return;
				}else if("gradeId"==typeStr  && 'all'==id){
					$("#subjectId").html(allHtml.replace("allToAll","subjectId"));
					$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
					$("#knowId").html(allHtml.replace("allToAll","kwonId"));
					//查询课程信息
					fillCategory(datas);
					fillStage(datas);
					fillType(datas);
					Form.queryshelvesCoursesApp(1);
					return;
				}else if("subjectId"==typeStr  && 'all'==id){
					$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
					$("#knowId").html(allHtml.replace("allToAll","kwonId"));
					//查询课程信息
					fillGrade(datas);
					fillStage(datas);
					fillType(datas);
					Form.queryshelvesCoursesApp(1);
					return;
				}else if("kwonProId"==typeStr  && 'all'==id){
					$("#knowId").html(allHtml.replace("allToAll","knowId"));
					//查询课程信息
					fillSubject(datas);
					fillStage(datas);
					fillType(datas);
					Form.queryshelvesCoursesApp(1);
					return;
				}else if("knowId"==typeStr  && 'all'==id){
					fillKnowPro(datas);
					fillStage(datas);
					fillType(datas);
					Form.queryshelvesCoursesApp(1);
					return;
				}else if("stageId"==typeStr || "typeId"==typeStr){
					fillKnowPro(datas);
					fillGrade(datas);
					fillSubject(datas);
					fillKnowPro(datas);
					fillKnow(datas);
					fillStage(datas);
					fillType(datas);
					Form.queryshelvesCoursesApp(1);
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
							$("#knowId").html(allHtml.replace("allToAll","knowId"));
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
						}else if("gradeId"==typeStr){
							//跟新科目
							html +=allHtml.replace("allToAll","subjectId");
							for(var i=0;i<data.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'subjectId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
							}
							$("#subjectId").html(html);
							$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
							$("#knowId").html(allHtml.replace("allToAll","knowId"));
							//查询课程信息
							fillGrade(datas);
							fillStage(datas);
							fillType(datas);
						}else if("subjectId"==typeStr){
							//更新知识点专题
							html +=allHtml.replace("allToAll","kwonProId");
							for(var i=0;i<data.length;i++){
								html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'kwonProId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
							}
							$("#kwonProId").html(html);
							$("#knowId").html(allHtml.replace("allToAll","knowId"));
							//查询课程信息
							fillSubject(datas);
							fillStage(datas);
							fillType(datas);
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
						}else if("knowId"==typeStr){
							//查询课程信息
							fillKnow(datas);
							fillStage(datas);
							fillType(datas);
						}
						else if("stageId"==typeStr){
							//查询课程信息
							fillCategory(datas);
							fillGrade(datas);
							fillSubject(datas);
							fillKnowPro(datas);
							fillKnow(datas);
							fillStage(datas);
							fillType(datas);
						}
						else if("typeId"==typeStr){
							//查询课程信息
							fillCategory(datas);
							fillGrade(datas);
							fillSubject(datas);
							fillKnowPro(datas);
							fillKnow(datas);
							fillStage(datas);
							fillType(datas);
						}
						Form.queryshelvesCoursesApp(1);
					}
				});
			},
		}

	$(document).ready(function(){		
		Form.init();
		var datas = {};
		fillCategory(datas);
		fillGrade(datas);
		fillSubject(datas);
		fillKnowPro(datas);
		fillKnow(datas);
		fillStage(datas);
		fillType(datas);
		Form.queryshelvesCoursesApp(1);
	})
	window.Form=Form;
})(jQuery)


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


