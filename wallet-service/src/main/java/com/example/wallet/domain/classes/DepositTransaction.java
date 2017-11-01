package com.example.wallet.domain.classes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepositTransaction {

	private String transactionId;
	private int depositAmount;
	private boolean loyaltyEnabled;

	public DepositTransaction(
			String transactionId,
			int depositAmount,
			boolean loyaltyEnabled) {
		this.transactionId = transactionId;
		this.depositAmount = depositAmount;
		this.loyaltyEnabled = loyaltyEnabled;
	}
}
