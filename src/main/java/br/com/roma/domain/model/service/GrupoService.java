package br.com.roma.domain.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roma.domain.exception.GrupoNaoEncontradoException;
import br.com.roma.domain.model.Grupo;
import br.com.roma.domain.model.repository.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	GrupoRepository grupoRepository;
	
	
	public List<Grupo> listar (){
		return	grupoRepository.findAll();
		
	}
	
	public Grupo buscarOufalhar (Long grupoId){
	return	grupoRepository.findById(grupoId).orElseThrow(
		() -> new GrupoNaoEncontradoException(grupoId));
				
	}
	
	public Grupo salvar(Grupo grupo) {
		return	grupoRepository.save(grupo);
		
	}
	
	public void deletar(Long grupoId) {
		Grupo grupo = buscarOufalhar(grupoId);
			 grupoRepository.delete(grupo);
	}

	
	
	

}
