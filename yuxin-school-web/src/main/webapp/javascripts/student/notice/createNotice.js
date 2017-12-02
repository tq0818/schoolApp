var msgCount;
     $(function(){
		$(".sendStuMsg").show();
		$(".phoneHint").hide();
		$("#ckecktor").hide();
		//订阅文章
		$(".sendGrade,.dingyueChooseBtn,.dingyueSendBtn,.con-coverimg").hide();
		$("#dingyue_ckecktor").hide();
		
 		$selectSubMenu('student_message');
 		selectGroup1('_edit');
 		selItem();
 		msgCount = $("#msgCount").val();
 		$("#one").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var oneItem = $("#one").val();
 	    	selTwoItem(messageType,oneItem);
 		});
 		$("#two").change(function(){
 	    	var messageType = $(".btn-type.btn-primary").attr("data-type");
 	    	var twoItem = $("#two").val();
            selThreeItem(messageType,twoItem);
 	    	// selClassOrModule(url,oneItem,$("#two").val());
 		});
         $("#three").change(function(){
             var messageType = $(".btn-type.btn-primary").attr("data-type");
             var oneItem = $("#one option:selected").attr("data-code");
             var twoItem = $("#two option:selected").attr("data-code");
             if(messageType == "STUDENT_MESSAGE_CLASSTYPE"){
                 url = rootPath + "/classModule/selClassType";
                 $("#classTitle").html("课程：");
             }else if(messageType == "STUDENT_MESSAGE_MODULENO"){
                 url = rootPath + "/classModule/selModuleNo";
                 $("#classTitle").html("班号：");
             }
             selClassOrModule(url,oneItem,twoItem,$("#three option:selected").attr("data-code"));
         });
         // 通知标题,发送内容,短信字数       
 		if($(".btn-type.btn-primary").attr("data-type")=='STUDENT_MESSAGE_CLASSTYPE' && $(".btn-method.btn-primary").attr("data-type")=='STUDENT_MESSAGE_MOBILE'){
			$(".con-tzbt,.con-fsnr,.tips-txt").hide();
			$(".notice-main").css({'margin':'100px auto 100px'});
		}
 		
 		$(".btn-upload").on('click',function(){
			$("#chooseDiv").css("display", "block");
			$("#stopDiv").css("display", "block");
		});
 		// 弹层处理
 	      $('.upload-layer')
 	          .on('click','i.close',function(){
 	              $('.upload-layer').fadeOut(200,function(){
 	                  $('.add-layer-bg').fadeOut(200);
 	              });
 	          })
 	          // 取消
 	          .on('click','.btn-cancel',function(){
 	              $(this).parents('.pic-upload').fadeOut(200,function(){
 	                 // alert('这个仅作示例，为了展示列表')
 	            	  $('.upload-layer').css({'height':'481px'});
 	              })
 	          })
 	          .on('click','li.add',function(){
 	              $('.pic-upload').fadeIn(200,function(){
 	                  //alert('仅作示例，具体根据实际情况自行修改！')
 	            	  $('.upload-layer').css({'height':'540px'});
 	              })
 	          });
 		
 		$(".btn-method").on('click',function(){
 			$(".con-tzbt,.con-fsnr,.tips-txt").show();
 			$(".notice-main").css({'margin':'0 auto'});
 			var type = $(this).data("type");
 			//通知方式 选中
			$(this).addClass('btn-primary').removeClass('btn-default').siblings().removeClass('btn-primary').addClass('btn-default');//选中第一行
			//通知类型 默认选中第一个
			$(".btn-type:first").addClass('btn-primary').removeClass('btn-default').siblings().removeClass('btn-primary').addClass('btn-default');//第二行默认选中第一个
			if(type != 'STUDENT_MESSAGE_DINGYUE'){
				selItem();
			}
			
			//短信通知
			if(type == 'STUDENT_MESSAGE_MOBILE'){
				//	$(".btn-type").last().prev().show();//指定通知
				$(".phoneHint,.emailHint,.sendGrade,.con-coverimg,.dingyueChooseBtn,.dingyueSendBtn,.emailTitle,#ckecktor,#email_ckecktor,#dingyue_ckecktor,.use_email,.stuGroup,.lj-tops").hide();
				$(".sendMsgType,.sendStuMsg,.zhan,#messageContent,.sendBtn").show();
				if($(".btn-type.btn-primary").attr("data-type")=='STUDENT_MESSAGE_CLASSTYPE'){
					$(".lj-tops").show();
					$(".con-tzbt,.con-fsnr,.tips-txt").hide();
					$(".notice-main").css({'margin':'100px auto 100px'});
				}
			}
			//站内信通知
			if(type == "STUDENT_MESSAGE_WEB"){
				//$(".btn-type").last().prev().hide();//指定通知
				$("#messageContent,.zhan,.sendGrade,.con-coverimg,.dingyueChooseBtn,.dingyueSendBtn,.phoneHint,.emailHint,.emailTitle,#email_ckecktor,#dingyue_ckecktor,#use_email,.stuGroup,.use_email,.lj-tops").hide();
				$(".sendMsgType,#ckecktor,.sendStuMsg,.sendBtn").show();
 				
			}
			//邮件通知
			if(type == 'STUDENT_MESSAGE_EMAIL'){
				//$(".btn-type").last().prev().show();//指定通知
				$(".phoneHint,.emailHint,.sendGrade,.con-coverimg,.dingyueChooseBtn,.dingyueSendBtn,#ckecktor,#dingyue_ckecktor,#messageContent,.zhan,.lj-tops").hide();
				$(".sendMsgType,.sendStuMsg,.emailTitle,#email_ckecktor,.use_email,.sendBtn").show();
			}
			//订阅文章
			if(type == 'STUDENT_MESSAGE_DINGYUE'){
				//$(".btn-type").last().prev().show();
				$(".phoneHint,.sendMsgType,.emailHint,#ckecktor,.sendStuMsg,.sendBtn,#messageContent,.zhan,.lj-tops,.emailTitle,#email_ckecktor,.use_email").hide();
				$(".sendGrade,.con-coverimg,.sendStuNum,#dingyue_ckecktor,.dingyueChooseBtn,.dingyueSendBtn").show();
				selPersonOfDingyue();
			}
			
 		});
 		
 		// 	    推荐学段复选
 		$('.articlesList').children('button:not(:first)').click(function () {
 		    if ($(this).hasClass('btn-primary')) {
 		        $(this).removeClass('btn-primary');
 		        $('.articlesList').children('button').eq(0).removeClass('btn-primary');
 		    } else {
 		        $(this).addClass('btn-primary');
 		    }
 		   selPersonOfDingyue();
 		});
