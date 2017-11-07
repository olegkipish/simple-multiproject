package com.example.loyalty.controller;

import com.example.loyalty.api.LoyaltyApi;
import com.example.loyalty.api.classes.AddPointsRequest;
import com.example.loyalty.api.classes.GeneralResponse;
import com.example.loyalty.api.classes.LoyaltyBalanceResponse;
import com.example.loyalty.api.classes.RegisterPlayerRequest;
import com.example.loyalty.service.LoyaltyService;
import com.example.newage.exeptions.TransactionException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class LoyaltyApiController implements LoyaltyApi {

	@Autowired
	private LoyaltyService service;

	@Override
	public GeneralResponse registerPlayer(
			@RequestBody @NonNull RegisterPlayerRequest request) {
		log.info("Checking player with playerId {}", request.getPlayerId());
		service.checkOrRegisterPlayer(request.getPlayerId());
		return new GeneralResponse();
	}

	@Override
	public LoyaltyBalanceResponse add(
			@PathVariable @NonNull String playerId,
			@RequestBody @NonNull AddPointsRequest request) {
		log.info("Adding {} points to player with id {}...", request.getPointsAmount(), playerId);
		try {
			return new LoyaltyBalanceResponse(
					service.updateLoyalty(
							playerId,
							request.getPaymentTransactionId(),
							LocalDateTime.now(),
							request.getPointsAmount()
					)
			);
		} catch (TransactionException e) {
			log.error("Adding points to player with id {} failed with exception: {}", playerId, e.toString());
			return new LoyaltyBalanceResponse(e.getErrorCode(), e.getErrorDescription());
		}
	}

	@Override
	public LoyaltyBalanceResponse getPointBalance(
			@PathVariable @NonNull String playerId) {
		log.info("Getting balance for player with id {}...", playerId);
		try {
			return new LoyaltyBalanceResponse(service.getPlayerPointsBalance(playerId));
		} catch (TransactionException e) {
			log.error("Adding points to player with id {} failed with exception: {}", playerId, e.toString());
			return new LoyaltyBalanceResponse(e.getErrorCode(), e.getErrorDescription());
		}
	}
}
