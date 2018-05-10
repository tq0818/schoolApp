var nowPage = 0;
var pageSize = 7;
var recommendNum = null;


$(function () {
    //分页
    $(".pagination").pagination('',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page:'',
            link_to: "",
            num_display_entries: 6,
            items_per_page: 6,
            num_edge_entries: 1,
            callback: function (page) {

            }
        }
    );
    $(".paginationOnLine").pagination('',
        {
            next_text: "下一页",
            prev_text: "上一页",
            current_page:'',
            link_to: "",
            num_display_entries: 6,
            items_per_page: 6,
            num_edge_entries: 1,
            callback: function (page) {

            }
        }
    );
    //    左侧active切换
    $selectSubMenus('course');
    //图片移入显示隐藏的按钮和文字
    $('.imgList').find('li').mouseover(function () {
        $(this).children('.imgInfo').show();
        $(this).children('.listBg').show();
    });
    $('.imgList').find('li').mouseleave(function () {
        $(this).children('.imgInfo').hide();
        $(this).children('.listBg').hide();
    });
    //筛选
    $('.chooseBtn').children('a').click(function () {
        if(!$(this).hasClass('btn-primary')){
            $(this).addClass('btn-primary').siblings('a').removeClass('btn-primary');
        }

        getClassTypeList();
    });
   /* //删除弹窗
    $('body').on('click','.deleteBtn',function () {
        $.confirm('是否确定删除该课程?',function (data) {
            if(data){
                console.log('点击了确定');
            }
        })
    });*/
    //tab切换
    $('.courseLine').click(function () {
            $(this).addClass('active');
            $(this).siblings().removeClass('active');
            if($(this).index()==0){
                $('.courseUnderLine').show();
                $('.courseOnLine').hide();
            }else{
                $('.courseUnderLine').hide();
                $('.courseOnLine').show();
            }
    });
    //二次确认添加课程
    $('body').on('click','.addConfirm',function () {
        $.confirm('是否确定添加该课程？',function (data) {
            if(data){
                $('.addClassPopup').hide();

            }
        });
    });
    //添加课程弹窗
    $('.addCourse').click(function () {
        $('.addClassPopup').show();
    });
    $('.closeCoursePopup').click(function () {
        $('.addClassPopup').hide();
    });
    //关联
    $('.relation').click(function () {
            if($(this).html()=='关联'){
                $(this).html("取消关联");
                $(this).siblings('.relationResult').html("已关联");
            }else{
                $(this).html("关联");
                $(this).siblings('.relationResult').html("未关联");
            }
    });
    //获取课程信息
    getClassTypeList()

});

function getClassTypeList(){
    if(recommendNum == null){
        getRecommendCount2();
        return;
    }
    $.ajax({
        url: rootPath+'/institutionClassType/getClassTypeList',
        data: {
            insId:$('#insId').val(),
            status:getUpDownStatus(),
            pageStart:nowPage,
            pageSize:pageSize
        },
        type: 'post',
        beforeSend: function () {
            $(".loading").show();
            $(".loading-bg").show();
        },
        complete:function(){
            $(".loading").hide();
            $(".loading-bg").hide();
        },
        success: function (json) {
           var list = json.data;
           var html = `<li class="addImg mienShow" id="">
                            <i class="icon iconfont"></i>
                       </li>`;
           for(var i in list){
               html += `<li>
                            <img src="${list[i].coverUrl}" alt="" style="width: 100%;height: auto">
                                <span class="imgInfo">${list[i].name}</span>
                                ${
                                    list[i].isReser == 1 ? 
                                        "<a href='javascript:void(0)' data-id="+list[i].id+" class='btn btn-primary btn-sm rightShow'>取消推荐</a>" :
                                        ( recommendNum < 2 && list[i].isReser == 0 && list[i].isShelves == 1  ? 
                                            "<a href='javascript:void(0)' data-id="+list[i].id+" class='btn btn-primary btn-sm rightShow'>推荐</a>" : 
                                            '')
                                            
                                } 
                                <div class="listBg">
                                <a href="javascript:void(0)" data-id="${list[i].id}" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                <a href="javascript:void(0)" data-id="${list[i].id}" class="btn btn-success btn-sm ">${list[i].isShelves == 1 ? '下架' : '上架'}</a>
                                <a href="javascript:void(0)" data-id="${list[i].id}" class="btn btn-success btn-sm " >管理</a>
                                </div>
                        </li>`;
           }


            $(".pagination").pagination(json.rowCount,
                {
                    next_text: "下一页",
                    prev_text: "上一页",
                    current_page:json.pageNo,
                    link_to: "javascript:getClassTypeList()",
                    num_display_entries: 7,
                    items_per_page: 7,
                    num_edge_entries: 1,
                    callback: function (page) {
                        nowPage = page;
                        getClassTypeList();
                    }
                }
            );



        $('#courseContainer').html(html);

            $('.imgList').find('li').mouseover(function () {
                $(this).children('.imgInfo').show();
                $(this).children('.listBg').show();
            });
            $('.imgList').find('li').mouseleave(function () {
                $(this).children('.imgInfo').hide();
                $(this).children('.listBg').hide();
            });

            //添加监听器
            $("li a").click(function(){
                controlClass(this);
            });

        }
    });
}


function controlClass(dom){
   var id = $(dom).attr('data-id');
   var action = $(dom).html() ;
    if(action == '下架' || action == '上架' ) {
       /*
        提示框暂时不用
       $.confirm("是否要" + action + "该课程?", function (s) {
            if (s) {

            }
        })*/

        $.post(rootPath + '/institutionClassType/upDownClass', {cid: id}, function (json) {
            $.msg(json == 'success' ? '操作成功' : '操作失败');
            if (json == 'success') {
                getClassTypeList();
            }
        })

    } else if(action == '取消推荐' || action == '推荐'){

        $.post(rootPath+"/institutionClassType/recommend",{insId:$('#insId').val() , cid:id },function(json){
            if (json == 'success') {
                $.msg('操作成功');
                getRecommendCount2();
               // getClassTypeList();
            }else{
                $.msg(json);
            }
        })


    } else if(action == '删除'){
        $.confirm('是否确定删除该课程?',function (data) {
            if(data){
                $.post(rootPath + '/institutionClassType/delClass', {cid: id}, function (json) {
                    $.msg(json == 'success' ? '操作成功' : '操作失败');
                    if (json == 'success') {
                        getClassTypeList();
                    }
                })
            }
        })
    }

}

function getRecommendCount2(){
    $.post(rootPath+"/institutionClassType/recommendCount",{insId:$('#insId').val()},function(json){
        if(json.status == 1){
            recommendNum = json.num;
            getClassTypeList();
        }

    })
}


function getRecommendCount(){
    $.post(rootPath+"/institutionClassType/recommendCount",{insId:$('#insId').val()},function(json){
       // json =  JSON.parse(json);
        if(json.status == 1){
            recommendNum = json.num;
        }
    })
}



function getUpDownStatus(){
    var arr = $(".chooseBtn").find("a");
    for(var i in arr){
       if($(arr[i]).hasClass('btn-primary')){
           return i;
       }
    }
}

