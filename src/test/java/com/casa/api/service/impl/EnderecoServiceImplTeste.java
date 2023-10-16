package com.casa.api.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.casa.api.models.Endereco;
import com.casa.api.models.Pessoa;
import com.casa.api.models.dtos.PessoaDto;
import com.casa.api.repositories.EnderecoRepository;
import com.casa.api.services.impls.EnderecoServiceImpl;

@SpringBootTest
public class EnderecoServiceImplTeste {

	private Pessoa pessoa;
	private PessoaDto dto;
	private Endereco endereco;
	private List<Pessoa> pessoas;

	@InjectMocks
	private EnderecoServiceImpl enderecoService;

	@Mock
	private EnderecoRepository enderecoRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		instanciar();
	}

	@Test
	void criarEndereco() {
//		when(enderecoRepository.save(any())).thenReturn(endereco);
//
//		Endereco enderecoMockado = enderecoService.criarEndereco(endereco);
//
//		assertNotNull(enderecoMockado);
//		assertEquals(Endereco.class, enderecoMockado.getClass());
//		assertEquals(1, endereco.getId());
//		assertEquals(Pessoa.class, enderecoMockado.getPessoa().getClass());
//		assertEquals(true, enderecoMockado.getPrincipal());
//		assertEquals("rua maranhão", enderecoMockado.getLogradouro());
//		assertEquals("rio das ostras", enderecoMockado.getCidade());
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
