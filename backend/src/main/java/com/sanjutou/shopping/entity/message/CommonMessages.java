
package com.sanjutou.shopping.entity.message;


import com.sanjutou.shopping.entity.result.CodeMessage;

/**
 * 通用的 Code Message 设置。
 *
 * @author liubin
 */
public interface CommonMessages extends CommonHttpStatusMessages {
    /**
     * 操作成功(通用)。
     */
    CodeMessage SCOM0000 = CommonMessageBuilder.INSTANCE.success(0, "操作成功");

    /**
     * 操作失败(通用)。
     */
    CodeMessage ECOM9999 = CommonMessageBuilder.INSTANCE.error(9999, "操作失败");

    /**
     * 操作成功(通用)。
     */
    CodeMessage SUCCESS = SCOM0000;

    /**
     * 操作失败(通用)。
     */
    CodeMessage ERROR = ECOM9999;
}
