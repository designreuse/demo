$(document).ready(function(){
    var account= $.cookie('registerMaxInactive');
    if(account!=null){
        t=60;
        $("#mobileCodeBtn").hide();
        timeOut();
    }
});

//手机验证码
function checkMobileCode(){
    if(!isNotNull($("#mobileCode").val())){
        showTip("#mobileCodeTip","请输入验证码!");
        mobileCode= false;
        return;
    }
    var mobileValideCode = $("#mobileCode").val();
    var mobileNo = $("#userMobile").val();
    //$.ajax({
    //    type:"POST",
    //    url:webPath.webRoot+"/member/wapCheckValidateNum.json",
    //    data:{"messageCode":mobileValideCode,"registerType":"mobile","userMobile":mobileNo},
    //    async: false,//同步
    //    success:function(data) {
    //        if(data.success == true){
    //            showSuccess("#mobileCodeTip");
    //            mobileCode= true;
    //            return;
    //        }
    //        //else{
    //        //    showTip("#mobileCodeTip","验证码不正确");
    //        //    mobileCode= false;
    //        //    return;
    //        //}
    //    },
    //    error:function(data){
    //        showTip("#mobileCodeTip","验证码不正确");
    //        if (XMLHttpRequest.status == 500) {
    //            var result = eval("(" + XMLHttpRequest.responseText + ")");
    //            showTip("#mobileCodeTip","抱歉，网络异常，请重试。");
    //        }
    //        mobileCode= false;
    //        return;
    //    }
    //});
    mobileCode= true;

}
/**
 * 检查手机号是否已被注册*/
function checkUserMobile(mobile){
    var bol = false;
    $.ajax({
        type:"GET",
        url:webPath.webRoot+"/member/isExistMobile.json",
        dataType: "json",
        data:{mobile:mobile},
        async: false,//同步
        success:function(data) {
            if(data.success == false){
                showSuccess("#mobileTip");
                bol = true;
            }
            else{
                showTip("#mobileTip","注册失败:您填写的手机号已被注册");
                bol = false;
            }
        },
        error:function(data){
            if (XMLHttpRequest.status == 500) {
                var result = eval("(" + XMLHttpRequest.responseText + ")");
                alert(result.jsonError.errorText);
                bol =  false;
            }
        }
    });
    return bol;
}
var currentMobileNo;
//获取验证码
function getMobileCode(){
    currentMobileNo = $("#userMobile").val();
    if (!isNotNull(currentMobileNo)) {
        alert("请输入手机号！");
        return false;
    }
    $.ajax({
        type:"POST",
        url:webPath.webRoot+"/member/sendValidateNum.json",
        data:{"mobileNum":currentMobileNo},
        async: false,//同步
        success:function(data) {
            if(data.success == true){
                alert("手机验证码发送成功，请注意查看手机信息！");
            }else{
                if (data.errorCode == "mobileIsRegistered") {
                    alert("手机号已经注册了！");
                }else if (data.errorCode == "mobileNoIsNull") {
                    alert("请输入手机号！");
                }else if(data.errorCode == "mobileSmsMaxTime") {
                    alert("今天发送短信的次数已达到上限，请明天再来！");
                } else if(data.errorCode == "ipAddressSmsMaxTime") {
                    alert("今天发送短信的次数已达到上限，请明天再来！");
                } else {
                    alert("验证码发送失败，请重新获取！");
                }
            }
        },
        error:function(data){
            if (XMLHttpRequest.status == 500) {
                var result = eval("(" + XMLHttpRequest.responseText + ")");
                alert("抱歉，网络异常，请重试。");
            }
        }
    });
    t=60;
    $("#mobileCodeBtn").hide();
    timeOut();
}


var checkRegisterForm = function(obj){
    if($(obj).hasClass("notlog-btn")){
        return false;
    }
    if ($("#regByMoblie").hasClass("cur")) {
        //验证手机号是否存在
        if(!checkUserMobileValidate()){
            return false;
        }
        $("#regType").val("mobile");
    }
    if("mobile" == $("#regType").val()) {
        checkMobileCode();
        if(!mobileCode) {
            return;
        }
    }

    //验证密码
    if(!checkPsw()){
        return false;
    }
    if(!cheCkcheckPsw()){
        return false;
    }


    //同意协议验证
    $(".all-sel").attr("class")=="all-sel selected"?isAgree=true:isAgree=false;
    if(!isAgree){//同意协议验证
        alert("请勾选同意服务条款");
        return false;
    }

    var params = $("#registerForm").formToArray();
    $(obj).addClass("notlog-btn").text("注册中...");
    $.ajaxSettings['contentType'] = "application/x-www-form-urlencoded; charset=utf-8;";
    $.ajax({
        type:"POST",
        url:webPath.webRoot+"/member/register.json",
        data:params,
        dataType: "json",
        async: false,//同步
        success:function(data) {
            if (data.success == true) {
                alert("注册成功");
                setTimeout(function(){
                    window.location.href=webPath.webRoot+"/template/ou/wap/index.jsp";
                },3000)
            }
        },
        error:function(XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 500) {
                var result = eval("(" + XMLHttpRequest.responseText + ")");
                alert(result.errorObject.errorText);
            }
            $(obj).removeClass("notlog-btn").text("注册");
        }
    });

    return true;
};


