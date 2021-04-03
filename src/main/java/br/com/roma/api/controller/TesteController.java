package br.com.roma.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.Cozinha;
import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.repository.CozinhaRepository;
import br.com.roma.domain.model.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> consultaPorNome(@RequestParam("nome") String nome){
		
		return cozinhaRepository.findByNomeContaining(nome);
	}

	
	
	@GetMapping("/restaurantes/por-taxa")
	public List<Restaurante> RestaurantesPorNomeFrete(String nome,
			BigDecimal taxaFreteInicial,BigDecimal taxaFreteFinal){
		
		return  restauranteRepository.find(nome,taxaFreteInicial,taxaFreteFinal);
	}
	

	
}
