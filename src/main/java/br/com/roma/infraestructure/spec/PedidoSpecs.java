package br.com.roma.infraestructure.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import br.com.roma.domain.filter.PedidoFilter;
import br.com.roma.domain.model.Pedido;

public class PedidoSpecs {
	
	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro){
		return (root, query, builder) -> {
			
			ArrayList<Predicate> predicates = new ArrayList<Predicate>();
			
			if (filtro.getClientId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filtro.getClientId()));	
			}
			
			
			
			
			
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
