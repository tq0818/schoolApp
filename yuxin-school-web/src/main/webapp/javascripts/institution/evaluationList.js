var reviewStatus = "";
var insCommntId = "";
$(function () {
    //    左侧active切换
    $selectSubMenu('review');
    //tab切换
    $('.evaTitle').click(function () {
        $(this).addClass('active');
        $(this).siblings('span').removeClass('active');
        sessionStorage.setItem('courseBtn',$(this).html());
    });


    var table = $('.evaScreen').children('div').children('a').html();
    if (table == '全部') {
        $("#evaluation").hide();
    }
    //筛选样式切换


    $('.evaScreen').children('div').eq(0).children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');

    });

    $('.evaScreen').children('div').eq(1).children('a').click(function () {


        var table = $(this).html();
        if (table == '全部') {
            reviewStatus = '';
        } else if (table == '待审核') {
            reviewStatus = 0;
        } else {
            reviewStatus = 1;
        }

        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');

        initInsComment(1,insCommntId, reviewStatus);

    });

    $("#ins").children().click(function () {
        insCommntId = $(this).attr('data-insId');
        initInsComment(1,insCommntId, reviewStatus);
    });



    //课程评价筛选
    $('.curriculum').children('div').children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
    });


    //课程筛选
    let curriculumClass = '';
    $('body').on('click','.curriculumClass',function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
        curriculumClass = $(this).attr('data-insClassId');
        initInsClassComment(1,insClassId,curriculumState,curriculumClass);
    });
    //状态筛选
    let curriculumState = '';
    $('#curriculumState').children('a').click(function () {
        curriculumState = $(this).attr('data-review');
        initInsClassComment(1,insClassId,curriculumState,curriculumClass);
    });

    //点击机构评论
    $('body').on('click','.insComment',function () {

        initInsComment(1,insCommntId, reviewStatus);
        $('.evaScreen').show();
        $('.curriculum').hide();
    })

    //点击课程评论
    $('body').on('click','.insClassComment',function () {
        console.log("课程评论")
        //获取被评论的课程的机构
        initInsList();
        initInsClassComment(1,insClassId,curriculumState,curriculumClass);
        $('.evaScreen').hide();
        $('.curriculum').show();
    })



    //删除按钮弹窗
    $('body').on('click', '.delete', function () {
        var commentId = $(this).attr("data-commentId");
        $.confirm("您是否确定删除该条评论？", function (data) {

            if (data) {
                delIns(commentId,0);
            }
        });
    });

    //点击审核
    $('body').on('click', '.evaluationIns', function () {
        var commentId = $(this).attr("data-commentId");
        $.confirm('您是否确认审核通过该条评论',function (data) {
            if(data){
                evaluationIns(commentId,0);
            }
        })

    });



    //删除课程按钮弹窗
    $('body').on('click', '.deleteClass', function () {
        var commentId = $(this).attr("data-commentId");
        $.confirm("您是否确定删除该条评论？", function (data) {

            if (data) {
                delIns(commentId,1);
            }
        });
    });

    //点击课程审核
    $('body').on('click', '.evaluationInsClass', function () {
        var commentId = $(this).attr("data-commentId");
        $.confirm('您是否确认审核通过该条评论',function (data) {
            if(data){
                evaluationIns(commentId,1);
            }
        })

    });

    //初始机构化评论
    initInsComment();
    $('.evaTitle').eq(0).addClass('active');

    //课程评论下的被评论的课程对应的机构id
    var insClassId = '';
    $('body').on('click','.insClass',function () {
        curriculumClass='';
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
        insClassId = $(this).attr("data-insid");
        //查询该机构下的课程
        findInsClassByInsId(insClassId);
        initInsClassComment(1,insClassId,curriculumState,curriculumClass);
    });



});





