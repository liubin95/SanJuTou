package com.sanjutou.shopping.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 检验token是否存在有效的用户。
 * 无效用户返回403，需要连接数据库查询。
 *
 * @author liubin
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckLogin {

}
