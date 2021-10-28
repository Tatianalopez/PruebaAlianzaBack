package com.prueba.client.dto;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDTO {

	@NotEmpty
	@JsonProperty("sharedKey")
	private String sharedKey;
	
	@NotEmpty
	@JsonProperty("businessId")
	private String businessId;
	
	@NotEmpty
	@JsonProperty("email")
	private String email;
	
	@NotEmpty
	@JsonProperty("phone")
	private String phone;	
	
}
