
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>已上架课程</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/classes.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css" />
    
    <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
</head>
<body style="position:relative;">
    <!-- 二级导航 -->
    <jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
    <%--已上架课程分类筛选--%>
    <div class="u-wrap classes">
        <div class="mainbackground nopadding">
        <div class="classes-type">
            <p class="c">
                <span class="t-title">课程分类</span>
                <span class="t-content" id="categoryNameList">
                    <a href="javascript:Form.queryAllCommdityByItemNew(1,'all');" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1,'TYPE_LOW');" data-code="TYPE_LOW" class="btn btn-mini btn-default">小低</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1,'TYPE_HIGH');" data-code="TYPE_HIGH" class="btn btn-mini btn-default">小高</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1,'TYPE_MID');" data-code="TYPE_MID" class="btn btn-mini btn-default">初中</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1,'TYPE_HIHER');" data-code="TYPE_HIHER" class="btn btn-mini btn-default">高中</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1,'TYPE_AS');" data-code="TYPE_AS" class="btn btn-mini btn-default">数学思维拓展</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1,'TYPE_YD');" data-code="TYPE_YD" class="btn btn-mini btn-default">绘本阅读</a>

                </span>
            </p>
            <p class="c">
                <span class="t-title">学段</span>
                <span class="t-content" id="gradeNameList">
                     <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="MIN_UP" class="btn btn-mini btn-default">幼升小</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_FOUR" class="btn btn-mini btn-default">四年级</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_FIVE" class="btn btn-mini btn-default">五年级</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_SIX" class="btn btn-mini btn-default">六年级</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_UP" class="btn btn-mini btn-default">小升初</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="MID_ONE" class="btn btn-mini btn-default">初一</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="MID_EXAM" class="btn btn-mini btn-default">中考</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="MID_UP" class="btn btn-mini btn-default">初升高</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="HIHER_ONE" class="btn btn-mini btn-default">高一</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="HIHER_EXAM" class="btn btn-mini btn-default">高考</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="HIHER_TWO" class="btn btn-mini btn-default">高二</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="HIHER_THREE" class="btn btn-mini btn-default">高三</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_QT" class="btn btn-mini btn-default">其他</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_SED" class="btn btn-mini btn-default">二年级</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_THREE" class="btn btn-mini btn-default">三年级</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="MID_SED" class="btn btn-mini btn-default">初二</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="GRADE_ONE" class="btn btn-mini btn-default">一年级</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="MID_THREE" class="btn btn-mini btn-default">初三</a>

                </span>
            </p>
            <p class="c">
                <span class="t-title">学科</span>
                <span class="t-content" id="subjectNameList">
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="all" class="btn btn-mini btn-default btn-success">全部</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_YJ" class="btn btn-mini btn-default">幼教</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_YW" class="btn btn-mini btn-default">语文</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_SX" class="btn btn-mini btn-default">数学</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_YY" class="btn btn-mini btn-default">英语</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_WL" class="btn btn-mini btn-default">物理</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_HX" class="btn btn-mini btn-default">化学</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_QT" class="btn btn-mini btn-default">其他</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="SUBJECT_XL" class="btn btn-mini btn-default">心理健康</a>

                </span>
            </p>
            <p class="c">
                <span class="t-title">知识点专题</span>
                <span class="t-content" id="knowledgeNameList">

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="小高-四年级-语文-知识点" class="btn btn-mini btn-default">小高-四年级-语文-知识点</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="初中-初一-语文-知识点" class="btn btn-mini btn-default">初中-初一-语文-知识点</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="高中-高一-语文-知识点" class="btn btn-mini btn-default">高中-高一-语文-知识点</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="儿童微影评：心语解码" class="btn btn-mini btn-default">儿童微影评：心语解码</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="经典阅读：大话西游" class="btn btn-mini btn-default">经典阅读：大话西游</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="古诗品析：临渊明道" class="btn btn-mini btn-default">古诗品析：临渊明道</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="思维写作：写作育心" class="btn btn-mini btn-default">思维写作：写作育心</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="口语交际" class="btn btn-mini btn-default">口语交际</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="走进古代诗歌" class="btn btn-mini btn-default">走进古代诗歌</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="作文体验" class="btn btn-mini btn-default">作文体验</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="《论语》中的智慧" class="btn btn-mini btn-default">《论语》中的智慧</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="学法指导" class="btn btn-mini btn-default">学法指导</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="核心数学思想" class="btn btn-mini btn-default">核心数学思想</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="情意体验" class="btn btn-mini btn-default">情意体验</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="通识高中数学" class="btn btn-mini btn-default">通识高中数学</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="知识拓展" class="btn btn-mini btn-default">知识拓展</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="思想方法" class="btn btn-mini btn-default">思想方法</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="绘本阅读" class="btn btn-mini btn-default">绘本阅读</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="双语科学" class="btn btn-mini btn-default">双语科学</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="小学听说课" class="btn btn-mini btn-default">小学听说课</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="进阶绘本阅读" class="btn btn-mini btn-default">进阶绘本阅读</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="看电影学英语" class="btn btn-mini btn-default">看电影学英语</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="小说欣赏入门" class="btn btn-mini btn-default">小说欣赏入门</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="Steam课程" class="btn btn-mini btn-default">Steam课程</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="英语电影欣赏" class="btn btn-mini btn-default">英语电影欣赏</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="理解幼小衔接" class="btn btn-mini btn-default">理解幼小衔接</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="幼小衔接家庭教育" class="btn btn-mini btn-default">幼小衔接家庭教育</a>

                           <a href="javascript:Form.queryAllCommdityByItemNew(1);" data-code="绘本知识点" class="btn btn-mini btn-default">绘本知识点</a>

                </span>
            </p>
            <p class="c">
                <span class="t-title">知识点</span>
                <span class="t-content" id="knowledgeProNameList">
                	<a href="javascript:Form.queryAllCommdityByItemNew(1)" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="CLASS_UNPUBLISHED" class="btn btn-mini btn-default">未上架</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="CLASS_ON_SALE" class="btn btn-mini btn-default">招生中</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="CLASS_STOP_SALE" class="btn btn-mini btn-default">已下架</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">阶段</span>
                <span class="t-content" id="stageNameList">
                	<a href="javascript:Form.queryAllCommdityByItemNew(1)" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_LIVE" class="btn btn-mini btn-default">直播</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_VIDEO" class="btn btn-mini btn-default">录播</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_FACE" class="btn btn-mini btn-default">面授</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_REMOTE" class="btn btn-mini btn-default">其他</a>
                </span>
            </p>
            <p class="c">
                <span class="t-title">类型</span>
                <span class="t-content" id="typeCodeList">
                	<a href="javascript:Form.queryAllCommdityByItemNew(1)" ids="all" class="btn btn-mini btn-default btn-success">全部</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_LIVE" class="btn btn-mini btn-default">直播</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_VIDEO" class="btn btn-mini btn-default">录播</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_FACE" class="btn btn-mini btn-default">面授</a>
                    <a href="javascript:Form.queryAllCommdityByItemNew(1);" ids="IS_REMOTE" class="btn btn-mini btn-default">其他</a>
                </span>
            </p>
        </div>
    </div>
    </div>
    <div class="u-wrap classes">
        <div class="mainbackground nopadding">
			<div id="ShelvesCourseDetailList">
			
			</div>
        </div>
