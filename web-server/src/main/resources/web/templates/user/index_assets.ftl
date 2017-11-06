<#include "../functional_page_layout/layout.ftl">

<#macro main_area_impl>
       <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                当前工作区主标题
                <small>副标题</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">当前功能名称</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content">
            <div>用户资产</div>
        </section>
        <!-- /.content -->
</#macro>

<@layout main_area=main_area_impl>
</@layout>