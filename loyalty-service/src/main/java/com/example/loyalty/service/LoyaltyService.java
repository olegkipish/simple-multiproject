package com.example.loyalty.service;

import com.example.loyalty.repository.LoyaltyPointsRepository;
import com.example.loyalty.repository.LoyaltyTransactionRepository;
import com.example.loyalty.repository.entity.LoyaltyPoints;
import com.example.loyalty.repository.entity.LoyaltyTransaction;
import com.example.newage.exeptions.DuplicatePaymentTransactionException;
import com.example.newage.exeptions.LoyaltyTopUpLimitReachedException;
import com.example.newage.exeptions.PlayerNotFoundException;
import com.example.newage.exeptions.TransactionException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Slf4j
@Service
public class LoyaltyService {

	@Autowired
	private LoyaltyPointsRepository loyaltyPointsRepository;

	@Autowired
	private LoyaltyTransactionRepository transactionRepository;

	//Period in minutes or fraud monitoring
	@Value("${topUpIntervalMinutes}")
	private int topUpIntervalMinutes;

	//How many top-up operations available for period
	@Value("${topUpCountLimit}")
	private int topUpCountLimit;

	@Transactional
	public void checkOrRegisterPlayer(@NonNull String playerId) {
		log.info("Trying to find player with playerId {}", playerId);
		LoyaltyPoints player = loyaltyPointsRepository.findOne(playerId);
		if (player == null) {
			log.info("Player with id {} not found. Creating new one...", playerId);
			player = new LoyaltyPoints(playerId);
			loyaltyPointsRepository.save(player);
		} else {
			log.info("Player with id {} exists", playerId);
		}
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public int updateLoyalty(
			@NonNull String playerId,
			@NonNull String paymentTransactionId,
			@NonNull LocalDateTime receiveDt,
			int pointsAmount) throws TransactionException {
		long cnt = transactionRepository.countLoyaltyTransactionByPaymentTransactionId(paymentTransactionId);
		if (cnt > 0) {
			log.error("Payment transaction with id {} already exists", paymentTransactionId);
			throw new DuplicatePaymentTransactionException();
		}
		final LoyaltyPoints entity = loyaltyPointsRepository.findOneForUpdate(playerId);
		if (entity == null) {
			throw new PlayerNotFoundException();
		}
		final LoyaltyTransaction transaction = LoyaltyTransaction.builder()
		                                                         .paymentTransactionId(paymentTransactionId)
		                                                         .receiveDt(receiveDt)
		                                                         .loyaltyPoints(entity)
		                                                         .pointsAmount(pointsAmount)
		                                                         .build();
		transactionRepository.saveAndFlush(transaction);
		if (receiveDt.isBefore(entity.getNextTopUp())) {
			if (entity.getOperCount() == topUpCountLimit) {
				throw new LoyaltyTopUpLimitReachedException();
			} else {
				entity.incOperCount();
				transaction.setSuccess(true);
			}
		} else {
			entity.setNextTopUp(receiveDt.plus(topUpIntervalMinutes, ChronoUnit.MINUTES));
			entity.setOperCount(1);
			transaction.setSuccess(true);
		}
		entity.addPointsAmount(pointsAmount);
		return entity.getPointsAmount();
	}


	public int getPlayerPointsBalance(@NonNull String playerId) throws TransactionException {
		log.info("Getting points balance for player with id {}...", playerId);
		final LoyaltyPoints entity = loyaltyPointsRepository.findOne(playerId);
		if (entity == null){
			throw new PlayerNotFoundException();
		}
		return entity.getPointsAmount();
	}
}
