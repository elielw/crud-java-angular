package com.totvs.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.totvs.TestUtils;
import com.totvs.dto.ClienteDTO;
import com.totvs.model.Cliente;
import com.totvs.repository.ClienteRepository;
import com.totvs.repository.TelefoneRepository;
import com.totvs.service.ClienteService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClienteServiceTest {

	@Autowired
	ClienteService clienteService;

	@Autowired
	TelefoneRepository telefoneRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Test
	@DisplayName("Criar cliente")
	public void test_createCliente() {
		ClienteDTO defaultClienteDTO = TestUtils.getDefaultClienteDTO();
		Cliente cliente = clienteService.create(defaultClienteDTO);

		assertNotNull(cliente);
		assertNotNull(cliente.getId());
		assertEquals(cliente.getNome(), defaultClienteDTO.getNome());

		cleanup();
	}

	@Test
	@DisplayName("Telefone vinculado a outro cliente")
	public void test_telefoneVinculadoOutroCliente() {
		ClienteDTO defaultClienteDTO = TestUtils.getDefaultClienteDTO();
		Cliente cliente1 = clienteService.create(defaultClienteDTO);

		assertNotNull(cliente1);
		assertNotNull(cliente1.getId());
		assertEquals(cliente1.getNome(), defaultClienteDTO.getNome());

		assertThrows(IllegalArgumentException.class, () -> clienteService.create(defaultClienteDTO));

		cleanup();
	}

	@Test
	@DisplayName("Telefone com caracteres repetidos")
	public void test_telefoneCaracteresRepetidos() {
		ClienteDTO dto = TestUtils.getClienteTelefoneRepetido();
		assertThrows(IllegalArgumentException.class, () -> clienteService.create(dto));
	}

	private void cleanup() {
		telefoneRepository.deleteAll();
		clienteRepository.deleteAll();
	}

}
