package com.alpha.alipay.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer
{
//    @Bean
//    public AuthInterceptor authInterceptor() {
//        return new AuthInterceptor();
//    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry)
//    {
//        registry.addInterceptor(authInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login", "/user/register", "/imserver/**", "/files/**", "/alipay/**",
//                        "/doc.html", "/webjars/**", "/swagger-resources/**");
//    }
    @Override
    public void addResourceHandlers(@NotNull ResourceHandlerRegistry registry)
    {
        //配置拦截器访问静态资源
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
