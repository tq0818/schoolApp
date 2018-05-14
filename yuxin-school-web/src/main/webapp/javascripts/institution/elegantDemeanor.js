$(function () {
    //    左侧active切换
    $selectSubMenus('elegantDemeano');
  //分页插件
/*    $(".pagination").pagination('${rowCount}', {
        next_text : "下一页",
        prev_text : "上一页",
        current_page :'${pageNo - 1}',
        link_to : "javascript:void(0)",
        num_display_entries : 9,
        items_per_page : 9,
        num_edge_entries : 1,
        callback:function(page){
            var pageNo = page + 1;
            queryInstitutionStyle(pageNo);
        }
    });*/
    //点击视频上传出视频上传弹窗
    $('#videoUp').click(function () {
        $('.videoUpload').show();
    });
    $('.closeVideoUpload').click(function () {
        $('.videoUpload').hide();
    });

    //点击风采上传弹窗
    $('#eleShow').click(function () {
        $('#elegant').show();
    });
    $('.closeElePic').click(function () {
        $('#elegant').hide();
    });
    //点击封面弹窗
    $('#addCover').click(function () {
        $('#cover').show();
    });
    $('.closeElePic').click(function () {
        $('#cover').hide();
    });
    //关闭大图
    $(document).click(function(){
    	$('.bigImg').hide();
    });
    
    $('.bigImg').click(function(){
    	return false;
    });
    
    //为图片添加点击事件,以便图片方大
    $('.imgClick').click(function(){
    	var url = $(this).attr('src');
    	$('.bigImg').show().attr('src',url);
    	return false;
    });
    
    
});

//查询风采
function queryInstitutionStyle(pageNo){
	$.ajax({
        url: rootPath + "/institutionStyle/queryInsStyle",
        data: {"page":pageNo,
            "pagesize":9,
            "relationId":$("#institutionId").val(),
        },beforeSend: function (XMLHttpRequest) {
           /* $(".loading").show();
            $(".loading-bg").show();*/
        },
        dataType: "html",
        success: function (data) {
//            $(".loading").hide();
//            $(".loading-bg").hide();
            $("#insStyleInfo").html("").html(data);
        }
    });
}