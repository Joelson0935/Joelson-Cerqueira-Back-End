package com.casa.api.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casa.api.exceptions.EnderecoException;
import com.casa.api.models.Endereco;
import com.casa.api.models.Pessoa;
import com.casa.api.repositories.EnderecoRepository;
import com.casa.api.services.EnderecoService;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PessoaServiceImpl pessoaService;

	@Override
	public Endereco criarEndereco(Endereco endereco) {
		Pessoa pessoa = pessoaService.buscarPorId(endereco.getPessoa().getId());
		pessoa.getEnderecos().forEach(enderecoEncontrado -> {
			if (enderecoEncontrado.getPrincipal() == true && endereco.getPrincipal() == true) {
				throw new EnderecoException("Já existe um endereço principal.");
			}
		});
		endereco.setPessoa(pessoa);
		return enderecoRepository.save(endereco);
	}

}
