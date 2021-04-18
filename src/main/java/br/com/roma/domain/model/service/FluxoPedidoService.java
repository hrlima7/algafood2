package br.com.roma.domain.model.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.exception.NegocioException;
import br.com.roma.domain.model.Pedido;
import br.com.roma.domain.model.StatusPedido;
import br.com.roma.domain.model.repository.PedidoRespository;

@Service
public class FluxoPedidoService {

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoRespository pedidoRepository;
	
	
	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
				
		if(!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(
					String.format("Status do pedido %d não pode ser alterado para %s",
							pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO));
		}
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDatacriacao(OffsetDateTime.now());
		

	}
	
	@Transactional
	public void entregue(Long pedidoId) {
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
				
		if(!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
			throw new NegocioException(
					String.format("Status do pedido %d não pode ser alterado para %s",
							pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO));
		}
		pedido.setStatus(StatusPedido.ENTREGUE);
		pedido.setDatacriacao(OffsetDateTime.now());
		
	}
	
	@Transactional
	public void cancelado(Long pedidoId) {
		Pedido pedido = pedidoService.buscarOuFalhar(pedidoId);
				
		if(!pedido.getStatus().equals(StatusPedido.ENTREGUE) &&
					!pedido.getStatus().equals(StatusPedido.CONFIRMADO)) {
			throw new NegocioException(
					String.format("Status do pedido %d não pode ser alterado para %s",
							pedido.getId(), pedido.getStatus(), StatusPedido.CONFIRMADO));
		}
		pedido.setStatus(StatusPedido.CANCELADO);
		pedido.setDatacriacao(OffsetDateTime.now());
		
	}


	
	

}
