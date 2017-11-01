package com.example.loyalty.api.classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPlayerRequest {

	@JsonProperty(required = true)
	private String playerId;
}
