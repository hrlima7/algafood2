package br.com.roma.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.roma.domain.model.Grupo;
import br.com.roma.domain.model.Permissao;

@Service
public class GrupoPermissaoService {

	@Autowired
	PermissaoService permissaoService;
	
	@Autowired
	GrupoService grupoService;
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId){
			Grupo grupo =  grupoService.buscarOufalhar(grupoId);
			Permissao permissao  = permissaoService.buscarOufalhar(permissaoId);
				grupo.adicionarPermissao(permissao);
	
	}
	@Transactional
	public void  desassociarPermissao(Long grupoId,Long permissaoId) {
		Grupo grupo =  grupoService.buscarOufalhar(grupoId);
		Permissao permissao  = permissaoService.buscarOufalhar(permissaoId);
			grupo.removerPermissao(permissao);
	}
	
	
	
}
