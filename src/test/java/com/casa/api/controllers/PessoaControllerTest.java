package com.casa.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.casa.api.models.Endereco;
import com.casa.api.models.Pessoa;
import com.casa.api.models.dtos.PessoaDto;
import com.casa.api.services.PessoaService;

@SpringBootTest
public class PessoaControllerTest {

	private Pessoa pessoa;

	private PessoaDto dto;

	private Endereco endereco;

	private List<Pessoa> pessoas;

	@InjectMocks
	private PessoaController controller;

	@Mock
	private PessoaService service;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		instanciar();
	}

	@Test
	void criarPessoa() {
		Mockito.when(service.criarPessoa(any())).thenReturn(dto);

		ResponseEntity<PessoaDto> pessoaDto = controller.criarPessoa(dto);

		Assertions.assertNotNull(pessoaDto);
		Assertions.assertEquals(ResponseEntity.class, pessoaDto.getClass());
	}

	@Test
	void consultarPessoa() {
		Mockito.when(service.consultarPessoaPorId(Mockito.anyInt())).thenReturn(dto);

		ResponseEntity<PessoaDto> pessoaDto = controller.consultarPessoa(1);

		Assertions.assertNotNull(pessoaDto);
		assertEquals(ResponseEntity.class, pessoaDto.getClass());
		assertNotNull(pessoaDto.getBody());
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
