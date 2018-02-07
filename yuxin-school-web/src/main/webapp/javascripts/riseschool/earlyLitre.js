//小升初，学校管理
$(function () {
    //点击下架
    $('.offShelf').click(function () {
        if($(this).html()=='下架'){
            $(this).html('上架');
            $(this).parents('td').siblings('.offShelfState').html('已下架');
        }else{
            $(this).html('下架');
            $(this).parents('td').siblings('.offShelfState').html('已上架');
        }
    });
    //点击置顶
    $('.top').click(function () {
        if($(this).html()=='置顶'){
            $(this).html('取消置顶');
            $(this).parents('td').siblings('.offShelfState').html('已置顶');
        }else{
            $(this).html('置顶');
            $(this).parents('td').siblings('.offShelfState').html('未置顶');
        }
    });
    //点击账号管理弹窗弹窗
    $('.countManagement').click(function () {
        $('.opacityPopup').fadeIn();
        $('.countPopup').fadeIn();
    });
    //点击保存或取消，隐藏弹窗
    $('.countPopupCancel').click(function () {
        $('.opacityPopup').fadeOut();
        $('.countPopup').fadeOut();

        $('.addNewSchool').fadeOut();
    });
    $('.countPopupSave').click(function () {
        $('.opacityPopup').fadeOut();
        $('.countPopup').fadeOut();

        $('.addNewSchool').fadeOut();
    });
    //点击添加学校按钮弹窗
    $('.addSchool').click(function () {
        $('.opacityPopup').fadeIn();
        $('.addNewSchool').fadeIn();
    });
    //招生类型选择
    $('.enrolment').children('a').click(function () {
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
    });
    //置顶状态选择
    $('.topState').children('a').click(function () {
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
    });





});