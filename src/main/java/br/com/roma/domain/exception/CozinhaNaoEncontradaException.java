package br.com.roma.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class CozinhaNaoEncontradaException  extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;
	

	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	

	public CozinhaNaoEncontradaException(Long cidadeId) {
		this(String.format("Não existe um cadastro de cozinha !!!! com código %d", cidadeId));
	}

	
	
}
