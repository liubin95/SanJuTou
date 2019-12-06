package com.sanjutou.shopping.config;

import com.google.gson.GsonBuilder;
import com.sanjutou.shopping.interceptor.OptionsInterceptor;
import com.sanjutou.shopping.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
     * options请求拦截器。
     */
    @Autowired
    private OptionsInterceptor optionsInterceptor;

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
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }

    /**
     * Gson 解析json 時間轉換
     *
     * @param converters converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final GsonHttpMessageConverter gsonHttpMessageConverter = new GsonHttpMessageConverter();
        gsonHttpMessageConverter.setGson(new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create());
        converters.add(gsonHttpMessageConverter);
    }

}
