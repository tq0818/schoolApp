/* 该js用于首页列表推荐使用 */
var nowIndexPage = 0;
var pageSize = 10;


var pageCount = '';
var pageNo = '';
var dataSize2 = '';
function getIndexRecommendList(){
    var typeId = getCurrentTypeId();
    if(null == typeId){
        $("#indexRecommendTbody").html("<tr><td colspan='7'>没有数据</td></tr>");
        return;
    }
    $.ajax({
        url: rootPath+'/institutionRecommend/getRecommendList',
        data: {
             typeId:typeId,
             page:nowIndexPage,
             pageSize:pageSize,
             status:getStatus(),
             name:$('#recommendName').val()
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
            var size = json.data.count;
            var page = size%10;
             pageCount = '';
            if(page ==0){
                pageCount = parseInt(size/10);
            }else{
                pageCount = parseInt(size/10)+1;
            }
             pageNo = json.data.page+1;
             dataSize2 = json.data.list.length;

            var recommendBtnContainor = '';
            var level = '';
            var recommendBtnContainorList = $("#recommendBtnContainor").children('a');
            for(var i =0;i<recommendBtnContainorList.length;i++){
                if(recommendBtnContainorList.eq(i).hasClass("btn-primary")){
                    if(i == 0){
                        recommendBtnContainor = recommendBtnContainorList.eq(i).children('span').eq(0).text();
                        level = recommendBtnContainorList.eq(i).children('span').eq(0).attr("data-level");
                    }else{
                        recommendBtnContainor = recommendBtnContainorList.eq(i).children('span').eq(1).text();
                        level = recommendBtnContainorList.eq(i).children('span').eq(1).attr("data-level");
                    }

                }
            }



            if(json.status != 1){
                $.msg(json.msg);
                return;
            }
            var list = json.data.list;
            var html = "";

            //判断是否还有下一页，用于判断最后一个推荐机构上下箭头有几个的问题
            var nowPage = parseInt(nowIndexPage/pageSize) + 1;
            var pageNum = json.data.count <= 10 ? 1 : parseInt(json.data.count / pageSize) + 1;
            var hasNextPage = pageNum > nowPage ;

            for(var i in list){
                var statu = '';
                var statu2 = '';
                var flag = '';
                var isR = list[i].is_recommend;
                if(isR == 1 && level ==1){
                    statu='取消推荐';
                    statu2='已推荐';
                    flag ='0';
                }else if(isR == 1 && level ==2){
                    statu='推荐';
                    statu2='未推荐';
                    flag ='1';
                }else if(isR == 2 && level ==1){
                    statu='推荐';
                    statu2='未推荐';
                    flag ='1';
                }else if(isR == 2 && level ==2){
                    statu='取消推荐';
                    statu2='已推荐';
                    flag ='0';
                }else if(isR == 3 && level ==1){
                    statu='取消推荐';
                    statu2='已推荐';
                    flag ='0';
                }else if(isR == 3 && level ==2){
                    statu='取消推荐';
                    statu2='已推荐';
                    flag ='0';
                }else if(isR == 0 && level ==2){
                    statu='推荐';
                    statu2='未推荐';
                    flag ='1';
                }else if(isR == 0 && level ==1){
                    statu='推荐';
                    statu2='未推荐';
                    flag ='1';
                }

                var isR = list[i].is_recommend;
                if(isR == 0){
                    isR = 0;
                }else if(isR == 1 && level == 1){
                    isR = 1;
                }else if(isR == 1 && level == 2){
                    isR = 0;
                }else if(isR == 2 && level == 1){
                    isR = 0;
                }else if(isR == 2 && level == 2){
                    isR = 1;
                }else if(isR == 3 && level == 1){
                    isR = 1;
                }else if(isR == 3 && level == 2){
                    isR = 1;
                }

                var showSort = json.data.page*json.data.pageSize + parseInt(i) + 1 ;
                console.log('json.data.page = '+json.data.page);
                console.log("i = "+i);
                html += `
                    <tr>
                        <td>${parseInt(i)+1}</td>
                        <td>${list[i].name}</td>
                        <td>`+recommendBtnContainor+`</td>
                        
                        <td class="relationResult">${statu2}</td>
                        <td>${
                    isR == 1 ? ( showSort
                    + ( (json.data.page == 0 && parseInt(i) == 0 ) ? '' : "<i data-id='"+list[i].rid+"' data-status='up' class='icon iconfont'>&#xe6e3;</i>" )
                    + ( (list[parseInt(i)+1] != undefined && list[parseInt(i)+1].is_recommend == 0) || (json.data.page == 0 && json.data.count == 1) ||  (showSort >= json.data.recommendNum) || showSort >= json.data.count ? '' : "<i data-id='"+list[i].rid+"' data-status='down' class='icon iconfont'>&#xe6e4;</i>" ))
                    : '-'
                    }</td>
                        <td class="recomendBtn" data-id="${list[i].rid}" data-insId="${list[i].id}" data-level = "${level}" data-flag = "${flag}">`+statu+`</td>
                    </tr>
                `;
            }


            if(list.length == 0){
                html = "<tr><td colspan='7'>没有数据</td></tr>";
            }

            $("#indexRecommendTbody").html(html);

            if(json.data.count <= pageSize){
                $(".paginationIndexRecommend").html('');
            }else{
                $(".paginationIndexRecommend").pagination(json.data.count,
                    {
                        next_text: "下一页",
                        prev_text: "上一页",
                        current_page:json.data.page,
                        link_to: "javascript:getIndexRecommendList()",
                        num_display_entries: 10,
                        items_per_page: 10,
                        num_edge_entries: 0,
                        callback: function (page) {
                            nowIndexPage = page;
                            getIndexRecommendList();
                        }
                    }
                );

            }



         //   $('#onlineTbody').html(html);

            //调整排序
            $('.iconfont').click(function(){
                //upDownOnlineClass($(this).attr('data-id'),$(this).attr('data-status'));

                $.post(rootPath+'/institutionRecommend/sort',{

                    rid:$(this).attr('data-id'),
                    method:$(this).attr('data-status') == 'up' ? 'add' : 'sub',
                    typeId:getCurrentTypeId()
                },function(json){
                    if(json == 'success'){
                        $.msg('操作成功');
                        getIndexRecommendList();
                    }else{
                        $.msg('操作失败!')
                    }
                })

            })

            //取消推荐
            $('.recomendBtn').click(function(){
                // linkClass($(this).attr('data-id'),$(this).html());

                var level = $(this).attr('data-level');
                var flag = $(this).attr('data-flag');

                $.post(rootPath+'/institutionRecommend/updateIndexRecommendStatus',{
                    insId:$(this).attr('data-insId'),
                    rid:$(this).attr('data-id'),
                    typeId:getCurrentTypeId(),
                    level:level,
                    flag:flag
                },function(json){
                    var recommendStatusBtn = $('.recommendStatusBtn');
                    for(var i = 0;i<recommendStatusBtn.length;i++){
                        if(recommendStatusBtn.eq(i).hasClass('btn-primary') && recommendStatusBtn.eq(i).text() != '全部'){
                            if(pageCount != 1){
                                if(pageNo = pageCount && dataSize2 == 1){
                                    nowIndexPage = nowIndexPage - 1;
                                }
                            }
                        }
                    }


                    if(json == 'success'){
                        $.msg('操作成功');
                        getIndexRecommendList();
                    }else{
                        $.msg('操作失败!')
                    }
                })

            });

        }
    });

}


