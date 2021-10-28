package com.prueba.client.conversor;

import com.prueba.client.dto.ClientDTO;
import com.prueba.client.entidad.Client;

public class ClientConverter extends AbstractConverter<Client, ClientDTO>{

	@Override
	public ClientDTO fromEntity(Client entity) {
		if(entity == null) return null;
		
		return ClientDTO.builder()
				.sharedKey(entity.getSharedKey())
				.businessId(entity.getBusinessId())
				.email(entity.getEmail())
				.phone(entity.getPhone())
				.build();
	}

	@Override
	public Client fromDTO(ClientDTO dto) {
		if(dto == null) return null;
		return Client.builder()
				.sharedKey(dto.getSharedKey())
				.businessId(dto.getBusinessId())
				.email(dto.getEmail())
				.phone(dto.getPhone())
				.build();
	}

}
