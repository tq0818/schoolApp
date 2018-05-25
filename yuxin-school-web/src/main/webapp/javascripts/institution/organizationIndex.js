insId = '';

$(function () {
    var twoLevelIdI = $("#twoLevelIdI").val();
    var twoLevelNameI = $("#twoLevelNameI").val();

    if(twoLevelIdI != null && twoLevelIdI != ''){
        var html ='<option value="">请选择二级分类</option>';
        html+='<option value="'+twoLevelIdI +'" selected>'+ twoLevelNameI+'</option>'
        $("#findSecondCategorys").html(html);
    }

    var isCertifiedI = $("#isCertifiedI").val();
    var isShelvesI = $("#isShelvesI").val();

    if(isCertifiedI == ''){
        var html ='';
        html+='<span>认证状态</span>';
        html+=' <a href="javascript:void(0)" class="btn btn-default btn-primary btn-mb">全部</a>\n' +
            '<a href="javascript:void(0)" class="btn btn-default btn-mb">已认证</a>\n' +
            '<a href="javascript:void(0)" class="btn btn-default btn-mb">未认证</a>'
        $("#isCertified").html(html);
    }else if(isCertifiedI == 1){
        var html ='';
        html+='<span>认证状态</span>';
        html+=' <a href="javascript:void(0)" class="btn btn-default  btn-mb">全部</a>\n' +
            '<a href="javascript:void(0)" class="btn btn-default btn-mb btn-primary">已认证</a>\n' +
            '<a href="javascript:void(0)" class="btn btn-default btn-mb">未认证</a>'
        $("#isCertified").html(html);
    }else if(isCertifiedI == 0){
        var html ='';
        html+='<span>认证状态</span>';
        html+=' <a href="javascript:void(0)" class="btn btn-default  btn-mb">全部</a>\n' +
            '<a href="javascript:void(0)" class="btn btn-default btn-mb ">已认证</a>\n' +
            '<a href="javascript:void(0)" class="btn btn-default btn-mb btn-primary">未认证</a>'
        $("#isCertified").html(html);
    }

    if(isShelvesI == ''){
        var html ='';
        html+='<span>上下架&nbsp&nbsp&nbsp</span>';
        html+='  <a href="javascript:void(0)" class="btn btn-default btn-primary btn-mb">全部</a>'+
            '<a href="javascript:void(0)" class="btn btn-default btn-mb">已上架</a>'+
            '<a href="javascript:void(0)" class="btn btn-default btn-mb">未上架</a>'
        $("#isShelves").html(html);
    }else if(isShelvesI == 1){
        var html ='';
        html+='<span>上下架&nbsp&nbsp&nbsp</span>';
        html+='  <a href="javascript:void(0)" class="btn btn-default  btn-mb">全部</a>'+
            '<a href="javascript:void(0)" class="btn btn-default btn-mb btn-primary">已上架</a>'+
            '<a href="javascript:void(0)" class="btn btn-default btn-mb">未上架</a>'
        $("#isShelves").html(html);
    }else if(isShelvesI == 0){
        var html ='';
        html+='<span>上下架&nbsp&nbsp&nbsp</span>';
        html+='  <a href="javascript:void(0)" class="btn btn-default  btn-mb">全部</a>'+
            '<a href="javascript:void(0)" class="btn btn-default btn-mb">已上架</a>'+
            '<a href="javascript:void(0)" class="btn btn-default btn-mb btn-primary">未上架</a>'
        $("#isShelves").html(html);
    }

    $("#startTime").val($("#startTimeI").val());
    $("#endTime").val($("#endTimeI").val());
    $("#insName").val($("#nameI").val());

    //初始化数据
    //initDate();
    //选中二级菜单
    $selectSubMenu('organizationIndex');
    //管理显示弹窗
    $('body').on('mouseover','.manageBtn',function () {
        $(this).siblings('ul').show();
    });
    $('body').on('mouseleave','.manageBtn',function () {
        $(this).siblings('ul').hide();
    });
    $('body').on('mouseover','.box',function () {
        $(this).show();
    });
    $('body').on('mouseleave','.box',function () {
        $(this).hide();

    });

    //认证取消认证
    $('body').on('click','.authentication',function () {
        var id = $(this).attr("data-id");
        var flag = 1;
        if($(this).html()=='认证'){
            authFrameLower(id,1,flag)
            $(this).html("取消认证");
            $(this).parents('td').siblings('.authenticationReal').html('已认证');
        }else{
            authFrameLower(id,0,flag)
            $(this).html("认证");
            $(this).parents('td').siblings('.authenticationReal').html('未认证');
        }
    });
    //上架和下架
    $('body').on('click','.frameLower',function () {
        var id = $(this).attr("data-id");
        var flag = 0;
        if($(this).html()=='下架'){
            authFrameLower(id,0,flag);
            $(this).html("上架");
            $(this).parents('td').siblings('.frameLowerReal').html('已下架');
        }else{
            authFrameLower(id,1,flag);
            $(this).html("下架");
            $(this).parents('td').siblings('.frameLowerReal').html('已上架');
        }
    });
    //点击账号管理
    $('body').on('click','.countManage',function () {
        //查询当前是否有账号
        var id = $(this).attr("data-id");
        insId = id;
        $.ajax({
            url:rootPath+"/InsInfoBase/checkUser?checkId="+id,
            type:"get",
            success:function(data){
                if(data == null || data == ''){
                    $('.createCount').show();
                }else{
                    $('.editCount').show();
                    $("#user").html("账号："+data.userName);
                    $("#userId").val(data.userId);
                    $("#insUserName").val(data.userName);
                }
            }
        })
    });

    //修改密码
    $('.updateManageUser').click(function () {
        $('.editCount').hide();
        updateManageUser();
    });
    //取消修改密码
    $('.cancelManageUser').click(function () {
        $('.editCount').hide();
        $('.cureatManageUser').hide();
    });
    //立即创建密码
    $('.immediateCreation').click(function () {
        $('.createCount').hide();
        $('.cureatManageUser').show();

    });
    //取消立即创建密码
    $('.canclImmediateCreation').click(function () {
        $('.createCount').hide();

    });
    //确认创建
    $('.manageUser').click(function () {
        $('.cureatManageUser').hide();
        cureatManageUser()
    });
    //取消确认创建
    $('.cabcelManageUser').click(function () {
        $('.cureatManageUser').hide();
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
        //清空机构名
        $("#name").val('');
        //清空机构分类
        var cat = $(".catType");
        if(cat.length>1){
            for(var i = 1;i<cat.length;i++){
                cat.eq(i).remove();
            }
        }
        $(".findFistCategorys2").val('');
        $(".findSecondCategorys2").val('');
        //清空地址
        $("#eduArea2").val('');
        $("#eduSchool2").val('');
        $("#registStatus2").val('');
        $("#address").val('');
        //清空机构账号
        $("#userName").val('');
        //清空联系电话
        var mobile = $('.mobile');
        if(mobile.length>1){
            for(var i =1;i<mobile.length;i++){
                mobile.eq(i).remove();
            }
        }
        $(".telephone").val('');
        //清空手机号
        var phone = $('.phone');
        if(phone.length>1){
            for(var i=0;i<phone.length;i++){
                phone.eq(i).remove();
            }
        }
        $(".phoneNum").val('');
        //清空系统标签
        var systemBtn = $('.systemBtn');
        for(var i = 0;i<systemBtn.length;i++){
            systemBtn.eq(i).remove();
        }

        $("#orgs").html('<span>是否属于连锁机构：</span>\n' +
            '<a href="javascript:void(0)"><input type="radio" name="org" id="isOrg" value="1" >是</a>\n' +
            '<a href="javascript:void(0)"><input type="radio" name="org" id="noOrg" value="0" checked="true">否</a>');

    });

    $('.addMechanism').click(function () {

        //手机号码判重
        let arrPhoneNum = [];
        for(let i=0;i<$('.phoneNum').length;i++){
            if($('.phoneNum').eq(i).val() != ''){
                arrPhoneNum.push($('.phoneNum').eq(i).val());
            }
        }

        //点确定拼接电话号码
        let telephone = [];
        for(let i=0;i<$('.telephone').length;i++){
            var _phone = $('.telephone').eq(i).siblings('input').val()+'-'+$('.telephone').eq(i).val();
            if(_phone != '-'){
                telephone.push(_phone);
            }
        }

        //机构分类判重
        let insCat = [];
        let onOff = true;
        for(let i=0;i<$('.findFistCategorys2').length;i++){
            var insCatNum =$('.findFistCategorys2').eq(i).val()+'-'+$('.findFistCategorys2').eq(i).siblings('select').val();
            if(insCatNum != '-'){
                insCat.push(insCatNum);
            }
            if($('.findFistCategorys2').eq(i).val()==""||$('.findFistCategorys2').eq(i).siblings('select').val()==''){
                onOff = false;
            }
        }

        var name = $("#name").val();
        name = name.split(" ").join("");
        if(name.length == 0){
            $.msg("机构名称不能为空！");
        }

        //系统标签判重
        let systemLabel = $('.systemLabel');
        let labelName = [];
        for(let i = 0;i<systemLabel.length;i++){
            if(systemLabel.eq(i).val().split(" ").join("").length == 0){
                $.msg("存在空的系统标签！");
                return;
            }
            labelName.push(systemLabel.eq(i).val());
        }

        let listMachineChi = $('#listMachine').children('div');
        for(let i=0;i<listMachineChi.length;i++){
            var quhao = listMachineChi.eq(i).children('input').eq(0).val();
            var num = listMachineChi.eq(i).children('input').eq(1).val();
            if(quhao == ""&& num!=""){
                $.msg("电话号码不完整！");
                return;
            }
            if(quhao != ""&& num==""){
                $.msg("电话号码不完整！");
                return;
            }
        }

        var province = $("#eduArea2").val();
        var city = $("#eduSchool2").val();
        var area = $("#registStatus2").val();
        var address = $("#address").val();
        address = address.split(" ").join("");
        if(name == ''){
            $.msg("机构名称不能为空！");
        }else if(!onOff){
            $.msg("机构分类不能为空！");
        }else if(province == ''|| city == ''||area == ''){
            $.msg("机构地址不能为空！");
        }else if(address ==''){
            $.msg("详细地址不能为空！");
        }else if(arrPhone(insCat,0)==1){

         if(arrPhone(telephone,1)==1){

             if(arrPhone(arrPhoneNum,2)==1){

                 if(arrPhone(labelName,3)==1){
                     //添加机构
                     $.confirm('是否确认添加该机构?',function (data) {
                         if(data){
                             addInsInfo();

                         }
                     })
                 }
             }
         }
     }

    });

    //认证状态、推荐状态、上下架按钮效果切换
    $('.changeBtn').children('a').click(function () {
        if(!$(this).hasClass('btn-primary')){
            $(this).addClass('btn-primary');
            $(this).siblings('a').removeClass('btn-primary');
        }

        findInsDate(1,0);
    });
    //系统标签增加和删除
    $('.addSystem').click(function () {
        let systemLength = $('.systemBtn').length;
        if(systemLength<4){
            var _html =
                `<span href="javascript:void(0)" class="systemBtn">
                <input class="systemLabel" maxlength="5">
                <i class="icon iconfont deleteBtn">&#xe610;</i>
            </span>`;
            $(this).before(_html);
        }
        if(systemLength>2){
            $('.addSystem').hide();
        }

    });
    $('body').on('click','.deleteBtn',function () {
        $(this).parents('.systemBtn').remove();
        let systemLength = $('.systemBtn').length;
        if(systemLength<4){
            $('.addSystem').show();

        }
    });

    var i = 2;

    //添加机构分类
    $('.addType').click(function () {
        i++;
        let _html = `
            <div style="padding-left: 80px;margin-top: 6px;" class="catType">
                <select name=""  class="findFistCategorys2">
                    <option value="" >请选择一级分类</option>
                </select>
                <select name=""  class="findSecondCategorys2">
                    <option value="">请选择二级分类</option>
                </select>
                <span class="iconBtn deleteType">-</span>
            </div>
        `;
        $('#orgType').append(_html);
        findFistCategorys2(i-2);
    });
    //删除分类
    $('body').on('click','.deleteType',function () {
        $(this).parent('div').remove();
        i--
    });
    //添加座机号
    $('.addMachine').click(function () {
        let _html = `
            <div class="mobile">
                    <input type="text" placeholder="区号" class="telephone" style="width: 30px;" onkeyup="value=value.replace(/[^\\d]/g,'')">-
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
            <div class="phone">
                    <input type="text" placeholder="请输入手机号" maxlength="11" class="phoneNum">
                    <span class="iconBtn deletePhone" >-</span>
                </div>
        `;
        $('#listPhone').append(_html);
    });
    $('body').on('click','.deletePhone',function () {
        $(this).parent('div').remove();

    });

    //判重函数
    function arrPhone(arr,index){

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
                    }else if(index == 3){
                        $.msg("存在重复系统标签！");
                    }
                    return 0;

                }
            }
            return 1;
        }
        return unique1(arr);

    }



    //选择所属省份 初始化身份
    queryRiseSchoolDict(0);
    queryRiseSchoolDict2(0)

    //初始化分类数据
    findFistCategorys();

    //初始化分类数据
    findFistCategorys2(0);

    //分类筛选
    $('#findFistCategorys').change(function () {
        findSecondCategorys(2);
        findInsDate(1,0);
    });
    $('#findSecondCategorys').change(function () {
        findInsDate(1,0);
    });

    //分类筛选

    $('body').on('change','.findFistCategorys2',function () {
        findSecondCategorys2($(this).val(),this);
    });

    //搜索数据
    $(".searchContents").click(function () {
        findInsDate(1,0);
    });

    $('body').on('click','.ingInfo',function () {
        indexParam();
    })


});

