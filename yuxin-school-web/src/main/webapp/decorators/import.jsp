<%@ page import="com.yuxin.wx.model.user.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%
	String rootPath=request.getContextPath();
	Users loginUser=(Users)session.getAttribute("loginUser");
%>
<%--新增样式begin--%>
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/addNewStyle.css" />
<%--新增样式end--%>
<%--新增script begin--%>
<script src="<%=rootPath %>/javascripts/addNewCommonScript.js" ></script>
<script>
    //日期插件调整
    $(document).click(function () {
        $('.datetimepicker').hide();
    });
    //阻止冒泡
    $('.date-picker').click(function () {
        return false;
    });
    $('.date-picker1').click(function () {
        return false;
    });

</script>
<%--新增script end--%>