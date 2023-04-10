package com.totvs;

import java.util.Arrays;

import com.totvs.dto.ClienteDTO;

public class TestUtils {

	public static ClienteDTO getDefaultClienteDTO() {
		ClienteDTO dto = new ClienteDTO();
		dto.setNome("Cliente 1");
		dto.setEndereco("Rua Fernando Zanatta");
		dto.setBairro("Centro");
		dto.setTelefones(Arrays.asList("48123","48012"));

		return dto;
	}

	public static ClienteDTO getClienteTelefoneRepetido() {
		ClienteDTO dto = new ClienteDTO();
		dto.setNome("Cliente 2");
		dto.setEndereco("Rua Fernando Zanatta");
		dto.setBairro("Centro");
		dto.setTelefones(Arrays.asList("123123","55555"));

		return dto;
	}
}
