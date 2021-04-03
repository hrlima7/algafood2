package br.com.roma.api.model;

import com.fasterxml.jackson.annotation.JsonView;

import br.com.roma.domain.model.view.CozinhaView;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CozinhaModelDTO {

	private Long id;
	
	@JsonView(CozinhaView.CozinhaResumo.class)
	private String nome;
	
	
	
}
