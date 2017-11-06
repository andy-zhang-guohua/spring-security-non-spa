package andy.backyard.common.exception;

/**
 * 0-9999 之间的错误码定义
 * Created by ZhangGuohua on 2017/09/27.
 */
public interface CommonErrorCode {
    int SUCCESS = 0; // 成功时的错误码定义为0
    int NULL_PARAMETER = 1001;//空参数
    int INVALID_PARAMETER = 1002; // 参数无效
    int FEATURE_NOT_SUPPORTED = 1003;  // 功能缺失或者尚未提供
    int MISSING_DATA_OR_ATTRIBUTE = 1004;//数据或者属性缺失或者尚未提供
    int DUPLICATE_KEYS = 1005;// 键值重复
    int STATE_NOT_READY = 1006;// 目标数据或者属性状态不符合操作条件
    int OPERATION_NOT_ALLOWED = 1007; // 该操作被禁止
    int ERROR_TRANSFORMING_DATA_OBJECT = 1008; // 数据转换错误
    int ERROR_INSERT_RECORD = 1009;// 往数据库插入记录错误
    int TARGET_NOT_FOUND = 1010; // 目标未找到

    //// 安全认证授权相关
    int NOT_AUTHENTICATED = 1150; // 访问者未认证
    int ERROR_LOGGING_IN = 1151; // 登录失败
    int ACCESS_DENIED = 1151; // 访问被拒绝

    int USER_NOT_FOUND = 1100;
    int USER_FROZEN = 1101;
    int USER_PASSWORD_ERROR = 1102;
    int ERROR_ADD_USER = 1103;
    int ERROR_UPDATE_USER = 1104;

    int ADMIN_NOT_FOUND = 1200;
    int ADMIN_LOCKED = 1201; // 管理员账号被锁定
    int ADMIN_DISABLED = 1202; // 管理员账号被禁用
    int ADMIN_PASSWORD_ERROR = 1203;
    int ERROR_ADD_ADMIN = 1204;
    int ERROR_UPDATE_ADMIN = 1205;

    int ADMIN_ROLE_NOT_FOUND = 1210;
    int ERROR_ADD_ADMIN_ROLE = 1211;
    int ERROR_UPDATE_ADMIN_ROLE = 1212;
    int ERROR_DELETE_ADMIN_ROLE = 1213;

    int ERROR_SENDING_SMS = 1300;
    int SEND_SMS_CAPTCHA_ERROR = 1301;
    int VERIFY_SMS_CAPTCHA_ERROR = 1302;

    int ERROR_PUT_MESSAGE_TO_QUEUE = 1700;
    int ERROR_POP_MESSAGE_FROM_QUEUE = 1701;


    int COMMON = 1000; // 一般错误,程序员已经理解此问题，但是认为没有必要对该错误做细分
    int UNKNOWN = 9999;// 未知错误(遇到该种错误表示程序员需要分析和调整代码)或者未知状态(表示最终结果尚未出现)
}