<<<<<<< HEAD
=======
        <div class="user-list">
            <table class="table table-center" id="tableList">
                <tbody>
                    <tr data-buy="true">
                        <th width="1%"><input type="checkbox" class="checkboxAll"></th>
                        <th width="5%">课程图片</th>
                        <th width="5%">课程名称</th>
                        <th width="10%">学段</th>
                        <th width="5%">学科</th>
                        <th width="10%">知识点专题</th>
                        <th width="10%">知识点</th>
                        <th width="5%">阶段</th>
                        <th width="8%">类型</th>
                        <th width="6%">上架时间</th>
                        <th width="6%">直播时间</th>
                        <th width="3%">学习人数</th>
                        <th width="3%">价格</th>
                        <th width="3%">实际价格</th>
                        <th width="20%">操作</th>
                    </tr>
                    <tr data-buy="false">
                        <td><input type="checkbox" class="signUpMany" uname="sdsdsd" value=""></td>
                        <td><img src="/images/1.jpg" alt="" class="shelvesIcon"></td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>2017-11-14</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>
                            <span><a href="javascript:;" class="btn btn-primary btn-sm">下架</a></span>
                            <span><a href="/appNewClasses/homeRecommendation" class="btn btn-primary btn-sm">推荐</a></span>
                            <span><a href="##" class="btn btn-primary btn-sm eidtShelvesCourses">编辑</a></span>
                        </td>
                    </tr>
                </tbody>
            </table>

            <div class="pages pagination"></div>
        </div><input type="hidden" id="rowCount" value="68266"><input type="hidden" id="pageNo" value="1"><input type="hidden" id="maxCount" value="999999999">
