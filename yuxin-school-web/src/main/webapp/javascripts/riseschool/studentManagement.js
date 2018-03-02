//小升初，学校管理
$(function () {
    $('.searchStudentManagement').click(function () {
    	queryStudentApply(1);
    });
    //审核状态选择
    $('.isCheck').children('a').click(function () {
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
    });
    
});

//查询学校信息
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
    console.log(timeOrder);
    $.ajax({
    	type:"POST",
        url: rootPath + "/riseStudentSchoolTag/queryStudentSchoolTag",
        data: {"page":pageNo,
        	   "isCheck":isCheck,
               "schoolName":schoolName,
                "from":from,
                "to":to,
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

//模糊搜索
function queryDimRiseSchoolInfo(pageNo) {
	var enrolment = $('.enrolment').children('a');
	var enrolmentVal;
	for(var i= 0;i<enrolment.length;i++){
		if(enrolment.eq(i).hasClass('btn-primary')){
			enrolmentVal = enrolment.eq(i).attr('data-value');
		}
	}
	var topState = $('.topState').children('a');
	var topStateVal;
	for(var i= 0;i<topState.length;i++){
		if(topState.eq(i).hasClass('btn-primary')){
			topStateVal = topState.eq(i).attr('data-value');
		}
	}
    var eduArea = $("#eduArea").val();
    var eduSchool = $("#eduSchool").val();
    var registStatus = $("#registStatus").val();
    var registMethods = $("#registMethods").val();
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
    var schoolShortName = $("#schoolShortName").val();
    $.ajax({
        url: rootPath + "/riseSchoolManage/queryDimRiseSchoolInfo",
        data: {"page":pageNo,
            "pagesize":12,
            "provinceCode":eduArea,
            "cityCode":eduSchool,
            "district":registStatus,
            "isShalve":registMethods,
            "startTime":from,
            "endTime":to,
            "isTop":topStateVal,
            "enrollmentType":enrolmentVal,
            "schoolName":schoolShortName
        },beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        dataType: "html",
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            $(".user-list").html("").html(data);
        }
    });

}

//更新上下架或者置顶
function updateRiseSchool(id,isShalves,isTop) {
    $.ajax({
        type:"POST",
        url: rootPath + "/riseSchoolManage/updateRiseSchoolInfo",
        data: {
            "id":id,
            "isShalve":isShalves,
            "isTop":isTop
        },
        dataType: "json",
        success: function (data) {
            if (data.flag == 1){
                //成功之后进行刷新
                queryDimRiseSchoolInfo(1);
            }else {
                $.msg(data.msg);
            }
        }
    });
}

//更新账户密码
function updateAccount() {
    var key = $("#password").val();
    if (key == null || key == ''){
        $.msg("密码不能为空");
        return ;
    }
    $.ajax({
        url: rootPath + "/riseSchoolManage/updateUsersInfo",
        data: {
            "id":$("#accountUserName").attr("value"),
            "username":$("#accountUserName").text(),
            "password":key
        },
        dataType: "json",
        success: function (data) {
            $.msg(data.msg);
            $("#password").val(""); 
            $('.opacityPopup').fadeOut();
            $('.countPopup').fadeOut();
            $('.addNewSchool').fadeOut();
        }
    });
}

//页面跳转
function loalUrl(flag,schoolId,schoolName) {
    if (flag == 0){//基本
        window.location.href = rootPath + "/riseschoolback/essential?schoolId="+schoolId;
    }else if (flag == 1){//详情
        window.location.href = rootPath + "/riseschoolback/schoolDetails?schoolId="+schoolId;
    }else if (flag == 2){//风采
        window.location.href = rootPath + "/riseschoolback/mien?schoolId="+schoolId;
    }else if (flag == 3){//升学
        window.location.href = rootPath + "/riseschoolback/upgradeSchools?schoolId="+schoolId;
    }
}

//点击通过显示弹窗
$('.pass').click(function () {
    $('.confirmPopup').fadeIn();
    $('.opacityPopup').fadeIn();
});
//点击取消或者确认通过隐藏弹窗
$('.hidePopup').click(function () {
    $('.confirmPopup').fadeOut();
    $('.opacityPopup').fadeOut();
    $('.reason').fadeOut();
});

//点击不通过弹出弹窗
$('.noPass').click(function () {
    $('.reason').fadeIn();
    $('.opacityPopup').fadeIn();
});


