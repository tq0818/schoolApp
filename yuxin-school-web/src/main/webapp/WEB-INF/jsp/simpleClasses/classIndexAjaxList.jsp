<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>

<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

<form method="post" id="myForm">
	<input type="hidden" name="id" id="classTypeId"/>
	<input type="hidden" name="type" value="update"/>
	<input type="hidden" name="typeCode" id="typeCode"/>
	<input type="hidden" name="itemOneId" id="oneId">
	<input type="hidden" name="itemSecondId" id="twoId"/>
	<input type="hidden" name="lable" id="lab"/>
</form>
<form method="post" id="myForm01"></form>
<div class="m-list clear">
	<ul class="clear" id="ulListss">
		<li class='add-class'> <a href='javascript:;' class="checkStudent operate_btn btn-sel-stu"><i class='iconfont icons'>&#xe61c;</i></a></li>
		<c:forEach items="${pageFinder.data }" var="allCommdotity" varStatus="status">
			<li id="commodityLi${allCommdotity.id }" onmouseover="Form.showSave(${allCommdotity.id})" onmouseout="Form.closeSave(${allCommdotity.id})" publishStatus="${allCommdotity.publishStatus}">
				<c:choose>
					<c:when test="${allCommdotity.publishStatus=='CLASS_STOP_SALE' }">
						<i class="tips tips_type" style="background-color: rgba(231,31,26,0.8);color: white;">
							<c:choose>
								<c:when test="${allCommdotity.publishStatus=='CLASS_UNPUBLISHED'}">未发布</c:when>
								<c:when test="${allCommdotity.publishStatus=='CLASS_ON_SALE'}">在售</c:when>
								<c:otherwise>停售</c:otherwise>
							</c:choose>
						</i>
					</c:when>
					<c:otherwise>
						<i class="tips tips_type"><c:choose>
							<c:when test="${allCommdotity.publishStatus=='CLASS_UNPUBLISHED'}">未发布</c:when>
							<c:when test="${allCommdotity.publishStatus=='CLASS_ON_SALE'}">在售</c:when>
							<c:otherwise>停售</c:otherwise>
						</c:choose></i>
					</c:otherwise>
				</c:choose>
				<c:if test="${allCommdotity.iconLable !=null ||allCommdotity.iconLable.length>0}">
					<i class="tips zb_date">
							${allCommdotity.iconLable}
					</i>
				</c:if>
				<c:if test="${allCommdotity.originType eq 1}">
					<i class="add-com" id="com${allCommdotity.id}" marks="${allCommdotity.recommendFlag}" style="display: none;" onclick="Form.collectShop(${allCommdotity.id})">${allCommdotity.recommendFlag==1?'取消推荐':'加入推荐' }</i>
				</c:if>
				<div class="infos-pic">
					<a href="javascript:Form.showClassTypeDetail(${allCommdotity.id },'${allCommdotity.typeCode }');">
						<c:if test="${allCommdotity.cover!=null }">
							<img src="${commodityPicUrl }${allCommdotity.cover}" alt="">
						</c:if>
						<c:if test="${empty allCommdotity.cover}">
							<img alt="" src="<%=rootPath %>/images/overview_demo.jpg">
						</c:if>
					</a>
				</div>

				<div class="infos-title">
					<h2 class="h5">
						<a href="javascript:Form.showClassTypeDetail(${allCommdotity.id }'${allCommdotity.typeCode }');" title="${allCommdotity.name }">
							<c:choose>
								<c:when test="${fn:length(allCommdotity.name)>15}">
									${fn:substring(allCommdotity.name, 0, 15)}...
								</c:when>
								<c:otherwise>
									${allCommdotity.name }
								</c:otherwise>
							</c:choose>
						</a>
					</h2>
					<div class="type" id="lab${allCommdotity.id }">
						<c:if test="${allCommdotity.liveFlag==1 }">
							<a href="javascript:;" mark="live" class="btn btn-mini btn-default">直播</a>
						</c:if>
						<c:if test="${allCommdotity.videoFlag==1 }">
							<a href="javascript:;" mark="video" class="btn btn-mini btn-default">录播</a>
						</c:if>
						<c:if test="${allCommdotity.faceFlag==1 }">
							<a href="javascript:;" mark="face" class="btn btn-mini btn-default">面授</a>
						</c:if>
						<c:if test="${allCommdotity.liveFlag==0&&allCommdotity.videoFlag==0&&allCommdotity.faceFlag==0 }">
							<a href="javascript:;" mark="remote" class="btn btn-mini btn-default">其他</a>
						</c:if>
					</div>
					<p class="descript" title="${allCommdotity.description }">
						<c:if test="${fn:length(allCommdotity.description)>15}">
							${fn:substring(allCommdotity.description, 0, 15)}......
						</c:if>
						<c:if test="${fn:length(allCommdotity.description)<=15}">
							${allCommdotity.description }
						</c:if>
						<c:if test="${empty allCommdotity.description }">
							&nbsp;&nbsp;
						</c:if>
					</p>
					<div class="btns list-btn">
						<c:if test="${allCommdotity.originType eq 1}">
							<a href="javascript:Form.deleteClassType(${allCommdotity.id });" class="btn btn-sm btn-default deleteGoods">删除</a>
						</c:if>
						<c:if test="${allCommdotity.isShelves == 1}">
							<a href="javascript:Form.stopOnsale(${allCommdotity.appId });" class="btn btn-sm btn-default downGoods">下架</a>
							<a href="javascript:Form.editClassType(${allCommdotity.id });" class="btn btn-sm btn-primary">管理</a>
							<a href="<%=rootPath %>/classModuleLesson/classesResource/${allCommdotity.id }/none" target="_blank" class="btn btn-sm btn-primary">资料</a>
						</c:if>
						<c:if test="${allCommdotity.isShelves == '' or allCommdotity.isShelves == 0}">
							<%--<a href="javascript:Form.classTypeOnsale('${allCommdotity.appId}_${allCommdotity.id}');" class="btn btn-sm btn-default upSale">上架</a>--%>
							<a href="##" class="btn btn-sm btn-default upSale eidtShelvesCourses">上架</a>
							<a href="javascript:Form.editClassType(${allCommdotity.id });" class="btn btn-sm btn-primary">管理</a>
						</c:if>
						<c:if test="${allCommdotity.publishStatus=='CLASS_UNPUBLISHED'}">
							<a href="javascript:Form.editClassType(${allCommdotity.id });" class="btn btn-sm btn-primary">管理</a>
						</c:if>
					</div>
				</div>
				<div class="infos-tips clear">
					<p><span class="price">￥ ${allCommdotity.realPrice }</span><del>${allCommdotity.originalPrice }</del><span style="float:right; color: gray;font-size: 10px;">${allCommdotity.actualNum }人学习</span></p>
				</div>
				<div class="course-sort">
					<label for="" class="sort-txt">学科课程排序：</label>
					<input type="text" class="sort-input" maxLength="2" name="sortInput" <c:if test="${not empty allCommdotity.subjectClassOrder}"> value="${allCommdotity.subjectClassOrder }"  isOrder="1" </c:if> <c:if test="${empty allCommdotity.subjectClassOrder}"> placeholder ="未排序"</c:if> onfocus="this.placeholder=''" onblur="this.placeholder='未排序'">
					<!-- <div class="sortbtn"> -->
					<i class='iconfont icons sortbtn sortbtn-gou'>&#xe660;</i>
					<i class='iconfont icons sortbtn sortbtn-cha'>&#xe6bd;</i>
					<!-- </div> -->

				</div>

			</li>
		</c:forEach>
	</ul>
