package com.momo.momoapi.config;

import com.momo.momoapi.props.MomoApiConfigProps;
import com.momo.momoapi.services.AccessTokenProvider;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MomoApiConfig {
    private final AccessTokenProvider provider;
    private final MomoApiConfigProps configProps;

    @Bean
    RequestInterceptor requestInterceptor() {

        return requestTemplate -> requestTemplate
                .header("Authorization", "Bearer " + provider.getAccessToken())
                .header("X-Target-Environment", configProps.getTargetEnvironment())
                .header("Ocp-Apim-Subscription-Key", configProps.getRemittancesSubscriptionKey());
    }
}
