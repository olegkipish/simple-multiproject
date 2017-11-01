package com.example.wallet.api.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddDepositRequest {

	@JsonProperty(required = true)
	private int amount;
}
