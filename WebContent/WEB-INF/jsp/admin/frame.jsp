<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/b2c/common/taglibs.jsp"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    application.setAttribute("basePath",basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>国税协同办公平台-纳税服务</title>
</head>
<frameset cols="*,1222,*" class="bj" frameborder="no" border="0" framespacing="0">
    <frame src="${ctx}/admin/bg.html" scrolling="No" noresize="noresize"/>
    <frameset rows="100,*" cols="*" frameborder="no" border="0" framespacing="0">
        <frame src="${ctx }admin/home_top.html" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
        <frameset cols="14%,60%" frameborder="no" border="0" framespacing="0">
            <frame src="${ctx}admin/home_left.html" scrolling="yes" noresize="noresize" id="leftFrame" />
            <frame src="${ctx}/admin/welcome.html" name="mainFrame" id="mainFrame" />
        </frameset>
    </frameset>
    <frame src="${ctx}/admin/bg.html" scrolling="No" noresize="noresize"/>
</frameset>
<body>
<br>
</body>
</html>