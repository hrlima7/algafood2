package br.com.roma.infraestructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import br.com.roma.domain.model.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	private 
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		
		try {
			
			Path arquivoPath = Paths.get("/tmp/COSTELA", novaFoto.getNomeArquivo());
		
			FileCopyUtils.copy(novaFoto.getInputStream(),
					  Files.newOutputStream(arquivoPath));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		  
	}

}
