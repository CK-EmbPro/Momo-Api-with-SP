package com.momo.momoapi.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FeignClientConfig {

    @Bean(name = "loggingRequestInterceptor")
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // Log all headers
                log.info("Request Headers: {}", requestTemplate.headers());
                // Log the request body (optional, might be null)
                log.info("Request Body: {}", requestTemplate.requestBody());
            }
        };
    }
}
