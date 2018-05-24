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
                    id: $(styleLis[i]).find('div').eq(0).find('a').eq(1).attr('data-id'),
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
        for(var i = 0;i<labelSpans.length;i++){

            labels.push({
                id:$(labelSpans[i]).find('.systemLabel').eq(0).attr('label-id'),
                name:$(labelSpans[i]).find('.systemLabel').eq(0).val()
            })
        }


        //课程价格
        var price = $('#classPrice').val();
        //限制报名人数
        var limit = $('input:radio[name="classLimitNum"]:checked').val();
        console.log('limit = '+limit);
       // return;
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
                insId:$("#insId").val(),
                id:$('#underLineId').val(),
                face:face,
                style:JSON.stringify(classStyles),
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
                $.msg(json.msg);
                if(json.status == 1){

                    $('.closeMechanismCommit').unbind();

                    $.msg(json.msg,10,function(){
                        window.location.href = rootPath + "/institutionClassType/classTypeMain/"+$("#insId").val()
                    });
                }else{
                    $.msg(json.msg);
                }
                console.log(json);
            }
        });

    })

    addCourseListener();

})

/*function getLimit() {
    var val=$('input:radio[name="classLimitNum"]:checked').val();
   console.log(val);
}*/

//获取线下课程信息
function getUnderLineClassInfo() {
    //添加一些必要的事件监听器



    var id = $("#underLineId").val();
    if (id <= 0) {
        $(".h5").html("新增线下课程");
    } else {
        $(".h5").html("线下课程信息");

        $.post(rootPath + "/institutionClassType/underLineClassInfo",{
            id:$("#underLineId").val()
        },function(json){
            console.log(json);

            if(json.status == 1){
                var data = json.data;


                //课程封面
                $('#hidTop').val(data.face);
                if(data.face != null && data.face != ''){
                    $('#imgTop').html("<img src='" + data.fullFace + "'  alt=\"\" style=\"width: 150px;height: 100px;margin-left: 15px;\">");
                }else{
                    $('#imgTop').html('');
                }

                //课程风采
                var styleHtml = `
                     <li class="addImg mienShow openPopup" id="">
                            <i class="icon iconfont"></i>
                     </li>
                `;
                for(var i in data.styles){
                    styleHtml += `
                            <li data-i="${i}">
                                <span class="imgSpan"><img src="${data.styles[i].url}" alt="" style="width: 100%; max-height: 260px;"></span>
                                <input type="hidden" value="${data.styles[i].path}"  />
                                <span class="imgInfo"></span>
                                <div class="listBg">
                                <a href="javascript:void(0)" class="btn btn-warning btn-sm deleteBtn">删除</a>
                                   <a href="javascript:void(0)" data-id="${data.styles[i].id}"  class="btn btn-success alterBtn btn-sm openPopup">修改</a>
                                </div>
                            </li>
                    `;
                }


                $("#styleContainer").html(styleHtml);


                //课程名称
                $("#className").val(data.name);
                //课程描述
                $("#classSummary").val(data.summary);
                //课程标签
                var labelHtml = "";
                for(var i in data.labels){
                    labelHtml += `
                         <span href="##" class="systemBtn">
                             <input class="systemLabel" maxlength="5" label-id="${data.labels[i].id}" value="${data.labels[i].name}">
                             <i class="icon iconfont deleteBtn deleteLabelBtn">&#xe610;</i>
                        </span>
                    `;
                }
                $("#spanContainerLabel").html(labelHtml);

                if(data.labels.length >= MAX_LABEL){
                    $('.addSystemBtn').hide();
                }

                //课程价格
                $('#classPrice').val(data.price);
                //限制报名人数
                if(data.limit == 0){
                    $("#classLimitNumYes").removeAttr('checked');
                    $("#classLimitNumNo").attr("checked","checked");
                    $("#limitContainer").hide();
                }else{
                    $("#classLimitNumYes").attr("checked","checked");
                    $("#classLimitNumNo").removeAttr('checked');
                    $("#limitContainer").show();
                }
                // return;
                //限制预约人数  limit = 1有效
                $('#classPersonLimit').val(data.limitNum == null ? '' : data.limitNum);
                //课程详情
                $('#classDetail').val(data.detail);

                clearCourseListenner(); //清除监听器

                addCourseListener();    //重新添加监听器

            }


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




function flushLiDataI(){
    var list = $("#styleContainer").find('li');
    for(var i = 1;i<list.length;i++){
       $(list[i]).attr('data-i',(i-1));
    }
}
