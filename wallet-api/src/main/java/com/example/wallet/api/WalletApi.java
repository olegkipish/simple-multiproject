package com.example.wallet.api;

import com.example.wallet.api.classes.AddDepositRequest;
import com.example.wallet.api.classes.DepositBalanceResponse;
import com.example.wallet.api.classes.RegisterPlayerRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The interface for Wallet API.
 */
@RequestMapping(value = "wallet",
		produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public interface WalletApi {

	/**
	 * Register new player .
	 *
	 * @param request the registration data
	 * @return the deposit balance response
	 */
	@PostMapping(
			value = "register",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ResponseBody
	DepositBalanceResponse registerPlayer(@RequestBody RegisterPlayerRequest request);

	/**
	 * Add deposit for player.
	 *
	 * @param playerId the player id
	 * @param request  the deposit detail
	 * @return the deposit balance response
	 */
	@PostMapping(
			value = "{playerId}/deposit",
			consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	@ResponseBody
	DepositBalanceResponse addDeposit(
			@PathVariable("playerId") String playerId,
			@RequestBody AddDepositRequest request);

	/**
	 * Gets player's balance.
	 *
	 * @param playerId the player id
	 * @return the balance
	 */
	@GetMapping(value = "{playerId}/balance")
	@ResponseBody
	DepositBalanceResponse getBalance(@PathVariable("playerId") String playerId);

}
