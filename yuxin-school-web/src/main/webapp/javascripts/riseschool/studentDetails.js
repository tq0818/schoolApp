//通过弹窗
$('.pass').click(function () {
    $('.opacityPopup').show();
    $('.confirmPopup').show();
    return false;
});
//隐藏弹窗
$('.hidePopup').click(function () {
    $('.opacityPopup').hide();
    $('.confirmPopup').hide();
    $('.reason').hide();
});
//不通过弹窗
$('.noPass').click(function () {
    $('.opacityPopup').show();
    $('.reason').show();
    $("input:checkbox").removeAttr("checked");
    $("#otherReason").val('');
    return false;
});
var id = $("#stuId").val();
var schoolId = $("#schoolId").val();
$('.studentDetailPass').click(function () {
	$.ajax({
    	type:"POST",
        url: rootPath + "/riseStudentSchoolTag/passStudent",
        data: {"id":id,"schoolId":schoolId},
        beforeSend: function(){
               $('.loading').show();
               $('.loading-bg').show();
        },
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            if(data=="success"){
	        	$.msg("保存成功");
	        	window.location.reload();
        	}else{
        		$.msg("保存失败");
        	}
        }
    });
});

$('.studentDetailNoPass').click(function () {
	var otherReason = $("#otherReason").val();
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
        data: {"id":id,"reason":reason,"schoolId":schoolId},
        beforeSend: function(){
               $('.loading').show();
               $('.loading-bg').show();
        },
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            if(data=="success"){
	        	$.msg("保存成功");
	        	window.location.reload();
        	}else{
        		$.msg("保存失败");
        	}
        }
    });
});

//点击图片放大
$('.clickImg img').click(function () {
    var image = new Image();
    image.src = $(this).attr('src');
    image.onload = function(){
        if(image.width>image.height){
            $('.bigImage').css('width','100%');
        }else {
            $('.bigImage').css('height','100%');
        }
    };
    $('.bigImage').attr('src',$(this).attr('src'));
    $('#bigImage').show();
    $('.opacityPopup').show();
    return false;
});
//点击其他地方关闭大图
$(document).click(function () {
    if($('#bigImage').css('display')=='block'){
        $('#bigImage').hide();
        $('.opacityPopup').hide();
    }
});
