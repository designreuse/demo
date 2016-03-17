<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/b2c/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>home</title>
<script type="text/javascript" src="${ctx}/static/js/common/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<script type="text/javascript">
    $(function() {
       console.log("sssssssss");
       $(".submit").click(function() {
           var username = $("#username").val();
           var password = $("#password").val();
           $.ajax({
              type:"POST",
              data:{username:username, password:password},
              url:ctx + "/demo/saveInfo.json",
              success:function(data) {
                  console.log(data);
                  if(data.success) {
                      alert(data.userInfo);
                  }
              }

           });
       })
    });

</script>


</head>
<body>
<h2>spring mvc 实例</h2>
${sdk:getUserInfo()}

	username:<input type="text" name="username" id="username" />
	<p>
	password:<input type="password" name="password" id="password"/>
	<p>
	<input type="button" class="submit" value="submit" />
</body>
</html>