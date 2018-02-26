// 学校详情
$(function () {
    var menuList = $('.headContent').find('li');
    //点击保存，获取menu的名称
    $('.btnSave').click(function () {
        for(var i= 0;i<menuList.length;i++){
            if(menuList.eq(i).hasClass('active')){
				var _this = $(menuList.eq(i));
            	var riseSchoolId  = $("#riseSchoolId").val();
            	var itemDiscrible  = $("#footerContentDetail").val();
            	var itemName = menuList.eq(i).children('a').html();
            	if(itemDiscrible.length > 2000){
        			$.msg("最多两千字");
        			return;
        		}
            	$.ajax({
           	        type : 'post',
           	        url : rootPath + '/riseSchoolDetailsUp/saveOrupdateUpgradeSchool',
           	        data : {
           	        	riseSchoolId:riseSchoolId,
           	        	itemDiscrible:itemDiscrible,
           	        	itemName:itemName
           	        },beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
           	        success : function(data){
                        $(".loading").hide();
                        $(".loading-bg").hide();
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
           	        url : rootPath + '/riseSchoolDetailsUp/queryUpgradeSchool',
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