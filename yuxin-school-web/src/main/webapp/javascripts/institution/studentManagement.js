$(function () {
    //选中二级菜单
    $selectSubMenu('student');
    //添加备注弹窗  //点击备注，弹出弹窗

    $('body').on('click','.closeNote',function () {
        $("#not").val('');
    })
    $('body').on('click','.addRemarks',function () {
        var note = $(this).attr("data-note");
        if(note != 'null' && note !=''){
            $("#not").val(note);
        }


        var id = $(this).attr("data-id");
        $("#addNote").attr("data-id",id)
        $('.remarks').show();
    })

    $('body').on('click','.addNot',function () {
        updateReServApplyNot($(this).attr("data-id"));
    })

    $('.remarksBtn').children('a').click(function () {
        $('.remarks').hide();
    });
    //tab切换
    $('.changeBtn').children('a').click(function () {
        $(this).addClass('btn-primary');
        $(this).siblings('a').removeClass('btn-primary');
        initReServApplyList(1);
    });

    initReServApplyList(1);

    //搜索
    $('.search').click(function () {
        initReServApplyList(1);
    });

    $('body').on('click','.changeStatus',function () {
        var id = $(this).attr("data-id");
        var status = $(this).attr("data-status");
        updateReServApply(id,status);
    })

    $(".exportStudent").on(
        'click',
        function () {
            if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                $.msg("没有数据可以导出");
            } else {
                var statu ='';
                let status = $('#status').children('a');
                for(let i=0;i<status.length;i++){
                    if(status.eq(i).hasClass('btn-primary')){
                        if(i==0){
                            statu = '';
                        }else if(i==1){
                            statu = 1;
                        }else{
                            statu = 0;
                        }
                    }
                }
                //预约机构
                var ins = $("#reServApplyInsId").val();
                var insId = $("#insId").val();
                if(insId != null && insId != ''){
                    ins = insId;
                }
                //预约课程
                var insClass = $("#reServApplyClass").val();


                //两个时间不为空时，则需要判断时间大小
                var from = $(".from").val();
                var to = $(".to").val();
                if (from !=null && to != null){
                    if (parseInt(from.replace(/-/g,"")) > parseInt(to.replace(/-/g,""))){
                        $(".from").val("");
                        $(".to").val("");
                        $.msg("左边时间不能晚于右边时间!");
                        return;
                    }
                }

                $("#statu").val(statu);
                $("#ins").val(ins);
                $("#insClass").val(insClass);

                $("#searchForm").attr("action",
                    rootPath + "/InsStudent/exportStudent")
                    .submit();
            }

        });

});

function searchCount(){
    $("#selectCounts").val($("#selectCount").val());
    initReServApplyList(1);
}

function findReServApplyListByClassId() {
    initReServApplyList(1);
}

var currtPage = '';
//查找预约课程
function findReServApplyClassByInsId() {
    var insId = $("#reServApplyInsId").val();
    $.ajax({
        type:"POST",
        url : rootPath +"/InsStudent/findReServApplyClassByInsId",
        data : {"insId":insId},
        async: false,
        dataType:"json",
        success : function(data) {
            var html ='<option value="">请选择课程</option>';

            $.each(data, function(i, item){
                html+='<option value="'+ item.id+'">'+item.name+'</option>';
            })

            $('#reServApplyClass').html(html);
        }
    });

    if(!insId){
        $('#reServApplyClass').html('<option value="">请选择课程</option>');
    }
    initReServApplyList(1);
}

