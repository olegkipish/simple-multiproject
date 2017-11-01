package com.example.wallet.domain;

import com.example.loyalty.api.LoyaltyApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(name="loyalty-service", url = "${loyalty.api.url}")
public interface LoyaltyClient extends LoyaltyApi{
}
