package com.example.newage.exeptions;

public class LoyaltyTopUpLimitReachedException extends TransactionException {
	private static final long serialVersionUID = -1336671032868823005L;

	public LoyaltyTopUpLimitReachedException() {
		super(406, "Loyalty top up limit reached");
	}
}
