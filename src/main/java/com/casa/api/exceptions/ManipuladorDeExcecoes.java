package com.casa.api.exceptions;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ManipuladorDeExcecoes {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<Erro> naoEncontrado(ObjetoNaoEncontradoException e, HttpServletRequest request) {
		Erro erro = new Erro(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDate.now(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Erro> argumentoInvalido(MethodArgumentNotValidException e, HttpServletRequest request) {
		Erro erro = new Erro("Argumento inválido!", HttpStatus.BAD_REQUEST.value(), LocalDate.now(),
				request.getRequestURI());

		e.getBindingResult().getFieldErrors().forEach(err -> {
			erro.getCampos().add(new Campo(err.getField(), err.getDefaultMessage()));
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(EnderecoException.class)
	public ResponseEntity<Erro> enderecoInvalido(EnderecoException e, HttpServletRequest request) {
		Erro erro = new Erro(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDate.now(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(DateTimeParseException.class)
	public ResponseEntity<Erro> dataInvalida(DateTimeParseException e, HttpServletRequest request) {
		Erro erro = new Erro("Data fora do seguinte padrão: 10/05/2005", HttpStatus.BAD_REQUEST.value(),
				LocalDate.now(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

}
