// 结束 no-fouc 控制
(function ($) {
    'use strict';
    // 页面基础框架渲染完成后去掉 no-fouc 属性 : 页面处于不可见状态
    // 这句话应该放在所有页面渲染逻辑之后
    $('.no-fouc').removeClass('no-fouc');
})(jQuery);