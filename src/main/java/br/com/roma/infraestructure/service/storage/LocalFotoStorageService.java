package br.com.roma.infraestructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import br.com.roma.domain.model.service.FotoStorageService;

@Service
public class LocalFotoStorageService implements FotoStorageService {

	//@Value("algafood.storage.local.diretorio-fotos")
	private Paths diretorioFotos;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		
		try {
			
			Path arquivoPath = Paths.get("/tmp/COSTELA", novaFoto.getNomeArquivo());
		
			FileCopyUtils.copy(novaFoto.getInputStream(),
					  Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			
		throw new StorageException("Não foi possivel armazenar o arquivo", e);
		}
		  
	}

}
