<!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar">
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu tree" data-widget="tree">
        <li class="header">功能导航</li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-pie-chart"></i>
                <span>管理员管理</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
            </a>
            <ul class="treeview-menu ">
            <@security.authorize  access="hasAuthority('VIEW_ADMINS')">
                <li><a href="/admin/admins"><i class="fa fa-circle-o"></i> 管理员账号管理</a></li>
            </@security.authorize>
            <@security.authorize  access="hasAuthority('VIEW_ADMIN_ROLES')">
                <li><a href="/admin/roles"><i class="fa fa-circle-o"></i> 管理员角色管理</a></li>
            </@security.authorize>
            </ul>
        </li>


        <li class="treeview">
            <a href="#">
                <i class="fa fa-pie-chart"></i>
                <span>用户管理</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
            </a>
            <ul class="treeview-menu">
            <@security.authorize  access="hasAuthority('VIEW_USERS')">
                <li><a href="/customer/users"><i class="fa fa-circle-o"></i> 用户账号管理</a></li>
            </@security.authorize>
            <@security.authorize  access="hasAuthority('VIEW_USER_ASSETS')">
                <li><a href="/customer/assets"><i class="fa fa-circle-o"></i> 用户资产管理</a></li>
            </@security.authorize>
            </ul>
        </li>

        <li class="treeview">
            <a href="#">
                <i class="fa fa-pie-chart"></i>
                <span>系统配置管理</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
            </a>
            <ul class="treeview-menu">

            </ul>
        </li>
    </ul>
</section>
