package com.casa.api.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.api.exceptions.ObjetoNaoEncontradoException;
import com.casa.api.models.Endereco;
import com.casa.api.models.Pessoa;
import com.casa.api.models.dtos.PessoaDto;
import com.casa.api.repositories.PessoaRepository;
import com.casa.api.services.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public PessoaDto criarPessoa(PessoaDto pessoa) {
		pessoaRepository.save(dtoParaEntidade(pessoa));
		return pessoa;
	}

	@Override
	public PessoaDto editarPessoa(Integer id, PessoaDto pessoa) {
		PessoaDto pessoaEncontrada = consultarPessoaPorId(id);
		if (pessoa.getNome() != null) {
			pessoaEncontrada.setNome(pessoa.getNome());
		}

		if (pessoa.getDataNascimento() != null) {
			pessoaEncontrada.setDataNascimento(pessoa.getDataNascimento());
		}
		if (pessoa.getEnderecos() != null) {
			pessoa.getEnderecos().forEach(endereco -> {
				pessoaEncontrada.getEnderecos().add(endereco);
			});
		}
		pessoaEncontrada.setId(id);
		return criarPessoa(pessoaEncontrada);
	}

	@Override
	public PessoaDto consultarPessoaPorId(Integer id) {
		Pessoa pessoaEncontrada = buscarPorId(id);
		PessoaDto dto = new PessoaDto();
		BeanUtils.copyProperties(pessoaEncontrada, dto);
		return dto;
	}

	@Override
	public List<PessoaDto> listarPessoas() {
		List<Pessoa> pessoas = pessoaRepository.findAll();
		List<PessoaDto> pessoasDto = new ArrayList<>();
		pessoas.forEach(pessoa -> {
			PessoaDto dto = new PessoaDto();
			BeanUtils.copyProperties(pessoa, dto);
			pessoasDto.add(dto);
		});
		return pessoasDto;
	}

	@Override
	public List<Endereco> listarEnderecosDaPessoaPorId(Integer id) {
		Pessoa pessoa = buscarPorId(id);
		return pessoa.getEnderecos();
	}

	@Override
	public Endereco enderecoPrincipalDaPessoaPorId(Integer id) {
		Pessoa pessoa = buscarPorId(id);
		List<Endereco> enderecos = new ArrayList<>();
		Endereco endereco = new Endereco();
		pessoa.getEnderecos().forEach(enderecoEncontrado -> {
			if (enderecoEncontrado.getPrincipal() == true) {
				BeanUtils.copyProperties(enderecoEncontrado, endereco);
				enderecos.add(endereco);
			}
		});
		if (enderecos.isEmpty()) {
			throw new ObjetoNaoEncontradoException("A lista está vazia.");
		}
		return endereco;
	}

	public Pessoa buscarPorId(Integer id) {
		Pessoa pessoa = pessoaRepository.findById(id)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Pessoa não encontrada."));
		return pessoa;
	}

	private Pessoa dtoParaEntidade(PessoaDto pessoa) {
		Pessoa entidadePessoa = new Pessoa();
		entidadePessoa.setId(pessoa.getId());
		entidadePessoa.setNome(pessoa.getNome());
		entidadePessoa.setDataNascimento(pessoa.getDataNascimento());
		if (!pessoa.getEnderecos().isEmpty()) {
			pessoa.getEnderecos().forEach(endereco -> {
				entidadePessoa.getEnderecos().add(endereco);
			});
		}
		return entidadePessoa;
	}

}
