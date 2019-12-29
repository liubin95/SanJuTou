
package com.sanjutou.shopping.dictionary;


import com.sanjutou.shopping.entity.message.CommonMessages;
import com.sanjutou.shopping.entity.result.CodeMessage;

/**
 * 本项目中用到的 Result 定义。
 *
 * @author admin
 */
public interface Messages extends CommonMessages {
    /**
     * 未处理的异常。
     */
    CodeMessage E0001 = MessageBuilder.INSTANCE.error(1, "未处理的异常");

    /**
     * 缺少必要参数。
     */
    CodeMessage E0002 = MessageBuilder.INSTANCE.error(2, "缺少必要参数");

    /**
     * 用户不存在。
     */
    CodeMessage E0003 = MessageBuilder.INSTANCE.error(3, "用户不存在");

    /**
     * 密码错误。
     */
    CodeMessage E0004 = MessageBuilder.INSTANCE.error(4, "密码错误");

    /**
     * 邮箱已经注册.
     */
    CodeMessage E0005 = MessageBuilder.INSTANCE.error(5, "邮箱已经注册");
}
