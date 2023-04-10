package com.totvs.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.totvs.dto.ClienteDTO;
import com.totvs.model.Cliente;
import com.totvs.service.ClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
		Cliente cliente = clienteService.create(clienteDTO);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(location).body(cliente);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cliente> listClientes(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable pageable) {
		Page<Cliente> clientes = clienteService.findAll(pageable);
		return clientes.getContent();
	}

}
