package com.prueba.client.servicio;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prueba.client.entidad.Client;
import com.prueba.client.exceptions.GeneralServiceException;
import com.prueba.client.exceptions.NoDataFoundException;
import com.prueba.client.exceptions.ValidateServiceException;
import com.prueba.client.repositorio.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteServicio {

	private final Logger logger = LogManager.getLogger(ClienteServicio.class);

	@Autowired
	private ClientRepository clientRepo;

	public Page<Client> findAll(Pageable page) {
		try {
			Page<Client> products = clientRepo.findAll(page);
			return products;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	public Client findById(String id) {
		try {
			logger.info("Iniciando operacion obtener por id : {}", id);
			return clientRepo.findById(id).orElseThrow(() -> new NoDataFoundException("No existe el producto"));
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public Client save(Client product) {
		try {
			Optional<Client> exitProduct = clientRepo.findById(product.getSharedKey());
			if (exitProduct.isPresent()) {
				throw new ValidateServiceException("El cliente ya se encuentra registrado");
			}
			return clientRepo.save(product);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}

	}

}
