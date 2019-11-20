

package com.sanjutou.shopping.entity.result;


import com.sanjutou.shopping.entity.message.CommonMessages;

/**
 * HTTP请求返回消息类型。
 * 

 *
 * @author admin
 */
public class Result<T> extends CodeMessage {
    /**
     * 序列化版本号。
     */
    private static final long serialVersionUID = -6421279427859768728L;

    /**
     * 返回对象(具体类型参照具体的HTTP请求)。
     */
    private T obj;

    public Result() {
        setCodeMsg(CommonMessages.SUCCESS);
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "code->" + getCode() + ",msg->" + getMsg() + ",obj->" + obj;
    }
}
