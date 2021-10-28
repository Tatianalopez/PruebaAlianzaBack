package com.prueba.client.controlador;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.client.conversor.ClientConverter;
import com.prueba.client.dto.ClientDTO;
import com.prueba.client.entidad.Client;
import com.prueba.client.servicio.ClienteServicio;
import com.prueba.client.util.WrapperResponse;

@RestController
@RequestMapping("cliente")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class ClientControlador {

	private final Logger logger = LogManager.getLogger(ClientControlador.class);

	@Autowired
	private ClienteServicio servicio;

	@Autowired
	private ClientConverter converter;
	
	@GetMapping
	public ResponseEntity<WrapperResponse<Page<Client>>> findAll(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
		logger.info("iniciando consumo del API obtenerTodos ");
		Pageable page = PageRequest.of(pageNumber, pageSize);
		Page<Client> products = servicio.findAll(page);
		logger.info("finalizando consumo del API obtenerTodos ");
		return new WrapperResponse<Page<Client>>(true, "success", products)
				.createResponse(HttpStatus.OK);
	}

	@GetMapping(value="/clienteId")
	public ResponseEntity<WrapperResponse<List<ClientDTO>>> findById(@RequestParam("id") String id) {
		Client product = servicio.findById(id);
		ClientDTO productDTO = converter.fromEntity(product);
		return new WrapperResponse<List<ClientDTO>>(true, "success", Arrays.asList(productDTO))
				.createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO product) {
		Client newProduct = servicio.save(converter.fromDTO(product));
		ClientDTO productDTO = converter.fromEntity(newProduct);
		return new WrapperResponse(true, "success", productDTO)
				.createResponse(HttpStatus.CREATED);
	}
	
}