function getCurrentTypeId(){
    var alist = $('#recommendBtnContainor').find('.recommendTypeBtn');
    // console.log(alist);
    for(var i = 0;i<alist.length;i++){
        if($(alist[i]).parent().hasClass('btn-primary')){
            return $(alist[i]).parent().attr('data-id');
        }
    }
    return null;
}

function getStatus(){
    var alist = $('#recommendStatusContainor').find('.btn-default');
    for(var i = 0;i<alist.length;i++){
        if($(alist[i]).hasClass('btn-primary')){
           if($(alist[i]).html() == '全部'){
               return "";
           }else if($(alist[i]).html() == '已推荐'){
               return 1;
           }else{
               return 0;
           }
        }
    }
}


/**
 * 获取推荐分类，用于筛选推荐机构
 */
function getRecommendTypeData(id){
    $.post(rootPath + '/institutionRecommend/typeIndexListAll' , {type:1} , function(json){


    var arr = new Array();
    for(var i in json){
        if(json[i].thirdRecommend == 1){
            arr.push(json[i]);
        }
    }

    json = arr;

        console.log(json)

    var html = '<span>推荐分类</span>';
        if(!id){
            for(var i in json){
                html += parseInt(i) == 0 ? "<a href=\"javascript:void(0)\" data-id='"+json[i].id+"'  data-level='"+json[i].codeLevel+"' class=\"btn  btn-default btn-primary btn-sm\">" :  "<a href=\"##\" data-id='"+json[i].id+"' data-level='"+json[i].codeLevel+"' class=\"btn  btn-default  btn-sm\">";
                html += parseInt(i) == 0 ?'':"<span class='pullLeft' data-id='"+json[i].id+"'>&lt;</span>";
                html += "<span data-id='"+json[i].id+"' data-level='"+json[i].codeLevel+"' class='recommendTypeBtn'>"+json[i].codeName+"</span>";
                html += parseInt(i) == json.length - 1 ? '' : "<span class='pullRight' data-id='"+json[i].id+"'>&gt;</span>";
                html += '</a>';

            }
        }else{

            for(var i in json){
                html += json[i].id == id ? "<a href=\"javascript:void(0)\" data-id='"+json[i].id+"' data-level='"+json[i].codeLevel+"' class=\"btn  btn-default btn-primary btn-sm\">" :  "<a href=\"##\" data-id='"+json[i].id+"' data-level='"+json[i].codeLevel+"' class=\"btn  btn-default  btn-sm\">";
                html += parseInt(i) == 0 ?'':"<span class='pullLeft' data-id='"+json[i].id+"'>&lt;</span>";
                html += "<span data-id='"+json[i].id+"' data-level='"+json[i].codeLevel+"' class='recommendTypeBtn'>"+json[i].codeName+"</span>";
                html += parseInt(i) == json.length - 1 ? '' : "<span class='pullRight' data-id='"+json[i].id+"'>&gt;</span>";
                html += '</a>';

            }

        }

        html += " <span>\n" + "<a href=\"/InsInfoBase/recommendationHomeC/1\" style=\"color: red;\">管理</a>\n" + "</span>";

       $('#recommendBtnContainor').html(html);

       //初始化好推荐分类按钮后，查询一次列表
        getIndexRecommendList();

       $('.recommendTypeBtn').click(function() {

           //取消其他标签的class属性
           var alist = $('#recommendBtnContainor').find('.recommendTypeBtn');
          // console.log(alist);
           //$("#test1").parent()
            for(var i = 0;i<alist.length;i++){
                if($(alist[i]).parent().hasClass('btn-primary')){
                    $(alist[i]).parent().removeClass('btn-primary');
                }
            }


           //给当前a标签添加class
           $(this).parent().addClass('btn-primary');

           nowIndexPage = 0;

           getIndexRecommendList();
       })


    })
}


$(function () {
    //点击箭头
    $('body').on('click', '.pullLeft', function () {
        //alert("点击了左边");
        var id = getCurrentTypeId();
        $.post(rootPath+'/institutionRecommend/changeSort',{
            third:1,
            method:'up',
            id:$(this).attr('data-id')
        },function(json){
           if(json.status == 1){
               $.msg('操作成功');
               getRecommendTypeData(id);
           }else{
               $.msg('操作失败');
           }
        })

    });
    $('body').on('click', '.pullRight', function () {
        var id = getCurrentTypeId();
        $.post(rootPath+'/institutionRecommend/changeSort',{
            third:1,
            method:'down',
            id:$(this).attr('data-id')
        },function(json){
            if(json.status == 1){
                $.msg('操作成功');
                getRecommendTypeData(id);

            }else{
                $.msg('操作失败');
            }
        })
    });
});





