<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/jsp/b2c/common/taglibs.jsp"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  application.setAttribute("basePath",basePath);
%>
<html>
<head>
  <base href="${basePath}">
  <title>会员列表</title>
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="${ctx}static/adminLTE/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="${ctx}static/adminLTE/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="${ctx}static/adminLTE/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="${ctx}static/adminLTE/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
  <script src="${ctx}static/adminLTE/plugins/jQuery/jQuery-2.2.0.min.js"></script>
  <!-- Bootstrap 3.3.5 -->
  <script src="${ctx}static/adminLTE/bootstrap/js/bootstrap.min.js"></script>
  <script src="${ctx}static/adminLTE/dist/js/app.min.js"></script>
  <script src="${ctx}static/js/user/userList.js"></script>

  <script src="${ctx}static/adminLTE/plugins/datatables/jquery.dataTables.min.js"></script>
  <script src="${ctx}static/adminLTE/plugins/datatables/dataTables.bootstrap.min.js"></script>
  <script src="${ctx}static/adminLTE/plugins/slimScroll/jquery.slimscroll.min.js"></script>
  <script src="${ctx}static/adminLTE/plugins/fastclick/fastclick.js"></script>
  <script src="${ctx}static/adminLTE/plugins/bootstrap-table/bootstrap-table.js"></script>
  <script src="${ctx}static/adminLTE/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>


  <link rel="stylesheet" href="${ctx}static/adminLTE/plugins/datatables/dataTables.bootstrap.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
  <jsp:include page="../top.jsp"/>
  <jsp:include page="../left.jsp"/>
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Data Tables
        <small>advanced tables</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li><a href="#">Tables</a></li>
        <li class="active">Data tables</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Data Table With Full Features</h3>
              <%--<div style="width: 100px"><button class="btn btn-success btn-labeled fa fa-plus">新增</button>--%>
              <%--</div>--%>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div id="toolbar" class="btn-group">
                <button id="btn_add" type="button" class="btn btn-default">
                  <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
                <button id="btn_edit" type="button" class="btn btn-default">
                  <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
                </button>
                <button id="btn_delete" type="button" class="btn btn-default">
                  <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
              </div>
              <table data-toggle="table"
                      id="userList"
                     class="table table-bordered table-striped"
                     data-url="${ctx}/sysUser/findUserList.json"
                     data-search="true"
                     data-click-to-select="true"
                     data-show-refresh="true"
                     data-show-toggle="true"
                     data-side-pagination="server"
                     data-show-columns="true"
                     data-sort-name="id"
                     data-page-list="[5, 10, 10]"
                     data-page-size="10"
                     data-show-export="true"
                     data-query-params-type='limit'
                     data-pagination="true"
                     data-uniqueId="ID"
                      >
                <thead>
                <tr>
                  <th data-field="loginId" data-sortable="true">登陆账号</th>
                  <th data-field="isAdmin" data-sortable="true">是否是管理员</th>
                  <th data-field="userName" data-sortable="true">名称</th>
                  <th data-field="userPsw" data-sortable="true">密码</th>
                  <th data-field="userMobile" data-sortable="true">手机</th>
                  <th data-field="userTel" data-sortable="true">电话</th>
                  <th data-field="userSexCode" data-sortable="true">性别</th>
                  <th data-field="userEmail" data-sortable="true">邮箱</th>
                  <th data-field="registerDate" data-sortable="true">注册时间</th>
                  <th data-field="userIcon" data-sortable="true">头像</th>
                  <th data-field="lastPswModifyTime" data-sortable="true">最后修改密码时间</th>
                  <th data-field="LAST_BUY_TIME" data-sortable="true">最后购买时间</th>
                </tr>
                </thead>

              </table>
            </div>
            <!-- /.box-body -->
          </div>

          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>

  <jsp:include page="../footer.jsp"/>
</div>
</body>
</html>
