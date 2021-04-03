package br.com.roma.domain.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.roma.domain.exception.EntidadeEmUsoException;
import br.com.roma.domain.exception.EntidadeNaoEncontradaException;
import br.com.roma.domain.exception.EstadoNaoEncontradaException;
import br.com.roma.domain.model.Estado;
import br.com.roma.domain.model.repository.EstadoRepository;

@Service
public class EstadoService {
	
	private static final String MSG_ESTADO_EM_USO 
	= "Estado de código %d não pode ser removida, pois está em uso";

	
@Autowired
private EstadoRepository estadoRepository;



public Estado salvar(Estado estado) {
	Long estadoId = estado.getId();

	Estado estadonovo = buscarOuFalhar(estadoId);
	
//	Estado estado = estadoRepository.findById(estadoId)
//		.orElseThrow(() -> new EntidadeNaoEncontradaException(
//				String.format("Não existe cadastro de estado com código %d", estadoId)));
	
	return estadoRepository.save(estadonovo);
}

public void excluir(Long estadoId) {
	try {
		estadoRepository.deleteById(estadoId);
		
	} catch (EmptyResultDataAccessException e) {
		throw new EstadoNaoEncontradaException(estadoId);
	
	} catch (DataIntegrityViolationException e) {
		throw new EntidadeEmUsoException(
			String.format(MSG_ESTADO_EM_USO, estadoId));
	}
}


public Estado buscarOuFalhar(Long estadoId) {
	return estadoRepository.findById(estadoId)
		.orElseThrow(() -> new EstadoNaoEncontradaException(estadoId));
}


}