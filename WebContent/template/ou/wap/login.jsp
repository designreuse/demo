<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/template/ou/module/common/taglibs.jsp" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>${webName}-会员登录页</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <!-- Bootstrap -->
  <link href="${webRoot}/template/ou/wap/statics/css/header.css" rel="stylesheet" media="screen">
  <link href="${webRoot}/template/ou/wap/statics/css/log-in.css" rel="stylesheet" media="screen">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
  <![endif]-->
  <%--<script src="${webRoot}/template/100yue3/wap/statics/js/bootstrap.min.js"></script>--%>
  <script src="${webRoot}/template/ou/wap/statics/js/jquery-1.6.1.min.js"></script>
  <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery.md5.js"></script>
  <script src="${webRoot}/template/ou/wap/statics/js/base.js" type="text/javascript"> </script>

  <script language="javascript" type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/loginValidate.js"></script>
  <script language="javascript" type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/loginValidateCode.js"></script><%--登录验证插件--%>
  <script type="text/javascript">
    var webPath = {webRoot:"${webRoot}"};
  </script>
</head>
<body>
    <header class="header">
      <a href="javascript:" onclick="history.go(-1);" class="back"></a>
      <div class="tit">登陆</div>
      <a href="${webRoot}/wap/regist.ac" class="hd-save">注册</a>
    </header>
  <form method="post"  id="loginForm" name="loginForm" >
    <div class="main">
      <div>
        <input type="text" class="name" id="loginId" placeholder="用户名" >

        <div  id="alert" style="display: none;">
          <div class="t-b" id="alerttext" style="background-color: #eb6877;color: #fff;text-align: center;"></div>
          <div class="clear"></div>
        </div>
      </div>
      <div>
        <input type="password" id="userPsw" name="userPsw" class="psword" placeholder="密码" >

        <div id="alert2" style="display: none;">
          <div class="t-b" id="alerttext1" style="background-color: #eb6877;color: #fff;text-align: center;"></div>
          <div class="clear"></div>
        </div>
      </div>
      <a href="javascript:"  class="log-btn" onclick="$('#loginForm').submit();">登录</a>

      <%--</div>--%>
      <%--<a href="javascript:;"  class="log-btn" onclick="$('#loginForm').submit();">登录</a>--%>

  </form>

    <div class="other">
      第三方账号登录
      <%--<ul>--%>
        <%--<c:if test="${sdk:checkMultiLoginIsEnable('0')=='Y'}">--%>
          <%--<li><a href="#" title="新浪微博"><img src="${webRoot}/template/zblp/wap/statics/images/weibo-icon.png" alt=""/></a></li>--%>
        <%--</c:if>--%>
        <%--<c:if test="${sdk:checkMultiLoginIsEnable('1')=='Y'}">--%>
          <%--<li><a href="#" title="qq"><img src="${webRoot}/template/zblp/wap/statics/images/qq-icon.png" alt=""/></a></li>--%>
        <%--</c:if>--%>
      <%--</ul>--%>
    </div>
    <div class="forgot">
      <span>忘记密码?</span>
      <a href="${webRoot}/wap/fetchPsw/fetchPswByMobile.ac">马上找回</a>
    </div>
    </div>
    <div class="pop-layer" style="display: none">未找到该用户名！</div>
    <%--<div class="reg-mode" style="display: none">--%>
      <%--<div class="rm-cont">--%>
        <%--<a href="${webRoot}/wap/regist.ac" class="phone">短信注册</a>--%>
        <%--<a href="#" class="e-mail">邮箱注册</a>--%>
        <%--<a href="#" class="cancel">取消</a>--%>
      <%--</div>--%>
    <%--</div>--%>

</body>
</html>

