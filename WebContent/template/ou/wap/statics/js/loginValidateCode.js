/**
 * Created by IntelliJ IDEA.
 * User: lzp
 * Date: 11-7-12
 * Time: 下午7:05
 * 登录验证插件
 */
(function($){
    $.fn.login=function(settings){
        var defaultSettings={
            loginId:{
                id:"loginId",
                alert:{
                    emptyAlert:"请输入用户名！"
                }

            },
            userPsw:{
                id:"userPsw",
                alert:{
                    emptyAlert:"请输入密码！"
                }

            },
            validateCode:{
                id:"validateCode",
                alert:{
                    emptyAlert:"请输入验证码！"
                }

            },
            contextPath:"",
            url:"/member/loginValidateCode.json"

        };
        settings = $.extend(true,defaultSettings,settings);
        return this.each(function(){
            $(this).bind("submit",function(){
                return checkLoginForm(settings)
            })

        });
        function checkLoginForm(settings){
            var loginId = $("#"+settings.loginId.id);
            var userPsw = $("#"+settings.userPsw.id);
            var validateCode = $("#"+settings.validateCode.id);
            if(loginId.val() == ""){
                $("#alert").show();
                $("#alerttext").html(settings.loginId.alert.emptyAlert);

                return false;
            }
            if(userPsw.val() == ""){
                $("#alert2").show();
                $("#alerttext1").html(settings.userPsw.alert.emptyAlert);
                return false;
            }
            if($("#validateCodeField").css("display")=='block'&&!$("#validateCode").val()){
                if(validateCode.val() == ""){
                    $("#alert3").show();
                    $("#alerttext2").html(settings.validateCode.alert.emptyAlert);
                    return false;
                }
            }
            confirmLogin(loginId,userPsw,validateCode);
            return false;
        }
        function confirmLogin(loginId,userPsw,validateCode){
            //非空验证结束
            //var params = $("#loginForm").formToArray();
            $.ajaxSettings['contentType'] = "application/x-www-form-urlencoded; charset=utf-8";
            $.ajax({
                type:"POST",
                url:settings.contextPath+settings.url,
                //data:{loginId:loginId.val(),userPsw:$.md5(userPsw.val()),validateCode:validateCode.val(),dicuzPsw:userPsw.val()},
                data:{loginId:loginId.val(),userPsw:userPsw.val(),validateCode:validateCode.val(),dicuzPsw:userPsw.val()},
                dataType: "json",
                success:function(data) {

                    if(isNotNull(data)) {
                        location.href =settings.contextPath+"/template/ou/wap/index.jsp?sysUserId="+data.id ;
                    } else{
                        $("#alert2").show();
                        $("#alerttext").html("<span style='color: #fff'>密码或账号错误！<span>")
                    }

                },
                error:function(XMLHttpRequest, textStatus) {
                    if (XMLHttpRequest.status == 500) {
                        var result = eval("(" + XMLHttpRequest.responseText + ")");
//                                "{\"errorObject\":{\"errorData\":null,\"errorCode\":\"errors.login.userstat\",\"errorText\":\"用户{0}的状态不正常\"}}"
                        checkLoginResult(result.errorObject);
                    }
                }
            });

        }

        function showDialog(text,buttons){
            $("#tiptext").html(text);
            $("#tip").dialog({
                buttons:buttons
            });

        }
    };
})(jQuery);
//判断是否为空
function isNotNull(value){
    return value!=undefined&&value!=""&&value!=null;
}