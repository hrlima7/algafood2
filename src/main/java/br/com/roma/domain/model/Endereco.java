package br.com.roma.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Endereco {
	@Column(name ="Endereco_cep")
	private String cep;
	
	@Column(name ="Endereco_logradouro")
	private String Logradouro;
	
	@Column(name ="Endereco_numero")
	private String numero;
	
	@Column(name ="Endereco_complemento")
	private String complemento;
	
	@Column(name ="Endereco_bairro")
	private String bairro;
	
	@ManyToOne
	@JoinColumn(name ="Endereco_cidade")
	private Cidade cidade;
	
	
	

}
