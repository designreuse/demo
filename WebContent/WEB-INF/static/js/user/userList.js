$(document).ready(function(){
    //$('#userList').DataTable();
    //$('#btn_add').click(function(){
    //    alert("s")
    //})
    var $table = $('#userList');
    var $button = $('#btn_edit');

        $button.click(function () {
            alert('获取的ID: ' + JSON.stringify($table.bootstrapTable('getSelections')));
        });
    var btn_delete = $('#btn_delete');

    btn_delete.click(function () {
        alert('获取的ID: ' + $table.bootstrapTable('getSelections')[0]);
    });





});

angular.module('userAdd', []).controller('userCtrl', function($scope) {

    // process the form
    $scope.processForm = function() {
        $http({
            method  : 'POST',
            url     : dataValue.webRoot+'sysUser/addSysUser.json',
            data    : $.param($scope.formData),  // pass in data as strings
            headers : { 'Content-Type': 'application/x-www-form-urlencoded' }  // set the headers so angular passing info as form data (not request payload)
        })
            .success(function(data) {
                console.log(data);

                if (!data.success) {
                    // if not successful, bind errors to error variables
                    $scope.errorName = data.errors.name;
                    $scope.errorSuperhero = data.errors.superheroAlias;
                } else {
                    // if successful, bind success message to message
                    $scope.message = data.message;
                }
            });
    };

});
var userSexCode = function(value,rows){
    switch(value) {
        case '0':
            return '男';
        case '1':
            return '女';
    }
}
var isAdmin = function(value,rows){
    switch(value) {
        case 'Y':
            return '是';
        case 'N':
            return '否';
    }
}