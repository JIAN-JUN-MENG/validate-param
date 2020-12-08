package com.github.validate.util;

/**
 * 返回给前台的 错误代码 和 提示
 *
 * @author MENG
 * @version 2018/9/15
 * @see
 */
public enum ValidateCodeEnum {
    //当code == 00000 程序调用成功
    SUCCESS("00000", "SUCCESS"),

    //  公共的错误代码
    REQUIRE_FIELD("00001", "不能为空."),
    LENGTH_ILLEGAL("00002", "长度不合法."),
    EMAIL_ILLEGAL("00003", "邮箱格式不合法."),
    NUMBER_ILLEGAL("00004", "数字格式不合法."),
    DATE_ILLEGAL("00005", "日期格式不合法."),
    PHONE_ILLEGAL("00006", "手机号格式不合法."),
    BOOLEAN_ILLEGAL("00007", "Boolean格式不正确."),
    LIST_ILLEGAL("00008", "数组格式不正确."),
    MODEL_ILLEGAL("00009", "自定义对象格式不正确."),
    REGULAR_ILLEGAL("00010", "不符合正则表达式规则."),
    SPECIAL_OR_REQUIRE_FIELD("00011", "其中一项不能为空."),
    VALIDATE_SIGN_ERROR("00012", "请求参数签名验证错误."),

    /**
     * SPRING 异常提示
     */
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("90001", "[Spring 程序异常(HTTP_MESSAGE_NOT_READABLE_EXCEPTION:400)]JSON格式参数转换错误."),
    TYPE_MISMATCH_EXCEPTION("90002", "[Spring 程序异常(TYPE_MISMATCH_EXCEPTION:400)]类型不匹配异常"),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION("90003", "[Spring 程序异常(HTTP_REQUEST_METHOD_NOT_SUPPORTED_EXCEPTION:405)]不支持HTTP请求方法异常"),
    HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION("90004", "[Spring 程序异常(HTTP_MEDIA_TYPE_NOT_ACCEPTABLE_EXCEPTION:406)]HTTP媒体类型不可接受异常"),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION("90005", "[Spring 程序异常(HTTP_MEDIA_TYPE_NOT_SUPPORTED_EXCEPTION:415)]HTTP媒体类型不支持异常"),
    MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION("90006", "[Spring 程序异常(MISSING_SERVLET_REQUEST_PARAMETER_EXCEPTION:400)]缺少servlet请求参数异常"),
    EXCEPTION("99999","系统错误:发生程序未捕获的异常......"),
    ;



    private String code;

    private String message;

    ValidateCodeEnum(String code, String message) {
        this.code = code;

        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
