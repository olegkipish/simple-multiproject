package com.example.loyalty.repository;

import com.example.loyalty.repository.entity.LoyaltyTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyTransactionRepository extends JpaRepository<LoyaltyTransaction, String> {

	long countLoyaltyTransactionByPaymentTransactionId(String paymentTransactionId);
}
