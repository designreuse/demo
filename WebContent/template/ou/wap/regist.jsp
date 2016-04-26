<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/template/ou/module/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${webName}-会员注册页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <link href="${webRoot}/template/ou/wap/statics/css/header.css" rel="stylesheet" media="screen">
    <link href="${webRoot}/template/ou/wap/statics/css/sign-up.css" rel="stylesheet" media="screen">
    <script src="${webRoot}/template/ou/wap/statics/js/base.js" type="text/javascript"> </script>
    <link href="${webRoot}/template/ou/wap/statics/css/wapcommondialog.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery-1.6.1.min.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/wapcommondialog.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery.md5.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery.form.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/jquery-ui-1.8.13/development-bundle/external/jquery.cookie.js"></script>
    <script type="text/javascript" src="${webRoot}/template/ou/wap/statics/js/register.js"></script>
    <script type="text/javascript">
        var webPath = {webRoot:"${webRoot}"};
        //显示弹出框出
        function showDialog(obj){
            $(".hideDialog").click(function(){
                hideDialog(obj);
            });
            center(obj).show();
            $("#fade").show();
        }
        //隐藏弹出框出
        function hideDialog(obj){
            $(obj).hide();
            $("#fade").hide();
        }
        //设置弹出框出现的位置
        function center(obj) {
            var screenWidth =parseInt($(window).width()), screenHeight = parseInt($(window).height());  //当前浏览器窗口的 宽高
            /*var scrolltop =parseInt($(document).scrollTop());//获取当前窗口距离页面顶部高度*/
            var objLeft =  (screenWidth-parseInt($(obj).css("width")))/2;
            var objTop = (screenHeight-parseInt($(obj).css("height")))/2;
            return $(obj).css({"left": objLeft + 'px', "top": objTop + 'px',"position":"fixed"});
        }
        function openWin(){
            showDialog("")
        }
    </script>
    <style type="text/css">
        /***确认弹出框样式***/
        .white_content {
            display: none;
            position: absolute;
            background-color: white;
            z-index:1002;
            overflow: auto;
        }
        /**遮罩层***/
        .black_overlay {
            cursor:pointer;
            display: none;
            top: 0%;
            left: 0%;
            width: 100%;
            height: 100%;
            position: fixed;
            /*background-color:#f5f5f5;*/
            background:none repeat scroll 0 0 #000;
            z-index:1001;
            -moz-opacity: 0.5;
            opacity:0.5;
            filter: alpha(opacity=80);
        }
        .conpnyDo{ width:30rem; height:20rem; padding:15px 15px 0 15px;  overflow-y:scroll; overflow-x:hidden;}
        .conpnyDo h1{ line-height:23px; font-size: 16px;}
        .conpnyDo p{ line-height:20px; text-indent:26px;}
        .main1 .btn{text-align: center; width: 100%; padding-top: 24px; border-top: 1px solid #ccc;}
        .main1 .btn .close{display: inline-block; font-size: 15px; border: 1px solid #ccc; width: 100px;height: 35px;  text-align: center;  line-height:36px;box-shadow: 0 0 6px rgba(0, 0, 0, 0.1);color: #333; background-color: #f8f8f8;}
        .main1 .btn a:hover.close{background-color: #e9e9e9;}
    </style>
</head>
<body>
<header class="header">
    <a href="javascript:;" onclick="javascript: history.go(-1);" class="back"></a>
    <div class="tit">注册</div>
    <a href="${webRoot}/wap/login.ac" class="hd-save">登陆</a>
</header>
    <form method="post" id="registerForm" name="registerForm" >
        <div class="main01">
            <input type="hidden" name="regType" id="regType" />
            <input type="text" id="userMobile" name="userMobile" class="name" placeholder="请输入您的手机号" onblur="return checkUserMobileValidate()">
            <p class="error" id="mobileTip" style="display:none; text-align: center;background-color: #f0226e;color: #fff;"></p>
            <div  style="position:relative;"><input id="mobileCode" type="text" class="psword" placeholder="请输入验证码" onblur="return checkMobileCode()" />
            <p  style="display:none; text-align: center;background-color: #f0226e;color: #fff;" id="mobileCodeTip">短信验证码不正确</p>
            <span style="" id="mobileCodeSpan"><a href="javascript:;" class="yzm-btn"  onclick="return getMobileCode()" id='mobileCodeBtn' >发送验证码</a></span></div>
            <a href="javascript:;" onclick="return checkNextstep()" class="next-btn">下一步</a>
        </div>


        <div class="main02" style="display: none;">
            <input  class="name" type="text" placeholder="请输入您的用户昵称（必填）" id="userName" name="userName" onblur="return changeUserName()">
            <p class="error" id="userNameTip" style="display:none;text-align: center;background-color: #f0226e;color: #fff;"></p>
            <input class="psword" type="password" placeholder="请输入6-14位字符密码" id="userPsw" name="userPsw" maxlength="16" onblur="return checkPsw()">
            <p class="error" id="userPswTip" style="display:none;text-align: center;background-color: #f0226e;color: #fff;"></p>
            <input class="psword" type="password" placeholder="请再次输入密码" id="checkPassword" maxlength="16" onblur="return cheCkcheckPsw()">
            <p class="error" id="checkPasswordTip" style="display:none;text-align: center;background-color: #f0226e;color: #fff;"></p>
            <a href="javascript:;" class="sign-btn" onclick="return checkRegisterForm(this)" id="regBtn">注册</a>
            <div class="agree">
                <a class="all-sel selected" onclick="return checkAgree();" href="javascript:;"></a> <!--选择加class:selected-->
                <span>同意</span>
                <%--<a href="javascript:;" class="ag-link" onclick="showDialog('#serviceDiv')">《${sdk:getSysParamValue('index_title')}网服务条款》</a>--%>
            </div>
        </div>
    </form>
    <!---服务条款-->
    <%--<div id="serviceDiv" class="main1 white_content" style="display: none;width: 30rem;height: 26rem">--%>
        <%--<div style="width: 30rem;height: auto">--%>
            <%--<div class="conpnyDo" >--%>
                <%--<center><h1>${sdk:getSysParamValue('index_title')}服务协议</h1></center>--%>
                <%--<p>${sdk:getSysParamValue('register_provisions')}</p>--%>
            <%--</div>--%>
            <%--<div class="btn">--%>
                <%--<a href="javascript:;" class="close" onclick="hideDialog('#serviceDiv');" >关闭</a>--%>
            <%--</div>--%>
        <%--</div>--%>

    <%--</div>--%>
</body>
</html>

