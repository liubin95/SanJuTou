
package com.sanjutou.shopping.dictionary;


import com.sanjutou.shopping.entity.message.CommonMessages;
import com.sanjutou.shopping.entity.result.CodeMessage;

/**
 * 本项目中用到的 Result 定义。
 * 
 *
 * @author admin
 */
public interface Messages extends CommonMessages {
    /**
     * 【单点登录】无效的 App Id。
     */
    CodeMessage E0001 = MessageBuilder.INSTANCE.error(1, "无效的 App Id");
}
