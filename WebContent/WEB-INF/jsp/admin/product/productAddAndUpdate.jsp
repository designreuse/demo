<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close ngdialog-custom-close" data-dismiss="modal" aria-label="Close"><span class="ngdialog-custom-close" aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增用户角色</h4>
      </div>
      <form  w5c-form-validate="validateOptions" novalidate name="validateForm" id="validateForm" class="form-horizontal  w5c-form">
        <div class="modal-body">
          <input type="hidden" name="id" id="id" >
          <div class="form-group">
            <label class="control-label col-md-3" for="alterSysRoleNm"><span style='color:red'>*</span>&nbsp;角色名称：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="sysRoleNm" ng-model="obj.sysRoleNm"  required ng-minlength="1" ng-maxlength="128" id="alterSysRoleNm" >
            </div>
          </div>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="control-label col-md-3" for="alterPosition"><span style='color:red'>*</span>&nbsp;排序：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="position" ng-model="obj.position"  required ng-minlength="1" ng-maxlength="128" id="alterPosition" >
            </div>
          </div>
        </div>
      <div class="modal-footer">
        <button type="submit"  onclick="addSysRoleForm()" value="Submit" class="btn btn-primary addSysRoleForm">保存</button>
        <button type="submit"  onclick="updateSysRoleForm()" value="Submit" class="btn btn-primary updateSysRoleForm" style="display: none;">修改</button>
        <button type="button" class="btn btn-default ngdialog-custom-close" data-dismiss="modal">关闭</button>
      </div>
    </form>
  </div>
</div>