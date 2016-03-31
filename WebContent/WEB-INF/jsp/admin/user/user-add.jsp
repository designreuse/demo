<%@ page contentType="text/html;charset=UTF-8" language="java" %>

  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close ngdialog-custom-close" data-dismiss="modal" aria-label="Close"><span class="ngdialog-custom-close" aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增用户</h4>
      </div>
      <form  w5c-form-validate="validateOptions" novalidate name="validateForm" class="form-horizontal  w5c-form">
        <div class="modal-body">

          <div class="form-group">
            <label class="control-label col-md-3" for="alterUserName"><span style='color:red'>*</span>&nbsp;登录账号：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="userName" ng-model="obj.userName" required ng-minlength="1" ng-maxlength="128" id="alterUserName">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterRealName"><span style='color:red'>*</span>&nbsp;真实姓名：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="realName" ng-model="obj.realName" required ng-minlength="1" ng-maxlength="128"  id="alterRealName">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterEmployeeCode"><span style='color:red'>*</span>&nbsp;员工工号：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="employeeCode" ng-model="obj.employeeCode" required  ng-minlength="1" ng-maxlength="32" id="alterEmployeeCode">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterPassword"><span style='color:red'>*</span>&nbsp;密 码：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="password" ng-model="obj.password" required  ng-minlength="1" ng-maxlength="16" id="alterPassword">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3"><span style='color:red'>*</span>&nbsp;性别：</label>
            <div class="col-md-6">
              <label class="radio-inline">
                <input  type="radio"  ng-model="obj.sex"  value="MALE"> 男
              </label>
              <label class="radio-inline">
                <input type="radio"  ng-model="obj.sex" value="FEMALE"> 女
              </label>
              <label class="radio-inline">
                <input type="radio"  ng-model="obj.sex" value="SECRET" ng-checked="true"> 保密
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterEmail"><span style='color:red'>*</span>&nbsp;E-mail：</label>
            <div class="col-md-6">
              <input class="form-control" type="email" name="email" ng-model="obj.email" required ng-minlength="1" ng-maxlength="128" id="alterEmail">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterMobile"><span style='color:red'>*</span>&nbsp;手机号：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="mobile" ng-model="obj.mobile" required ng-minlength="11" ng-maxlength="32" id="alterMobile">
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3">备注：</label>
            <div class="col-md-6">
              <textarea rows="5" style="width: 100%; resize:none;" name="mark" ng-model="obj.mark" id="mark">

              </textarea>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3"><span style='color:red'>*</span>&nbsp;账号状态：</label>
            <div class="col-md-9">
              <label class="radio-inline">
                <input type="radio" ng-model="obj.status"  value="NORMAL"  ng-checked="true"> 正常
              </label>
              <label class="radio-inline">
                <input type="radio" ng-model="obj.status"  value="FREEZE"> 冻结
              </label>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-md-3" for="alterOrderby"><span style='color:red'>*</span>&nbsp;顺序：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" onkeyup="if(/\D/.test(value))value=value.replace(/\D/g,'')" name="orderby" value="0" ng-model="obj.orderby" required  id="alterOrderby">
            </div>
          </div>

        </div>
        <div class="modal-footer">
          <button type="submit" w5c-form-submit="submit()" id="submit" value="Submit" class="btn btn-primary">保存</button>
          <button type="button" class="btn btn-default ngdialog-custom-close" data-dismiss="modal">关闭</button>
        </div>
      </form>
    </div>
  </div>