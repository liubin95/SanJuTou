package com.sanjutou.shopping.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域配置类。
 *
 * @author admin
 */
@Configuration
@EnableWebMvc
public class SpringWebConfig extends WebMvcConfigurerAdapter {

    /**
     * 全局跨域。
     *
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        // 对所有的URL配置
        registry.addMapping("/**");
    }
}
