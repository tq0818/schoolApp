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
    <div class="mainbackground nopadding ">
        <div class="heading">
            <h2 class="h5">发布问题</h2>
            <span class="line"></span>
        </div>
        <form id="addQuestion" action="<%=rootPath%>/Question/addQuestione" method="post" enctype="multipart/form-data">
        <div class="findQuestion">
            <div>
                <span>标题:</span>
                <input type="text" placeholder="请输入问题标题" id="questionTitle" name="questionTitle" class="inputQuestion">
            </div>
            <div class="checkBoxBtn">
                <span>系统标签:</span>
                <c:forEach items="${systemTag }" var="stag" varStatus="status">
                    <button type="button" class="btn btn-default" name="panduan" labtype="0" ids="${stag.id }">${stag.labName }</button>
               	</c:forEach>
            </div>
            <div class="checkBoxBtn">
                <span>自定义标签:</span>
                <div id="zdyBtn">
	                <c:forEach items="${userDefinedTag }" var="udt" varStatus="status">
	                	<button type="button" class="btn btn-default" name="panduan" labtype="0" ids="${udt.id }">${udt.labName }</button>
	                </c:forEach>
               </div>
                <ul class="putQuestionContent">
                    <li class="labelInputContent">
                        <input type="text" class="customTag" id="systemLab" name="systemLab">
                        <button type="button" class="btn btn-success labelInputContentSave">确定</button>
                        <a href="javascript:void(0);" class="btn btn-warning labelInputContentCancel">取消</a>
                    </li>
                    <li class="labelAddContent">
                        <button type="button" class="btn btn-success labelAdd">+</button>
                    </li>
                </ul>
            </div>
            <div class="contentBox">
                <span>内容:</span>
                <div id="ckecktor"> 
					<textarea id="newsContents" class="msg-content"></textarea>
				</div> 
            </div>
            <div>
                <span>积分打赏:</span>
                <input type="text" name="questionScore" id="questionScore" class="PointsReward" placeholder="积分打赏必填，不能超过系统预设积分值" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " onafterpaste="this.value=this.value.replace(/[^\d]/g,'') ">
            </div>
            <div class="putQuestion">
                <button  type="button" class="btn btn-success" onclick="addQuestion()" >提问</button>
            </div>
        </div>
        </form>
    </div>
</div>

<script>
//点击增加增加一个输入框
		$('.labelAdd').click(function () {
			var btns = $("button[name='panduan']");  
			var checked_counts = 0; 
		    for(var i=0;i<btns.length;i++){  
		        if(btns.eq(i).hasClass("btn-primary")){
		        	checked_counts++;  
		        }  
		    }  
			
			if(checked_counts>=5){
				alert("已选择5个标签不能在添加");
				return
			}else{
			    $('.labelInputContent').css('display','inline-block');
			    $('.labelAddContent').hide();
			}
		});
		//点击确定，点击取消隐藏输入框显示增加按钮
		$('.labelInputContentSave').click(function () {
			var btns = $("button[name='panduan']");  
			var checked_counts = 0; 
		    for(var i=0;i<btns.length;i++){  
		        if(btns.eq(i).hasClass("btn-primary")){
		        	checked_counts++;  
		        }  
		    }  
			
			if(checked_counts>=5){
				alert("已选择5个标签不能在添加");
				return
			}else{
		    var a=$('#systemLab').val();
		    if(null==$('#systemLab').val() || ''==$('#systemLab').val().replace(/(^\s*)|(\s*$)/g, "")){
	    		alert("标签不能为空");
	    		return;
	    	}else{
	    		$.ajax({
					url: rootPath + "/Question/checkName",
					type:"post",
					data:{"systemLab":$('#systemLab').val(),"biaoshi":2},
					dataType:"json",
					success:function(data){
						if(data == 'success'){
							$('.labelInputContent').css('display','none');
							$('.labelAddContent').show();
							var html="<button type='button' class='btn btn-default' name='panduan' labtype='2' ids="+a+" >"+a+"</button>";
						//  此处可发起ajax请求
							$("#zdyBtn").append(html);
							$("#systemLab").val('');
					    	
						}else{
							alert("标签名称不能重复");
							 $("#systemLab").val('');
							}
						}
					});
	    		}
			}
		});
		$('.labelInputContentCancel').click(function () {
		    $('.labelInputContent').css('display','none');
		    $('.labelAddContent').show();
		    $("#systemLab").val('');
		});

