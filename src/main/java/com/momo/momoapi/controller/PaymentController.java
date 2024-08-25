package com.momo.momoapi.controller;

import com.momo.momoapi.dto.UserDataDto;
import com.momo.momoapi.services.RequestToPayProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final RequestToPayProvider provider;
    @PostMapping("pay")
    public ResponseEntity<String> makePayment(@RequestBody UserDataDto userDataDto){
         return ResponseEntity.status(HttpStatus.CREATED)
                         .body(provider.initiatePayment(userDataDto));
    }

}
