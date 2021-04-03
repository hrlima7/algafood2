package br.com.roma.api.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.Usuario;
import br.com.roma.domain.model.service.RestauranteCadastroService;

@RestController
@RequestMapping(value="/restaurantes/{restauranteId}/usuarios")
public class RestauranteResponsavel {
	
	@Autowired
	RestauranteCadastroService restauranteService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Set<Usuario> buscarResponsavel(@PathVariable Long restauranteId){
	
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		return restaurante.getResponsaveis();
	}
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarResponsavel(@PathVariable Long restauranteId,
					@PathVariable Long usuarioId) {
			restauranteService.associarResponsavel(restauranteId, usuarioId);
		
	}
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarResponsavel(@PathVariable Long restauranteId,
						@PathVariable Long usuarioId){
		restauranteService.desassociarResponsavel(restauranteId, usuarioId);
		
		
	}
	

	
	
}
