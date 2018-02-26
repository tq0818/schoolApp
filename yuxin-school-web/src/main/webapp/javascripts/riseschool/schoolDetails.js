// 学校详情
$(function () {
    var menuList = $('.headContent').find('li');
    //点击保存，获取menu的名称
    $('.btnSave').click(function () {
        for(var i= 0;i<menuList.length;i++){
            if(menuList.eq(i).hasClass('active')){
                var _this = menuList.eq(i);
            	var riseSchoolId  = $("#riseSchoolId").val();
            	var itemDiscrible  = $("#footerContentDetail").val();
            	var itemName = menuList.eq(i).children('a').html();
            	if(itemDiscrible.length > 2000){
        			$.msg("最多两千字");
        			return;
        		}
            	$.ajax({
           	        type : 'post',
           	        url : rootPath + '/riseSchoolDetailsUp/saveOrupdateSchoolDetails',
           	        data : {
           	        	riseSchoolId:riseSchoolId,
           	        	itemDiscrible:itemDiscrible,
           	        	itemName:itemName
           	        },
                    beforeSend: function () {
                        $('.loading').show();
                        $('.loading-bg').show();
                    },
           	        success : function(data){
                        $('.loading').hide();
                        $('.loading-bg').hide();
           	        	if(data=="success"){
           	        		$.msg("保存成功");
           	        		//window.location.reload();
           	        		if(itemDiscrible.length > 0){
           	        			_this.append('<i class="icon iconfont iconDetails">&#xe611;</i>');
           	        			_this.click();
           	        		}else{
           	        			_this.find("i").remove();
           	        		}
           	        	}else{
           	        		alert("保存失败");
           	        	}
           	        }
           	    });
            }
        }
    });
    //点击menu，切换class
    menuList.click(function () {
        $(this).addClass('active');
        $(this).siblings('li').removeClass('active');
        for(var i= 0;i<menuList.length;i++){
            if(menuList.eq(i).hasClass('active')){
            	var itemName = menuList.eq(i).children('a').html();
            	var riseSchoolId  = $("#riseSchoolId").val();
            	$.ajax({
           	        type : 'post',
           	        url : rootPath + '/riseSchoolDetailsUp/queryRiseDetails',
           	        data : {
           	        	riseSchoolId:riseSchoolId,
           	        	itemName:itemName
           	        },
           	        success : function(data){
           	        	//footerContentDetail
           	        	if(data!=null){
           	        		$('#footerContentDetail').val(data.itemDiscrible);
           	        	}else{
           	        		$('#footerContentDetail').val('');
           	        	}
           	        }
           	    });
            }
        }
    });
});

//区域联动查询
function queryRiseSchoolDict(areaFlag) {
    var itemType = '';
    var itemCode = '';
    if (areaFlag == 1){
    	if ($("#province").val() == null || $("#province").val() == ''){
            itemType = 'PROVINCE';
		}else {
            itemType = 'CITY';
            itemCode = $("#province").val();
		}
    }else if (areaFlag == 2){
        itemType = 'DISTRICT';
        itemCode = $("#city").val();
    }
    $.ajax({
        url:rootPath +"/riseSchoolManage/queryRiseSchoolDict",
        data:{"itemType":itemType,
            "itemCode":itemCode},
        dataType:"json",
        success:function (data) {
            //拼接下拉值
            if (data.flag == 1){
                var html = '';
                for (var i in data.dictList){
                    html = html + "<option value=\""+data.dictList[i].itemCode+"\">"+data.dictList[i].itemName+"</option>"
                }
                if (itemType == 'PROVINCE'){
                    html = "<option value=\"\">请选择省份</option>" + html;
                    $("#province").html("").html(html);
                    $("#city").html("").html("<option value=\"\">请选择市</option>");
                    $("#area").html("").html("<option value=\"\">请选择区</option>");
                }else if (itemType == 'CITY'){
                    html = "<option value=\"\">请选择市</option>" + html;
                    $("#city").html("").html(html);
                    $("#area").html("").html("<option value=\"\">请选择区</option>");
                }else if (itemType == 'DISTRICT'){
                    html = "<option value=\"\">请选择区</option>" + html;
                    $("#area").html("").html(html);
                }
            }
        }
    });
}

//新增学校请求接口 2018-2-7 zj
function updateRiseSchoolInfo() {
    var province = $("#province").val();
    var city = $("#city").val();
    var district = $("#area").val();
    var schoolAddress = $("#schoolAddress").val();
    var schoolWeb = $("#schoolWeb").val();
    var schoolFax = $("#schoolFax").val();
    var busRoad = $("#busRoad").val();
    var collectBaseCount = $("#collectBaseCount").val();
    var schoolId = $("#schoolId").val();
    if (province == null || province == '' || city == null || city == '' || district == null || district == ''
        || schoolAddress == null || schoolAddress == ''){
        $.msg("学校地址存在未录入项",2000);
        return ;
    }

    if (collectBaseCount == null || collectBaseCount == ''){
        collectBaseCount = 0;
	}
    if(parseInt(collectBaseCount)<0 || parseInt(collectBaseCount)>10000){
        $.msg("收藏基数请输入0-10000");
        return ;
    }
    $.ajax({
        type:"POST",
        url : rootPath +"/riseSchoolManage/updateRiseSchoolInfo",
        data : {
            "provinceCode":province,
            "cityCode":city,
            "district":district,
            "detailAddress":schoolAddress,
            "schoolWeb":schoolWeb,
            "schoolFax":schoolFax,
            "busRoad":busRoad,
            "baseNum":collectBaseCount,
			"id":schoolId
        },
        dataType:"json",
        success : function(data) {
            if (data.flag == 1){
                $.msg(data.msg);
                // $('#schoolBtn').find(".countPopupSave").removeAttr("id");
                // //重新请求查询接口
                // queryRiseSchoolInfo(1);
            }else {
                $.msg(data.msg);
            }
        }
    });
}
//检验网址
var reg=/^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
function judgeSchoolWeb() {
    var schoolWeb = $("#schoolWeb").val();
    if(!schoolWeb)return;
    if (schoolWeb != null || schoolWeb != ""){
        if (!reg.test(schoolWeb)){
            $.msg("请输入有效的网址!",1000);
            $("#schoolWeb").val("")
            return ;
        }
    }
}


