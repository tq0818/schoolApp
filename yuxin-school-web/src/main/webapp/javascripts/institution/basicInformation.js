$(function () {
    //    左侧active切换
    $selectSubMenus('essential');
    //系统标签增加和删除
    $('.addSystem').click(function () {
        let systemLength = $('.systemBtn').length;
        if(systemLength<4){
            var _html =
                `<span href="##" class="systemBtn">
                <input class="systemLabel sysLabel" maxlength="5"/>
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
                            <input class="systemLabel cusLabel" maxlength="5"/>
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
                                <img src="../../../images/institution/1.jpg" alt="" style="width: 40px;height: 40px;"  class="iconPic">
                                <input class="systemLabel iconPicName" maxlength="5"></input>
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


    /*//添加机构分类
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
    });*/

    var i = 2;
    //添加机构分类
    $('.addType').click(function () {
        i++;
        let _html = `
            <div style="padding-left: 80px;margin-top: 6px;">
                <select name=""  class="findFistCategorys">
                    <option value="" >请选择一级分类</option>
                </select>
                <select name=""  class="findSecondCategorys">
                    <option value="">请选择二级分类</option>
                </select>
                <span class="iconBtn deleteType">-</span>
            </div>
        `;
        $('#orgType').append(_html);
        findFistCategorys(i-1);
    });
    //删除分类
    $('body').on('click','.deleteType',function () {
        $(this).parent('div').remove();
        i--
    });
    //添加座机号
    $('.addMachine').click(function () {
        let _html = `
            <div>
                    <input type="text" placeholder="区号" style="width: 30px;" onkeyup="value=value.replace(/[^\\d]/g,'')">-
                    <input type="text" placeholder="请输入座机号" class="telephone" onkeyup="value=value.replace(/[^\\d]/g,'')">
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
            <div style="margin-top: 5px;">
                    <input type="text" style="width: 440px;" maxlength="11"  class="phoneNum" placeholder="请输入手机号" onkeyup="value=value.replace(/[^\\d]/g,'')">
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
    $('body').on('click','.iconPic',function () {

        findSpecialServiceImg(1);
        $('.iconList').show();
    });
    //点击关闭图片列表
    $('.closeIconList').click(function () {
        $('.iconList').hide();
    });

    //选择所属省份 初始化身份
    queryRiseSchoolDict(0);


    $('body').on('change','.findFistCategorys',function () {
        findSecondCategorys($(this).val(),this);
    });
    //分类筛选
    $('.findFistCategorys').change(function () {
        findSecondCategorys();
    });


    //保存修改的内容
    $('.updateIns').click(function () {
        //判断机构名称是否为空
        var insName = $("#insName").val();
        if(insName == '' || insName == null){
            $.msg("机构名称不能为空！");
            return;
        }

        //判断机构分类是否为空
        let insCat = [];
        let onOff = true;
        for(let i=0;i<$('.findFistCategorys').length;i++){
            var insCatNum =$('.findFistCategorys').eq(i).val()+'-'+$('.findFistCategorys').eq(i).siblings('select').val();
            if(insCatNum != '-'){
                insCat.push(insCatNum);
            }
            if($('.findFistCategorys').eq(i).val()==""||$('.findFistCategorys').eq(i).siblings('select').val()==''){
                onOff = false;
            }
        }
        if(!onOff){
            $.msg("机构分类不能为空！");
            return;
        }

        //判断机构地址是否为空
        var province = $("#eduArea").val();
        var city = $("#eduSchool").val();
        var area = $("#registStatus").val();
        var address = $("#address").val();
        if(province == ''|| city == ''||area == ''|| address ==''){
            $.msg("机构地址不能为空！");
            return;
        }
        //手机号码判重
        let arrPhoneNum = [];
        for(let i=0;i<$('.phoneNum').length;i++){
            if($('.phoneNum').eq(i).val() != ''){
                arrPhoneNum.push($('.phoneNum').eq(i).val());
            }
        }

        //拼接电话号码
        let telephone = [];
        for(let i=0;i<$('.telephone').length;i++){
            var _phone = $('.telephone').eq(i).siblings('input').val()+'-'+$('.telephone').eq(i).val();
            if(_phone != '-'){
                telephone.push(_phone);
            }
        }

        if(checkData(insCat,0)==1){

            if(checkData(telephone,1)==1){

                if(checkData(arrPhoneNum,2)==1){
                    //添加机构
                    updataIns();
                }
            }
        }



    })

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
                    var eduArea = $("#eduAreaCode").val();
                    if(data.dictList[i].itemCode == eduArea){
                        html = html + "<option value=\""+data.dictList[i].itemCode+"\" selected>"+data.dictList[i].itemName+"</option>"
                    }else{
                        html = html + "<option value=\""+data.dictList[i].itemCode+"\">"+data.dictList[i].itemName+"</option>"
                    }


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
function findFistCategorys(index) {
    $.ajax({
        url:rootPath +"/institutionCategory/findFistCategorys",
        success:function (data) {
            var html = '';
            html = "<option value=\"\">请选择一级分类</option>"
            if(data.length>0){
                for (var i in data){
                    html = html + "<option value=\""+data[i].id+"\">"+data[i].codeName+"</option>"
                }
            }
            $(".findFistCategorys").eq(index).html(html);
        }
    });

}

//获取二级分类
function findSecondCategorys(id,that) {
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
            $(that).siblings("select").html("").html(html);
        }
    });
}


function findSpecialServiceImg(page) {
console.log(page);
    $.ajax({
        url:rootPath +"/InsInfoBase/findSpecialServiceImg",
        type:"post",
        data:{"page":page},
        success:function (data) {
            var imgS = data.data;
            if(imgS.length == 0){

                return;
            }

            var html = '';
            html +='<a href="##" class="uploadImg">+<input type="file"></a>';
            for(var i in imgS){
                html +='<img src="'+ imgS[i].imgUrl +'" alt="" class="iconListImg" >'
            }
            $(".imgDiv").html(html);

            if (data.rowCount > 2) {
                $(".pagination").html('');
                $(".pagination").pagination(data.rowCount,
                    {
                        next_text: "下一页",
                        prev_text: "上一页",
                        current_page: data.pageNo,
                        link_to: "javascript:void(0)",
                        num_display_entries: 8,
                        items_per_page: data.pageSize,
                        num_edge_entries: 1,
                        callback: function (page, jq) {
                            var pageNo = page + 1;
                            findSpecialServiceImg(pageNo);
                        }
                    });
            } else {
                $(".pagination").html('');
            }
        }
    });

}

//判重函数
function checkData(arr,index){

    // 最简单数组去重法
    function unique1(array){
        var n = []; //一个新的临时数组
        //遍历当前数组
        for(var i = 0; i < array.length; i++){
            //如果当前数组的第i已经保存进了临时数组，那么跳过，
            //否则把当前项push到临时数组里面
            if (n.indexOf(array[i]) == -1){
                n.push(array[i]);
            }else {
                if(index==0){
                    $.msg("机构分类重复！");
                }else if(index==1){
                    $.msg("电话号码重复！");
                }else if(index==2){
                    $.msg("手机号重复！");

                }
                return 0;

            }
        }
        return 1;
    }
    return unique1(arr);

}

//修改机构信息
function updataIns() {
    var insName = $("#insName").val();
    var province = $("#eduArea").val();
    var city = $("#eduSchool").val();
    var area = $("#registStatus").val();
    var address = $("#address").val();

    let firstCat='';
    let secondCat='';

    for(let i=0;i<$('.findFistCategorys').length;i++){
        firstCat += $('.findFistCategorys').eq(i).val()+',';
        secondCat += $('.findSecondCategorys').eq(i).val()+',';
    }

    //系统标签
    let systemLabel = $('.sysLabel');
    let labelName = '';
    for(let i = 0;i<systemLabel.length;i++){
        if(i == systemLabel.length-1){
            labelName+= systemLabel.eq(i).val()+',';
        }else{
            labelName+= systemLabel.eq(i).val()+',';
        }
    }

    //电话号码
    let listMachine = '';
    let listMachineChi = $('#listMachine').children('div');
    for(let i=0;i<listMachineChi.length;i++){
        if(i == listMachineChi.length-1){
            listMachine+= listMachineChi.eq(i).children('input').eq(0).val()+'-'+
                listMachineChi.eq(i).children('input').eq(1).val()
            ;
        }else{
            listMachine+= listMachineChi.eq(i).children('input').eq(0).val()+'-'+
                listMachineChi.eq(i).children('input').eq(1).val()+','
            ;
        }
    }

    //手机号码
    let listPhone = '';
    let listPhoneChi = $('#listPhone').children('div');
    for(let i=0;i<listPhoneChi.length;i++){
        if(listPhoneChi.eq(i).children('input').val() != '' && listPhoneChi.eq(i).children('input').val() != null){
            if(!/^09\d{8}|1[3-9]\d{9}$/.test(listPhoneChi.eq(i).children('input').eq(0).val())){
                $.msg("手机号格式不正确");
                return;
            }
        }

        if(i == listPhoneChi.length-1){
            listPhone+= listPhoneChi.eq(i).children('input').val();
        }else{
            listPhone+= listPhoneChi.eq(i).children('input').val()+',';
        }

    }

    var mobile = listMachine+','+listPhone;

    //自定义标签
    let cusLabel = $('.cusLabel');
    let cusLabelName = '';
    for(let i = 0;i<cusLabel.length;i++){
        if(i == cusLabel.length-1){
            cusLabelName+= cusLabel.eq(i).val()+',';
        }else{
            cusLabelName+= cusLabel.eq(i).val()+',';
        }
    }

    //特色服务
    let imgUrl='';
    let specialName='';


    for(let i=0;i<$('.iconPic').length;i++){

        imgUrl += $('.iconPic').eq(i).attr("src")+',';
        specialName += $('.iconPicName').eq(i).val()+',';
    }
    var insId = $("#insId").val();
    var reservService = $("#reservService").val();

    console.log(province,city,area,address,labelName,mobile,firstCat,secondCat,cusLabelName,specialName,imgUrl)

    $.ajax({
        url:rootPath+"/InsInfoBase/updateIns",
        type:"post",
        data:{
            "id":insId,
            "name":insName,
            "province":province,
            "city":city,
            "area":area,
            "address":address,
            "sysLabel":labelName,
            "mobile":mobile,
            "oneLevelId":firstCat,
            "twoLevelId":secondCat,

            "customLabel":cusLabelName,
            "specialService":specialName,
            "imgUrl":imgUrl,
            "reservService":reservService

        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(data){
            if(data == '200'){
                $.msg("保存成功")
            }else{
                $.msg("保存失败")
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })



}



