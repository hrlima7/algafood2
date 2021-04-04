package br.com.roma.domain.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RestauranteRelatorio {
	
	private String nome;
	private BigDecimal TaxaFrete;

}
