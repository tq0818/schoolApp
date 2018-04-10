var whichChoose = 0;


$(function () {
	//初始化小学学年
	//queryRiseSchoolYear();
	//选择所属省份 初始化身份
    queryRiseSchoolDict(0);
    //发送到指定用户下拉列表
    $('#userListInput').keyup(function () {
        if(Number($(this).val().length)>0){
        	var searchCondition = $(this).val();
        	if(searchCondition.length != 11){
        		return;
        	}
            $.ajax({
                url: rootPath+"/riseSchoolManage/searchUsers",
                type:"post",
                data:{"searchCondition":searchCondition},
                dataType:"json",
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success:function(data){
                	$(".loading").hide();
                    $(".loading-bg").hide();
                    if (data.flag == 1){
                        var html = '';
                        var v ;
                        for (var i in data.dictList){
                        	if(data.dictList[i].nickName==null){
                        		v = '无'
                        	}else{
                        		v = data.dictList[i].nickName;
                        	}

                        	html = html + '<li data-value='+data.dictList[i].mobile+' '+'data-user='+v+','+data.dictList[i].mobile+' >'+v+','+data.dictList[i].mobile+'</li>';
                        }
                        $('.userList').html('').html(html);
                    }
                }
            });
            $('.userList').show();
        }else{
            $('.userList').hide();
        }
    });
    //点击选择
    $('.userList').on('mouseenter','li',function () {
        $(this).addClass('active');
        $(this).siblings('li').removeClass('active');
    });
    //点击li赋值给input
    $('.userList').on('click','li',function () {
        $('#userListInput').val('');
        $('.userList').hide();
        var userInfo = $(this).attr('data-user').split(",");

        var userInfoListAll = $('.userInfoListAll');

        if(userInfoListAll.length >0 ){
            var onOff = true;
            for(var i =0 ;i<userInfoListAll.length;i++){
                if(Number(userInfo[1])==Number(userInfoListAll.eq(i).html())){
                    $.msg("该条数据已有");
                    onOff = false;
                    break;
                }
            }
            if(onOff){
                var _html = `
                    <span class="c-content">
								<label for="" class="nameTitle">`+userInfo[0]+`</label>
								<span class="userInfoListAll">`+userInfo[1]+`</span>
								<i class="icon iconfont iconDelete">&#xe610;</i>
							</span>
                    `;
                $('.userListInfo').append(_html);
            }




        }else {
            var _html = `
                    <span class="c-content">
								<label for="" class="nameTitle">`+userInfo[0]+`</label>
								<span class="userInfoListAll">`+userInfo[1]+`</span>
								<i class="icon iconfont iconDelete">&#xe610;</i>
							</span>
        `;
            $('.userListInfo').append(_html);
        }
        /*for(var i = 0;i< $('.userInfoListAll').length;i++ ){
            console.log($('.userInfoListAll').eq(i).html())
        }*/
        sendMsgCount();

    });

    // 已选用户，点击删除删除用户
    $('.userListInfo').on('click','.iconDelete',function () {
        $(this).parent('span').remove();
        sendMsgCount();
    });
    // 点击站内信隐藏发送模板
    $('.font-style').children('a').eq(0).click(function () {
        $('.templete').show();
        $('.userType').hide();
    });
    $('.font-style').children('a').eq(1).click(function () {
        $('.templete').hide();
        $('.userType').show();
    });

    //点击发送通知，弹窗提示

    $('.btn-send').click(function () {
        $.confirm('是否确认发送信息给选定用户？',function (data) {
            if(data){
       
            	var registeredUser = 0;//注册用户
            	var noRegisteredUser = 0;//非注册用户
            	for(var i=0;i<$('.checkNew').length;i++){
	            	if($('.checkNew').eq(i).prop('checked')){
	            		if(i == 0){
	            			registeredUser = 1;
	            		}
	            		if(i == 1){
	            			noRegisteredUser = 1;
	            		}
	            	}
            	}
            	
            	//判断当前选中的是第几项
            	var radioList = $("input[type='radio']");
            	var checkChoose = '';
 	 			for(var i = 0;i< radioList.length;i++){
 	 	            if(radioList.eq(i).prop('checked')){
 	 	            	checkChoose = i;
 	 	            }
 	 	        }
 	 			var msgTemplateId = $("#messageId").val();
 	 			if(checkChoose == 1 || checkChoose == 2){
 	 				if(msgTemplateId == null || msgTemplateId == ''){
 	 					$.msg("模板id不能为空");
 	 					return ;
 	 				}
 	 			}
 	 			//拿到指定用户里面的电话号码
 	 			var usersMobile = ''
 	 			for(var i = 0;i< $('.userInfoListAll').length;i++ ){
 	 				if(i == $('.userInfoListAll').length - 1){
 	 					usersMobile = usersMobile + $('.userInfoListAll').eq(i).html();
 	 				}else{
 	 					usersMobile = usersMobile + $('.userInfoListAll').eq(i).html()+',';
 	 				}
 	 	        }
 	 			//return;
 	 			//省，市，区，学校，学段，年份
 	 			var province = $("#eduArea").val();
 	 			var city = $("#eduSchool").val();
 	 			var district = $("#registStatus").val();
 	 			var schoolCode = $("#schoolName").val();
 	 			var step = $("#step").val();
 	 			var stepYear = $("#stepYear").val();
 	 			
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
 	 				msgcount = msgcount.replace(/<p>/g, "<span>");
 	 				msgcount = msgcount.replace(/<p /g, "<span ");
 	 				msgcount = msgcount.replace(/<\/p>/g, "</span><br>");
 	 			}else if(method == 'STUDENT_MESSAGE_EMAIL'){
 	 				CKupdate();
 	 				msgcount = $("#email_newsContents").val();
 	 			}else{
 	 				msgcount = $("#msgcount").val();
 	 			}
 	 			if($(".btn-type.btn-primary").attr("data-type")=='STUDENT_MESSAGE_CLASSTYPE' && $(".btn-method.btn-primary").attr("data-type")!='STUDENT_MESSAGE_MOBILE'){
 		 			if(!msgcount){
 							$('<div class="c-fa">'+ "发送内容不能为空！" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 		 						$(this).remove();}
 		 					);
 		 					return false;
 		 			}
 	 			}

 				var sendStuNum=$("#sendStu").text();
 				if(null==sendStuNum||''==sendStuNum||parseInt(sendStuNum)<1){
 		    		$('<div class="c-fa">'+ "可发送学员人数为0!" +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){
 						$(this).remove();}
 					);
 					return;
 		    	}
 				
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
 	 				data:{
 	 					"title":title,
 	 					"content":msgcount,
 	 					"messageType":types,
 	 					"messageMethod":method,
 	 					"itemOneCode":oneItemCode,
 	 					"itemSecondCode":twoItemCode,
 	 					"itemThirdCode":threeItemCode,
 	 					"classTypeId":classId,
 	 					'groupOneId':groupOneId,
 	 					'groupTwoId':groupTwoId,
 	 					'email':email,
 	 					'emailTitle':emailTitle,
 	 					"phone":phone,
 	 					"moduleNoId":classId,
 	 					"isHurry":isHurry,
 	 					"lessonId":lessonId,
 	 	 				"contentText":msgcounttext,
 	 	 				"checkChoose":checkChoose,
 	 	 				"usersMobile":usersMobile,
 	 	 				"province":province,
 	 	 				"city":city,
 	 	 				"district":district,
 	 	 				"schoolCode":schoolCode,
 	 	 				"step":step,
 	 	 				"stepYear":stepYear,
 	 	 				"registeredUser":registeredUser,
 	 	 				"msgTemplateId":msgTemplateId,
 	 	 				"noRegisteredUser":noRegisteredUser},
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
            }else {
                console.log("点击了取消");
            }
        })
    });

    //短信通知页面，点击radio,发送模板编辑模式切换
    $("input[type='radio']").click(function () {
    	//当前选中的是第几项
    	var checkNmuber = Number($(this).val());
    	//选中第几项则加载当前项所需发送的短信数和人数
        if(checkNmuber == 0){
            $('#messageId').attr('disabled',true);
            $('#messageId').val('');
            whichChoose = checkNmuber;
            selPerson();
        }
        if(checkNmuber == 1){
        	$('#messageId').attr('disabled',false);
        	whichChoose = checkNmuber;
        	provinceMsgCount();
        }
        if(checkNmuber == 2){
        	$('#messageId').attr('disabled',false);
        	whichChoose = checkNmuber;
        	sendMsgCount();
        }
        if(checkNmuber == 3){
        	$('#messageId').attr('disabled',false);
        	whichChoose = checkNmuber;
        	registered();
        }
    });

});
//加载学年
function queryRiseSchoolYear(){
    var step = $("#step").val();
    if(step == ""){
    	var html = "<option value=\"\">请选择</option>"
    	$("#stepYear").html("").html(html);
    }
    $.ajax({
        url:rootPath +"/riseSchoolManage/queryRiseSchoolYear",
        data:{"step":step},
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function (data) {
            $(".loading").hide();
            $(".loading-bg").hide();
            //拼接下拉值
            if (data.flag == 1){
                var html = '';
                for (var i in data.dictList){
                    html = html + "<option value=\""+data.dictList[i]+"\">"+data.dictList[i]+"</option>"
                }
                html = "<option value=\"\">请选择</option>" + html;
                $("#stepYear").html("").html(html);
            }
        }
    });
    provinceMsgCount();
}
//查询省份，城市，区域等下拉信息 2018-2-7
function queryRiseSchoolDict(areaFlag) {
    var itemType = '';
    var itemCode = '';
    var eduAreaNew = $("#eduArea").val();
    if (areaFlag == 0){
        itemType = 'PROVINCE';
    }else if (areaFlag == 1){
        itemType = 'CITY';
        itemCode = $("#eduArea").val();
    }else if (areaFlag == 2){
        itemType = 'DISTRICT';
        itemCode = $("#eduSchool").val();
    }
    $.ajax({
        url:rootPath +"/riseSchoolManage/queryRiseSchoolDict",
        data:{"itemType":itemType,
              "itemCode":itemCode},
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function (data) {
            $(".loading").hide();
            $(".loading-bg").hide();
            //拼接下拉值
            if (data.flag == 1){
                var html = '';
                for (var i in data.dictList){
                    html = html + "<option value=\""+data.dictList[i].itemCode+"\">"+data.dictList[i].itemName+"</option>"
                }
                if (areaFlag == 0){
                    html = "<option value=\"\">请选择省份</option>" + html;
                    $("#eduArea").html("").html(html);
                }else if (areaFlag == 1){
                	if(eduAreaNew == ""){
                		html = "<option value=\"\">请选择市</option>";
                	}else{
                		html = "<option value=\"\">请选择市</option>" + html;
                	}
                    $("#eduSchool").html("").html(html);
                    html2 = "<option value=\"\">请选择区</option>";
                    $("#registStatus").html("").html(html2);
                }else if (areaFlag == 2){
                	var eduSchool = $("#eduSchool").val();
                	if(eduSchool == ""){
                		html = "<option value=\"\">请选择区</option>";
                	}else{
                		html = "<option value=\"\">请选择区</option>" + html;
                	}
                    $("#registStatus").html("").html(html);
                }
            }
        }
    });
    if (areaFlag != 0){
    	provinceMsgCount();
    }
}
//加载学校
function querySchoolName() {
	var registStatus = $("#registStatus").val();
	if(registStatus == ""){
		 var html = "<option value=\"\">请选择学校</option>";
        $("#schoolName").html("").html(html);
		return;
	}
	$.ajax({
        url:rootPath +"/riseSchoolManage/querySchoolName",
        data:{"registStatus":registStatus},
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function (data) {
            $(".loading").hide();
            $(".loading-bg").hide();
            //拼接下拉值
            if (data.flag == 1){
                var html = '';
                for (var i in data.dictList){
                    html = html + "<option value=\""+data.dictList[i].itemCode+"\">"+data.dictList[i].itemName+"</option>"
                }
                html = "<option value=\"\">请选择学校</option>" + html;
                $("#schoolName").html("").html(html);
            }
        }
    });
	provinceMsgCount();
}
//选中指定用户时发送短信数量
function sendMsgCount() {
	if(whichChoose != 2){
		return ;
	}
	var count = $('.userInfoListAll').length;
	$("#useMsg").html(count+"条");
	$("#sendStu,#useEmailMsg").html(count);
}
//选中学校时发送短信数量
function provinceMsgCount() {
	if(whichChoose != 1){
		return ;
	}
	//省，市，区，学校，学段，年份
	var province = $("#eduArea").val();
	var city = $("#eduSchool").val();
	var district = $("#registStatus").val();
	var schoolCode = $("#schoolName").val();
	var step = $("#step").val();
	var stepYear = $("#stepYear").val();
	$.ajax({
        url:rootPath +"/riseSchoolManage/provinceMsgCount",
        data:{"province":province,
        	"city":city,
        	"district":district,
        	"schoolCode":schoolCode,
        	"step":step,
        	"stepYear":stepYear},
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function (data) {
            $(".loading").hide();
            $(".loading-bg").hide();
            //拼接下拉值
            if (data.flag == 1){
            	$("#useMsg").html(data.count+"条");
            	$("#sendStu,#useEmailMsg").html(data.count);
            }
        }
    });
}
//选中课程时发送短信数量
function selPerson(){
	if(whichChoose != 0){
		return ;
	}
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
//选中注册用户时发送站内信数量
function registered(){
	if(whichChoose != 3){
		return ;
	}
	var registeredUser = 0;//注册用户
	var noRegisteredUser = 0;//非注册用户
	for(var i=0;i<$('.checkNew').length;i++){
    	if($('.checkNew').eq(i).prop('checked')){
    		if(i == 0){
    			registeredUser = 1;
    		}
    		if(i == 1){
    			noRegisteredUser = 1;
    		}
    	}
	}
	$.ajax({
        url:rootPath +"/riseSchoolManage/loginUserCount",
        data:{"registeredUser":registeredUser,
        	"noRegisteredUser":noRegisteredUser},
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function (data) {
            $(".loading").hide();
            $(".loading-bg").hide();
            //拼接下拉值
            if (data.flag == 1){
            	$("#useMsg").html(data.count+"条");
            	$("#sendStu,#useEmailMsg").html(data.count);
            }
        }
    });
}	