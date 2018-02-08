//动态
$(function () {
	var checkId,changeId,deleteId; 
    //点击修改
    $('.change').click(function () {
    	changeId = $(this).attr('id');
    	$.ajax({
   	        type : 'post',
   	        url : rootPath + '/riseSchoolDynamic/checkDynamic',
   	        data : {
   	        	id:changeId
   	        },
   	        success : function(data){
   	        	$('#changeTitle').val(data.tittle);
   	        	$('#changeContent').html(data.content);
   	        }
   	    });
        $('.opacityPopup').fadeIn();
        $('.changeNews').fadeIn();
        
        
    });
    //点击修改确定
    $('.addNewsBtnSave').click(function () {
    	var riseSchoolId = $("#riseSchoolId").val();
    	var tittle = $("#changeTitle").val();
    	var content = $("#changeContent").val();
    	$.ajax({
   	        type : 'post',
   	        url : rootPath + '/riseSchoolDynamic/addDynamic',
   	        data : {
   	        	id:changeId,
   	        	tittle:tittle,
   	        	content:content,
   	        	riseSchoolId:riseSchoolId
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
        $('.opacityPopup').fadeOut();
        $('.addNews').fadeOut();
        console.log(changeId);
    });
    //点击取消
    $('.addNewsBtnCancel').click(function () {
        $('.opacityPopup').fadeOut();
        $('.addNews').fadeOut();
    });
    //点击查看
    $('.check').click(function () {
       
        var id = $(this).attr('id');
        $.ajax({
   	        type : 'post',
   	        url : rootPath + '/riseSchoolDynamic/checkDynamic',
   	        data : {
   	        	id:id
   	        },
   	        success : function(data){
   	        	$('.titleCheck').html(data.tittle);
   	        	$('.contentCheck').html(data.content);
   	        }
   	    });
        $('.opacityPopup').fadeIn();
        $('.checkNews').fadeIn();
    });
    //点击删除
    $('.delete').click(function () {
    	var id = $(this).attr('id');
        $.confirm('是否删除该条动态？',function (data) {
            if(data){
            	$.ajax({
           	        type : 'post',
           	        url : rootPath + '/riseSchoolDynamic/deleteDynamic',
           	        data : {
           	        	id:id
           	        },
           	        success : function(data){
           	        	if(data=="success"){
           	        		$.msg("删除成功");
           	        		window.location.reload();
           	        	}else{
           	        		alert("删除失败");
           	        	}
           	        }
           	    });
            }
        })
    });
    //点击查看确定
    $('.addNewsBtncheck').click(function () {
    	$('.opacityPopup').fadeOut();
    	$('.addNews').fadeOut();
    });
});