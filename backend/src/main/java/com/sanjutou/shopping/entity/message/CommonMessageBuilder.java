
package com.sanjutou.shopping.entity.message;

/**
 * @author admin
 */
public class CommonMessageBuilder extends AbstractMessageBuilder {
    /**
     * 声明CommonMessageBuilder。
     */
    public static final CommonMessageBuilder INSTANCE = new CommonMessageBuilder();

    private CommonMessageBuilder() {
        // 禁止外部生成实例
    }

    @Override
    public String getSubSystemCode() {
        return CommonSubSystemCodes.COMMON;
    }
}
