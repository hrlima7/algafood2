package br.com.roma.infraestructure;

import java.util.List;

import br.com.roma.domain.filter.VendaDiariaFilter;
import br.com.roma.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
