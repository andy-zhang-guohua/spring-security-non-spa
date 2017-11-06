package andy.backyard.common.utils;

import andy.backyard.common.exception.ApplicationException;

/**
 * Created by ZhangGuohua on 2017/5/25.
 */
public class BeanUtils {
    private BeanUtils() {

    }

    public static void copyProperties(Object dest, Object orig) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, orig);
        } catch (Exception e) {
            throw new ApplicationException("Java Bean 属性复制异常", e);
        }
    }
}