var curPage = 1;

//查询省市区
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
                    //$("#eduArea").html("").html(html);
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
            pagaI = '';
            findInsDate(1,0);
        }
    });
}

//选择区域
function queryInsData() {
    findInsDate(1,0);
}

//查询省市区
function queryRiseSchoolDict2(areaFlag) {
    var itemType = '';
    var itemCode = '';
    var eduAreaNew = $("#eduArea2").val();
    if (areaFlag == 0){
        itemType = 'PROVINCE';
    }else if (areaFlag == 1){
        itemType = 'CITY';
        itemCode = $("#eduArea2").val();
    }else if (areaFlag == 2){
        itemType = 'DISTRICT';
        itemCode = $("#eduSchool2").val();
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
                    //$("#eduArea2").html("").html(html);
                }else if (areaFlag == 1){
                    if(eduAreaNew == ""){
                        html = "<option value=\"\">请选择市</option>";
                    }else{
                        html = "<option value=\"\">请选择市</option>" + html;
                    }
                    $("#eduSchool2").html("").html(html);
                    html2 = "<option value=\"\">请选择区</option>";
                    $("#registStatus2").html("").html(html2);
                }else if (areaFlag == 2){
                    html = "<option value=\"\">请选择区</option>" + html;
                    $("#registStatus2").html("").html(html);
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
            var oneLevelId = $("#oneLevelIdI").val();
            html = "<option value=\"\">请选择一级分类</option>"
            if(data.length>0){
                for (var i in data){
                    if(oneLevelId != null && oneLevelId != ''){
                        if(data[i].id == oneLevelId){
                            html = html + "<option value=\""+data[i].id+"\" selected>"+data[i].codeName+"</option>"
                        }else{
                            html = html + "<option value=\""+data[i].id+"\">"+data[i].codeName+"</option>"
                        }
                    }else{
                        html = html + "<option value=\""+data[i].id+"\">"+data[i].codeName+"</option>"
                    }

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

//获取一级分类2
function findFistCategorys2(index) {
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
            $(".findFistCategorys2").eq(index).html(html);
        }
    });

}

//获取二级分类2
function findSecondCategorys2(catId,that){
    $.ajax({
        url:rootPath +"/institutionCategory/findSecondCategorys",
        data:{"id":catId},
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



//点击查询获取数据
function findInsDate(page,flag) {

    var pagaI = $("#pageI").val();
    curPage = page;
    if(flag != null && flag !=''){
        if(pagaI != null && pagaI !=''){
            page = pagaI;
        }
    }


    var eduArea = $("#eduArea").val();
    var eduSchool = $("#eduSchool").val();
    var registStatus = $("#registStatus").val();
    var findFistCategorys = $("#findFistCategorys").val();
    var findSecondCategorys = $("#findSecondCategorys").val();
    if(findFistCategorys == '' || findFistCategorys == null){
        findSecondCategorys == '';
    }

    var isCertifiedVal = '';
    let isCertified = $('#isCertified').children('a');
    for(let i=0;i<isCertified.length;i++){
        if(isCertified.eq(i).hasClass('btn-primary')){
            if(i==0){
                isCertifiedVal = '';
            }else if(i==1){
                isCertifiedVal = 1;
            }else{
                isCertifiedVal = 0;
            }
        }
    }

    var isShelvesVal = '';
    let isShelves = $('#isShelves').children('a');
    for(let i=0;i<isShelves.length;i++){
        if(isShelves.eq(i).hasClass('btn-primary')){
            if(i==0){
                isShelvesVal = '';
            }else if(i==1){
                isShelvesVal = 1;
            }else{
                isShelvesVal = 0;
            }
        }
    }


    var endTime = $("#endTime").val();
    var startTime = $("#startTime").val();
    var insName = $("#insName").val();
    //两个时间不为空时，则需要判断时间大小
    var from = $(".from").val();
    var to = $(".to").val();
    if (from !=null && to != null){
        if (parseInt(from.replace(/-/g,"")) > parseInt(to.replace(/-/g,""))){
            $(".from").val("");
            $(".to").val("");
            alert("左边时间不能晚于右边时间!");
            return;
        }
    }
    $.ajax({
        url:rootPath+"/InsInfoBase/insData",
        type:"post",
        data:{
            "province":eduArea,
            "city":eduSchool,
            "area":registStatus,
            "oneLevelId":findFistCategorys,
            "twoLevelId":!findFistCategorys ? findFistCategorys : findSecondCategorys,
            "isCertified":isCertifiedVal,
            "isShelves":isShelvesVal,
            "endTime":endTime,
            "startTime":startTime,
            "name":insName,
            "page":page,
            "pageSize":$("#selectCounts").val() || 10
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){

            var html = '\'<tr data-buy="true">' +
                '<th width="3%">序号</th>' +
                '<th width="12%">机构名称</th>' +
                '<th width="5%">省份</th>' +
                '<th width="5%">市</th>' +
                '<th width="5%">区</th>' +
                '<th width="5%">一级分类</th>' +
                '<th width="30%">二级分类</th>'+
                '<th width="5%">上下架状态</th>'+
                '<th width="5%">认证状态</th>'+
                '<th width="15%">操作</th>'+
                '</tr>';
            if(!jsonData||jsonData.data.length==0){
                html+='<tr >'+
                    '<td colspan="10">暂无数据</td>'+
                    '</tr>'
            }else{
                $.each(jsonData.data,function(i,item){
                    var name = item.name;
                    name=name.replace(/ /g,"&nbsp");
                    i+1;
                    var isShelves;
                    var isShelvesval;
                    if(item.isShelves==1){
                        isShelves='已上架';
                        isShelvesval ='下架'
                    }else{
                        isShelves='未上架';
                        isShelvesval ='上架';
                    }
                    var isCertified;
                    var isCertifiedval;
                    if(item.isCertified==1){
                        isCertified='已认证';
                        isCertifiedval='取消认证';
                    }else{
                        isCertified='未认证';
                        isCertifiedval='认证';
                    }
                    html +=
                        '<tr data-buy="false">'+
                        '<td>'+item.sort+'</td>'+
                        '<td>'+name +'</td>'+
                        '<td>'+item.province +'</td>'+
                        '<td>'+item.city +'</td>'+
                        '<td>'+item.area +'</td>'+
                        '<td>'+item.firstcodeName +'</td>'+
                        '<td>'+item.secondcodeName +'</td>'+
                        '<td>'+isShelves+'</td>'+
                        '<td class="authenticationReal">'+isCertified+'</td>'+
                        '<td class="slink">'+
                        '<a href="javascript:void(0)" class="frameLower" data-id="'+item.id+'">'+isShelvesval+'</a>|'+
                        '<a href="javascript:void(0)" class="authentication" data-id="'+item.id+'">'+isCertifiedval+'</a>|'+
                        '<a href="javascript:void(0)" class="countManage" id="countManage" data-id="'+item.id+'">'+'账号管理'+'</a>|'+
                        '<a href="javascript:void(0)" class="manageBtn">'+'管理'+'</a>'+
                        '<ul class="none box" style="display: none">'+
                        '<li><a href="'+rootPath+'/InsInfoBase/findInsById?id='+item.id+'" class="ingInfo">基本信息</a>'+'</li>'+
                        '<li><a href="'+rootPath+'/institutionStyle/queryInstitutionStyle?relationId='+item.id+'">风采管理</a>'+'</li>'+
                        '<li><a href="'+rootPath+'/institutionClassType/classTypeMain/'+item.id+'">课程管理</a>'+'</li>'+
                        '<li><a href="'+rootPath+'/InsInfoBase/famousTeacher/'+item.id+'">名师管理</a>'+'</li>'+
                        '<li><a href="'+rootPath+'/comment/insCommentIndex?id='+item.id+'">评论管理</a>'+'</li>'+
                        '</ul>'+
                        '</td>'+
                        '</tr>'
                });
            }

            $("#tableList").html(html);



            if (jsonData.rowCount >$("#selectCounts").val()) {
                $(".pagination").html('');
                $(".pagination").pagination(jsonData.rowCount,
                    {
                        next_text: "下一页",
                        prev_text: "上一页",
                        current_page: jsonData.pageNo,
                        link_to: "javascript:void(0)",
                        num_display_entries: 8,
                        items_per_page: jsonData.pageSize,
                        num_edge_entries: 1,
                        callback: function (page, jq) {
                            var pageNo = page + 1;
                            findInsDate(pageNo,0);
                        }
                    });
                $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
                $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:searchCount()">'+
                    ' <option value="10">10</option>'+
                    ' <option value="20">20</option>'+
                    ' <option value="30">30</option>'+
                    ' <option value="50">50</option>'+
                    ' <option value="100">100</option>'+
                    ' </select> 条   ');
                $("#selectCount").val($("#selectCounts").val());
            } else {
                $(".pagination").html('');
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    })
}

function searchCount(){
    $("#selectCounts").val($("#selectCount").val());
    findInsDate(1,0);
}

//修改上下架，认证
function authFrameLower(id,num,flag) {

    $.ajax({
        url:rootPath+"/InsInfoBase/authFrameLower",
        type:"post",
        data:{"id":id,"num":num,"flag":flag},
        success:function(data){
            findInsDate(curPage,0);
        }
    })
}


//添加机构
function addInsInfo() {
    var name = $("#name").val();
    var province = $("#eduArea2").val();
    var city = $("#eduSchool2").val();
    var area = $("#registStatus2").val();
    var address = $("#address").val();
    var userName = $("#userName").val();

    let firstCat='';
    let secondCat='';

    for(let i=0;i<$('.findFistCategorys2').length;i++){
        firstCat += $('.findFistCategorys2').eq(i).val()+',';
        secondCat += $('.findSecondCategorys2').eq(i).val()+',';
    }


    //系统标签
    let systemLabel = $('.systemLabel');
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
        var quhao = listMachineChi.eq(i).children('input').eq(0).val();
        var num = listMachineChi.eq(i).children('input').eq(1).val();
        if(null != quhao && null != num && quhao != '' && num !=''){
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

    }


    let listPhone = '';
    let listPhoneChi = $('#listPhone').children('div');
    for(let i=0;i<listPhoneChi.length;i++){
        if(listPhoneChi.eq(i).children('input').val() != '' && listPhoneChi.eq(i).children('input').val() != null){
            if(!/^09\d{8}|1[3-9]\d{9}$/.test(listPhoneChi.eq(i).children('input').eq(0).val())){
                $.msg("手机号格式不正确");
                $('.addingMechanism').show();
                return;
            }
        }

        if(i == listPhoneChi.length-1){
            listPhone+= listPhoneChi.eq(i).children('input').val();
        }else{
            listPhone+= listPhoneChi.eq(i).children('input').val()+',';
        }

    }



    var mobile ='';
    if(listMachine == '' && listPhone ==''){
        mobile = '';
    }else if(listMachine ==''){
        mobile = listPhone;
    }else if(listPhone == ''){
        mobile = listMachine;
    }else{
        mobile = listMachine+','+listPhone;
    }

    //是否是连锁机构
    var org='';
    var radios = document.getElementsByName("org");
    for (let i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            org = radios[i].value
            break;
        }
    }
    //判断机构名称是否重复
    $.ajax({
        url: rootPath + "/InsInfoBase/insCheckName",
        type: "post",
        data: {
            "name": name
        },
        success: function (data) {
            if(data == null || data == ''){
                $.msg(name+"机构已存在！");
                $('.addingMechanism').show();
            }else{
                if(userName != null && userName != ''){
                    $.ajax({
                        url:rootPath+"/register/insCheckUserName",
                        type:"post",
                        data:{
                            "userName":userName
                        },
                        success:function(data){
                            if(data =='true'){
                                $.ajax({
                                    url:rootPath+"/InsInfoBase/addIns",
                                    type:"post",
                                    data:{
                                        "name":name,
                                        "province":province,
                                        "city":city,
                                        "area":area,
                                        "address":address,
                                        "userName":userName,
                                        "sysLabel":labelName,
                                        "mobile":mobile,
                                        "isChains":org,
                                        "oneLevelId":firstCat,
                                        "twoLevelId":secondCat
                                    },
                                    success:function(data){
                                        $('.addingMechanism').hide();
                                        $.msg("添加成功");
                                        window.location.reload();

                                    }
                                })
                            }else if(data =='用户名已经被注册'){
                                $.msg('机构账号已经被注册');
                                $('.addingMechanism').show();
                                return;
                            }else if('只能以字母开头并由数字、字母或下划线组成'){
                                $.msg('机构账号只能以字母开头并由数字、字母或下划线组成');
                                $('.addingMechanism').show();
                                return;
                            }else{
                                $.msg('机构账号不正确');
                                $('.addingMechanism').show();
                                return;
                            }

                        }
                    })
                }else{
                    $.ajax({
                        url:rootPath+"/InsInfoBase/addIns",
                        type:"post",
                        data:{
                            "name":name,
                            "province":province,
                            "city":city,
                            "area":area,
                            "address":address,
                            "userName":userName,
                            "sysLabel":labelName,
                            "mobile":mobile,
                            "isChains":org,
                            "oneLevelId":firstCat,
                            "twoLevelId":secondCat
                        },
                        success:function(data){
                            $('.addingMechanism').hide();
                            $.msg("添加成功");
                            window.location.reload();
                        }
                    })
                }
            }
        }
    });
}

//创建机构用户
function cureatManageUser() {
    var manageUser = $("#manageUser").val();
    var countManage = insId;

    $.ajax({
        url:rootPath+"/register/insCheckUserName",
        type:"post",
        data:{
            "userName":manageUser
        },
        success:function(data){
            if(data =='true'){
                $.ajax({
                    url:rootPath+"/InsInfoBase/cureatManageUser",
                    type:"post",
                    data:{
                        "manageUser":manageUser,
                        "countManage":countManage
                    },
                    success:function(data){
                        findInsDate(curPage,0);
                    }
                })
            }else if(data =='用户名已经被注册'){
                $(".cureatManageUser").show();
                $.msg('机构账号已经被注册');
                $("#manageUser").val("");
            }else if(data =='用户名不能为空'){
                $(".cureatManageUser").show();
                $.msg('机构账号不能为空');
                $("#manageUser").val("");
            }else if(data =='只能以字母开头并由数字、字母或下划线组成'){
                $(".cureatManageUser").show();
                $.msg('机构账号只能以字母开头并由数字、字母或下划线组成');
                $("#manageUser").val("");
            }else{
                $.msg('机构账号不正确');
                $(".cureatManageUser").show();
                $("#manageUser").val("");
            }

        }
    })




}

//修改机构用户密码
function updateManageUser() {
    var userId = $("#userId").val();
    var updataPwd = $("#updataPwd").val();

    var reg = /^[0-9a-zA-Z]+$/
    var str = $("#updataPwd").val();
    if(!reg.test(str)){
        $.msg("密码只能为数字或字母！")
        $('.editCount').show();
        return;
    }
    if(str.length<6){
        $('.editCount').show();
        $.msg("密码长度必须大于6位！")
        return;
    }
    if(updataPwd == ''){
        $.msg("密码不能为空！");
        $('.editCount').show();
        return;
    }
    updataPwd = updataPwd.split(" ").join("");
    if(updataPwd.length == 0){
        $.msg("密码不能为空！");
        $('.editCount').show();
        return;
    }

    var userName = $("#insUserName").val();
    $.ajax({
        url:rootPath+"/InsInfoBase/updataUserPwd",
        type:"post",
        data:{
            "userId":userId,
            "updataPwd":updataPwd,
            "userName":userName
        },
        success:function(data){
            findInsDate(curPage,0);
        }
    })
}

//存放首页参数
function indexParam() {
    var eduArea = $("#eduArea").val();
    var eduSchool = $("#eduSchool").val();
    var registStatus = $("#registStatus").val();
    var findFistCategorys = $("#findFistCategorys").val();
    var findSecondCategorys = $("#findSecondCategorys").val();
    if(findFistCategorys == '' || findFistCategorys == null){
        findSecondCategorys == '';
    }

    var isCertifiedVal = '';
    let isCertified = $('#isCertified').children('a');
    for(let i=0;i<isCertified.length;i++){
        if(isCertified.eq(i).hasClass('btn-primary')){
            if(i==0){
                isCertifiedVal = '';
            }else if(i==1){
                isCertifiedVal = 1;
            }else{
                isCertifiedVal = 0;
            }
        }
    }

    var isShelvesVal = '';
    let isShelves = $('#isShelves').children('a');
    for(let i=0;i<isShelves.length;i++){
        if(isShelves.eq(i).hasClass('btn-primary')){
            if(i==0){
                isShelvesVal = '';
            }else if(i==1){
                isShelvesVal = 1;
            }else{
                isShelvesVal = 0;
            }
        }
    }


    var endTime = $("#endTime").val();
    var startTime = $("#startTime").val();
    var insName = $("#insName").val();

    $.ajax({
        url:rootPath+"/InsInfoBase/indexParam",
        type:"post",
        data:{
            "province":eduArea,
            "city":eduSchool,
            "area":registStatus,
            "oneLevelId":findFistCategorys,
            "twoLevelId":!findFistCategorys ? findFistCategorys : findSecondCategorys,
            "isCertified":isCertifiedVal,
            "isShelves":isShelvesVal,
            "endTime":endTime,
            "startTime":startTime,
            "name":insName,
            "page":curPage
        },
        success:function(data){

        }
    })

}


