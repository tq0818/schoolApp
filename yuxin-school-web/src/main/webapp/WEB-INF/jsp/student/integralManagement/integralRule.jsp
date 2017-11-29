<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>配置积分规则</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/jedate.css">
    <script src="<%=rootPath %>/javascripts/plus/jquery.jedate.min.js"></script>
    <script src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading integralHeading">
            <h2 class="h5 integralH5">学生积分修改</h2>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <table class="table table-center tableRule">
                <tr data-buy="true">
                    <th width="10%" >规则编号</th>
                    <th width="20%" >积分变更名目</th>
                    <th width="10%" >积分值</th>
                    <th width="15%" >生效时间</th>
                    <th width="15%" >失效时间</th>
                    <th width="10%" >操作人</th>
                    <th width="20%" >状态操作</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>回答问题</td>
                    <td><input type="text" value="+2" disabled="disabled"></td>
                    <td><input type="text" value="2017/11/01 18:39" readonly class="dateRuleStart1"></td>
                    <td><input type="text" value="2017/11/01 18:39" readonly class="dateRuleEnd1"></td>
                    <td>系统</td>
                    <td>
                        <button class="btn btn-primary forbidBtn">禁用</button>
                        <button class="btn btn-default editRuleBtn">编辑</button>
                    </td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>回答问题</td>
                    <td><input type="text" value="+2" disabled="disabled"></td>
                    <td><input type="text" value="2017/11/01 18:39" readonly class="dateRuleStart2"></td>
                    <td><input type="text" value="2017/11/01 18:39" readonly class="dateRuleEnd2"></td>
                    <td>系统</td>
                    <td>
                        <button class="btn btn-primary forbidBtn">禁用</button>
                        <button class="btn btn-default editRuleBtn">编辑</button>
                    </td>
                </tr>
            </table>
            <div class="integraBtn">
                <button class="btn btn-success">保存</button>
                <button class="btn btn-warning">取消</button>
            </div>
        </div>
        <div class="pages ">
            <ul class="pagination">

            </ul>
        </div>
    </div>
</div>

<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
    //    分页
    $(".pagination").pagination($("#rowCount").val(), {
        next_text : "下一页",
        prev_text : "上一页",
        current_page : ($("#pageNo").val() - 1),
        link_to : "javascript:;",
        num_display_entries : 5,
        items_per_page : $("#pageSize").val(),
        num_edge_entries : 1,
        callback : function(page, jq) {
            var pageNo = page + 1;
        }
    });

</script>
<script>
//    禁用和启用的切换
    $('.forbidBtn').click(function(){
        if($(this).html()=='禁用'){
            $(this).html('启用');
            $(this).removeClass('btn-primary');
            $(this).addClass('btn-default');
        }else{
            $(this).html('禁用');
            $(this).addClass('btn-primary');
            $(this).removeClass('btn-default');
        }
    });
//点击编辑，该行变为可编辑状态
    $('.editRuleBtn').click(function() {
        var rowInput = $(this).parent().siblings('td').find('input');
        rowInput.addClass('active');
        rowInput.eq(0).attr('disabled', false);

        //调用日历插件
        var start = {
            format:'YYYY-MM-DD hh:mm',
            minDate: $.nowDate({DD: 0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            okfun: function (obj) {
                end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
            }
        };
        var end = {
            format:'YYYY-MM-DD hh:mm',
            minDate: $.nowDate({DD: 0}), //设定最小日期为当前日期
            maxDate: '2099-06-16 23:59:59', //最大日期
            okfun: function (obj) {
                start.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
            }
        };

        var editIndex = $(this).parent().parent().index();

        $.jeDate('.dateRuleStart'+editIndex, start);
        $.jeDate('.dateRuleEnd'+editIndex, end);

    });
</script>

<script>
    //        二级菜单加active
    $(function () {
        $selectSubMenu('integralRule');
    });
</script>
</body>
</html>