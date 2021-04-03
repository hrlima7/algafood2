package br.com.roma.infraestructure;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.roma.domain.model.Restaurante;
@Repository
public class RestauranteRepositoryImpl {
	
	@Autowired
	EntityManager manager;
	
	
	
	public List<Restaurante> find(String nome,BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal){
		
		String jpql = "from Restaurante where nome like :nome and taxaFrete Between"+
		 ":taxaInicial and :taxaFinal ";
		 return manager.createQuery(jpql,Restaurante.class)
				 .setParameter("nome","%" +nome +"%")
				 .setParameter("taxaInicial", taxaFreteInicial)
				 .setParameter("taxaFinal",taxaFreteFinal)
				 .getResultList();
		
	}

}
