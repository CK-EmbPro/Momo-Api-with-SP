package com.momo.momoapi.props;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("my.mtn-momo")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MomoApiConfigProps {
    private String subscriptionKey;
    private String targetEnvironment;
    private String userId;
    private String apiKey;
}
