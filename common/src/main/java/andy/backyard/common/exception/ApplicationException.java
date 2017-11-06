package andy.backyard.common.exception;

import andy.backyard.common.utils.GsonUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * 对业务异常的封装
 * Created by ZhangGuohua on 2017/09/27.
 */
public class ApplicationException extends RuntimeException {
    int errorCode = CommonErrorCode.COMMON;

    public ApplicationException() {
    }

    public ApplicationException(String message, Exception e) {
        super(message, e);
    }

    public ApplicationException(Exception e) {
        super(e);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ApplicationException(int errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public String toJSON() {
        Map<String, Object> map = new TreeMap<>();
        map.put("errorCode", errorCode);
        map.put("message", getMessage());
        return GsonUtils.toString(map);
    }
}
