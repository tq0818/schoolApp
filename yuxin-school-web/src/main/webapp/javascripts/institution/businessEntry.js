$(function () {
    //选中二级菜单
    $selectSubMenu('organizationIndex');
    //分页插件
//    $(".pagination").pagination('', {
//        next_text : "下一页",
//        prev_text : "上一页",
//        current_page : 1,
//        link_to : "javascript:void(0)",
//        num_display_entries : 8,
//        items_per_page : 12,
//        num_edge_entries : 1,
//        callback:function(page){
//            var pageNo = page + 1;
//
//        }
//    });
    //添加备注弹窗     //点击备注，弹出弹窗
    $('.addRemarks').click(function () {
        $('.remarks').show();
    });
    $('.remarksBtn').children('a').click(function () {
        $('.remarks').hide();
        console.log("是否清理:"+$("#updateId").val()+" ,"+$("#content").text());
    });
    //tab切换
    $('.changeBtn').children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
    });
    
    queryMerchantEntry(1);
    
    //设值
    $(".btn-default").click(function(){
    	$("#dimFlag").attr("value",0);
    	dimMerchantEntry(1);
    });
    //时间点击事件#
    $("#startTime").click(function(){
    	$("#dimMobile").val("");
    	$("#dimInstitu").val("");
    	$("#dimFlag").attr("value",0);
    });
    $("#endTime").click(function(){
    	$("#dimMobile").val("");
    	$("#dimInstitu").val("");
    	$("#dimFlag").attr("value",0);
    });
    //右边搜索条件
    $("#dimMobile").click(function(){
    	console.log("111");
    	$("#startTime").val("");
    	$("#endTime").val("");
    	$("#dimFlag").attr("value",1);
    	$('.changeBtn').find('a').each(function (i) {
    		if(i == 0){
    			$(this).addClass('btn-primary');
    		}else{
    			$(this).removeClass('btn-primary');
    		}
    	});
    });
    $("#dimInstitu").click(function(){
    	$("#startTime").val("");
    	$("#endTime").val("");
    	$("#dimFlag").attr("value",1);
    	$('.changeBtn').find('a').each(function (i) {
    		if(i == 0){
    			$(this).addClass('btn-primary');
    		}else{
    			$(this).removeClass('btn-primary');
    		}
    	});
    });
});

//查询商家入驻
function queryMerchantEntry(page){
	$.ajax({
        url: rootPath + "/merchantEntry/queryMerchantEntry",
        data: {"page":page,
            "pageSize":10
        },beforeSend: function (XMLHttpRequest) {
           /* $(".loading").show();
            $(".loading-bg").show();*/
        },
        dataType: "html",
        success: function (data) {
//            $(".loading").hide();
//            $(".loading-bg").hide();
            $(".user-list").html("").html(data);
        }
    });
}

//更新状态和备注
function updateMerchantEntry(id,dealStauts){
	var status = dealStauts;
	$.ajax({
		 url: rootPath + "/merchantEntry/updateMerchantEntry",
	        data: {"id":id,
	            "dealStatus":1-status
	        },
	        type:"post",
	        dataType: "json",
	        success: function (data) {
	        	if(data == "success"){
	        		//成功刷新
	        		queryMerchantEntry($("#pageNo").val());
	        	}
	        }
	});
}

//备注保存
function updateMerchantEntryT(){
	console.log($("#updateId").val() +","+$("#content").val());
	$.ajax({
		 url: rootPath + "/merchantEntry/updateMerchantEntry",
	        data: {"id":$("#updateId").val(),
	            "note":$("#content").val(),
	        },
	        type:"post",
	        dataType: "json",
	        success: function (data) {
	        	if(data == "success"){
	        		 //保存和取消都应该清理掉上一次的值
	                $("#updateId").val("");
	                $("#content").text("");
	        		//成功刷新
	        		queryMerchantEntry($("#pageNo").val());
	        	}
	        }
	});
}

//模糊查询 dimFlag 0 是左边筛选条件，1是右边筛选条件
function dimMerchantEntry(page){
	var dimFlag = $("#dimFlag").val();
	if(dimFlag == ""){
		$.msg("未录入筛选项");
		return false;
	}
	var dealStatus = $(".changeBtn").find(".btn-primary").attr("data-value");
	var leftTime = $("#startTime").val();
	var rightTime = $("#endTime").val();
	var mobile = $("#dimMobile").val();
	var insName = $("#dimInstitu").val();
    //两个时间不为空时，则需要判断时间大小
    if (leftTime !=null && rightTime != null){
        if (parseInt(leftTime.replace(/-/g,"")) > parseInt(rightTime.replace(/-/g,""))){
            $("#startTime").val("");
            $("#endTime").val("");
            alert("左边时间不能晚于右边时间!");
            return;
        }
    }
	$.ajax({
        url: rootPath + "/merchantEntry/dimMerchantEntry",
        data: {"page":page,
            "pageSize":10,
            "dimFlag":$("#dimFlag").val(),
            "dealStatus":dealStatus == 2?null:dealStatus,
            "leftTime":leftTime == ""?null:leftTime,
            "rightTime":rightTime == ""?null:rightTime,
            "mobile":mobile,
            "insName":insName
        },
        type:"post",
        dataType: "html",
        success: function (data) {
            $(".user-list").html("").html(data);
        }
    });
}

