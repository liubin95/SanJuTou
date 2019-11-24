
package com.sanjutou.shopping.entity.message;

/**
 * @author admin
 */
public class SystemMessageBuilder extends AbstractMessageBuilder {
    /**
     * 声明SystemMessageBuilder。
     */
    public static final SystemMessageBuilder INSTANCE = new SystemMessageBuilder();

    private SystemMessageBuilder() {
        // 禁止外部生成实例
    }

    @Override
    public String getSubSystemCode() {
        return CommonSubSystemCodes.SYSTEM;
    }
}
