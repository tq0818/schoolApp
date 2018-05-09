$(function () {
    //选中二级菜单
    $selectSubMenu('organizationIndex');

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
    //管理显示弹窗
    $('.manageBtn').mouseover(function () {
        $(this).siblings('ul').show();
    });
    $('.manageBtn').mouseleave(function () {
        $(this).siblings('ul').hide();
    });
    $('.box').mouseover(function () {
        $(this).show();
    });
    $('.box').mouseleave(function () {
        $(this).hide();

    });
    //认证取消认证
    $('.authentication').click(function () {
        if($(this).html()=='认证'){
            $(this).html("取消认证");
            $(this).parents('td').siblings('.authenticationReal').html('已认证');
        }else{
            $(this).html("认证");
            $(this).parents('td').siblings('.authenticationReal').html('未认证');
        }
    });
    //上架和下架
    $('.frameLower').click(function () {
        if($(this).html()=='下架'){
            $(this).html("上架");
            $(this).parents('td').siblings('.frameLowerReal').html('已下架');
        }else{
            $(this).html("下架");
            $(this).parents('td').siblings('.frameLowerReal').html('已上架');
        }
    });
    //点击账号管理
    $('.countManage').click(function () {
        $('.createCount').show();
    });
    $('.closeCountPopup').click(function () {
        $('.createCount').hide();
    });

    //添加机构弹窗
    $('.addOrganization').click(function () {
        $('.addingMechanism').show();
    });
    $('.closeMechanism').click(function () {
        $('.addingMechanism').hide();
    });
    //认证状态、推荐状态、上下架按钮效果切换
    $('.changeBtn').children('a').click(function () {
        if(!$(this).hasClass('btn-primary')){
            $(this).addClass('btn-primary');
            $(this).siblings('a').removeClass('btn-primary');
        }
    });
    //系统标签增加和删除
    $('.addSystem').click(function () {
        let systemLength = $('.systemBtn').length;
        if(systemLength<3){
            var _html =
                `<span href="##" class="systemBtn">
                <span class="systemLabel">我的名字没</span>
                <i class="icon iconfont deleteBtn">&#xe610;</i>
            </span>`;
            $(this).before(_html);
        }
    });
    $('body').on('click','.deleteBtn',function () {
        $(this).parents('.systemBtn').remove();
    });
    //添加机构分类
    $('.addType').click(function () {
        let _html = `
            <div style="padding-left: 80px;margin-top: 6px;">
                <select name="" id="">
                    <option value="">请选择一级分类</option>
                </select>
                <select name="" id="">
                    <option value="">请选择二级分类</option>
                </select>
                <span class="iconBtn deleteType">-</span>
            </div>
        `;
        $('#orgType').append(_html);
    });
    //删除分类
    $('body').on('click','.deleteType',function () {
        $(this).parent('div').remove();
    });
    //添加座机号
    $('.addMachine').click(function () {
        let _html = `
            <div>
                    <input type="text" placeholder="区号" style="width: 30px;">-
                    <input type="text" placeholder="请输入座机号">
                    <span class="iconBtn deleteMachine">-</span>
                </div>
        `;
        $('#listMachine').append(_html);
    });
    $('body').on('click','.deleteMachine',function () {
        $(this).parent('div').remove();
    });
    //添加手机号
    $('.addPhone').click(function () {
        let _html = `
            <div style="margin-left: 73px;margin-top: 14px;">
                    <input type="text" style="width: 440px;" placeholder="请输入手机号">
                    <span class="iconBtn deletePhone" >-</span>
                </div>
        `;
        $('#listPhone').append(_html);
    });
    $('body').on('click','.deletePhone',function () {
        $(this).parent('div').remove();
    });
});