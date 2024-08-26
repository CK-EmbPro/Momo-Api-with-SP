package com.momo.momoapi.client;

import com.momo.momoapi.config.FeignClientConfig;
import com.momo.momoapi.dto.MomoPaymentRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "momo-request-to-pay",
        url = "https://sandbox.momodeveloper.mtn.com/collection",
        configuration = FeignClientConfig.class
)
public interface MomoRequestToPayClient {
    @PostMapping(value = "/v1_0/requesttopay", consumes = "application/json")
    String requestToPay(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("X-Reference-Id") String referenceId,
            @RequestHeader("Ocp-Apim-Subscription-Key") String ocpApimSubscriptionKey,
            @RequestHeader("X-Target-Environment") String targetEnvironment,
            @RequestBody MomoPaymentRequestDto paymentRequest
    );
}
