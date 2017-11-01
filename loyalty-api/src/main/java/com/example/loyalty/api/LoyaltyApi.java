package com.example.loyalty.api;

import com.example.loyalty.api.classes.AddPointsRequest;
import com.example.loyalty.api.classes.GeneralResponse;
import com.example.loyalty.api.classes.LoyaltyBalanceResponse;
import com.example.loyalty.api.classes.RegisterPlayerRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(
		value = "loyalty",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public interface LoyaltyApi {

	@PostMapping(value = "register")
	@ResponseBody
	GeneralResponse registerPlayer(
			@RequestBody RegisterPlayerRequest request
	);

	@PostMapping(
			value = "{playerId}",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ResponseBody LoyaltyBalanceResponse add(
			@PathVariable("playerId") String playerId,
			@RequestBody AddPointsRequest request
	);

	@GetMapping(value = "{playerId}/balance")
	@ResponseBody LoyaltyBalanceResponse getBalance(
			@PathVariable("playerId") String playerId
	);
	
}
