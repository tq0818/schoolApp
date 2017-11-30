
     	$(function(){
     		$selectSubMenu('student_message');
     		$(".btn-view").click(function(){
     			var msgId = $("#msgId").val();
     			var status = $(".btn-view").attr("data-type");
     			selStudent(0,5,status,msgId);
     		});
	 		$(".btn-failure").click(function(){
	 			var msgId = $("#msgId").val();
	 			var status = $(".btn-failure").attr("data-type");
	 			selStudent(0,5,status,msgId);
	 		});
	 		$(".btn-returns").click(function(){
	 			location.href = rootPath + "/student/notice";
	 		});
	 		var messageMethod=$("#messageMethod").val();
	 		if("STUDENT_MESSAGE_DINGYUE"==messageMethod){
	 			selDingyueDetail(1);
	 		}
	 		
	 		 $(".checkOutData").on('click',function () {
                 if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                     $.msg("没有数据可以导出");
                 } else {
                     $("#exportDingyueForm").attr("action",rootPath + "/classModuleLesson/exportDingyueDetailExcle").submit();
                 }

             });
     	});
    	function selStudent(pageNo,pageSize,status,msgId){
    		$.ajax({
    			url:rootPath + "/classModuleLesson/selStudent",
    			type:"post",
    			data:{"page":pageNo,"pageSize":pageSize,"status":status,"msgId":msgId},
    			dataType:"html",
    			success:function(data){
    				$(".place-list").html(data);
    			}
    		});
    	}

    	function selDingyueDetail(pageNo){
       		$.ajax({
       			url : rootPath + "/classModuleLesson/dingyueDetail",
       			type:"post",
       			data:{"stuMsgId":$("#msgId").val(),"page":pageNo,"pageSize":$.trim($("#pageSize").val())},
       			dataType:"html",
       			beforeSend:function(XMLHttpRequest){
       	              $(".loading").show();
       	              $(".loading-bg").show();
       	         },
       			success:function(data){
       				$(".dingyue_detaillist").html(data);
       			},
       			complete:function(XMLHttpRequest,textStatus){
       				$(".loading").hide();
       	            $(".loading-bg").hide();
       	        }
    			});
    	}