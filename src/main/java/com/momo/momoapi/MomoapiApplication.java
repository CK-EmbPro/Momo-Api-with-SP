package com.momo.momoapi;

import com.momo.momoapi.client.MomoApiClient;
import com.momo.momoapi.dto.AccountBalanceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
@RequiredArgsConstructor
@Slf4j
public class MomoapiApplication {

	private final MomoApiClient apiClient;

	@Bean
	ApplicationRunner application(){
		return  args -> {
			AccountBalanceDto accountBalance = apiClient.getAccountBalance();
			log.info("Your account balance : {}",accountBalance);
		};
	}
	public static void main(String[] args) {
		SpringApplication.run(MomoapiApplication.class, args);
	}

}
