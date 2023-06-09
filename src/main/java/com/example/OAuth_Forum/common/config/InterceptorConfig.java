package com.example.OAuth_Forum.common.config;

import com.example.OAuth_Forum.auth.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    private final AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePatterns = Arrays.asList(
                "/api/auth/register","/api/auth/login","/api/auth/oAuth","/dev","/h2/**",
                "/swagger-ui/**", // 스웨거 UI 예외 처리
                "/swagger-resources/**", // 스웨거 리소스 예외 처리
                "/v3/api-docs/**" // 스웨거 API 문서 예외 처리
                );//interceptor 제외할 부분
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePatterns);

    }
}