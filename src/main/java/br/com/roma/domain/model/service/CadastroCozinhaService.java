package br.com.roma.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.roma.domain.exception.CozinhaNaoEncontradaException;
import br.com.roma.domain.exception.EntidadeEmUsoException;
import br.com.roma.domain.model.Cozinha;
import br.com.roma.domain.model.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO 
			= "Cozinha de codigo %d não pode ser removida, pois está em uso";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	

	public Cozinha salvar(Cozinha cozinha) {		
		return  cozinhaRepository.save(cozinha);
	}
	
	
	
	public void excluir (Long id) {
		try {
			cozinhaRepository.deleteById(id);
		
	  	}catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO,id));
		}
		
	}
	
}
