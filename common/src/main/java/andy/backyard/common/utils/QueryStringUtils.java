package andy.backyard.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ZhangGuohua on 2017/4/1.
 */
public final class QueryStringUtils {
    public static final String ENCODING = "UTF-8";

    private QueryStringUtils() {
    }


    public static String buildQueryString(Map<String, Object> parameters) {
        StringBuilder sb = new StringBuilder();
        parameters.entrySet().stream().forEach(entry -> {
            String key = entry.getKey();
            String value = entry.getValue() == null ? "" : entry.getValue().toString();
            sb.append(key + "=" + value);
            sb.append("&");
        });
        return sb.toString();
    }

    public static String buildAndEncodeQueryString(Map<String, Object> parameters) {
        return encodeQueryString(buildQueryString(parameters));
    }

    public static String encodeQueryString(String rawQueryString) {
        try {
            return URLEncoder.encode(StringUtils.defaultString(rawQueryString), ENCODING);
        } catch (UnsupportedEncodingException e) {
            return rawQueryString;
        }
    }

    public static String decodeQueryString(String encodedQueryString) {
        try {
            return URLDecoder.decode(StringUtils.defaultString(encodedQueryString), ENCODING);
        } catch (UnsupportedEncodingException e) {
            return encodedQueryString;
        }
    }


    public static Map<String, Object> parseQueryStringParameters(String queryString) {
        Map<String, Object> parameters = new LinkedHashMap<>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            if (org.apache.commons.lang3.StringUtils.isBlank(pair))
                continue;

            int idx = pair.indexOf("=");
            if (idx >= 0) {
                String name = pair.substring(0, idx).trim();
                String value = pair.substring(idx + 1).trim();
                parameters.put(name, value);
            } else {
                parameters.put(pair, "");
            }
        }
        return parameters;
    }
}
