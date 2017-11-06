<#assign security=JspTaglibs["/META-INF/security.tld"] />
<#macro main_area_blank>
<div></div>
</#macro>

<#macro biz_js_includes_blank>

</#macro>

<#macro layout main_area=main_area_blank biz_js_includes=biz_js_includes_blank>
<!DOCTYPE html>
<html>
<head>
<#include "fragments/head.ftl"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">

        <!-- Logo -->
        <a href="index" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini">X</span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>XXX平台管理界面</b></span>
        </a>

        <!-- Header Navbar -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">功能导航栏开关</span>
            </a>
            <!-- Navbar Right Menu -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                    <#include "fragments/user-menu.ftl"/>
                    </li>
                    <!-- Control Sidebar Toggle Button -->
                    <li>
                        <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
                    </li>
                </ul>
            </div>

        </nav>
    </header>

    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
    <#include "fragments/main-sidebar.ftl"/>
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <@main_area />
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
    <#include "fragments/main-footer.ftl"/>
    </footer>

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
    <#include "fragments/control-sidebar.ftl"/>
    </aside>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->
<#include "fragments/common-js-scripts.ftl"/>
<@biz_js_includes />
<!-- end-no-fouc.js 应该放在所有页面渲染逻辑之后-->
<script src="/static/js/end-no-fouc.js"></script>
</body>
</html>
</#macro>