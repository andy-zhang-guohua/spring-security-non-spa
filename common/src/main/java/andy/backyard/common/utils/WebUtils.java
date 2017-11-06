package andy.backyard.common.utils;

import andy.backyard.common.exception.ApplicationException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ZhangGuohua on 2017/10/13.
 */
public final class WebUtils {
    public static String toString(InputStream is, String charset) {
        try {
            String s = IOUtils.toString(is, charset);
            return s;
        } catch (IOException e) {
            throw new ApplicationException(e);
        }
    }

    public static String getContentType(HttpServletResponse response) {
        if (response == null)
            return "";

        String contentType = response.getContentType();
        return StringUtils.defaultString(contentType);
    }

    public static boolean isJsonResponse(HttpServletResponse response) {
        boolean yes = getContentType(response).contains("json");
        return yes;
    }

}
