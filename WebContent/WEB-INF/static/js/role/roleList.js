$(document).ready(function(){
    var $table = $('#roleList');
    var $button_add = $('#btn_add');
    var $button_edit = $('#btn_edit');
    $button_add.click(function(){
        $("#showSysRoleModal").removeData("modal");
        $('#showSysRoleModal').modal('show');
        $(".addSysUserForm").show();
        $(".updateSysUserForm").hide();

        $(".modal-title").html("新增用户角色");
        $("#id").val("");
        $("#alterSysRoleNm").val("");
        $("#alterPosition").val("");
    });



    $button_edit.click(function () {
        if(!$table.bootstrapTable('getSelections')[0]){
            alert('请选择一行数据！');
            return;
        }
        var id =  $table.bootstrapTable('getSelections')[0].id;
        $.ajax({
            type:"POST",
            url:webPath.webRoot+"/sysRole/findOne.json",
            data:{id:id},
            dataType: "json",
            async: false,//同步
            success:function(data) {
                $('#showSysRoleModal').modal('show');
                $(".addSysRoleForm").hide();
                $(".updateSysRoleForm").show();

                $(".modal-title").html("修改用户角色");
                $("#id").val(data.id);

                $("#alterSysRoleNm").val(data.sysRoleNm);
                $("#alterPosition").val(data.position);
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
        $.ajax({
            type:"POST",
            url:webPath.webRoot+"/sysRole/delete.json",
            data:{id:id},
            dataType: "json",
            success:function(data) {
                if (data.success == true) {
                    $("#roleList").bootstrapTable('refresh', {url: webPath.webRoot+'/sysRole/findSysRoleList.json'});
                }
                else{

                }
            },
            error:function(XMLHttpRequest, textStatus) {
                alert(result.jsonError.errorText);
            }
        });

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