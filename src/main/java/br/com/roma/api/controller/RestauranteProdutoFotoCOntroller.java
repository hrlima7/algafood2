package br.com.roma.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.roma.api.model.input.FotoProdutoInput;
import br.com.roma.domain.model.FotoProduto;
import br.com.roma.domain.model.Produto;
import br.com.roma.domain.model.dto.FotoProdutoModel;
import br.com.roma.domain.model.service.CatalogoFotoProdutoService;
import br.com.roma.domain.model.service.ProdutoService;

@RestController
@RequestMapping("/restaurante/{restuarnateId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoCOntroller {
	
	@Autowired
	private ProdutoService produtoService;
	
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProduto atualizarFoto(@PathVariable Long restauranteId,
					@PathVariable Long produtoId, FotoProdutoInput fotoProdutoInput ) {
		
			Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
	
			MultipartFile arquivo =  fotoProdutoInput.getArquivo();
			
				FotoProduto foto =  new FotoProduto();
				foto.setProduto(produto);
				foto.setDescricao(fotoProdutoInput.getDescricao());
				foto.setContentType(arquivo.getContentType());
				foto.setTamanho(arquivo.getSize());
				foto.setNomeArquivo(arquivo.getOriginalFilename());
				
				
				FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto);
					return fotoSalva;
			
		
	}

}
