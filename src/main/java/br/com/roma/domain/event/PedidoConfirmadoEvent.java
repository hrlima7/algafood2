package br.com.roma.domain.event;

import br.com.roma.domain.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
//@AllArgsConstructor
public class PedidoConfirmadoEvent {

	private Pedido pedido;

	public PedidoConfirmadoEvent(Pedido pedido) {
		super();
		this.pedido = pedido;
	}
	
	
}
