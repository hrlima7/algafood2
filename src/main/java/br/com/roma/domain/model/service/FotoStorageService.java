package br.com.roma.domain.model.service;

import java.io.InputStream;

import lombok.Builder;
import lombok.Getter;

public interface FotoStorageService {

	void armazenar(NovaFoto novaFoto);
	
	@Getter
	@Builder
	class NovaFoto{
		
		private String nomeArquivo;
		private InputStream inputStream;
		
		
	}
}
