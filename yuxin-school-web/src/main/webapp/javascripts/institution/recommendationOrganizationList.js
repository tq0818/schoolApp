/* 该js用于首页列表推荐使用 */
var nowIndexPage = 0;
var pageSize = 10;
function getIndexRecommendList(){

    $.ajax({
        url: rootPath+'/institutionRecommend/getRecommendList',
        data: {
             typeId:1,
             page:nowIndexPage,
             pageSize:pageSize
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
            console.log(json);
            var list = json.data.list;
            var html = "";
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
                        ( list[i].sort + ( list[i].sort + parseInt(i) == 1 ?
                            "<i data-id='"+list[i].rid+"' data-status='down' class='icon iconfont'>&#xe6e4;</i>"
                            : (list[i].sort == json.data.recommendNum || (parseInt(i) < list.length - 1 && list[parseInt(i) + 1].is_recommend != 1 ) ?
                                    "<i data-id='"+list[i].rid+"' data-status='up' class='icon iconfont'>&#xe6e3;</i>" :
                                    "<i data-id='"+list[i].rid+"' data-status='up' class='icon iconfont'>&#xe6e3;</i>" +
                                    "<i data-id='"+list[i].rid+"' data-status='down' class='icon iconfont'>&#xe6e4;</i>"
                            )) ) : "-"
                    }</td>
                        <td class="recomendBtn" data-id="${list[i].rid}" data-insId="${list[i].id}" >${list[i].is_recommend == 1 ? '取消推荐' : '推荐'}</td>
                    </tr>
                `;
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
    return 1;
}


/**
 * 获取推荐分类，用于筛选推荐机构
 */
function getRecommendTypeData(){
    $.post(rootPath + '' , {} , function(json){

    })
}




