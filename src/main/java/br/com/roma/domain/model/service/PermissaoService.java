package br.com.roma.domain.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.roma.domain.exception.GrupoNaoEncontradoException;
import br.com.roma.domain.model.Permissao;
import br.com.roma.domain.model.repository.PermissaoRepository;

@Service
public class PermissaoService {

	
	@Autowired
	PermissaoRepository  permissaoRepository;

	
	
	public List<Permissao> listar (){
		return	permissaoRepository.findAll();
		
	}
	
	public Permissao buscarOufalhar (Long permissaoId){
	return	permissaoRepository.findById(permissaoId).orElseThrow(
		() -> new GrupoNaoEncontradoException(permissaoId));
				
	}
	
	public Permissao salvar(Permissao permissao) {
		return	permissaoRepository.save(permissao);
		
	}
	
	public void deletar(Long permissaoId) {
		Permissao permissao = buscarOufalhar(permissaoId);
		permissaoRepository.delete(permissao);
	}

	


}
	
	
	