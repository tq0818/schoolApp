//小升初，学校管理
$(function () {
    //点击下架,下架完成后显示上架
    // $('.offShelf').click(function () {
    //
    // });
    //点击置顶$('.top').click(function () {
    //     if($(this).html()=='置顶'){
    //         $(this).html('取消置顶');
    //         $(this).parents('td').siblings('.offShelfState').html('已置顶');
    //     }else{
    //         $(this).html('置顶');
    //         $(this).parents('td').siblings('.offShelfState').html('未置顶');
    //     }
    // });
    //
    //点击账号管理弹窗弹窗
    $('.countManagement').click(function () {
        $('.opacityPopup').fadeIn();
        $('.countPopup').fadeIn();
        //移除学校新增保存的id
        $('#schoolBtn').find(".countPopupSave").removeAttr("id");
    });
    //点击保存或取消，隐藏弹窗
    $('.countPopupCancel').click(function () {
        $('.opacityPopup').fadeOut();
        $('.countPopup').fadeOut();

        $('.addNewSchool').fadeOut();
    });
    $('.countPopupSave').click(function () {
       /* $('.opacityPopup').fadeOut();
        $('.countPopup').fadeOut();

        $('.addNewSchool').fadeOut();*/
        if (!$("#schoolSave").attr("id")){
            //id存在则是账户管理密码保存
            updateAccount();
        }else{
            //点保存触发添加
            addRiseSchoolInfo();
        }

    });
    //点击添加学校按钮弹窗
    $('.addSchool').click(function () {
        //先清除之前的值再弹窗,相当于初始化页面
        $("#schoolName").val("");
        $("#userName").val("");
        $("#province").html("<option value=\"\">学校所在省份</option>");
        $("#city").html("<option value=\"\">学校所在市</option>");
        $("#district").html("<option value=\"\">学校所在区</option>");
        $("#schoolAddress").val("");
        $("#schoolWeb").val("");
        $("#schoolFax").val("");
        $("#busRoad").val("");
        $("#collectBaseCount").val("");

        $('.opacityPopup').fadeIn();
        $('.addNewSchool').fadeIn();
        $('#schoolBtn').find(".countPopupSave").attr("id","schoolSave");
        //执行查询的省份 2018-2-7 zj
        addQueryRiseSchoolDict(0);
    });
    //招生类型选择
    $('.enrolment').children('a').click(function () {
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
        //选择后触发筛选
        queryRiseSchoolInfo(1);
    });
    //置顶状态选择
    $('.topState').children('a').click(function () {
        $(this).addClass('btn-primary').removeClass('btn-default');
        $(this).siblings('a').addClass('btn-default').removeClass('btn-primary');
        //选择后触发筛选
        queryRiseSchoolInfo(1);
    });
    //选择所属省份 初始化身份
    queryRiseSchoolDict(0);
    //点击搜索时触发事件
    $(".searchContents").click(function () {
        queryDimRiseSchoolInfo(1);
    });
});

//新增学校请求接口 2018-2-7 zj
function addRiseSchoolInfo() {
    var schoolName = $("#schoolName").val();
    var userName = $("#userName").val();
    var enRollMent = $("#enRollMent").val();
    var province = $("#province").val();
    var city = $("#city").val();
    var district = $("#district").val();
    var schoolAddress = $("#schoolAddress").val();
    var schoolWeb = $("#schoolWeb").val();
    var schoolFax = $("#schoolFax").val();
    var busRoad = $("#busRoad").val();
    var collectBaseCount = $("#collectBaseCount").val();
    if (schoolName == null || schoolName == ''){
        $.msg("未输入学校名称");
        return ;
    }
    if (userName == null || userName == ''){
        $.msg("学校账号未输入");
        return ;
    }
    if(allStr.test(userName)){
        if (!regNum.test(userName)&&!regStr.test(userName)){
/*            //校验账号是否重复
            $.ajax({
                url:rootPath + "/riseSchoolManage/judgeAccountName",
                data:{"username":userName},
                dataType:"json",
                success:function (data) {
                    if (data.count != 0){
                        $.msg("该账号已存在!",1000);
                        $("#userName").val("");
                        return ;
                    }
                }
            });*/
        }else {
            $.msg("账号只支持字母和数字的组合",1000);
            return ;
        }
    }else{
        $.msg("账号只支持字母和数字的组合",1000);
        return ;
    }
    if (!regNum.test(userName)&&!regStr.test(userName)){

    }else {
        $.msg("账号只支持字母和数字的组合");
        return ;
    }
    if (enRollMent == null || enRollMent == ''){
        $.msg("未选择招生方式");
        return ;
    }
    if (province == null || province == '' || city == null || city == '' || district == null || district == ''
        || schoolAddress == null || schoolAddress == ''){
        $.msg("学校地址存在未录入项");
        return ;
    }
    if (collectBaseCount == null || collectBaseCount == ""){
        collectBaseCount = 0;
    }
    if(parseInt(collectBaseCount)<0 || parseInt(collectBaseCount)>10000){
        $.msg("收藏基数请输入0-10000");
        return ;
    }

    if (!confirm("是否确认添加该学校")){
        return ;
    }
    $.ajax({
        type:"POST",
        url : rootPath +"/riseSchoolManage/addRiseSchoolInfo",
        data : {"schoolName":schoolName ,
                "enrollmentType":enRollMent,
                "provinceCode":province,
                "cityCode":city,
                "district":district,
                "detailAddress":schoolAddress,
                "schoolWeb":schoolWeb,
                "schoolFax":schoolFax,
                "busRoad":busRoad,
                "username":userName,
                "baseNum":collectBaseCount
        },
        dataType:"json",
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success : function(data) {
            if (data.flag == 1){
                $(".loading").hide();
                $(".loading-bg").hide();
                $.msg(data.msg);
                $('#schoolBtn').find(".countPopupSave").removeAttr("id");
                //重新请求查询接口
                queryRiseSchoolInfo(1);
            }else {
                $.msg(data.msg);
            }
        },
        complete: function (XMLHttpRequest, textStatus) {
            $('.opacityPopup').fadeOut();
            $('.countPopup').fadeOut();
            $('.addNewSchool').fadeOut();
        }
    });
}

