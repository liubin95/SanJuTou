
package com.sanjutou.shopping.entity.message;


import com.sanjutou.shopping.entity.result.CodeBuilder;
import com.sanjutou.shopping.entity.result.CodeMessage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractMessageBuilder {
    /**
     * 声明CodeMessage。
     */
    protected static final Map<String, CodeMessage> MESSAGEMAP = new ConcurrentHashMap<>();

    protected AbstractMessageBuilder() {
        //
    }

    public abstract String getSubSystemCode();

    /**
     * 创建CodeMessage。
     * 
     * @param succeeded succeeded
     * @param codeType codeType
     * @param code code
     * @param format format
     * @param args args
     * @return CodeMessage
     */
    public CodeMessage build(boolean succeeded, String codeType, int code, String format, Object... args) {
        CodeMessage codeMessage = new CodeMessage(succeeded, CodeBuilder.build(codeType, getSubSystemCode(), code), format, args);
        MESSAGEMAP.put(codeMessage.getCode(), codeMessage);
        return codeMessage;
    }

    public CodeMessage success(int code, String format, Object... args) {
        return build(true, CodeTypes.SUCCESS, code, format, args);
    }

    public CodeMessage error(int code, String format, Object... args) {
        return build(false, CodeTypes.ERROR, code, format, args);
    }

    public CodeMessage warn(int code, String format, Object... args) {
        return build(true, CodeTypes.WARN, code, format, args);
    }

    public static CodeMessage find(String code) {
        return MESSAGEMAP.get(code);
    }

    public static CodeMessage find(String subSystemCode, String codeType, int code) {
        return find(CodeBuilder.build(codeType, subSystemCode, code));
    }

    public CodeMessage find(String codeType, int code) {
        return find(CodeBuilder.build(codeType, getSubSystemCode(), code));
    }
}
