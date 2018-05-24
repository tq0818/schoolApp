/*
* 该js中主要存放首页分类推荐相关js
* */

var nowTypePageStart = 0;
var typePageSize = 10;

function getRecommendTypeList(){

    $.ajax({
        url: rootPath+'/institutionRecommend/typeList',
        data: {
            pageStart:nowTypePageStart,
            pageSize:typePageSize
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
            var num = json.num;
            var list = json.data.data;
            var html = "";
            for(var i = 0;i<list.length;i++ ){
                list[i].num = num;
                html += `
                        <tr data-buy="false">
                            <td>${parseInt(i) + 1}</td>
                            <td>${list[i].codeName}</td>
                            <td>${list[i].codeLevel == 1 ? '一级' : '二级'}</td>
                            <td>
                            <span>${list[i].firstRecommend != 1 ? '-' : (list[i].sort)}</span>
                                ${list[i].firstRecommend != 1 || (list.length == 1 && nowTypePageStart == 0) ? '' : (
                                   parseInt(i) == 0 && list[i].sort == 1  ? "<i data-id='"+list[i].id+"' data-status='down' class=\"icon iconfont\">&#xe6e4;</i>" : (
                                       list[parseInt(i)].sort == list[parseInt(i)].num || (parseInt(i) + 1 < list.length && list[parseInt(i) + 1].firstRecommend != 1) ? 
                                           " <i data-id='"+list[i].id+"'  data-status='up' class=\"icon iconfont\">&#xe6e3;</i>" :
                                           " <i data-id='"+list[i].id+"'  data-status='up' class=\"icon iconfont\">&#xe6e3;</i> " + "<i data-id='"+list[i].id+"' data-status='down' class=\"icon iconfont\">&#xe6e4;</i>"
                                   )
                )}
                              
                            </td>
                            <td data-id="${list[i].id}" class="cancelRecommend">${list[i].firstRecommend != 1 ? '推荐' : '取消推荐'}</td>
                        </tr>
                `;
            }


            if(list.length == 0){
                html = "<tr><td colspan='5'>没有数据</td></tr>";
            }

            $(".paginationType").pagination(json.data.rowCount,
                {
                    next_text: "下一页",
                    prev_text: "上一页",
                    current_page:json.data.pageNo,
                    link_to: "javascript:getRecommendTypeList()",
                    num_display_entries: 3,
                    items_per_page: 10,
                    num_edge_entries: 1,
                    callback: function (page) {
                        nowTypePageStart = page;
                        getRecommendTypeList();
                    }
                }
            );



            $('#typeTbody').html(html);

            //调整排序
            $('.iconfont').click(function(){
                //upDownOnlineClass($(this).attr('data-id'),$(this).attr('data-status'));

                $.post(rootPath+'/institutionRecommend/changeSort',{
                    id:$(this).attr('data-id'),
                    method:$(this).attr('data-status')
                },function(json){
                    if(json.status == 1){
                        $.msg('操作成功');
                        getRecommendTypeList();
                    }else{
                        $.msg('操作失败!')
                    }
                })

            })

            //取消推荐
            $('.cancelRecommend').click(function(){
                // linkClass($(this).attr('data-id'),$(this).html());

                $.post(rootPath+'/institutionRecommend/changeStatus',{

                    id:$(this).attr('data-id')
                },function(json){
                    console.log();
                    if(json.status == 1){
                        $.msg('操作成功');
                        getRecommendTypeList();
                    }else{
                        $.msg('操作失败!')
                    }
                })

            });

        }
    });

}






