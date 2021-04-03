package br.com.roma.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

		private static final long serialVersionUID = 1L;
		

		public FormaPagamentoNaoEncontradaException(String mensagem) {
			super(mensagem);
		}
		

		public FormaPagamentoNaoEncontradaException(Long pagamentoId) {
			this(String.format("Não existe um cadastro de Forma de pagamento com código %d", pagamentoId));
		}
}
