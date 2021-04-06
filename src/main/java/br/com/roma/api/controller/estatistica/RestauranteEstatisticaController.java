package br.com.roma.api.controller.estatistica;

import java.util.List;

import org.bouncycastle.crypto.tls.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.roma.domain.model.dto.RestauranteRelatorio;
import br.com.roma.domain.model.service.RestauranteReportService;
import br.com.roma.domain.model.service.infraestructure.RestauranteReportServicePDF;

@RestController
@RequestMapping("/estatisticas")
public class RestauranteEstatisticaController {

	@Autowired
	RestauranteReportService restauranteReporteService;
	
	@Autowired
	RestauranteReportServicePDF restaurantePDF;
	
	@GetMapping(path= "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RestauranteRelatorio> listar(){
		return restauranteReporteService.emitirReportRestaurante();
		
	}
	
	@GetMapping(path = "/restaurantes", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<byte[]> listarPDF(){
		
		byte[] relatorio =  restaurantePDF.emitirReportRestaurantePDF();
		
		HttpHeaders headers = new HttpHeaders();
				headers.add(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=Restaurante.pdf");
		
				
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.headers(headers)
				.body(relatorio);
			
		
	}
	
}
