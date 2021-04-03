package br.com.roma.domain.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roma.domain.exception.PedidoNaoEncontradoException;
import br.com.roma.domain.model.Pedido;
import br.com.roma.domain.model.StatusPedido;
import br.com.roma.domain.model.repository.PedidoRespository;

@Service
public class PedidoService {
	
	@Autowired
	PedidoRespository pedidoRepository;
	
	public List<Pedido> buscar(){
		return pedidoRepository.findAll();
		
	}
	
	
		public Pedido buscarOuFalhar(Long pedidoId) {	
			return pedidoRepository.findById(pedidoId).
					orElseThrow( ()-> new PedidoNaoEncontradoException (pedidoId));
		}
		
		public Pedido salvar(Pedido pedido) {
				pedido.setStatus(pedido.getStatus().CRIADO);
					return pedidoRepository.save(pedido);
			
		}

	

}
