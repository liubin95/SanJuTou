
package com.sanjutou.shopping.dictionary;


import com.sanjutou.shopping.entity.message.AbstractMessageBuilder;

public class MessageBuilder extends AbstractMessageBuilder {
    /**
     * 声明MessageBuilder。
     */
    public static final MessageBuilder INSTANCE = new MessageBuilder();

    private MessageBuilder() {
        // 禁止外部生成实例
    }

    @Override
    public String getSubSystemCode() {
        return SubSystemCodes.BIZ_GONGDAN;
    }
}
