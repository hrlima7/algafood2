package br.com.roma.api.controller.estatistica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.dto.RestauranteRelatorio;
import br.com.roma.domain.model.service.RestauranteReportService;

@RestController
@RequestMapping("/estatisticas/restaurantes")
public class RestauranteEstatisticaController {

	@Autowired
	RestauranteReportService restauranteReporteService;
	
	@GetMapping
	public List<RestauranteRelatorio> listar(){
		return restauranteReporteService.emitirReportRestaurante();
		
	}
}
