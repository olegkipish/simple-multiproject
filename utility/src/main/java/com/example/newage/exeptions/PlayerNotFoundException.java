package com.example.newage.exeptions;

public class PlayerNotFoundException extends TransactionException {
	private static final long serialVersionUID = 2157005685508245157L;

	public PlayerNotFoundException() {
		super(404, "Player not found");
	}
}
