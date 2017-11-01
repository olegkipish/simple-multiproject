package com.example.loyalty.api.classes;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoyaltyBalanceResponse extends GeneralResponse {

	public Integer loyaltyPoints;

	public LoyaltyBalanceResponse(){
		super();
		this.loyaltyPoints = 0;
	}

	public LoyaltyBalanceResponse(
			int loyaltyPoints) {
		super();
		this.loyaltyPoints = loyaltyPoints;
	}

	public LoyaltyBalanceResponse(
			int errorCode,
			String errorDescription) {
		super(errorCode, errorDescription);
	}
}
