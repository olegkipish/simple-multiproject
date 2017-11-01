package com.example.newage.exeptions;

public class DuplicatePaymentTransactionException extends TransactionException {
	private static final long serialVersionUID = 7599160287018683933L;

	public DuplicatePaymentTransactionException() {
		super(405, "Duplicate payment transaction");
	}
}
