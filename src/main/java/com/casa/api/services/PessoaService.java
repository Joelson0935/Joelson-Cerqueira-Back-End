package com.casa.api.services;

import java.util.List;

import com.casa.api.models.Endereco;
import com.casa.api.models.dtos.PessoaDto;

public interface PessoaService {

	PessoaDto criarPessoa(PessoaDto pessoa);

	PessoaDto editarPessoa(Integer id, PessoaDto pessoa);

	PessoaDto consultarPessoaPorId(Integer id);

	List<PessoaDto> listarPessoas();

	List<Endereco> listarEnderecosDaPessoaPorId(Integer id);

	Endereco enderecoPrincipalDaPessoaPorId(Integer id);
}
