package br.com.roma.infraestructure;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.model.FotoProduto;
import br.com.roma.domain.model.repository.ProdutoRepositoryQueries;
@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@Transactional
	@Override
	public FotoProduto save(FotoProduto foto) {
		return manager.merge(foto);
	}

	@Transactional
	@Override
	public void deleteFoto(FotoProduto foto) {
		manager.remove(foto);
		
	}


	
	
	
	
	
	
	
	
	
	

}
