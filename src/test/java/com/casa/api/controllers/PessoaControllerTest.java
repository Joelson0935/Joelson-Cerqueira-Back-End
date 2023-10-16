package com.casa.api.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

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
import org.springframework.http.HttpStatus;
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

		ResponseEntity<PessoaDto> response = controller.criarPessoa(dto);

		Assertions.assertNotNull(response);
		assertNotNull(response.getBody());
		Assertions.assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	void editarPessoa() {
		Mockito.when(service.editarPessoa(Mockito.anyInt(), any())).thenReturn(dto);

		ResponseEntity<PessoaDto> response = controller.editarPessoa(dto, 1);

		Assertions.assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertNotNull(response.getBody());
		assertEquals(PessoaDto.class, response.getBody().getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void consultarPessoa() {
		Mockito.when(service.consultarPessoaPorId(Mockito.anyInt())).thenReturn(dto);

		ResponseEntity<PessoaDto> response = controller.consultarPessoa(1);

		Assertions.assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		assertNotNull(response.getBody());
		assertEquals(PessoaDto.class, response.getBody().getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void listarPessoas() {
		when(service.listarPessoas()).thenReturn(List.of(dto));

		ResponseEntity<List<PessoaDto>> responses = controller.listarPessoas();

		assertNotEquals(0, responses.getBody().size());
		assertEquals(PessoaDto.class, responses.getBody().get(0).getClass());
		assertEquals(HttpStatus.OK, responses.getStatusCode());
		assertEquals(ResponseEntity.class, responses.getClass());
	}

	@Test
	void listarEnderecosDaPessoa() {
		when(service.listarEnderecosDaPessoaPorId(anyInt())).thenReturn(List.of(endereco));

		ResponseEntity<List<Endereco>> responses = controller.listarEnderecosDaPessoa(1);

		assertNotEquals(0, responses.getBody().size());
		assertEquals(Endereco.class, responses.getBody().get(0).getClass());
		assertEquals(HttpStatus.OK, responses.getStatusCode());
		assertEquals(ResponseEntity.class, responses.getClass());

	}

	@Test
	void enderecoPrincipalDaPessoaPorId() {
		when(service.enderecoPrincipalDaPessoaPorId(anyInt())).thenReturn(endereco);

		ResponseEntity<Endereco> response = controller.enderecoPrincipalDaPessoaPorId(1);

		assertNotNull(response);
		assertEquals(Endereco.class, response.getBody().getClass());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(HttpStatus.OK, response.getStatusCode());

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
