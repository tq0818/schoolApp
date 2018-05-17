function addCourseListener(){
    //新增弹窗
    $('.openPopup').click(function () {
        var num = $("#styleContainer").find('li').length - 1;
        if (num >= MAX_STYLE) {
            $.msg('已超过课程风采数量');
            return;
        }
        $("#hidCoverTop").val('');
        $('#hidCoverFid').val('');
        $('#hidCoverSort').val('');
        $('#coverReturn').html('');
        $('#cover').show();
    });

    //弹出层取消按钮事件
    $('.closeElePicCancel').click(function () {
        $('#cover').hide();
    });

    //弹出层确定按钮事件
    $('.closeElePicCommit').click(function () {
        var imgUrl = $("#hidCoverTop").val();
        var id = $("#hidCoverFid").val();
        if (imgUrl == null || imgUrl == '') {
            $.msg('请上传图片');
            return;
        }

        if (id == null || id == '') {
            var num = $("#styleContainer").find('li').length - 1;
            if (num >= MAX_STYLE) {
                $.msg('已超过课程风采数量');
                return;
            }
            var showImg = $('#coverReturn').html();
            var subscript = null;

            //新增风采
            var html = `
                    <li data-i="${num}">
                        <span>${showImg}</span>
                        <input type="hidden" value="${imgUrl}"  />
                        <span class="imgInfo"></span>
                        <div class="listBg">
                        <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                           <a href="javascript:void(0)" data-id=""  class="btn btn-success alterBtn btn-sm openPopup">修改</a>
                        </div>
                    </li>
           `;

            $("#styleContainer").append(html);

            $('#fileUploadInput').val('');

            $('#cover').hide();

            //添加完节点后添加监听器
            $('.deleteBtn').click(function () {
                $(this).parent('div').parent('li').remove();
            })

            $('.alterBtn').click(function () {
                //获取当前元素的li根节点
                var baseNode = $(this).parent('div').parent('li');
                //获取span中的img的src
                var url = $(baseNode).find('span').eq(0).find('img')[0].src;
                //弹出层中显示图片
                $('#coverReturn').html("<img src='" + url + "' alt=\"\" style=\"width: 100%; max-height: 260px;\">");
                //保存图片的相对路径
                $("#hidCoverTop").val($(baseNode).find('input').eq(0).val());
                //保存映射id
                $('#hidCoverFid').val($(this).attr('data-id'));
                //保存当前修改的是哪一个li，用于点击确认的时候更新数据
                $('#hidCoverSort').val($(baseNode).eq(0).attr('data-i'));
                $('#cover').show();
            });

        } else {
            //修改风采

            var i = $('#hidCoverSort').val();
            var dom = $("#styleContainer").find('li').eq(i + 1);
            for (var i in dom) {
                if ($(dom).eq(i).attr('data-i') == i) {
                    $(dom).find('span').eq(0).html($('#coverReturn').html());
                    $(dom).find('input').eq(0).val($("#hidCoverTop").val());
                    $('#cover').hide();
                }
            }

        }


    })

//监听课程人数限定监听器
    $(":radio").click(function () {
        if ($(this).val() == 1) {
            $('#limitContainer').show();
        } else {
            $('#limitContainer').hide();
        }
    })

    //
    //添加限制人数监听器，>= 0
    $('#classPersonLimit').bind('input propertychange', 'input' , function(){
        var val = $('#classPersonLimit').val();
        if(isNaN(val)){
            $('#classPersonLimit').val(val.substr(0,val.length - 1));
            return;
        }

    })


    //添加价格输入监听器
    $('#classPrice').bind('input propertychange', 'input' , function(){
        var price = $('#classPrice').val();
        if(isNaN(price)){
            $('#classPrice').val(price.substr(0,price.length - 1));
            return;
        }
        if(!priceTest.test(price)){
            $('#classPrice').val(price.substr(0,price.length - 1));
            return;
        }

        if(price > 1000000){
            $('#classPrice').val(price.substr(0,price.length - 1));
            return;
        }

    })

    // 课程名称事件监听
    $('#className').bind('input propertychange', 'input' , function(){
        var val = $('#className').val();
        //不能输入全是空格的字符串
        if(trim(val) == ''){
            $('#className').val('');
            return;
        }

        //字符串末尾连续多个空格
        if(val.length - trim(val).length > 1){
            $('#className').val(val.substr(0,val.length - 1));
            return;
        }

        if(!nameTest.test(trim(val))){
            $('#className').val(val.substr(0,val.length - 1));
        }
    })

    //课程说明事件监听
    $('textarea').bind('input propertychange', function(){
        var val = $('#classDetail').val();
        //不能输入全是空格的字符串
        if(trim(val) == ''){
            $('#classDetail').val('');
            return;
        }

        //字符串末尾连续多个空格
        if(val.length - trim(val).length > 1){
            $('#classDetail').val(val.substr(0,val.length - 1));
            return;
        }

        if(!nameTest.test(trim(val))){
            $('#classDetail').val(val.substr(0,val.length - 1));
        }
    })

    //添加课程标签
    $('.addSystemBtn').click(function () {
        var len = $('#labelContainer').find('.systemBtn').length;
        if (len >= MAX_LABEL) {
            $(this).hide();
        } else {
            var html = `
                        <span href="##" class="systemBtn">
                             <input class="systemLabel" maxlength="5" label-id="" value="">
                             <i class="icon iconfont deleteBtn deleteLabelBtn">&#xe610;</i>
                        </span>
                       
             `;
            $('#spanContainerLabel').append(html);

            if (len + 1 >= MAX_LABEL) {
                $(this).hide();
            }


            $('.deleteLabelBtn').click(function () {
                $(this).parent('span').remove();
                var len = $('#labelContainer').find('.systemBtn').length;
                if (len < MAX_LABEL) {
                    $('.addSystemBtn').show();
                }

            })

        }
    })



    $('.deleteBtn').click(function () {
        $(this).parent('div').parent('li').remove();
    })

    $('.alterBtn').click(function () {
        //获取当前元素的li根节点
        var baseNode = $(this).parent('div').parent('li');
        //获取span中的img的src
        var url = $(baseNode).find('span').eq(0).find('img')[0].src;
        //弹出层中显示图片
        $('#coverReturn').html("<img src='" + url + "' alt=\"\" style=\"width: 100%; max-height: 260px;\">");
        //保存图片的相对路径
        $("#hidCoverTop").val($(baseNode).find('input').eq(0).val());
        //保存映射id
        $('#hidCoverFid').val($(this).attr('data-id'));
        //保存当前修改的是哪一个li，用于点击确认的时候更新数据
        $('#hidCoverSort').val($(baseNode).eq(0).attr('data-i'));
        $('#cover').show();
    });

    //课程标签删除监听器
    $('.deleteLabelBtn').click(function () {
        $(this).parent('span').remove();
        var len = $('#labelContainer').find('.systemBtn').length;
        if (len < MAX_LABEL) {
            $('.addSystemBtn').show();
        }

    })

}


function  clearCourseListenner(){
    $('.openPopup').unbind();
    $('.closeElePicCancel').unbind();
    $('.closeElePicCommit').unbind();
    $('.deleteLabelBtn').unbind();
    $('.addSystemBtn').unbind();
    $('textarea').unbind();
    $('#className').unbind();
    $('#classPrice').unbind();
    $(":radio").unbind();
    $('.deleteBtn').unbind();
    $('.alterBtn').unbind();
    $('.deleteLabelBtn').unbind();
}

