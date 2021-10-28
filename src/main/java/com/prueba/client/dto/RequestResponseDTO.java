package com.prueba.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RequestResponseDTO {

	@JsonProperty("id")
	private String id;
	@JsonProperty("resultado")
	private String resultado;
	@JsonProperty("descripcion")
	private String descripcion;
	
	public RequestResponseDTO() {
		super();
	}	
}
