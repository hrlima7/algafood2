package br.com.roma.api.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.FormaPagamento;
import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.service.FormaPagamentoService;
import br.com.roma.domain.model.service.RestauranteCadastroService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoCotroller {
	
	@Autowired
	RestauranteCadastroService restauranteService;
	
	@Autowired
	FormaPagamentoService formaPagamentoService;
	
	@GetMapping
	private Collection<FormaPagamento> listarPagamento(@PathVariable Long restauranteId){
		Restaurante restaurante = restauranteService.buscar(restauranteId);
	
		return restaurante.getFormasPagamentos();
	}
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, 
			@PathVariable Long formaPagamentoId) {
		restauranteService.desassociarFormaPagamento(restauranteId,formaPagamentoId);
		
	}
	
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, 
			@PathVariable Long formaPagamentoId) {
		restauranteService.associarFormaPagamento(restauranteId,formaPagamentoId);
		
	}
	
	
}









