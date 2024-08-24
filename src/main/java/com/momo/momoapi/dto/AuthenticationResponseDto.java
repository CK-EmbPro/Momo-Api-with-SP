package com.momo.momoapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthenticationResponseDto(
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("token_type")
        String tokenType,
        @JsonProperty("expires_in")
        Integer expiresIn
) {
}
