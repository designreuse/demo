$(document).ready(function(){
    //$('#userList').DataTable();
    //$('#btn_add').click(function(){
    //    alert("s")
    //})
});

var addSysRoleForm = function(){
    var params = $("#validateForm").formToArray();
    $.ajaxSettings['contentType'] = "application/x-www-form-urlencoded; charset=utf-8;";
    $.ajax({
        type:"POST",
        url:webPath.webRoot+"/sysRole/saveOrUpdateSysRole.json",
        data:params,
        dataType: "json",
        async: false,//同步
        success:function(data) {
            if (data.success == true) {
                $("#roleList").bootstrapTable('refresh', {url: webPath.webRoot+'/sysUser/findUserList.json'});
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
        url:webPath.webRoot+"/sysRole/saveOrUpdateSysRole.json",
        data:params,
        dataType: "json",
        async: false,//同步
        success:function(data) {
            if (data.success == true) {
                $("#roleList").bootstrapTable('refresh', {url: webPath.webRoot+'/sysUser/findUserList.json'});
            }
            else{

            }
        },
        error:function(XMLHttpRequest, textStatus) {
            alert(result.jsonError.errorText);
        }
    });
};