var currPage='';
var currPageClass='';
//查询机构评论
function initInsComment(page=1,insCommntId='',reviewStatus='') {
    currPage = page;

    $.ajax({
        url:rootPath+"/comment/findInsComment",
        type:"post",
        data:{
            "relationId":insCommntId,
            "isCheck":reviewStatus,
            "page":page
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(data){
            var userType = data.usersType;
            var jsonData = data.comment;
            var html ='';
            if(!jsonData||jsonData.data.length==0){
                html+='<div style="text-align: center">暂无数据</div>'
            }else{
                $.each(jsonData.data,function(i,item){
                    var score=item.score?item.score:0;
                    var check = item.isCheck;
                    var com = item.content ? unescape(item.content.replace(/\\u/g, '%u')) : "";
                    let  _check = "";

                    if(check==0){
                        _check = '<button  class="evaluationIns" id="evaluation" data-commentId="'+item.id+'">审核通过</button>';
                    }

                    var del = '<button class="delete " id="delete" data-commentId="'+item.id+'">删除</button>';

                    if(userType == 'INSTITUTION_MANAGE'){
                        _check='';
                        del ='';
                    }

                    var name = item.nickName;
                    if(item.nickName == '' || item.nickName == null ){
                        name = item.mobile;
                    }
                    var scorehtml ='';
                    if(score==1){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==2){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==3){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==4){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==5){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i></span>';
                    }
                    html+='<li class="Y_clear">'+
                        '<div class="headpic">'+
                        '<img src="'+(item.headPicMax ? item.headPicMax :  rootPath + "/images/user/head_top.png")+'" alt="" width="50" height="50">'+
                        '</div>'+
                        ' <div class="Y_backcomment_content">'+
                        '<div class="word Y_clear">'+
                        '<span>'+name+'：'+'</span>'+
                        '<span class="wordcontent" style="word-break:break-all">'+com+'</span>'+
                        '</div>'+
                        '<p class="Y_time Y_mt10">'+
                        '<span>'+item.createTimeText+' </span>'+
                        '<span>'+item.createTimeText2+' </span>'+
                        '<span>'+ scorehtml+'</span>'+
                        '</p>'+
                        '</div>'+
                        _check+
                        del+
                        '</li>'
                });
            }
            $(".evaScreen ").children('.comment_all').html(html);

            if (jsonData.rowCount > 10) {
                $(".pagination").html('');
                $(".pagination").pagination(jsonData.rowCount,
                    {
                        next_text: "下一页",
                        prev_text: "上一页",
                        current_page: jsonData.pageNo ,
                        link_to: "javascript:void(0)",
                        num_display_entries: 8,
                        items_per_page: jsonData.pageSize,
                        num_edge_entries: 1,
                        callback: function (page, jq) {
                            var pageNo = page + 1;
                            initInsComment(pageNo,insCommntId,reviewStatus);
                        }
                    });
            } else {
                $(".pagination").html('');
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })

}

//机构课程评价
function initInsClassComment(page=1,ins='',reviewStatus='',relationId='') {
    currPageClass = page;

    $.ajax({
        url:rootPath+"/comment/findInsClassComment",
        type:"post",
        data:{
            "insId":ins,
            "relationId":relationId,
            "isCheck":reviewStatus,
            "page":page
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(data){
            var userType = data.usersType;
            var jsonData = data.comment;
            var html ='';
            if(!jsonData||jsonData.data.length==0){
                html+='<div style="text-align: center">暂无数据</div>'
            }else{
                $.each(jsonData.data,function(i,item){
                    var score=item.score?item.score:0;
                    var com = (item.content ? unescape(item.content.replace(/\\u/g, '%u')) : "");
                    var check = item.isCheck;

                    let  _check = "";

                    if(check==0){
                        _check = '<button  class="evaluationInsClass" id="evaluation" data-commentId="'+item.id+'">审核通过</button>';
                    }

                    var del = '<button class="deleteClass" id="delete" data-commentId="'+item.id+'">删除</button>';

                    if(userType == 'INSTITUTION_MANAGE'){
                        _check='';
                        del ='';
                    }

                    var name = item.nickName;
                    if(item.nickName == '' || item.nickName == null ){
                        name = item.mobile;
                    }
                    if(score==1){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==2){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==3){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==4){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65f;</i></span>';
                    }else if(score==5){
                        scorehtml='<span>评分:</span><span class="Y_mr10" style="color: #fb9f1b;">' +
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i>'+
                            '<i class="iconfont">&#xe65e;</i></span>';
                    }
                    html+='<li class="Y_clear">'+
                        '<div class="headpic">'+
                        '<img src="'+(item.headPicMax ? item.headPicMax :  rootPath + "/images/teachers.png")+'" alt="" width="50" height="50">'+
                        '</div>'+
                        ' <div class="Y_backcomment_content">'+
                        '<div class="word Y_clear">'+
                        '<span>'+name+'：'+'</span>'+
                        '<span class="wordcontent" style="word-break:break-all">'+com+'</span>'+
                        '</div>'+
                        '<p class="Y_time Y_mt10">'+
                        '<span>'+item.createTimeText+' </span>'+
                        '<span>'+item.createTimeText2+' </span>'+
                        '<span>'+ scorehtml+'</span>'+
                        '</p>'+
                        '</div>'+
                        _check+
                        del+
                        '</li>'
                });
            }
            $(".curriculum").children('.comment_all').html(html);

            if (jsonData.rowCount > 10) {
                $(".paginationClass").html('');
                $(".paginationClass").pagination(jsonData.rowCount,
                    {
                        next_text: "下一页",
                        prev_text: "上一页",
                        current_page: jsonData.pageNo ,
                        link_to: "javascript:void(0)",
                        num_display_entries: 8,
                        items_per_page: jsonData.pageSize,
                        num_edge_entries: 1,
                        callback: function (page, jq) {
                            var pageNo = page + 1;
                            initInsClassComment(pageNo,ins,reviewStatus,relationId);
                        }
                    });
            } else {
                $(".paginationClass").html('');
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })

}



//审核评论
function evaluationIns(commentId,flag) {

    $.ajax({
        url:rootPath+"/comment/updateComment",
        type:"post",
        data:{
            "id":commentId,
            "isCheck":1
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){

            $.msg("审核成功!");

            //机构评论里的状态
            var insStatu ='';
            var insStatus = $("#status").children('a');
            for(var i=0;i<insStatus.length;i++){
                if(insStatus.eq(i).hasClass("btn-primary")){
                    insStatu = insStatus.eq(i).attr("data-review");
                }
            }

            //机构评论里的机构id
            var insCommentId ='';
            var insId = $("#ins").children('a');
            for(var i=0;i<insId.length;i++){
                if(insId.eq(i).hasClass("btn-primary")){
                    insCommentId = insId.eq(i).attr("data-insid");
                }
            }

            //课程评论里的机构id
            var insCommentClassId ='';
            var insClassId = $("#insClass").children('a');
            for(var i=0;i<insClassId.length;i++){
                if(insClassId.eq(i).hasClass("btn-primary")){
                    insCommentClassId = insClassId.eq(i).attr("data-insid");
                }
            }

            //课程id
            var classId = '';
            var classList = $("#curriculumClass").children('a');
            for(var i = 0; i<classList.length;i++){
                if(classList.eq(i).hasClass("btn-primary")){
                    classId = classList.eq(i).attr("data-insclassid");
                }
            }


            //课程评论里的状态
            var status = '';
            var statusList = $("#curriculumState").children('a');
            for(var i = 0;i<statusList.length;i++){
                if(statusList.eq(i).hasClass("btn-primary")){
                    status = statusList.eq(i).attr("data-review");
                }

            }

            if(flag == 0){
                //机构
                initInsComment(currPage,insCommentId,insStatu);
            }else{
                //课程
                initInsClassComment(currPageClass,insCommentClassId,status,classId);
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })

}

//删除评论
function delIns(commentId,flag) {

    $.ajax({
        url:rootPath+"/comment/updateComment",
        type:"post",
        data:{
            "id":commentId,
            "delFlag":1
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){
            //机构评论里的状态
            var insStatu ='';
            var insStatus = $("#status").children('a');
            for(var i=0;i<insStatus.length;i++){
                if(insStatus.eq(i).hasClass("btn-primary")){
                    insStatu = insStatus.eq(i).attr("data-review");
                }
            }

            //机构评论里的机构id
            var insCommentId ='';
            var insId = $("#ins").children('a');
            for(var i=0;i<insId.length;i++){
                if(insId.eq(i).hasClass("btn-primary")){
                    insCommentId = insId.eq(i).attr("data-insid");
                }
            }

            //课程评论里的机构id
            var insCommentClassId ='';
            var insClassId = $("#insClass").children('a');
            for(var i=0;i<insClassId.length;i++){
                if(insClassId.eq(i).hasClass("btn-primary")){
                    insCommentClassId = insClassId.eq(i).attr("data-insid");
                }
            }

            //课程id
            var classId = '';
            var classList = $("#curriculumClass").children('a');
            for(var i = 0; i<classList.length;i++){
                if(classList.eq(i).hasClass("btn-primary")){
                    classId = classList.eq(i).attr("data-insclassid");
                }
            }


            //课程评论里的状态
            var status = '';
            var statusList = $("#curriculumState").children('a');
            for(var i = 0;i<statusList.length;i++){
                if(statusList.eq(i).hasClass("btn-primary")){
                    status = statusList.eq(i).attr("data-review");
                }

            }

            if(flag == 0){
                //机构
                initInsComment(currPage,insCommentId,insStatu);
            }else{
                //课程
                initInsClassComment(currPageClass,insCommentClassId,status,classId);
            }


        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })


}


//课程评论下的机构列表（这里是被评论的课程所对应的机构）
function initInsList() {
    $.ajax({
        url:rootPath+"/comment/initInsClassList",
        type:"post",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){
            var html ='<span style="font-size: 16px;margin-right: 30px;">机构名称</span>\n' +
                '<a href="javascript:void(0)" class="btn btn-default btn-primary insClass" data-insId="">全部</a>';
            if(jsonData.length>0){
                for(var i in jsonData){
                    html+=' <a href="javascript:void(0)" class="btn btn-default insClass"  data-insId="'+jsonData[i].id+'">'+jsonData[i].name +'</a>';
                }

                $("#insClass").html(html);
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })
}


//查询机构下的课程
function findInsClassByInsId(id) {
    var html ='<span style="font-size: 16px;margin-right: 30px;">课程名称</span>\n' +
        '<a href="javascript:void(0)" class="btn btn-default btn-primary curriculumClass" data-classId="">全部</a>';
    if(id == '' ){
        $("#curriculumClass").html(html);
        return;
    }
    $.ajax({
        url:rootPath+"/comment/findInsClassByInsId",
        type:"post",
        data:{
            "id":id
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){

            if(jsonData.length>0){
                for(var i in jsonData){
                    html+=' <a href="javascript:void(0)" class="btn btn-default curriculumClass "  data-insClassId="'+jsonData[i].id+'">'+jsonData[i].name +'</a>';
                }

            }
            $("#curriculumClass").html(html);

        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })
}