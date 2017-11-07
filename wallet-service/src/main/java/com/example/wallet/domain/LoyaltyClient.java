package com.example.wallet.domain;

import com.example.loyalty.api.LoyaltyApi;
import org.springframework.cloud.netflix.feign.FeignClient;

import static com.example.wallet.domain.LoyaltyClient.SERVICE_ID;

@FeignClient(SERVICE_ID)
public interface LoyaltyClient extends LoyaltyApi{
	String SERVICE_ID = "loyalty-service";
}
