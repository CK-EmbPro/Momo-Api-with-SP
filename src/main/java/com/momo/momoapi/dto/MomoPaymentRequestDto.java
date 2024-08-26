package com.momo.momoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MomoPaymentRequestDto {
    private String amount;
    private String currency;
    private String externalId;
    private Payer payer;
//    private Payee payee;
    private String payerMessage;
    private String payeeNote;



}
