package com.casa.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casa.api.models.Endereco;
import com.casa.api.models.dtos.PessoaDto;
import com.casa.api.services.PessoaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping("/guardar")
	public ResponseEntity<PessoaDto> criarPessoa(@Valid @RequestBody PessoaDto pessoa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaService.criarPessoa(pessoa));
	}

	@PatchMapping("/editar/{id}")
	public ResponseEntity<PessoaDto> editarPessoa(@Valid @RequestBody PessoaDto pessoa, @PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.editarPessoa(id, pessoa));
	}

	@GetMapping("/consultar/{id}")
	public ResponseEntity<PessoaDto> consultarPessoa(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.consultarPessoaPorId(id));
	}

	@GetMapping("/listar")
	public ResponseEntity<List<PessoaDto>> listarPessoas() {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarPessoas());
	}

	@GetMapping("/listar-enderecos-pessoa")
	public ResponseEntity<List<Endereco>> listarEnderecosDaPessoa(@RequestParam("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listarEnderecosDaPessoaPorId(id));
	}

	@GetMapping("/buscar-endereco-principal")
	public ResponseEntity<Endereco> enderecoPrincipalDaPessoaPorId(@RequestParam("id") Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.enderecoPrincipalDaPessoaPorId(id));
	}

}
