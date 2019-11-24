package com.sanjutou.shopping.config;

import com.sanjutou.shopping.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置类。
 *
 * @author admin
 */
@Configuration
@EnableWebMvc
public class SpringWebConfig implements WebMvcConfigurer {

    /**
     * token拦截器。
     */
    @Autowired
    private TokenInterceptor tokenInterceptor;

    /**
     * 全局跨域。
     *
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 对所有的URL配置
        registry.addMapping("/**");
    }

    /**
     * 注册拦截器。
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 对所有的URL配置
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }
}
