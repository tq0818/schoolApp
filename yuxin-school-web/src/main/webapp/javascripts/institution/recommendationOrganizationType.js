/*
* 该js中主要存放首页分类推荐相关js
* */

function getRecommendTypeList(){

    $.ajax({
        url: rootPath+'xxxx',
        data: {
           /* insId:$('#insId').val(),
            link:getUpDownStatus('chooseBtn2'),
            pageStart:nowOnlinePage,
            pageSize:pageSize*/
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
            var list = json.data;
            var html = "";
            for(var i in list){
                html += `
                    <tr>
                        <td>${parseInt(i)+1}</td>
                        <td>${list[i].name}</td>
                        <td class="relationResult">${list[i].isLink == 1 ? '已关联' : '未关联'}</td>
                        <td>${
                    list[i].isLink == 1 ?
                        ( list[i].sort + (parseInt(i) == 0 ?
                            "<i data-id='"+list[i].rid+"' data-status='down' class='icon iconfont'>&#xe6e4;</i>"
                            : (parseInt(i) == list.length - 1 || list[parseInt(i) + 1].isLink != 1 ?
                                    "<i data-id='"+list[i].rid+"' data-status='up' class='icon iconfont'>&#xe6e3;</i>" :
                                    "<i data-id='"+list[i].rid+"' data-status='up' class='icon iconfont'>&#xe6e3;</i>" +
                                    "<i data-id='"+list[i].rid+"' data-status='down' class='icon iconfont'>&#xe6e4;</i>"
                            )) ) : "-"
                    }</td>
                        <td class="relation" data-id="${list[i].rid}" >取消推荐</td>
                    </tr>
                `;
            }

            $(".paginationOnLine").pagination(json.rowCount,
                {
                    next_text: "下一页",
                    prev_text: "上一页",
                    current_page:json.pageNo,
                    link_to: "javascript:getOnlineClassTypeList()",
                    num_display_entries: 7,
                    items_per_page: 7,
                    num_edge_entries: 1,
                    callback: function (page) {
                        nowOnlinePage = page;
                        getOnlineClassTypeList();
                    }
                }
            );



            $('#onlineTbody').html(html);

            //调整排序
            $('.iconfont').click(function(){
                //upDownOnlineClass($(this).attr('data-id'),$(this).attr('data-status'));

                $.post(rootPath+'/institutionClassType/updateSortOnlineClass',{
                    insId:$('#insId').val(),
                    rid:$(this).attr('data-id'),
                    method:$(this).attr('data-status') == 'up' ? 'add' : 'sub'
                },function(json){
                    if(json == 'success'){
                        $.msg('操作成功');
                        getOnlineClassTypeList();
                    }else{
                        $.msg('操作失败!')
                    }
                })

            })

            //取消推荐
            $('.relation').click(function(){
                // linkClass($(this).attr('data-id'),$(this).html());

                $.post(rootPath+'/institutionClassType/linkOnlineClass',{
                    insId:$('#insId').val(),
                    rid:$(this).attr('data-id')
                },function(json){
                    if(json == 'success'){
                        $.msg('操作成功');
                        getOnlineClassTypeList();
                    }else{
                        $.msg('操作失败!')
                    }
                })

            });

        }
    });

}






