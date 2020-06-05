

package com.sanjutou.shopping.entity.result;


import com.sanjutou.shopping.entity.message.CommonMessages;

/**
 * HTTP请求返回消息类型。
 *
 * @author admin
 */
public class Result extends CodeMessage {
    /**
     * 序列化版本号。
     */
    private static final long serialVersionUID = -6421279427859768728L;

    /**
     * 返回对象(具体类型参照具体的HTTP请求)。
     */
    private Object obj;

    public Result() {
        setCodeMsg(CommonMessages.SUCCESS);
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "code->" + getCode() + ",msg->" + getMsg() + ",obj->" + obj;
    }
}
