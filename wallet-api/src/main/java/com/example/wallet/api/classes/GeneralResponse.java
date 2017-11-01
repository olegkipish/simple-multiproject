package com.example.wallet.api.classes;

import lombok.Data;
import lombok.NonNull;

@Data
public class GeneralResponse {

	private int errorCode;
	private String errorDescription;

	public GeneralResponse(
			int errorCode,
			@NonNull String errorDescription) {
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
	}

	public GeneralResponse() {
		this.errorCode=200;
		this.errorDescription="OK";
	}
}
