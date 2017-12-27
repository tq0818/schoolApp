<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>配置积分规则</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/jedate.css">
    <script src="<%=rootPath %>/javascripts/plus/jquery.jedate.min.js"></script>
    <script src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading integralHeading">
            <h2 class="h5 integralH5">学生积分修改</h2>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <table class="table table-center tableRule">
                <tr data-buy="true">
                    <th width="10%" >规则编号</th>
                    <th width="20%" >积分变更名目</th>
                    <th width="10%" >积分值</th>
                    <th width="15%" >生效时间</th>
                    <th width="15%" >失效时间</th>
                    <th width="10%" >操作人</th>
                    <th width="20%" >状态操作</th>
                </tr>
            </table>
        </div>
        <div class="pages ">
            <ul class="pagination">

            </ul>
        </div>
    </div>
</div>
<input type="hidden" id="selectCounts" value="10">
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
//    禁用和启用的切换
    function updateScoreRulsAppStatus(obj){
    	var rulesId=$(obj).attr('name');
    	var data={};
    	data.rulesId=rulesId;
        if($(obj).html()=='禁用'){
            data.status="0";
            $.ajax({
                url: rootPath + "/student/updateScoreRulsAppStatus",
                data: data,
                type: 'post',
                success: function (jsonData) {
                	if(jsonData){
                		$.msg('保存成功');
                		$(obj).html('启用');
                        $(obj).removeClass('btn-primary');
                        $(obj).addClass('btn-default');
                	}else{
                		$.msg('保存失败');
                	}
                }
            });
        }else{
        	data.status="1";
            $.ajax({
                url: rootPath + "/student/updateScoreRulsAppStatus",
                data: data,
                type: 'post',
                success: function (jsonData) {
                	if(jsonData){
                		$.msg('保存成功');
                		$(obj).html('禁用');
                        $(obj).addClass('btn-primary');
                        $(obj).removeClass('btn-default');
                	}else{
                		$.msg('保存失败');
                	}
                }
            });
        }
}
function giveupRule(objt){
	$(objt).attr('style','display:none');
	$(objt).parent().find('.btn-success').attr('style','display:none');
	$(objt).parent().find('.btn-default').attr('style','display:inline-block');
	var rowInput = $(objt).parent().siblings('td').find('input');
    rowInput.removeClass('active');
    //rowInput.attr('disabled', true);
}
function saveRule(objt){
	var rulesId=$(objt).attr('name');
	var data={};
	data.id=rulesId;
	var score=$(objt).parent().siblings('td').find('input').eq(0).val();
	var validTime=$(objt).parent().siblings('td').find('input').eq(1).val();
	var invalidTime=$(objt).parent().siblings('td').find('input').eq(2).val();
	data.score=score;
	data.validTime=validTime;
	data.invalidTime=invalidTime;
	var username='${sessionScope.loginUser.username}';
	$(objt).parent().siblings('td').eq(5).text(username);
	 $.ajax({
         url: rootPath + "/student/updateScoreRuleById",
         data: data,
         type: 'post',
         success: function (jsonData) {
         	if(jsonData){
         		$.msg('保存成功');
                giveupRule(objt);
                $(objt).parent().find('.btn-warning').attr('style','display:none');
         	}else{
         		$.msg('保存失败');
         	}
         }
     });
}
//点击编辑，该行变为可编辑状态
function editRule(objt){
        var rowInput = $(objt).parent().siblings('td').find('input');
        rowInput.addClass('active');
        rowInput.eq(0).attr('disabled', false);

        //调用日历插件
        var start = {
            format:'YYYY-MM-DD hh:mm',
            minDate: $.nowDate({DD: 0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            okfun: function (obj) {
                end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
            }
        };
        var end = {
            format:'YYYY-MM-DD hh:mm',
            minDate: $.nowDate({DD: 0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            okfun: function (obj) {
                start.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
            }
        };
        var editIndex = $(objt).parent().parent().index();

        $.jeDate('.dateRuleStart'+editIndex, start);
        $.jeDate('.dateRuleEnd'+editIndex, end);
		$(objt).attr('style','display:none');
		$(objt).parent().find('.btn-success').css('display','inline-block');
		$(objt).parent().find('.btn-warning').css('display','inline-block');
    };
    (function ($) {
        var ScoreRuls = {
            init: function () {
                var $this = this;
                // 初始化数据
                $this.search(1);
            },
            searchCount: function(){
            	$("#selectCounts").val($("#selectCount").val());
            	$this.search(1);
            },
            search: function (page) {
                var $this = this;
                var data = {};
                data.page = page ? page : 1;
                data.pageSize=$("#selectCounts").val() || 10;
                $(".user-list").find("table").find("tr:gt(0)").remove();
                //查询积分规则
                $.ajax({
                        url: rootPath + "/student/integralRuleAjax",
                        data: data,
                        type: 'post',
                        beforeSend: function (XMLHttpRequest) {
                            $(".loading").show();
                            $(".loading-bg").show();
                        },
                        success: function (jsonData) {

//                            console.log(jsonData.data.length);

                        	if (jsonData.data.length == 0) {

                            	/*if(userorg_roleopenflag == 1 && proxyOrgRole == 1){
    	                            $(".user-list")
    	                                .find("table")
    	                                .append(
    	                                '<tr><td colspan="7">没有查找到数据</td></tr>');
                            	}else{*/
                            		  $(".user-list")
    	                                .find("table")
    	                                .append(
    	                                '<tr><td colspan="7">没有查找到数据</td></tr>');
//                            	}
                            }
                             $.each(jsonData.data,function (i, stu) {
                            	 	var oprator="";
                            	 	if(stu.oprator!=null)
                            	 		oprator=stu.oprator;
                                    var validTime = stu.validTime;
                                    var invalidTime = stu.invalidTime;
                                    if(validTime==null){
                                        validTime = "";
                                    }
                                     if(invalidTime==null){
                                         invalidTime = "";
                                     }

                                    var bl=(page-1)*10;

                                    var htmlStr='<tr>'+
			            	                    '<td>'+(i+1+bl)+'</td>'+
			            	                    '<td>'+stu.scoreTopic+'</td>'+
			            	                    '<td><input type="text" value="'+stu.score+'" disabled="disabled"></td>'+
			            	                    '<td><input type="text" value="'+validTime+'" readonly class="dateRuleStart'+(i+1)+'"></td>'+
			            	                    '<td><input type="text" value="'+invalidTime+'" readonly class="dateRuleEnd'+(i+1)+'"></td>'+
			            	                    '<td>'+oprator+'</td>'+
			            	                    '<td>';
			            	                    
			            	        var validStr="";
			            	        if(stu.ststus=="1"){
			            	        	validStr='<button class="btn btn-primary forbidBtn" name="'+stu.id+'" onclick="updateScoreRulsAppStatus(this)">禁用</button>';
			            	        }else{
			            	        	validStr='<button class="btn btn-default forbidBtn" name="'+stu.id+'" onclick="updateScoreRulsAppStatus(this)">启用</button>';
			            	        }
			            	        var lastStr='<button class="btn btn-default editRuleBtn" name="'+stu.id+'" onclick="editRule(this)">编辑</button>'+
			            	        			'<button class="btn btn-success" style="display:none" name="'+stu.id+'" onclick="saveRule(this)">保存</button>'+
			               		 				'<button class="btn btn-warning" style="display:none" name="'+stu.id+'" onclick="giveupRule(this)">取消</button>'+
			            	                    '</td>'+
			            	                '</tr>';
                                	$(".user-list").find('table').append(htmlStr+validStr+lastStr);
                                });
                            $("#pageNo").remove();
                            $(".user-list").after('<input type="hidden" id="pageNo" value="'+jsonData.pageNo+'"/>');
                            if (jsonData.rowCount >$("#selectCounts").val()) {
                                $(".pagination").pagination(jsonData.rowCount,
                                    {
                                        next_text: "下一页",
                                        prev_text: "上一页",
                                        current_page: jsonData.pageNo - 1,
                                        link_to: "javascript:void(0)",
                                        num_display_entries: 8,
                                        items_per_page: jsonData.pageSize,
                                        num_edge_entries: 1,
                                        callback: function (page, jq) {
                                            var pageNo = page + 1;
                                            $this.search(pageNo);
                                        }
                                    });
                            } else {
                                $(".pagination").html('');
                            }
                        },
                        complete:function (XMLHttpRequest, textStatus) {
                            $(".loading").hide();
                            $(".loading-bg").hide();
                        }
                    });
                $("#maxCount").remove();
            }
        }
        $(document).ready(function () {
        	ScoreRuls.init();
        })
        window.ScoreRuls =ScoreRuls;
    })(jQuery)
</script>

<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('integralRule');
    });
</script>
</body>
</html>
