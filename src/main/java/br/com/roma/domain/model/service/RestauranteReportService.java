package br.com.roma.domain.model.service;

import java.util.List;

import br.com.roma.domain.model.dto.RestauranteRelatorio;

public interface RestauranteReportService {
	
	List<RestauranteRelatorio> emitirReportRestaurante();
	
	
	

}
