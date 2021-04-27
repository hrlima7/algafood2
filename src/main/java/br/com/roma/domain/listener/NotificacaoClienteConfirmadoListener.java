package br.com.roma.domain.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.roma.domain.event.PedidoConfirmadoEvent;
import br.com.roma.domain.model.Pedido;
import br.com.roma.domain.model.service.EnvioEmailService;
import br.com.roma.domain.model.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClienteConfirmadoListener {

	@Autowired
	private EnvioEmailService envioEmail;
	
	
	@EventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		Pedido pedido = event.getPedido();
		
		Mensagem messagem =  Mensagem.builder()
		.assunto(pedido.getRestaurante().getNome()+"Pedido confirmado")
		.corpo("pedidoCOnfirmado.html")
		.variavel("pedido", pedido)
		.destinatario(pedido.getCliente().getEmail())
		.build();
		
		
		envioEmail.enviar(messagem);
		
		
	}
}
