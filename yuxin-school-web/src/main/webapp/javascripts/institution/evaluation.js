var reviewStatus = "";
$(function () {
    //    左侧active切换
    $selectSubMenus('evaluate');
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

    $('.evaScreen').children('div').children('a').click(function () {


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

        initInsComment(1, reviewStatus);


    });
    
    //点击机构评论
    $('body').on('click','.insComment',function () {
        initInsComment();
        $('.evaScreen').show();
        $('.curriculum').hide();
    })

    //点击课程评论
    $('body').on('click','.insClassComment',function () {
        initInsClassComment();
        $('.evaScreen').hide();
        $('.curriculum').show();
    })


    //课程评价筛选
    $('.curriculum').children('div').children('a').click(function () {
        // if($(this).html() == '全部' || $(this).html() == '审核通过'){
        //     $("#evaluation").hide();
        // }else{
        //     $("#evaluation").show();
        // }
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
    });

    //课程筛选
    let curriculumClass = '';
    $('#curriculumClass').children('a').click(function () {
        curriculumClass = $(this).attr('data-classId');
        initInsClassComment(1,curriculumState,curriculumClass);

    });
    //状态筛选
    let curriculumState = '';
    $('#curriculumState').children('a').click(function () {
        curriculumState = $(this).attr('data-review');
        initInsClassComment(1,curriculumState,curriculumClass);
    });


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
            var userId = $(this).attr("data-userId");
        $.confirm('您是否确认审核通过该条评论',function (data) {
            if(data){
                evaluationIns(userId,commentId,0);
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
        var userId = $(this).attr("data-userId");
        $.confirm('您是否确认审核通过该条评论',function (data) {
            if(data){
                evaluationIns(userId,commentId,1);
            }
        })
    });

    //初始机构化评论
    initInsComment();
    $('.evaTitle').eq(0).addClass('active');
   /* let courseBtn = sessionStorage.getItem('courseBtn');
    if(courseBtn=='机构评价'){
        initInsComment();
        $('.evaScreen').show();
        $('.curriculum').hide();
        $('.evaTitle').eq(0).addClass('active');
    }else{
        initInsClassComment();
        $('.evaScreen').hide();
        $('.curriculum').show();
        $('.evaTitle').eq(1).addClass('active');
    }*/

    });





var currPage='';
var currPageClass='';
//查询机构评论
function initInsComment(page=1,reviewStatus='') {
    currPage = page;

    var insId = $("#insId").val();//机构id

    $.ajax({
        url:rootPath+"/comment/findInsComment",
        type:"post",
        data:{
            "relationId":insId,
            "isCheck":reviewStatus,
            "page":page
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){
            var userType = jsonData.usersType;
            jsonData = jsonData.comment;
            var html ='';
            if(!jsonData||jsonData.data.length==0){
                html+='<div style="text-align: center">暂无数据</div>'
            }else{
                $.each(jsonData.data,function(i,item){
                    var score=item.score?item.score:0;
                    var check = item.isCheck;
                    var com = (item.content ? unescape(item.content.replace(/\\u/g, '%u')) : "");
                    let  _check = "";
                    if(check==0){
                        _check = '<button  class="evaluationIns" id="evaluation" data-commentId="'+item.id+'" data-userId="'+item.userId+'">审核通过</button>';
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
                            initInsComment(pageNo,reviewStatus);
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
function initInsClassComment(page=1,reviewStatus='',relationId='') {
    console.log('课程评价');
    currPageClass = page;

    var insId = $("#insId").val();//机构id

    $.ajax({
        url:rootPath+"/comment/findInsClassComment",
        type:"post",
        data:{
            "insId":insId,
            "relationId":relationId,
            "isCheck":reviewStatus,
            "page":page
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){
            var userType = jsonData.usersType;
            jsonData = jsonData.comment;

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
                        _check = '<button  class="evaluationInsClass" id="evaluation" data-commentId="'+item.id+'" data-userId="'+item.userId+'">审核通过</button>';
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
                            initInsClassComment(pageNo,reviewStatus);
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
function evaluationIns(userId,commentId,flag) {

    $.ajax({
        url:rootPath+"/comment/updateComment",
        type:"post",
        data:{
            "id":commentId,
            "isCheck":1,
            "userId":userId
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){

            $.msg("审核成功！");
            var classId = '';
            var classList = $("#curriculumClass").children('a');
            for(var i = 0; i<classList.length;i++){
                if(classList.eq(i).hasClass("btn-primary")){
                    classId = classList.eq(i).attr("data-classId");
                }
            }
            var status = '';
            var statusList = $("#curriculumState").children('a');
            for(var i = 0;i<statusList.length;i++){
                if(statusList.eq(i).hasClass("btn-primary")){
                    status = statusList.eq(i).attr("data-review");
                }

            }

            var insStatu ='';
            var insStatus = $("#status").children('a');
            for(var i=0;i<insStatus.length;i++){
                if(insStatus.eq(i).hasClass("btn-primary")){
                    insStatu = insStatus.eq(i).attr("data-review");
                }
            }
            if(flag == 0){
                initInsComment(currPage,insStatu);
            }else{
                initInsClassComment(currPageClass,status,classId);
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
            var classId = '';
            var classList = $("#curriculumClass").children('a');
            for(var i = 0; i<classList.length;i++){
                if(classList.eq(i).hasClass("btn-primary")){
                    classId = classList.eq(i).attr("data-classId");
                }
            }
            var status = '';
            var statusList = $("#curriculumState").children('a');
            for(var i = 0;i<statusList.length;i++){
                if(statusList.eq(i).hasClass("btn-primary")){
                    status = statusList.eq(i).attr("data-review");
                }

            }

            var insStatu ='';
            var insStatus = $("#status").children('a');
            for(var i=0;i<insStatus.length;i++){
                if(insStatus.eq(i).hasClass("btn-primary")){
                    insStatu = insStatus.eq(i).attr("data-review");
                }
            }

            console.log(insStatu,status,classId)
            if(flag == 0){
                //机构
                initInsComment(currPage,insStatu);
            }else{
                //课程
                initInsClassComment(currPageClass,status,classId);
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })


}