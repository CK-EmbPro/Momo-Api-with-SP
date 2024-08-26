package com.momo.momoapi.services;

import com.momo.momoapi.client.MomoRequestToPayClient;
import com.momo.momoapi.dto.MomoPaymentRequestDto;
import com.momo.momoapi.dto.Payee;
import com.momo.momoapi.dto.Payer;
import com.momo.momoapi.dto.UserDataDto;
import com.momo.momoapi.props.MomoApiConfigProps;
import com.momo.momoapi.utils.GenerateUUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpRange;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestToPayProvider {
    private final MomoRequestToPayClient requestToPayClient;
    private final AccessTokenProvider provider;
    private final MomoApiConfigProps configProps;

    public String initiatePayment(UserDataDto requestDto) {
        String trasanctionId = GenerateUUID.generateUUID();
        System.out.println("transaction id :" + trasanctionId);
        String authorization = "Bearer " + provider.getAccessToken();
        System.out.println("AUHOTROIZATION: " + authorization);
        String collectionSubKey = configProps.getCollectionsSubscriptionKey();
        System.out.println("sub key: "+collectionSubKey);
        String targetEnv = configProps.getTargetEnvironment();
        System.out.println("environment: "+targetEnv);
        Payer payer = Payer.builder()
                .partyIdType("MSISDN")
                .partyId("+250798594425")
                .build();

        Payee payee = Payee.builder()
                .partyIdType("MSISDN")
                .partyId("+250783253141")
                .build();

        MomoPaymentRequestDto paymentRequest = MomoPaymentRequestDto.builder()
                .amount("0.0000000001")
                .currency("EUR")
                .externalId("c6eda44a-958c-4102-bb16-e3957342ed66")
                .payer(payer)
//                .payee(payee)
                .payerMessage("Payment for services")
                .payeeNote("Thank you!!")
                .build();

        String response = null;
        try {
            response = requestToPayClient.requestToPay(
                    authorization,
                    trasanctionId,
                    collectionSubKey,
                    targetEnv,
                    paymentRequest
            );

            log.info("The response: {} ", response);
        } catch (Exception e) {
            log.error("Something bad happened: {}", e.getMessage());
        }

        return response;

    }
}