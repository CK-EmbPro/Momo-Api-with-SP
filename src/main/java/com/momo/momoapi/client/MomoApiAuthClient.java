package com.momo.momoapi.client;

import com.momo.momoapi.dto.AuthenticationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "momo-api-auth-client",
        url = "https://sandbox.momodeveloper.mtn.com/remittance"
)
public interface MomoApiAuthClient {
    @PostMapping("/token/")
    AuthenticationResponseDto getAccessToken(@RequestHeader("Authorization") String authorization, @RequestHeader("Ocp-Apim-Subscription-Key") String subscriptionKey);
}
