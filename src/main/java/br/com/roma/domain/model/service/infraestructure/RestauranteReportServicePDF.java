package br.com.roma.domain.model.service.infraestructure;

import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteReportServicePDF {
	
	byte[] emitirReportRestaurantePDF();

}
