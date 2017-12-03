<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>积分修改</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
<input type="hidden" id="userid" name="userid" value="${user.id }"></input>
<input type="hidden" id="username" name="username" value="${user.username}"></input>
<input type="hidden" name="total_score_id" id="total_score_id" value="${totalScoreVo.totalScoreId}"></input>
    <div class="mainbackground nopadding">
        <div class="heading integralHeading">
            <h2 class="h5 integralH5">学生积分修改</h2>
            <span class="">
                <a href="##">姓名:</a>
                <span>${totalScoreVo.stuName}</span>
            </span>
            <span>
                <a href="##">现有积分:</a>
                <span id="integral">${totalScoreVo.totalScore}</span>
            </span>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <table class="table table-center">
                <tr data-buy="true">
                    <th width="20%" >顺序</th>
                    <th width="20%" >积分变更名目</th>
                    <th width="20%" >积分流水</th>
                    <th width="20%" >积分变更时间</th>
                    <th width="20%" >操作人</th>
                </tr>
                <c:forEach items="${totalScoreVo.scoreDetailsAppVos}" var="scoreDetailsAppVo" varStatus="status">
                <tr>
	                    <td>${status.count+1}</td>
	                    <td>${scoreDetailsAppVo.origin}</td>
	                    <td>${scoreDetailsAppVo.itemScore}</td>
	                    <td>${scoreDetailsAppVo.createTime}</td>
	                    <td>${scoreDetailsAppVo.fixedPerson}</td>
                </tr>
                </c:forEach>
            </table>
            <div class="changeIntegral">
                <input type="text" class="integralInput">
                <select class="integralselect" id="integral" name="integral">
                    <c:forEach items="${scoreRulsAppVos}" var="scoreRulsAppVo">
						<option value="${scoreRulsAppVo.id}">${scoreRulsAppVo.scoreTopic}</option>
					</c:forEach>
                </select>
                <button class="btn btn-primary adjusting">调整积分</button>
            </div>
            <div class="integraBtn">
                <button class="btn btn-success">保存</button>
                <a href="javascript:void(0);" onclick="history.go(-1)" class="btn btn-default">取消</a>
            </div>
        </div>
    </div>
</div>
<div>
<form id="saveData">
<input type="hidden" id="userFrontId" name="userFrontId" value="${totalScoreVo.userId}"></input>
</form>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
    //    分页
//    $(".pagination").pagination($("#rowCount").val(), {
//        next_text : "下一页",
//        prev_text : "上一页",
//        current_page : ($("#pageNo").val() - 1),
//        link_to : "javascript:;",
//        num_display_entries : 5,
//        items_per_page : $("#pageSize").val(),
//        num_edge_entries : 1,
//        callback : function(page, jq) {
//            var pageNo = page + 1;
//        }
//    });
$('.btn-success').click(function(){
	
	$.ajax({
    	url: rootPath+"/student/saveOrUpdateTotalScore",
    	dataType: "json",
    	type: "post",
    	data:$('#saveData').serialize(),
    	success: function(jsonData){
    		if(jsonData)
    			$.msg('保存成功');
    		else 
    			$.msg('保存失败');
    	}
    })
});

//点击积分调整，增加一条
$('.adjusting').click(function () {
    var integralInput = $('.integralInput').val();
    if(integralInput==""||integralInput==undefined){
    	$.msg("积分值不能为空！");
    	return;
    }
    var integralTotalScore=$.trim($('#integral').text());
    if(integralInput.indexOf('-')>-1){
    	integralTotalScore=parseInt(integralTotalScore)-parseInt(integralInput.substring(1));
    }else if(integralInput.indexOf('+')>-1){
    	integralTotalScore=parseInt(integralTotalScore)+parseInt(integralInput.substring(1));
    }else{
    	integralTotalScore=parseInt(integralTotalScore)+parseInt(integralInput);
    }
    $('#integral').text(integralTotalScore);
    var userid=$('#userid').val();
    var username=$('#username').val();
    var integralselect = $('.integralselect').children('option:selected ').html();
    var index = $('.table-center').find('tr').length-1;
    $.ajax({
    	url: rootPath+"/student/getTime",
    	dataType: "json",
    	success: function(time){
    		var _html ="<tr><td>"+index+"</td>"+
            "<td>"+integralselect+"</td>"+
            "<td>"+integralInput+"</td>"+
            "<td>"+time+"</td>"+
            "<td>"+username+"</td>"+
            "</tr>";
			$('table').append(_html);
			var value=integralselect+"_"+integralInput+"_"+time+"_"+userid;
			var html='<input type="hidden" name="strValue" value="'+value+'"/>';
			$('#saveData').append(html);
    	}
    })
});
</script>
</body>
</html>