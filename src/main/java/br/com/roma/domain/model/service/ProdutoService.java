package br.com.roma.domain.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.roma.domain.exception.ProdutoNaoEncontradoException;
import br.com.roma.domain.model.Produto;
import br.com.roma.domain.model.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired	
	ProdutoRepository produtoRepository;
	
	@Autowired
	RestauranteCadastroService produtoService;
	
	public List<Produto> listar(){
			return produtoRepository.findAll();
	
	}

	public Produto buscarOuFalhar(@PathVariable Long produtoId){
		return produtoRepository.findById(produtoId).
				orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));

	}

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public void deletar(Produto produto) {
		 produtoRepository.delete(produto);
	}
	
	  public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
	        return produtoRepository.findById(restauranteId, produtoId)
	            .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	    }
	
	
	
}
