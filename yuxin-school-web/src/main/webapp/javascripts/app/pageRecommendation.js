/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function ($) {
    var Forms = {
        init: function () {
            var $this = this;
        },
        queryshelvesCoursesApp: function (page) {
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
            $.ajax({
                url: rootPath + "/specialModel/getModelListByIds",
                type: "post",
                data: datas,
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (result) {
                    $("#modelList").html(result);
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        },
        deleteClassType: function (id) {
            $.confirm("您确定要删除此课程?", function (a) {
                if (a == true) {
                    location.href = rootPath + "/simpleClasses/deleteClassType/" + id;
                } else {
                    return;
                }
            })
        },
        showAllShelvesClssType: function (id, typeStr) {
			var datas = {};
			var categoryid = '',
			gradeid = ''
			subjectid = '',
			knowledgeProid = '',
			knowledgeid = '',
			stageid = '',
			typeCode = '';
			datas.knowledgeid=knowledgeid;
			var allHtml = '<a href="javascript:Form.showAllShelvesClssType(\'all\',\'allToAll\');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>';
			if("gradeId"==typeStr  && 'all'==id){
				$("#subjectId").html(allHtml.replace("allToAll","subjectId"));
				$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
				$("#knowId").html(allHtml.replace("allToAll","kwonId"));
				//查询课程信息
				return;
			}else if("subjectId"==typeStr  && 'all'==id){
				$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
				$("#knowId").html(allHtml.replace("allToAll","kwonId"));
				//查询课程信息
				return;
			}else if("kwonProId"==typeStr  && 'all'==id){
				$("#knowId").html(allHtml.replace("allToAll","knowId"));
				//查询课程信息
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
						if(categoryid!='all'){
		                    datas.categoryid=categoryid;
						}
					}else if("gradeId"==typeStr){
						//跟新科目
						html +=allHtml.replace("allToAll","subjectId");
						for(var i=0;i<data.length;i++){
							html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'subjectId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
						}
						$("#subjectId").html(html);
						$("#kwonProId").html(allHtml.replace("allToAll","kwonProId"));
						$("#kwonId").html(allHtml.replace("allToAll","kwonId"));
						$("#courseCaId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								categoryid=$(this).attr("data-code");
							}
						});
						if(categoryid!='all'){
		                    datas.categoryid=categoryid;
						}
						$("#gradeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								gradeid=$(this).attr("data-code");
							}
						});
						if(gradeid!='all'){
		                    datas.gradeid=gradeid;
						}
						$("#stageId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								stageid=$(this).attr("data-code");
							}
						});
						if(stageid!='all'){
							datas.stageid=stageid;
						}
						$("#typeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								typeCode=$(this).attr("data-code");
							}
						});
						if(typeCode!='all'){
							datas.typeCode=typeCode;
						}
					}else if("subjectId"==typeStr){
						//更新知识点专题
						html +=allHtml.replace("allToAll","kwonProId");
						for(var i=0;i<data.length;i++){
							html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'kwonProId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
						}
						$("#kwonProId").html(html);
						$("#kwonId").html(allHtml.replace("allToAll","kwonId"));
						$("#courseCaId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								categoryid=$(this).attr("data-code");
							}
						});
						if(categoryid!='all'){
		                    datas.categoryid=categoryid;
						}
						$("#gradeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								gradeid=$(this).attr("data-code");
							}
						});
						if(gradeid!='all'){
		                    datas.gradeid=gradeid;
						}
						$("#subjectId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								subjectid=$(this).attr("data-code");
							}
						});
						if(subjectid!='all'){
		                    datas.subjectid=subjectid;
						}
						$("#stageId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								stageid=$(this).attr("data-code");
							}
						});
						if(stageid!='all'){
							datas.stageid=stageid;
						}
						$("#typeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								typeCode=$(this).attr("data-code");
							}
						});
						if(typeCode!='all'){
							datas.typeCode=typeCode;
						}
					}else if("kwonProId"==typeStr){
						//更新知识点
						html +=allHtml.replace("allToAll","knowId");
						for(var i=0;i<data.length;i++){
							html +='<a href="javascript:Form.showAllShelvesClssType(\''+data[i].id+'\',\'knowId\');" data-code=\''+data[i].id+'\' class="btn btn-mini btn-default">'+data[i].name+'</a>';
						}
						$("#knowId").html(html);
						$("#courseCaId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								categoryid=$(this).attr("data-code");
							}
						});
						if(categoryid!='all'){
		                    datas.categoryid=categoryid;
						}
						$("#gradeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								gradeid=$(this).attr("data-code");
							}
						});
						if(gradeid!='all'){
		                    datas.gradeid=gradeid;
						}
						$("#subjectId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								subjectid=$(this).attr("data-code");
							}
						});
						if(subjectid!='all'){
		                    datas.subjectid=subjectid;
						}
						$("#kwonProId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								knowledgeProid=$(this).attr("data-code");
							}
						});
						if(knowledgeProid!='all'){
		                    datas.knowledgeProid=knowledgeProid;
						}
						$("#stageId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								stageid=$(this).attr("data-code");
							}
						});
						if(stageid!='all'){
							datas.stageid=stageid;
						}
						$("#typeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								typeCode=$(this).attr("data-code");
							}
						});
						if(typeCode!='all'){
							datas.typeCode=typeCode;
						}
					}else if("knowId"==typeStr){
						$("#courseCaId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								categoryid=$(this).attr("data-code");
							}
						});
						if(categoryid!='all'){
		                    datas.categoryid=categoryid;
						}
						$("#gradeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								gradeid=$(this).attr("data-code");
							}
						});
						if(gradeid!='all'){
		                    datas.gradeid=gradeid;
						}
						$("#subjectId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								subjectid=$(this).attr("data-code");
							}
						});
						if(subjectid!='all'){
		                    datas.subjectid=subjectid;
						}
						$("#kwonProId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								knowledgeProid=$(this).attr("data-code");
							}
						});
						if(knowledgeProid!='all'){
		                    datas.knowledgeProid=knowledgeProid;
						}
						$("#knowId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								knowledgeid=$(this).attr("data-code");
							}
						});
						if(knowledgeid!='all'){
		                    datas.knowledgeid=knowledgeid;
						}
						$("#stageId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								stageid=$(this).attr("data-code");
							}
						});
						if(stageid!='all'){
							datas.stageid=stageid;
						}
						$("#typeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								typeCode=$(this).attr("data-code");
							}
						});
						if(typeCode!='all'){
							datas.typeCode=typeCode;
						}
					}
					else if("#stageId"==typeStr){
						$("#courseCaId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								categoryid=$(this).attr("data-code");
							}
						});
						if(categoryid!='all'){
		                    datas.categoryid=categoryid;
						}
						$("#gradeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								gradeid=$(this).attr("data-code");
							}
						});
						if(gradeid!='all'){
		                    datas.gradeid=gradeid;
						}
						$("#subjectId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								subjectid=$(this).attr("data-code");
							}
						});
						if(subjectid!='all'){
		                    datas.subjectid=subjectid;
						}
						$("#kwonProId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								knowledgeProid=$(this).attr("data-code");
							}
						});
						if(knowledgeProid!='all'){
		                    datas.knowledgeProid=knowledgeProid;
						}
						$("#knowId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								knowledgeid=$(this).attr("data-code");
							}
						});
						if(knowledgeid!='all'){
		                    datas.knowledgeid=knowledgeid;
						}
						$("#stageId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								stageid=$(this).attr("data-code");
							}
						});
						if(stageid!='all'){
							datas.stageid=stageid;
						}
						$("#typeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								typeCode=$(this).attr("data-code");
							}
						});
						if(typeCode!='all'){
							datas.typeCode=typeCode;
						}
					else if("#typeId"==typeStr){
						$("#courseCaId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								categoryid=$(this).attr("data-code");
							}
						});
						if(categoryid!='all'){
		                    datas.categoryid=categoryid;
						}
						$("#gradeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								gradeid=$(this).attr("data-code");
							}
						});
						if(gradeid!='all'){
		                    datas.gradeid=gradeid;
						}
						$("#subjectId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								subjectid=$(this).attr("data-code");
							}
						});
						if(subjectid!='all'){
		                    datas.subjectid=subjectid;
						}
						$("#kwonProId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								knowledgeProid=$(this).attr("data-code");
							}
						});
						if(knowledgeProid!='all'){
		                    datas.knowledgeProid=knowledgeProid;
						}
						$("#knowId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								knowledgeid=$(this).attr("data-code");
							}
						});
						if(knowledgeid!='all'){
		                    datas.knowledgeid=knowledgeid;
						}
						$("#stageId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								stageid=$(this).attr("data-code");
							}
						});
						if(stageid!='all'){
							datas.stageid=stageid;
						}
						$("#typeId").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								typeCode=$(this).attr("data-code");
							}
						});
						if(typeCode!='all'){
							datas.typeCode=typeCode;
						}
					}
				}
				   //查询课程信息
		          queryshelvesCoursesApp(1);
				}
			});
        }
    }
    $(document).ready(function () {
        Forms.init();
        Forms.queryshelvesCoursesApp(1);
    });
    window.Forms = Forms;
})(jQuery)