var reviewStatus = "";
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



    //机构评价和课程评价tab切换
    $('.evaTitle').click(function () {
        if ($(this).html() == '机构评价') {
            $('.evaScreen').show();
            $('.curriculum').hide();
        } else {
            $('.evaScreen').hide();
            $('.curriculum').show();
        }

        //课程评价
        initInsClassComment();
    });

    //删除按钮弹窗
    $('body').on('click', '.delete', function () {
        var commentId = $(this).attr("data-commentId");
        $.confirm("您是否确定删除该条评论？", function (data) {

            if (data) {
                delIns(commentId);
            }
        });
    });

    //点击审核
    $('body').on('click', '.evaluationIns', function () {
        var commentId = $(this).attr("data-commentId");
        evaluationIns(commentId);
    });



    //删除课程按钮弹窗
    $('body').on('click', '.deleteClass', function () {
        var commentId = $(this).attr("data-commentId");
        $.confirm("您是否确定删除该条评论？", function (data) {

            if (data) {
                delIns(commentId);
            }
        });
    });

    //点击课程审核
    $('body').on('click', '.evaluationInsClass', function () {
        var commentId = $(this).attr("data-commentId");
        evaluationIns(commentId);
    });

    //初始机构化评论
    let courseBtn = sessionStorage.getItem('courseBtn');
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
    }

});





var currPage='';
var currPageClass='';
//查询机构评论
function initInsComment(page=1,reviewStatus='') {
    console.log('机构评价');
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
            if(!jsonData||jsonData.data.length==0){
                /*$('.coursePackageList').append('<tr><td colspan="4">暂无数据</td></tr>');
                return;*/
            }
            var html ='';
            $.each(jsonData.data,function(i,item){
                var score=item.score?item.score:0;
                var check = item.isCheck;
                let  _check = "";
                if(check==0){
                    _check = '<button  class="evaluationIns" id="evaluation" data-commentId="'+item.id+'">审核通过</button>';
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
                    '<span>'+item.nickName+'</span>'+
                    '<span class="wordcontent" style="word-break:break-all">'+item.content+'</span>'+
                    '</div>'+
                    '<p class="Y_time Y_mt10">'+
                    '<span>'+item.createTimeText+' </span>'+
                    '<span>'+item.createTimeText2+' </span>'+
                    '<span>评分:'+ scorehtml+'</span>'+
                    '</p>'+
                    '</div>'+
                    _check+
                    '<button class="delete " id="delete" data-commentId="'+item.id+'">删除</button>'+
                    '</li>'
            });
            $(".evaScreen ").children('.comment_all').html(html);

            if (jsonData.rowCount > 2) {
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
            if(!jsonData||jsonData.data.length==0){
                /*$('.coursePackageList').append('<tr><td colspan="4">暂无数据</td></tr>');
                return;*/
            }
            var html ='';
            $.each(jsonData.data,function(i,item){
                var score=item.score?item.score:0;
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
                    '<span>'+item.nickName+'</span>'+
                    '<span class="wordcontent" style="word-break:break-all">'+item.content+'</span>'+
                    '</div>'+
                    '<p class="Y_time Y_mt10">'+
                    '<span>'+item.createTimeText+' </span>'+
                    '<span>'+item.createTimeText2+' </span>'+
                    '<span>评分:'+ scorehtml+'</span>'+
                    '</p>'+
                    '</div>'+
                    '<button  class="evaluationInsClass" id="evaluation" data-commentId="'+item.id+'">审核通过</button>'+
                    '<button class="deleteClass" id="delete" data-commentId="'+item.id+'">删除</button>'+
                    '</li>'
            });
            $(".curriculum").children('.comment_all').html(html);

            if (jsonData.rowCount > 2) {
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
function evaluationIns(commentId) {

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

            // initInsComment(currPageClass);
            location.reload();
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })

}

//删除评论
function delIns(commentId) {
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
            // initInsComment(currPage);
            location.reload();

        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })


}