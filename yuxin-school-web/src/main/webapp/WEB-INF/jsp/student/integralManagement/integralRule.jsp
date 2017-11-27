<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>配置积分规则</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading integralHeading">
            <h2 class="h5 integralH5">学生积分修改</h2>
            <span class="">
                <a href="##">姓名:</a>
                <span>张三</span>
            </span>
            <span>
                <a href="##">现有积分:</a>
                <span>999</span>
            </span>
            <span class="line"></span>
        </div>
        <div class="user-list">
            <table class="table table-center">
                <tr data-buy="true">
                    <th width="20%" >顺序</th>
                    <th width="20%" >积分变更名目</th>
                    <th width="20%" >积分流水</th>
                    <th width="20%" >积分变更时间</th>
                    <th width="20%" >操作人</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>课程报名自动通知</td>
                    <td>2</td>
                    <td>2017/11/01 18:39</td>
                    <td>系统</td>
                </tr>
            </table>
            <%--<div class="pages ">--%>
                <%--<ul class="pagination">--%>

                <%--</ul>--%>
            <%--</div>--%>
            <div class="changeIntegral">
                <input type="text" class="integralInput">
                <select class="integralselect">
                    <option value="1">选择积分调整理由</option>
                    <option value="2">选择积分调整理由</option>
                    <option value="3">选择积分调整理由</option>
                </select>
                <button class="btn btn-primary adjusting">调整积分</button>
            </div>
            <div class="integraBtn">
                <button class="btn btn-success">保存</button>
                <button class="btn btn-warning">取消</button>
            </div>
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
//    $(".pagination").pagination($("#rowCount").val(), {
//        next_text : "下一页",
//        prev_text : "上一页",
//        current_page : ($("#pageNo").val() - 1),
//        link_to : "javascript:;",
//        num_display_entries : 5,
//        items_per_page : $("#pageSize").val(),
//        num_edge_entries : 1,
//        callback : function(page, jq) {
//            var pageNo = page + 1;
//        }
//    });

//点击积分调整，增加一条

$('.adjusting').click(function () {
    var integralInput = $('.integralInput').val();
    var integralselect = $('.integralselect').children('option:selected ').html();
    var index = $('.table-center').find('tr').length;
    var integralDate = new Date();
    var _html =   `<tr>
                    <td>`+index+`</td>
                    <td>`+integralselect+`</td>
                    <td>`+integralInput+`</td>
                    <td>`+integralDate+`</td>
                    <td>系统</td>
                    </tr>`;
    $('table').append(_html);
});
</script>
</body>
</html>