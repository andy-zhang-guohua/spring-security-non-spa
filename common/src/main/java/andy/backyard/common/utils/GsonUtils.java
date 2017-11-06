package andy.backyard.common.utils;

import andy.backyard.common.exception.ApplicationException;
import andy.backyard.common.exception.CommonErrorCode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;

;

/**
 * Created by ZhangGuohua on 2017/4/13.
 */
public final class GsonUtils {
    private GsonUtils() {
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        if (StringUtils.isBlank(json))
            throw new ApplicationException("空参数无法转换为对象", CommonErrorCode.NULL_PARAMETER);

        try {
            return new Gson().fromJson(json, valueType);
        } catch (JsonSyntaxException e) {
            throw new ApplicationException(e);
        }
    }

    public static <T> T toObject(String json, Type typeOfT) {
        if (StringUtils.isBlank(json))
            throw new ApplicationException("空参数无法转换为对象", CommonErrorCode.NULL_PARAMETER);

        try {
            return newGson().fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            throw new ApplicationException(e);
        }
    }

    private static Gson newGson() {
        return new GsonBuilder().disableHtmlEscaping().create();
    }

    public static String toString(Object src) {
        return newGson().toJson(src);
    }
}
