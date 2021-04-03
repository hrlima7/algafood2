package br.com.roma.infraestructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.model.Estado;
import br.com.roma.domain.model.repository.EstadoRepository;
/*
@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	EntityManager manager;
	
	
	@Override
	public List<Estado> listar() {		
		return manager.createQuery("from Estado",Estado.class).getResultList();
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Override
	@Transactional
	public Estado salvar(Estado estado) {		
		return manager.merge(estado) ;
	}

	@Override
	@Transactional
	public void remover(Estado estado) {
		estado = buscar(estado.getId());
		manager.remove(estado);;
		
	}
	
	

}
*/
