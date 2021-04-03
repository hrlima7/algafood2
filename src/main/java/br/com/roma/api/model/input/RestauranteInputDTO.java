package br.com.roma.api.model.input;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestauranteInputDTO {
		
		
		private String nome;
		private BigDecimal taxaFrete;
		private CozinhaIdInput cozinhaId;
		private Boolean aberto;
}
