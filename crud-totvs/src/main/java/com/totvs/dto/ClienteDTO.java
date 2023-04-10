package com.totvs.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.totvs.model.Cliente;

public class ClienteDTO {

	@NotBlank(message = "O campo 'nome' é obrigatório")
	@Size(max = 10, message = "O nome do cliente deve ter no máximo 10 caracteres")
	private String nome;

	@NotBlank(message = "O campo 'endereco' é obrigatório")
	@Size(max = 100, message = "O endereco do cliente deve ter no máximo 100 caracteres")
	private String endereco;

	@NotBlank(message = "O campo 'bairro' é obrigatório")
	@Size(max = 100, message = "O bairro do cliente deve ter no máximo 100 caracteres")
	private String bairro;

	@NotEmpty(message = "O campo 'telefone' é obrigatório")
	private List<@NotBlank(message = "O campo 'telefone' é obrigatório") String> telefones;

	public Cliente toEntity() {
		Cliente cliente = new Cliente();
		cliente.setNome(this.nome);
		cliente.setEndereco(this.endereco);
		cliente.setBairro(this.bairro);
		return cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}
}
