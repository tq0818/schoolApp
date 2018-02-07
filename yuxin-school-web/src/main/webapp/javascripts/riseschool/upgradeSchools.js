// 学校详情
$(function () {
    var menuList = $('.headContent').find('li');
    //点击保存，获取menu的名称
    $('.btnSave').click(function () {
        for(var i= 0;i<menuList.length;i++){
            if(menuList.eq(i).hasClass('active')){
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
           	        },
           	        success : function(data){
           	        	if(data=="success"){
           	        		$.msg("保存成功");
           	        		window.location.reload();
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