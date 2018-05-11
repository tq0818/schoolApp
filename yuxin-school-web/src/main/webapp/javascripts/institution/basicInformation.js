$(function () {
    //    左侧active切换
    $selectSubMenus('essential');
    //系统标签增加和删除
    $('.addSystem').click(function () {
        let systemLength = $('.systemBtn').length;
        if(systemLength<4){
            var _html =
                `<span href="##" class="systemBtn">
                <span class="systemLabel">我的名字没</span>
                <i class="icon iconfont deleteBtn">&#xe610;</i>
            </span>`;
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

    //自定义标签增加和删除
    $('.customLabelBtn').click(function () {
        let customLabelLength = $('.customLabel').length;
        if(customLabelLength<5){
            var _html =
                `  <span href="##" class="customLabel">
                            <span class="systemLabel">我的名字没</span>
                            <i class="icon iconfont deleteCustomLabel">&#xe610;</i>
                    </span>
            </span>`;
            $(this).before(_html);
            if(customLabelLength==4){
                $(this).hide();
            }
        }
    });
    $('body').on('click','.deleteCustomLabel',function () {
        $(this).parents('.customLabel').remove();
        let systemLength = $('.customLabel').length;
        if(systemLength<5){
            $('.customLabelBtn').show();
        }
    });

    //特色服务增加和删除
    $('.specialServiceBtn').click(function () {
        let specialServiceLength = $('.specialService').length;
        if(specialServiceLength<10){
            var _html =
                `   <span href="##" class="specialService">
                                <img src="../../../images/institution/1.jpg" alt="" style="width: 40px;height: 40px;">
                                <span class="systemLabel">你的名字么</span>
                                <i class="icon iconfont deletespecialService">&#xe610;</i>
                    </span>
                `;
            $(this).before(_html);
            if(specialServiceLength==9){
                $(this).hide();
            }
        }
    });
    $('body').on('click','.deletespecialService',function () {
        $(this).parents('.specialService').remove();
        let specialServiceLength = $('.specialService').length;
        if(specialServiceLength<10){
            $('.specialServiceBtn').show();
        }
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
    //点击特色图片出下面图片列表
    $('.iconPic').click(function () {

        findSpecialServiceImg();
        $('.iconList').show();
    });
    //点击关闭图片列表
    $('.closeIconList').click(function () {
        $('.iconList').hide();
    });

    //选择所属省份 初始化身份
    queryRiseSchoolDict(0);

    //获取一级分类
    findFistCategorys();

    //分类筛选
    $('#findFistCategorys').change(function () {
        findSecondCategorys();
    });

});

function queryRiseSchoolDict(areaFlag) {
    var itemType = '';
    var itemCode = '';
    var eduAreaNew = $("#eduArea").val();
    if (areaFlag == 0){
        itemType = 'PROVINCE';
    }else if (areaFlag == 1){
        itemType = 'CITY';
        itemCode = $("#eduArea").val();
    }else if (areaFlag == 2){
        itemType = 'DISTRICT';
        itemCode = $("#eduSchool").val();
    }
    $.ajax({
        url:rootPath +"/riseSchoolManage/queryRiseSchoolDict",
        data:{"itemType":itemType,
            "itemCode":itemCode},
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function (data) {
            $(".loading").hide();
            $(".loading-bg").hide();
            //拼接下拉值
            if (data.flag == 1){
                var html = '';
                for (var i in data.dictList){
                    html = html + "<option value=\""+data.dictList[i].itemCode+"\">"+data.dictList[i].itemName+"</option>"
                }
                if (areaFlag == 0){
                    html = "<option value=\"\">请选择省份</option>" + html;
                    $("#eduArea").html("").html(html);
                }else if (areaFlag == 1){
                    if(eduAreaNew == ""){
                        html = "<option value=\"\">请选择市</option>";
                    }else{
                        html = "<option value=\"\">请选择市</option>" + html;
                    }
                    $("#eduSchool").html("").html(html);
                    html2 = "<option value=\"\">请选择区</option>";
                    $("#registStatus").html("").html(html2);
                }else if (areaFlag == 2){
                    html = "<option value=\"\">请选择区</option>" + html;
                    $("#registStatus").html("").html(html);
                }
            }
        }
    });
}

//获取一级分类
function findFistCategorys() {
    $.ajax({
        url:rootPath +"/institutionCategory/findFistCategorys",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },

        success:function (data) {
            var html = '';
            html = "<option value=\"\">请选择一级分类</option>"
            if(data.length>0){
                for (var i in data){
                    html = html + "<option value=\""+data[i].id+"\">"+data[i].codeName+"</option>"
                }
            }
            $("#findFistCategorys").html("").html(html);
        }
    });

}

//获取二级分类
function findSecondCategorys() {
    var id = $("#findFistCategorys").val();
    $.ajax({
        url:rootPath +"/institutionCategory/findSecondCategorys",
        data:{"id":id},
        type:"post",

        success:function (data) {
            var html = '';
            html = "<option value=\"\">请选择二级分类</option>"
            if(data.length>0){
                for (var i in data){
                    html = html + "<option value=\""+data[i].id+"\">"+data[i].codeName+"</option>"
                }
            }
            $("#findSecondCategorys").html("").html(html);
        }
    });
}

var page = 1;
function findSpecialServiceImg(page) {
    $.ajax({
        url:rootPath +"/InsInfoBase/findSpecialServiceImg",
        type:"post",
        data:{"page":page},
        success:function (data) {
            var html = '';
            /*for(var i in data.){

            }*/

        }
    });

}



