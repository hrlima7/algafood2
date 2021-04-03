package br.com.roma.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.roma.infraestructure.core.validation.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@NotBlank
//	@NotEmpty
	@NotNull(groups = Groups.CadastroRestaurante.class)
	private String nome;
	
	private Boolean ativo = Boolean.TRUE;
	
	private Boolean aberto = Boolean.FALSE;

	//@NotNull
	//@PositiveOrZero(message= "{TaxaFrete.invalida}")
	@DecimalMin("1")
	@Column(name="taxa_frete")
	private BigDecimal taxaFrete;
	
	@CreationTimestamp
	@Column(columnDefinition= "dateTime")
	private LocalDateTime dataCadastro;
	
	@UpdateTimestamp
	@Column(columnDefinition = "dateTime")
	private LocalDateTime dataAtualizacao;
	
	//@Valid
	//@NotNull(groups = Groups.CadastroRestaurante.class)
	//@JsonBackReference
	//@JsonIgnore
	@ManyToOne
	@JoinColumn(name="cozinha_id")
	public Cozinha cozinha;
	
	@JsonIgnore
	@Embedded
	private Endereco endereco;

	@ManyToMany
	@JoinTable(name="Restaurante_forma_pagamento",
			joinColumns= @JoinColumn(name="restaurante_id_pag"),
			inverseJoinColumns = @JoinColumn(name = "forma_pagamentoId"))
	private Set<FormaPagamento> formasPagamentos = new HashSet<>();
	
	
	@ManyToMany
	@JoinTable(name="restaurante_usuario_responsavel",
			joinColumns = @JoinColumn(name="restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> responsaveis = new HashSet<>();
	
	
	@OneToMany(mappedBy= "restaurante" )
	private List<Produto> produtos = new ArrayList<>();

	public void ativar() {
		this.ativo = true;
	}
	public void inativar() {
		this.ativo = false;
	}
	
	
	
	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamentos().remove(formaPagamento);
	}

	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamentos().add(formaPagamento);
	}
	
	public boolean adicionarProduto(Produto produto) {
		return getProdutos().add(produto);
	}

	public void aberto() {
		setAberto(true);
	}
	public void fechado() {
		setAberto(false);
	}

	public boolean associarResponsavel(Usuario usuario){
		return getResponsaveis().add(usuario);
		
	}
	
	public boolean desassociarResponsavel(Usuario usuario){
		return getResponsaveis().remove(usuario);
		
	}
	

	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return getFormasPagamentos().contains(formaPagamento);
	}

	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
	    return !aceitaFormaPagamento(formaPagamento);
	}

	
}
