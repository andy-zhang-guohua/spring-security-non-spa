package andy.backyard.common.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by LuoLinjun on 2017/9/6.
 */
@Slf4j
public class Base64Utils {
    public static String encode(byte[] bytes) {
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decode(String base64String) {
        return java.util.Base64.getDecoder().decode(base64String);
    }
}
