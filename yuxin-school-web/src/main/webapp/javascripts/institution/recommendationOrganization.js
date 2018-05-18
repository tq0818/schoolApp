$(function () {
    //选中二级菜单
    $selectSubMenu('recommendation');
    //推荐tab切换
    $('#recommendBtn').children('a').click(function () {
        if($(this).html()=="首页分类推荐"){
            $('.classificationRecommendation').show();
            $('.listRecommendation').hide();
        }else{
            $('.classificationRecommendation').hide();
            $('.listRecommendation').show();
        }
    });
    //推荐和取消推进啊
    $('.recomendBtn').click(function () {
        if($(this).html()=="取消推荐"){
            $(this).html("推荐");
            $(this).siblings('.recomendState').html('未推荐');
        }else{
            $(this).html("取消推荐");
            $(this).siblings('.recomendState').html('已推荐');
        }
    });
    //分页插件
    $(".pagination").pagination('', {
        next_text : "下一页",
        prev_text : "上一页",
        current_page : 1,
        link_to : "javascript:void(0)",
        num_display_entries : 10,
        items_per_page : 10,
        num_edge_entries : 1,
        callback:function(page){
            var pageNo = page + 1;

        }
    });
    //推荐位置、推荐分类、推荐状态按钮颜色切换
    $('.changeBtn').children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');

        if($(this).hasClass('recommendType')){
           // console.log('点击首页分类推荐 。。。 ');

            getRecommendTypeList();

        }else{
            getIndexRecommendList();
        }

    });

    getRecommendTypeList();


});









