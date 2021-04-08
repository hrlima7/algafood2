package br.com.roma.domain.model.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.model.FotoProduto;
import br.com.roma.domain.model.repository.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		
		Long restauranteId = foto.getProduto().getRestaurante().getId();
		Long produtoId = foto.getProduto().getId();
		
		
		Optional<FotoProduto>  fotoExistente = produtoRepository
				.findFotoById(restauranteId, produtoId);
		
		if(fotoExistente.isPresent()) {
			produtoRepository.deleteFoto(fotoExistente.get());
		}
		
		
	return produtoRepository.save(foto);
	}
	
	
	
	
	
	
}
