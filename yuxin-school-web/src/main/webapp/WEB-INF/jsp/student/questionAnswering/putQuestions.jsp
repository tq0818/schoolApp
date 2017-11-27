<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
    <%@include file="/decorators/import.jsp" %>
    <title>提问</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_student.jsp"/>
<div class="u-wrap set-system">
    <div class="mainbackground nopadding">
        <div class="heading">
            <h2 class="h5">标签管理</h2>
            <span class="line"></span>
        </div>
        <div class="labelContent">
            <div>
                <h6>系统标签管理</h6>
                <ul>
                    <li>
                        <button class="btn btn-primary">语文</button>
                        <img src="/images/delete.png" alt="">
                    </li>
                    <li>
                        <button class="btn btn-primary">语文</button>
                        <img src="/images/delete.png" alt="">
                    </li>
                    <li>
                        <button class="btn btn-primary">语文</button>
                        <img src="/images/delete.png" alt="">
                    </li>
                    <li class="labelInputContent">
                        <input type="text" class="labelInput">
                        <button class="btn btn-success labelInputContentSave">确定</button>
                        <button class="btn btn-warning labelInputContentCancel">取消</button>
                    </li>
                    <li class="labelAddContent">
                        <button class="btn btn-success labelAdd">+</button>
                    </li>
                </ul>
            </div>
            <div>
                <h6>用户自定义标签</h6>
                <ul>
                    <li>
                        <button class="btn btn-primary">美丽</button>
                        <img src="/images/delete.png" alt="">
                    </li>
                </ul>
            </div>
            <div class="labelSaveBtn">
                <button class="btn btn-success">保存</button>
                <button class="btn btn-warning">取消</button>
            </div>
        </div>
    </div>
</div>


<script>
//点击增加增加一个输入框
    $('.labelAdd').click(function () {
        $('.labelInputContent').css('display','inline-block');
        $('.labelAddContent').hide();
    });
//点击确定，点击取消隐藏输入框显示增加按钮
    $('.labelInputContentSave').click(function () {
        $('.labelInputContent').css('display','none');
        $('.labelAddContent').show();
//      此处可发起ajax请求
        location.reload();
    });
    $('.labelInputContentCancel').click(function () {
        $('.labelInputContent').css('display','none');
        $('.labelAddContent').show();
    });
//点击删除icon弹窗提醒
    $('.labelContent').find('img').click(function () {
        $.confirm("确认删除此项？",function (s) {
            if(s==true){
//              此处发起ajax请求
                location.reload();
                alert("删除成功");
            }
        })
    });

</script>
</body>
</html>