package br.com.roma.domain.model.service.infraestructure;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

import br.com.roma.domain.filter.VendaDiariaFilter;
import br.com.roma.domain.model.Pedido;
import br.com.roma.domain.model.dto.VendaDiaria;
import br.com.roma.infraestructure.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements  VendaQueryService {
	@PersistenceContext
	EntityManager manager;
	
	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		 CriteriaBuilder builder = manager.getCriteriaBuilder();
		 CriteriaQuery<VendaDiaria>	query = builder.createQuery(VendaDiaria.class);
		 Root<Pedido> root = query.from(Pedido.class);
 
		List<Predicate> predicates = new ArrayList<>();
		
		//if (filtro.getRestauranteId() != null) {
		//    predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
		//	}
		
		 
		 Selection<VendaDiaria> selection = builder.construct(VendaDiaria.class
				, builder.function("date", Date.class, root.get("datacriacao"))
				, builder.count(root.get("id"))
				, builder.sum(root.get("ValorTotal")));
				 
		query.select(selection);
		
	 	//query.where(predicates.toArray(new Predicate[0]));
		
		query.groupBy(builder.function("date", LocalDate.class, root.get("datacriacao")));
		
	
		return manager.createQuery(query).getResultList();
		
	}
	
}
