//动态
$(function () {
	var checkId,changeId,deleteId; 
    //点击修改
    $('#dataList').on('click','.change',function () {
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
    $('.addNewsBtnUpdate').click(function () {
    	var riseSchoolId = $("#riseSchoolId").val();
    	var tittle = $("#changeTitle").val();
    	if(tittle == null || tittle == ""){
    		$.msg("标题不能为空");
			return;
    	}
    	var content = $("#changeContent").val();
    	if(content == null || content == ""){
    		$.msg("正文不能为空");
			return;
    	}
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
    });
    //点击取消
    $('.addNewsBtnCancel').click(function () {
        $('.opacityPopup').fadeOut();
        $('.addNews').fadeOut();
    });
    //点击查看
    $('#dataList').on('click','.check',function () {
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
    $('#dataList').on('click','.delete',function () {
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
    //点击新增
    $('.addNewDynamic').click(function () {

        $('.opacityPopup').fadeIn();
        $('.saveNews').fadeIn();


    });
    //点击新增确定
    $('.addNewsBtnSave').click(function () {
    	var riseSchoolId = $("#riseSchoolId").val();
    	var tittle = $("#saveTitle").val();
    	if(tittle == null || tittle == ""){
    		$.msg("标题不能为空");
			return;
    	}
    	var content = $("#saveContent").val();
    	if(content == null || content == ""){
    		$.msg("正文不能为空");
			return;
    	}
    	$.ajax({
   	        type : 'post',
   	        url : rootPath + '/riseSchoolDynamic/addDynamic',
   	        data : {
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
    });
    
    //加载数据
    function dataList(page) {
        var $this = this;
        var data = {};
        data.riseSchoolId = $("#riseSchoolId").val();
        data.page = page ? page : 1;
        $('#dataList').empty();
        $.ajax({
            url: rootPath + "/riseSchoolDynamic/queryAllDynamic",
            data: data,
            type: 'post',
            beforeSend: function (XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
            success: function (jsonData) {
            	$(".loading").hide();
                $(".loading-bg").hide();
                $.each(jsonData.data.data,function(i, dynamic) {
                    $('#dataList').append('<li>' +
                        '<ul class="dynamicList">' +
                        '<li>' + dynamic.tittle + '</li>' +
                        '<li class="dashedLi"><span class="dashed"></span></li>' +
                        '<li>' + dynamic.updateTime + '</li>' +
                        '<li class="dynamicBtn">' +
                        '<a href="##" class="btn btn-primary btn-sm check" id="' + dynamic.id + '">查看</a>' +
                        '<a href="##" class="btn btn-success btn-sm change" id="' + dynamic.id + '">修改</a>' +
                        '<a href="##" class="btn btn-danger btn-sm delete" id="' + dynamic.id + '">删除</a>' +
                        '</li>' +
                        '</ul>' +
                        '</li>');
                    $("#rowCount").remove();
                    $("#pageNo").remove();
                    $("#dataList").after('<input type="hidden" id="pageNo" value="' + jsonData.pageNo + '"/>');
                       if(jsonData.data.rowCount >jsonData.data.pageSize){ 
                    	$(".pagination").pagination(jsonData.data.rowCount,
                            {
                                next_text: "下一页",
                                prev_text: "上一页",
                                current_page: jsonData.data.pageNo - 1,
                                link_to: "javascript:void(0)",
                                num_display_entries: 5,
                                items_per_page: jsonData.data.pageSize,
                                num_edge_entries: 1,
                                callback: function (page, jq) {
                                    var pageNo = page + 1;
                                    //$this.dataList(pageNo);
                                    dataList(pageNo);
                                }
                            });
                        $(".pagination").find("li:first").css("background-color", "#fff").css("border", "1px solid #999").css('cursor', 'default');
                        $("#selectCount").val($("#selectCounts").val());
                       }else{
                    	   $(".pagination").html('');
                       }
                  })
            }
    	})
    }
    /*$("#maxCount").remove();*/
    //进来就执行一次
    dataList(1);
    
    
});