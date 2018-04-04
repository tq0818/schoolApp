
$(function () {
	//初始化小学学年
	queryRiseSchoolYear();
	//选择所属省份 初始化身份
    queryRiseSchoolDict(0);
    //发送到指定用户下拉列表
    $('#userListInput').keyup(function () {
        if(Number($(this).val().length)>0){
        	var searchCondition = $(this).val();
        	console.log(searchCondition.length);
        	if(searchCondition.length != 11){
        		return;
        	}
        	$.ajax({
                url: rootPath + "/riseSchoolManage/searchUsers",
                type:"post",
                data:{"searchCondition":searchCondition},
                dataType:"json",
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success:function(data){
                	$(".loading").hide();
                    $(".loading-bg").hide();
                    if (data.flag == 1){
                        var html = '';
                        for (var i in data.dictList){
                        	html = html + '<li data-value='+data.dictList[i].mobile+'data-user='+data.dictList[i].nickName+','+data.dictList[i].mobile+' >'+data.dictList[i].nickName+','+data.dictList[i].mobile+'</li>';
                        }
                        $('.userList').html('').html(html);
                    }
                }
            });
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
            var onOff = true;
            for(var i =0 ;i<userInfoListAll.length;i++){
                if(Number(userInfo[1])==Number(userInfoListAll.eq(i).html())){
                    $.msg("该条数据已有");
                    onOff = false;
                    break;
                }
            }
            if(onOff){
                var _html = `
                    <span class="c-content">
								<label for="" class="nameTitle">`+userInfo[0]+`</label>
								<span class="userInfoListAll">`+userInfo[1]+`</span>
								<i class="icon iconfont iconDelete">&#xe610;</i>
							</span>
                    `;
                $('.userListInfo').append(_html);
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

    //点击发送通知，弹窗提示

    $('.btn-send').click(function () {
        $.confirm('是否确认发送信息给选定用户？',function (data) {
            if(data){
                console.log("点击了确定");
            }else {
                console.log("点击了取消");
            }
        })
    });


});

function queryRiseSchoolYear(){
    var step = $("#step").val();
    $.ajax({
        url:rootPath +"/riseSchoolManage/queryRiseSchoolYear",
        data:{"step":step},
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
                    html = html + "<option value=\""+data.dictList[i]+"\">"+data.dictList[i]+"</option>"
                }
                $("#stepYear").html("").html(html);
            }
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
                	var eduSchool = $("#eduSchool").val();
                	if(eduSchool == ""){
                		html = "<option value=\"\">请选择区</option>";
                	}else{
                		html = "<option value=\"\">请选择区</option>" + html;
                	}
                    $("#registStatus").html("").html(html);
                }
            }
        }
    });
}
function querySchoolName() {
	var registStatus = $("#registStatus").val();
	if(registStatus == ""){
		 var html = "<option value=\"\">请选择学校</option>";
        $("#schoolName").html("").html(html);
		return;
	}
	$.ajax({
        url:rootPath +"/riseSchoolManage/querySchoolName",
        data:{"registStatus":registStatus},
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
                html = "<option value=\"\">请选择学校</option>" + html;
                $("#schoolName").html("").html(html);
            }
        }
    });
}
