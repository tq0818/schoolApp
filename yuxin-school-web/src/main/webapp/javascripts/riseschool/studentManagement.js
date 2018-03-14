//小升初，学校管理
$(function () {
	var checkNum = sessionStorage.getItem('checkNum');
		if(checkNum == null){
			checkNum = 0;
		}
		for(var i=0;i<4;i++){
			if(checkNum==i){
				$('.isCheck').children('a').eq(i).siblings('a').addClass('btn-default').removeClass('btn-primary');
				$('.isCheck').children('a').eq(i).addClass('btn-primary').removeClass('btn-default');
			}			
		}
	
    $('.searchStudentManagement').click(function () {
    	queryStudentApply(1);
    });
    //审核状态选择
    $('.isCheck').children('a').click(function () {
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
        $('#isCheckBtn').val($(this).attr('data-value'));
        sessionStorage.setItem('reloadCheckNum',$(this).attr('data-value'));
        queryStudentApply(1);
    });
    $(".exportexcleStudent").on(
            'click',
            function () {
                if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                    $.msg("没有数据可以导出");
                } else {
                    //申请学校
                    var schoolName = $("#schoolName").val();
                    if(schoolName == -1){
                    	alert("请选择学校!");
                    	return;
                    }
                    //提交时间
                    var from = $(".from").val();
                    var to = $(".to").val();
                    //两个时间不为空时，则需要判断时间大小
                    if (from !=null && to != null){
                        if (parseInt(from.replace(/-/g,"")) > parseInt(to.replace(/-/g,""))){
                            $(".from").val("");
                            $(".to").val("");
                            alert("左边时间不能晚于右边时间!");
                            return;
                        }
                    }
                    $("#searchFormStudent").attr("action",
                            rootPath + "/riseStudentSchoolTag/exportExcle")
                        .submit();
                    
                }

            });
    
});

//查询
function queryStudentApply(pageNo) {
	//审核状态
    var isCheck = $(".isCheck .btn-primary").attr("data-value");
    //申请学校
    var schoolName = $("#schoolName").val();
    if(schoolName == -1){
    	alert("请选择学校!");
    	return;
    }
    //提交时间
    var from = $(".from").val();
    var to = $(".to").val();
    //两个时间不为空时，则需要判断时间大小
    if (from !=null && to != null){
        if (parseInt(from.replace(/-/g,"")) > parseInt(to.replace(/-/g,""))){
            $(".from").val("");
            $(".to").val("");
            alert("左边时间不能晚于右边时间!");
            return;
        }
    }
    //姓名到毕业学校
    var studentName = $("#studentName").val();
    var mobile = $("#mobile").val();
    var studentNo = $("#studentNo").val();
    var SchoolTag = $("#SchoolTag").val();
    //提交时间排序
    var timeOrder = $("#timeOrder").val();
    $.ajax({
    	type:"POST",
        url: rootPath + "/riseStudentSchoolTag/queryStudentSchoolTag",
        data: {"page":pageNo,
        	   "isCheck":isCheck,
               "schoolName":schoolName,
                "startTime":from,
                "endTime":to,
                "studentName":studentName,
                "mobile":mobile,
                "studentNo":studentNo,
                "SchoolTag":SchoolTag,
                "timeOrder":timeOrder
                
        },
        beforeSend: function(){
               $('.loading').show();
               $('.loading-bg').show();
        },
        dataType: "html",
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            $(".user-list").html("").html(data);
        }
    });
    
}