//查询省份，城市，区域等下拉信息 2018-2-7
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

//添加时查询的下拉省份信息
function addQueryRiseSchoolDict(areaFlag) {
    var itemType = '';
    var itemCode = '';
    if (areaFlag == 0) {
        itemType = 'PROVINCE';
    } else if (areaFlag == 1) {
        itemType = 'CITY';
        itemCode = $("#province").val();
    } else if (areaFlag == 2) {
        itemType = 'DISTRICT';
        itemCode = $("#city").val();
    }
    $.ajax({
        url: rootPath + "/riseSchoolManage/queryRiseSchoolDict",
        data: {
            "itemType": itemType,
            "itemCode": itemCode
        },
        dataType: "json",
        success: function (data) {
            //拼接下拉值
            if (data.flag == 1) {
                var html = '';
                for (var i in data.dictList) {
                    html = html + "<option value=\"" + data.dictList[i].itemCode + "\">" + data.dictList[i].itemName + "</option>"
                }
                if (areaFlag == 0) {
                    html = "<option value=\"\">学校所在省份</option>" + html;
                    $("#province").html("").html(html);
                } else if (areaFlag == 1) {
                    html = "<option value=\"\">学校所在市</option>" + html;
                    $("#city").html("").html(html);
                } else if (areaFlag == 2) {
                    html = "<option value=\"\">学校所在区</option>" + html;
                    $("#district").html("").html(html);
                }
            }
        }
    });
}

//查询学校信息
function queryRiseSchoolInfo(pageNo) {
    var enrolment = $(".enrolment .btn-primary").attr("data-value");
    var topState = $(".topState .btn-primary").attr("data-value");
    if (enrolment == 0){
        enrolment = '';
    }
    if (topState == 0){
        topState = '';
    }
    $.ajax({
        url: rootPath + "/riseSchoolManage/queryRiseSchoolInfo",
        data: {"page":pageNo,
               "pagesize":12,
                "enrollmentType":enrolment,
                "isTop":topState
        },
        beforeSend: function(){
               $('.loading').show();
               $('.loading-bg').show();
        },
        dataType: "html",
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            $(".user-list").html("").html(data);
        }
    });
    
}

//模糊搜索
function queryDimRiseSchoolInfo(pageNo) {
	var enrolment = $('.enrolment').children('a');
	var enrolmentVal;
	for(var i= 0;i<enrolment.length;i++){
		if(enrolment.eq(i).hasClass('btn-primary')){
			enrolmentVal = enrolment.eq(i).attr('data-value');
		}
	}
	var topState = $('.topState').children('a');
	var topStateVal;
	for(var i= 0;i<topState.length;i++){
		if(topState.eq(i).hasClass('btn-primary')){
			topStateVal = topState.eq(i).attr('data-value');
		}
	}
    var eduArea = $("#eduArea").val();
    var eduSchool = $("#eduSchool").val();
    var registStatus = $("#registStatus").val();
    var registMethods = $("#registMethods").val();
    var from = $(".from").val();
    var to = $(".to").val();
    var schoolShortName = $("#schoolShortName").val();
    $.ajax({
        url: rootPath + "/riseSchoolManage/queryDimRiseSchoolInfo",
        data: {"page":pageNo,
            "pagesize":12,
            "provinceCode":eduArea,
            "cityCode":eduSchool,
            "district":registStatus,
            "isShalve":registMethods,
            "startTime":from,
            "endTime":to,
            "isTop":topStateVal,
            "enrollmentType":enrolmentVal,
            "schoolName":schoolShortName
        },beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        dataType: "html",
        success: function (data) {
            $('.loading').hide();
            $('.loading-bg').hide();
            $(".user-list").html("").html(data);
        }
    });

}

