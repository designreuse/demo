<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close ngdialog-custom-close" data-dismiss="modal" aria-label="Close"><span class="ngdialog-custom-close" aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增用户</h4>
      </div>
      <form  w5c-form-validate="validateOptions" novalidate name="validateForm" id="validateForm" class="form-horizontal  w5c-form">
        <div class="modal-body">
          <input type="hidden" name="id" id="id" >
          <div class="form-group">
            <label class="control-label col-md-3" for="alterLoginId"><span style='color:red'>*</span>&nbsp;登录账号：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="loginId" ng-model="obj.loginId"  required ng-minlength="1" ng-maxlength="128" id="alterLoginId" >
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterUserName"><span style='color:red'>*</span>&nbsp;真实姓名：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="userName" ng-model="obj.userName" required ng-minlength="1" ng-maxlength="128"  id="alterUserName" >
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterUserPsw"><span style='color:red'>*</span>&nbsp;密 码：</label>
            <div class="col-md-6">
              <input class="form-control" type="password" name="userPsw" ng-model="obj.userPsw" required  ng-minlength="1" ng-maxlength="16" id="alterUserPsw">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3"><span style='color:red'>*</span>&nbsp;性别：</label>
            <div class="col-md-6">
              <label class="radio-inline">
                <input  type="radio" name="userSexCode"  value="0"> 男
              </label>
              <label class="radio-inline">
                <input type="radio" name="userSexCode"   value="1"> 女
              </label>
              <label class="radio-inline">
                <input type="radio" name="userSexCode"  value="2" checked="true"> 保密
              </label>
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-md-3" for="alterUserMobile"><span style='color:red'>*</span>&nbsp;手机号：</label>
          <div class="col-md-6">
            <input class="form-control" type="text" name="userMobile" ng-model="obj.userMobile" required ng-minlength="11" ng-maxlength="32" id="alterUserMobile">
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-md-3" for="alterUserEmail"><span style='color:red'>*</span>&nbsp;邮箱：</label>
          <div class="col-md-6">
            <input class="form-control" type="text" name="userEmail" ng-model="obj.userEmail"  required ng-minlength="1" ng-maxlength="128" id="alterUserEmail" >
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-md-3"><span style='color:red'>*</span>&nbsp;账号状态：</label>
          <div class="col-md-9">
            <label class="radio-inline">
              <input type="radio" name="isDelete" value="N" checked="true"> 正常
            </label>
            <label class="radio-inline">
              <input type="radio"  name="isDelete" value="Y"> 冻结
            </label>
          </div>

        </div>
      <div class="modal-footer">
        <button type="submit"  onclick="addSysUserForm()" value="Submit" class="btn btn-primary addSysUserForm">保存</button>
        <button type="submit"  onclick="updateSysUserForm()" value="Submit" class="btn btn-primary updateSysUserForm" style="display: none;">修改</button>
        <button type="button" class="btn btn-default ngdialog-custom-close" data-dismiss="modal">关闭</button>
      </div>
    </form>
  </div>
</div>