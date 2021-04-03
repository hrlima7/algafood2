package br.com.roma.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class PedidoNaoEncontradoException  extends EntidadeNaoEncontradaException{


			private static final long serialVersionUID = 1L;
			

			public PedidoNaoEncontradoException(String mensagem) {
				super(mensagem);
			}
			

			public PedidoNaoEncontradoException(Long produtoId) {
				this(String.format("Não existe um pedido de produto %d", produtoId));
			}
			
			   public PedidoNaoEncontradoException(Long restauranteId, Long produtoId) {
			        this(String.format("Não existe um pedido de produto com código %d para o restaurante de código %d", 
			                produtoId, restauranteId));
			    }
			
			
	}