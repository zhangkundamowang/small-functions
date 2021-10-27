package com.zk.mybatisplus.config.interceptor;

import com.zk.mybatisplus.mapper.TTenantUserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.annotation.Resource;


/**
 * 拦截器配置
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Resource
    private TTenantUserMapper userMapper;

    /**
     *  配置拦截器、拦截路径
     *  每次请求到拦截的路径，就会去执行拦截器中的方法
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自己写的拦截器，如果是多个拦截器，形成一个拦截器链
        InterceptorRegistration registration = registry.addInterceptor(new AuthInterceptor(userMapper));
        registration.addPathPatterns("/**");             //所有路径被拦截
        registration.excludePathPatterns(//添加不拦截路径
                "/swagger-resources",//swagger路径
                "/root/login",   //登陆路径
                "/root/auth",    //注册路径
                "/**/*.html",    //html静态资源
                "/**/*.js",      //js静态资源
                "/**/*.css");    //css静态资源
    }


    /**
     * 解决跨域请求
     */
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOriginPatterns("*")
                .allowCredentials(true);
    }

    //注入拦截器
    @Bean
    public AuthInterceptor AuthInterceptor() {
        return new AuthInterceptor(userMapper);
    }

}
