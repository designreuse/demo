$(document).ready(function(){
    //$('#userList').DataTable();
    //$('#btn_add').click(function(){
    //    alert("s")
    //})
    $("#upLoad_btn").click(function () {
        $("#tmpFile").click();
    });
    var options = {
        type:'post',
        url: webPath.webRoot+'/member/uploadPhoto.json', //用于文件上传的服务器端请求地址
        dataType:"json",
        success: function (result){  //服务器成功响应处理函数
            //var result = eval("("+data+")");
            if(result.success == false) {
                alert("您提交的图片格式不正确");
                return;
            } else if (result.success == true) {
                $("#upload_pic").parent().show();
                $("#photoFileId").attr("value",$.trim(result.fileId));
                $("#upload_pic").attr("src",$.trim(result.url));
                return;
            } else {
                alert("上传失败!");
                return;
            }

        },
        error: function (XMLHttpRequest, textStatus) {
            if (XMLHttpRequest.status == 500) {
                alert("上传失败!");
            }
        }

    };
    $('#upload').submit(function() {
        $(this).ajaxSubmit(options);
        return false;
    });
});

/*上传图片*/
function ajaxFileUpload() {
    $("#upload").submit();
}

var addSysRoleForm = function(){
    var params = $("#validateForm").formToArray();
    $.ajaxSettings['contentType'] = "application/x-www-form-urlencoded; charset=utf-8;";
    $.ajax({
        type:"POST",
        url:webPath.webRoot+"/product/saveOrUpdateProduct.json",
        data:params,
        dataType: "json",
        async: false,//同步
        success:function(data) {
            if (data == success) {
                $("#productList").bootstrapTable('refresh', {url: webPath.webRoot+'/product/findProductList.json'});
            }
            else{

            }
        },
        error:function(XMLHttpRequest, textStatus) {
                alert(result.jsonError.errorText);
        }
    });
};
var updateSysRoleForm = function(){
    var params = $("#validateForm").formToArray();
    $.ajaxSettings['contentType'] = "application/x-www-form-urlencoded; charset=utf-8;";
    $.ajax({
        type:"POST",
        url:webPath.webRoot+"/product/saveOrUpdateProduct.json",
        data:params,
        dataType: "json",
        async: false,//同步
        success:function(data) {
            if (data == success) {
                $("#productList").bootstrapTable('refresh', {url: webPath.webRoot+'/product/findProductList.json'});
            }
            else{

            }
        },
        error:function(XMLHttpRequest, textStatus) {
            alert(result.jsonError.errorText);
        }
    });
};
