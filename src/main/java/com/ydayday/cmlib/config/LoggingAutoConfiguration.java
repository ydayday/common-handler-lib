package com.ydayday.cmlib.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ydayday.cmlib.exception.RestControllerExceptionHandler;
import com.ydayday.cmlib.filter.RequestAndResponseLoggingFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@AutoConfiguration
public class LoggingAutoConfiguration {

    /**
     * <pre>
     * REST API LOGGING 설정
     * - REQUEST LOG
     * - RESPONSE LOG
     * </pre>
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean<RequestAndResponseLoggingFilter> loggingFilter() {
        log.info(">>>>>>>> INIT BEAN : FilterRegistrationBean<RequestAndResponseLoggingFilter>");
        FilterRegistrationBean<RequestAndResponseLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestAndResponseLoggingFilter());
        registrationBean.addUrlPatterns("/api/*"); // 필터를 적용할 URL 패턴을 지정
        registrationBean.setOrder(1); // 필터의 실행 순서를 지정
        return registrationBean;
    }

    /**
     * CorsFilter
     * @return CorsFilter
     */
    @Bean
    public CorsFilter corsFilter() {
        log.info(">>>>>>>> INIT BEAN : CorsFilter");
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);

        return new CorsFilter(source);
    }

    @Bean
    public ObjectMapper objectMapper() {
        log.info(">>>>>>>> INIT BEAN : ObjectMapper");
        return new ObjectMapper();
    }

    @Bean
    public RestControllerExceptionHandler restControllerExceptionHandler() {
        log.info(">>>>>>>> INIT BEAN : RestControllerExceptionHandler");
        return new RestControllerExceptionHandler();
    }

}