//通过
function pass() {
	var id = passId;
	var schoolNoId = schoolId;
	var passPageNo = $("#passPageNo").val();
	$.ajax({
    	type:"POST",
        url: rootPath + "/riseStudentSchoolTag/passStudent",
        data: {"id":id,"schoolId":schoolNoId},
        beforeSend: function(){
               $('.loading').show();
               $('.loading-bg').show();
        },
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            if(data=="success"){
	        	$.msg("保存成功");
	        	queryStudentApply(passPageNo);
        	}else{
        		$.msg("保存失败");
        	}
        }
    });
}
//不通过
function noPass() {
	var passPageNo = $("#passPageNo").val();
	var otherReason = $("#otherReason").val();
	var schoolNoId = schoolId;
	obj = document.getElementsByName("noPassReason");
    check_val = [];
    for(k in obj){
    	if(obj[k].checked)
            
            if(obj[k].value == "其他" && otherReason == ''){
        		$.msg("请输入不通过原因");
        		return;
        	}else if(obj[k].value == "其他"){
        		check_val.push(otherReason);
        	}
            else{
        		check_val.push(obj[k].value);
        	}
    }
    var reason = check_val.join("@");
    if(reason == ''){
    	$.msg("请选择不通过原因");
		return;
    }
    $.ajax({
    	type:"POST",
        url: rootPath + "/riseStudentSchoolTag/NopassStudent",
        data: {"id":noPassId,"reason":reason,"schoolId":schoolNoId},
        beforeSend: function(){
               $('.loading').show();
               $('.loading-bg').show();
        },
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            if(data=="success"){
	        	$.msg("保存成功");
	        	queryStudentApply(passPageNo);
        	}else{
        		$.msg("保存失败");
        	}
        }
    });
}
//查看
function watch(id,schoolId,isCheck) {
	var page = $("#passPageNo").val();
	sessionStorage.setItem('page', page);
	var checkNum = $('.isCheck').children('a')
	for(var i=0;i<checkNum.length;i++){
		if(checkNum.eq(i).hasClass('btn-primary')){
			sessionStorage.setItem('checkNum',i);
		}			
	}

	//post跳转，目前无法传参数
/*	document.write("<form id='kkk' action="+rootPath + "/riseStudentSchoolTag/studentDetails method=post name=formx1 style='display:none'>");
	document.write("<input type='hidden' id='studentId' name='studentId' value='"+id+"'>");
	document.write("<input type='hidden' id='schoolId' name='schoolId' value='"+schoolId+"'>");
	document.write("</form>");*/
	$("#studentId").val(id);
	$("#schoolId").val(schoolId);
	$("#isCheck").val(isCheck);
	$("#watchStudentDetails").submit();
	//window.location.href = rootPath + "/riseStudentSchoolTag/studentDetails?id="+id+"&schoolId="+schoolId;
	
}

reloadStudentApply();
function reloadStudentApply() {
	var pageNo = JSON.parse(sessionStorage.getItem('page'));
	sessionStorage.removeItem('page');
	//审核状态
    var isCheck =  sessionStorage.getItem('reloadCheckNum');
    //sessionStorage.removeItem('checkNum');
    //申请学校
    var schoolName = $("#schoolName").val();
    if(schoolName == -1){
    	return;
    }
    //提交时间
    var from = $(".from").val();
    var to = $(".to").val();
    //两个时间不为空时，则需要判断时间大小
    if (from !=null && to != null){
        if (parseInt(from.replace(/-/g,"")) > parseInt(to.replace(/-/g,""))){
            $(".from").val("");
            $(".to").val("");
            alert("左边时间不能晚于右边时间!");
            return;
        }
    }
    //姓名到毕业学校
    var studentName = $("#studentName").val();
    var mobile = $("#mobile").val();
    var studentNo = $("#studentNo").val();
    var SchoolTag = $("#SchoolTag").val();
    //提交时间排序
    var timeOrder = $("#timeOrder").val();
    $.ajax({
    	type:"POST",
        url: rootPath + "/riseStudentSchoolTag/queryStudentSchoolTag",
        data: {"page":pageNo,
        	   "isCheck":isCheck,
               "schoolName":schoolName,
                "startTime":from,
                "endTime":to,
                "studentName":studentName,
                "mobile":mobile,
                "studentNo":studentNo,
                "SchoolTag":SchoolTag,
                "timeOrder":timeOrder
                
        },
        beforeSend: function(){
               $('.loading').show();
               $('.loading-bg').show();
        },
        dataType: "html",
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            $(".user-list").html("").html(data);
            sessionStorage.removeItem('checkNum');
        }
    });
    
}



