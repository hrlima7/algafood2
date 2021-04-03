package br.com.roma.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


	@ResponseStatus(value= HttpStatus.NOT_FOUND)
	public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException {

			private static final long serialVersionUID = 1L;
			

			public GrupoNaoEncontradoException(String mensagem) {
				super(mensagem);
			}
			

			public GrupoNaoEncontradoException(Long pagamentoId) {
				this(String.format("Não existe um Grupo com código %d", pagamentoId));
			}
	}