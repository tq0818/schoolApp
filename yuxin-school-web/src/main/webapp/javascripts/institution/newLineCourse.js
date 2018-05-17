var MAX_STYLE = 7;
var MAX_LABEL = 5;

var nameTest = /^[\u4e00-\u9fa5a-zA-Z\d,\s《》\-`]+$/;
var priceTest = /^-?\d+\.?\d{0,2}$/;

//去掉字符串两端的空格
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}


$(function () {

    //    左侧active切换
    $selectSubMenus('course');
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

    getUnderLineClassInfo();

    //点击提交按钮
    $('.closeMechanismCommit').click(function () {
        //课程封面
        var face = $('#hidTop').val();
        //课程风采
        var classStyles = new Array();
        var styleLis = $("#styleContainer").find('li');
        if (styleLis.length > 1) {
            for (var i = 1; i < styleLis.length; i++) {
                classStyles.push({
                    id: $(styleLis[i]).attr('data-id'),
                    path: $(styleLis[i]).find('input').eq(0).val()
                })
            }
        }

        //课程名称
        var className = $("#className").val();
        //课程描述
        var summary = $("#classSummary").val();
        //课程标签
        var labels = new Array();
        var labelSpans = $("#spanContainerLabel").find('.systemBtn');
        for (var i in labelSpans) {
            labels.push({
                id: $(labelSpans[i]).attr('label-id'),
                name: $(labelSpans[i]).find('input').eq(0).val()
            })
        }
        //课程价格
        var price = $('#classPrice').val();
        //限制报名人数
        var limit = getLimit();
        //限制预约人数  limit = 1有效
        var limitNum = $('#classPersonLimit').val();
        //课程详情
        var detail = $('#classDetail').val();

        //参数检查
        //课程封面不检查
        //课程风采不检查
        //课程名称检查
        if(trim(className) == ''){
            $.msg('请填写课程名');
            return ;
        }

        //描述可为空
        if(trim(summary) != ''){
            if(!nameTest.test(summary)){
                $.msg('描述信息不能含有特殊字符');
                return;
            }
        }

        //验证价格
        if(trim(price) == ''){
            price = '0.00';
        }

        //验证限制人数
        if(limit == 1 && limitNum == 0){
            $.msg('限定预约人数必须大于0');
            return;
        }




        $.ajax({
            url: rootPath+'/institutionClassType/editUnderLineClass',
            data: {
                id:$('#underLineId').val(),
                face:face,
                style:JSON.stringify(styleLis),
                name:className,
                summary:summary,
                label:JSON.stringify(labels),
                price:price,
                limit:limit,
                limitNum:limitNum,
                detail:detail
            },
            type: 'post',
            beforeSend: function () {
                $(".loading").show();
                $(".loading-bg").show();
            },
            complete:function(){
                $(".loading").hide();
                $(".loading-bg").hide();
            },
            success: function (json) {
                console.log(json);
            }
        });




    })


})

function getLimit() {
    $("input[name='classLimitNum']").each(
        function () {
            if ($(this).get(0).checked) {
                return $(this).val();
            }
        })
}

//获取线下课程信息
function getUnderLineClassInfo() {
    //添加一些必要的事件监听器
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


    var id = $("#underLineId").val();
    if (id <= 0) {
        $(".h5").html("新增线下课程");
    } else {
        $(".h5").html("线下课程信息");

        $.post(rootPath + "/institutionClassType/underLineClassInfo",{
            id:$("#underLineId").val()
        },function(json){
            console.log(json);
        })

    }
}


//课程封面上传图片
function fileChange() {
    $.ajax({
        url: rootPath + '/institutionClassType/uploadImgs',　　　　　　　　　　//上传地址
        type: 'POST',
        cache: false,
        data: new FormData($('#uploadForm')[0]),　　　　　　　　　　　　　//表单数据
        processData: false,
        contentType: false,
        success: function (json) {
            if (json.status == 1) {
                $('#imgTop').html("<img src='" + json.url + "'  alt=\"\" style=\"width: 150px;height: 100px;margin-left: 15px;\">");
                //  $('#imgTop').html("<img src='../../../images/institution/1.jpg'  alt=\"\" style=\"width: 150px;height: 100px;margin-left: 15px;\">");
                $('#hidTop').val(json.picPath);
            } else if (json.status == 0) {
                return;
            } else {
                $.msg(json.msg);
            }


        }
    });

}

//弹出层上传图片
function fileChangeCover() {
    $.ajax({
        url: rootPath + '/institutionClassType/uploadImgs',　　　　　　　　　　//上传地址
        type: 'POST',
        cache: false,
        data: new FormData($('#uploadFormCover')[0]),　　　　　　　　　　　　　//表单数据
        processData: false,
        contentType: false,
        success: function (json) {
            if (json.status == 1) {
                $('#coverReturn').html("<img src='" + json.url + "'  alt=\"\" style=\"width: 100%; max-height: 260px;\">");
                $('#hidCoverTop').val(json.picPath);
            } else if (json.status == 0) {
                return;
            } else {
                $.msg(json.msg);
            }

        }
    });
}


