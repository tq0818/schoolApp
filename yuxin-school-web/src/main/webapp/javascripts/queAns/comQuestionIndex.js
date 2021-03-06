/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
var oneId="";
var twoId="";
var jz = "";
var questionName ="";
var queId = "";//问题id（加载评论用）
var types = "";//是老师，还是管理员（加载评论用）
var questionUserId = "";//提问者的id，用户后边判断是否是自己的提问回答，是则不显示
function selOneAns (pageNo){
	console.log(queId+"==="+types+"===="+questionUserId);
	$.ajax({
		url : rootPath + "/questionanswermanager/selAns",
		type : "post",
		data : {"questionId":queId,"types":types,"page":pageNo,"pageSize":"5","questionUserId":questionUserId},
		beforeSend:function(XMLHttpRequest){
            $(".loading").show();
            $(".loading-bg").show();
        },
		success : function(data) {
			$(".pl"+queId+"").html("").html(data);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	});
}
/*一级二级回复 js begin*/
var req = null;
$(function(){
	$(document).off("click").on("click",".ore",function(){
		//回复
		var parent = $(this).parents(".oneanswer");
		parent.find(".onetext").slideDown(200,function(){
			$(this).find("textarea").focus();
		});
	})
	.on("click",".btn-oneca",function(){
		//取消回复
		var parent = $(this).parents(".oneanswer");
		parent.find(".onetext").slideUp(200,function(){
			$(this).find("textarea").val("").blur();
		});
	})
	.on("click",".anss",function(){
		//查询回答
		var parent = $(this).parents(".oneanswer");

		selTwoAns(parent.attr("data-id"),parent);
	})
	.on("click",".cn",function(){
		//采纳
		if($(this).attr("data-adopcount")>=5){
			alert($(this).attr("data-adopcount"));
			alert("最多能采纳5条回答");
			return;
		}
		var parent = $(this).parents(".oneanswer");
		if(req != null){
			req.abort();
		}
		req = $.ajax({
			url : rootPath + "/questionanswermanager/adoptAns",
			type:"post",
			data:{"id":$(this).attr("data-id"),"ids":$(this).attr("data-ids"),"anaswerUserId":$(this).attr("data-userId"),"questionscore" :$(this).attr("data-questionscore")},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					alert("采纳成功");
					selOneAns(1);
				}
			}
		});
	})
	
	.on("click",".dz",function(){
		//点赞
		var parent = $(this).parents(".oneanswer");
		if(req != null){
			req.abort();
		}
		req = $.ajax({
			url : rootPath + "/questionanswermanager/thumbs",
			type:"post",
			data:{"id":$(this).attr("data-id"),"types":$(this).attr("data-types")},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					alert("点赞成功");
					selOneAns(1);
				}
			}
		});
	})
	.on("click",".chec",function(){
		//点赞
		var parent = $(this).parents(".oneanswer");
		if(req != null){
			req.abort();
		}
		req = $.ajax({
			url : rootPath + "/questionanswermanager/checkMgc",
			type:"post",
			data:{"id":$(this).attr("data-id")},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					alert("审核成功");
					selOneAns(1);
				}
			}
		});
	})
	.on("click",".del",function(){
		var parent = $(this).parents(".oneanswer");
		if($(this).attr("data-adotp")== 1 ){
			alert("问题已采纳，不能删除");
			return;
		}
		//删除回答
		if(req != null){
			req.abort();
		}
		req = $.ajax({
			url : rootPath + "/questionanswermanager/delAns",
			type:"post",
			data:{"id":$(this).attr("data-id"),"types":$(this).attr("data-types")},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					var count = parseInt($.trim(parent.find(".count").text()));
					parent.find(".count").html(count - 1);
					parent.slideUp(200,function(){
						$(this).remove();
					});
				}
			}
		});
	})
	.on("click",".btn-onere",function(){
		var rename = $(this).parents(".onetext").find("textarea").attr("data-name");
		var reuser = $(this).parents(".onetext").find("textarea").val();
		//回复一级回复
		var parent = $(this).parents(".oneanswer");
		var replyUserType = $(this).parents(".onetext").find("textarea").attr("data-types");
		if(req != null){
			req.abort();
		}
		req = $.ajax({
			url: rootPath + "/questionanswermanager/addAns",
			type:"post",
			data:{"questionId":$(this).parents(".oneanswer").attr("data-queid"),"answerDesc":reuser,"answerId":parent.attr("data-id"),"replyUserId":$(this).attr("data-uid"),"replyUserName":rename,"replyUserType":replyUserType,"answerType":$("#types").val(),"parentId":$(this).attr("data-id")},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					var count = parseInt($.trim(parent.find(".count").text()));
					parent.find(".count").html(count + 1);
					parent.find(".onetext").slideUp(200,function(){
						$(this).find("textarea").val("").blur();
					});
					selTwoAns(parent.attr("data-id"),parent);
				}
			}
		});
	})
	.on("click",".oret",function(){
		//回复
		var parent = $(this).parents(".twoanswer");
		parent.find(".twotext").slideDown(200,function(){
			$(this).find("textarea").focus();
		});
	})
	.on("click",".btn-twoca",function(){
		//取消回复
		var parent = $(this).parents(".twoanswer");
		parent.find(".twotext").slideUp(200,function(){
			$(this).find("textarea").val("").blur();
		});
	})
	.on("click",".delt",function(){
		var parent = $(this).parents(".twoanswer");
		var parents = $(this).parents(".oneanswer");
		//删除回答
		if(req != null){
			req.abort();
		}
		req = $.ajax({
			url : rootPath + "/questionanswermanager/delAns",
			type:"post",
			data:{"id":$(this).attr("data-id"),"types":$(this).attr("data-types")},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					var count = parseInt($.trim(parents.find(".count").text()));
					parents.find(".count").html(count - 1);
					parent.slideUp(200,function(){
						$(this).remove();
					});
				}
			}
		});
	})
	.on("click",".btn-twore",function(){
		var rename = $(this).parents(".twotext").find("textarea").attr("data-name");
		var reuser = $(this).parents(".twotext").find("textarea").val();
		var replyUserType = $(this).parents(".twotext").find("textarea").attr("data-types");
		//回复二级回复
		var parent = $(this).parents(".twoanswer");
		var parents = $(this).parents(".oneanswer");
		if(req != null){
			req.abort();
		}
		req = $.ajax({
			url: rootPath + "/questionanswermanager/addAns",
			type:"post",
			data:{"questionId":$(this).parents(".oneanswer").attr("data-queid"),"answerDesc":reuser,"answerId":parents.attr("data-id"),"replyUserId":$(this).attr("data-uid"),"replyUserName":rename,"replyUserType":replyUserType,"answerType":$("#types").val(),"parentId":$(this).attr("data-id")},
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					var count = parseInt($.trim(parents.find(".count").text()));
					parents.find(".count").html(count + 1);
					parent.find(".twotext").slideUp(200,function(){
						$(this).find("textarea").val("").blur();
					});
					selTwoAns(parents.attr("data-id"),parents);
				}
			}
		});
	})
	.on('click','i.close',function(){
        $('.add-classes').fadeOut(200,function(){
            $('.add-classes-bg').fadeOut(200);
            $(".shimg").html("");
        });
    })
	.on('click','.icons',function(){
		var url = $(this).attr("data-url");
        $('.add-classes').fadeIn(200,function(){
            $('.add-classes-bg').fadeIn(200);
            $(".shimg").html("<img src='" + url + "'/>");
        });
    });
});
/*一级二级回复 js end*/
(function($){
	var Form={
			init : function(){
				var $this=this;
				$(".t-content").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success');
					if(!status){
						 _this.addClass('btn-success').siblings('a').removeClass('btn-success');
					}
				});
				$(".zdy").click(function(){
					questionName ="";
					oneId = $(this).attr("ids");
					Form.findQuestion(1,oneId,twoId,jz);
				});
				$(".clas").click(function(){
					questionName ="";
					twoId = $(this).attr("ids");
					Form.findQuestion(1,oneId,twoId,jz);
				});
				$(".jz").click(function(){
					questionName ="";
					jz = $(this).attr("jz");
					console.log(jz);
					Form.findQuestion(1,oneId,twoId,jz);
				});
				$(".searchByName").click(function(){
					questionName = $("#questionName").val();
					Form.findQuestion(1,oneId,twoId,jz,questionName);
				});
				Form.findQuestion(1,oneId,twoId,jz);
			},
			findQuestion : function(pageNo,oneId,twoId,jz,questionName){
				//注（根据需求变更）：用户自定义标签改为了审核标签，后台接收字段任为userDefuledId，xml文件对应字段做相应改变
				console.log("pageNo="+pageNo+",systemTagId="+oneId+",userDefuledId="+twoId+",jz="+jz,"questionName="+questionName);
				var param = "";
				if(oneId){
					param+="&systemTagId="+oneId;
				}
				if(twoId){
					param+="&userDefuledId="+twoId;
				}
				if(jz==1){
					param+="&topFlag=1";
				}
				if(jz==2){
					param+="&essenceFlag=1";
				}
				param+="&page="+pageNo;
				if(questionName){
					param+="&questionTitle="+questionName;
				}
				param+="&courseFlag=0";
				console.log(param);
				$.ajax({
					url : rootPath + "/Question/questionAjax",
					type : "post",
					data : param,
					beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(data) {
						$("#questionList").html("").html(data);
						
						//置顶或精华加悬浮效果
						$(".zdIcon").hover(function(){
							var id = $(this).attr("ids");
							$(this).attr("style","color: #00b7ee;");
						},function(){
							var id = $(this).attr("ids");
							$(this).attr("style","");
						});
						
						$(".jhIcon").hover(function(){
							var id = $(this).attr("ids");
							$(this).attr("style","color: #FE5151;");
						},function(){
							var id = $(this).attr("ids");
							$(this).attr("style","");
						});
						//置顶帖点击
						$(".zdIcon").click(function(){
							var id = $(this).attr("ids");
							var isZd = 0;
							if($(this).hasClass("zdt")){
								isZd = 0;
							}else{
								isZd = 1;
							}
							Form.update(id,isZd,null);
						});
						//精华帖点击
						$(".jhIcon").click(function(){
							var id = $(this).attr("ids");
							var isJh = 0;
							if($(this).hasClass("jht")){
								isJh = 0;
							}else{
								isJh = 1;
							}
							Form.update(id,null,isJh);
						});
						//删除点击
						$(".delIcon").click(function(){
							var id = $(this).attr("ids");
							$.confirm("确认删除该问答吗？",function(result){
								if(result){
									var pageNo = $(".pagination").find(".active").find("a").html();
									Form.delQue(id,pageNo);
								}
							});
						});
						//审核点击
						$(".shIcon").click(function(){
							var id = $(this).attr("ids");
							$.confirm("确认审核通过么？",function(result){
								if(result){
									Form.shQue(id);
								}
							});
						});
						//显示全部点击
						$(".showAll").click(function(){
							var id = $(this).attr("ids");
							questionUserId = $(this).attr("questionUserId");
							queId = id;
							var html = $(this).html();
							var isMan = $("#isMan").val();
							if(isMan == 'yes'){
								types = "3";
							}else{
								types = "2";
							}
							if(html == '显示全部'){
								$(this).html("收起全部");
								$(".showCon"+id+"").addClass("none");
								$(".showAllCon"+id+"").removeClass("none");
								$(".pl"+id+"").removeClass("none");
								selOneAns(1);
								$(".plContent").each(function(){
									var queIds = $(this).attr("queId");
									if(queIds!=id){
										$(".showAlls"+queIds+"").html("显示全部");
										$(".pl"+queIds+"").addClass("none");
										$(".showAllCon"+queIds+"").addClass("none");
										$(".showCon"+queIds+"").removeClass("none");
									}
								});
							}else{
								$(this).html("显示全部");
								$(".pl"+id+"").addClass("none");
								$(".showAllCon"+id+"").addClass("none");
								$(".showCon"+id+"").removeClass("none");
							}
						});
						//回答点击
						$(".showAns").click(function(){
							var id = $(this).attr("ids");
							var userT = $(this).attr("userT");
							$(".pl"+id+"").html("").append("<textarea id='editorSpace"+id+"'></textarea><div class='fr anExpressBut'><a href='javascript:void(0)' class='btn qxQue btn-default'>取消</a><a href='javascript:void(0)' class='btn fbQue btn-success' ids='"+id+"' userT='"+userT+"'>发布</a></div>").removeClass("none");
						});
						//取消回答
						$(".plContent").on("click",".qxQue",function(){
							$(this).parents(".plContent").html("").addClass("none");
						})
						//发布回答
						$(".plContent").on("click",".fbQue",function(){
							var id = $(this).attr("ids");
							var userT = $(this).attr("userT");
							Form.CKupdate();
							var plCon = $("#editorSpace"+id+"").val();
							if(!plCon){
								$('<div class="c-fa">'+ "回复内容不能为空" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
								});
								return;
							}
							if(plCon.length>2200){
								$('<div class="c-fa">'+ "回复内容不能超过2000字" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
								});
								return;
							}
							Form.addAns(id,plCon,userT);
						})
						
					},
					complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			},
			delQue : function(id,pageNo){
				if(!pageNo){
					pageNo = 1;
				}
				$.ajax({
					url : rootPath + "/Question/del/"+id,
					type : "post",
					success : function(data) {
						if(data == 'success'){
							Form.findQuestion(pageNo,oneId,twoId,jz);
						}
					}
				});
			},
			shQue : function(id){
				$.ajax({
					url : rootPath + "/Question/shenhe/"+id,
					type : "post",
					success : function(data) {
						if(data == 'success'){
							Form.findQuestion(1,oneId,twoId,jz);
						}
					}
				});
			},
			update : function(id,zd,jh){
				var param = "";
				param+="&id="+id;
				if(zd || zd == 0){
					param+="&topFlag="+zd;
				}
				if(jh || jh == 0){
					param+="&essenceFlag="+jh;
				}
				$.ajax({
					url : rootPath + "/Question/update",
					type : "post",
					data : param,
					success : function(data) {
						if(data == 'success'){
							Form.findQuestion(1,oneId,twoId,jz);
						}
					}
				});
			},
			CKupdate : function () {
				for (instance in CKEDITOR.instances) {
					CKEDITOR.instances[instance].updateElement();
				}
			},
			addAns : function(queId,ansDesc,userT){
				if(ansDesc.length > 2000){
					alert("回答字数不能超过2000字");
					return ;
				}
				$.ajax({
					url : rootPath + "/QuestionAnswer/add",
					type : "post",
					data : {"questionId":queId,"answerDesc":ansDesc,"replyUserId":userT},
					success : function(data) {
						if(data == 'success'){
							Form.findQuestion(1,oneId,twoId,jz);
						}else{
							$('<div class="c-fa">'+ "回复失败" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();
							});
						}
					}
				});
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)