</div>
<div class="pages">
	<ul class="pagination"></ul>
</div>
<input type="hidden" id="itemOneId" name="itemOneId" value="${itemOneId }"/>
<input type="hidden" id="searchName" value="${searchName }"/>
<%--弹出框--%>
<div class="popupContainer">
	<span class="closePopupContainer">x</span>
	
	
	
	<!-- 二级导航 -->
    <div class="u-wrap classes">
        <div class="informationEditHeader">
            <div class="informationEditImg">
                <img src="/images/1.jpg" alt="">
                <div class="informationEditChoose">
                    <a href="##" ><input type="file">选择图片</a>
                </div>
            </div>
            <div class="informationEditDetail">
                <ul>
                    <li>
                        <label>课程名称:</label>
                        <span>
                             <c:choose>
                                 <c:when test="${fn:length(searchAndResult.name)>15}">
                                     ${fn:substring(searchAndResult.name, 0, 15)}...
                                 </c:when>
                                 <c:otherwise>
                                     ${searchAndResult.name }
                                 </c:otherwise>
                             </c:choose>
                        </span>
                    </li>
                    <li>
                        <label>教师:</label>
                        <span>${searchAndResult.teacherName}</span>
                    </li>
                    <li>
                        <label>学校:</label>
                        <span>${searchAndResult.schoolName}</span>
                    </li>
                    <c:if test="${searchAndResult.liveFlag eq 1}">
                        <li>
                            <label>时长:</label>
                            <span>${searchAndResult.lessonDate} ${searchAndResult.lessonTimeStart}~${searchAndResult.lessonDate} ${searchAndResult.lessonTimeEnd}</span>
                        </li>
                    </c:if>
                    <c:if test="${searchAndResult.liveFlag ne 1}">
                        <li>
                            <label>时长:</label>
                            <span>${searchAndResult.lessonDate}</span>
                        </li>
                    </c:if>
                    <li>
                        <label>学习人数:</label>
                        <span>${searchAndResult.actualNum}人学习</span>
                    </li>
                </ul>
                <a href="##" class="courseDetail btn btn-default" onclick="queryClassDetails(${searchAndResult.id});">课程详情</a>
            </div>
        </div>
        <div>
            <ul class="classification">
                <li>
                    <label>课程分类</label>
                    <ul class="courseList">
                        <li>
                           <select id="courseCaId" onchange="chooseSlibMenu($(this));">
                               <c:forEach var="menu" items="${firstMenus}">
                                   <option value="${menu.id}">${menu.name}</option>
                               </c:forEach>
                           </select>
                        </li>
                    </ul>
                </li>
                <li>
                    <label>学段</label>
                    <ul class="courseList">
                        <select id="gradeId" onchange="chooseSlibMenu($(this));">
                            <c:forEach var="menu" items="${secondeMenus}" varStatus="status">
                                <option value="${menu.id}">${menu.name}</option>
                            </c:forEach>
                        </select>
                    </ul>
                </li>
                <li>
                    <label>学科</label>
                    <ul class="courseList">
                        <li>
                            <select id="subjectId" onchange="chooseSlibMenu($(this));">
                                <c:forEach var="menu" items="${thirdMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                </li>
                <li>
                    <label>知识点专题</label>
                    <ul class="courseList">
                        <li>
                            <select id="kwonProId" onchange="chooseSlibMenu($(this));">
                                <c:forEach var="menu" items="${forthMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                </li>
                <li>
                    <label>知识点</label>
                    <ul class="courseList">
                        <li>
                            <select id="knowId" onchange="chooseSlibMenu($(this));">
                                <c:forEach var="menu" items="${fifthMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                </li>
                <li>
                    <label>阶段</label>
                    <ul class="courseList">
                        <li>
                            <select id="stageId">
                                <c:forEach var="menu" items="${jieduanMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                </li>
                <li>
                    <label>类型</label>
                    <ul class="courseList">
                        <li>
                            <select id="typeId">
                                <c:forEach var="menu" items="${leixingMenus}">
                                    <option value="${menu.id}">${menu.name}</option>
                                </c:forEach>
                            </select>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div>
            <ul class="screeningConditions">
                <li>
                    <label>价格：</label><input type="text" value="${searchAndResult.originalPrice}">
                </li>
                <li>
                    <label>实际价格：</label><input type="text" value="${searchAndResult.realPrice}">
                </li>
                <c:if test="${searchAndResult.liveFlag == '1'}">
                    <li>
                        <label>直播开始时间：</label><input type="text" value="${searchAndResult.lessonDate} ${searchAndResult.lessonTimeStart}:00" disabled="disabled">
                    </li>
                </c:if>
                <li>
                    <label>课程标签：</label><input type="text" id="labDesc">
                </li>
            </ul>
        </div>
        <div class="submitCourse">
            <button class="btn btn-success" onclick="toShelves('1');">立即上架</button>
            <button class="btn btn-warning" onclick="toShelves('0');">预约上架</button>
            <input type="text" placeholder="指定上架时间" id="shelvesTime">
        </div>
    </div>
        <c:if test="${searchAndResult.liveFlag==1 }">
            <input id="lab" name="lab" value="live" type="hidden"/>
        </c:if>
        <c:if test="${searchAndResult.videoFlag==1 }">
            <input id="lab" name="lab" value="video" type="hidden"/>
        </c:if>
        <c:if test="${searchAndResult.faceFlag==1 }">
            <input id="lab" name="lab" value="face" type="hidden"/>
        </c:if>
        <c:if test="${searchAndResult.liveFlag==0&&searchAndResult.videoFlag==0&&allCommdotity.faceFlag==0 }">
            <input id="lab" name="lab" value="remote" type="hidden"/>
        </c:if>
        <div id="shelvesInfo">
            <input name="id" type="hidden" id="commodityId" value="${searchAndResult.id}"/>
            <input name="appId" type="hidden" id="appId" value="${searchAndResult.appId}"/>
        </div>
    <form method="post" id="myForm">
	
	
