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
				var itemOneId="";
                var itemOneCode="";
				$("#itemOneCodeList").find("a").each(function(){
					var st=$(this).hasClass("btn-success");
					if(st){
							itemOneId=$(this).attr("ids");
                            itemOneCode =$(this).attr("data-code");
						}
				});
                $("#itemFourthCodeList").off().delegate("a","click",function(){
                    $(this).toggleClass("btn-success");
                });
				//this.queryItemSecond(itemOneCode,itemOneId);
				
				//判断是否有上架学科(学科)
	    		$("#itemOneCodeList").find("a").each(function(){
    				if($(this).attr("data-code")==$("#one").val()){
    					$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
    				}
    			});
                $("#itemSecondCodeList").find("a").each(function(){
                    if($(this).attr("data-code")==$("#tow").val()){
                        $(this).addClass("btn-success").siblings('a').removeClass('btn-success');
                    }
                });
                $("#itemThirdCodeList").find("a").each(function(){
                    if($(this).attr("data-code")==$("#three").val()){
                        $(this).addClass("btn-success").siblings('a').removeClass('btn-success');
                    }
                });
                $("#itemFouthCodeList").find("a").each(function(){
                    if($(this).attr("data-code")==$("#four").val()){
                        $(this).addClass("btn-success").siblings('a').removeClass('btn-success');
                    }
                });
	    		$(".upload-layer").on("click","li",function(){
	    			if($(this).hasClass('b1')){
	    				$this.addClassType('live');
	    			}
	    			if($(this).hasClass('b2')){
	    				$this.addClassType('video');
	    			}
	    			if($(this).hasClass('b3')){
	    				$this.addClassType('face');
	    			}
	    			if($(this).hasClass('b4')){
	    				$this.addClassType('togther');
	    			}
	    			if($(this).hasClass('b5')){
	    				$this.addClassType('other');
	    			}
	    		})
                this.queryAllCommdityByItemNew(1);
			},
			queryshelvesCoursesApp : function(page){
				var datas = {"page":page};
				var categoryName = '',
				gradeName = ''
				subjectName = '',
				knowledgeName = '',
				knowledgeProName = '',
				stageName = '',
				typeCode = '';
				$("#categoryNameList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						categoryName=$(this).attr("data-code");
					}
				});
				if(categoryName!='all'){
                    datas.categoryName=categoryName;
				}
				$("#gradeNameList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						gradeName=$(this).attr("data-code");
					}
				});
				if(gradeName!='all'){
					datas.gradeName=gradeName;
				}
				$("#subjectNameList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						subjectName=$(this).attr("data-code");
					}
				});
				if(subjectName!='all'){
					datas.subjectName=subjectName;
				}
				$("#knowledgeNameList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						knowledgeName=$(this).attr("data-code");
					}
				});
				if(knowledgeName!='all'){
					datas.knowledgeName=knowledgeName;
				}
				$("#knowledgeProNameList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						knowledgeProName=$(this).attr("data-code");
					}
				});
				if(knowledgeProName!='all'){
					datas.knowledgeProName=knowledgeProName;
				}
				$("#stageNameList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						stageName=$(this).attr("data-code");
					}
				});
				if(stageName!='all'){
					datas.stageName=stageName;
				}
				$("#typeCodeList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						typeCode=$(this).attr("data-code");
					}
				});
				if(typeCode!='all'){
					datas.typeCode=typeCode;
				}
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
			
			
			
			
			
			
			
			
			
			},
			
			queryAllSysApp : function(id){

				$.ajax({
					url : rootPath + "/appNewClasses/shelvesCourses",
					type : "get",
//					data:datas,
				    data : {"id" : id},
					success : function(result) {
						/*$("#itemOneCodeList").append(result.firstList);
						$("#itemSecondCodeList").append(result.thirdList);
						$("#itemThirdCodeList").append(result.fourthList);
						$("#flagList").append(result.secondList);*/
						var aHtml = "";
//						if(result.thirdList.length > 0){
						for(var i = 0;i<result.thirdList.length;i++ ){
							aHtml+='<a href="javascript:Form.queryAllSysApp();" data-code="" class="btn btn-mini btn-default">1</a>';
							$("#itemSecondCodeList").append(aHtml);
						}
//						}
						
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			queryItemSecond : function (code,id){
				if(id==null){
                    $("#itemOneCodeList").find("a").each(function(){
                        var st=$(this).hasClass("btn-success");
                        if(st){
                            id=$(this).attr("data-id");
                            code=$(this).attr("value");
                        }
                    });
                }
                $("#itemSecondCodeList").html('');
                $("#itemSecondCodeList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+code+");' value='all' class='btn btn-mini btn-default btn-success'>全部</a>");
                $.ajax({
                    url : rootPath + "/itemTree/queryItemSecond",
                    type : "post",
                    data : {pid:id},
                    dataType : "json",
                	success : function(result) {
                        $.each(result,function(i,item){
                                $("#itemSecondCodeList").append("<a href='javascript:Form.queryAllCommdityByItem(1,"+code+","+item.itemCode+");' data-code='"+item.itemCode+"' ids='"+item.id+"' class='btn btn-mini btn-default'>"+item.itemName+"</a>");
                        });
						//判断是否有上架学科(学科)
						$("#itemSecondCodeList").find("a").each(function(){
		    				if($(this).attr("data-code")==$("#two").val()){
		    					$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
		    				}
		    			});
					}
				});
			},
        getietmList: function (dom){
            var itemCode = [];
            if(dom.children()){
                var checkitem = dom.children(".btn-success")
                $.each(checkitem,function(i,v){
                    itemCode.push($(v).attr("data-code"));
                });
            }
            return itemCode.join(",");
        },
        setietmList:function (list){
            $.each(list,function(i,v){
                $("a[data-code="+v+"]",dom).addClass("btn-success");
            });
        },

    queryAllCommdityByItem : function(page,id,itemSecondId,status,lab,labTwo){
				var labSec="";
				if(id==null){
					$("#itemOneList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							id=cid;
						}
					});
				}
				if(itemSecondId==null){
					$("#itemSecondList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							itemSecondId=cid;
						}
					});
				}else{
					
				}
				// Form.querylablesList(null,null,"change");
				if(status==null){
					$("#statusList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							status=cid;
						}
					});
				}
				if(lab==null){
					$("#labelLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							lab=cid;
						}
					});
				}
				if(labTwo && labTwo!=null){
					labSec=labTwo;
				}else{
					$("#labelSecondLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							labSec=cid;
						}
					});
				}

				var faceFlag = 0;
				var liveFlag = 0;
				var videoFlag = 0;
				var remoteFlag = 0;
				$("#flagList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						var cid=$(this).attr("ids");
						switch(cid){
							case "IS_LIVE":liveFlag = 1;break;
							case "IS_VIDEO":videoFlag = 1;break;
							case "IS_FACE":faceFlag = 1;break;
							case "IS_REMOTE":remoteFlag = 1;break;
							default:break;
						}
					}
				});
				$.ajax({
					url : rootPath + "/simpleClasses/showAllclassType",
					type : "post",
					data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec,
						"faceFlag":faceFlag,"liveFlag":liveFlag,"videoFlag":videoFlag,"remoteFlag":remoteFlag},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#commodityDetailList").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			queryCommdityByFlag : function(page,id,itemSecondId,status,lab,labTwo){
				var labSec="";
				if(id==null){
					$("#itemOneList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							id=cid;
						}
					});
				}
				if(itemSecondId==null){
					$("#itemSecondList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							itemSecondId=cid;
						}
					});
				}else{

				}
				// Form.querylablesList(null,null,"change");
				if(status==null){
					$("#statusList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							status=cid;
						}
					});
				}
				if(lab==null){
					$("#labelLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							lab=cid;
						}
					});
				}
				if(labTwo && labTwo!=null){
					labSec=labTwo;
				}else{
					$("#labelSecondLists").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							labSec=cid;
						}
					});
				}
				var faceFlag = 0;
				var liveFlag = 0;
				var videoFlag = 0;
				var remoteFlag = 0;
				$("#flagList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						var cid=$(this).attr("ids");
						switch(cid){
							case "IS_LIVE":liveFlag = 1;break;
							case "IS_VIDEO":videoFlag = 1;break;
							case "IS_FACE":faceFlag = 1;break;
							case "IS_REMOTE":remoteFlag = 1;break;
							default:break;
						}
					}
				});
	//				window.Form.querylablesList(id);
				$.ajax({
					url : rootPath + "/simpleClasses/showAllclassType",
					type : "post",
					data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec,
						"faceFlag":faceFlag,"liveFlag":liveFlag,"videoFlag":videoFlag,"remoteFlag":remoteFlag},
					beforeSend:function(XMLHttpRequest){
						$(".loading").show();
						$(".loading-bg").show();
					},
					success : function(result) {
						$("#commodityDetailList").html(result);
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
						$(".loading-bg").hide();
					}
				});
			},
			queryAllCommdityByItemNew : function(page){
				var labSec="";
                var datas = {"page":page};
				var itemOneCode,itemSecondCode,itemThirdCode,itemFourthCode,status;
					$("#itemOneCodeList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
                            itemOneCode=$(this).attr("data-code");
						}
					});
					if(itemOneCode!='all'){
                        datas.itemOneCode=itemOneCode;
					}
					$("#itemSecondCodeList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
                            itemSecondCode=$(this).attr("data-code");
						}
					});
                if(itemSecondCode!='all'){
                    datas.itemSecondCode=itemSecondCode;
                }
                $("#itemThirdCodeList").find("a").each(function(i){
                    if($(this).hasClass('btn-success')){
                        itemThirdCode=$(this).attr("data-code");
                    }
                });
                if(itemThirdCode!='all'){
                    datas.itemThirdCode=itemThirdCode;
                }
                itemFourthCode = this.getietmList($("#itemFourthCodeList"));
                if(itemFourthCode.length>0){
                    datas.itemFourthCode=itemFourthCode;
                }
				if(status==null){
					$("#statusList").find("a").each(function(i){
						if($(this).hasClass('btn-success')){
							var cid=$(this).attr("ids");
							status=cid;
						}
					});
				}
                if(status!='all'){
                    datas.publishStatus=status;
                }

				var faceFlag = 0;
				var liveFlag = 0;
				var videoFlag = 0;
				var remoteFlag = 0;
				var flag;
				$("#flagList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						flag=$(this).attr("ids");
						switch(flag){
							case "IS_LIVE":liveFlag = 1;break;
							case "IS_VIDEO":videoFlag = 1;break;
							case "IS_FACE":faceFlag = 1;break;
							case "IS_REMOTE":remoteFlag = 1;break;
							default:break;
						}
					}
				});
                    if(flag!='all'){
                        datas.liveFlag = liveFlag;
                        datas.videoFlag = videoFlag;
                        datas.faceFlag = faceFlag;
                        datas.remoteFlag = remoteFlag;
                }

				$.ajax({
					url : rootPath + "/simpleClasses/showAllclassType",
					type : "post",
					data:datas,
					// data : {"page" : page,"itemOneId" : id,"itemSecondId" : itemSecondId,"publishStatus" : status,"itemTag":lab,"itemTag2":labSec,
					// 	"faceFlag":faceFlag,"liveFlag":liveFlag,"videoFlag":videoFlag,"remoteFlag":remoteFlag},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#commodityDetailList").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			queryCommodityByName : function(page){
				var name=$("#classTypeName").val();
				$.ajax({
					url : rootPath + "/simpleClasses/showAllclassType",
					type : "post",
					data : {"page" : page,"name":name},
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(result) {
						$("#commodityDetailList").html(result);
					},
					 complete:function(XMLHttpRequest,textStatus){
							$(".loading").hide();
				            $(".loading-bg").hide();
				     }
				});
			},
			stopOnsale : function(id){
				$.confirm("您确定要下架此课程?下架后学员将无法再报名此课程。",function(a){
					if(a==true){
						$.ajax({
							url : rootPath + "/simpleClasses/StopSale",
							type : "post",
							data : {"id":id,"publishStatus":'CLASS_STOP_SALE'},
							success : function(result) {
								var st="";
								$("#statusList").find("a").each(function(i){
									if($(this).hasClass('btn-success')){
										var cid=$(this).attr("ids");
										st=cid;
									}
								});
								if(st!=""){
									//$("#commodityLi"+id).remove();
									$("#commodityLi"+id).find("i:first").text("停售").css({"background-color":"rgba(231,31,26,0.8)","color":"white"});;
									$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("上架").attr("href","javascript:Form.classTypeOnsale("+result.id+")");
								}else{
									$("#itemOneList").find("a").each(function(i){
										if(result.itemOneId==$(this).attr("ids")){
											$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
										}
									});
									$("#itemSecondList").find("a").each(function(i){
										if(result.itemOneId==$(this).attr("ids")){
											$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
										}
									});
									$("#commodityLi"+id).find("i:first").text("停售").css({"background-color":"rgba(231,31,26,0.8)","color":"white"});;
									$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("上架").attr("href","javascript:Form.classTypeOnsale("+result.id+")");
								}
								
								//alert(result);
								Form.queryAllCommdityByItem(1);
							}
						});
					}else{
						return;
					}
				});
			},
			deleteClassType : function(id){
				$.confirm("您确定要删除此课程?",function(a){
					if(a==true){
						location.href= rootPath + "/simpleClasses/deleteClassType/"+id;
					}else{
						return;
					}
				})
			},
			classTypeOnsale : function(id){
				$.ajax({
					url : rootPath + "/simpleClasses/classTypeonSale",
					type : "post",
					data : {"id":id,"publishStatus":'CLASS_ON_SALE'},
					success : function(result) {
						var st="";
						$("#statusList").find("a").each(function(i){
							if($(this).hasClass('btn-success')){
								var cid=$(this).attr("ids");
								st=cid;
							}
						});
						if(st!=""){
							//$("#commodityLi"+id).remove();
							$("#commodityLi"+id).find("i:first").text("在售").css({"background-color":"rgba(194,235,235,.8)","color":"black"});
							$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("下架").attr("href","javascript:Form.stopOnsale("+result.id+")");
						}else{
							$("#itemOneList").find("a").each(function(i){
								if(result.itemOneId==$(this).attr("ids")){
									$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
								}
							});
							$("#itemSecondList").find("a").each(function(i){
								if(result.itemOneId==$(this).attr("ids")){
									$(this).addClass("btn-success").siblings('a').removeClass('btn-success');
								}
							});
							$("#commodityLi"+id).find("i:first").text("在售").css({"background-color":"rgba(194,235,235,.8)","color":"black"});
							$("#commodityLi"+id).find("div.btns").find("a:eq(1)").text("下架").attr("href","javascript:Form.stopOnsale("+result.id+")");
						}
						Form.queryAllCommdityByItem(1);
					}
				});
			},
			showClassTypeDetail : function(id,typeCode){
				var itemOneId="";
				// $("#itemOneList").find("a").each(function(i){
				// 	if($(this).hasClass('btn-success')){
				// 		itemOneId=$(this).attr("ids");
				// 	}
				// });
				// if(itemOneId==""){
				// 	alert("请先设置学科!");
				// 	return;
				// }
				$("#classTypeId").val(id);
				$("#typeCode").val(typeCode);
				$("#myForm").attr("action",rootPath+"/simpleClasses/showClassTypeDetail").submit();
			},
			editClassType : function(id){
				var itemOneId="";
				var lab="";
				$("#itemOneList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						itemOneId=$(this).attr("ids");
					}
				});
				//得到课程所属标签
				$("#lab"+id).find("a").each(function(i){
					lab+=$(this).attr("mark")+",";
				});
				//console.log(lab);
				// if(itemOneId==""){
				// 	alert("请先设置学科!");
				// 	return;
				// }
				$("#classTypeId").val(id);
				$("#lab").val(lab);
				$("#myForm").attr("action",rootPath+"/editSimpleCourse/editClassTypeMessage").submit();
			},
			show : function(){
				$("#divDisplay").css("display","block");
			},
			close : function(){
				$("#divDisplay").css("display","none");
			},
			showSave : function(id){
				$("#com"+id).css("display","block");
			},
			closeSave : function(id){
				$("#com"+id).css("display","none");
			},
			collectShop : function(id){
				var status=$("#com"+id).attr("marks");
				if(status=="0"){
					status=1;
				}else{
					status="0";
				}
				$.ajax({
					url : rootPath + "/simpleClasses/changClassTypeCollect",
					type : "post",
					data : {"id":id,"recommendFlag":status},
					success : function(result) {
						if(result.recommendFlag==1){
							$("#com"+id).text('取消推荐');
							$("#com"+id).attr("marks",1);
						}else{
							$("#com"+id).text('加入推荐');
							$("#com"+id).attr("marks",0);
						}
					}
				});
			},
			addClassType : function(mark){
				$("#lab").val(mark);
				var itemOneId="";
				$("#itemOneList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						itemOneId=$(this).attr("ids");
						$("#oneId").val($(this).attr("ids"));
					}
				});
				$("#itemSecondList").find("a").each(function(i){
					if($(this).hasClass('btn-success')){
						$("#twoId").val($(this).attr("ids"));
					}
				});
				// if(itemOneId==""){
				// 	alert("请先设置学科!");
				// 	return;
				// }
				var count=0;
				$("#itemSecondList").find("a.btn").each(function(i){
					if($(this).hasClass("btn-default")){
						count++;
					}
				});
				// if(count<=0){
				// 	alert("请先设置学科小类!");
				// 	return;
				// }
				$("#myForm").attr("action",rootPath+"/simpleClasses/addClassType").submit();
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
						else if("#typeId"==typeStr){
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
					}
				});
			},
		}
	$(document).ready(function(){		
		Form.init();
		Form.queryshelvesCoursesApp();
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
	$("#knowId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			knowId=$(this).attr("data-code");
		}
	});
	if(knowId!='all'){
		datas.knowId=knowId;
	}
}

function fillStage(datas){
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
	$("#typeId").find("a").each(function(i){
		if($(this).hasClass('btn-success')){
			typeCode=$(this).attr("data-code");
		}
	});
	if(typeCode!='all'){
		datas.typeCode=typeCode;
	}
}


