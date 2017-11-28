<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>提问</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <link href="<%=rootPath %>/plugins/editor/froala_editor.min.css" rel="stylesheet" type="text/css">
    <link href="<%=rootPath %>/plugins/editor/froala_page.min.css" rel="stylesheet" type="text/css">
    <script src="<%=rootPath %>/plugins/editor/froala_editor.min.js"></script>


</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding putQuestionLeft">
        <div class="heading">
            <h2 class="h5">发布问题</h2>
            <span class="line"></span>
        </div>
        <form id="addQuestion" action="<%=rootPath%>/Question/addQuestione" method="post" enctype="multipart/form-data">
        <div class="findQuestion">
            <div>
                <span>标题:</span>
                <input type="text" placeholder="请输入问题标题" name="questionTitle" class="inputQuestion">
            </div>
            <div class="checkBoxBtn">
                <span>系统标签:</span>
                <c:forEach items="${systemTag }" var="stag" varStatus="status">
                    <button class="btn btn-default" ids="${stag.id }">${stag.labName }</button>
               	</c:forEach>
            </div>
            <div class="checkBoxBtn">
                <span>自定义标签:</span>
                <c:forEach items="${userDefinedTag }" var="udt" varStatus="status">
                	<button class="btn btn-default" ids="${udt.id }">${udt.labName }</button>
                </c:forEach>
                 <li class="labelInputContent">
                        <input type="text" class="customTag" id="systemLab" name="systemLab">
                        <button class="btn btn-success labelInputContentSave">保存</button>
                      <button class="btn btn-warning labelInputContentCancel">取消</button>
                  </li>
                  <li class="labelAddContent">
                      <button class="btn btn-success labelAdd">+</button>
                  </li>
            </div>
            <div class="contentBox">
                <span>内容:</span>
                <section id="editor" name="questionDesc">
                    <div id='edit' style="margin-top: 30px;">

                    </div>
                </section>
            </div>
            <div>
                <span>积分打赏:</span>
                <input type="text" name="questionScore" class="PointsReward" placeholder="积分打赏必填，不能超过系统预设积分值">
            </div>
            <div class="putQuestion">
                <button class="btn btn-success" onclick="formSubmit()">提问</button>
            </div>
        </div>
    </div>
</div>

<script>
//点击增加增加一个输入框
		$('.labelAdd').click(function () {
		    $('.labelInputContent').css('display','inline-block');
		    $('.labelAddContent').hide();
		});
		//点击确定，点击取消隐藏输入框显示增加按钮
		$('.labelInputContentSave').click(function () {
			$('.labelInputContent').css('display','none');
			$('.labelAddContent').show();
		    $.ajax({
				url: rootPath + "/Question/addLab",
				type:"post",
				data:{"systemLab":$('#systemLab').val(),"biaoshi" : 1},
				dataType:"json",
				success:function(data){
					if(data == 'success'){
						alert("保存成功");
						location.reload();
						
						window.location.href = "labelManagement";
					}
				}
			});
		//  此处可发起ajax请求
		    
		});
		$('.labelInputContentCancel').click(function () {
		    $('.labelInputContent').css('display','none');
		    $('.labelAddContent').show();
		});

//    标签选择
    $('.checkBoxBtn').find('button').click(function () {
        if($(this).hasClass('btn-primary')){
            $(this).addClass('btn-default');
            $(this).removeClass('btn-primary');
        }else{
            $(this).addClass('btn-primary');
            $(this).removeClass('btn-default');
        }
    });

    function formSubmit(){
    	var teacherlis = $(".choose-item").children("li");
    	var commoditylis = $("#courseList").children("li");
    	var teacherIds = "";
    	var commodityIds = "";
  
    
    	commoditylis.each(function(i,v){
    		if($(v).find("input[type='checkbox']:checked").length > 0){
    			var id = $(v).attr("commondityId");
        		commodityIds = commodityIds + id +","
    		}
    	
        });
    	
    	
    	if($('#title').val().length <=0){
    		$.msg("专题标题不能为空");
    		return;
    	}
    	
    	$('#commodityIds').attr("value",commodityIds);
    	$('#teacherIds').attr("value",teacherIds);
        $('#addQuestion').submit();
    }

</script>
<script>
//    编辑器
    $(function(){
        $('#edit').editable({inlineMode: false, alwaysBlank: true})
    });
</script>
</body>
</html>