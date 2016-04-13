$(document).ready(function(){
    //$('#userList').DataTable();
    //$('#btn_add').click(function(){
    //    alert("s")
    //})
});

var addSysUserForm = function(){

    var params = $("#validateForm").formToArray();
//    params[5]={name:'mobilePhone',value:1000000 };
    $.ajaxSettings['contentType'] = "application/x-www-form-urlencoded; charset=utf-8;";
    $.ajax({
        type:"POST",
        url:webPath.webRoot+"/sysUser/addSysUser.json",
        data:params,
        dataType: "json",
        async: false,//同步
        success:function(data) {
            if (data.success == true) {
                $("userList").bootstrapTable('refresh', {url: webPath.webRoot+'/sysUser/findUserList.json'});
            }
            else{

            }
        },
        error:function(XMLHttpRequest, textStatus) {
                alert(result.jsonError.errorText);
        }
    });
};
var updateSysUserForm = function(){
    var params = $("#validateForm").formToArray();
    $.ajaxSettings['contentType'] = "application/x-www-form-urlencoded; charset=utf-8;";
    $.ajax({
        type:"POST",
        url:webPath.webRoot+"/sysUser/addSysUser.json",
        data:params,
        dataType: "json",
        async: false,//同步
        success:function(data) {
            if (data.success == true) {
                $("userList").bootstrapTable('refresh', {url: webPath.webRoot+'/sysUser/findUserList.json'});
            }
            else{

            }
        },
        error:function(XMLHttpRequest, textStatus) {
            alert(result.jsonError.errorText);
        }
    });
};


///**
// * 检查用户名（为空和是否存在）*/
//var checnLoginId = function(){
//    var bol = false;
//    var loginId = $.trim($("#loginId").val());
//    if(loginId == ""){
//        showTip("#loginIdTip","请输入用户帐号!");
//        return bol;
//    }
//    if(loginId.length < 2 || loginId.length > 20){
//        showTip("#loginIdTip","会员名称在2~20个字之间!");
//        return bol;
//    }
//    //测试登录名重复
//    $.ajaxSettings['contentType'] = "application/json; charset=utf-8;";
//    $.ajax({
//        type:"POST",
//        url:webPath.webRoot+"/member/isExistLoginId.json?loginId="+loginId,
//        dataType: "json",
//        async: false,//同步
//        success:function(data) {
//            if (data.success == false) {
//                /*if(data.errorCode=="errors.login.noexist"){*/
//                /*用户名可用*/
//                showSuccess("#loginIdTip");
//                bol = true;
//                /*   }*/
//            }
//            else{
//                showTip("#loginIdTip","用户名已被他人注册!");
//                bol = false;
//            }
//        },
//        error:function(XMLHttpRequest, textStatus) {
//            if (XMLHttpRequest.status == 500) {
//                var result = eval("(" + XMLHttpRequest.responseText + ")");
//                alert(result.jsonError.errorText);
//                bol = false;
//            }
//        }
//    });
//    return bol;
//};
///**
// * 检查密码（空、长度和密码强度验证）*/
//var checkPsw = function(){
//    var userPsw = $("#userPsw").val();
//
//    /*添加代码*/
//    $("#checkPasswordTip").hide();
//    $(".PasswordTipsBox").show();
//
//    if(userPsw == ""){
//        showTip("#userPswTip","请输入密码!");
//        return false;
//    }
//    if(userPsw.length < 6 || userPsw.length > 16){
//        showTip("#userPswTip","密码为6-16位数字、字母或者符号，建议混合使用!");
//        return false;
//    }
//    showSuccess("#userPswTip");
//    return true;
//};
///**
// * 检查重复密码（长度和密码一致验证）*/
//var cheCkcheckPsw = function(){
//    var checkPassword = $("#checkPassword").val();
//    $(".PasswordTipsBox").hide();
//    if(checkPassword == ""){
//        showTip("#checkPasswordTip","请再次输入密码!");
//        /*添加代码  隐藏密码强度进度条*/
////        $(".PasswordTipsBox").attr("visible",false);
//        $(".PasswordTipsBox").hide();
//        return false;
//    }
//    if(checkPassword.length < 6 || checkPassword.length > 16){
//        showTip("#checkPasswordTip","密码为6-16位数字、字母或者符号，建议混合使用!");
//        return false;
//    }
//    if(!checkPasswordValidate()){/*确认密码长度及一致验证*/
//        return false;
//    }
//    showSuccess("#checkPasswordTip");
//    return true;
//};
///**
// * 密码和重复密码一致性验证*/
//var checkPasswordValidate = function(){
//
//    var userPsw = $("#userPsw").val();
//    var checkPassword = $("#checkPassword").val();
//    if(userPsw != checkPassword){
//        showTip("#checkPasswordTip","两次输入密码不一致，请再次输入密码!");
//        return false;
//    }else{
//        showSuccess("#checkPasswordTip");
//    }
//    return true;
//};
///**
// * 邮箱检查（空和邮箱合法性、是否存在验证）*/
//var checkUserMailValidate = function(){
//    var userEmail = $("#userEmail").val();
//    if(userEmail == ""){
//        showTip("#emailTip","请输入邮箱!");
//        return false;
//    }
//    var reMail=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
//    if(!reMail.test(userEmail)){
//        showTip("#emailTip","请输入有效的E_mail!");
//        return false;
//    }
//    if(!checkUserEmail(userEmail)){
//        return false;
//    }
//    showSuccess("#showTip");
//    return true;
//};