</div>
<div class="popupOpacity"></div>

<script>
    //关闭弹窗
    $('.closePopupContainer').click(function () {
        $('.popupContainer').hide();
        $('.popupOpacity').hide();
    });
    //点击上架打开弹窗
    $('.eidtShelvesCourses').click(function () {
        $('.popupContainer').show();
        $('.popupOpacity').show();
    });
</script>

<script type="text/javascript">
	var orderCount = ${orderCount};
	function resizeLayout(){
		var w=$(".upload-layer").width();
		var h=$(".upload-layer").height();
		var ww=$(window).width();
		var hh=$(window).height();
		var left =(ww-w)/2;
		var top =(hh-h)/2;
		$(".upload-layer").css({"left":left+"px","top":top+"px"});

	}
	$(document).ready(function(){
		$(".pagination").pagination('${pageFinder.rowCount}', {
			next_text : "下一页",
			prev_text : "上一页",
			current_page :'${pageFinder.pageNo-1}',
			link_to : "javascript:void(0)",
			num_display_entries : 8,
			items_per_page : '${pageFinder.pageSize}',
			num_edge_entries : 1,
			callback:function(page,jq){
				var pageNo = page + 1;
				if($("#searchName").val()){
					Form.queryCommodityByName(pageNo);
				}else{
					Form.queryAllCommdityByItemNew(pageNo);
				}
			}
		});
		/*点击已招学员-弹出已招学员列表*/
		$(".checkStudent").click(function(){
			//判断是否是排课老师的权限
			$(".add-layer-bg").fadeIn(200,function(){
				$(".upload-layer").css({
					'width': '860px',
					'height': '400px',
					'left':'55%',
					'top':'60%',
					'background': '#fff',
					'box-shadow': '0 0 6px #999',
					'border': 'none',
					'border-radius': '5px',
					'display': 'none'
				}).fadeIn(200);
				//resizeLayout();
			});
			/* $.ajax({
			 type:'GET',
			 data:{},
			 url:rootPath+"/simpleClasses/checkIsFuShengPaikeOrCommonPaike",
			 dataType : "json",
			 success:function(data){
			 if(data=='yes'){
			 $.msg("当前是排课老师权限，不能添加！");
			 return;
			 }else{
			 $(".add-layer-bg").fadeIn(200,function(){
			 $(".upload-layer").css({
			 'width': '860px',
			 'height': '400px',
			 'left':'55%',
			 'top':'60%',
			 'background': '#fff',
			 'box-shadow': '0 0 6px #999',
			 'border': 'none',
			 'border-radius': '5px',
			 'display': 'none'
			 }).fadeIn(200);
			 //resizeLayout();
			 });
			 }
			 }
			 }) */
		});
		$(document).on('click.close','.upload-layer .close',function(){
			$('.upload-layer').fadeOut(200,function(){
				$(".add-layer-bg").fadeOut(200);
			})
		});
		var oldVal = '';
		$("[name=sortInput]").focus(function(){
			var _this = $(this);
			console.log(_this.val());
			$("#ulListss .sort-input").removeClass("editing");
			_this.addClass("editing");
			_this.siblings('.sortbtn').show();
			_this.val()?oldVal=_this.val():oldVal='';
		});
		$(".course-sort").delegate(".sortbtn-gou","click",function(e){
			var isOrder = $(this).prev().attr("isOrder");
//	  		if(isOrder !=1 && orderCount >= 8 ){
//	  			$.msg("排序总数不能超过8个.");
//	  			return false;
//	  		}
			var publishStatus = $(this).parent().parent("li[id^='commodityLi']").attr("publishStatus");
			if(publishStatus !="CLASS_ON_SALE"){
				$.msg("课程未发布,不能排序.");
				return false;
			}

			var _input = $(this).prev().val();
			var reg =  /(^$)|(^[1-9]\d*$)/;
			if(!$.trim(_input) == '' && !reg.test(_input)) {
				$.msg("请输入正整数,并且序号范围1-99");
				return false;
			}

			$("[name=sortInput]").removeClass("editing");
			$(e.delegateTarget).find('.sortbtn').hide();
			var id;
//	        var itemOneId;
//	        $("#itemOneList").find("a").each(function(i){
//				if($(this).hasClass('btn-success')){
//					var cid=$(this).attr("ids");
//					itemOneId=cid;
//					return false;
//				}
//			});
			id = $(this).parent().parent("li[id^='commodityLi']").attr("id");
			id = id.replace("commodityLi","");
			$.ajax({
				type:'GET',
				data:{'id':id,'order':_input},
				url:"<%=rootPath%>/simpleClasses/updateSubjectClassOrder",
				dataType : "json",
				success:function(data){
					if(data=='success'){
						$.msg("修改排序成功");
						Form.queryAllCommdityByItemNew(1);
					}else{
						$.msg("修改排序失败");
					}
				}
			});

		}).delegate(".sortbtn-cha","click",function(e){
			var sortinput = $(this).closest(".course-sort").find("[name=sortInput]");
			if(oldVal){
				sortinput.val(oldVal);
			}else{
				sortinput.val("");
			}
			sortinput.removeClass("editing");
			$(e.delegateTarget).find('.sortbtn').hide();
		});
		$(document).bind("click",function(e){
			var _this = $(e.target);
			if(_this.hasClass("sort-input")){
				oldVal = _this.val();
			}
			var sortinput = $(".course-sort").find(".editing");
			if(!_this.hasClass('sort-input')&& !_this.hasClass('sortbtn-gou')){
				if(oldVal){
					sortinput.val(oldVal);
				}else{
					sortinput.val("");
				}
				sortinput.removeClass("editing");
				$(e.delegateTarget).find('.sortbtn').hide();
			}
		});
	});
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/class/editClass/validatePrivilige.js"></script>
