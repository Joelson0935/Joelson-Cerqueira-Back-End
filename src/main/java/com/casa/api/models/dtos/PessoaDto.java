package com.casa.api.models.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.casa.api.models.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

public class PessoaDto {

	private Long id;

	@Size(min = 3, message = "nome precisa ter ao menos {min} caracteres")
	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_De_Nascimento;

	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos = new ArrayList<>();

	public PessoaDto() {
		super();
	}

	public PessoaDto(Long id, @Size(min = 3, message = "nome precisa ter ao menos {min} caracteres") String nome,
			LocalDate data_De_Nascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.data_De_Nascimento = data_De_Nascimento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData_De_Nascimento() {
		return data_De_Nascimento;
	}

	public void setData_De_Nascimento(LocalDate data_De_Nascimento) {
		this.data_De_Nascimento = data_De_Nascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

}
