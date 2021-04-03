package br.com.roma.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ProdutoNaoEncontradoException  extends EntidadeNaoEncontradaException{


			private static final long serialVersionUID = 1L;
			

			public ProdutoNaoEncontradoException(String mensagem) {
				super(mensagem);
			}
			

			public ProdutoNaoEncontradoException(Long produtoId) {
				this(String.format("Não existe um cadastro de produto %d", produtoId));
			}
			
			   public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
			        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d", 
			                produtoId, restauranteId));
			    }
			
			
	}