package br.com.roma.domain.model.service.infraestructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import br.com.roma.domain.model.Restaurante;
import br.com.roma.domain.model.dto.RestauranteRelatorio;
import br.com.roma.domain.model.service.RestauranteReportService;

@Repository
public class RestauranteQueryServiceImpl implements RestauranteReportService {

	@Autowired
	EntityManager manager;
	
	@Override
	public List<RestauranteRelatorio> emitirReportRestaurante() {
	
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<RestauranteRelatorio> query = builder.createQuery(RestauranteRelatorio.class);
		Root<Restaurante> root = query.from(Restaurante.class);
	
				query.multiselect(root.get("nome"),root.get("taxaFrete"));
			
		
				List<RestauranteRelatorio> usuarios = manager.createQuery(query).getResultList();
				return usuarios;
	}

	
	

}
