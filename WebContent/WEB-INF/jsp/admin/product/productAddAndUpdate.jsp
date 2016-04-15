<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css">
  
</style>
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close ngdialog-custom-close" data-dismiss="modal" aria-label="Close"><span class="ngdialog-custom-close" aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">新增商品</h4>
      </div>
      <form  w5c-form-validate="validateOptions" novalidate name="validateForm" id="validateForm" class="form-horizontal  w5c-form">
        <div class="modal-body">
          <input type="hidden" name="id" id="id" >
          <div class="form-group">
            <label class="control-label col-md-3" for="alterProductNm"><span style='color:red'>*</span>&nbsp;商品名称：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="productNm" ng-model="obj.productNm"  required ng-minlength="1" ng-maxlength="128" id="alterProductNm" >
            </div>
          </div>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="control-label col-md-3" for="alterSellingPoint"><span style='color:red'>*</span>&nbsp;卖点：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="sellingPoint" ng-model="obj.sellingPoint"  required ng-minlength="1" ng-maxlength="128" id="alterSellingPoint" >
            </div>
          </div>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="control-label col-md-3" for="alterProductCoding"><span style='color:red'>*</span>&nbsp;商品编码：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="productCoding" ng-model="obj.productCoding"  required ng-minlength="1" ng-maxlength="128" id="alterProductCoding" >
            </div>
          </div>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="control-label col-md-3" for="alterProductTag"><span style='color:red'>*</span>&nbsp;商品标签：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="productTag" ng-model="obj.productTag"  required ng-minlength="1" ng-maxlength="128" id="alterProductTag" >
            </div>
          </div>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="control-label col-md-3" for="alterMarketPrice"><span style='color:red'>*</span>&nbsp;市场价：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="marketPrice" ng-model="obj.marketPrice"  required ng-minlength="1" ng-maxlength="128" id="alterMarketPrice" >
            </div>
          </div>
        </div>
        <div class="form-group">
          <label class="control-label col-md-3"><span style='color:red'>*</span>&nbsp;是否销售：</label>
          <div class="col-md-6">
            <label class="radio-inline">
              <input  type="radio" name="isOnSale"  value="Y"  checked="true"> 上架
            </label>
            <label class="radio-inline">
              <input type="radio" name="isOnSale"   value="N"> 下架
            </label>
          </div>
        </div>
        <%--<div class="form-group">--%>
          <%--<label class="control-label col-md-3"><span style='color:red'>*</span>&nbsp;是否分期：</label>--%>
          <%--<div class="col-md-6">--%>
            <%--<label class="radio-inline">--%>
              <%--<input  type="radio" name="isInstallment"  value="Y"  checked="true"> 是--%>
            <%--</label>--%>
            <%--<label class="radio-inline">--%>
              <%--<input type="radio" name="isInstallment"   value="N"> 否--%>
            <%--</label>--%>
          <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
          <%--<label class="control-label col-md-3"><span style='color:red'>*</span>&nbsp;是否支持货到付款：</label>--%>
          <%--<div class="col-md-6">--%>
            <%--<label class="radio-inline">--%>
              <%--<input  type="radio" name="isSupportCod"  value="Y"  checked="true"> 支持--%>
            <%--</label>--%>
            <%--<label class="radio-inline">--%>
              <%--<input type="radio" name="isSupportCod"   value="N"> 不支持--%>
            <%--</label>--%>
          <%--</div>--%>
        <%--</div>--%>
        <div class="modal-body">
          <div class="form-group">
            <label class="control-label col-md-3" for="alterPosition"><span style='color:red'>*</span>&nbsp;排序：</label>
            <div class="col-md-6">
              <input class="form-control" type="text" name="position" ng-model="obj.position"  required ng-minlength="1" ng-maxlength="128" id="alterPosition" >
            </div>
          </div>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label class="control-label col-md-3" for="alterPosition"><span style='color:red'>*</span>&nbsp;产品图片：</label>
            <div class="col-md-6 picture">
              <ul class="mc">
                <li style="display:none;">
                  <input type="hidden" name="photoFileId" id="photoFileId" value="" />
                  <img id="upload_pic" src="" alt="">
                </li>
                <li>
                  <a href="javascript:;" class="add" id="upLoad_btn"></a>
                </li>
              </ul>
            </div>
          </div>
        </div>

      <div class="modal-footer">
        <button type="submit"  onclick="addSysRoleForm()" value="Submit" class="btn btn-primary addSysRoleForm">保存</button>
        <button type="submit"  onclick="updateSysRoleForm()" value="Submit" class="btn btn-primary updateSysRoleForm" style="display: none;">修改</button>
        <button type="button" class="btn btn-default ngdialog-custom-close" data-dismiss="modal">关闭</button>
      </div>
    </form>

      <form enctype="multipart/form-data" id="upload">
        <input type="file" name="imageFile" id="tmpFile" style="display: none" onchange="ajaxFileUpload();"/>
        <input type="hidden" value=""/>
      </form>
  </div>
</div>