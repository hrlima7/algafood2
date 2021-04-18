package br.com.roma.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import br.com.roma.domain.event.PedidoConfirmadoEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
public class Pedido extends AbstractAggregateRoot<Pedido> {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	private String codigo;
	
	private BigDecimal subtotal;
	
	private BigDecimal taxaFrete;
	
	private BigDecimal ValorTotal;
	
	@Embedded
	private Endereco  enderecoEntrega;
	
	@CreationTimestamp
	private OffsetDateTime datacriacao;
	
	private OffsetDateTime dataCancelamento;
	
	private OffsetDateTime dataEntrega;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	@ManyToOne
	private FormaPagamento formaPagamento;
	
	@ManyToOne
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name= "usuario_cliente_id")
	private Usuario cliente;
	 
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();
	
	public void calcularValorTotal() {
	    this.subtotal = getItens().stream()
	        .map(item -> item.getPrecoTotal())
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    this.ValorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void definirFrete() {
	    setTaxaFrete(getRestaurante().getTaxaFrete());
	}

	public void atribuirPedidoAosItens() {
	    getItens().forEach(item -> item.setPedido(this));
	}
	
	
	public void confirmar() {
		setStatus(StatusPedido.CONFIRMADO);
		setDatacriacao(OffsetDateTime.now());
		
		registerEvent(new PedidoConfirmadoEvent(this));
		
	}
	
	
	
}
