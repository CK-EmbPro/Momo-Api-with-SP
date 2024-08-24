package com.momo.momoapi.services;

import com.momo.momoapi.client.MomoApiAuthClient;
import com.momo.momoapi.dto.AuthenticationResponseDto;
import com.momo.momoapi.props.MomoApiConfigProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AccessTokenProvider {
    private final MomoApiAuthClient authClient;
    private final MomoApiConfigProps configProps;

    public String getAccessToken() {
        String header = Base64.getEncoder().encodeToString(String.format("%s:%s", configProps.getUserId(), configProps.getApiKey()).getBytes());
        AuthenticationResponseDto authResponse = authClient.getAccessToken("Basic " + header, configProps.getSubscriptionKey());

        return authResponse.accessToken();
    }

}
