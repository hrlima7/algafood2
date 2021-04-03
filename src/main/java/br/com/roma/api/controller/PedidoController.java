package br.com.roma.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.Pedido;
import br.com.roma.domain.model.repository.PedidoRespository;
import br.com.roma.domain.model.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	PedidoRespository  pedidoRepository;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Page<Pedido> listar(Pageable pageable){

		Page<Pedido> pedidoPage = pedidoRepository.findAll(pageable);
		
		List<Pedido> pedidoLista = pedidoPage.getContent();
		
		Page <Pedido> pedidoConteudo = new PageImpl(pedidoLista,pageable,pedidoPage.getTotalElements());
		
		return pedidoConteudo;
		
		
	}
	
	@GetMapping("/{pedidoId}")
	@ResponseStatus(HttpStatus.OK)
	public Pedido buscar(@PathVariable Long pedidoId) {
		return	pedidoService.buscarOuFalhar(pedidoId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public Pedido salvar(@RequestBody Pedido pedido) {
				
		return pedidoService.salvar(pedido);
	}
	
}
