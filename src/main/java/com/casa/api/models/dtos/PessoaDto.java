package com.casa.api.models.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.casa.api.models.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public class PessoaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Size(min = 3, message = "nome precisa ter ao menos {min} caracteres")
	private String nome;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@PastOrPresent(message = "data inválida.")
	private LocalDate dataNascimento;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private List<Endereco> enderecos = new ArrayList<>();

	public PessoaDto() {
		super();
	}

	public PessoaDto(Integer id, String nome, LocalDate dataNascimento) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate data_De_Nascimento) {
		this.dataNascimento = data_De_Nascimento;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

}
