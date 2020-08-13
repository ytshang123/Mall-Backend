package project.linkortech.test.config;

import mizuki.project.core.restserver.interceptor.DefaultIntercep;
import mizuki.project.core.restserver.interceptor.RestAuthIntercep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by ycj on 16/4/20.
 * 配置web 拦截等
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(defaultIntercep()).addPathPatterns("/**");
//        registry.addInterceptor(restAuthIntercep())
//                .addPathPatterns("/rest/**")
//                .excludePathPatterns("/rest/data/receive/**")
//                .excludePathPatterns("/rest/common/**")
//                .excludePathPatterns("/rest/user/login*")
//                .excludePathPatterns("/rest/setting/factory/query")
//                .excludePathPatterns("/rest/setting/dashboard/url");
    }


    @Bean
    public DefaultIntercep defaultIntercep(){
        return new DefaultIntercep();
    }

//    @Bean
//    public RestAuthIntercep restAuthIntercep(){
//        return new RestAuthIntercep();
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
