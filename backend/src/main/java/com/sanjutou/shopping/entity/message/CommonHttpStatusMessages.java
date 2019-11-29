
package com.sanjutou.shopping.entity.message;


import com.sanjutou.shopping.entity.result.CodeMessage;

/**
 * 通用的 HTTP 请求错误定义。
 *
 * @author liubin
 */
public interface CommonHttpStatusMessages {
    /**
     * HTTP 请求错误定义码的开始值。
     */
    int CODE_HTTP_MESSAGE_START = 9000;

    /**
     * 通过 HTTP 状态码得到对应的错误定义。
     *
     * @param httpStatusCode httpStatusCode
     * @return CodeMessage
     */
    static CodeMessage findHttpMessage(int httpStatusCode) {
        return SystemMessageBuilder.INSTANCE.find(CodeTypes.ERROR, httpStatusCode + CODE_HTTP_MESSAGE_START);
    }

    /**
     * 400 Bad Request。
     */
    CodeMessage ESYS9400 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 400, "请求参数不正确");

    /**
     * 401 Unauthorized。
     */
    CodeMessage ESYS9401 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 401, "尚未登录或者登录超时");

    /**
     * 403 Forbidden。
     */
    CodeMessage ESYS9403 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 403, "权限不足");

    /**
     * 404 Not Found。
     */
    CodeMessage ESYS9404 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 404, "找不到请求的资源");

    /**
     * 405 Method Not Allowed。
     */
    CodeMessage ESYS9405 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 405, "不支持请求的方法");

    /**
     * 408 Request Timeout。
     */
    CodeMessage ESYS9408 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 408, "请求超时");

    /**
     * 413 Payload Too Large。
     */
    CodeMessage ESYS9413 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 413, "请求数据超长");

    /**
     * 414 URI Too Long。
     */
    CodeMessage ESYS9414 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 414, "请求 URL 超长");

    /**
     * 415 Unsupported Media Type。
     */
    CodeMessage ESYS9415 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 415, "不支持的数据类型");

    /**
     * 429 Too Many Requests。
     */
    CodeMessage ESYS9429 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 429, "请求过于频繁");

    /**
     * 500 Internal Server Error。
     */
    CodeMessage ESYS9500 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 500, "服务器内部错误");

    /**
     * 502 Bad Gateway。
     */
    CodeMessage ESYS9502 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 502, "服务器网关错误");

    /**
     * 503 Service Unavailable。
     */
    CodeMessage ESYS9503 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 502, "服务器维护或停机");

    /**
     * 504 Gateway Timeout。
     */
    CodeMessage ESYS9504 = SystemMessageBuilder.INSTANCE.error(CODE_HTTP_MESSAGE_START + 504, "服务器网关超时");

    /**
     * 请求处理失败(通用)。
     */
    CodeMessage ESYS9900 = SystemMessageBuilder.INSTANCE.error(9900, "网络错误");

    /**
     * 请求处理失败(通用)。
     */
    CodeMessage ERROR_HTTP_REQUEST = ESYS9900;
}
