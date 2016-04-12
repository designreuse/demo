$(document).ready(function(){
    var $table = $('#userList');
    var $button_add = $('#btn_add');
    var $button_edit = $('#btn_edit');
    $button_add.click(function(){
        $("#showSysUserModal").removeData("modal");
        $('#showSysUserModal').modal('show');
        $("#addSysUserForm").show();
        $("#updateSysUserForm").hide();

        $(".modal-title").html("添加用户");
        $("#id").val("");
        $("#alterLoginId").val("");
        $("#alterUserName").val("");
        $("#alterUserPsw").val("");
        $("#alterUserMobile").val("");
        $("#alterUserEmail").val("");
    });



    $button_edit.click(function () {
        if(!$table.bootstrapTable('getSelections')[0]){
            alert('请选择一行数据！');
            return;
        }
        var id =  $table.bootstrapTable('getSelections')[0].id;

        $.ajax({
            type:"POST",
            url:webPath.webRoot+"/sysUser/findOne.json",
            data:{id:id},
            dataType: "json",
            async: false,//同步
            success:function(data) {
                var s = 1;
                $('#showSysUserModal').modal('show');
                $("#addSysUserForm").hide();
                $("#updateSysUserForm").show();

                $(".modal-title").html("修改用户");
                $("#id").val(data.id);
                $("#alterLoginId").val(data.loginId);
                $("#alterUserName").val(data.userName);
                $("#alterUserPsw").val(data.userPsw);
                $("#alterUserMobile").val(data.userMobile);
                $("#alterUserEmail").val(data.userEmail);
                $("input[name='userSexCode']").each(function(){
                    if($(this).val()==data.userSexCode){
                        $(this).attr("checked",true);
                    }
                });
                $("input[name='isDelete']").each(function(){
                    if($(this).val()==data.isDelete){
                        $(this).attr("checked",true);
                    }
                })
            },
            error:function(XMLHttpRequest, textStatus) {
                alert(result.jsonError.errorText);
            }
        });
    });
    var btn_delete = $('#btn_delete');

    btn_delete.click(function () {
        if(!$table.bootstrapTable('getSelections')[0]){
            alert('请选择一行数据！');
            return;
        }
        var id =  $table.bootstrapTable('getSelections')[0].id;
        alert(id);
        //$.ajax({
        //    type:"POST",
        //    url:webPath.webRoot+"/sysUser/addSysUser.json",
        //    data:params,
        //    dataType: "json",
        //    async: false,//同步
        //    success:function(data) {
        //        if (data.success == true) {
        //            $("userList").bootstrapTable('refresh', {url: webPath.webRoot+'/sysUser/findUserList.json'});
        //        }
        //        else{
        //            setTimeout(function(){
        //                window.location.href=webPath.webRoot+"/registerSuccess.ac";
        //            },1)
        //
        //        }
        //    },
        //    error:function(XMLHttpRequest, textStatus) {
        //        alert(result.jsonError.errorText);
        //    }
        //});

    });
});
var userSexCode = function(value,rows){
    switch(value) {
        case '0':
            return '男';
        case '1':
            return '女';
        case '2':
            return '保密';
    }
};
var isAdmin = function(value,rows){
    switch(value) {
        case 'Y':
            return '是';
        case 'N':
            return '否';
    }
};