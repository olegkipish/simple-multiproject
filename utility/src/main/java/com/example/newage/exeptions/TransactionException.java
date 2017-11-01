package com.example.newage.exeptions;

public class TransactionException extends Exception {
	private static final long serialVersionUID = 7485783738392237649L;

	private final int errorCode;
	private final String errorDescription;

	public TransactionException(
			int errorCode,
			String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}


	@Override
	public String toString() {
		return "TransactionException{" +
				"errorCode=" + errorCode +
				", errorDescription='" + errorDescription + '\'' +
				'}';
	}
}
