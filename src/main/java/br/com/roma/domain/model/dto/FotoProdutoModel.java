package br.com.roma.domain.model.dto;

import javax.persistence.MapsId;

import br.com.roma.domain.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FotoProdutoModel {

	private String nomeArquivo;
	private String descricao;
	private String contentType;
	private Long tamanho;
	
	
}
