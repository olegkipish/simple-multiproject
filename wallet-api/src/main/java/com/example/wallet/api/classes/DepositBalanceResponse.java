package com.example.wallet.api.classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DepositBalanceResponse extends GeneralResponse {

	private int depositAmount;

	public DepositBalanceResponse() {
		super();
		this.depositAmount = 0;
	}


	public DepositBalanceResponse(int depositAmount) {
		super();
		this.depositAmount = depositAmount;
	}

	public DepositBalanceResponse(
			int errorCode,
			String errorDescription) {
		super(errorCode, errorDescription);
	}
}
