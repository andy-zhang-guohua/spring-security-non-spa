<a href="#" class="dropdown-toggle" data-toggle="dropdown">
    <img src="/static/AdminLTE-2.4.2/dist/img/user2-160x160.jpg" class="user-image" alt="用户头像">
    <span class="hidden-xs"> ${user.username!}</span>
</a>
<ul class="dropdown-menu">
    <!-- User image -->
    <li class="user-header">
        <img src="/static/AdminLTE-2.4.2/dist/img/user2-160x160.jpg" class="img-circle"
             alt="用户头像">

        <p>
        ${user.name!}
            <small>登录时间 : ${loginTime!}</small>
        </p>
    </li>
    <!-- Menu Body -->
    <li class="user-body">
        <div class="row">
            <div class="col-xs-4 text-center">
                <a href="#"></a>
            </div>
            <div class="col-xs-4 text-center">
                <a href="#"></a>
            </div>
            <div class="col-xs-4 text-center">
                <a href="#"></a>
            </div>
        </div>
        <!-- /.row -->
    </li>
    <!-- Menu Footer-->
    <li class="user-footer">
        <div class="pull-left">
            <a href="#" class="btn btn-default btn-flat">个人信息</a>
        </div>
        <div class="pull-right">
            <a href="/logout" class="btn btn-default btn-flat">退出登录</a>
        </div>
    </li>
</ul>