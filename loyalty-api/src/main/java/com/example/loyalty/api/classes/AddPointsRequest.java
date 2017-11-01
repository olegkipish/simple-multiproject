package com.example.loyalty.api.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddPointsRequest {

	@JsonProperty(required = true)
	private String paymentTransactionId;
	@JsonProperty(required = true)
	private int pointsAmount;
	
}
