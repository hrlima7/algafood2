package br.com.roma.domain.filter;

import java.time.OffsetDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class VendaDiariaFilter {

	private Long restauranteId;
	
	@DateTimeFormat(iso =ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoInicio;
	
	@DateTimeFormat(iso =ISO.DATE_TIME)
	private OffsetDateTime dataCriacaoFim;

}
