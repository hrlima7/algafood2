package br.com.roma.infraestructure;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.model.Cidade;
import br.com.roma.domain.model.repository.CidadeRepository;
/*
**public class CidadeRepositoryImpl implements CidadeRepository {

	@Autowired
	EntityManager manager;
	
	@Override
	public List<Cidade> listar() {
		
		return manager.createQuery("from Cidade",Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	
	}

	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Override
	@Transactional
	public void remover(Long id) {
		
		Cidade cidade = buscar(id);
		
		 manager.remove(cidade);
		
	}

}
*/
