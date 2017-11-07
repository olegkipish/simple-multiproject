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

	@RequestMapping(value = "register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	GeneralResponse registerPlayer(
			@RequestBody RegisterPlayerRequest request
	);

	@RequestMapping(value = "{playerId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	LoyaltyBalanceResponse add(
			@PathVariable("playerId") String playerId,
			@RequestBody AddPointsRequest request
	);

	@RequestMapping(value = "{playerId}/balance", method = RequestMethod.GET)
	@ResponseBody
	LoyaltyBalanceResponse getPointBalance(
			@PathVariable("playerId") String playerId
	);

}
