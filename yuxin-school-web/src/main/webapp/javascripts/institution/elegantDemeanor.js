$(function () {
    //    左侧active切换
    $selectSubMenus('elegantDemeano');
    //分页插件
    $(".pagination").pagination('', {
        next_text : "下一页",
        prev_text : "上一页",
        current_page : 1,
        link_to : "javascript:void(0)",
        num_display_entries : 8,
        items_per_page : 12,
        num_edge_entries : 1,
        callback:function(page){
            var pageNo = page + 1;

        }
    });
    //点击视频上传出视频上传弹窗
    $('#videoUp').click(function () {
        $('.videoUpload').show();
    });
    $('.closeVideoUpload').click(function () {
        $('.videoUpload').hide();
    });

    //点击风采上传弹窗
    $('#eleShow').click(function () {
        $('.elePic').show();
    });
    $('.closeElePic').click(function () {
        $('.elePic').hide();
    });
});