//更新上下架或者置顶
function updateRiseSchool(id,isShalves,isTop) {
    $.ajax({
        type:"POST",
        url: rootPath + "/riseSchoolManage/updateRiseSchoolInfo",
        data: {
            "id":id,
            "isShalve":isShalves,
            "isTop":isTop
        },
        dataType: "json",
        success: function (data) {
            if (data.flag == 1){
                //成功之后进行刷新
                queryDimRiseSchoolInfo(1);
            }else {
                $.msg(data.msg);
            }
        }
    });
}

//更新账户密码
function updateAccount() {
    var key = $("#password").val();
    if (key == null || key == ''){
        $.msg("密码不能为空");
        return ;
    }
    $.ajax({
        url: rootPath + "/riseSchoolManage/updateUsersInfo",
        data: {
            "id":$("#accountUserName").attr("value"),
            "username":$("#accountUserName").text(),
            "password":key
        },
        dataType: "json",
        success: function (data) {
            $.msg(data.msg);
            $('.opacityPopup').fadeOut();
            $('.countPopup').fadeOut();
            $('.addNewSchool').fadeOut();
        }
    });
}

//给账户管理设置
function setUserNameAndId(userId,userName) {
    //先清空在设置
    $("#accountUserName").html("");
    $("#accountUserName").attr("value","");
    $("#accountUserName").html(userName);
    $("#accountUserName").attr("value",userId);
}

//页面跳转
function loalUrl(flag,schoolId,schoolName) {
    if (flag == 0){//基本
        window.location.href = rootPath + "/riseschoolback/essential?schoolId="+schoolId;
    }else if (flag == 1){//详情
        window.location.href = rootPath + "/riseschoolback/schoolDetails?schoolId="+schoolId;
    }else if (flag == 2){//风采
        window.location.href = rootPath + "/riseschoolback/mien?schoolId="+schoolId;
    }else if (flag == 3){//升学
        window.location.href = rootPath + "/riseschoolback/upgradeSchools?schoolId="+schoolId;
    }
//    if (flag == 0){//基本
//    	window.location.href = rootPath + "/riseschoolback/essential?schoolId="+schoolId+"&schoolName="+schoolName;
//    }else if (flag == 1){//详情
//    	window.location.href = rootPath + "/riseschoolback/schoolDetails?schoolId="+schoolId+"&schoolName="+schoolName;
//    }else if (flag == 2){//风采
//    	window.location.href = rootPath + "/riseschoolback/mien?schoolId="+schoolId+"&schoolName="+schoolName;
//    }else if (flag == 3){//升学
//    	window.location.href = rootPath + "/riseschoolback/upgradeSchools?schoolId="+schoolId+"&schoolName="+schoolName;
//    }
}

//正则判断学校账号是否是字母和数字的组合
var regNum = /^[0-9]+$/;
var regStr = /^[a-zA-Z]+$/;
var allStr = /^[a-zA-Z0-9]+$/;
function judgeAccountName() {
    var userName = $("#userName").val();
    if (userName == null || userName == ''){
        return ;
    }
    if(allStr.test(userName)){
        if (!regNum.test(userName)&&!regStr.test(userName)){
            //校验账号是否重复
            $.ajax({
                url:rootPath + "/riseSchoolManage/judgeAccountName",
                data:{"username":userName},
                dataType:"json",
                success:function (data) {
                    if (data.count != 0){
                        $.msg("该账号已存在!",1000);
                        $("#userName").val("");
                        return ;
                    }
                }
            });
        }else {
            $.msg("账号只支持字母和数字的组合",1000);
            return ;
        }
    }else{
        $.msg("账号只支持字母和数字的组合",1000);
        return ;
    }

}

//检验网址
var reg=/^([hH][tT]{2}[pP]:\/\/|[hH][tT]{2}[pP][sS]:\/\/)(([A-Za-z0-9-~]+)\.)+([A-Za-z0-9-~\/])+$/;
function judgeSchoolWeb() {
    var schoolWeb = $("#schoolWeb").val();
    if(!schoolWeb)return;
    if (schoolWeb != null || schoolWeb != ""){
        if (!reg.test(schoolWeb)){
            $.msg("请输入有效的网址!",1000);
            $("#schoolWeb").val("")
            return ;
        }
    }
}

