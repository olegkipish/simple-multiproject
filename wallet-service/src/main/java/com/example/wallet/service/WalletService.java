package com.example.wallet.service;


import com.example.loyalty.api.classes.AddPointsRequest;
import com.example.loyalty.api.classes.RegisterPlayerRequest;
import com.example.newage.exeptions.PlayerExistsException;
import com.example.newage.exeptions.PlayerNotFoundException;
import com.example.newage.exeptions.TransactionException;
import com.example.wallet.domain.LoyaltyClient;
import com.example.wallet.domain.classes.DepositTransaction;
import com.example.wallet.repository.DepositTransactionRepository;
import com.example.wallet.repository.WalletRepository;
import com.example.wallet.repository.entities.DepositTransactionEntity;
import com.example.wallet.repository.entities.WalletEntity;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
public class WalletService {

	@Autowired
	private WalletRepository walletRepository;
	@Autowired
	private DepositTransactionRepository transactionRepository;
	@Autowired
	private LoyaltyClient loyaltyClient;

	@Value("${loyalty.enabled}")
	private boolean loyaltyIsEnabled;
	@Value("${loyalty.minPaymentAmount}")
	private int minPaymentAmountForLoyalty;
	@Value("${loyalty.pointsToDonate}")
	private int pointsToDonate;

	@Transactional
	public int registerPlayer(@NonNull String playerId) throws TransactionException {
		log.info("Registering new wallet for player with id {}...", playerId);
		WalletEntity entity = walletRepository.findOne(playerId);
		if (entity != null) {
			log.error("Wallet for player with id {} already exists", playerId);
			throw new PlayerExistsException();
		}
		entity = new WalletEntity(playerId);
		walletRepository.save(entity);
		if (loyaltyIsEnabled) {
			log.info("Loyalty system is enabled. Trying to register player with id {} in it...", playerId);
			try {
				loyaltyClient.registerPlayer(new RegisterPlayerRequest(playerId));
				entity.setLoyaltyEnabled(true);
			} catch (Exception e) {
				log.warn("Checking registration of player with id {} in loyalty service failed. Will try on next request", playerId);
			}
		}

		return entity.getDepositAmount();
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public DepositTransaction addDeposit(
			@NonNull String playerId,
			int depositAmount,
			@NonNull LocalDateTime receiveDate) throws TransactionException {
		log.info("Adding {} to deposit for player with id {}...", depositAmount, playerId);
		final WalletEntity wallet = walletRepository.findOneForUpdate(playerId);
		if (wallet == null) {
			log.error("Wallet for player with id {} not found", playerId);
			throw new PlayerNotFoundException();
		}
		final DepositTransactionEntity transactionEntity = transactionRepository.save(
				DepositTransactionEntity.builder()
				                        .wallet(wallet)
				                        .amount(depositAmount)
				                        .receiveDate(receiveDate)
				                        .build()
		);
		wallet.addDeposit(depositAmount);
		if (loyaltyIsEnabled && depositAmount >= minPaymentAmountForLoyalty && pointsToDonate > 0) {
			if (!wallet.isLoyaltyEnabled()) {
				try {
					loyaltyClient.registerPlayer(new RegisterPlayerRequest(playerId));
					wallet.setLoyaltyEnabled(true);
				} catch (Exception e) {
					log.warn("Checking registration of player with id {} in loyalty service failed. Will try on next request", playerId);
				}
			}
			if (wallet.isLoyaltyEnabled()) {
				try {
					loyaltyClient.add(playerId, new AddPointsRequest(transactionEntity.getId(),pointsToDonate));
				} catch (Exception e) {
					log.warn("Donating {} point to loyalty service for player with id {} failed with exception: {}", pointsToDonate, playerId, e.toString());
				}
			}
		}
		return new DepositTransaction(
				transactionEntity.getId(),
				wallet.getDepositAmount(),
				wallet.isLoyaltyEnabled()
		);
	}

	public int getBalance(@NonNull String playerId) throws TransactionException {
		log.info("Getting deposit balance for player with id {}...", playerId);
		final WalletEntity entity = walletRepository.findOne(playerId);
		if (entity == null) {
			log.error("Wallet for player with id {} not found", playerId);
			throw new PlayerNotFoundException();
		}
		return entity.getDepositAmount();
	}
}
