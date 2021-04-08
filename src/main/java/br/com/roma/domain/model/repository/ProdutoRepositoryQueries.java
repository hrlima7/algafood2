package br.com.roma.domain.model.repository;

import br.com.roma.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto save(FotoProduto foto);
	
	void deleteFoto(FotoProduto foto);

}
