$(function () {
    //选中二级菜单
    $selectSubMenu('recommendation');

    //判断请求参数，显示不同的tab页面
    var urlParams = location.search;
    if(urlParams != null && urlParams.length > 1){
        urlParams = urlParams.substring(1,urlParams.length);
    }

   // console.log('urlParams = '+urlParams);
    var param =  getParam(urlParams);
   // console.log(param);
    if(param.type){
        $('.classificationRecommendation').hide();
        $('.listRecommendation').show();
        $('.recommendList').addClass('btn-primary');
        $('.recommendType').removeClass('btn-primary');
        getRecommendTypeData(); //首页列表推荐
    }else{
        //获取推荐类型
        getRecommendTypeList();
    }

    //推荐tab切换
    $('#recommendBtn').children('a').click(function () {
        if($(this).html()=="首页分类推荐"){
            $('.classificationRecommendation').show();
            $('.listRecommendation').hide();
            getRecommendTypeList(); //首页分类推荐数据
        }else{
            $('.classificationRecommendation').hide();
            $('.listRecommendation').show();
            getRecommendTypeData(); //首页列表推荐
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

    //推荐位置、推荐分类、推荐状态按钮颜色切换
    $('.changeBtn').children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
        nowIndexPage = 0;
        getIndexRecommendList();



    });




    $('.btnSearch').click(function(){
        nowIndexPage = 0;
        getIndexRecommendList();
    })


});









