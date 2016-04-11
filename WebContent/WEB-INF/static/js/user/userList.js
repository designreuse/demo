$(document).ready(function(){
    var $table = $('#userList');
    var $button = $('#btn_edit');

    $button.click(function () {
        alert('获取的ID: ' + JSON.stringify($table.bootstrapTable('getSelections')));
    });
    var btn_delete = $('#btn_delete');

    btn_delete.click(function () {
        if(!$table.bootstrapTable('getSelections')[0]){
            alert('请选择一行数据！');
            return;
        }
        var id =  $table.bootstrapTable('getSelections')[0].id;
        alert(id)

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