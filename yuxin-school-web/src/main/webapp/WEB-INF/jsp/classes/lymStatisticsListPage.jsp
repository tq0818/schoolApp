<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>订单</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/admin.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>

	<style type="text/css">
		.head-div {
			position: relative;
			margin-top: 15px;
			padding: 3px 8px;
		}

		.font-size {
			font-size: 14px;
			margin-left: 10px;
			margin-right: 11px;
		}
	</style>

	<%--tob--%>
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/jeUI/jedate.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/tob-new.css" />
</head>

<body>
<form action="<%=rootPath%>/statistics/queryResultExport" method="post" id="export"></form>
<div class="u-wrap admin overflow">
	<div class="right-side">
		<div class="mainbackground nopadding allOrderContent">
			<div class="allOrderHeader">
				<ul>
					<li>
						<textarea rows="10" cols="200" id="eSql"></textarea>
					</li>
					<li>
						<input type="password" value="" id="key"/><span id="load" style="display: none;">数据加载中</span>
					</li>

				</ul>

				<button class="btn btn-primary" onclick="queryResult();">查询</button>
				<button class="btn btn-primary" onclick="exportData();">导出</button>

			</div>
			<div class="user-list allOrderTable" id="queryResult">

			</div>
		</div>

	</div>
</div>
<!-- ajax加载中div开始 -->
<%--<div class="loading lp-units-loading" style="display: none">
	<p>
		<i></i>加载中,请稍后...
	</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>--%>
<!--  ajax加载中div结束 -->




<script type="text/javascript">



	function exportData(){
		if(!$("#key").val()){
			return;
		}
		var excuteSql = $("#eSql").val();
		if(!excuteSql){
			alert("请录入查询语句...");
			return;
		}

		if(excuteSql.indexOf("insert")>0
				|| excuteSql.indexOf("INSERT")>0
				|| excuteSql.indexOf("UPDATE")>0
				|| excuteSql.indexOf("update")>0
				|| excuteSql.indexOf("delete")>0
				|| excuteSql.indexOf("DELETE")>0
		){
			alert("禁止录入更新数据脚本...");
			return;
		}
		var inputs = '<input type="hidden" name="excuteSql" value="'+excuteSql+'"/>' +
				'<input type="hidden" name="key" value="'+$("#key").val()+'"/>';

		$("#export").append(inputs);
		$("#export").submit();

	}

	function queryResult(pageNo){
		if(!$("#key").val()){
			return;
		}
		var excuteSql = $("#eSql").val();
//		console.log(excuteSql+"--"+excuteSql01);
		if(!excuteSql){
			alert("请录入查询语句...");
			return;
		}

		if(excuteSql.indexOf("insert")>0
				|| excuteSql.indexOf("INSERT")>0
				|| excuteSql.indexOf("UPDATE")>0
				|| excuteSql.indexOf("update")>0
				|| excuteSql.indexOf("delete")>0
				|| excuteSql.indexOf("DELETE")>0
		){
			alert("禁止录入更新数据脚本...");
			return;
		}
		$.ajax({
			url : "/statistics/queryResult",
			type:"post",
			data:{"excuteSql":excuteSql,"key":$("#key").val()},
			dataType:"html",
			beforeSend:function(XMLHttpRequest){
				$("#load").show();
			},
			success:function(data){
				$("#queryResult").html("").html(data);
			},
			complete:function(XMLHttpRequest,textStatus){
				$("#load").hide();
			}
		});
	}


</script>
</body>
</html>
