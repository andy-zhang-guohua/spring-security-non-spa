<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>平台管理</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet"
          href="/static/AdminLTE-2.4.2/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet"
          href="/static/AdminLTE-2.4.2/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/static/AdminLTE-2.4.2/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/AdminLTE-2.4.2/dist/css/AdminLTE.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="/static/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="/static/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">请登录</p>

        <form id="formLogin" action="login" method="post">
            <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group has-feedback">
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名或者手机号">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" class="form-control" id="rawPassword" name="rawPassword" placeholder="密码">
                <input type="hidden" id="password" name="password" value=""/>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <!-- /.col -->
                <div class="col-xs-4">
                    <button id="buttonLogin" type="button" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
                <!-- /.col -->
            </div>
        </form>

        <!--a href="#">I forgot my password</a--><br>
    <#if RequestParameters['error']??>
        <div class="alert alert-danger">
        ${errorMessage!}
        </div>
    <#else>
        <div class="alert alert-danger hidden">
        </div>
    </#if>
    </div>
    <!-- /.login-box-body -->
</div>
<!-- /.login-box -->

<!-- jQuery 3 -->
<script src="/static/AdminLTE-2.4.2/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/AdminLTE-2.4.2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!--crypto-js 3.1.6-->
<script src="/static/crypto-js-3.1.6/crypto-js.js"></script>
<script>
    $(function () {
                var $username = $('#username');
                var $rawPassword = $('#rawPassword');
                var $shaPassword = $('#password');
                var $buttonLogin = $('#buttonLogin');
                var $formLogin = $('#formLogin');
                var $alert = $('.alert');

                var init = function () {
                    $username.bind('keyup', function (evt) {
                        var et = evt || window.event;
                        inputFocus(et);
                    });
                }

                $rawPassword.bind('keyup', function (evt) {
                    var et = evt || window.event;
                    inputFocus(et);
                });

                $buttonLogin.bind('click', function () {
                    if ($username.val() == '') {
                        $alert.removeClass("hidden");
                        $alert.html('请输入用户名或者手机号！');
                    }
                    else if ($rawPassword.val() == '') {
                        $alert.removeClass("hidden");
                        $alert.html('请输入密码！');
                    }
                    else {
                        var shaPassword = CryptoJS.SHA256($rawPassword.val()).toString(CryptoJS.enc.Base64);
                        $shaPassword.val(shaPassword);
                        $formLogin.submit();
                    }
                });

                function inputFocus(et) {
                    var keyCode = et.keyCode;
                    if (keyCode == 13) {
                        if ($username.val() == '') {
                            $username.get(0).focus();
                        }
                        else if ($rawPassword.val() == '') {
                            $rawPassword.get(0).focus();
                        }
                        else {
                            $formLogin.submit();
                        }
                    }
                };


                init();
            }
    );
</script>
</body>
</html>
