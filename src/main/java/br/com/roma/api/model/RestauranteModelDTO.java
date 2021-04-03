package br.com.roma.api.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.view.RestauranteView;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModelDTO {
	
		@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
		private Long id;
		@JsonView({RestauranteView.Resumo.class,RestauranteView.ApenasNome.class})
		private String nome;
		@JsonView(RestauranteView.Resumo.class)
	    private BigDecimal taxaFrete;
		
		private CozinhaModelDTO cozinha;
		
		@JsonView(RestauranteView.Resumo.class)
		private Boolean ativo =  Boolean.TRUE;
		
		@JsonView(RestauranteView.Resumo.class)
		private Boolean aberto = Boolean.FALSE;
		
		
		
		
		
		
	

}