//    标签选择
    $('.checkBoxBtn ').delegate('button','click',function () {
    	
        if($(this).hasClass('btn-primary')){
            $(this).addClass('btn-default');
            $(this).removeClass('btn-primary');
        }else{
        	var btns = $("button[name='panduan']");  
    		var checked_counts = 0; 
    	    for(var i=0;i<btns.length;i++){  
    	        if(btns.eq(i).hasClass("btn-primary")){
    	        	checked_counts++;  
    	        }  
    	    }  
    		if(checked_counts>=5){
    			alert("已选择5个标签不能在添加");
    			return
    		}else{
            $(this).addClass('btn-primary');
            $(this).removeClass('btn-default');
        }
		}
    });

     function addQuestion(){
    	 var questionTitle=$("#questionTitle").val();
    	 if(null==questionTitle  || ''==questionTitle.replace(/(^\s*)|(\s*$)/g, "")){
    		 alert("标题不能为空");
    		 return;
    	 }
    	 var questionScore=$("#questionScore").val();
    	 if(null==questionScore || ''==questionScore){
    		 alert("积分打赏不能为空");
    		 return;
    	 }
    	 CKupdate();
    	 
    	 var questionDescTP=editor.document.getBody().getHtml(); //取得纯文本
    	 var questionDesc=editor.document.getBody().getText(); //取得纯文本
    	 var systemId=new Array();
    	 var userDefued=new Array();
    	 var count=0;
    	 var count1=0;
    	 var count2=0;
    	 var re = /^[0-9]+.?[0-9]*$/; 
    	 var btns = $("button[name='panduan']");  
		    for(var i=0;i<btns.length;i++){  
		        if(btns.eq(i).hasClass("btn-primary")){
		        	if(re.test(btns.eq(i).attr("ids"))){
		        		if(btns.eq(i).attr("labtype")==0){
			        		systemId[count] = btns.eq(i).attr("ids");
				        	count++;
		        		}else{
		        			userDefued[count1] = btns.eq(i).attr("ids");
				        	count1++;
		        		}
		        	}else{
		        		userDefued[count1] = btns.eq(i).attr("ids");
			        	count1++;
		        	}
		        	count2++;
		        }  
		    } 
    	  if(count2==0){
    		  alert("必须选择一个标签");
    		  return;
    	  }
    	  $.ajax({
 			url: rootPath + "/Question/addQuestione",
 			type:"post",
 			data:{"questionTitle":questionTitle,"questionDesc" : questionDesc,"questionscore":questionScore,"systemTagIds":systemId,"userDefuledNames":userDefued,"questionDescTP":questionDescTP},
 			dataType:"json",
 			success:function(data){
 				if(data == 'success'){
 					alert("保存成功");
 					window.location.href = "comQuestionIndex";
 				}
 			}
 		});
    	 
       
    } 

</script>
<script type="text/javascript" src="<%=rootPath%>/plugins/ckeditor/ckeditor.js"></script>
<script>
//    编辑器
    $(function(){
        $('#edit').editable({inlineMode: false, alwaysBlank: true})
    });
    
    //处理CKEDITOR的值
	function CKupdate() {
		for (instance in CKEDITOR.instances) {
			CKEDITOR.instances[instance].updateElement();
		}
	}
    
   
    var editor = CKEDITOR.replace('newsContents');
	/* editor.config.width="atuo"; */
	editor.config.toolbar = [
			[ 'mode', 'document', 'doctools' ],
			[ 'Source', '-', 'NewPage' ],
			[ 'basicstyles', 'cleanup' ],
			[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript',
					'Superscript' ],
			[ 'list', 'indent', 'blocks', 'align', 'bidi' ],
			[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
					'JustifyLeft', 'JustifyCenter', 'JustifyRight',
					'JustifyBlock' ], [ 'Link', 'Unlink' ],
			[ 'Image', 'Table' ],
			[ 'Styles', 'Format', 'Font', 'FontSize' ],
			[ 'TextColor', 'BGColor' ], [ 'Maximize' ], [ '-' ]];
	editor.config.baseFloatZIndex = 10100;
	editor.config.customConfig = 'config.js';
</script>
<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('community_qa');
    });
</script>
</body>
</html>