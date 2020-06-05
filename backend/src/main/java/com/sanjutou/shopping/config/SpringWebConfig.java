package com.sanjutou.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sanjutou.shopping.interceptor.OptionsInterceptor;
import com.sanjutou.shopping.interceptor.TokenInterceptor;

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
    private final TokenInterceptor tokenInterceptor;

    /**
     * options请求拦截器。
     */
    private final OptionsInterceptor optionsInterceptor;

    @Autowired
    public SpringWebConfig(TokenInterceptor tokenInterceptor, OptionsInterceptor optionsInterceptor) {
        this.tokenInterceptor = tokenInterceptor;
        this.optionsInterceptor = optionsInterceptor;
    }

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
        registry.addInterceptor(optionsInterceptor).addPathPatterns("/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**").excludePathPatterns("/image/**");
    }

    /**
     * 静态文件映射
     *
     * @param registry registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:/shopping/SanJuTou/image/");
    }
}
