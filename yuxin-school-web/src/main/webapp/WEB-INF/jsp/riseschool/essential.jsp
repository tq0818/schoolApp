<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>基本信息</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/fonts/iconfont.css">
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/riseschool/schoolDetails.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/riseschool/essential.css">

</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitre.jsp"/>
<div class="u-wrap admin overflow schoolDetails">
    <jsp:include page="/WEB-INF/jsp/menu/menu_earlyLitreLeft.jsp"></jsp:include>
    <div class="right-side">
        <div class="mainbackground nopadding">
            <div class="heading">
                <h2 class="h5">基本信息</h2>
                <span class="line"></span>
            </div>
            <div class="schoolDetailsContent">

                    <div>
                        <i class="icon iconfont star">&#xe605;</i>
                        <label for="" class="noMargin">学校名称：</label>
                        <input type="text"  maxlength="60">
                    </div>
                    <div class="schoolCount">
                        <i class="icon iconfont star">&#xe605;</i>
                        <label for="" class="noMargin">学校账号：</label>
                        <input type="text" placeholder="请输入学校管理员账号">
                        <span>初始密码为：111111</span>
                    </div>
                    <div>
                        <i class="icon iconfont star">&#xe605;</i>
                        <label for="" class="noMargin">招生方式：</label>
                        <select name="" id="">
                            <option value="">请选择招生方式</option>
                        </select>
                    </div>
                    <div class="schoolSite">
                        <i class="icon iconfont star">&#xe605;</i>
                        <label for="" class="noMargin">学校地址：</label>
                        <select name="" id="">
                            <option value="">学校所在省份</option>
                        </select>
                        <select name="" id="">
                            <option value="">学校所在市</option>
                        </select>
                        <select name="" id="">
                            <option value="">学校所在区</option>
                        </select>
                        <input type="text" placeholder="请输入详细地址" maxlength="60">
                    </div>
                    <div>
                        <label for="">学校网址：</label>
                        <input type="text">
                    </div>
                    <div>
                        <label for="">学校传真：</label>
                        <input type="text">
                    </div>
                    <div>
                        <label for="">公交路线：</label>
                        <input type="text" maxlength="200">
                    </div>
                    <div>
                        <label for="">收藏基数：</label>
                        <input type="text" maxlength="4" placeholder="请输入0-1000">
                    </div>
                    <div class="countPopupBtn">
                        <a href="##" class="btn btn-sm btn-primary countPopupCancel">取消</a>
                        <a href="##" class="btn btn-sm btn-primary countPopupSave">保存</a>
                    </div>

            </div>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display: none">
    <p>
        <i></i>加载中,请稍后...
    </p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display: none"></div>

<script src="<%=rootPath %>/javascripts/riseschool/schoolDetails.js"></script>
<script>
//    左侧active切换
    $selectSubMenus('essential');
</script>
</body>
</html>
