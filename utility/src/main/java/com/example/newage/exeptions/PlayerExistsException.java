package com.example.newage.exeptions;

public class PlayerExistsException extends TransactionException {
	private static final long serialVersionUID = 2157005685508245157L;

	public PlayerExistsException() {
		super(402, "Player already exists");
	}
}