// 		    点击全部，则全部选中
 		$('.articlesList').children('button').eq(0).click(function () {
 			if ($(this).hasClass('btn-primary')) {
 		        $(this).removeClass('btn-primary');
 		        $('.articlesList').children('button').eq(0).removeClass('btn-primary');
 		    } else {
 		        $(this).addClass('btn-primary');
 		    }
 		    var allChildren = $('.articlesList').children('button');
 		    //如果全部有active则删除全部的选中，否则全部选中
 		    if ($(this).hasClass('btn-primary')) {
 		        for (var i = 1; i < allChildren.length; i++) {
 		            allChildren.eq(i).addClass('btn-primary');
 		        }
 		    } else {
 		        for (var i = 1; i < allChildren.length; i++) {
 		            allChildren.eq(i).removeClass('btn-primary');
 		        }
 		    }
 		    selPersonOfDingyue();
 		});
 		
 		$('.btn-type').on('click',function(){
 			$(".con-tzbt,.con-fsnr,.tips-txt").show();
 			$(".notice-main").css({'margin':'0 auto'});
 			var notice_fs;
 			var notice_type = $(this).data('type');
 			$.each($('.btn-method'),function(){
 				if($(this).hasClass('btn-primary')) notice_fs = $(this).data('type');
 			});
 			$(this).addClass('btn-primary').removeClass('btn-default').siblings().removeClass('btn-primary').addClass('btn-default');
 			if(notice_fs == 'STUDENT_MESSAGE_MOBILE' && notice_type == 'STUDENT_MESSAGE_SPECIAL'){
 				$(".phoneHint").show();
 				$(".sendStuMsg,.emailHint,.stuGroup,.lj-tops").hide();
 			}
 			if(notice_fs == 'STUDENT_MESSAGE_EMAIL' && notice_type == 'STUDENT_MESSAGE_SPECIAL'){
 				$(".emailHint").show();
 				$(".sendStuMsg,.phoneHint,.stuGroup,.lj-tops").hide();
 			}
 			if(notice_type == 'STUDENT_MESSAGE_CLASSTYPE' || notice_type == 'STUDENT_MESSAGE_MODULENO'){
 				selItem();
 				$('.sendStuMsg,.lj-tops').show();
 				$('.phoneHint,.emailHint,.stuGroup').hide();
 				if($(".btn-method.btn-primary").attr("data-type")=='STUDENT_MESSAGE_MOBILE'){
					$(".con-tzbt,.con-fsnr,.tips-txt").hide();
					$(".notice-main").css({'margin':'100px auto 100px'});
				}
 			}
 			if(notice_type == 'STUDENT_MESSAGE_GROUP'){
 				selectGroup1('_edit');
 				$('.stuGroup').show();
 				$('.phoneHint,.emailHint,.sendStuMsg,.lj-tops').hide();
 			}
 			valida();
 		});
 		
 		$("#class").change(function(){
 			selPerson();
 		});
 		$("#useMsg").html("0条");
 		$("#write").html(0);
 		$("#SurootPathlus").html(msgCount + "条");
 		
 		$(".btn-send").click(function(){
 			var title = $.trim($("#title").val());
 			var method = $.trim($(".btn-method.btn-primary").attr("data-type"));
 			var types = $.trim($(".btn-type.btn-primary").attr("data-type"));
 			var msgcount = "";
 			var msgcounttext = "";			
 			var emailTitle = '';
 			var oneItemId = null;
 			var twoItemId = null;
 			var classId = null;
 			var groupOneId = null;
 			var groupTwoId = null;
 			var oneItemCode = null;
 			var twoItemCode = null;
 			var threeItemCode =null;
 			var phone = null;
 			var email = null;
 			if($(".btn-type.btn-primary").attr("data-type")=='STUDENT_MESSAGE_CLASSTYPE' && $(".btn-method.btn-primary").attr("data-type")!='STUDENT_MESSAGE_MOBILE'){
 				if(title == ""){
 					$("#title").focus();
 					$("#title").select();
 					return false;
 				}
 			}
 			
 			if(method == 'STUDENT_MESSAGE_MOBILE' && types == "STUDENT_MESSAGE_SPECIAL"){
 				phone = $.trim($("#phone").val());
 				if(phone == ""){
 					$("#phone").focus();
 					$("#phone").select();
 					return false;
 				}
 				phone = phone.replace(/，/g,",");
 				var reg = /^0?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$/;
 				if(phone.indexOf(",") < 0){
 	 				if(!reg.test($.trim(phone))){
 	 					$("#phone").focus();
 	 					$('<div class="c-fa">'+ "手机号" + phone + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 						$(this).remove();}
 	 					);
 	 					return false;
 	 				}
 				}else{
 					var array = phone.split(",");
 					for(var i = 0 ; i < array.length ; i++){
 	 	 				if(!reg.test($.trim(array[i]))){
 	 	 					$("#phone").focus();
 	 	 					$('<div class="c-fa">'+ "手机号" + array[i] + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 	 						$(this).remove();}
 	 	 					);
 	 	 					return false;
 	 	 				}
 					}
 				}
 			}
 			if(method == 'STUDENT_MESSAGE_EMAIL'){
 				emailTitle = $.trim($('#email_title').val());
 				if(!emailTitle){
 					$('#emailTitle').focus();
 					$('<div class="c-fa">'+ "请填写邮件标题！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
	 						$(this).remove();}
	 					);
 					return;
 				}
 			}
 			if(method == 'STUDENT_MESSAGE_EMAIL' && types == "STUDENT_MESSAGE_SPECIAL"){
 				email = $.trim($('#email').val());
 				if(!email){
 					$("#email").focus();
 					$("#email").select();
 					return false;
 				}
 				email = email.replace(/，/g,",");
 				var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
 				if(email.indexOf(',') < 0){
 					if(!reg.test(email)){
 						$('#email').focus();
 						$('<div class="c-fa">'+ "邮箱" + email + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 						$(this).remove();}
 	 					);
 	 					return false;
 					}
 				}else{
 					var arr = email.split(',');
 					for(var i=0;i<arr.length;i++){
 						if(!reg.test($.trim(arr[i]))){
 							$('<div class="c-fa">'+ "邮箱" + arr[i] + "格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 	 	 						$(this).remove();}
 	 	 					);
 	 	 					return false;
 						}
 					}
 				}
 			}
 			if(types == 'STUDENT_MESSAGE_CLASSTYPE' || types == 'STUDENT_MESSAGE_MODULENO'){
 				oneItemId = $.trim($("#one").val());
 				twoItemId = $.trim($("#two").val());
                oneItemCode = $("#one").find("option:selected") .attr("data-code");
                twoItemCode = $("#two").find("option:selected").attr("data-code");
                threeItemCode = $("#three").find("option:selected").attr("data-code");
 				classId = $.trim($("#class").val());
 			}
 			if(types == 'STUDENT_MESSAGE_GROUP'){
 				groupOneId = $.trim($('#studentG1_edit').val());
 				groupTwoId = $.trim($('#studentG2_edit').val());
 			}
 			
 			if(method == "STUDENT_MESSAGE_WEB"){
 				CKupdate();
 				msgcount = $("#newsContents").val();
 				msgcounttext=editor.document.getBody().getText();
 			/*	msgcount = msgcount.replace(/<p>/g, "<span>");
 				msgcount = msgcount.replace(/<p /g, "<span ");
 				msgcount = msgcount.replace(/<\/p>/g, "</span><br>");*/
 			}else if(method == 'STUDENT_MESSAGE_EMAIL'){
 				CKupdate();
 				msgcount = $("#email_newsContents").val();
// 				msgcount = msgcount.replace(/<p>/g, "<span>");
// 				msgcount = msgcount.replace(/<p /g, "<span ");
// 				msgcount = msgcount.replace(/<\/p>/g, "</span><br>");
 			}else{
 				msgcount = $("#msgcount").val();
 			}
 			if($(".btn-type.btn-primary").attr("data-type")=='STUDENT_MESSAGE_CLASSTYPE' && $(".btn-method.btn-primary").attr("data-type")!='STUDENT_MESSAGE_MOBILE'){
	 			if(!msgcount){
	// 				if(!reg.test($.trim(arr[i]))){
						$('<div class="c-fa">'+ "发送内容不能为空！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
	 						$(this).remove();}
	 					);
	 					return false;
	//				}
	 			}
 			}
 			console.log('send');
 			var isHurry = $('.hurryNotice:checked').val();
 			var lessonId = $('#classLesson').val();
 			if($.trim(lessonId) ==""){
 				 $('<div class="c-fa">'+ "您还没选择课次" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
			        	$(this).remove();
			      });
 				 return;
 			}
 			$.ajax({
 				url:rootPath + "/classModule/sendMsg",
 				type:"post",
 				data:{"title":title,"content":msgcount,"messageType":types,"messageMethod":method,
 					"itemOneCode":oneItemCode,"itemSecondCode":twoItemCode,"itemThirdCode":threeItemCode,
 					"classTypeId":classId,'groupOneId':groupOneId,'groupTwoId':groupTwoId,'email':email,
 					'emailTitle':emailTitle,"phone":phone,"moduleNoId":classId,"isHurry":isHurry,"lessonId":lessonId,
 	 				"contentText":msgcounttext},
 				dataType:"json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
 				success:function(data){
 					if(data.result == "success"){
 						$(".loading").hide();
				        $('<div class="c-fa">'+ "消息已发送！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
				        	$(this).remove();
		 					location.href = rootPath + "/student/notice";
		 					$(".loading").show();
				        });
 					}else{
			            $(".loading").hide();
			            $(".loading-bg").hide();
			            if(data.result == "stuno"){
					        $('<div class="c-fa">'+ "当前没有学员！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }else if(data.result == "msgNotCount"){
					        $('<div class="c-fa">'+ "短信量不足，请购买后再发送" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }else{
					        $('<div class="c-fa">'+ "消息发送失败！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }
 					}
 				}
 			});
 		});
 		//订阅文章
 		$(".btn-publish").click(function(){
			var title = $.trim($("#title").val());
			var method = $.trim($(".btn-method.btn-primary").attr("data-type"));
			var msgcount = "";
			var msgcounttext = "";
			var signup_vote=$("input[name='signup_vote']:checked").val();
			var limitStuNum=$.trim($("#limitStuNum").val());
			var isSend=null;
			if(title == ""){
				$("#title").focus();
				$("#title").select();
				return false;
			}
			var picUrl = $("#commdotityPic").attr("realPath");
			if (picUrl.length < 0 || picUrl == "") {
				 $('<div class="c-fa">'+ "请上传封面图片!" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					$(this).remove();}
				);
				return;
			}
			 var gradeCodes=new Array();
	    	 var allChildren = $('.articlesList').children('button');
	    	 var k=0;
	    	 if(null!=allChildren&&allChildren.length>1){
	    		 for (var i = 1; i < allChildren.length; i++) {
		            if(allChildren.eq(i).hasClass('btn-primary')){
		            	gradeCodes[k++]=allChildren.eq(i).attr("data-type");
		            }
		        }
	    	 }
	    	 if(null==gradeCodes||gradeCodes.length<1){
	    		 $('<div class="c-fa">'+ "请选择学段!" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					$(this).remove();}
				);
				return;
	    	 }
	    	if(method == "STUDENT_MESSAGE_DINGYUE"){
				CKupdate();
				msgcount = $("#dingyue_newsContents").val();
				msgcounttext=dingyue_ckecktor.document.getBody().getText();
			/*	msgcount = msgcount.replace(/<p>/g, "<span>");
				msgcount = msgcount.replace(/<p /g, "<span ");
				msgcount = msgcount.replace(/<\/p>/g, "</span><br>");*/
			}
 			if(!msgcount){
// 				if(!reg.test($.trim(arr[i]))){
					$('<div class="c-fa">'+ "发送内容不能为空！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 						$(this).remove();}
 					);
 					return false;
//				}
 			}
 			if(!signup_vote){
 				$('<div class="c-fa">'+ "请选择报名或投票！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					$(this).remove();}
				);
				return false;
 			}else if(signup_vote=='0'){
 				var reg = /^[1-9]\d*$/;
 				if(!limitStuNum){
 					$("#limitStuNum").focus();
 					$('<div class="c-fa">'+ "报名人数限制不能为空！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 						$(this).remove();}
 					);
 					return false;
 				}else if(!reg.test($.trim(limitStuNum))){
 					$("#limitStuNum").focus();
 					$('<div class="c-fa">'+ "报名人数限制格式不正确！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 						$(this).remove();}
 					);
 					return false;
 				}
 			} 			
 			if($("#isNeedSend").is(":checked")){
 				isSend="1";
 			}
			console.log('send');
			$.ajax({
				url:rootPath + "/classModule/sendMsgOfDingyue",
				type:"post",
				data:{"title":title,"content":msgcount,"messageMethod":method,"signupVote":signup_vote,
					"maxNum":limitStuNum,"gradeCodes":gradeCodes,"isSend":isSend,"coverImg":picUrl,"contentText":msgcounttext},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
	              $(".loading").show();
	              $(".loading-bg").show();
	        	},
				success:function(data){
					if(data.result == "success"){
						$(".loading").hide();
			        	$('<div class="c-fa">'+ "消息已发送！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
				        	$(this).remove();
		 					location.href = rootPath + "/student/notice";
		 					$(".loading").show();
				        });
					}else{
			            $(".loading").hide();
			            $(".loading-bg").hide();
			            if(data.result == "stuno"){
					        $('<div class="c-fa">'+ "当前没有学员！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }else{
					        $('<div class="c-fa">'+ "消息发送失败！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
					        	$(this).remove();
					        });
			            }
					}
				}
			});
		});
	
     });
     function selItem(){
    	 var messageType = $(".btn-type.btn-primary").attr("data-type");
    	 var oneItem = $("#one").val();
    	 selTwoItem(messageType,oneItem);
     }
     
     function selTwoItem(messageType,oneItem){
    	 $.ajax({
             url:rootPath + "/classModule/selItemRelationByPid",
    		 // url:rootPath + "/classModule/selTwoItem",
    		 type:"post",
    		 data:{"pid":oneItem},
    		 dataType:"json",
   			beforeSend:function(XMLHttpRequest){
   	              $(".loading").show();
   	              $(".loading-bg").show();
   	         },
    		 success:function(data){
    			 $("#two").empty();
    			 $.each(data.two,function(index,item){
    				 $("#two").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
    			 });
                 selThreeItem(messageType,$("#two").val());
    		 }
    	 });
     }

		function selThreeItem(messageType,twoItem){
			$.ajax({
				url:rootPath + "/classModule/selItemRelationByPid",
				// url:rootPath + "/classModule/selTwoItem",
				type:"post",
				data:{"pid":twoItem},
				dataType:"json",
				beforeSend:function(XMLHttpRequest){
					$(".loading").show();
					$(".loading-bg").show();
				},
				success:function(data){
					$("#three").empty();
					$.each(data.two,function(index,item){
						$("#three").append("<option value='" + item.id + "' data-code='"+item.itemCode+"'>" + item.itemName + "</option>");
					});
					var url = "";
					if(messageType == "STUDENT_MESSAGE_CLASSTYPE"){
						url = rootPath + "/classModule/selClassType";
						$("#classTitle").html("课程：");
					}else if(messageType == "STUDENT_MESSAGE_MODULENO"){
						url = rootPath + "/classModule/selModuleNo";
						$("#classTitle").html("班号：");
					}
                    var oneItem = $("#one option:selected").attr("data-code");
                    var twoItem = $("#two option:selected").attr("data-code");
					selClassOrModule(url,oneItem,twoItem,$("#three option:selected").attr("data-code"));
				}
			});
}
     
     function selClassOrModule(url,oneItem,twoItem,threeItem){

		 $.ajax({
			 url:url,
			 type:"post",
             data:{"itemOneCode":oneItem,"itemSecondCode":twoItem,"itemThirdCode":threeItem},
			 // data:{"itemOneId":oneItem,"itemSecondId":twoItem,"itemThridCode":threeItem},
			 dataType:"json",
	   			beforeSend:function(XMLHttpRequest){
	   	              $(".loading").show();
	   	              $(".loading-bg").show();
	   	         },
			 success:function(data){
				 $("#class").empty();
				 $.each(data.types,function(index,item){
					 $("#class").append("<option value='" + item.id + "'>" + item.name + "</option>");
				 });
				 $(".js-example-basic-single").select2();
				 selPerson();
			 }
		 });
     }
     
     function selPerson(){
    	 //查询人数
    	 var messageType = $(".btn-type.btn-primary").attr("data-type");
    	 var classTypeId = $("#class").val();
    	 var itemOneId = $("#one").val();
    	 var itemSecondId = $("#two").val();
    	 if(classTypeId !=null  && classTypeId.length > 0){
             $.ajax({
                 url:rootPath + "/classModule/selPerson",
                 type:"post",
                 data:{"messageType":messageType,"id":classTypeId},
                 dataType:"json",
                 beforeSend:function(XMLHttpRequest){
                     $(".loading").show();
                     $(".loading-bg").show();
                     $("#classLesson").empty();
                     $(".btn-view").empty();
                 },
                 success:function(data){
                     $(".btn-view").html(data.count + "人");
                     $("#sendStu,#useEmailMsg").html(data.count);
                     $("#useMsg").html(data.count+"条");
                     $.each( data.lessons, function(index, lesson){
                         if(index == 0){
                             $("#classLesson").append("<option  selected = 'selected' value='"+lesson.id+"'>"+lesson.lessonName+"</option>");
                         }else{
                             $("#classLesson").append("<option  value='"+lesson.id+"'>"+lesson.lessonName+"</option>");
                         }

                     });
                 },
                 complete:function(XMLHttpRequest,textStatus){

                     $(".loading").hide();
                     $(".loading-bg").hide();
                 }
             });
		 }else{
             $("#classLesson").empty();
             $(".btn-view").html(0 + "人");
             $("#sendStu,#useEmailMsg").html(0);
             $("#useMsg").html(0+"条");
             $(".loading").hide();
             $(".loading-bg").hide();
		 }

     }
     
     /**
      * 查询订阅文章学生数
      */
     function selPersonOfDingyue(){
    	 var gradeCodes=new Array();
    	 var allChildren = $('.articlesList').children('button');
    	 var k=0;
    	 if(null!=allChildren&&allChildren.length>1){
    		 for (var i = 1; i < allChildren.length; i++) {
	            if(allChildren.eq(i).hasClass('btn-primary')){
	            	gradeCodes[k++]=allChildren.eq(i).attr("data-type");
	            }
	        }
    	 }
    	 if(gradeCodes !=null  && gradeCodes.length > 0){
             $.ajax({
                 url:rootPath + "/classModule/selPersonOfDingyue",
                 type:"post",
                 data:{"gradeCodes":gradeCodes},
                 dataType:"json",
                 beforeSend:function(XMLHttpRequest){
                     $(".loading").show();
                     $(".loading-bg").show();
                     $("#sendStu").empty();
                     $(".btn-view").empty();
                 },
                 success:function(data){
                     $("#sendStu").html(data.count);
                 },
                 complete:function(XMLHttpRequest,textStatus){
                     $(".loading").hide();
                     $(".loading-bg").hide();
                 }
             });
		 }else{
             $("#sendStu").html(0);
             $(".loading").hide();
             $(".loading-bg").hide();
		 }

     }
     function valida(){
    	 var method = $.trim($(".btn-method.btn-primary").attr("data-type"));
    	 switch(method){
    	 	case "STUDENT_MESSAGE_EMAIL":
    	 		if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_SPECIAL"){
    	 			 var count = 1;
    				 var phone = $.trim($("#email").val());
    				 if(phone.indexOf(",") < 0){
    					 var useMsg = count;
    		      		 $("#useEmailMsg").html(useMsg);
    				 }else{
    					 var phones = phone.split(",");
    					 var person = phones.length;
    		      		 var useMsg = count * parseInt(person);
    		      		 $("#useEmailMsg").html(useMsg);
    				 }
    			}
    	 		break;
    	 	default:
    	 		 if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_MODULENO"){
    	        	 var count = ($("#msgcount").val()).length;
    	      		 $("#write").html(count);
    	      		 var person = $.trim($("#sendStu").text());
    	      		 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 ) * parseInt(person);
    	        	 
    	      		 $("#useMsg").html(useMsg + "条");
    	      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    	      		 $("#write").html(count);
    	 		 }else if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_CLASSTYPE"){
	      			 var count = ($("#msgcount").val()).length;
	      			 $("#write").html(count);
	      			 var person = $.trim($("#sendStu").text());
	      			 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 ) * parseInt(person);
	      			 
	      			 $("#useMsg").html(useMsg + "条");
	      			 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
	      			 $("#write").html(count);
    			}else if($(".btn-type.btn-primary").attr("data-type") == "STUDENT_MESSAGE_GROUP"){
    				 var count = ($("#msgcount").val()).length;
    	      		 $("#write").html(count);
    	      		 var person = parseInt($("#_sendStu").html());
    	      		 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 ) * parseInt(person);
    	        	 
    	      		 $("#useMsg").html(useMsg + "条");
    	      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    	      		 $("#write").html(count);
    			}else{
    				 var count = ($("#msgcount").val()).length;
    				 var phone = $.trim($("#phone").val());
    				 if(phone.indexOf(",") < 0){
    					 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : parseInt(count / 70) + 1 );
    		      		 $("#useMsg").html(useMsg + "条");
    		      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    				 }else{
    					 var phones = phone.split(",");
    					 var person = phones.length;
    		      		 var useMsg = parseInt((count % 70) == 0 ? (count / 70) : (count / 70) + 1 ) * parseInt(person);
    		      		 $("#useMsg").html(useMsg + "条");
    		      		 $("#SurootPathlus").html((msgCount - useMsg)+ "条");
    				 }
    	      		 $("#write").html(count);
    			}
    	 		break;
    	 }
     }
   //处理CKEDITOR的值
		function CKupdate() {
			for (instance in CKEDITOR.instances) {
				CKEDITOR.instances[instance].updateElement();
			}
		}
		//初始化
		function selectGroup1(type){
			$("#studentG1"+type).html('');
			 $.ajax({
		    	url: rootPath+"/studentGroup/selectStudentGroup1",
		    	type: "post",
		    	dataType: "json",
		    	async:false,
		    	success: function(jsonData){
		    		var id ;
//		    		$("#studentG1"+type).append('<option value="" selected="selected">全部</option>');
		    		$.each(jsonData,function(i, g){
		    			if(i==0) id = g.id;
		    			$("#studentG1"+type).append('<option value="' + g.id + '">'
						+ g.groupName + '</option>');
		    		})
		    		$("#studentG2"+type).append('<option value="" selected="selected">全部</option>');
		    		selectGroup2(id,'_edit');
		    	}
		     })
		}
		//一级切换事件
		function selectGroup2(a,type){
			$("#studentG2"+type).html('');
			if($("#studentG1"+type).val()==""){
				$("#studentG2"+type).append('<option value="" selected="selected">全部</option>');
				return false;
			}
			$.ajax({
		    	url: rootPath+"/studentGroup/selectStudentGroup2/"+$("#studentG1"+type).val(),
		    	type: "post",
		    	dataType: "json",
		    	async:false,
		    	success: function(jsonData){
		    		$("#studentG2"+type).append('<option value="" selected="selected">全部</option>');
		    		$.each(jsonData,function(i, g){
		    			$("#studentG2"+type).append('<option value="' + g.id + '">'
						+ g.groupName + '</option>');
		    		})
		    	}
		     })
		     selGroupStu({groupOneId:$("#studentG1"+type).val()});
		}
		//二级切换事件
		function selGroupStu(dataInfo){
			var reData = {};
			if(typeof dataInfo === 'object' && !dataInfo.hasOwnProperty('groupOneId')){
				reData.groupTwoId = $(dataInfo).find('option:selected').val();
			}else{
				reData = dataInfo;
			}
			if(!reData.groupTwoId){
				reData.groupOneId = $('#studentG1_edit').find('option:selected').val();
			}
			
			$.ajax({
		    	url: rootPath+"/studentGroup/selGroupStu",
		    	type: "post",
		    	dataType: "json",
		    	data : reData,
		    	async:false,
		    	success: function(jsonData){
		    		$('#groupStuCount').html(jsonData.count+'人');
		    		$('#_sendStu,#useEmailMsg').html(jsonData.count);
		    	}
		     })
		}
		