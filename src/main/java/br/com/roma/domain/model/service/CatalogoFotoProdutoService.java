package br.com.roma.domain.model.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.model.FotoProduto;
import br.com.roma.domain.model.repository.ProdutoRepository;
import br.com.roma.domain.model.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivos) {
		
		Long restauranteId = foto.getProduto().getRestaurante().getId();
		Long produtoId = foto.getProduto().getId();
		
		
		Optional<FotoProduto>  fotoExistente = produtoRepository
				.findFotoById(restauranteId, produtoId);
		
		if(fotoExistente.isPresent()) {
			produtoRepository.deleteFoto(fotoExistente.get());
		}
		
		foto = produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder().
				nomeArquivo(foto.getNomeArquivo()).
				inputStream(dadosArquivos)
				.build();
				
		fotoStorageService.armazenar(novaFoto);
		
	return foto  ;
	}
	
	
	
	
	
	
}
