package andy.backyard.common.model;

import andy.backyard.common.exception.ApplicationException;
import andy.backyard.common.exception.CommonErrorCode;

/**
 * 面向WEB外部的API接口的返回值的统一格式
 * <p>
 * Created by ZhangGuohua on 2016/8/18.
 */
public class ApiResult<T> extends BaseJSONObject {
    private static final long serialVersionUID = 20160818L;

    private T data;
    private String errorMessage;
    private int errorCode;

    private ApiResult() {
        this.data = null;
        errorMessage = "成功";
        errorCode = CommonErrorCode.SUCCESS;
    }

    /**
     * @param data 返回的数据
     * @return
     */
    public static ApiResult success(Object data) {
        ApiResult result = new ApiResult();
        result.setData(data);
        result.setErrorCode(CommonErrorCode.SUCCESS);
        result.setErrorMessage("成功");
        return result;
    }


    public static ApiResult success() {
        ApiResult result = new ApiResult();
        result.setData("");
        result.setErrorCode(CommonErrorCode.SUCCESS);
        result.setErrorMessage("成功");
        return result;
    }


    public static ApiResult fail(int errorCode, String errorMessage) {
        ApiResult result = new ApiResult();
        result.setData(null);
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        return result;
    }

    public static ApiResult fail(ApplicationException e) {
        return fail(e.getErrorCode(), e.getMessage());
    }

    public static ApiResult fail(RuntimeException e) {
        return fail(CommonErrorCode.UNKNOWN, e.getMessage());
    }

    public static ApiResult fail(int errorCode) {
        ApiResult result = new ApiResult();
        result.setData(null);
        result.setErrorCode(errorCode);
        result.setErrorMessage("");
        return result;
    }

    public static ApiResult fail(int errorCode, String errorMessage, Object data) {
        ApiResult result = new ApiResult();
        result.setData(data);
        result.setErrorCode(errorCode);
        result.setErrorMessage(errorMessage);
        return result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String error_message) {
        this.errorMessage = error_message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int error_code) {
        this.errorCode = error_code;
    }

    public boolean isSuccess() {
        return errorCode == CommonErrorCode.SUCCESS;
    }
}
