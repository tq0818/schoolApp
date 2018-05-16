$(function () {
    //    左侧active切换
    $selectSubMenus('teacherFamous ');
    //系统标签增加和删除
    $('.addSystem').click(function () {
        let systemLength = $('.systemBtn').length;
        if(systemLength<4){
            var _html =
                ` <span href="##" class="systemBtn">
                                <input class="systemLabel">
                                <i class="icon iconfont deleteBtn">&#xe610;</i>
                  </span>
            `;
            $(this).before(_html);
            if(systemLength==3){
                $(this).hide();
            }
        }
    });
    $('body').on('click','.deleteBtn',function () {
        $(this).parents('.systemBtn').remove();
        let systemBtnLength = $('.systemBtn').length;
        if(systemBtnLength<5){
            $('.addSystem').show();
        }
    });
});




