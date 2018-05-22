/* 该js用于首页列表推荐使用 */
var nowIndexPage = 0;
var pageSize = 10;
function getIndexRecommendList(){

    $.ajax({
        url: rootPath+'/institutionRecommend/getRecommendList',
        data: {
             typeId:getCurrentTypeId(),
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
          //  console.log(json);
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
                html += `
                    <tr>
                        <td>${parseInt(i)+1}</td>
                        <td>${list[i].name}</td>
                        <td>${list[i].lv == 2 ? list[i].name2 : list[i].name1}</td>
                        <td>${list[i].lv == 2 ? '二级' : '一级'}</td>
                        <td class="relationResult">${list[i].is_recommend == 1 ? '已推荐' : '未推荐'}</td>
                        <td>${
                    list[i].is_recommend == 1 ?
                        ( parseInt(i)+1 + ( ( list.length > 1 && parseInt(i) == 0 && list[parseInt(i) + 1].is_recommend != 1) || (list.length == 1 && nowIndexPage == 0) ? '' : parseInt(i) == 0 ?
                            "<i data-id='"+list[i].rid+"' data-status='down' class='icon iconfont'>&#xe6e4;</i>"
                            : ( list[i].sort == json.data.maxSort ||  list[i].sort == json.data.recommendNum || (parseInt(i) < list.length - 1 && list[parseInt(i) + 1].is_recommend != 1 ) ?
                                    "<i data-id='"+list[i].rid+"' data-status='up' class='icon iconfont'>&#xe6e3;</i>" :
                                    "<i data-id='"+list[i].rid+"' data-status='up' class='icon iconfont'>&#xe6e3;</i>" +
                                    "<i data-id='"+list[i].rid+"' data-status='down' class='icon iconfont'>&#xe6e4;</i>"
                            )) ) : "-"
                    }</td>
                        <td class="recomendBtn" data-id="${list[i].rid}" data-insId="${list[i].id}" >${list[i].is_recommend == 1 ? '取消推荐' : '推荐'}</td>
                    </tr>
                `;
            }


            if(list.length == 0){
                html = "<tr><td colspan='7'>没有数据</td></tr>";
            }


            $("#indexRecommendTbody").html(html);

            $(".paginationIndexRecommend").pagination(json.data.count,
                {
                    next_text: "下一页",
                    prev_text: "上一页",
                    current_page:json.data.page,
                    link_to: "javascript:getIndexRecommendList()",
                    num_display_entries: 7,
                    items_per_page: 7,
                    num_edge_entries: 1,
                    callback: function (page) {
                        nowIndexPage = page;
                        getIndexRecommendList();
                    }
                }
            );



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

                $.post(rootPath+'/institutionRecommend/updateIndexRecommendStatus',{
                    insId:$(this).attr('data-insId'),
                    rid:$(this).attr('data-id'),
                    typeId:getCurrentTypeId()
                },function(json){
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
        if($(alist[i]).hasClass('btn-primary')){
            return $(alist[i]).attr('data-id');
        }
    }
    return 1;
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
function getRecommendTypeData(){
    $.post(rootPath + '/institutionRecommend/typeListAll' , {type:1} , function(json){
      //  console.log(json);

    var recommendNum = 0;

    var html = '<span>推荐分类</span>';
        for(var i in json){
            if(json[i].thirdRecommend == 1){
                if(recommendNum == 0){
                    html += "<a href=\"##\" data-id='"+json[i].id+"' class=\"btn recommendTypeBtn btn-default btn-primary btn-sm\">"+json[i].codeName+"<span class='pullRight'>></span></a>" ;
                    recommendNum ++ ;
                }else{
                    html += "<a href=\"##\" data-id='"+json[i].id+"' class=\"btn recommendTypeBtn btn-default  btn-sm\"><span class='pullLeft'><</span>"+json[i].codeName+"<span class='pullRight'>></span></a>" ;
                    recommendNum ++ ;
                }

            }
        }

        html += " <span>\n" + "<a href=\"/InsInfoBase/recommendationHomeC/1\" style=\"color: red;\">管理</a>\n" + "</span>";

       $('#recommendBtnContainor').html(html);


       $('.recommendTypeBtn').click(function() {
           //取消其他标签的class属性
           var alist = $('#recommendBtnContainor').find('.recommendTypeBtn');
          // console.log(alist);
            for(var i = 0;i<alist.length;i++){
                if($(alist[i]).hasClass('btn-primary')){
                    $(alist[i]).removeClass('btn-primary');
                }
            }


           //给当前a标签添加class
           $(this).addClass('btn-primary');

           getIndexRecommendList();
       })


    })
}


$(function () {
    //点击箭头
    $('body').on('click', '.pullLeft', function () {
        alert("点击了左边");
    });
    $('body').on('click', '.pullRight', function () {
        alert("点击了右边");
    });
});


