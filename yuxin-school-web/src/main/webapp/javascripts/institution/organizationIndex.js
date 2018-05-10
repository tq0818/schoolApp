insId = '';
$(function () {
    //初始化数据
    //initDate();
    //选中二级菜单
    $selectSubMenu('organizationIndex');
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
                console.log(data);
                if(data == null || data == ''){
                    $('.createCount').show();
                }else{
                    $('.editCount').show();
                    $("#user").html("账号："+data.userName);
                    $("#userId").val(data.userId);
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
        //$('.cureatManageUser').show();
    });

    /*$('.manageUser').click(function () {
        $('.cureatManageUser').hide();
        cureatManageUser();
    });*/

   /* $('.updateManageUser').click(function () {
        $('.editCount').hide();
        updateManageUser();
    })*/

    //添加机构弹窗
    $('.addOrganization').click(function () {
        $('.addingMechanism').show();
    });
    $('.closeMechanism').click(function () {
        $('.addingMechanism').hide();
    });

    $('.addMechanism').click(function () {
        //添加机构
        addInsInfo();
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
                <input class="systemLabel" maxlength="5">
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
        findFistCategorys2();
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
            <div>
                    <input type="text" placeholder="请输入手机号">
                    <span class="iconBtn deletePhone" >-</span>
                </div>
        `;
        $('#listPhone').append(_html);
    });
    $('body').on('click','.deletePhone',function () {
        $(this).parent('div').remove();
    });

    //选择所属省份 初始化身份
    queryRiseSchoolDict(0);
    queryRiseSchoolDict2(0)

    //初始化分类数据
    findFistCategorys();

    //初始化分类数据
    findFistCategorys2();

    //分类筛选
    $('#findFistCategorys').change(function () {
        findSecondCategorys();
    });

    //分类筛选
    $('.findFistCategorys2').change(function () {
        findSecondCategorys2();
    });

    //搜索数据
    $(".searchContents").click(function () {
        findInsDate(1);
    });
});

var curPage = 1;

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
                    $("#eduArea2").html("").html(html);
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

function findFistCategorys2() {
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
            $(".findFistCategorys2").html("").html(html);
        }
    });

}

function findSecondCategorys2() {
    var id = $(".findFistCategorys2").val();
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
            $(".findSecondCategorys2").html("").html(html);
        }
    });
}

function findInsDate(page) {
    console.log("cur"+page);
    curPage = page;
    var eduArea = $("#eduArea").val();
    var eduSchool = $("#eduSchool").val();
    var registStatus = $("#registStatus").val();
    var findFistCategorys = $("#findFistCategorys").val();
    var findSecondCategorys = $("#findSecondCategorys").val();

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
        url:rootPath+"/InsInfoBase/insData",
        type:"post",
        data:{
            "province":eduArea,
            "city":eduSchool,
            "area":registStatus,
            "oneLevelId":findFistCategorys,
            "twoLevelId":findSecondCategorys,
            "isCertified":isCertifiedVal,
            "isShelves":isShelvesVal,
            "endTime":endTime,
            "startTime":startTime,
            "name":insName,
            "page":page
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success:function(jsonData){
            if(!jsonData||jsonData.data.length==0){
                /*$('.coursePackageList').append('<tr><td colspan="4">暂无数据</td></tr>');
                return;*/
            }
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
            $.each(jsonData.data,function(i,item){
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
                if(jsonData.data.isCertified==1){
                    isCertified='已认证';
                    isCertifiedval='取消认证';
                }else{
                    isCertified='未认证';
                    isCertifiedval='认证';
                }
                    html +=
                    '<tr data-buy="false">'+
                        '<td>'+item.sort+'</td>'+
                        '<td>'+item.name +'</td>'+
                        '<td>'+item.province +'</td>'+
                        '<td>'+item.city +'</td>'+
                        '<td>'+item.area +'</td>'+
                        '<td>'+item.firstcodeName +'</td>'+
                        '<td>'+item.secondcodeName +'</td>'+
                        '<td>'+isShelves+'</td>'+
                        '<td class="authenticationReal">'+isCertified+'</td>'+
                        '<td class="slink">'+
                            '<a href="##" class="frameLower" data-id="'+item.id+'">'+isShelvesval+'</a>|'+
                            '<a href="##" class="authentication" data-id="'+item.id+'">'+isCertifiedval+'</a>|'+
                            '<a href="##" class="countManage" id="countManage" data-id="'+item.id+'">'+'账号管理'+'</a>|'+
                            '<a href="##" class="manageBtn">'+'管理'+'</a>|'+
                            '<ul class="none box" style="display: none;">'+
                                '<li><a href="">基本信息</a>'+'</li>'+
                                '<li><a href="">风采管理</a>'+'</li>'+
                                '<li><a href="">课程管理</a>'+'</li>'+
                                '<li><a href="">名师管理</a>'+'</li>'+
                                '<li><a href="">评论管理</a>'+'</li>'+
                            '</ul>'+
                        '</td>'+
                    '</tr>'
            });
            $("#tableList").html(html);

            if (jsonData.rowCount > 2) {
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
                            findInsDate(pageNo);
                        }
                    });
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

//修改上下架，认证
function authFrameLower(id,num,flag) {

    $.ajax({
        url:rootPath+"/InsInfoBase/authFrameLower",
        type:"post",
        data:{"id":id,"num":num,"flag":flag},
        success:function(jsonData){
            //findInsDate(curPage);
        }
    })
}


function addInsInfo() {
    var name = $("#name").val();
    var province = $("#eduArea2").val();
    var city = $("#eduSchool2").val();
    var area = $("#registStatus2").val();
    var address = $("#address").val();
    var userName = $("#userName").val();

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
        if(i == listPhoneChi.length-1){
            listPhone+= listPhoneChi.eq(i).children('input').eq(0).val()

            ;
        }else{
            listPhone+= listPhoneChi.eq(i).children('input').eq(0).val()+','
            ;
        }
    }

    var mobile = listMachine+','+listPhone;

    //是否是连锁机构
    var org='';
    var radios = document.getElementsByName("org");
    for (let i = 0; i < radios.length; i++) {
        if (radios[i].checked) {
            org = radios[i].value
            break;
        }
    }
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
            "isChains":org
        },
        success:function(data){
            findInsDate(1);
        }
    })

}

function cureatManageUser() {
    var manageUser = $("#manageUser").val();
    var countManage = insId;
    $.ajax({
        url:rootPath+"/InsInfoBase/cureatManageUser",
        type:"post",
        data:{
            "manageUser":manageUser,
            "countManage":countManage
        },
        success:function(data){
            findInsDate(curPage);
        }
    })


}

function updateManageUser() {
    var userId = $("#userId").val();
    var updataPwd = $("#updataPwd").val();
    var userName = $("#user").html();
    $.ajax({
        url:rootPath+"/InsInfoBase/updataUserPwd",
        type:"post",
        data:{
            "userId":userId,
            "updataPwd":updataPwd,
            "userName":userName
        },
        success:function(data){
            findInsDate(curPage);
        }
    })
}
