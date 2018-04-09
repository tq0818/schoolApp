<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>标签管理</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">标签管理</h2>
            <span class="line"></span>
        </div>
        <div class="labelContent">
            <div>
                <h6>系统标签管理</h6>
                <ul>
                	<c:forEach items="${systemTag }" var="stag" varStatus="status">
                    <li>
                        <button class="btn btn-primary" >${stag.labName }</button>
                        <img src="<%=rootPath %>/images/delete.png" ids="${stag.id }" alt="">
                    </li>
                    </c:forEach>
                    
                    <li class="labelInputContent">
                        <input type="text" class="labelInput" id="systemLab" name="systemLab" maxlength="5">
                        <button class="btn btn-success labelInputContentSave">保存</button>
                        <button class="btn btn-warning labelInputContentCancel">取消</button>
                    </li>
                    <li class="labelAddContent">
                        <button class="btn btn-success labelAdd">+</button>
                    </li>
                </ul>
            </div>
           <%-- <div>
                <h6>用户自定义标签</h6>
                <ul>
                <c:forEach items="${userDefinedTag }" var="udt" varStatus="status">
                    <li>
                        <button  class="btn btn-primary" >${udt.labName }</button>
                        <img src="<%=rootPath %>/images/delete.png" ids="${udt.id }" alt="">
                    </li>
                </c:forEach>    
                </ul>
            </div>--%>
            <div class="labelSaveBtn">
                <a href="<%=rootPath %>/Question/comQuestionIndex" class="btn btn-warning" >返回</a>
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
    	if(null==$('#systemLab').val() || ''==$('#systemLab').val().replace(/(^\s*)|(\s*$)/g, "")){
    		alert("标签不能为空");
    		return;
    	}else{
    		$.ajax({
				url: rootPath + "/Question/checkName",
				type:"post",
				data:{"systemLab":$('#systemLab').val(),"biaoshi":1},
				dataType:"json",
				success:function(data){
					if(data == 'success'){
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
					}else{
						alert("标签名称不能重复");
						 $("#systemLab").val('');
					}
				}
			});
		
    	
//      此处可发起ajax请求
    	}
    });
    $('.labelInputContentCancel').click(function () {
        $('.labelInputContent').css('display','none');
        $('.labelAddContent').show();
    });
//点击删除icon弹窗提醒
    $('.labelContent').find('img').click(function () {
    	var LabId=$(this).attr("ids");
        $.confirm("确认删除此项？",function (s) {
            if(s==true){
           		
              $.ajax({
				url: rootPath + "/Question/addLab",
				type:"post",
				data:{"LabId":LabId,"biaoshi" : 0},
				dataType:"json",
				success:function(data){
				if(data == 'success'){
					location.reload();
					alert("删除成功");
					window.location.href = "labelManagement";
					}
				}
			});
                
            }
        })
    });

	
</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('community_qa');
    });
</script>
</body>
</html>