package com.momo.momoapi.dto;

public record AccountBalanceDto(
        String availableBalance,
        String currency
) {
}
