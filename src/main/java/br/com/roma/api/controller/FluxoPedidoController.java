package br.com.roma.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.service.FluxoPedidoService;

@RestController
@RequestMapping("/pedidos/{pedidoId}")
public class FluxoPedidoController {
	
	@Autowired
	private FluxoPedidoService fluxoPedidoService;
	
	
	@PutMapping("/confirmacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmar(@PathVariable Long pedidoId) {
		fluxoPedidoService.confirmar(pedidoId);
		
	}
	
	@PutMapping("/entregue")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void entregue(@PathVariable Long pedidoId) {
		fluxoPedidoService.entregue(pedidoId);
		
	}
	@PutMapping("/cancelado")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelado(@PathVariable Long pedidoId) {
		fluxoPedidoService.cancelado(pedidoId);
		
	}
	
	
}
