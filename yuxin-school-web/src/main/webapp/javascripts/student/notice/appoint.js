
$(function () {
    //发送到指定用户下拉列表
    $('#userListInput').keyup(function () {
        if(Number($(this).val().length)>0){
            $('.userList').show();
        }else{
            $('.userList').hide();
        }
    });
    //点击选择
    $('.userList').on('mouseenter','li',function () {
        $(this).addClass('active');
        $(this).siblings('li').removeClass('active');
    });
    //点击li赋值给input
    $('.userList').on('click','li',function () {
        $('#userListInput').val('');
        $('.userList').hide();
        var userInfo = $(this).attr('data-user').split(",");

        var userInfoListAll = $('.userInfoListAll');

        if(userInfoListAll.length >0 ){

            for(var i =0 ;i<userInfoListAll.length;i++){
                if(userInfo[1]==userInfoListAll.eq(i).html()){
                    $.msg("该条数据已有");
                    break;
                }else {
                    console.log(userInfoListAll.eq(i).html());
                    var _html = `
                    <span class="c-content">
								<label for="" class="nameTitle">`+userInfo[0]+`</label>
								<span class="userInfoListAll">`+userInfo[1]+`</span>
								<i class="icon iconfont iconDelete">&#xe610;</i>
							</span>
                    `;
                    $('.userListInfo').append(_html);
                }
            }

        }else {
            var _html = `
                    <span class="c-content">
								<label for="" class="nameTitle">`+userInfo[0]+`</label>
								<span class="userInfoListAll">`+userInfo[1]+`</span>
								<i class="icon iconfont iconDelete">&#xe610;</i>
							</span>
        `;
            $('.userListInfo').append(_html);
        }



    });

    // 已选用户，点击删除删除用户
    $('.userListInfo').on('click','.iconDelete',function () {
        $(this).parent('span').remove();
    });
    // 点击站内信隐藏发送模板
    $('.font-style').children('a').eq(0).click(function () {
        $('.templete').show();
    });
    $('.font-style').children('a').eq(1).click(function () {
        $('.templete').hide();
    });



});
