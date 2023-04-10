package com.totvs.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.totvs.dto.ClienteDTO;
import com.totvs.model.Cliente;
import com.totvs.model.Telefone;
import com.totvs.repository.ClienteRepository;
import com.totvs.repository.TelefoneRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TelefoneRepository telefoneRepository;

	@Transactional
	public Cliente create(ClienteDTO clienteDTO) throws IllegalArgumentException {
		beforeSave(clienteDTO);
		Cliente clienteSaved = clienteRepository.save(clienteDTO.toEntity());
		List<Telefone> telefonesSaved = saveTelefones(clienteSaved, clienteDTO.getTelefones());
		clienteSaved.setTelefones(telefonesSaved);
		return clienteSaved;
	}

	@Transactional
	public Page<Cliente> findAll(Pageable pageable) {
		Page<Cliente> clientes = clienteRepository.findAll(pageable);
		return clientes;
	}

	private void beforeSave(ClienteDTO clienteDTO) throws IllegalArgumentException {
		Optional<String> found = clienteDTO.getTelefones()
				.stream()
				.filter(t -> t.length() != t.chars().distinct().count())
				.findAny();

		if (found.isPresent()) {
			throw new IllegalArgumentException("Existem telefones com caracteres repetidos");
		}

		found = clienteDTO.getTelefones()
				.stream()
				.filter(t -> telefoneRepository.findAllByTelefone(t).size() > 0)
				.findAny();

		if (found.isPresent()) {
			throw new IllegalArgumentException("Este telefone já está vinculado a outro cliente");
		}
	}

	private List<Telefone> saveTelefones(Cliente cliente, List<String> telefones) {
		List<Telefone> newTelefones = telefones.stream()
				.map(t -> new Telefone(t, cliente))
				.collect(Collectors.toList());

		return telefoneRepository.saveAll(newTelefones);
	}

}
