//小升初，学校管理
$(function () {
    $('.searchStudentManagement').click(function () {
    	queryStudentApply(1);
    });
    //审核状态选择
    $('.isCheck').children('a').click(function () {
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
        $('#isCheckBtn').val($(this).attr('data-value'));
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
	var passPageNo = $("#passPageNo").val();
	$.ajax({
    	type:"POST",
        url: rootPath + "/riseStudentSchoolTag/passStudent",
        data: {"id":id},
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
	alert(noPassId);
}
//查看
function watch(id) {
	alert(id);
}




