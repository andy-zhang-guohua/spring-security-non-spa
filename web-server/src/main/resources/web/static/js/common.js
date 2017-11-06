// 为javascript String 对象添加 endWith 方法
String.prototype.endWith = function (s) {
    if (s == null || s == "" || this.length == 0 || s.length > this.length)
        return false;

    if (this.substring(this.length - s.length) == s)
        return true;
    else
        return false;
};

(function ($) {
    'use strict';
    // 左侧功能导航栏中隐藏不包含功能菜单项的功能分类项
    var hideEmptyFunctionNavigationCategoryInSidebar = function () {
        $("ul.treeview-menu").each(function () {
            // this is the single ul tag contained by one function category,
            // it supposed to contains zero or more function menu items,
            // when it contains zero function menu item, this function category,
            // which is the parent container of this ul tag, should be hidden.
            var $functionMenuItems = $(this).children('li');
            var $countFunctionMenuItems = $functionMenuItems.length;
            //alert($countFunctionMenuItems)
            if ($countFunctionMenuItems == 0) {
                var $functionCategory = $(this).parent();
                //$functionCategory.addClass("am-hide");
                $functionCategory.remove();
            }
        });
    }

    // 左侧功能导航栏中当前主功能区对应菜单项的选中处理
    var selectCurrentFunctionMenuItemInSidebar = function () {
        var $url = window.location.href;     // Returns full URL


        var endOfURL = $url.indexOf("?");
        if (endOfURL >= 0) {
            // remove the query string part in the URL (?a=1&b=2 will be removed)
            $url = $url.substring(0, endOfURL);
        }

        $("ul.treeview-menu li").each(function () {
            // this li, containing just only one single tag a ,is the function menu item to be highlighted or not based on the current url of the page;
            // the parent of this tag , a ul is the container of all function menu items of current function category, when one function menu li is current highlighted,
            // the parent ul should be visible by adding class am-in to it
            var $a = $(this).find('a');// the function menu item text container
            var $href = $a.attr("href");

            var $hit = ($url.indexOf($href + "/") >= 0 ) || $url.endWith($href) || ($url.indexOf($href + "#") >= 0 );// if current page url matches the url of current function menu item or not
            if ($hit) {
                var $li = $(this).parent().parent();
                $li.addClass("menu-open"); // make visible the parent of this tag , a ul is the container of all function menu items of current function category

                $li.addClass("active");
                $(this).addClass("active")
            }
        });
    };


    // 渲染左侧功能导航区域 函数定义
    var renderingFunctionNavigationInSidebar = function () {
        // hide empty function navigation categories
        hideEmptyFunctionNavigationCategoryInSidebar();

        //侧边栏选中高亮
        selectCurrentFunctionMenuItemInSidebar();
    };

    // 渲染左侧功能导航区域
    renderingFunctionNavigationInSidebar();
})(jQuery);