>>>>>>> branch 'feature_app' of http://git.winshare-edu.com:80/winshare/winshare-yuxin-school.git
    </div>

    <%--弹出框--%>
    <div class="popupContainer">
        <span class="closePopupContainer">x</span>
    </div>
    <div class="popupOpacity"></div>

    <script type="text/javascript">
        $(document).ready(function(){
            $.ajax({
                url : rootPath + "/companyServiceStatic/queryCompanyNoServices",
                type : "post",
                dataType : 'json',
                success : function(jsonData) {
                    var count=jsonData.length;
                    var html="";
                    if(count==1){
                        $.each(jsonData,function(i,item){
                            if(item.groupCode=='SERVICE_LIVE'){
                                html+='<ul class="tabsn c4"><li class="b2">录播</li>'+
                                    '<li class="b3">面授</li>'+
                                    '<li class="b4">混合</li>'+
                                    '<li class="b5">其他</li></ul>';
                            }else if(item.groupCode=='SERVICE_VIDEO'){
                                html+='<ul class="tabsn c4"><li class="b1">直播</li>'+
                                    '<li class="b3">面授</li>'+
                                    '<li class="b4">混合</li>'+
                                    '<li class="b5">其他</li></ul>';
                            }else if(item.groupCode=='SERVICE_FACE'){
                                html+='<ul class="tabsn c4"><li class="b1">直播</li>'+
                                    '<li class="b2">录播</li>'+
                                    '<li class="b4">混合</li>'+
                                    '<li class="b5">其他</li></ul>';
                            }
                        });
                    }else if(count==2){
                        var num1,num2;
                        $.each(jsonData,function(i,item){
                            if(i==0){
                                num1=item.groupCode;
                            }else{
                                num2=item.groupCode;
                            }
                        })
                        if((num1=="SERVICE_LIVE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_LIVE")){
                            html+='<ul class="tabsn c2"><li class="b3">面授</li>'+
                                '<li class="b5">其他</li></ul>';
                        }
                        if((num1=="SERVICE_LIVE"&&num2=="SERVICE_FACE")||(num1=="SERVICE_FACE"&&num2=="SERVICE_LIVE")){
                            html+='<ul class="tabsn c2"><li class="b2">录播</li>'+
                                '<li class="b5">其他</li></ul>';
                        }
                        if((num1=="SERVICE_FACE"&&num2=="SERVICE_VIDEO")||(num1=="SERVICE_VIDEO"&&num2=="SERVICE_FACE")){
                            html+='<ul class="tabsn c2"><li class="b1">直播</li>'+
                                '<li class="b5">其他</li></ul>';
                        }
                    }else if(count==3){
                        html+='<ul class="tabsn c8">'+
                            '<li class="b5">其他</li></ul>';
                    }else{
                        html+='<ul class="tabsn"><li class="b1">直播</li>'+
                            '<li class="b2">录播</li>'+
                            '<li class="b3">面授</li>'+
                            '<li class="b4">混合</li>'+
                            '<li class="b5">其他</li></ul>';
                    }
                    $("#lsOne").append(html);
                }
            });
            $("body").css("position","relative")
        });
    </script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/app/shelvesCourses.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/classes.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>
