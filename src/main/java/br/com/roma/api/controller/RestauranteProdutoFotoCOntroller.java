package br.com.roma.api.controller;


import java.nio.file.Path;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/restaurante/{restuarnatesId}/produtos/{produtosId}/foto")
public class RestauranteProdutoFotoCOntroller {
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restaurantesId,
					@PathVariable Long produtosId, @RequestParam MultipartFile arquivo ) {
		
		String nomeArquivo = UUID.randomUUID().toString()+ "_" + 
		arquivo.getOriginalFilename();
				
					
				Path arquivoFoto = Path
			
		
	}

}