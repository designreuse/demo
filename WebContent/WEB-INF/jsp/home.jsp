<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/b2c/common/taglibs.jsp"%>

<%--获取用户信息 用户名和年龄--%>
<c:set value="${sdk:getUserInfo()}" var="user"></c:set>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>home</title>
    <script type="text/javascript" src="${ctx}/static/js/common/jquery-1.11.1.min.js"></script>
    <script type="application/javascript">
        $(function() {
          console.log("ssssssss");
          console.log("userName=" +'${user.userName}'+ " age=" +'${user.age}');
        });
    </script>
</head>
<body>
<h2>spring mvc 实例</h2>

<form action="login.html" method="post">
	username:<input type="text" name="username" />
	<p>
	password:<input type="password" name="password"/>
	<p>
	<input type="submit" value="submit" />
</form>

<a href="other.html">other page</a>
</body>
</html>