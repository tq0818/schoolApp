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
    });
    //删除弹窗
    $('body').on('click','.deleteBtn',function () {
        $.confirm('是否确定删除该课程?',function (data) {
            if(data){
                console.log('点击了确定');
            }
        })
    });
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
                console.log("确认添加");
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


});