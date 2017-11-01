package com.example.wallet.controller;

import com.example.newage.exeptions.TransactionException;
import com.example.wallet.api.WalletApi;
import com.example.wallet.api.classes.AddDepositRequest;
import com.example.wallet.api.classes.DepositBalanceResponse;
import com.example.wallet.api.classes.RegisterPlayerRequest;
import com.example.wallet.domain.classes.DepositTransaction;
import com.example.wallet.service.WalletService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
public class WalletApiController implements WalletApi {

	@Autowired
	private WalletService service;


	@Override
	public DepositBalanceResponse registerPlayer(
			@RequestBody @NonNull RegisterPlayerRequest request) {
		final int depositAmount;
		try {
			depositAmount = service.registerPlayer(request.getPlayerId());
		} catch (TransactionException e) {
			log.error("Registration of player with id {} failed with exception: {}", request.getPlayerId(), e.toString());
			return new DepositBalanceResponse(e.getErrorCode(), e.getErrorDescription());
		}
		return new DepositBalanceResponse(depositAmount);
	}

	@Override
	public @ResponseBody
	DepositBalanceResponse addDeposit(
			@PathVariable("playerId") @NonNull String playerId,
			@RequestBody @NonNull AddDepositRequest request) {
		final LocalDateTime receiveDate = LocalDateTime.now();
		final DepositTransaction depositTransaction;
		try {
			depositTransaction = service.addDeposit(playerId, request.getAmount(), receiveDate);
		} catch (TransactionException e) {
			log.error("Adding deposit to player with id {} failed with exception: {}", playerId, e.toString());
			return new DepositBalanceResponse(e.getErrorCode(), e.getErrorDescription());
		}
		return new DepositBalanceResponse(depositTransaction.getDepositAmount());
	}

	@Override
	public DepositBalanceResponse getBalance(
			@PathVariable("playerId") @NonNull String playerId) {
		log.info("Getting deposit balance for player with id");
		try {
			return new DepositBalanceResponse(service.getBalance(playerId));
		} catch (TransactionException e) {
			log.error("Getting deposit balance for player with id {} failed with exception: {}", playerId, e.toString());
			return new DepositBalanceResponse(e.getErrorCode(), e.getErrorDescription());
		}
	}
}