//初始化预约列表
function initReServApplyList(page) {

    currtPage = page;
    //处理状态
    var statu ='';
    let status = $('#status').children('a');
    for(let i=0;i<status.length;i++){
        if(status.eq(i).hasClass('btn-primary')){
            if(i==0){
                statu = '';
            }else if(i==1){
                statu = 1;
            }else{
                statu = 0;
            }
        }
    }
    //预约机构
    var ins = $("#reServApplyInsId").val();
    var insId = $("#insId").val();
    if(insId != null && insId != ''){
        ins = insId;
    }
    //预约课程
    var insClass = $("#reServApplyClass").val();
    var startTime = $("#startTime").val();
    var endTime = $("#endTime").val();
    var mobile = $("#mobile").val();


    //两个时间不为空时，则需要判断时间大小
    var from = $(".from").val();
    var to = $(".to").val();
    if (from !=null && to != null){
        if (parseInt(from.replace(/-/g,"")) > parseInt(to.replace(/-/g,""))){
            $(".from").val("");
            $(".to").val("");
            $.msg("左边时间不能晚于右边时间!");
            return;
        }
    }


    $.ajax({
        type:"POST",
        url : rootPath +"/InsStudent/findReServApplyList",
        data : {
            "dealStatus":statu,
            "insId":ins,
            "insClassId":insClass,
            "mobile":mobile,
            "startTime":startTime,
            "endTime":endTime,
            "page":page,
            "pageSize":$("#selectCounts").val() || 10
        },
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success : function(jsonData) {
            var html ='<tr data-buy="true">' +
                            '<th width="3%">序号</th>' +
                            '<th width="5%">手机号</th>' +
                            '<th width="5%">预约机构</th>' +
                            '<th width="5%">预约课程</th>' +
                            '<th width="5%">课程价格(元)</th>' +
                            '<th width="5%">提交时间</th>' +
                            '<th width="5%">处理状态</th>' +
                            '<th width="5%">备注</th>' +
                            '<th width="5%">操作</th>' +
                        '</tr>';

            if(!jsonData||jsonData.data.length==0){
                html+='<tr >'+
                    '<td colspan="9">暂无数据</td>'+
                    '</tr>'
            }else{
                $.each(jsonData.data, function(i, item){
                    var dealStatus ='';
                    if(item.dealStatus == 0){
                        dealStatus = '未处理'
                    }else{
                        dealStatus = '已处理'
                    }
                    var note ='';
                    if(item.note == null || item.note == ''){
                        note ='';
                    }else{
                        note = item.note;
                        if(note.length>30){
                            note = note.substring(0,30);
                            note = note+"...";
                        }
                    }


                    var className = item.className;
                    var price = item.price;
                    if(null == className || className == ''){
                        className = '-';
                    }
                    if(price == null || price == ''){
                        price = '-';
                    }

                    html+='<tr>'+
                            '<td>'+item.sort+'</td>'+
                            '<td>'+item.mobile+'</td>'+
                            '<td>'+item.insName+'</td>'+
                            '<td>'+className+'</td>'+
                            '<td>'+price+'</td>'+
                            '<td>'+item.time+'</td>'+
                            '<td>'+dealStatus+'</td>'+
                            '<td class="note" title="'+(!note?note:item.note)+'">'+note+'</td>'+
                            '<td>'+
                                '<a href="javascript:void(0)" class="changeStatus" data-id="'+item.id+'" data-status="'+item.dealStatus+'">切换状态</a>|'+
                                '<a href="javascript:void(0)" class="addRemarks" data-id="'+item.id+'" data-note="'+item.note+'">添加备注</a>'+
                            '</td>'+
                        '</tr>';
                })
            }

            $('#tableList').html(html);


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
                            initReServApplyList(pageNo);
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
    });
}

function updateReServApply(id,status) {
    var dealStatus = '';
    if(status == 0){
        dealStatus = 1;
    }else{
        dealStatus = 0;
    }
    $.ajax({
        type:"POST",
        url : rootPath +"/InsStudent/updateReServApply",
        data : {
            "id":id,
            "dealStatus":dealStatus
        },
        async: false,
        dataType:"json",
        success : function(data) {
            initReServApplyList(currtPage);
        }
    });
}

function updateReServApplyNot(id) {
    var not = $("#not").val();
    $.ajax({
        type:"POST",
        url : rootPath +"/InsStudent/updateReServApply",
        data : {
            "id":id,
            "note":not
        },
        async: false,
        dataType:"json",
        success : function(data) {
            initReServApplyList(currtPage);
        }
    });
}

