<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<style>

.active{color:green;}
</style>
<c:forEach var="an" items="${alist }">
<div class="twoanswer" data-id="${an.id }">
<table class="onean"  cellpadding="0px" cellspacing="0px">
	<col width="100px">
	<col width="100%">
	<tr>
		<td class="title-t" valign="top">
			<div class="title-hip">
				<c:if test="${!empty an.imgurl }">
					<img class="yuanPic" src="http://${imgpath }/${an.imgurl}"/>
				</c:if>
				<c:if test="${empty an.imgurl }">
					<img class="yuanPic" src="../images/user/iconfont-danxiantouxiangline.png"/>
				</c:if>
			</div>
			<div class="title-name">
				${an.name }
			</div>
		</td>
		<td class="content-c" valign="top">
			<div class="c-top">
				<span style="color:#0099ff;">回复 @${an.replyUserName }: </span>${an.answerDesc }
			</div>
			<div class="c-bottom">
				<span>回复时间: ${an.times }</span>
				<span class="oret" style="margin-left:30px;cursor: pointer;"><i class="iconfont">&#xe64f;</i><span class="xzi">回复</span></span>
				<c:if test="${types == 3 }">
					<span class="delt" style="margin-left:10px;cursor: pointer;" data-adotp="${an.isAdopt}" data-id="${an.id }" data-types="2"><i class="iconfont">&#xe626;</i><span class="xzi">删除</span></span>
				</c:if>
				<c:if test="${(an.isAdopt == 0 || null == an.isAdopt)  }">
					<span class="cn" style="margin-left:10px;cursor: pointer;"  data-id="${an.id }" data-ids="${an.questionId }" data-userId="${an.userId}" data-adopcount="${an.adoptFlag}" data-questionscore="${an.questionscore}"><i class="iconfont">&#xe605;</i><span class="xzi">采纳</span></span>
				</c:if>
				<c:if test="${an.isAdopt == 1  }">
					<span class="" style="margin-left:10px;cursor: pointer;"  data-id="${an.id }" data-id="${an.questionId }"><i class="iconfont">&#xe605;</i><span class="xzi">已采纳</span></span>
				</c:if>
				<span class="dz" style="margin-left:10px;cursor: pointer;"  data-id="${an.id }" data-types="${an.isThumbs}"><i class="iconfont <c:if test="${an.isThumbs ==1}"> active </c:if>">&#xe64e;</i><span class="xzi">点赞</span><span>${an.likeanswer}</span></span>
				<c:if test="${(an.isChecke == 0 || null == an.isChecke)}">
					<span class="chec" style="margin-left:10px;cursor: pointer;" data-id="${an.id }" ><i class="iconfont">&#xe621;</i><span class="xzi">审核</span></span>
				</c:if>
			</div>
		</td>
	</tr>
	<tr class="h-text">
		<td></td>
		<td class="t">
			<div class="twotext" style="display: none;">
			<textarea rows="5" style="width: 95%;" placeholder="回复  @${an.name } " data-name="${an.name }" data-types="${an.answerType }"></textarea>
			<p style="float: right;margin-right: 3%;">
				<a class="btn btn-mini btn-primary btn-twore" style="cursor: pointer;" data-id="${an.id }" data-uid="${an.userId }">回复</a>
				<a class="btn btn-mini btn-default btn-twoca" style="cursor: pointer;">取消</a>
			</p>
			</div>
		</td>
	</tr>
</table>
</div>
</c:forEach>