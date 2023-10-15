package com.casa.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.casa.api.exceptions.ObjetoNaoEncontradoException;
import com.casa.api.models.Endereco;
import com.casa.api.models.Pessoa;
import com.casa.api.models.dtos.PessoaDto;
import com.casa.api.repositories.PessoaRepository;
import com.casa.api.services.impls.PessoaServiceImpl;

@SpringBootTest
public class PessoaServiceImplTeste {

	private Pessoa pessoa;

	private PessoaDto dto;

	private Endereco endereco;

	private List<Pessoa> pessoas;

	@InjectMocks
	private PessoaServiceImpl pessoaService;

	@Mock
	private PessoaRepository pessoaRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		instanciar();
	}

	@Test
	void criarPessoa() {
		Mockito.when(pessoaRepository.save(any())).thenReturn(pessoa);

		PessoaDto dtoEncontrado = pessoaService.criarPessoa(dto);

		Assertions.assertEquals(PessoaDto.class, dtoEncontrado.getClass());
	}

	@Test
	void editarPessoa() {
		Mockito.when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));

		PessoaDto dtoEncontrado = pessoaService.editarPessoa(1, dto);

		assertNotNull(dtoEncontrado);
		Assertions.assertEquals(PessoaDto.class, dtoEncontrado.getClass());
	}

	@Test
	void consultarPessoaPorId() {
		Mockito.when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));

		PessoaDto dtoEncontrado = pessoaService.consultarPessoaPorId(1);

		assertNotNull(dtoEncontrado);
		assertEquals(PessoaDto.class, dtoEncontrado.getClass());
	}

	@Test
	void objetoNaoEncontradoException() {
		Mockito.when(pessoaRepository.findById(anyInt()))
				.thenThrow(new ObjetoNaoEncontradoException("Objeto Não Encontrado."));

		try {
			pessoaService.consultarPessoaPorId(1);
		} catch (Exception e) {
			assertEquals(ObjetoNaoEncontradoException.class, e.getClass());
			assertEquals("Objeto Não Encontrado.", e.getMessage());
		}

	}

	@Test
	void listarPessoas() {
		Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);

		List<PessoaDto> dtos = pessoaService.listarPessoas();

		assertNotNull(dtos);
		assertEquals(1, dtos.size());
		assertEquals(PessoaDto.class, dtos.get(0).getClass());
	}

	@Test
	void listarEnderecosDaPessoaPorId() {
		Mockito.when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));

		List<Endereco> enderecos = pessoaService.listarEnderecosDaPessoaPorId(1);

		assertNotNull(enderecos);
		assertEquals(1, enderecos.size());
		assertEquals(Endereco.class, enderecos.get(0).getClass());

	}

	@Test
	void buscarEnderecoPrincipalDaPessoaPorId() {
		Mockito.when(pessoaRepository.findById(anyInt())).thenReturn(Optional.of(pessoa));

		Endereco endereco = pessoaService.enderecoPrincipalDaPessoaPorId(1);

		assertNotNull(endereco);
		assertEquals(Endereco.class, endereco.getClass());

	}

	private void instanciar() {
		pessoas = new ArrayList<>();
		pessoa = new Pessoa(1, "Joelson", LocalDate.parse("14/05/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dto = new PessoaDto(1, "Joelson", LocalDate.parse("14/05/1989", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		endereco = new Endereco(1, pessoa, true, "rua maranhão", "28890058", "219", "rio das ostras");
		pessoa.getEnderecos().add(endereco);
		dto.getEnderecos().add(endereco);
		pessoas.add(pessoa);
	}

}
