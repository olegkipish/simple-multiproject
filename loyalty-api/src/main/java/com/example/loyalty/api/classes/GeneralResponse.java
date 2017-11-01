package com.example.loyalty.api.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;

@Data
public class GeneralResponse {

	@JsonProperty(required = true)
	private int errorCode;
	@JsonProperty(required = true)
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