//手机验证码倒计时
var t;
var mobileCode;
function timeOut() {
    t -= 1;
    if(t!=0){
        $("#mobileCodeSpan").html("<a href='javascript:;'class='yzm-btn' >" +t+ "秒后可重发</a>");
        /*$("#mobileCodeSpan").text(t + "秒");*/
    }else{
        mobileCode=null;

        $("#mobileCodeSpan").html("<a href='javascript:;' class='yzm-btn'  onclick='return getMobileCode()' id='mobileCodeBtn'>发送验证码</a>");
        return;
    }
    //每秒执行一次,showTime()
    setTimeout("timeOut()",1000);
}

/**
 * 手机号检查（空和手机号合法性、是否存在验证）*/
var checkUserMobileValidate = function(){
    var mobile = $("#userMobile").val();
    if(!isNotNull(mobile)){
        showTip("#mobileTip","请输入手机号！");
        return false;
    }
    //var reMobile=/^13[0-9]{9}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}$/;
    var reMobile=/^\d{11}$/;
    if(!reMobile.test(mobile)){
        showTip("#mobileTip","手机号码格式有误，请输入正确的手机号！");
        return false;
    }
    if(!checkUserMobile(mobile)){
        return false;
    }
    return true;
};

var mobileCode = false;
var checkNextstep = function(){
    //验证手机号是否存在
    if(!checkUserMobileValidate()){
        return false;
    }
    $("#regType").val("mobile");
    if("mobile" == $("#regType").val()) {
        checkMobileCode();
        if(!mobileCode) {
            return;
        }
    }
    if(mobileCode){
        $(".main01").hide();
        $(".main02").show();
    }
};

var isAgree = false;
var checkAgree = function (){
    if (isAgree) {
        $(".all-sel").removeClass("selected");
        isAgree = false;
    } else {
        $(".all-sel").addClass("selected");
        isAgree = true;
    }
};

var changeUserName = function (){
    var nick = $("#userName").val();
    if(!isNotNull(nick)){
        showTip("#userNameTip","请输入用户名称！");
        return false;
    }
    if(nick.length < 2){
        showTip("#userNameTip","用户名称长度要大于2");
        return false;
    }
    showSuccess("#userNameTip");
    return true;
};
/**
 * 检查密码（空、长度和密码强度验证）*/
var checkPsw = function(){
    var userPsw = $("#userPsw").val();
    if(!isNotNull(userPsw)){
        showTip("#userPswTip","请输入密码！");
        return false;
    }
    if(userPsw.length < 6 || userPsw.length > 16){
        showTip("#userPswTip","密码为6-16位数字、字母或符号！");
        return false;
    }
    showSuccess("#userPswTip");
    return true;
};
/**
 * 检查重复密码（长度和密码一致验证）*/
var cheCkcheckPsw = function(){
    var checkPassword = $("#checkPassword").val();
    if(!isNotNull(checkPassword)){
        showTip("#checkPasswordTip","请再次输入密码！");
        return false;
    }
    if(checkPassword.length < 6 || checkPassword.length > 16){
        showTip("#checkPasswordTip","确认密码为6-16位数字、字母或符号！");
        return false;
    }
    if(!checkPasswordValidate()){/*确认密码长度及一致验证*/
        return false;
    }
    return true;
};
/**
 * 密码和重复密码一致性验证*/
var checkPasswordValidate = function(){
    var userPsw = $("#userPsw").val();
    var checkPassword = $("#checkPassword").val();
    if(userPsw != checkPassword){
        showTip("#checkPasswordTip","两次输入密码不一致，请再次输入密码！");
        return false;
    }else{
        showSuccess("#checkPasswordTip");
    }
    return true;
};





//判断是否为空
function isNotNull(value){
    return value!=undefined&&value!=""&&value!=null;
}
var showTip = function(tipId,errorText){
    var tips = $(tipId);
    tips.html(errorText);
    tips.show();
};
var showSuccess = function(tipId){
    $(tipId).hide();
};
