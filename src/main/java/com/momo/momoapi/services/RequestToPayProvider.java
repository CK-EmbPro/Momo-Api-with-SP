package com.momo.momoapi.services;

import com.momo.momoapi.client.MomoRequestToPayClient;
import com.momo.momoapi.dto.MomoPaymentRequestDto;
import com.momo.momoapi.dto.Payee;
import com.momo.momoapi.dto.Payer;
import com.momo.momoapi.dto.UserDataDto;
import com.momo.momoapi.props.MomoApiConfigProps;
import com.momo.momoapi.utils.GenerateUUID;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestToPayProvider {
    private final MomoRequestToPayClient requestToPayClient;
    private final AccessTokenProvider provider;
    private final MomoApiConfigProps configProps;

    public String initiatePayment(UserDataDto requestDto) {
        String trasanctionId = GenerateUUID.generateUUID();
        String authorization = "Bearer " + provider.getAccessToken();
        String collectionSubKey = configProps.getCollectionsSubscriptionKey();
        String targetEnv = configProps.getTargetEnvironment();

        Payer payer = Payer.builder()
                .partyIdType("MSISDN")
                .partyId(requestDto.getSenderPhone())
                .build();


        Payee payee = Payee.builder()
                .partyIdType("MSISDN")
                .partyId("+250783253141")
                .build();

        MomoPaymentRequestDto paymentRequest = MomoPaymentRequestDto.builder()
                .amount(requestDto.getAmount())
                .currency(requestDto.getCurrency())
                .externalId(trasanctionId)
                .payer(payer)
                .payee(payee)
                .payerMessage("Payment for services")
                .payeeNote("Thank you!!")
                .build();

        return requestToPayClient.requestToPay(
                authorization,
                configProps.getUserId(),
                collectionSubKey,
                targetEnv,
                paymentRequest
        );
    }

}
